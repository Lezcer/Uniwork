import java.util.*;

public class QueueTestHarness
{
    public static void main(String[] args)
    {
        final int MAX_VAL = 51;//Max value for random integer
        final int REMOVE_NO = 7;//Number of Elements to be removed from queue
        int length = 20;
        try
        {
            //OBJECT CREATION - CONSTRUCTORS
            DSAQueue test = new DSAQueue();

            Random rand = new Random();
            int num;
    
            for(int i=0; i<length; i++)
            {
                
                num = rand.nextInt(MAX_VAL);
                test.enqueue((Integer) num);
            }
            test.display();
            System.out.println("\n*MUTATOR: enqueue was used to insert values.");

            //ACCESSORS
            System.out.println("\n*ACCESSOR: peek()");
            System.out.println("Please check above if the Queue's first value is: " + test.peek());

            System.out.println("\n*ACCESSOR: isEmpty");
            System.out.println(test.isEmpty() + " = False. The Queue is not empty!");

            System.out.println("\n*MUTATOR: dequeue()");

            System.out.println("Take " + REMOVE_NO + " from the Queue (FIFO):");
            for(int x=0; x<REMOVE_NO; x++)
            {
                Object bin = test.dequeue();
            }
            test.display();
            System.out.println("Please check above if the Queue's first value is: " + test.peek());
        }

        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
