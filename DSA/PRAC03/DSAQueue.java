import java.util.*;
public class DSAQueue
{
    Object[] queue;
    int count;
    public static final int DEFAULT_CAPACITY = 30;
    //CONSTRUCTORS
    public DSAQueue()
    {
        queue = new Object[DEFAULT_CAPACITY];
        int count = 0;
    }
    public DSAQueue(int inMax)
    {
        queue = new Object[inMax];
        int count = 0;
    }
    //ACCESSORS
    public int getLength()
    {
        int length = queue.length;
        return length;
    }
    public int getCount()
    {
        return count;
    }
    public boolean isEmpty()
    {
        boolean empty = (count == 0);
        return empty;
    }
    public void displayArray()
    {
        System.out.print("[");
        for(int i=0; i<(getCount()); i++)
        {
            if(i== getCount() - 1)
            {
                System.out.print(queue[i]);
            }
            else
            {
                System.out.print(queue[i] + ",");
            }
        }
        System.out.println("]");
    }
    //MUTATORS
    
}
