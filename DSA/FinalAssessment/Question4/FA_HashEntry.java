/**
 * DSA Final Assessment Question 4 - FA_HashEntry.java
 *
 * Name : Salah Mahamod 
 * ID   : 20152428
 *
 **/

public class FA_HashEntry
{

    //class fields
    private String key;
    private Object value; 
    private int state; //0 = never used/free,  1 = used / not free

    //default constructor
    public FA_HashEntry() {

        key = "";
        value = null;
        state = 0;
    }

    //alternate constructor
    public FA_HashEntry(String inKey, Object inValue) 
	{

        key = inKey;
        value = inValue;
        state = 1;
    }

    public String getKey() 
	{

        return this.key;
    }

    public Object getValue() 
	{

        return this.value;
    }


    public int getState() 
	{

        return this.state;
    }

    public void setKey(String inKey) {

        this.key = inKey;
    }

    public void setValue(Object inValue) {

        this.value = inValue;
    }

    public void setState() {

        this.state = -1;
    }
}
