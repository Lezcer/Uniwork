import java.util.*;
public class DSAStack
{
    Object[] stack;
    int count;
    public static final int DEFAULT_CAPACITY = 100;
    //CONSTRUCTORS
    public DSAStack()
    {
        stack = new Object[DEFAULT_CAPACITY];
        int count = 0;
    }
    public DSAStack(int maxCapacity)
    {
        stack = new Object[maxCapacity];
        int count = 0;
    }
    //ACCESSORS
    public int getLength()
    {
        int length = stack.length;
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
    public boolean isFull()
    {
        boolean full = (count == stack.length);
        return full;
    }
    public Object top()
    {
        Object topVal = 0;
        if(isEmpty())
        {
            //Trhow exception - Error
        }
        else
        {
            topVal = stack[count-1];
        }
        return topVal;
    }
    public void displayArray()
    {
        System.out.print("[");
        for(int i=0; i<(getCount()); i++)
        {
            if(i== getCount() - 1)
            {
                System.out.print(stack[i]);
            }
            else
            {
                System.out.print(stack[i] + ",");
            }
        }
        System.out.println("]");
    }
    //MUTATORS
    public void push(Object value)
    {
        if(isFull())
        {
            //Throws exception - Erroe
        }
        else
        {
            stack[count] = value;
            count = count + 1;
        }
    }
    public Object pop()
    {
        Object topVal = top();
        count = count - 1;
        return topVal;
    }
}
