import java.util.*;
import java.lang.*;
public class HashTable
{
    HashEntry[] hashArray;
    int count;

    public HashTable(int inSize)
    {
        int size = findNextPrime(inSize);
        hashArray = new HashEntry[size];
        for(int i=0; i<size-1; i++)
        {
            hashArray[i] = new HashEntry();
        }
    }
    public Object get(String inKey)
    {
        int hashIdx = hash(key);
        int origIdx = hashIdx;
        boolean found = false, giveUp = false;

        while(!found && !giveUP)
        {
            if(hashArray[hashIdx].state = 0)
            {
                giveUp = true;
            }
            else if(hashArray[hashIdx].key = inKey)
            {
                found = true;
            }
            else
            {
                hashIdx = (hashIdx + 1) % hashArray.length
                if(hashIdx = origIdx)
                {
                    giveUp = true;
                }
            }
        }
        if(!found)
        {
            
        }
        return hashArray[hashIdx].value;
    }
    public void put(String inKey, Object inValue)
    {
        int hashIdx = hash(inKey);
        hashArray[hashIdx] = new HashEntry(inKey, inValue);
    }
    private boolean hasIndex(int index)
    {
        boolean exists = false;
        if(hashArray[index].state = 1)
        {
            exists = true;
        }
        return exists;
    }
    private int hash(String inKey)
    {
        int hashIdx = 0;
        for(int i=0; i<key.length(); i++)
        {
            hashIdx = (31 * hashIdx) + key.charAt(i);
        }
        int index = hashIdx % hashArray.length;
        if(hasIndex(index))
        {
            index = stepHash(index);
        }//If there's a collision, rehash
        return index;
    }
    private int stepHash(int inKey)
    {
        int hashStep = MAX_STEP - (inKey % MAX_STEP);
        return hashStep;
    }
    public void reSize(int size)
    {
        HashEntry[] temp = this.hashArray;
        int newSize = findNextPrime(size);
        int oldSize = temp.length;
        hashArray = new HashEntry[newSize];
        HashEntry entry;

        for(int i=0; i<newSize-1; i++)
        {
            hashArray[i] = new HashEntry();
        }//Default constructor - set the state to unusued
        
        for(int i=0; i<oldSize-1; i++)
        {
            entry = temp[i];
            if(entry.state == 1)//Check if it is used
            {
                put(entry.key, entry.value);
            }
        }//re-hash used entries
    }
    
    /*public void remove(String inKey)
    {
        int hashIdx = hash(inKey);
        hashArray[hashIdx] = new HashEntry();
    }*/
    private int findNextPrime(int start)
    {
        int prime;
        if(start % 2 == 0)
        {
            prime = start - 1;
        }
        else
        {
            prime = start;
        }

        boolean isPrime = false;
        do
        {
            prime = prime + 2;
            int ii = 3;
            isPrime = true;
            int root = Math.sqrt(prime);

            do
            {
                if(prime % ii == 0)
                {
                    isPrime = false;
                }
                else
                {
                    ii = ii + 2;
                }
            }
            while(ii<=root && isPrime);
        }
        while(!isPrime);
    }


    private class HashEntry
    {
        private String key;
        private Object value;
        private int state;
    
        private HashEntry()
        {
            key = "";
            value = null;
            state = 0;
        }
    
        private HashEnrty(String inKey, Object inValue)
        {
            key = inKey;
            value = inValue;
            state = 1;
        }
        
    }
}
