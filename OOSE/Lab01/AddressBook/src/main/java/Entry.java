package edu.curtin.addressbook;

import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author Salah Mahamod
 */
public class Entry 
{
    // Insert your code here.
    String name;
    ArrayList<String> emails = new ArrayList<String>();
    public Entry()
    {
    }

    public Entry(String[] parts)
    {
        name = parts[0];
        for(int i = 1; i < parts.length; i++)
        {
            emails.add(parts[i]);
        }
    }
    //Constructor

    public void displayEntry()
    {
        System.out.print("Contact's Name: " + name + ", Contact's Email(s): ");
        for(int i = 0; i < emails.size(); i++)
        {
            System.out.print(emails.get(i));
            System.out.print(" ");
        }
        System.out.println();
    }
    // Displays contact details

    public int getIndexEmail(String email)
    {
        return emails.indexOf(email);
    }
}
