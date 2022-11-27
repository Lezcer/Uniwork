package edu.curtin.addressbook.controller;

import java.util.*;

import edu.curtin.addressbook.model.*;

/**
 * Contains the DisplayAll class.
 * 
 * @author Salah Mahamod
 */
public class DisplayAll implements Option
{
    public AddressBook addressBook;

    public DisplayAll(AddressBook inAddressBook)
    {
        addressBook = inAddressBook;
    }
    @Override
    public String doOption(String term) 
    {
        return addressBook.toString();
    }

    @Override
    public boolean requiresText()
    {
        return false;
    }
}