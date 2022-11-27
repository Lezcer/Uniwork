import java.util.*;
public class Markup
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner (System.in);
        int numTerms, k;
        
        do
        {
            System.out.println("Please enter the number of terms you would like to approximate Markup's constant.");
            System.out.println("Please make sure it's between 2 and 35 (Inclusive).");
            numTerms = sc.nextInt();
        }
        while(numTerms < 2 || numTerms > 35);

        do
        {
            System.out.println("Please enter the number for the precision of each individual term.");
            System.out.println("Please make sure it's between 5 and 100 (Inclusive).");
            k = sc.nextInt();
        }
        while(k < 5 || k > 100);

        double[] terms = new double[numTerms + 1];
        terms = calculateTerm(numTerms, k);
        displayArray(terms);
    }
    
    public static double[] calculateTerm(int numTerms, int k)
    {
        double[] terms = new double[numTerms + 1];
        double sum = 0;
        for(int i=0; i < (terms.length - 1); i++)
        {
            terms[i] = expressionCalc(numTerms, k, i);
            sum = sum + terms[i];
        }
        terms[terms.length - 1] = sum;
        return terms;
    }

    public static double expressionCalc(int k, int numTerms, int i)
    {
        double product = 1.0;
        for(int j=0; j <= k; j++)
        {
            double r = (double)numTerms;
            double s = (double)k;
            double t = (double)i + 1.0;
            double u = (double)j;
            
            double numerator = Math.pow(t, 2.0) * (u + 1.0);
            double denominator = 4.0 * r * s;
            
            double block = numerator / denominator;
            product = product * block;
        }
        double result = product;
        return result;
    }

    public static void displayArray(double[] array1Dimension)
    {
        System.out.print("[ ");
        for(int ff = 0; ff < array1Dimension.length; ff++)
        {        
            System.out.print(array1Dimension[ff] + " ");
        }
        System.out.print("]");
        System.out.println();
    }
}
