/**
 * Question 2: Part B
 */

public class DependencyInjector 
{
    /**
     * // Example of ejector code, that creates an instance of SecuritySystem
     * @param Hardware
     */
    public void foo(Hardware hw)
    {
        SensorBundle sens = hw.getSensors();
        MotionSensor motionSensor = sens.getMotionSensor();
        HeatSensor heatSensor = sens.getHeatSensor();
        Alarm alarm = new Alarm();

        SecuritySystem system = new SecuritySystem(motionSensor, heatSensor, alarm);
        // Inject SecuritySystem's dependencies
        // (part B)

        motionSensor.addSensorObserver(system);
        heatSensor.addSensorObserver(system);
        // Add this security system as an observer to the Motion and Heat Sensors
        // (part A)
    }    
}
