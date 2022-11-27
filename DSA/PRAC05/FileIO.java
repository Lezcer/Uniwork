import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class FileIO
{
    public static String[][] readFile(String inFileName)
    {
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader buffRdr;
        int arrayHeight, numOfLines;
        final int COLUMNS = 2;
        String line;
        boolean noFile;
        String[][] details = null;
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
            details = new String[numOfLines][COLUMNS];
            //Creating an array to store the integers
            while(line != null)
            {
                details[arrayHeight] = processLine(line);
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
        return details;
    }
    
    public static void writeFile(String fileName, String writeString)
    {
        FileOutputStream fileStream = null;
        PrintWriter pw;
        //Necessary Declarations

        try
        {
            fileStream = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStream);
            pw.print(writeString);
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
    private static String[] processLine(String row)
    {
        String[] splitLine;
        //Creates array for each column of thats row
        splitLine = row.split(",");
        //Stores each term before the ',' into an array
        return splitLine;
    }


    //SERIALIZATION.
    //  READING
    public static DSABinarySearchTree load(String filename)
    {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        DSABinarySearchTree tree = null;
                
        try
        {
            fileStrm = new FileInputStream(filename);
            objStrm = new ObjectInputStream(fileStrm);
            tree = (DSABinarySearchTree) objStrm.readObject();
            objStrm.close();
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("The Class: DSABinarySearchTree not found.");
        }
        catch(Exception e)
        {
            System.out.println("Unable to load object from file.");
        }
        return tree;
    }
    //  WRITING
    public static void save(DSABinarySearchTree tree, String filename)
    {
        FileOutputStream fileStrm;
        ObjectOutputStream objStrm;
        try
        {
            fileStrm = new FileOutputStream(filename);
            objStrm = new ObjectOutputStream(fileStrm);
            objStrm.writeObject(tree);
            objStrm.close();
        }
        catch (Exception e)
        {
            System.out.println("Unable to serialize object.");   
        } 
    }    
}
