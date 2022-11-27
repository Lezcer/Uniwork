import java.util.*;
import java.lang.*;
public class Functions
{
    //Referencing Practical 2 Submission of 20152428
    //For only Factorial and Fibonacci Recursive Implementations
    public static long factorialWr(int n)
    {
        return factorialRec(n);
    }//Wrapper
    private static long factorialRec(int n)
    {
        long result = 1;
        //Using a variable to get rid of multiple returns
        if (n == 0)
        {
            result = 1;
        }//0! = 1, base case
        else
        {
            result = n * factorialRec(n - 1);
            //Recurive call
        }
        return result;
    }
    
    public static int fibonacciWr(int n)
    {
        return fibonacciRec(n);
    }//Wrapper
    private static int fibonacciRec(int n)
    {
        int answer = 0;
        if (n == 0 || n == 1)
        {
            answer = n;
        }//base cases
        else
        {
            answer = fibonacciRec(n-1) + fibonacciRec(n-2);
            //note 2 recursive calls!
        }
        return answer;
    }
    public static long factorialIter(int n)
    {
        long result = 1;
        for(int i=1; i<=n; i++)
        {
            result = result * i;
        }
        return result;
    }
    
    public static long fibonacciIter(int n)
    {
        long beforePrevNum, prevNum = 0, currentNum = 1;
        
        for(int i=1; i<n; i++)
        {
            beforePrevNum = prevNum;
            prevNum = currentNum;
            currentNum = beforePrevNum + prevNum;
        }
        return currentNum;
    }
}
