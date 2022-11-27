import java.util.*;

public class DSACircularQueue extends DSAQueue
{
    int front = -1;
    public DSACircularQueue()
    {
        super();
    }
    public DSACircularQueue(int inMax)
    {
        super();
    }

    public void enqueueCirc(Object value)
    {
        if(isFullCirc())
        {
        }
        else
        {
            count = (count+1) % queue.length;
            queue[count] = value;
            count++;
            if(front == -1)
            {
                front = count;
            }
        }
    }
    public Object dequeueCirc()
    {
        Object firstVal = 0;
        if(isEmpty())
        {
        }
        else
        {
            firstVal = queue[front];
            queue[front] = null;
            count--;
        }
        return firstVal;    
    }
    public Object peekCirc()
    {
        Object frontVal = 0;
        if(isEmpty())
        {
        }
        else
        {
            frontVal = queue[front];
        }
        return frontVal;
    }
    public boolean isFullCirc()
    {
        return ((count+1) % queue.length == count);
    }
}
