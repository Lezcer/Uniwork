package states;

public class SealedDepressurised implements AirlockState
{
    public void pressurise(Airlock airlock)
    {
        airlock.pump.beginReturn();
        if(airlock.sensor.getPressure() > 90)
            airlock.setState(new Sealed());  
    }
    public void depressurise(Airlock airlock)
    {
        airlock.pump.beginExtraction();
        if(airlock.sensor.getPressure() > 90)
            airlock.setState(new Sealed());  
    }
    public void openOuterDoor(Airlock airlock)
    {
        airlock.pump.beginExtraction();
        if(airlock.sensor.getPressure() < 5)
        {
            airlock.outer.open();
            airlock.setState(new OuterDoorOpen());
        }
    }
    public void openInnerDoor(Airlock airlock)
    {
    }// Can't open the inner door when depressurised
}
