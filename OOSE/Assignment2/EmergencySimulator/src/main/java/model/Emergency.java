/**
 * Author     Salah Mahamod
 * Student ID 20152428
 * Date       04/06/2022
 * Class      Emergency
 * Package    model
 * Purpose    Abstract class for defining behaviour shared by all Emergency types
 */
package edu.curtin.emergencysimulator.model;

import edu.curtin.emergencysimulator.responders.ResponderComm;
import java.lang.Math;
public abstract class Emergency
{
    // Emergency Data
    protected int startTime;
    protected int elapsedTime;
    protected int simulTime;
    protected String location;
    protected int casualties = 0; // total people hospitalised
    protected int damages = 0; // total properties destroyed
    protected int contamination = 0; // total contamination
    protected ResponderComm responder;

    // Emergency States (Explanation below)
    protected int state;
    protected final int INACTIVE = 0;
    protected final int RESPONDERS_ABSENT = 1;
    protected final int RESPONDERS_PRESENT = 2;
    protected final int END = 4;
    public int ongoingAbsent;
    public int ongoingPresent;// Need to be public so it can be accessed by FireStates
    /**
     * Explanation as to why I didn't implement the state pattern here:
     * For each emergency, fire, flood and chemical their implementation of InactiveState, ResponAbsState, ResponPreState 
     * would all be under 1 interface "EmergencyStates" and this means that the implementation of the functions in EmergencyStates
     * would be the same for all types of emergencies. I have used the state implementation here and have used my state pattern 
     * implementation elsewhere.
     */
    
    // Method to be overriden
    public void occur(){}
    
    // Setters
    public void incrementTime()
    {
        elapsedTime++;
    }
    public void incrementCasualties()
    {
        casualties++;
    }
    public void incrementDamages()
    {
        damages++;
    }
    public void incrementContamination()
    {
        contamination++;
    }
    
    // Getters
    public int getElapsedTime()
    {
        return elapsedTime;
    }
    public int getStartTime()
    {
        return startTime;
    }
    public int getCasualties()
    {
        return casualties;
    }
    public int getDamages()
    {
        return damages;
    }
    public int getContamination()
    {
        return contamination;
    }
    public String getLocation()
    {
        return location;
    }
    public boolean getProbability(double chance)
    {
        boolean event = false;
        double prob = Math.random();
        if(prob < chance)
        {
            event = true;
        }
        /**
         * MY LOGIC:
         * For example, if an event has a 60% chance of happening,
         * a random number between 0 and 1 is generated -> "prob"
         * if prob lands on any number between 0 - 0.6 ---
         * [The probability of this happening is 60% which is the same likelihood as the event]
         * --- then that means the event happens.
         */
        return event;
    }
    public String getStatus(String emergency, String statusChange)
    {
        String status = emergency + " " + statusChange + " " + getLocation();
        return status;
    }
    public String getLoss(String emergency, String type, int total)
    {
        String status = emergency + " " + type + " " +  total + " " + getLocation();
        return status;
    }
    public int getState()
    {
        return state;
    }

    // Observer Pattern dependent methods
    public void update(char sign)
    {
        if(sign == '+' && state == RESPONDERS_ABSENT)
        {
            state = RESPONDERS_PRESENT;
            ongoingPresent = 0;
        }
        else if(sign == '-' && state == RESPONDERS_PRESENT)
        {
            state = RESPONDERS_ABSENT;
            ongoingAbsent = 0;
        }
        /**
         * Observer Pattern used here, when the Simulation class gets updates from poll() from ResponderCommImpl
         * the notifyObservers() function will call this clas of all observers (which are the Emergencies)
         */
    }
}