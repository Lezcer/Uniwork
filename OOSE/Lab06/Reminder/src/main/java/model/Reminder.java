package edu.curtin.reminder.model;

import java.time.LocalDateTime;

/**
 * Represents a Reminder, having a piece of text describing the task, and a
 * date/time on which to be reminded about it.
 */
public class Reminder
{
    private String task;
    private LocalDateTime time;
    
    public Reminder(String task, LocalDateTime time)
    {
        this.task = task;
        this.time = time;
    }
    
    public String getTask()        { return task; }
    public LocalDateTime getTime() { return time; }
    
    public void setTask(String task) 
    {
        this.task = task;
    }
    
    public void setTime(LocalDateTime time)
    {
        this.time = time;
    }
}
