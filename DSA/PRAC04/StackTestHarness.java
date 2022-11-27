import java.util.*;

public class StackTestHarness
{
    public static void main(String[] args)
    {
        final int REMOVE_NO = 5;
        final int MAX_VAL = 100;
        int length = 20;
        try
        {
            //OBJECT CREATION - CONSTRUCTORS
            DSAStack test = new DSAStack();
            

            //print out lenghths i.e. MAX CAPACITY OF DSAStack object
            System.out.println();

            //FILL THE OBJECTS - MUTATOR push()
            Random rand = new Random();
            int num;
            for(int i=0; i<length; i++)
            {   
                num = rand.nextInt(MAX_VAL);
                test.push((Integer) num);
            }
            test.display();
            System.out.println();

            System.out.println("*MUTATOR: push was used to insert values.");

            //ACCESSORS
            System.out.println("\n*ACCESSOR: top");
            System.out.println("Please check above if Stack 1's last value is: " + test.top());

            System.out.println("\n*ACCESSOR: isEmpty");
            System.out.println(test.isEmpty() + " = False. Stack 1 not empty!");


            System.out.println("\n*MUTATOR: pop");  
            System.out.println("Take " + REMOVE_NO + " from Stack (LIFO):");
            for(int x=0; x<REMOVE_NO; x++)
            {
                Object bin = test.pop();
            }
            test.display();
            System.out.println("Please check above if the stack's last value is: " + test.top());
        }

        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
