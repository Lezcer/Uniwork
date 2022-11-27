/**
 * Author     Salah Mahamod
 * Student ID 20152428
 * Date       04/06/2022
 * Class      Fire
 * Package    model
 * Purpose    Holds data for the fire emergency data type
 */
package edu.curtin.emergencysimulator.model;

import edu.curtin.emergencysimulator.model.states.*;
import edu.curtin.emergencysimulator.responders.ResponderComm;
public class Fire extends Emergency
{
    private FireStates fireState;

    // Constructor
    public Fire(int inTime, String inLocation, ResponderComm res)
    {
        startTime = inTime;
        location = inLocation;
        responder = res;
        state = INACTIVE;
    }

    // State- dependent method
    @Override
    public void occur()
    {
        incrementTime();

        switch(state)
        {
            case INACTIVE:
                if(getElapsedTime() == getStartTime())
                {
                    responder.send(getStatus("fire", "start"));
                    state = RESPONDERS_ABSENT;
                    setState(new LowState(this, responder)); // State pattern used here
                }
                break;
            
            case RESPONDERS_ABSENT:
                fireState.occur(); // state dependent method call here
                break;

            case RESPONDERS_PRESENT:
                fireState.occur(); // state dependent method call here
                break;
            case END:
                // What to do?
                break;
        }
    }

    public void setState(FireStates state)
    {
        this.fireState = state;
    }
}

