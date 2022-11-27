package states;

public interface AirlockState 
{
    public void pressurise(Airlock airlock);
    public void depressurise(Airlock airlock);
    public void openOuterDoor(Airlock airlock);
    public void openInnerDoor(Airlock airlock);
}
