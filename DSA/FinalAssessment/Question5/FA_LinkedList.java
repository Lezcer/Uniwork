/**
 * DSA Final Assessment Question 5 - FA_LinkedList.java                             4
 *
 * Name : 
 * ID   :
 *
 **/
import java.util.*;
import java.io.*;

public class FA_LinkedList implements Iterable, Serializable
{
    //class fields
    private FA_ListNode head;
    private FA_ListNode tail;
    private int size;

    public Iterator iterator()
    {
        return new LinkedListIterator(this);
    }

    //default constructor
    public FA_LinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public void insertLast(Object inValue)
    {
        FA_ListNode newNode = new FA_ListNode(inValue);

        if (isEmpty()) 
        {
            head = newNode;
        } 
        else
        {
            tail.setNext(newNode); 
            newNode.setPrev(tail); 
        }

        tail = newNode; 
        size++;
    }

    public boolean isEmpty()
    {
        return (head == null); 
    }

    public boolean contains(String label)
    {
        boolean contains = false;

        Iterator iter = this.iterator();

        while (iter.hasNext()) 
        {
            if (((FA_GraphVertex)iter.next()).getLabel().equals(label))
            {
                contains = true;
            }
        } 
        return contains;
    }

    public Object peekFirst()
    {
        Object nodeValue;

        if (isEmpty())
        {
            throw new IllegalStateException("List is empty");
        }
        else
        {
            nodeValue = head.getValue();
        }
        return nodeValue;
    }

    public Object peekLast()
    {
        Object nodeValue;

        if (isEmpty())
        {
            throw new IllegalStateException("List is empty");
        }
        else
        {
            nodeValue = tail.getValue();
        }
        return nodeValue;
    }

    public void printLinkedList()
    {
        FA_ListNode p, nextNode;

        p = head;

        if (p == null) 
        {
            System.out.println("\t\tNothing in the list.");
        }
        else
        {
            while (p != null)
            {
                nextNode = p.getNext();
                System.out.println("\t\t" + p.getValue().toString());
                p = nextNode;
            }
        }
    }

    public FA_ListNode getTail()
    {
        return this.tail;
    }

    public FA_ListNode getHead()
    {
        return this.head;
    }

    public int getSize()
    {
        return this.size;
    }

    private class LinkedListIterator implements Iterator
    {
        private FA_ListNode iterNext;

        public LinkedListIterator(FA_LinkedList theList)
        {
            iterNext = theList.head;
        }

        public boolean hasNext()
        {
            return (iterNext != null);
        }

        public Object next()
        {
            Object value;
            if (iterNext == null)
            {
                value = null;
            }
            else
            {
                value = iterNext.getValue(); 
                iterNext = iterNext.getNext();
            }
            return value;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Not supported");
        }
    }
}
