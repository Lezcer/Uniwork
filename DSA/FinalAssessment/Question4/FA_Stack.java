/**
 * DSA Final Assessment Question 4 - FA_Stack.java                             4
 *
 * Name : Salah Mahamod 
 * ID   : 20152428
 *
 **/

class FA_Stack
{
    private static final int DEFAULT_CAPACITY = 100; 

    private Object[] stack;
    private int count;


    public FA_Stack()
    {
        stack = new Object[DEFAULT_CAPACITY];
        count = 0;

    }

    public FA_Stack( int inCapacity )
    {
        stack = new Object[inCapacity];
        count = 0;

    }

    public int getCount()
    {
        return count;
    }

    public int getSize()
    {
        return stack.length;
    }

    public boolean isEmpty()
    {
        return (count == 0);
    }

    public boolean isFull()
    {
        return (count == stack.length);
    }

    public Object top() throws PracExamException
    {
        Object topValue;

        if ( isEmpty() )
        {
            throw new PracExamException("Stack is empty");
        }
        else
        {
            topValue = stack[count - 1]; 
        }
        return topValue;
    }

    public void push( Object value ) throws PracExamException
    {
        if ( isFull() )
        {
            throw new PracExamException("Stack is full");
        }
        else
        {
            stack[count] = value;
            count++;
        }
    }

    public Object pop() throws PracExamException
    {
        Object topValue = top();
        
        stack[count - 1] = null;
        count--;
        return topValue;
    }

}
