/**
 * Author     Salah Mahamod
 * Student ID 20152428
 * Date       04/06/2022
 * Class      Flood
 * Package    model
 * Purpose    Holds data for the flood emergency data type
 */
package edu.curtin.emergencysimulator.model;

import edu.curtin.emergencysimulator.responders.ResponderComm;
public class Flood extends Emergency
{
    // Flood Emergency Specific data
    public final int FLOOD_END_TIME = 10;
    public final double FLOOD_DAMAGE_PROB = 0.2;
    public final double FLOOD_CASUALTY_PROB = 0.3;

    // Constructor
    public Flood(int inTime, String inLocation, ResponderComm res)
    {
        startTime = inTime;
        location = inLocation;
        responder = res;
        state = INACTIVE;
    }

    @Override
    public void occur() 
    {
        incrementTime();

        boolean cas = getProbability(FLOOD_CASUALTY_PROB);
        boolean dam = getProbability(FLOOD_DAMAGE_PROB);
        
        switch(state)
        {
            case INACTIVE:
                if(getElapsedTime() == getStartTime())
                {
                    responder.send(getStatus("flood", "start"));
                    state = RESPONDERS_ABSENT;
                }
                break;
            
            case RESPONDERS_ABSENT:
                simulTime++;
                if(cas)
                {
                    incrementCasualties();
                    responder.send(getLoss("flood", "casualty", getCasualties()));
                }
                if(dam)
                {
                    incrementDamages();
                    responder.send(getLoss("flood", "damage", getDamages()));
                }
                if(simulTime == FLOOD_END_TIME)
                {
                    responder.send(getStatus("flood", "end"));
                    state = END;
                }
                break;

            case RESPONDERS_PRESENT:
                simulTime++; // For floods it doesn't matter
                if(dam)
                {
                    incrementDamages();
                    responder.send(getLoss("flood", "damage", getDamages()));
                }
                if(simulTime == FLOOD_END_TIME)
                {
                    responder.send(getStatus("flood", "end"));
                    state = END;
                }
                break;
            case END:
                // What to do?
                break;
        }
    }
}
