import java.util.*;
public class DSALinkedList implements Iterable
{
    DSAListNode head, tail;
    public DSALinkedList()
    {
        head = null;
        tail = null;
    }//DEFAULT CONSTRUCTOR
    //MUTATORS
    public void insertFirst(Object inValue)
    {
        DSAListNode newNode = new DSAListNode(inValue);
        if(isEmpty())
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }
    public void insertLast(Object inValue)
    {
        DSAListNode newNode = new DSAListNode(inValue);
        if(isEmpty())
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
    }
    //ACESSORS
    public Object peekFirst()
    {
        Object nodeValue = null;
        if(isEmpty())
        {
            throw new NoSuchElementException ("List is Empty!");
        }
        else
        {
            nodeValue = head.value;
        }
        return nodeValue;
    }
    public Object peekLast()
    {
        Object nodeValue = null;
        if(isEmpty())
        {
            throw new NoSuchElementException ("List is Empty!");
        }
        else
        {
            nodeValue = tail.value;
        }
        return nodeValue;
    }
    public boolean isEmpty()
    {
        return (head == null);
    }
    //MUTATOR
    public Object removeFirst()
    {
        Object nodeVal = null;
        nodeVal = peekFirst();
        head = head.next;
        return nodeVal;
    }
    public Object removeLast()
    {
        Object nodeVal = null;
        nodeVal = peekLast();
        tail = tail.prev;
        return nodeVal;
    }
    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }
    public void displayList()
    {
        Iterator iter = this.iterator();

        System.out.print("[");
        while(iter.hasNext())
        {
            System.out.print(iter.next()+ ", ");
        }
        System.out.print("]\n");
    }
    public Object[] easterEgg()
    {
        Iterator iter = this.iterator();
        Object[] list;
        int length = 0;

        for(Object item : this)
        {
            length++;
        }

        list = new Object[length];
        int i = 0;

        for(Object value : this)
        {
            list[i] = value;
            i++;
        }
        return list;
    }






    /*Private Inner Class DSAListNode*/    
    private class DSAListNode
    {
        public Object value;
        public DSAListNode next;
        public DSAListNode prev;

        public DSAListNode(Object inValue)
        {
            value = inValue;
            next = null;
            prev = null;
        }//Alternate Constructor for DSAListNode
        public void setValue(Object inValue)
        {
            value = inValue;
        }
        public void setNext(DSAListNode inNext)
        {
            next = inNext;
        }
        public void setPrev(DSAListNode inPrev)
        {
            prev = inPrev;
        }
    }
    /*Private Inner Class DSALinkedListIterator*/
    private class DSALinkedListIterator implements Iterator
    {
        private DSAListNode iterNext;
        public DSALinkedListIterator(DSALinkedList list)
        {
            iterNext = list.head;
        }//Alternate Constructor for DSALinkedListIterator
        public boolean hasNext()
        {
            return (iterNext != null);
        }
        public Object next()
        {
            Object value;
            if(iterNext == null)
            {
                value = null;
            }
            else
            {
                value = iterNext.value;
                iterNext = iterNext.next;
            }
            return value;
        }
        public void remove()
        {
            throw new UnsupportedOperationException ("Not Supported;");
        }
    }
}
