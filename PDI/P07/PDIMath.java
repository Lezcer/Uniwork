import java.util.*;
public class PDIMath
{
    public static int min(int a, int b)
    {
        int number;
        if (a > b)
        {
            number = b;
        }
        else 
        {
            number = a;
        }
        return number;
    }

    public static double min(int a, double b)
    {
        double number;
        if (a > b)
        {
            number = b;
        }
        else 
        {
            number = a;
        }
        return number;
    }

    public static long min(long a, long b)
    {
        long number;
        if (a > b)
        {
            number = b;
        }
        else 
        {
            number = a;
        }
        return number;
    }

    public static float min(float a, float b)
    {
        float number;
        if (a > b)
        {
            number = b;
        }
        else 
        {
            number = a;
        }
        return number;
    }

    public static int max(int a, int b)
    {
        int number;
        if (a < b)
        {
            number = b;
        }
        else 
        {
            number = a;
        }
        return number;
    }

    public static double max(int a, double b)
    {
        double number;
        if (a < b)
        {
            number = b;
        }
        else 
        {
            number = a;
        }
        return number;
    }

    public static long max(long a, long b)
    {
        long number;
        if (a < b)
        {
            number = b;
        }
        else 
        {
            number = a;
        }
        return number;
    }

    public static float max(float a, float b)
    {
        float number;
        if (a < b)
        {
            number = b;
        }
        else 
        {
            number = a;
        }
        return number;
    }

    public static int abs(int a)
    {
        if (a < 0)
        {
            int b = -1 * a;
        }
        else
        {
            b = a;
        }
        return b;
    }

    public static double abs(double a)
    {
        if (a < 0)
        {
            double b = -1 * a;
        }
        else
        {
            b = a;
        }
        return b;
    }

    public static long abs(long a)
    {
        if (a < 0)
        {
            long b = -1 * a;
        }
        else
        {
            b = a;
        }
        return b;
    }

    public static float abs(float a)
    {
        if (a < 0)
        {
            float b = -1 * a;
        }
        else
        {
            b = a;
        }
        return b;
    }
    public static int floor(double a)
    {
        int truncA = (int)a;
        return truncA;
    }

    public static int ceil(double a)
    {
        int roundUpA = (int)a + 1;
        return roundUpA;
    }
    
    public static double pow(double base, int exponent)
    {
        double result = base;
        if(exponent > 0)
        {
            for(int ii = 2; ii <= exponent; ii++)
            {
                result = result * result;
            }
        }
        else if(exponent < 0)
        {
            for(int ii = 0; ii < exponent; ii--)
            {
                result = 1 / result;
            }
        }
        else
        {
            result = 1;
        }
        return result;
    }
    
    public static double pi(int precision)
    {
        double k, numerator, denominator, result;
        double sum = 0;

        for(int ii=0; ii < precision; ii++)
        {
            k = 2 * ((double)(ii));
            numerator = Math.sin(90 * (k + 1));
            denominator = k + 1;
            result = numerator / denominator;
            sum = sum + oneTermPie(radians, ii);
        }
        return sum;
    }

    public static double e(int precision)
    {
        double sum =0;
        for(int i=0; i< precision; i++)
        {
            sum = sum + 1 / factorial
        }
        return sum;
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
}
