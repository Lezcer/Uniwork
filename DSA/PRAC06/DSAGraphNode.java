import java.util.*;
public class DSAGraphNode
{
    public String label;
    public DSALinkedList adjacencyList;
    public boolean visited = false;

    public DSAGraphNode(String inLabel)
    {
        label = inLabel;
    }
    public void addEdge(DSAGraphNode vertex)
    {
        adjacencyList.insertFirst(vertex);
    }
    public void setVisted()
    {
        visited = true;
    }
    public void clearVisted()
    {
        visited = false;
    }
    public String stringify()
    {
        return label + " is adjacent to: " + adjacencyList.exportList();
    }
}
