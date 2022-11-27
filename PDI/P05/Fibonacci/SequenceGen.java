import java.util.*;
public class SequenceGen
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        char sequenceMode;
        int numberOfTerms, term1, term2;
        int[] terms;
        do
        {
            System.out.println("Select the the mode of generation; (S)ubtraction OR (A)ddition.");
            System.out.println("Please enter your selection in lower case mode!");
            sequenceMode = sc.nextLine().charAt(0);
        }
        while(sequenceMode != 'a' && sequenceMode != 's' );

        do
        {
            System.out.println("Please input the number of terms that should be generated");
            System.out.println("Please make sure its between 5 and 50");
            numberOfTerms = sc.nextInt();
        }
        while(numberOfTerms < 5 && numberOfTerms > 50);

        do
        {
            System.out.println("Enter the first digit");
            term1 = sc.nextInt();

            System.out.println("Enter the second digit");
            term2 = sc.nextInt();
        }
        while((term1 < -100 || term1 > 100) && (term2 < -100 || term2 > 100));


        if (sequenceMode == 'a')
        {
            terms = seqGenAddition(numberOfTerms, term1, term2);
            displayArray1D(terms);
        }

        else
        {
            terms = seqGenSubtraction(numberOfTerms, term1, term2); 
            displayArray1D(terms);
        }
    }
    public static int[] seqGenAddition(int numberOfTerms, int term1, int term2)
    {
        int[] terms = new int[numberOfTerms];
        terms[0] = term1;
        terms[1] = term2;
        
        for(int ee = 2; ee < terms.length; ee++)
        {
            terms[ee] = terms[ee - 1] + terms[ee - 2];
        }
        return terms;        
    }

    public static int[] seqGenSubtraction(int numberOfTerms, int term1, int term2)
    {
        int [] terms = new int[numberOfTerms];
        terms[0] = term1;
        terms[1] = term2;
        
        for(int ee = 2; ee < terms.length; ee++)
        {
            terms[ee] = terms[ee - 2] - terms[ee - 1];
        }
        return terms;        
    }

    public static void displayArray1D(int[] array1D)
    {
        System.out.print("[ ");
        for(int ff=0; ff<array1D.length; ff++)
        {
            System.out.print(array1D[ff] + " ");
        }
        System.out.print("]");
        System.out.println();
    }
}
