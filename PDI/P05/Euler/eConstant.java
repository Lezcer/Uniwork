import java.util.*;
public class eConstant
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner (System.in);
        int choice;
        do
        { 
            System.out.println("Please input a number between 6 and 100 (inclusive) for the number of terms");
            choice = sc.nextInt();
        }
        while(choice < 6 || choice > 100);

        double[] terms = new double[choice + 1];

        for(int i=0; i<(terms.length - 1); i++)
        {
            terms[i] = 1 / factorial(i);
        }

        double sum =0;
        for(int i=0; i<(terms.length - 1); i++)
        {
            sum = sum + terms[i];
        }
        terms[terms.length - 1] = sum;

        System.out.print("Terms: [ ");
        for(int i=0; i<terms.length; i++)
        {
            System.out.print(terms[i] + " ");
        }
        System.out.print("]");        
        System.out.println();        
    }
    
    public static double factorial(double number)
    {
        double result = 1;
        for(int x=1; x<=number; x++)
        {
            result = result * x;
        }
        return result;
    }

    public static double round(double number)
    {
        double result1 = (int)(number * 100000);
        double result2 = (double)(result1);
        double roundedNum = result2 / 100000;
        return roundedNum;
    }
}
