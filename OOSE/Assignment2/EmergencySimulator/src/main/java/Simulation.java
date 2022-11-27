/**
 * Author     Salah Mahamod
 * Student ID 20152428
 * Date       04/06/2022
 * Class      Simulation
 * Package    N/A
 * Purpose    Has main(String[] args)
 */
package edu.curtin.emergencysimulator;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.lang.Thread;

import edu.curtin.emergencysimulator.io.FileInputOutput;
import edu.curtin.emergencysimulator.model.Emergency;
import edu.curtin.emergencysimulator.responders.*;

public class Simulation
{
    private static Scanner input = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Simulation.class.getName());
        public static void main(String[] args)
    {
        // filename from the command line
        String filename = args[0];
        // Instantantiate ResponderCommImpl
        ResponderComm responders = new ResponderCommImpl();

        // Read the file and store it into a Emergency List called Inactive
        HashMap<String, Emergency> emergencies;
        emergencies = FileInputOutput.readFile(filename, responders);
        
        boolean simulate = true;
        int currentTime = 0;
        while(simulate)
        {
            currentTime++;
            // Problem 2: Simulate emergencies
            for(HashMap.Entry<String, Emergency> pair : emergencies.entrySet())
            {
                Emergency emergency = pair.getValue();
                emergency.occur();
            }

            // Problem 3: Poll() || OBSERVER PATTERN
            List<String> updates = responders.poll();
            if(updates.isEmpty()) // No updates
            {
                logger.info("At " + currentTime + " seconds, the responder comm has no updates.");
            }
            else if(updates.get(0) == "end") // "end" update
            {
                simulate = false;// end simulation
                // update responders.
                logger.info("Recieved \"end\" signal from responder comm. Ending Simulation.");
            }
            else // responders arriving / leaving
            {
                logger.info("At " + currentTime + " seconds, the responder comm has updates for " + updates.size() + " emergency events.");
                for(String update : updates)
                {
                    String line[] = update.split(" ", 3);
                    // Format of line
                    // <Emergency type> <+ or -> <Location>
                    Emergency emergency = emergencies.get(line[0]+line[2]);
                    emergency.update(line[1].charAt(0)); // Observer pattern
                }
            }

            // Problem 5: Sleep for 1000 milliseconds
            try
            {
                Thread.sleep(1000);
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
                logger.severe(e.getMessage());
            }
            catch(InterruptedException e)
            {
                System.out.println(e.getMessage());
                logger.severe(e.getMessage());
            }
        }
    }
}
