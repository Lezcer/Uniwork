package edu.curtin.addressbook.controller;

import java.util.*;

/**
 * Contains the Option interface.
 * 
 * @author Salah Mahamod
 */
public interface Option
{
    public String doOption(String term);
    public boolean requiresText();
}
