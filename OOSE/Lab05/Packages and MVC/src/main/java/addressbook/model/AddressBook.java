package edu.curtin.addressbook.model;

import java.util.*;
/**
 * Contains all the address book entries.
 * 
 * @author Salah Mahamod
 */

 
public class AddressBook
{
    // Insert your code here.
    Map<String,Entry> contacts = new HashMap<String, Entry>();

    public void addEntry(String key, Entry contact)
    {
        contacts.put(key, contact);
    }

    public Entry getEntryName(String name)
    {
        return contacts.get(name);
    }

    public Entry getEntryEmail(String email)
    {
        Entry entry = new Entry();
        for(String name : contacts.keySet())
        {
            entry = getEntryName(name);
            if(entry.getIndexEmail(email) != -1)
            {
                break;
            }
        }
        return entry; 
    }

    public void displayAll()
    {
        for(Entry entry : contacts.values())
        {
            entry.displayEntry();
        }
    }

    public String toString()
    {
        String str = "";
        for(Entry entry : contacts.values())
        {
            str = str + entry.getStringEntry() + "\n";
        }
        return str;
    }
    
}
