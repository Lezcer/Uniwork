import java.util.*;

public class SortIDs
{
public static void main(String[] args)
    {
        int[] listOfID = new int[7000];
        listOfID = FileIO.readFile("RandomNames7000.csv");

        Sorts.insertionSort(listOfID);

        FileIO.writeFile("RandomNames7000_InsSorted.csv", listOfID);
        System.out.println("File: RandomNames7000_InsSorted.csv saved.");
    }
}
