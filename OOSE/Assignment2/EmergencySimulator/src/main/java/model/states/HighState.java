/**
 * Author     Salah Mahamod
 * Student ID 20152428
 * Date       04/06/2022
 * Class      HighState
 * Package    model/states
 * Purpose    For the implementation of the FireStates interface, state: fire is in a high intensity state
 */
package edu.curtin.emergencysimulator.model.states;

import edu.curtin.emergencysimulator.model.Fire;
import edu.curtin.emergencysimulator.responders.ResponderComm;
public class HighState implements FireStates
{
    private Fire emergency;
    private ResponderComm responder;

    protected final int INACTIVE = 0;
    protected final int RESPONDERS_ABSENT = 1;
    protected final int RESPONDERS_PRESENT = 2;
    protected final int END = 4;

    public final int FIRE_HIGH_TO_LOW_TIME = 5;
    public final double FIRE_HIGH_CASUALTY_PROB = 0.4;
    public final double FIRE_HIGH_DAMAGE_PROB = 0.6;
    // constants

    public HighState(Fire emergency, ResponderComm responder)
    {
        this.emergency = emergency;
        this.responder = responder;
    }

    public void occur()
    {
        boolean cas = emergency.getProbability(FIRE_HIGH_CASUALTY_PROB);
        boolean dam = emergency.getProbability(FIRE_HIGH_DAMAGE_PROB);

        if(cas)
        {
            emergency.incrementCasualties();
            responder.send(emergency.getLoss("fire", "casualty", emergency.getCasualties()));
        }// Simulate Casualties
        if(dam)
        {
            emergency.incrementDamages();
            responder.send(emergency.getLoss("fire", "damage", emergency.getDamages()));
        }// Simulate Damages

        else if(emergency.getState() == RESPONDERS_PRESENT)
        {
            emergency.ongoingPresent++;
            if(emergency.ongoingPresent == FIRE_HIGH_TO_LOW_TIME)
            {
                emergency.setState(new LowState(emergency, responder)); //independency injection
                emergency.ongoingPresent = 0;
                responder.send(emergency.getStatus("fire", "low"));
            }
        }
    }    
}
