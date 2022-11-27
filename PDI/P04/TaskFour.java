import java.util.*;
public class TaskFour
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int length, number, numSearch;
        boolean validNum = false;
        boolean isItFound = true;

        do
        {
            System.out.println("Please enter a number between 1 and 20 (inclusive).");
            length = sc.nextInt(); 
        }
        while (length < 1 || length > 20);

        
        int[] arrayNumbers = new int[length];
        for (int i = 0; i < length; i++)
        {
            Random rand = new Random(); 
            number = rand.nextInt(15);
            number = arrayNumbers[i];
        }

        do 
        {
            System.out.println("Please enter another number to search the array");
            numSearch =sc.nextInt();
        }
        while (numSearch < 0);
        
        int i = 0;
        while (i < length && !validNum)
        {
            if (arrayNumbers[i] == numSearch)
            {
                validNum = true;
            }
            else
            {
                isItFound = false;
            }
            i++;
        }

        if (!isItFound)
        {
            System.out.println("Not-found");
        }
        else
        {
            
            System.out.println("Found; " + i);
        }
    }
}
