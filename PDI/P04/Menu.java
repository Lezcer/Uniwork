//Author: SALAH MAHAMOD
//Created on 21/03/2020
//Purpose: create a program with multiple functionalities such as converting
//temperatures, addition of two integers and the conversion of a character to
//an integer. Also splitting a date!
//Last date modified 24/03/2020
//Submitted 25/03/2020

import java.util.*;
public class Menu
{
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        char temp, choice, upperCase, lowerCase;
        int selection, numOne, numTwo, answer, decimal, firstNo, secondNo, date, day, month, year, lastDigit;
        double tempCel, tempFah; 
        String monthName = "";        
        String suffix = ""; 
        boolean validDate;
        //Variables monthName and suffix weren't initialised, thus added changed it to (String variable = "";) 

        System.out.println("Welcome to Programming Design and Implementation, Workshop 3");
        System.out.println("What would you like to do? Please enter a number from 0 - 5");
        System.out.println("(1) Sum of 2 integers");
        System.out.println("(2) Convert Temperature");
        System.out.println("(3) Convert a character's case OR display its ASCII value");
        System.out.println("(4) Check if 2 integers are divisible");
        System.out.println("(5) Split a date into its components");
        System.out.println("(0) Exit Menu");
        selection = sc.nextInt();

        if (selection == 1)
        {
            System.out.println("Please enter the first number");
            numOne = sc.nextInt();

            System.out.println("Please enter the second number");
            numTwo = sc.nextInt();
            
            answer = numOne + numTwo;

            System.out.println("The answer is: " + answer);
        }

        else if (selection == 2) 
        {
            System.out.println("What temperature scale are you working with?");
            System.out.println("(C)elsius");
            System.out.println("(F)ahrenheit");
            temp = sc.next().charAt(0);
            //Even if the user inputs celsius instead of c / C, the charAt(0) ensures the first letter is taken

            switch(temp) 
            {
                case 'c': case 'C':
                    System.out.println("Please input the temperature in Celsius");
                    tempCel = sc.nextDouble();
                    tempFah = tempCel * 9/5 + 32;
                    System.out.println("Temperature in Fahrenheit is: " + tempFah);
                break;

                case 'f': case 'F':
                    System.out.println("Please input the temperature in Fahrenheit");
                    tempFah = sc.nextDouble();
                    tempCel = (tempFah - 32) * 5 / 9;
                    System.out.println("Temperature in Celsius is: " + tempCel);
                break;

                default: 
                    System.out.println("Error, Please enter either C or F");
            }
        }

        else if (selection == 3) 
        {
            System.out.println("Are you working with (L)owercase or (U)ppercase");
            choice = sc.next().charAt(0);
            
            switch(choice)
            {
                case 'l': case 'L':
                    System.out.println("Please enter a lower case character");
                    lowerCase = sc.next().charAt(0);
                    
                    decimal = (int)(lowerCase);
                    // changes lowerCase to its integer equivalent

                    if ((decimal < 97) || (decimal > 122))
                    {
                        System.out.println("Error, Please enter a lower case character between a - z");
                        //just incase user inputs non-alphabetical characters such as [ , ' -
                    }

                    else
                    {
                        upperCase = (char)(decimal - 32); 
                        //integer is placed back 32 places then is converted back to a character

                        System.out.println("The upper case of " + lowerCase + " is: " + upperCase);
                        System.out.println("The ASCII value of " + lowerCase + " is: " + decimal);
                    }
                break; 

                case 'u': case 'U':
                    System.out.println("Please enter an upper case character");
                    upperCase = sc.next().charAt(0);

                    decimal = (int)(upperCase);
                    
                    if ((decimal < 65) || (decimal > 90))
                    {
                        System.out.println("Error, Please enter an upper case character between A - Z");
                    }
                    else 
                    {
                         
                        lowerCase = (char)(decimal + 32);

                        System.out.println("The lower case of " + upperCase + " is: " + lowerCase);
                        System.out.println("The ASCII value of " + upperCase + " is: " + decimal);
                    }
                break;

                default: 
                    System.out.println("Error, Please make an appropiate choice, either L or U.");
                    // if the user does not chose l / L / u / U
            }
        }

