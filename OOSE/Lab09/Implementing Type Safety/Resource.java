package edu.curtin.spaceprobe;

public interface Resource<R>
{
    void useUp(R amount);
    R getRemaining();
    long getTime(long elapsedTime);
}
