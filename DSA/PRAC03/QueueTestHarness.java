import java.util.*;

public class QueueTestHarness
{
    public static void main(String[] args)
    {
        final int MAX_VAL = 51;//Max value for random integer
        final int REMOVE_NO = 7;//Number of Elements to be removed from queue
        try
        {
            //OBJECT CREATION - CONSTRUCTORS
            DSAShufflingQueue testS = new DSAShufflingQueue();

            DSACircularQueue testC = new DSACircularQueue();

            //print out lenghths i.e. MAX CAPACITY OF DSAQueue object
            System.out.println("*LENGTH CHECK: ");
            System.out.println("CIRCULAR: Default Constructor Queue length: " + testC.getLength());
            System.out.println("SHUFFLING: Default Constructor Queue length: " + testS.getLength());
            System.out.println();

            //FILL THE OBJECTS - MUTATOR push()
            //int length = tests[0].getLength();//For Default
            Random rand = new Random();
            int num;
    
            for(int i=0; i<(testS.getLength()); i++)
            {
                
                num = rand.nextInt(MAX_VAL);
                testS.enqueueShuf((Integer) num);
            }
            testS.displayArray();
            System.out.println("\n*MUTATOR: shuffling enqueue was used to insert values.");

            for(int j=0; j<(testC.getLength()); j++)
            {
                
                num = rand.nextInt(MAX_VAL);
                testC.enqueueCirc((Integer) num);
            }
            testC.displayArray();
            System.out.println("\n*MUTATOR: circular enqueue was used to insert values.");

            //ACCESSORS
            System.out.println("\n*ACCESSOR: getCount INITIAL");
            System.out.println("The number of elements stored in Shuffling Queue are: " + testS.getCount());
            System.out.println("The number of elements stored in Circular Queue are: " + testC.getCount());

            System.out.println("\n*ACCESSOR: peekShuf()");
            System.out.println("Please check above if Shuffling Queue's first value is: " + testS.peekShuf());
            System.out.println("\n*ACCESSOR: peekCirc()");
            System.out.println("Please check above if Circular Queue's first value is: " + testC.peekCirc());

            System.out.println("\n*ACCESSOR: isEmpty");
            System.out.println(testS.isEmpty() + " = False. Shuffling Queue is not empty!");
            System.out.println(testC.isEmpty() + " = False. Circular Queue is not empty!");

            System.out.println("\n*ACCESSOR: isFullShuf()");
            System.out.println(testS.isFullShuf() + " = True. Shuffling Queue is full!");
            System.out.println("\n*ACCESSOR: isFullCirc()");
            System.out.println(testC.isFullCirc() + " = True. Circular Queue is full!");

            System.out.println("\n*MUTATOR: dequeueShuf()");

            System.out.println("Take " + REMOVE_NO + " from Shuffling Queue (FIFO):");
            for(int x=0; x<REMOVE_NO; x++)
            {
                Object bin = testS.dequeueShuf();
            }
            testS.displayArray();
            System.out.println("Please check above if Shuffling Queue's first value is: " + testS.peekShuf());
 
            System.out.println("\nTake " + REMOVE_NO + " from Circular Queue (FIFO):");
            for(int x=0; x<REMOVE_NO; x++)
            {
                Object bin = testC.dequeueCirc();
            }
            testC.displayArray();
            System.out.println("Please check above if Queue's first value is: " + testC.peekCirc());

            System.out.println("\n*ACCESSOR: getCount CHANGED!");
            System.out.println("The number of elements stored in Shuffling Queue are: " + testS.getCount());
            System.out.println("The number of elements stored in Circular Queue are: " + testC.getCount());
        }

        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
