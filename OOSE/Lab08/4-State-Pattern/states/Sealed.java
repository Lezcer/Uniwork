package states;

public class Sealed implements AirlockState
{
    public void pressurise(Airlock airlock)
    {     
    }// Already pressurised
    public void depressurise(Airlock airlock)
    {
        airlock.pump.beginExtraction();
        if(airlock.sensor.getPressure() > 90)
            airlock.setState(new Sealed());
    }
    public void openOuterDoor(Airlock airlock)
    {
    }// Needs to be depressurised first
    public void openInnerDoor(Airlock airlock)
    {
        airlock.inner.open();
        airlock.setState(new OuterDoorOpen());
    }
}
