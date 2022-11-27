package edu.curtin.maze;
import java.util.*;

/**
 * Contains key information about the player: their current maze position, the number and type of
 * keys obtained, and whether they've reached the end.
 *
 * (If the player obtains several keys of the same type, we do record that information here, 
 * though purely for aesthetic reasons, as it has no effect on the game.)
 */
public class Player
{
    private Location loc;
    private boolean won = false;
    private Map<Key,Integer> keyCount = new HashMap<>();
    
    public Player(Location loc)
    {
        this.loc = loc;
    }
    
    public Location getLocation() { return loc; }
    public void setLocation(Location loc) { this.loc = loc; }
    
    public void win()
    {
        won = true;
    }
    
    public boolean hasWon() 
    { 
        return won; 
    }
    
    public void putKey(Key key)
    {
        keyCount.put(key, keyCount.getOrDefault(key, 0) + 1);
    }
    
    public boolean hasKey(Key key)
    {
        return keyCount.containsKey(key);
    }
    
    public Map<Key,Integer> getKeys()
    {
        return Collections.unmodifiableMap(keyCount);
    }
}
