/**
 * Worksheet 07 Question 2: Dependency Injection – Refactoring
 */

public class SecuritySystem implements SensorObserver
{
    private MotionSensor motionSensor;
    private HeatSensor heatSensor
    private Alarm alarm;
    private boolean armed;

    public SecuritySystem(MotionSensor inMotionSen, HeatSensor inHeatSen, Alarm inAlarm)
    {
        motionSensor = inMotionSen;
        heatSensor = inHeatSen;
        alarm = inAlarm;
        armed = false;
        // Refactored code
        // (part A) 
    }

    public void setArmed(boolean newArmed)
    {
        armed = newArmed;
        EmailSystem.sendMessage("Armed: " + newArmed);
    }

    @Override
    public void sensorDetection(Sensor s)
    {
        if(armed)
        {
            alarm.ring();
            EmailSystem.sendMessage("Sensor detection for " +
            s.toString());
        }
    }

    /**
     * Other classes that may need to be refactored are ...
     */
}
