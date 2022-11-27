package edu.curtin.reminder.controller;
import edu.curtin.reminder.model.Reminder;
import edu.curtin.reminder.model.ReminderList;

import java.util.List;
import java.time.LocalDateTime;

/**
 * The controller of the reminders application. The responsibilities of this 
 * class are light -- it's a thin layer between the View and the Model.
 */
public class Controller
{
    private ReminderList list;

    /**
     * Takes in an existing ReminderList -- the Model -- and populates it with
     * data read from the reminders file.
     */
    public Controller(ReminderList list)
    {    
        this.list = list;
    }
    
    /** Used by the UI when a reminder needs adding. */
    public void addReminder(String task, LocalDateTime time)
    {
        list.addReminder(new Reminder(task, time));
    }
    
    /** Used by the UI when a reminder needs removing. */
    public void removeReminder(int index)
    {
        list.removeReminder(index);
    }
    
    /** Used by the UI to obtain reminders to display. */
    public List<Reminder> getReminders()
    {
        return list.getReminders();
    }
}
