import java.util.*;
public class UserInterface
{

    public static int userInput(String prompt, int lower, int upper)
    {
        Scanner sc = new Scanner (System.in);
        int choice;
        prompt = "Please enter a value: ";
        String errorMsg = " ";
        

        do
        {
            System.out.print(prompt);
            while(!sc.hasNextInt())
            {
                    displayError("Error, Input a number between " + lower + " and " + upper + ". ");
                    sc.next();
                    choice = lower - 1;
            }
            choice = sc.nextInt();
        }
        while(choice < lower || choice > upper);
        return choice;
    }

    public static double userInput(String prompt, double lower, double upper)
    {
        Scanner sc = new Scanner (System.in);
        double choice;
        prompt = "Please enter a value: ";
        String errorMsg = " ";

        do
        {
            System.out.print(prompt);
            while(!sc.hasNextDouble())
            {
                    displayError("Error, Input a number between " + lower + " and " + upper + ". ");
                    sc.next();
                    choice = lower - 1;
            }
            choice = sc.nextDouble();
        }
        while(choice < lower || choice > upper);
        return choice;
    }

    public static long userInput(String prompt, long lower, long upper)
    {
        Scanner sc = new Scanner (System.in);
        long choice;
        prompt = "Please enter a value: ";
        String errorMsg = " ";

        do
        {
            System.out.print(prompt);
            while(!sc.hasNextLong())
            {
                    displayError("Error, Input a number between " + lower + " and " + upper + ". ");
                    sc.next();
                    choice = lower - 1;
            }
            choice = sc.nextLong();
        }
        while(choice < lower || choice > upper);


        return choice;
    }

    public static float userInput(String prompt, float lower, float upper)
    {
        Scanner sc = new Scanner (System.in);
        float choice;
        prompt = "Please enter a value: ";
        String errorMsg = " ";

        do
        {
            System.out.print(prompt);
            while(!sc.hasNextFloat())
            {
                    displayError("Error, Input a number between " + lower + " and " + upper + ". ");
                    sc.next();
                    choice = lower - 1;
            }
            choice = sc.nextFloat();
        }
        while(choice < lower || choice > upper);
        return choice;
    }

    public static char userInput(String prompt, char lower, char upper)
    {
        Scanner sc = new Scanner (System.in);
        char choice;
        prompt = "Please enter a character: ";
        String errorMsg = " ";

        int min = (int)lower;
        int max = (int)upper;
        int choiceInt;

        do
        {
            System.out.print(prompt);
            choice = sc.nextLine().charAt(0);
            choiceInt = (int)choice;
            displayError(errorMsg);
            errorMsg = "Error, Input a character between " + lower + " and " + upper + ". ";
        }
        while(choiceInt < min || choiceInt > max);
        return choice;
    }

    public static String userInput(String prompt)
    {
        Scanner sc = new Scanner (System.in);
        System.out.print(prompt);
        String str = sc.nextLine();
        return str;
    }
    
    public static void displayError(String error)
    {
        System.out.print(error);
    }
}

