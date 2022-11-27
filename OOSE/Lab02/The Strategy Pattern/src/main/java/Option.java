package edu.curtin.addressbook;

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
