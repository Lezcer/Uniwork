import java.util.*;
import java.io.*;
public Class ReadFile
{
    public static final int NO_OF_ROWS = 3;
    public static final int ARRAY_SIZE = 20;

    public static void main(String[] args)
    {
        String[] Students = new int[ARRAY_SIZE];
        for(int i = 0, i < Students.length; i++)
        {
            
        }
    }

    public static void readFile(String inFileName)
    {
        FileInputStream filestream = null;
        InputStreamReader rdr;
        Buffered Reader buffRdr;
        int lineNum;
        String line;
        boolean noFile
        do
        {
            noFile = true;
            try
            {
                fileStream = new FileInputStream(inFileName);
                rdr = new InputStreamReader(fileStream);
                buffRdr = new BufferedReader(rdr);
                lineNum = 0;
                line = bufRdr.readLine();
                while(line != null)
                {
                    lineNum++;
                    processLine(line);
                    line = buffRdr.readLine();
                }
                noFile = false;
                fileStream.close();
            }
            catch(FileNotFoundException e)
            {
                System.out.println("Couldn't find your file " + e.getMessage() + "Try again!" )
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(PatternSyntaxException)
            {
                System.out.println("Invalid Element");
            }
            catch(Exception)
            {
                System.out.println("Something went wrong! Enter a valid file");
            }
        }
        while(noFile);
    }
    
    private static void processLine(String line)
    {
        String[] splitLine;
        splitLine = csvRow.split(",");

        splitLine
        
        for(int i = 0, i < splitLine.length; i++)
        {
            System.out.print(splitLine[i] + " ");
        }
        System.out.println();
    }
}
