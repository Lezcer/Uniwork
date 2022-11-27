import java.util.*;
public class DSAStack
{
    DSALinkedList stack;
    //CONSTRUCTORS
    public DSAStack()
    {
        stack = new DSALinkedList();
    }
    //ACCESSORS
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }
    public Object top()
    {
        return stack.peekFirst();
    }
    public void display()
    {
        stack.displayList();
    }
    //MUTATORSi
    public void push(Object value)
    {
        stack.insertFirst(value);
    }
    public Object pop()
    {
        return stack.removeFirst();
    }
}
