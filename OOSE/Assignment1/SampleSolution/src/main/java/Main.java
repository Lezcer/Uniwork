package edu.curtin.maze;
import edu.curtin.maze.read.*;
import java.util.*;
import java.util.logging.*;

/**
 * Entry point into the maze game.
 */
public class Main
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args)
    {
        String mazeFile;
        Scanner sc = new Scanner(System.in); // NOPMD
        
        // We can accept a maze file as a command-line argument, or via standard input.
        if(args.length > 0)
        {
            mazeFile = args[0];
        }
        else
        {
            System.out.printf("Enter maze filename (relative to cwd): ");
            mazeFile = sc.nextLine();
        }
        
        // Specify the LineReaders to be used while reading the maze file. Each LineReader is 
        // responsible for reading one type of input line, and each specifies the label that must 
        // occur at the beginning of the line, to identify the type.
        MazeReader mazeReader = new MazeReader();
        mazeReader.addLineReaders(
            new ReadStart(),
            new ReadEnd(),
            new ReadKey(),
            new ReadHorizontalWall(),
            new ReadVerticalWall(),
            new ReadHorizontalDoor(),
            new ReadVerticalDoor(),
            new ReadMessage());
    
        try
        {
            // Read the maze file, and accumulate any non-fatal errors in a list.
            List<MazeNonFatalException> warnings = new ArrayList<>();
            logger.info("Reading maze file");
            Maze maze = mazeReader.readMaze(mazeFile, warnings);
            
            boolean run = true;
            if(!warnings.isEmpty())
            {
                // If there were non-fatal errors (warnings), output them, and then ask the user 
                // whether to continue regardless.
                
                System.out.println("Warning:");
                for(MazeNonFatalException e : warnings)
                {
                    System.out.println("* " + e.getMessage());
                }
                
                String response;
                do
                {
                    System.out.print("Continue (y/n)? ");
                    response = sc.nextLine().trim().toLowerCase();
                }
                while(!response.equals("y") && !response.equals("n"));
                if(response.equals("n"))
                {
                    run = false;
                }
            }
            
            if(run)
            {
                // If everything is okay so far, create the other important game objects and start 
                // the game.         
                logger.info("Launching game");
                Player player = new Player(maze.getStart());
                Displayer displayer = new Displayer(maze, player);
                new GameEngine().run(maze, player, displayer);
            }
        }
        catch(MazeReadException e)
        {
            logger.log(Level.SEVERE, "Could not read maze file", e);
            System.err.println("Could not read maze: " + e.getMessage());
        }
    }
}
