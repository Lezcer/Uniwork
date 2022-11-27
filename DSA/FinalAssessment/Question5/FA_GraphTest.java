/**
 * DSA Final Assessment Question 5 - GraphTest.java
 *
 * Name : Salah Mahamod  
 * ID   : 20152428
 *
 **/
import java.util.*;
import java.io.*;
import java.awt.*;
public class FA_GraphTest
{
	public static void main(String args[])
	{
			System.out.println("**** Question 5: Testing Graphs ****");
			System.out.println("**** Testing FileIO ****");

            FA_Graph graph1 = readFile("FA_GraphData.txt");

			System.out.println("**** FileIO Test Complete (Question 5.a) ****");

			
			System.out.println("**** Normal Graph Testing ****");
			FA_Graph g = new FA_Graph();
			
			g.addVertex("one", 1);
			g.addVertex("two", 2);
			g.addVertex("three", 3);
			g.addVertex("four", 4);
			
			g.addEdge("one", "two");
			g.addEdge("one", "three");
			g.addEdge("one", "four");
			g.addEdge("four", "two");
			g.addEdge("four", "three");
			
			System.out.println("**** Displaying Graph as Adj Matrix (Question 5.b) ****");
			g.displayAsMatrix();
			System.out.println();
			graph1.displayAsMatrix();

			System.out.println("**** Tests Complete ****");

	}
    private static FA_Graph readFile(String filename)
    {
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader buffRdr;
        String line;
        FA_Graph graph = new FA_Graph();
        //Necessary Declarations

        try
        {
            fileStream = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStream);
            buffRdr = new BufferedReader(rdr);
            line = buffRdr.readLine();
            while(line != null)
            {
                processLine(graph, line);
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
        return graph;
    }
    private static void processLine(FA_Graph graph, String line)
    { 
        String[] info;
        info = line.split(" ");
        //Oth element is the 'from' node, 1st element is the 'to' node and 2nd
        //element is the 'weight'
        graph.addVertex(info[0], null);
        graph.addVertex(info[1], null);//Note the provided ADT doesn't have weight
        graph.addEdge(info[0], info[1]);
    }
	
}
