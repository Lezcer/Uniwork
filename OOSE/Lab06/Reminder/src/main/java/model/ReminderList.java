package edu.curtin.reminder.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.curtin.reminder.model.ChangeObs;
import edu.curtin.reminder.model.updateFile;

/**
 * Represents a collection of Reminders.
 */
public class ReminderList
{
    private List<Reminder> reminders;
    private List<ChangeObs> observers;

    public ReminderList()
    {
        reminders = new ArrayList<>();


        /** I didn't know where exactly to put this */
        observers.add(new updateFile());
        observers.add(new updateScreen());
    }
    
    /** Add a single reminder to the list. */
    public void addReminder(Reminder rem)
    {
        reminders.add(rem);
        notifyObservers();
    }
    
    /** Add a complete list of reminders to the existing list. */
    public void addReminders(List<Reminder> newReminders)
    {
        reminders.addAll(newReminders);
    }
    
    /** Remove a reminder by index (i.e. 0 to #reminders-1) */
    public void removeReminder(int index)
    {
        reminders.remove(index);
        notifyObservers();
    }
    
    /** Retrieve a copy of the reminder list. */
    public List<Reminder> getReminders()
    {
        return Collections.unmodifiableList(reminders);
    }

    /** Regarding the observer pattern */
    public void addObservers(ChangeObs newObs)
    {
        observers.add(newObs);
    }
    public void removeObservers(ChangeObs obs)
    {
        observers.remove(obs);
    }

    private void notifyObservers()
    {
        for(ChangeObs obs : observers)
        {
            obs.update(this.reminders);
        }
    }
}
