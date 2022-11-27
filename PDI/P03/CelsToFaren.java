//Author: Salah Mahamod
//Created on 12/03/2020
//Last modified on 
import java.util.*;
public class CelsToFaren
{
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        char temp;
        double Cel, Fah;
        //for Cel and Fah, assume the user inputs either a integer or real


        System.out.println("What temperature are you working with ");
        System.out.println("    >(C)elsius");
        System.out.println("    >(F)ahrenheit ");
        temp = sc.nextLine().charAt(0);
        
        if ((temp == 'C') || (temp == 'c'))
        {
            System.out.println("Please input temperature in Celsius " );
            Cel = sc.nextDouble();
            Fah = Cel * 9/5 + 32; 
            System.out.println("Temperature in Fahrenheit is: " + Fah);
        }
        else if ((temp == 'F') ||(temp == 'f'))
        {
            System.out.println("Please input temperature in Fahrenheit " );
            Fah = sc.nextDouble();
            Cel = (Fah - 32) * 5/9;
            System.out.println("Temperature in Celsius is: " + Cel);
        }
        else 
        {
        System.out.println("Invalid input, please enter C or F");
        //If user inputs neither c, C, f or F this desplays an error
        }
    }
}

