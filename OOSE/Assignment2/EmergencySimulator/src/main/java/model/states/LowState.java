/**
 * Author     Salah Mahamod
 * Student ID 20152428
 * Date       04/06/2022
 * Class      LowState
 * Package    model/states
 * Purpose    For the implementation of the FireStates interface, state: fire is in a low intensity state
 */
package edu.curtin.emergencysimulator.model.states;

import edu.curtin.emergencysimulator.model.Fire;
import edu.curtin.emergencysimulator.responders.ResponderComm;
public class LowState implements FireStates
{
    private Fire emergency;
    private ResponderComm responder;

    private final int INACTIVE = 0;
    private final int RESPONDERS_ABSENT = 1;
    private final int RESPONDERS_PRESENT = 2;
    private final int END = 4;

    public final int FIRE_LOW_TO_HIGH_TIME = 10;
    public final int FIRE_LOW_CLEANP_TIME = 5;
    public final double FIRE_LOW_CASUALTY_PROB = 0.2;
    public final double FIRE_LOW_DAMAGE_PROB = 0.3;
    // constants

    public LowState(Fire emergency, ResponderComm responder)
    {
        this.emergency = emergency;
        this.responder = responder;
    }

    public void occur()
    {
        boolean cas = emergency.getProbability(FIRE_LOW_CASUALTY_PROB);
        boolean dam = emergency.getProbability(FIRE_LOW_DAMAGE_PROB);

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

        if(emergency.getState() == RESPONDERS_ABSENT)
        {
            emergency.ongoingAbsent++;
            if(emergency.ongoingAbsent == FIRE_LOW_TO_HIGH_TIME)
            {
                emergency.setState(new HighState(emergency, responder)); // independency injection
                responder.send(emergency.getStatus("fire", "high"));
            }
        }
        else if(emergency.getState() == RESPONDERS_PRESENT)
        {
            emergency.ongoingPresent++;
            if(emergency.ongoingPresent == FIRE_LOW_CLEANP_TIME)
            {
                emergency.setState(new EndState()); // independency injection
                responder.send(emergency.getStatus("fire", "end"));
            }
        }
    }
}
