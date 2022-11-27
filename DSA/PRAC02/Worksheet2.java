import java.util.*;
public class Worksheet2
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int choice;

        do
        {
            System.out.println("1. Factorial.");
            System.out.println("2. Fibonacci.");
            System.out.println("3. GCD Finder.");
            System.out.println("4. Decimal Converter.");
            System.out.println("5. Towers of Hanoi");
            System.out.println("0. Exit");
            System.out.print("Select a choice: ");
            choice = sc.nextInt();
        }
        while(choice<0 || choice>5);

        switch(choice)
        {
            case 1:
                System.out.print("Enter the number: ");
                choice = sc.nextInt();
                long result = factorial(choice);
                System.out.println("The result is: " + result);
            break;

            case 2:
                System.out.print("Enter the number: ");
                choice = sc.nextInt();
                int answer = fibonacci(choice);
                System.out.println("The result is: " + answer);
            break;

            case 3:
                System.out.println("You chose GCD Finder.");
                int num1, num2, greatestcd;
                System.out.print("Please enter the first number: ");
                num1 = sc.nextInt();
                System.out.print("Now enter the second number: ");
                num2 = sc.nextInt();
                greatestcd = gcd(num1, num2);
                System.out.println("The GCD is: " + greatestcd);
            break;

            case 4:
                int num, base;
                do
                {
                    System.out.print("Please enter the number: ");
                    num = sc.nextInt();
                }while(num<0);
                do
                {
                    System.out.print("Now enter the base: ");
                    base = sc.nextInt();
                }while(base<2||base>16);
                String alpStr = decConv(num, base);
                System.out.println("The number in base '" + base + "' is: " + alpStr);
            break;

            case 5:
                System.out.println("You just executed the Towers of Hanoi Program.");
                int numOfDisks;
                do
                {
                    System.out.println("How many disks are on peg 1?");
                    numOfDisks = sc.nextInt();
                }
                while(numOfDisks<1); 
                tower(numOfDisks);
            break;

            case 0:
                System.out.println("Bye");
            break;
        }
    }//Main
    
    public static long factorial(int n)
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
    
    public static int fibonacci(int n)
    {
        return fibonacciRec(n);
    }//Wrapper
    private static int fibonacciRec(int n)
    {
        int answer = 0;
        if (n == 0)
        {
            answer = 0;
        }//base case
        else if (n == 1)
        {
            answer = 1;
        }//base case
        else
        {
            answer = fibonacciRec(n-1) + fibonacciRec(n-2);
            //note 2 recursive calls!
        }
        return answer;
    }

    public static int gcd(int num1, int num2)
    {
        return gcdRec(num1, num2);
    }//Wrapper
    private static int gcdRec(int num1, int num2)
    {
        int l = Math.max(num1, num2);//Largest number
        int s = Math.min(num1, num2);//Smallest ..

        int r;
        int gcf = 0;

        if(num1 == 0)
        {
            gcf = num2;
        }//base case
        else if(num2 == 0)
        {
            gcf = num1;
        }//base case
        else
        {
            r = l % s;
            //Obtain remainder
            gcf = gcdRec(s, r);
            //Recursive call
        }
        return gcf;
    }
    
    public static String decConv(int n, int base)
    {
        return decConvRec(n, base);
    }//Wrapper
    private static String decConvRec(int n, int base)
    {
        int r, ans;
        String digit = "", l ="";
        if(base>n)
        {
            digit = Integer.toString(n);
        }//base case
        else
        {
            ans = n / base;//This number is re-used
            r = n % base;//Remainder
            if(r>10)// Or you could say if base>10
            {
                switch(r)
                {
                    case 10:
                        l = "A";
                    break;
                    case 11:
                        l = "B";
                    break;
                    case 12:
                        l = "C";
                    break;
                    case 13:
                        l = "D";
                    break;
                    case 14:
                        l = "E";
                    break;
                    case 15:
                        l = "F";
                    break;
                }//for bases >10, require letters
                digit = decConvRec(ans, base) + l;
            }//recursive call
            else
            {
                digit = decConvRec(ans, base) + Integer.toString(r);
            }//recursive call when the base is 10 or lower
        }
        return digit;
    }
    
    public static void tower(int n)
    {
        try
        {
            towerRec(0, n, 1, 3);//Sets up our recursive method and protects it
        }
        catch(IllegalArgumentException t)
        {
            System.out.println("Something went wrong, try again");
        }
    }//Wrapper with exception handling
    private static void towerRec(int j, int n, int src, int dest)
    {
        if (n ==1)
        {
            moveDisk(j+1, src, dest);
        }
        else
        {
            int tmp = 6 - src - dest;
            towerRec(j+1, n-1, src, tmp);
            moveDisk(j+1, src, dest);
            towerRec(j+1, n-1, tmp, dest);
        }
        //COPIED CODE FROM LECTURE SLIDES
    }
    private static void moveDisk(int j, int src, int dest)
    {
        String str = "";
        if (j == 0)
        {
        }//to make sure 0 has no indentation
        else
        {
            for(int i=1; i<j; i++)
            {
                str += "    ";
            }
        }
        System.out.println(str + "Moving top disk from peg " + src + " to peg " + dest + ".");
    }
}
