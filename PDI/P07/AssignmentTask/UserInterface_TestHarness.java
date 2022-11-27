import java.util.*;
public class UserInterface_TestHarness
{
    public static void main(String[] args)
    {
        int integer;
        double real;
        char character;
        String string;

        System.out.println("Welcome to the Test Harness for UserInterface");

        System.out.println("Lower = 0, Upper = 100");
        integer = UserInterface.userInput(" ", 0, 100);

        System.out.println("Lower = 0.0, Upper = 100.0");
        real = UserInterface.userInput(" ", 0.0, 100.0);

        System.out.println("Lower = a, Upper = z");
        character = UserInterface.userInput(" ", 'a', 'z');

        System.out.println("Any string!");
        string = UserInterface.userInput(" ");

        System.out.println();
    }
}
