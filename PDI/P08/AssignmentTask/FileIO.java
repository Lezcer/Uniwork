import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class FileIO
{
    public static int[][] readFile(String inFileName)
    {
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader buffRdr;
        int arrayHeight, numOfLines;
        String line;
        boolean noFile;
        int[][] image = null;
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
            numOfLines = getLineNum(inFileName );
            image = new int[numOfLines][];
            //Creating an array to store the integers
            while(line != null)
            {
                image[arrayHeight] = processLine(line);
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
    
        if (!validationDimen(image))
        {
            System.out.println("Invalid File");
            image = null;
        }
        return image;
    }
    
    public static void writeFile(String fileName, int[][] writeArray)
    {
        FileOutputStream fileStream = null;
        PrintWriter pw;
        //Necessary Declarations

        try
        {
            fileStream = new FileOutputStream(fileName );
            pw = new PrintWriter(fileStream);
            
            for(int i = 0; i < writeArray.length; i++)
            {
                for(int j = 0; j < writeArray[0].length - 1; j++)
                {
                    pw.print(writeArray[i][j] + ",");
                }
                pw.print(writeArray[i][writeArray[0].length - 1]);
                //Last element cannot have a ","
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
    
    public static int[][] readPNG(String fileName)
    {
        BufferedImage img;
        File inputFile;
        int[][] image = null;

        try
        {
            inputFile = new File(fileName ); //Opens file
            img = ImageIO.read(inputFile); //Turn file into an image
            image = new int[img.getHeight()][img.getWidth()]; //Construct array to hold image
            for(int y = 0; y < img.getHeight(); y++)
            {
                for(int x = 0; x < img.getWidth(); x++)
                {
                    Color pixel = new Color(img.getRGB(x,y), true); //Turn the pixel into a color obj
                    image[y][x] = (int)((pixel.getRed() * 0.399) +
                                        (pixel.getBlue() * 0.587) +
                                        (pixel.getGreen() * 0.114));
                    //Converts each pixel to grey scale equivalent using weightings on each color.
                }
            }
        }
        catch(IOException e)
        {
            UserInterface.displayError("Error with .png reading: " + e.getMessage());
        }

        if (!validationDimen(image))
        {
            System.out.println("Invalid File");
            image = null;
            //Resets the image as its not valid.
        }
        return image;
    }
    
    public static void writePNG(String fileName, int[][] writeArray)
    {
        BufferedImage theImage;
        File outputFile;

        try
        {
            outputFile = new File(fileName ); //Open the file
            theImage = new BufferedImage(writeArray[0].length,
                                         writeArray.length,
                                         BufferedImage.TYPE_INT_RGB);
            //Construct a buffered Image, with dimensions and of type RGB

            for(int y = 0; y < writeArray.length; y++)
            {
                for(int x = 0; x < writeArray[0].length; x++)
                {
                    int value = (writeArray[y][x] % 256); //Ensures that value is between 0 and 255
                    Color newColor = new Color(value, value, value); //Turns the greyscale pixel to its colour rep
                    theImage.setRGB(x, y, newColor.getRGB()); //sets the value of pixel with in .png
                }
            }
            ImageIO.write(theImage, "png", outputFile); //Write image to a .png
        }
        catch(IOException e)
        {
            UserInterface.displayError("Error with .png writing: " + e.getMessage());
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
    private static int[] processLine(String row)
    {
        String[] splitLine;
        //Creates array for each column of thats row
        splitLine = row.split(",");
        //Stores each term before the ',' into an array
        int[] rowImage = new int[splitLine.length];

        for(int i = 0; i < splitLine.length; i++)
        {
            rowImage[i] = Integer.parseInt(splitLine[i]);
            //typecasts the integers stored as a string to integers
        }
        return rowImage;
    }

    private static boolean validationDimen(int[][] image)
    {
        //We need a reference point for the length of the columns and rows individually
        //thus column 1 should have the same length as all the other rows.
        boolean error = true;
        int checker = 0;

        for(int vv = 0; vv < image.length; vv++)
        {
            if (image[0].length == image[vv].length)
            {
                error = false;
                if(!error)
                {
                    checker = 1;
                }//This is because the last time this loops error might be true even though there's a case in which its false
                 //Thus this gets another value to ensure it never is false, now we need an selection method checking if its 0 or 1
            }
        }
        if (checker == 1)
        {
            error = true;
            //Over writes this value, because theres an error
        } 
        return error;
    }
}
