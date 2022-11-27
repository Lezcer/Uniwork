/**
 * DSA Final Assessment Question 5 - FA_ListNode.java                             4
 *
 * Name : 
 * ID   :
 *
 **/
import java.io.*;

public class FA_ListNode implements Serializable
{
    private Object value;
    private FA_ListNode next;
    private FA_ListNode prev;

    public FA_ListNode(Object inValue)
    {
        value = inValue;
        next = null;
        prev = null;
    }

    public void setValue(Object inValue)
    {
        this.value = inValue;
    }

    public void setNext(FA_ListNode newNext)
    {
        this.next = newNext;
    }

    public void setPrev(FA_ListNode newPrev)
    {
        this.prev = newPrev;
    }

    public Object getValue()
    {
        return this.value;
    }

    public FA_ListNode getNext()
    {
        return this.next;
    }

    public FA_ListNode getPrev()
    {
        return this.prev;
    }

    public String toString()
    {
        String nodeStr;
        nodeStr = value.toString();

        return nodeStr;
    }
}