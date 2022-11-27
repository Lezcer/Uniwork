package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

import edu.curtin.addressbook.view.*;
import edu.curtin.addressbook.model.*;
import edu.curtin.addressbook.controller.*;

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
        String fileName = UserInterface.promptUser("Enter address book filename: ");
        
        try
        {
            AddressBook addressBook = FileIO.readAddressBook(fileName);
            UserInterface.showMenu(addressBook);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }
}
