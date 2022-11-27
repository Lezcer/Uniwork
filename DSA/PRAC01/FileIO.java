import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class FileIO
{
    public static int[] readFile(String inFileName)
    {
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader buffRdr;
        int arrayHeight, numOfLines;
        String line;
        boolean noFile;
        int[] listOfIDs = null;
        //Necessary Declarations

        try
        {
            fileStream = new FileInputStream(inFileName );
            rdr = new InputStreamReader(fileStream);
            buffRdr = new BufferedReader(rdr);
            //Setting up the file reader
            arrayHeight = 0;
            //This will indicate the row number
            line = buffRdr.readLine();
            //Line are being read
            numOfLines = getLineNum(inFileName);
            listOfIDs = new int[numOfLines];
            //Creating an array to store the integers
            while(line != null)
            {
                listOfIDs[arrayHeight] = processLine(line);
                arrayHeight++;
                //increments by 1 to change the row number of file AND the array

                line = buffRdr.readLine();
            }
            fileStream.close();
        }
        //EXCEPTION HANDLERS
        catch(FileNotFoundException e)
        {
            System.out.println("Couldn't find your file " + e.getMessage() + "Try again!");
        }
        catch(IOException e)
        {
            System.out.println("Couldn't read file: " + e.getMessage());
        }
        return listOfIDs;
    }
    
    public static void writeFile(String fileName, int[] writeArray)
    {
        FileOutputStream fileStream = null;
        PrintWriter pw;
        //Necessary Declarations

        try
        {
            fileStream = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStream);
            
            for(int i = 0; i < writeArray.length; i++)
            {
                pw.print(writeArray[i]);
                pw.println();
            }
            pw.close();
        }
        catch(IOException e)
        {
            if (fileStream != null)
            {
                try
                {
                    fileStream.close();
                }
                catch(IOException e2)
                {
                }
            }
            System.out.println("Error in writing to file: " + e.getMessage());
        }
    }
    
    public static int getLineNum(String inFileName)
    {
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader buffRdr;
        int numOfLines = 0;
        String line;
        //Necessary Declarations

        try
        {
            fileStream = new FileInputStream(inFileName );
            rdr = new InputStreamReader(fileStream);
            buffRdr = new BufferedReader(rdr);
            //Setting up the file reader
            line = buffRdr.readLine();
            //Line are being read
            while(line != null)
            {
                numOfLines++;
                //increments by 1 to change the row number of file AND the array

                line = buffRdr.readLine();
            }
            fileStream.close();
        }
        //EXCEPTION HANDLERS
        catch(FileNotFoundException e)
        {
            System.out.println("Couldn't find your file " + e.getMessage() + "Try again!");
        }
        catch(IOException e)
        {
            System.out.println("Couldn't read file: " + e.getMessage());
        }
        return numOfLines;
    }
    private static int processLine(String row)
    {
        String[] splitLine;
        //Creates array for each column of thats row
        splitLine = row.split(",");
        //Stores each term before the ',' into an array
        int idNumber = Integer.parseInt(splitLine[0]);
        return idNumber;
    }
}
