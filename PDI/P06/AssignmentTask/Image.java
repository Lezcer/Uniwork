//Authir: Salah Mahamod
//Design Date: 02/05/2020
//Purpose practice with basic modular classes
import java.util.*;
public class Image
{
    private int[][] orginalImage;
    
    public Image()
    {
        int[][] originalImage = { {0,0,0,0}, {0,0,0,0}, {0,0,0,0},{0,0,0,0} };
        //Default constructor that is a zero matrix.
    }//End of Default Constructor

    public Image(int [][] inOriginalImage)
    {
        int[][] originalImage = new int[][] inOriginalImage;
    }//End of Alternate Constructor. 
    
    public Image (Image inImage)
    {
        int[][] originalImage = inImage.originalImage;
    }//End of Copy Constructor - Imports an Image object and exports a copy of that object.

    public Image clone()
    {
        return new Image(this);
    }//End of clone accessor
    
    public String toString()
    {
        String space = " ";
        String someString, str;
        int[][] resultArray = convolution(kernel);
        //Using the convolution method to fill in the values.

        for(int ff=0; ff<resultArray.length; ff++)
        {
            someString = "[";
            for(int gg=0; gg<resultArray[0].length; gg++)
            {
                someString += resultArray[ff][gg] + " ";
            }//Iterates through every element of the matrix
            someString += "]";
        } 
        str = someString;
        return str;
    }//End of toString accessor
    
    //Accessor for getting the dimensions of the array
    public int getOILength(int[][] originalImage)
    {
        int length = originalImage.length;
        return length;
    }

    //Accessor for getting the dimensions of the array
    public int getOIWidth(int[][] originalImage)
    {
        int width = originalImage[0].length;
        return width;
    }

    //Accessor for getting the dimensions of the array
    public int getKLength(int[][] kernel)
    {
        int length = kernel.length;
        return length;
    }

    //Accessor for getting the dimensions of the array
    public int getKWidth(int[][] kernel)
    {
        int width = kernel[0].length;
        return width;
    }

    private int[][] convolution(int[][] kernel)
    {
        int length = getOILength - getKernelLength + 1;
        int width = getOILength - getKernelLength + 1;

        int[][] resultArray = new int[length][width];

        for(int n = 0; n < length; n++)
        {
            for(int m = 0; m < width; m++)
            {
              resultArray[n][m] = convolOperation(n, m);
            }
        }
        return resultArray;
    }
    
    private int convolOperation(int n, int m)
    {
        int kernelLength = getKernelLength();
        int kernelWidth = getKernelWidth();
        //Using the getters / accessors to get these values

        int sum = 0;
        for(int i = 0; i < kernelLength; i++)
        {
            for(int j = 0; j < kernelWidth; j++)
            {
                sum += originalImage[n+i][m+j] * kernel[i][j];
            }//Performing Convolutions with the arrays and storing it in var sum
        }
        return sum;
    }
}
