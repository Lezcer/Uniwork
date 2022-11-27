import java.util.*;

public class StackTestHarness
{
    public static void main(String[] args)
    {
        final int SIZE = 30;//Size of DSAStack object
        final int MAX_VAL = 51;//Max value for random integer
        final int REMOVE_NO = 7;//Number of Elements to be removed from stack
        try
        {
            //OBJECT CREATION - CONSTRUCTORS
            DSAStack test[] = new DSAStack[2];
            test[0] = new DSAStack();
            test[1] = new DSAStack(SIZE);
            

            //print out lenghths i.e. MAX CAPACITY OF DSAStack object
            System.out.println("*LENGTH CHECK: ");
            System.out.println("Alternate Constructor Stack 1 length: " + test[0].getLength());
            System.out.println("Default Constructor Stack 2 length: " + test[1].getLength());
            System.out.println();

            //FILL THE OBJECTS - MUTATOR push()
            int length = test[0].getLength();//For Default
            Random rand = new Random();
            int num;
            for(int i=0; i<length; i++)
            {
                
                num = rand.nextInt(MAX_VAL);
                test[0].push((Integer) num);
            }
            test[0].displayArray();
            System.out.println();

            length = test[1].getLength();//For Alternate

            for(int i=0; i<length; i++)
            {
                num = rand.nextInt(MAX_VAL);
                test[1].push((Integer) num);
            }
            test[1].displayArray();
            System.out.println("*MUTATOR: push was used to insert values.");

            //ACCESSORS
            System.out.println("\n*ACCESSOR: getCount INITIAL");
            System.out.println("The number of elements stored in Stack 1 are: " + test[0].getCount());
            System.out.println("The number of elements stored in Stack 2 are: " + test[1].getCount());

            System.out.println("\n*ACCESSOR: top");
            System.out.println("Please check above if Stack 1's last value is: " + test[0].top());
            System.out.println("Please check above if Stack 2's last value is: " + test[1].top());//top tested

            System.out.println("\n*ACCESSOR: isEmpty");
            System.out.println(test[0].isEmpty() + " = False. Stack 1 not empty!");
            System.out.println(test[1].isEmpty() + " = False. Stack 2 not empty!");//isEmpty tested

            System.out.println("\n*ACCESSOR: isFull");
            System.out.println(test[0].isFull() + " = True. Stack 1 is full!");
            System.out.println(test[1].isFull() + " = True. Stack 2 is full!");//isFull tested

            System.out.println("\n*MUTATOR: pop");  
            for(int y=0; y<2; y++)
            {
                System.out.println("Take " + REMOVE_NO + " from Stack " + (y+1) + " (LIFO):");
                for(int x=0; x<REMOVE_NO; x++)
                {
                    Object bin = test[y].pop();
                }
                test[y].displayArray();
                System.out.println("Please check above if Stack " + (y+1) + "'s last value is: " + test[y].top());
                System.out.println();
            }     
            System.out.println("*ACCESSOR: getCount CHANGED!");
            System.out.println("The number of elements stored in Stack 1 are: " + test[0].getCount());
            System.out.println("The number of elements stored in Stack 2 are: " + test[1].getCount());
        }

        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
