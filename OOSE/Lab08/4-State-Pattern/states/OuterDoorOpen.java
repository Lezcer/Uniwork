package states;

public class OuterDoorOpen implements AirlockState
{
    public void pressurise(Airlock airlock)
    {
        //Astronaut has closed the outer door
        airlock.pump.beginReturn();
        if(airlock.sensor.getPressure() < 90)
            airlock.setState(new SealedDepressurised());
        else
            airlock.setState(new Sealed());
    }
    public void depressurise(Airlock airlock)
    {
    }// Already depressurised
    public void openOuterDoor(Airlock airlock)
    {   
    }// Door already open
    public void openInnerDoor(Airlock airlock)
    {
    }// Only one door can be open at a time
}
