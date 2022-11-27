/**
 * DSA Final Assessment Question 5 - FA_Graph.java                             4
 *
 * Name : Salah Mahamod
 * ID   : 20152428
 *
 **/
import java.util.*;

public class FA_Graph 
{
    private FA_LinkedList vertices;
    private int vertexCount;
    private int edgeCount;

    public FA_Graph() 
    {
        vertices = new FA_LinkedList();
        vertexCount = 0;
        edgeCount = 0;
    }

    public void addVertex(String label, Object value) 
    {
        FA_GraphVertex vertex = new FA_GraphVertex(label, value); 
        if (!(hasVertex(label))) 
        {
            vertices.insertLast(vertex);
            vertexCount++;
        }
    }

    public void addEdge(String label1, String label2)
    {
        FA_GraphVertex v1, v2; 
        
        v1 = getVertex(label1); 
        v2 = getVertex(label2);   

        v1.addEdge(v2); 

        edgeCount++;
    }

    public boolean hasVertex(String label) 
    {
        boolean has = false;
        for (Object v : vertices) 
        {
		   FA_GraphVertex vg = (FA_GraphVertex) v;
           if (vg.getLabel().equals(label))
			   has = true;
        }
        return has;
    }

    public FA_GraphVertex getVertex(String label) 
    {
        FA_GraphVertex theVertex = null;
        for (Object v : vertices) 
        {
		   FA_GraphVertex vg = (FA_GraphVertex) v;
           if (vg.getLabel().equals(label))
			   theVertex = vg;
        }
		return theVertex;    
	}

    public void displayAsMatrix() 
    {
		System.out.println("Adjacency Matrix display");
        String[] labels = new String[vertexCount];

        Iterator iter = vertices.iterator();
        System.out.print("    ");
        for(int i=0; i<vertexCount; i++)
        {
            labels[i] = ((FA_GraphVertex)iter.next()).getLabel();
            System.out.print(labels[i] + " ");
        }//All Vertex labels stored and printed for first line
        System.out.println("\n==============");    

        for(int j=0; j<vertexCount; j++)
        {
            System.out.print(labels[j] + " | ");
            FA_GraphVertex temp = this.getVertex(labels[j]);
            for(int z=0; z<vertexCount; z++)
            {
                if(temp.links.contains(labels[z]))
                {
                    System.out.print("1 ");
                }
                else
                {
                    System.out.print("0 ");
                } 
            }
            System.out.println();    
        }
            
        Iterator iter2 = vertices.iterator();
        /**while (iter.hasNext())
        {
            FA_GraphVertex current = (FA_GraphVertex)iter.next();
            current.links.contains(
        }*/
		// put your code here
    }

}  
