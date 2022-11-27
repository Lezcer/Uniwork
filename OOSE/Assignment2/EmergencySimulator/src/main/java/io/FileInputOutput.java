/**
 * Author     Salah Mahamod
 * Student ID 20152428
 * Date       04/06/2022
 * Class      FileInputOutput
 * Package    io
 * Purpose    Class for storing methods that handle reading and writing to / from files.
 */
package edu.curtin.emergencysimulator.io;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import edu.curtin.emergencysimulator.model.*;
import edu.curtin.emergencysimulator.responders.*;

public class FileInputOutput 
{
    private static final Logger logger = Logger.getLogger(FileInputOutput.class.getName());
    public static HashMap<String, Emergency> readFile(String filename, ResponderComm responders)
    {
        HashMap<String, Emergency> emergencies = new HashMap<>();
        // The key to each Emergency value will be the emergency type concatenated with its location as there can only be 1 emergency type in 1 location.
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            
            String line = reader.readLine();
            
            do
            {
                String[] data = line.split(" ", 3);
                // Format of line
                // <Starting time> <Emergency type> <Location>
                if(data.length !=  3)
                    throw new IllegalArgumentException("The line that was read isn't in the right format to be parsed.");
                
                int startingTime = Integer.parseInt(data[0]);
                if(startingTime < 0) // is starting time greater than 0?
                    throw new NumberFormatException("Starting time should be an integer greater than 0.");
                
                String emergencyType = data[1];
            
                if(emergencyType.equals("flood") && emergencyType.equals("fire") && emergencyType.equals("chemical"))
                    throw new NumberFormatException("Incorrect emergency type format."); // if emergencyType isn't fire, flood or chemical
                String location = data[2];
                
                //Create Emergency
                Emergency emergency;
                switch(emergencyType)
                {
                    case "fire":
                        emergency = new Fire(startingTime, location, responders);
                        if(!emergencies.containsKey(emergencyType+location)) // does the key doesn't exist?
                            emergencies.put(emergencyType+location, emergency);
                        break;
                    case "flood":
                        emergency = new Flood(startingTime, location, responders);
                        if(!emergencies.containsKey(emergencyType+location)) // does the key doesn't exist?
                            emergencies.put(emergencyType+location, emergency);
                        break;
                    case "chemical":
                        emergency = new Chemical(startingTime, location, responders);
                        if(!emergencies.containsKey(emergencyType+location)) // does the key doesn't exist?
                            emergencies.put(emergencyType+location, emergency);
                        break;
                }
                line = reader.readLine(); // read new line
            }
            while(line != null);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File doesn't exist.");
            logger.severe("File doesn't exist.");
        }
        catch(IOException e)
        {
            System.out.println("Error while reading file.");
            logger.severe("Error while reading file.");
        }
        catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
            logger.severe(e.getMessage());
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            logger.severe(e.getMessage());
        }
        return emergencies;
    }
}
