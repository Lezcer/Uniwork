import java.util.*;
public class DetectEdges
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner (System.in);
        String kernelName; 
        String pngName = " "; 
        String csvName = " ";
        String convertedImageFile = " ";
        boolean isItPNG = false;
        char choice;

        int[][] kernel;
        int[][] image;
        int[][] convolResult;

        System.out.println("Please enter the filename of the kernel without the .csv");
        kernelName = sc.nextLine();

        do
        {
            System.out.println("Would you like to perform on (C)SV or (P)NG: ");
            choice = sc.nextLine().charAt(0);
        }
        while((choice != 'c' && choice != 'C') && (choice != 'p'&& choice != 'P'));

        switch(choice)
        {
            case 'c': case 'C':    
                System.out.println("Please enter the filename of the CSV without .csv");
                csvName = sc.nextLine();
                convertedImageFile = csvName + "_Converted.csv";
                isItPNG = false;
            break;
            
            case 'p': case'P':
                System.out.println("Please enter the filename of the PNG without .png");
                pngName = sc.nextLine();
                convertedImageFile = pngName + "_Converted.png";
                isItPNG = true;
            break;
        }

        kernel = FileIO.readFile(kernelName);

        if (isItPNG)
        {
            image = FileIO.readPNG(pngName);
            convolResult = convolExp(image, kernel);
            FileIO.writePNG(convertedImageFile, convolResult);
        }
        else
        {
            image = FileIO.readFile(csvName);
            convolResult = convolExp(image, kernel);
            FileIO.writeFile(convertedImageFile, convolResult);
        }
        System.out.println("File (" + convertedImageFile  + ") written. Goodbye!");
    }

    //The following method is a method i wrote for Prac 5, file: Convolutions.java
    //Copied the entire method convolExp
    //Submiited Sunday 26th of April, 2020 @5pm
    public static int[][] convolExp (int[][] convoluteMatrix, int[][] kernelMatrix) 
    {
        int maxValN = convoluteMatrix.length - kernelMatrix.length + 1;
        int maxValM = convoluteMatrix[0].length - kernelMatrix[0].length + 1;
        //Variables for dimensions of ResultArray

        int[][] resultArray = new int[maxValN][maxValM];


        for(int n = 0; n < maxValN; n++)
        {
            for(int m = 0; m < maxValM; m++)
            {
                //Iterates through all elements of resultArray
                int sum = 0;
                //Initialises variable sum, whilst resetting the value for every element in resultArray

                for(int i = 0; i < (kernelMatrix.length) ; i++)
                {
                    for(int j = 0; j < (kernelMatrix[i].length) ; j++)
                    {
                        sum = sum + (convoluteMatrix[n+i][m+j] * kernelMatrix[i][j]);
                    }
                }
                resultArray[n][m] = sum;               
            }
        }

        return resultArray;
    }
}
