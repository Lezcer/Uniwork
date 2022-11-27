package edu.curtin.reminder.model;

public class ReminderFileFormatException extends Exception
{
    public ReminderFileFormatException(String msg)
    {
        super(msg);
    }
    
    public ReminderFileFormatException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
