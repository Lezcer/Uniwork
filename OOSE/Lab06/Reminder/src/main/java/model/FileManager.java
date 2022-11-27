package edu.curtin.reminder.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import edu.curtin.reminder.model.ChangeObs;


/**
 * Static methods for reading and writing the reminders file.
 */
public class FileManager implements ChangeObs
{
    public FileManager() {}
    
    /**
     * Reads a reminders file, given a filename, and returns a list of Reminder
     * objects.
     */
    public List<Reminder> read(String filename) throws IOException, ReminderFileFormatException
    {
        List<Reminder> reminders = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                if(!line.trim().isEmpty()) // Ignore empty/whitespace lines
                {
                    String[] parts = line.split(" ", 2);
                    
                    if(parts.length != 2)
                    {
                        throw new ReminderFileFormatException(
                            String.format("Invalid record: '%s'", line));
                    }
                
                    LocalDateTime time;
                    try
                    {
                        time = LocalDateTime.parse(parts[0]);
                    }
                    catch(DateTimeParseException e)
                    {
                        throw new ReminderFileFormatException(
                            String.format("Invalid date-time string: '%s'", parts[0]),
                            e);
                    }
                        
                    reminders.add(new Reminder(parts[1], time));
                }
                line = reader.readLine();
            }
        }
        return reminders;
    }
    
    /**
     * Writes the reminders file, overwriting any existing one, given a
     * filename and a list of Reminders. This method obviously endeavours to 
     * write in the same format that the read() method would expect.
     */
    public void write(String filename, List<Reminder> reminders) throws IOException
    {
        try(PrintWriter writer = new PrintWriter(filename))
        {            
            for(Reminder rem : reminders)
            {
                writer.printf("%s %s\n", rem.getTime().toString(), rem.getTask());
            }
        }
    }

    public void update(List<Reminder> reminders)
    {

    }
}
