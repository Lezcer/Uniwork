package edu.curtin.reminder;

import edu.curtin.reminder.model.*;
import edu.curtin.reminder.controller.Controller;
import edu.curtin.reminder.ui.*;

import java.io.IOException;
import javax.swing.*;

/**
 * A reminder application.
 * @author Dave
 */
public class ReminderApp 
{
    private static final String REMINDER_FILE = "reminders.txt";
    
    public static void main(String[] args)
    {
        FileManager fm = new FileManager();
        ReminderList remindersList = new ReminderList();        
        try
        {
            remindersList.addReminders(fm.read(REMINDER_FILE));
        }
        catch(IOException e)
        {
            System.err.println("Warning: unable to open the reminders file - " + e.getMessage());
        }
        catch(ReminderFileFormatException e)
        {
            System.err.println("Warning: ignoring reminders file due to invalid format - " + e.getMessage());
        }
        // Notice that, in this instance, it's okay to carry on even if we can't read the file. The 
        // app will simply start with an empty reminder list.
        
        Controller controller = new Controller(remindersList);       
            
        // Make sure all GUI-related stuff happens within the "Event Dispatch Thread". (Standard practice for the Java Swing GUI.)
        SwingUtilities.invokeLater(() -> 
        {
            // Create a window (hidden to begin with)
            new MainWindow(controller, new AddReminderWindow(controller)).show();
        });
    }
}
