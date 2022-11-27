package edu.curtin.maze.read;
import edu.curtin.maze.Maze;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/** 
 * A reader of maze files, using one or more LineReaders to handle specific types of lines within 
 * the file.
 */
public class MazeReader
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(MazeReader.class.getName());
    
    private Map<String,LineReader> lineReaders = new HashMap<>();

    public MazeReader() {}
    
    /** Specify one or more LineReaders. This must be called before readMaze(). */     
    public void addLineReaders(LineReader... lineReaders)
    {
        for(LineReader r : lineReaders)
        {
            this.lineReaders.put(r.label(), r);
        }
    }
        
    /**
     * Read and parse a maze file, returning a new Maze object, and populating a list of warnings
     * (if any are encountered).
     */
    public Maze readMaze(String filename, List<MazeNonFatalException> warnings) throws MazeReadException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            ValueReader vr = new ValueReader(reader.readLine());
            Maze maze;
            try
            {
                // Create the maze based on the row/col dimensions on the first line of the file.
                maze = new Maze(vr.readIntAtLeast(1), vr.readIntAtLeast(1));
            }
            catch(ValueOutOfBoundsException e)
            {
                // ValueOutOfBoundsException is normally a non-fatal error, but if the maze 
                // dimensions are invalid, we can't really recover from that, so substitute a fatal
                // error instead.
                String msg = "Maze dimensions must be at least 1x1";
                logger.log(Level.SEVERE, msg, e);
                throw new MazeReadException(msg, e);
            }
            vr.verifyEmpty();
            
            // Process each subsequent line by finding and invoking the appropriate LineReader.
            while(vr.nextLine(reader))
            {
                String label = vr.readWord();
                LineReader lineReader = lineReaders.get(label);
                if(lineReader == null)
                {
                    String msg = "Unknown operator: '" + label + "'";
                    logger.severe(msg);
                    throw new MazeReadException(msg);
                }
                try
                {
                    logger.fine(() -> "Reading '" + label + "' line");
                    lineReader.read(vr, maze);
                }
                catch(MazeNonFatalException e)
                {
                    // Accumulate non-fatal errors (which we'll call warnings) in a list, so the
                    // caller can deal with them.
                    logger.log(Level.WARNING, "Non-fatal maze file error", e);
                    warnings.add(e);
                }
                vr.verifyEmpty();
            }
            
            // Quickly check that one or more maze end points have been specified.
            if(!maze.hasEnd())
            {
                String msg = "No 'end' square specified";
                logger.warning(msg);
                warnings.add(new MazeNonFatalException(msg));
            }
            
            return maze;
        }
        catch(IOException e)
        {
            logger.log(Level.SEVERE, "IO error reading maze", e);
            throw new MazeReadException("IO error reading maze", e);
        }        
    }
}
