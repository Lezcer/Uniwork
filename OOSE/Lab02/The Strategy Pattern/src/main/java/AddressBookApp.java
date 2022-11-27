package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and ...
 */
public class AddressBookApp 
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        String fileName;
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = readAddressBook(fileName);
            showMenu(addressBook);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }
    
    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private static AddressBook readAddressBook(String fileName) throws IOException
    {
        AddressBook addressBook = new AddressBook();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                String[] parts = line.split(":");

                Entry entry = new Entry(parts);
                addressBook.addEntry(parts[0], entry);

                // FIXME #1: Insert your code here to add a new address book entry.
                // Note: 
                // parts[0] contains the person's name.
                // parts[1], parts[2], etc. contain the person's email address(es).

                /**  
                 * Created an instance of the Entry object
                 * The emails are stored in an ArrayList of Strings
                 * The Entry object is then added to the addressBook
                 * The addressBook holds Contacts in a Map of Entries
                 * The Key is the name of the Contact i.e. parts[0]
                 */              
                
                line = reader.readLine();
            }
        }
        
        return addressBook;
    }
    
    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private static void showMenu(AddressBook addressBook)
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

    public static void addOptions(ArrayList<Option> options, AddressBook addressBook)
    {
        options.add(new SearchByName(addressBook));
        options.add(new SearchByEmail(addressBook));
        options.add(new DisplayAll(addressBook));
    }
}
