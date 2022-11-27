import java.util.*;
public class MatrixMultiplication
{
    public static void main(String[] args)
    {
        int[][] matrixC = new int[2][2];
        int addition = 0;
        for(int i = 0; i < 2; i++)
        {
            System.out.print(" [ ");
            // Goes through every element in the 2x2 array

            for(int j = 0; j < 2; j++)
            {
                for(int x = 0; x < 3; x++)
                {
                    addition = addition + (Matrix.ARRAY_A[i][x] * Matrix.ARRAY_B[x][j]);
                    // Adds the multiplied i and j
                }
                matrixC[i][j] = addition; 
                // The sum is equivalent to the elements value
                
                if (matrixC[i][j] < 10)
                {
                    System.out.print(matrixC[i][j] + "  ");
                }
                else 
                {
                    System.out.print(matrixC[i][j] + " ");
                }
                // If the value is a 1 digit number then assign two spaces between elements

                addition = 0;
                // Resets the addition value for the next time it loops
            }
        System.out.print("]");
        System.out.println();
        }
    }
}
