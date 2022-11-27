import java.util.*;
import java.lang.*;
public class DSAGraph
{
    //Class Fields
    DSALinkedList nodes;
    private DSAGraph()
    {
        DSALinkedList nodes = new DSALinkedList();
    }
    private void addNode(String label)
    {   
        DSAGraphNode node = new DSAGraphNode(label);
        nodes.insertLast(node);
    }
    private void addEdge(String label1, String label2)
    {
        DSAGraphNode node1 = new DSAGraphNode(label1);
        DSAGraphNode node2 = new DSAGraphNode(label2);
        nodes.insertLast(node1);
        nodes.insertLast(node2);
        node1.addEdge(node2);
    }
    private boolean hasNode(String inLabel)
    {
        Iterator iter = nodes.iterator();
        boolean exists = false;
        while(!exists || iter.hasNext())
        {
            if(((DSAGraphNode)iter.next()).label.equals(inLabel));
                exists = true;
        }
        return exists;
    }
    private int getNodeCount()
    {
        return 0;
    }
    private int getEdgeCount()
    {
        return 0;
    }
    private DSAGraphNode getNode(String inLabel)
    {
        DSAGraphNode node;
        Iterator iter = nodes.iterator();
        boolean ifThisIs = iter.hasNext();  

        if(!hasNode(inLabel))
            throw new IllegalArgumentException("node is not found");
      
        do{
            node = (DSAGraphNode)iter.next();
            ifThisIs = iter.hasNext() || node.label.equals(inLabel);  
            if(node.label.equals(inLabel));
                node = null;//Will get replaces eventually
        }
        while(ifThisIs);
        
        return node;
    }
    private DSALinkedList getAdjacent(String label)
    {
        return getNode(label).adjacencyList;
    }
    private boolean isAdjacent(String label1, String label2)
    {
        DSAGraphNode node;
        boolean exists = false;

        if(getNode(label1) != null && getNode(label2) != null)
        { 
            node = getNode(label1);
            Iterator iter = node.adjacencyList.iterator();
            
            while(!exists || iter.hasNext())
            {
                if(((DSAGraphNode)iter.next()).label.equals(label2));
                    exists = true;
            }
        }
        return exists;
    }
    private void display()
    {
        
    }
}
