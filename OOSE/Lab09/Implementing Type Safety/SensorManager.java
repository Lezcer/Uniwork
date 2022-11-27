package edu.curtin.spaceprobe;

import java.util.*;

public class SensorManager implements Resource<Set<Sensor>>
{
    private Set<Sensor> workingSensors;
    private int nSensors;

    public SensorManager(Set<Sensor> sensors)
    {
        workingSensors = new HashSet<>();
        workingSensors.addAll(sensors);
        nSensors = sensors.size();
    }

    @Override
    public void useUp(Set<Sensor> amount)
    {
        for(Sensor sensor : amount)
        {
            workingSensors.remove(sensor);
        }
    }

    @Override
    public Set<Sensor> getRemaining()
    {
        return Collections.unmodifiableSet(workingSensors);
    }

    @Override
    public long getTime(long elapsedTime)
    {
        double nWorking = (double)workingSensors.size();
        return (long)((double)elapsedTime / ((double)nSensors - nWorking) * nWorking);
    }
}
