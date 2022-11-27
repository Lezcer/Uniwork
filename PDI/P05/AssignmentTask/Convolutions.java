//Author: Salah Mahamed Mahamod
//Date submitted 26th of April
//Purpose: Performing a convolution operation on a 2D array using a kernel (2D array) to produce another Matrix (i.e. 2D array).

import java.util.*;

public class Convolutions
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        char choiceCon = ' ';
        char choiceKer = ' ';
        boolean validChoice;
        int[][] convoluteMatrix = null;
        int[][] kernelMatrix = null;
        int[][] resultArray;
        char infoC = ' ';
        String infoK = " ";

        System.out.println("Welcome to Programming Design and Implementation Worksheet 5.");
        System.out.println("Please select a Convolute Matrix by typing A, B, C or D.");
        choiceCon = sc.nextLine().charAt(0);

        System.out.println("Now that you've entered a Convolute Matrix, proceed to type either;");
        System.out.println("(H)orizontal OR (V)ertical for a Kernel Matrix.");
        choiceKer = sc.nextLine().charAt(0);
        validChoice = true;

        System.out.println();
        System.out.println();
    
        switch(choiceCon)
        {
            case 'A': case 'a':
                convoluteMatrix = new int[Convolute.MATRIX_A.length][Convolute.MATRIX_A[0].length];
                convoluteMatrix = Convolute.MATRIX_A;
                infoC = 'A';
            break;
                
            case 'B': case 'b':
                convoluteMatrix = new int[Convolute.MATRIX_B.length][Convolute.MATRIX_B[0].length];
                convoluteMatrix = Convolute.MATRIX_B;                
                infoC = 'B';
            break;

            case 'C': case 'c':
                convoluteMatrix = new int[Convolute.MATRIX_C.length][Convolute.MATRIX_C[0].length];
                convoluteMatrix = Convolute.MATRIX_C;                
                infoC = 'C';
            break;

            case 'D': case 'd':
                convoluteMatrix = new int[Convolute.MATRIX_D.length][Convolute.MATRIX_D[0].length];
                convoluteMatrix = Convolute.MATRIX_D;   
                infoC = 'D';
            break;

            default: 
                System.out.println("Enter a valid choice for Convolute Matrix");
                validChoice = false;
        }

        switch(choiceKer)
        {
            case 'H': case 'h':
                kernelMatrix = new int[Kernel.HORIZONTAL.length][Kernel.HORIZONTAL[0].length];
                kernelMatrix = Kernel.HORIZONTAL;
                infoK = "Horizontal";
            break;
            
            case 'V': case 'v':
                kernelMatrix = new int[Kernel.VERTICAL.length][Kernel.VERTICAL[0].length];
                kernelMatrix = Kernel.VERTICAL;
                infoK = "Vertical";
            break;

            default:
                System.out.println("Enter a valid choice for Kernel Matrix");
                validChoice = false;
        }

        if (validChoice) 
        {
            System.out.println("Covolute Matrix (" + infoC + ")");
            displayMatrix(convoluteMatrix);

            System.out.println("Kernel Matrix (" + infoK + ")");
            displayMatrix(kernelMatrix);

            resultArray = convolExp(convoluteMatrix, kernelMatrix); 

            System.out.println("Resultant Matrix (" + infoC + " x " +infoK + ")");
            displayMatrix(resultArray);
        }
        else
        {
            System.out.println("Please re-run this program and fix either Convoute or Kernel Matrix");
            //This should happen if the user inputs any wrong input
        }        
    }

    /*SUBMODULE: convolExp                                         * 
     *IMPORT: 2D ARRAYS convoluteMatrix, KernelMatrix              *
     *EXPORT: 2D ARRAY resultArray                                 */
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

    /*SUBMODULE: displayMatrix                                     *
    * IMPORT: 2D ARRAYS convoluteMatrix, kernelMatrix, resultArray *
    * EXPORT: none                                                 */
    public static void displayMatrix (int[][] matrix)
    {    
        for(int y = 0; y < matrix.length; y++)
        {
            for(int x = 0; x < matrix[y].length; x++)
            {
                System.out.print(matrix[y][x] + " ");
            }
            System.out.println();
            //Breaks up different rows
        }
        System.out.println();
        System.out.println();
        //Gives space between matrices
    }
}
