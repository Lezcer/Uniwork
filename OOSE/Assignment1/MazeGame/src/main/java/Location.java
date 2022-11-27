package edu.curtin.assignment;

import java.io.*;
import java.util.*;

/**
 * Contains Location object
 * @author Salah
 */
public class Location 
{
    /*Location Fields*/
    protected int[] coordinates = new int[2]; 
    protected ArrayList<Integer> keys = new ArrayList<Integer>();
    protected String message = "";
    protected ArrayList<Integer> verticalDoors = new ArrayList<Integer>();
    protected ArrayList<Integer> horizontalDoors = new ArrayList<Integer>();
    boolean hasVerticalWall = false;
    boolean hasHorizontalWall = false;

    /*Constructor*/
    public Location(int x, int y)
    {
        coordinates[0] = x;
        coordinates[1] = y;
    }

    /*Mutators*/
    public void setWall(int placement)
    {
        /* If placement is 0 then it is vertical*/
        if(placement == 0)
        {
            hasVerticalWall = true;
        }
        /* Otherwise (in this case placement will be 1, then the wall is horizontal)*/
        else if(placement == 1)
        {
            hasHorizontalWall = true;
        }
    }
    public void setDoor(int placement, int type)
    {
        /* If placement is 0 then it is vertical*/
        if(placement == 0 && !hasVerticalWall)
        {
            if(verticalDoors.lastIndexOf(type) == -1)
            {
                verticalDoors.add(type);
            }
            // If the door of same colour exists then don't add
        }
        /* Otherwise (in this case placement will be 1, then the door is horizontal)*/
        else if(placement == 1 && !hasHorizontalWall)
        {
            if(horizontalDoors.lastIndexOf(type) == -1)
            {
                horizontalDoors.add(type);
            }
            // If the door of same colour exists then don't add
        }
    }
    public void setMessage(String message)
    {
        this.message = message + '\n';
    }
    public void setKey(int type)
    {
        keys.add(type);
    }
    public void getKeys(ArrayList<Integer> keys)
    {
        for(int i=0; i< this.keys.size(); i++)
        {
            keys.add(this.keys.remove(i));
        }
        this.keys.removeAll(keys);
    }

    /*Accessors*/
    public int verticalBarrier()
    {
        int no = 0;
        if(hasVerticalWall)
        {
            no = -1;
        }
        else if(verticalDoors.size() > 0)
        {
            no = verticalDoors.size();
        }
        return no;
    }
    //Returns -1 if there's a wall north, 0 if there is no barrier at all north, or a positive number indicating the number of doors north
    public int horizontalBarrier()
    {
        int no = 0;
        if(hasHorizontalWall)
        {
            no = -1;
        }
        else if(horizontalDoors.size() > 0)
        {
            no = horizontalDoors.size();
        }
        return no;
    }
    //Returns -1 if there's a wall west, 0 if there is no barrier at all west, or a positive number indicating the number of doors west    
    public boolean hasKey()
    {
        boolean value = false;
        if(keys.size() >  0)
        {
            value = true;
        }
        return value;
    }
    //Returns true if the location has a key
    public int getVerticalDoorColour()
    {
        int type = 0;
        if(verticalDoors.size() > 0)
        {
            type = verticalDoors.get(0);
        }
        return type;
    }
    //Returns the colour of the first vertical door in the list
    public int getHorizontalDoorColour()
    {
        int type = 0;
        if(horizontalDoors.size() > 0)
        {
            type = horizontalDoors.get(0);
        }
        return type;
    }
    //Returns the colour of the first horizontal door in the list
    public int getKeyColour()
    {
        int type = 0;
        if(this.hasKey())
        {
            type = keys.get(0);
        }
        return type;
    }
    //Returns the colour of the first key in the list
    public int[] getCoordinates()
    {
        return coordinates;
    }
    //Returns the coordinates of the location
    public String getMessage()
    {
        String message = this.message;
        this.message = "";
        return message;
    }
    //Rerurns the message in the location indented with newline character
    public ArrayList<Integer> getHorizontalDoors()
    {
        return horizontalDoors;
    }
    public ArrayList<Integer> getVerticalDoors()
    {
        return verticalDoors;
    }
}
