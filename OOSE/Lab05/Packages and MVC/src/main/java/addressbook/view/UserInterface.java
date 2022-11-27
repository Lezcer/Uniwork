package edu.curtin.addressbook.view;

import java.util.*;

import edu.curtin.addressbook.controller.*;
import edu.curtin.addressbook.model.*;

public class UserInterface {

    private static Scanner input = new Scanner(System.in);

    public static void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        // Problem 0: Add options to the ArrayList
        ArrayList<Option> options = new ArrayList<Option>();
        addOptions(options, addressBook);

        while(!done)
        {
            int option;

            System.out.println("(1) Search by name\n(2) Search by email\n(3) Display all contacts\n(4) Quit");
       
            try
            {
                option = Integer.parseInt(input.nextLine());
                if(option == 4)
                {
                    done = true;
                    break;
                }
                // Problem 1: Quit option is hard coded
                
                // Problem 2: Access Option ArrayList to get the interface required
                String term = "";
                Option opt = options.get(option-1);
                // Problem 3: Use requiresText() from Option class to see whether an option needs text
                if(opt.requiresText())
                {
                    System.out.println("Enter the term:");
                    term = input.nextLine();
                }
                // Problem 4: Pass the search term
                String output = opt.doOption(term);   
            
                // Problem 5: Display the returned String
                System.out.println(output);
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
    }

    private static void addOptions(ArrayList<Option> options, AddressBook addressBook)
    {
        options.add(new SearchByName(addressBook));
        options.add(new SearchByEmail(addressBook));
        options.add(new DisplayAll(addressBook));
    }

    public static String promptUser(String prompt)
    {
        String fileName;
        System.out.print(prompt);
        fileName = input.nextLine();
        return fileName;
    }
}