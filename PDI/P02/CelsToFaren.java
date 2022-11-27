import java.util.*;
        //Comment
public class CelsToFaren
        //Comment
{
    public static void main(String[] args)
        //Comment
    {
        Scanner sc= new Scanner(System.in);
        //Comment
        double F, C;
        //Comment

        System.out.print("What is the temperature in Celsius ");
        //Comment
        C = sc.nextDouble();
        //Comment
        F = C * 9/5  + 32;
        //Comment

        System.out.println("The temperature in Fahrenheit is " + F);
        //Comment
    }
}

