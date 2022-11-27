import java.util.*;

public class DSAShufflingQueue extends DSAQueue
{
    public DSAShufflingQueue()
    {
        super();
    }
    public DSAShufflingQueue(int inMax)
    {
        super();
    }
    public void enqueueShuf(Object value)
    {
        if(isFullShuf())
        {
            //Throws exception - Erroe
        }
        else
        {
            queue[count] = value;
            count = count + 1;
        }
    }
    public Object dequeueShuf()
    {
        Object frontVal = 0;
        if(isEmpty())
        {
            //Throw exception - Error
        }
        else
        {
            for(int i=0; i<(count - 1); i++)
            {
                queue[i] = queue[i+1];
            }
            frontVal = queue[0];
            count--;
        }
        return frontVal;
    }
    public Object peekShuf()
    {
        Object frontVal = 0;
        if(isEmpty())
        {
            //Trhow exception - Error
        }
        else
        {
            frontVal = queue[0];
        }
        return frontVal;
    }
    public boolean isFullShuf()
    {
        return (count == queue.length);
    }
}
