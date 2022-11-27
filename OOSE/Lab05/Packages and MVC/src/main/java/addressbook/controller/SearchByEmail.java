package edu.curtin.addressbook.controller;

import java.util.*;

import edu.curtin.addressbook.model.*;
/**
 * Contains the SearchByEmail class.
 * 
 * @author Salah Mahamod
 */
public class SearchByEmail implements Option
{
    public AddressBook addressBook;

    public SearchByEmail(AddressBook inAddressBook)
    {
        addressBook = inAddressBook;
    }

    @Override
    public String doOption(String term) {
        Entry contact = addressBook.getEntryEmail(term);
        return contact.getStringEntry();
    }

    @Override
    public boolean requiresText()
    {
        return true;
    }
}

