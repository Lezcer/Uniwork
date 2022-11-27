package states;

public class InnerDoorOpen implements AirlockState
{
    public void pressurise(Airlock airlock)
    {
        
    }// Already pressurised
    public void depressurise(Airlock airlock)
    {
        //Astronaut has closed the inner door
        airlock.pump.beginExtraction();
        if(airlock.sensor.getPressure() < 90)
            airlock.setState(new SealedDepressurised());
        else
            airlock.setState(new Sealed());
    }
    public void openOuterDoor(Airlock airlock)
    {   
    }// Only one door can be open at a time
    public void openInnerDoor(Airlock airlock)
    {   
    }// Door already open
}
