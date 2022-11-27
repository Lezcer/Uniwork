/**
 * Author     Salah Mahamod
 * Student ID 20152428
 * Date       04/06/2022
 * Class      Chemical
 * Package    model
 * Purpose    Holds data for the chemical emergency data type
 */
package edu.curtin.emergencysimulator.model;

import edu.curtin.emergencysimulator.responders.ResponderComm;
public class Chemical extends Emergency
{
    // Chemical Emergency Specific data
    public final int CHEM_CLEANUP_TIME = 10;
    public final double CHEM_CASUALTY_PROB = 0.2;
    public final double CHEM_CONTAM_PROB = 0.3;

    // Constructor
    public Chemical(int inTime, String inLocation, ResponderComm res)
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

        boolean cas = getProbability(CHEM_CASUALTY_PROB);
        boolean cont = getProbability(CHEM_CONTAM_PROB);

        switch(state)
        {
            case INACTIVE:
                if(getElapsedTime() == getStartTime())
                {
                    responder.send(getStatus("chemical", "start"));
                    state = RESPONDERS_ABSENT;
                }
                break;
            
            case RESPONDERS_ABSENT:
                if(cas)
                {
                    incrementCasualties();
                    responder.send(getLoss("chemical", "casualty", getCasualties()));
                }
                if(cont)
                {
                    incrementContamination();
                    responder.send(getLoss("chemical", "contam", getContamination()));
                }
                break;

            case RESPONDERS_PRESENT:
            ongoingPresent++;
                if(cas)
                {
                    incrementCasualties();
                    responder.send(getLoss("chemical", "casualty", getCasualties()));
                }
                if(cont)
                {
                    incrementContamination();
                    responder.send(getLoss("chemical", "contam", getContamination()));
                }
                if(ongoingPresent == CHEM_CLEANUP_TIME)
                {
                    responder.send(getStatus("chemical", "end"));
                    state = END;
                }
                break;
            case END:
                // What to do?
                break;
        }    
    }

}