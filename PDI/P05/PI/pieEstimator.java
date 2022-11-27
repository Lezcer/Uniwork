import java.util.*;
public class pieEstimator
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner (System.in);
        int numTerms;
        double degrees, radians;

        do
        {
            System.out.println("Please input the number of terms that should estimate PI");
            System.out.println("Please make sure its between 20 and 100");
            numTerms = sc.nextInt();
        }
        while(numTerms < 20 || numTerms > 100);

        do
        {
            System.out.println("Please input value for theta");
            System.out.println("Please make sure its between 0 and 360");
            degrees = sc.nextInt();
            radians = Math.toRadians(degrees);
        }
        while(numTerms < 0 || numTerms > 360);
        
        double[] terms = new double[numTerms + 1];
        terms = arraySum(numTerms, degrees);
        displayArray1D(terms);
    }
    
    public static double[] arraySum(int numTerms, double radians)
    {
        double sum = 0;
        double[] terms = new double[numTerms + 1];
        for(int ii=0; ii < (terms.length - 1); ii++)
        {
            terms[ii] = oneTermPie(radians, ii);
            sum = sum + oneTermPie(radians, ii);
        }
        terms[terms.length - 1] = 4 * sum;
        return terms;
    }

    public static double oneTermPie(double radians, int n)
    {
        double k = 2 * ((double)(n));
        double numerator = Math.sin(radians * (k + 1));
        double denominator = k + 1;
        double result = numerator / denominator;
        return result;
    }

    public static void displayArray1D(double[] array1D)
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
