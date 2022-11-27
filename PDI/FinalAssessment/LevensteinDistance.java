import java.util.*;
public class LevensteinDistance
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner (System.in);
        String string1, string2;
        int result;
    
        System.out.println("Please enter the first string");
        string1 = sc.nextLine();
        System.out.println("Please enter the second string");
        string2 = sc.nextLine();
        
        result = calculateLevenshtein(string1, string2);
        //The integer is returned once this method is called
        System.out.println("Levenstein distance is: "+ result);
    }
    
    public static int min(int num1, int num2, int num3)
    {
        int lower = 0;
        //Initialise so it compiles
        if (num1 < num2)
        {
            if (num1 < num3)
            {
                lower = num1;
            }
            else
            {
                lower = num3;
            }
        }
        else
        {
            if (num2 < num3)
            {
                lower = num2;
            }
            else
            {
                lower = num3;
            }
        }
        return lower;
    }
    
    public static int calculateLevenshtein(String x, String y)
    {
    }
    
    public static int costOfSubstitution(char x, char y)
    {
        boolean equals = x.equals(y);
        if (equals)
        {
            result = 0;
        }
        else
        {
            result = 1;
        }
        return result;
    }
}
