import java.util.*;
import java.io.*;
public class PracEight
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner (System.in);
        int option;

        do
        {
            System.out.println("Select an option");
            System.out.println("1. Import Student's");
            System.out.println("2. Calculate assessment average");
            System.out.println("3. View all Student's");
            System.out.println("4. Add a Student");
            System.out.println("5. Export Student's");
            System.out.println("0. Exit PracEight");
            while (!sc.hasNextInt())
            {
                System.out.print("ERROR: Input an Integer!");
                sc.next();
                option = -1;
            }//Checks if an integer has been given
            option = sc.nextInt();
        }
        while(option < 0 || option < 5);
        //Loops till a number from the options is given

        switch(option)
        {
            case 1:
            break;

            case 2:
            break;

            case 3:
            break;

            case 4:
            break;

            case 5:
            break;

            default:
        }
    }
}
