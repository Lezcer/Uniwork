/**
 * DSA Final Assessment Question 4 - FA_HashTest.java
 *
 * Name : Salah Mahamod
 * ID   : 20152428
 *
 **/
import java.util.*;
public class FA_HashTest
{
	public static void main(String args[])
	{
		System.out.println("**** Question 4: Testing Hash Tables ****");
        int initialCapacity = 20;
		Hashtable<String, String> tab = new Hashtable<String, String>(initialCapacity);

		String[] data = {"11111112", "11111121", "11111211", "11112111", "11121111", "11211111", "12111111", "21111111"};

        for (int i=0; i < data.length; i++)
		{
			tab.put(data[i], "O"+data[i]);
		}		

		/*System.out.println("Table size is: " + tab.getArrayLength() );*/
        /**Unsupported operation, enumeration shows the 'occupied' elements*/
		
		tab.toString();
		
		System.out.println("Load Factor is: " + 0.75 );
        /**the getLoadFactor() method isn't implemented however 0.75 is the default
            value according to the java api*/
		
		System.out.println("**** Tests Complete ****");
	}
}
