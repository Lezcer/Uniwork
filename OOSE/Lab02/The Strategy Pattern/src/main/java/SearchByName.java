package edu.curtin.addressbook;

import java.util.*;

/**
 * Contains the SearchByName class.
 * 
 * @author Salah Mahamod
 */
public class SearchByName implements Option
{
    public AddressBook addressBook;

    public SearchByName(AddressBook inAddressBook)
    {
        addressBook = inAddressBook;
    }

    @Override
    public String doOption(String term) 
    {
        Entry contact = addressBook.getEntryName(term);
        return contact.getStringEntry();
    }

    @Override
    public boolean requiresText()
    {
        return true;
    }
}
