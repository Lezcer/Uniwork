import java.util.*;
public class DSAQueue
{
    DSALinkedList queue;
    //CONSTRUCTORS
    public DSAQueue()
    {
        queue = new DSALinkedList();
    }
    //ACCESSORS
    public Object peek()
    {
        return queue.peekFirst();
    }
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
    public void display()
    {
        queue.displayList();
    }
    //MUTATORS    
    public void enqueue(Object value)
    {
        queue.insertLast(value);
    }
    public Object dequeue()
    {
        return queue.removeFirst();
    }
}
