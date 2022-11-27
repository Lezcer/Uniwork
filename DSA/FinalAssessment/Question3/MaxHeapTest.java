/**
 * DSA Final Assessment Question 3 - MaxHeapTest.java
 *
 * Name : Salah Mahamod
 * ID   : 20152428
 *
 **/
import java.util.*;
import java.io.*; 
public class MaxHeapTest
{
	public static void main(String args[])
	{
		System.out.println("**** Question 3: Testing MaxHeap ****");
		
		FA_Heap  testHeap = new FA_Heap();
		
		for (int i=0; i<10; i++)
		{
            try
            {
			    testHeap.add(i, i+100);
            }
            catch(PracExamException e)
            {
                System.out.println("Error: couldn't add entry!");
            }
			System.out.println("Added Entry: Priority = "+i+" | Value = "+(i+100));
		}
		
		
		System.out.println("\n\nPriority of each element inserted will be shown below, in order");
		for (int i=0; i<10; i++)
		{
            try
            {
			    System.out.print((testHeap.remove()).getPriority() +" ");
            }
            catch(PracExamException e)
            {
                System.out.println("Error: couldn't remove entry!");
            }
		}
		System.out.println("\n\n**** File IO test - FA_HeapData.txt ****");
        FA_Heap heapFile = readFile("FA_HeapData.txt");
		System.out.println("\nThe Entries retrieved from FA_HeapData.txt will be removed\nThe level of priority of each entry will be displayed as it gets removed.");
		for (int i=0; i<10; i++)
		{
            try
            {
			    System.out.println((heapFile.remove()).getValue() +" ");
            }
            catch(PracExamException e)
            {
                System.out.println("Error: couldn't remove entry!");
            }
		}//Order will be descending


		System.out.println("**** Tests Complete ****");
	}
    private static FA_Heap readFile(String filename)
    {
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader buffRdr;
        String line;
        FA_Heap heap = new FA_Heap();
        //Necessary Declarations

        try
        {
            fileStream = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStream);
            buffRdr = new BufferedReader(rdr);
            line = buffRdr.readLine();
            while(line != null)
            {
                processLine(heap, line);
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
        return heap;
    }
    private static void processLine(FA_Heap heap, String line)
    { 
        String[] info;
        info = line.split(" ");
        //Oth element is priority, 1st element is the value
        int priority = Integer.parseInt(info[0]); 
        System.out.println("Added Entry: Priority = " + priority + " | Value = " +info[1]);
        try
        {
            heap.add(priority, info[1]);
        }
        catch(PracExamException e)
        {
            System.out.println("Error: couldn't add entry!");
        }
    }
   
}
