import java.util.*;
public class factorial
{
    public static void main(String[] args)
    {
        DSAStack stack = new DSAStack();
        stack.push("Main, Imp: None, Exp: None");
        stack.displayArray();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number: ");
        int choice = sc.nextInt();

        long result = factorial(choice, stack);

        Object bin = stack.pop();
        stack.displayArray();

        System.out.println("The result is: " + result);
        bin = stack.pop();
        stack.displayArray();
    }
    public static long factorial(int n, DSAStack stack)
    {
        stack.push("factorial, Imp: n (Integer), Exp: result (Long)");
        stack.displayArray();
        return factorialRec(n, stack);
    }//Wrapper
    private static long factorialRec(int n, DSAStack stack)
    {
        stack.push("factorialRec, Imp: n (Integer), Exp: result (Long)");

        long result = 1;
        //Using a variable to get rid of multiple returns
        if (n == 0)
        {
            result = 1;
        }//0! = 1, base case
        else
        {
            result = n * factorialRec(n - 1, stack);
            //Recurive call
        }

        Object bin = stack.pop();
        stack.displayArray();

        return result;
    }
}
