import java.util.*;
public class TaskFive
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner (System.in);
        int rows, cols, numRows, numCols;
        
        do
        {
            System.out.println("Please enter the number of rows");
            rows = sc.nextInt();
            System.out.println("Please enter the number of columns");
            cols = sc.nextInt();
        }
        while (rows > 9 || cols > 9);
        
        numRows = rows + 0;
        numCols = cols + 0;

        int[][] timesTable = new int[numRows][numCols];
        for(int y = 0; y < timesTable.length; y++)
        {
            System.out.print(y + 1 + " | ");
            for(int x = 0; x < timesTable[y].length; x++)
            {
                timesTable[y][x] = (y + 1) * (x + 1);

                if (timesTable[y][x] < 10) 
                {
                    System.out.print(timesTable[y][x] + "  ");
                }
                else
                {
                    System.out.print(timesTable[y][x] + " ");
                }
            }

            //System.out.print("}");
            System.out.println();
        }   
    }
}
