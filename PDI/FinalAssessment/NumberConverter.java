import java.util.*;
public class NumberConverter
{
    public static void main(String[] args)
    {
        int choice, option;
        String number;
        String[] splitNumber = String[2];
        String errorMsg = "Please enter an appropiate input for your selection when you try again.";
        //Main Menu
        do
        {
            System.out.println("Welcome to number converter, what number type are you currently working with: ");
            System.out.println("1. Binary");
            System.out.println("2. Decimal");
            System.out.println("3. Octal");
            System.out.println("4. Hexadecimal");
            System.out.println("0. Exit");
            try
            {
                choice = sc.nextInt();
            }
            catch(InputMismatchException)
            {
                System.out.println("Error, Please enter an integer");
            }
        }
        while(choice < 0 || choice > 4);

        System.out.println("Enter the alphanumeric / numeric: ");
        number = sc.nextLine();
        //Alphanumeric is inputted it will be validated later

        switch(choice)
        {
            case 1:
                splitNumber = number.split(".\\");
                if (validate(splitNumber[0], 0, 1) && validate(splitNumber[1], 0, 1))
                //Validtates both parts if these are true then the number isnt altered
                {
                }
                else 
                {
                    System.out.println(errorMsg);
                    number = null;
                    //reset value as its invalid
                }
            break;
            case 2:
                splitNumber = number.split(".\\");
                if (validate(splitNumber[0], 0, 9) && validate(splitNumber[1], 0, 9))
                //Validtates both parts if these are true then the number isnt altered
                {
                }
                else 
                {
                    System.out.println(errorMsg);
                    number = null;
                    //reset value as its invalid
                }
            break;
            case 3:
                if (validate(number, 0, 7)
                //Validtates the number if its true then the number isnt altered
                {
                }
                else 
                {
                    System.out.println(errorMsg);
                    number = null;
                    //reset value as its invalid
                }
            break;
            case 4:
                if (validateHex(number)
                //Validtates the number if its true then the number isnt altered
                {
                }
                else 
                {
                    System.out.println(errorMsg);
                    number = null;
                    //reset value as its invalid
                }
            break;
            default:
        }


        do
        {
            System.out.println("What number type do you want to convert it to: ");
            System.out.println("1. Binary");
            System.out.println("2. Decimal");
            System.out.println("3. Octal");
            System.out.println("4. Hexadecimal");
            try
            {
                option = sc.nextInt();
            }
            catch(InputMismatchException)
            {
                System.out.println("Error, Please enter an integer");
            }
        }
        while(option < 1 || option > 4);
        
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
        }
    }
    public static boolean validate(String number, int lower, int upper)
    {
    }
    
    public static boolean validateHexa(String number)
    {
    }
    
}
