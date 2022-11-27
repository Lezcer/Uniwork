import java.util.*;
//Heavily Inspired by sorts test harness written by Andrew Turpin

class FA_RecursionHarness
{
    public static void main(String[] args)
    {
        int n;
        char functionToUse, methodOfImp;
        long result=0, startTime=1, endTime=1, runningTotal;
        if(args.length == 2)
        {
            n = Integer.parseInt(args[0]);
            functionToUse = args[1].charAt(0);
            methodOfImp = args[1].charAt(1);

            switch(functionToUse)
            {
                case 'l' :
                    if(methodOfImp == 'r')
                    {
                        startTime = System.nanoTime();
                        result = Functions.factorialWr(n);    
                        endTime = System.nanoTime();
                    }
                    else if(methodOfImp == 'i')
                    {
                        startTime = System.nanoTime();
                        result = Functions.factorialIter(n);    
                        endTime = System.nanoTime();
                    }
                    else
                    {
                        System.out.println("Wrong letter! for method of implementation");
                    }
                break;
                case 'i' :
                    if(methodOfImp == 'r')
                    {
                        startTime = System.nanoTime();
                        result = Functions.fibonacciWr(n);    
                        endTime = System.nanoTime();
                    }
                    else if(methodOfImp == 'i')
                    {
                        startTime = System.nanoTime();
                        result = Functions.fibonacciIter(n);    
                        endTime = System.nanoTime();
                    }
                    else
                    {
                        System.out.println("Wrong letter! for method of implementation");
                    }
                break;
                default :
                    System.out.println("Wrong letter! for function");
            }
        
            runningTotal = (int)((double)(endTime-startTime) / 10000.0);
            System.out.println("Number used is: "+n+", the result is: "+result);
            System.out.println("This took: " + runningTotal+" microseconds.");
        }
        else
        {
            usage();
        }
        
    }
    private static void usage() 
    {
        System.out.println(" Usage: java FA_RecursionHarness n xy [xy ...]");
        System.out.println("        where");
        System.out.println("        n is number to use in the functions");
        System.out.println("        x is one of");
        System.out.println("           l - factorial");
        System.out.println("           i - fibonacci");
        System.out.println("        y is one of");
        System.out.println("           r - recursive");
        System.out.println("           i - iterative");
    }
}
