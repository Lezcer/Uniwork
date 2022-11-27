import states.AirlockState;
import states.InnerDoorOpen;

public class Airlock 
{
    Sensor sensor;
    Pump pump;
    Door outer;
    Door inner;
    AirlockState state;

    double currentPressure;

    public Airlock()
    {
        currentPressure = 101.3;
        sensor = new Sensor(this, 101.3);
        pump = new Pump();
        outer = new Door();
        inner = new Door();
        state = new InnerDoorOpen();
    }

    public void setState(AirlockState state)
    {
        this.state = state;
    }

    public void depressurise()
    {
        state.depressurise(this);
    }

    public void pressurise()
    {
        state.pressurise(this);
    }

    public void openOuterDoor()
    {
        state.openOuterDoor(this);
    }

    public void openInnerDoor()
    {
        state.openInnerDoor(this);
    }

    public static void updatePressure(double pressure)
    {
        currentPressure = pressure;
    }
}
