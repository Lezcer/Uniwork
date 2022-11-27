import java.util.*;

public class TestHarness
{
    public static void main(String[] args)
   {
        //FILE IO
        final int ROWS = 7000;  
        final int COLUMNS = 2;
        String[][] data = new String[ROWS][COLUMNS];
        data = FileIO.readFile("RandomNames7000.csv");
        
        //Object Creation - TESTED insert()
        System.out.println("Inserting Student names as a key and ID as the value.");
        DSABinarySearchTree tree = new DSABinarySearchTree();
        for(int i=0; i<ROWS; i++)
        { 
                tree.insert(data[i][0], data[i][1]);
        }
        
        //display2D(data, ROWS, COLUMNS); //Displays all 7000 Random Names.

        //height test - TESTED height()
        System.out.println("\nHeight is "+ tree.height()+".");
        
        //Find test - TESTED find()
        System.out.println("Sofia B "+ tree.find("Sofia Bonfiglio") + " = 14495655");
        System.out.println("Kenya S "+ tree.find("Kenya Seidman") + " = 14578980");
        System.out.println("Jessie D " + tree.find("Jessie Dosch") + " = 14406596");

        //Minimum Maximum test - TESTED min() max()
        System.out.println("\nMaximum key is: " + tree.max());
        System.out.println("\nMinimum key is: " + tree.min());
        
        //Balanced? - TESTED isBalanced()
        
        System.out.println("\nThe Tree is Balanced, this statement is: " + tree.isBalanced());

        //Delete test - TESTED delete()
        System.out.println("\nLet's try to delete the last three keys");
        tree.delete("Sofia Bonfiglio");
        tree.delete("Kenya Seidman");
        tree.delete("Jessie Dosch");
        System.out.println("There will be three errors below, stating that keys are not found.");
        try
        {
            System.out.println("Sofia B "+ tree.find("Sofia Bonfiglio") + " = 14495655");
        }
        catch(NoSuchElementException e)
        {
            System.out.println("ERROR: Key not found.");
        }
        try
        {
            System.out.println("Kenya S "+ tree.find("Kenya Seidman") + " = 14578980");
        }
        catch(NoSuchElementException e)
        {
            System.out.println("ERROR: Key not found.");
        }
        try
        {
            System.out.println("Jessie D " + tree.find("Jessie Dosch") + " = 14406596");
        }
        catch(NoSuchElementException e)
        {
            System.out.println("ERROR: Key not found.");
        }
        //
    }
    public static void display2D(String[][] array, int row, int col)
    {
        System.out.print("\n");
        for(int i=0; i<row; i++)
        {
            System.out.print("[");
            for(int j=0; j<col; j++)
            {
                if(j == (col -1))
                    System.out.print(array[i][j]);
                else
                    System.out.print(array[i][j]+", ");
            }
            System.out.print("]\n");
        }
    }
}