        else if (selection == 4)
        {
            System.out.println("In order to check if two integers are divisible please enter your first number");
            firstNo = sc.nextInt();

            System.out.println("Please enter your second number");
            secondNo = sc.nextInt();

            if (firstNo % secondNo == 0) 
            {
                System.out.println("The two integers are DIVISIBLE");
                // i.e. remainder is equal to 0
            }

            else 
            {
                System.out.println("the two integers are NOT DIVISIBLE");
            }
        }

        else if (selection == 5)
        {
            validDate = true; 
            // assumes the date is true

            System.out.println("Please enter the date you would like to split");
            System.out.println("Please make sure it is in the format DDMMYYYY");
            date = sc.nextInt();
            
            day = date / 1000000;
            month = (date / 10000) - (day * 100);
            year = date - (date / 10000) * (10000);
            lastDigit = day % 10;
            // suffix is determined by the last digit. i.e. 121 = hundred and twenty first, thus assuming the user inputs 
            // a day that is less than 100, the last digit will be obtained

            switch(lastDigit) 
            {
                case 1: 
                    suffix = "st";
                break;

                case 2:
                    suffix = "nd";
                break;

                case 3:  
                    suffix = "rd";
                break;

                case 4: case 5: case 6: case 7: case 8: case 9: case 0:
                    suffix = "th";
                break;
            }
            
            switch(month)
            {
                case 4: case 6: case 9: case 11: 
                    if ((day < 1) || (day > 30))
                    {
                        System.out.println("Error this day does not exist");
                        validDate = false; 
                    }
                break;

                case 1: case 3: case 5: case 7: case 8: case 10: case 12: 
                    if ((day < 1) || (day > 31))
                    {
                        System.out.println("Error this day does not exist");
                        validDate = false;
                    }
                break;

                case 2:
                    if ((day < 1) || (day > 28))
                    {
                        if ((day != 29)) 
                        {
                            System.out.println("Error this day does not exist");
                            validDate = false;
                        }
                        else if ((year % 4 != 0) || ((year % 400 != 0) && (year % 100 == 0)))
                        {
                            System.out.println("Error this day does not exist, as it is not a Leap year");
                            validDate = false;
                        }
                            //If it isn't a leap year, febuary cannot have 29 days
                    }
                break;
                default:
                    System.out.println("Error this day does not exist");
                    validDate = false;
            }

            switch(month)
            {
                case 1:
                    monthName = "January";
                break;
                case 2:
                    monthName = "Febuary";
                break;
                case 3: 
                    monthName = "March";
                break;
                case 4:
                    monthName = "April";
                break;
                case 5: 
                    monthName = "May";
                break; 
                case 6: 
                    monthName = "June";
                break;
                case 7:
                    monthName = "July";
                break;
                case 8:
                    monthName = "August";
                break;
                case 9: 
                    monthName = "September";
                break; 
                case 10:
                    monthName = "October";
                break;
                case 11: 
                    monthName = "November";
                break;
                case 12:
                    monthName = "December";
                default:
                    System.out.println("Error, Please make sure the month (i.e. MM) is between 01 and 12");
                    //there are no names for months greater than 12, because they don't exist
                    validDate = false;
                break;
            }
            
            if ((day == 29) && (month == 2))
            {
                if (validDate)
                {
                    System.out.println("It is the 29th day of Febuary in " + year + ". It's a Leap year!");
                }
            }
            else 
            {
                if (validDate)
                    System.out.println("It is the " + day + suffix + " day of " + monthName + " in " + year + ".");
            }
        }

        else if (selection == 0) 
        {
            System.out.println("GOOD-BYE :)");
        }

        else 
        {
            System.out.println("Error please enter an integer between 0 - 5 in order to proceed.");
            // if the user has entered an integer other than 0, 1, 2, 3, 4 or 5
        }
    }
}
