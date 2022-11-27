public class Sensor 
{
    double currentPressure;
    Airlock airlock;

    public Sensor(Airlock airlock, double pressure)
    {
        this.airlock = airlock;
        currentPressure = pressure;
    }

    public double getPressure()
    {
        airlock.updatePressure(currentPressure);
        return airlock.currentPressure;
    }

    /**
     * Call updatePressure() every second (from its own thread)
     * Note to marker: I don't know what you mean by this?
     * 
     * while("second pass AND system is up")
     * {
     *      updatePressure(currentPressure);
     * }
     * 
     * this?
     */
}
