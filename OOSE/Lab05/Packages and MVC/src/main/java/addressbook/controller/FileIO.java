package edu.curtin.addressbook.controller;

import java.io.*;
import java.util.*;

import edu.curtin.addressbook.model.*;

public class FileIO {
    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */

    public static AddressBook readAddressBook(String fileName) throws IOException
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
}