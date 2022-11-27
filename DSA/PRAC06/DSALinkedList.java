import java.util.*;
import java.lang.*;
public class DSALinkedList implements Iterable//Private Inner Class
{
    /*Private Inner Class DSAListNode*/
    public class DSAListNode//Private Inner Class
    {
        public DSAGraphNode vertex;
        public DSAListNode next;
        public DSAListNode prev;

        public DSAListNode(DSAGraphNode inVertex)
        {
            vertex = inVertex;
            next = null;
            prev = null;
        }//Alternate Constructor for DSAListNode
        public void setVertex(DSAGraphNode inVertex)
        {
            vertex = inVertex;
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

    /*Class Fields*/
    DSAListNode head, tail;
    public DSALinkedList()
    {
        head = null;
        tail = null;
    }//DEFAULT CONSTRUCTOR
    //MUTATORS
    public void insertFirst(DSAGraphNode inVertex)
    {
        DSAListNode newNode = new DSAListNode(inVertex);
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
    public void insertLast(DSAGraphNode inVertex)
    {
        DSAListNode newNode = new DSAListNode(inVertex);
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
    public DSAGraphNode peekFirst()
    {
        DSAGraphNode nodeVertex;
        if(isEmpty())
        {
            throw new NoSuchElementException ("List is Empty!");
        }
        else
        {
            nodeVertex = head.vertex;
        }
        return nodeVertex;
    }
    public DSAGraphNode peekLast()
    {
        DSAGraphNode nodeVertex;
        if(isEmpty())
        {
            throw new NoSuchElementException ("List is Empty!");
        }
        else
        {
            nodeVertex = tail.vertex;
        }
        return nodeVertex;
    }
    public boolean isEmpty()
    {
        return (head == null);
    }
    //MUTATOR
    public DSAGraphNode removeFirst()
    {
        DSAGraphNode nodeVer;
        nodeVer = peekFirst();
        head = head.next;
        return nodeVer;
    }
    public DSAGraphNode removeLast()
    {
        DSAGraphNode nodeVer;
        nodeVer = peekLast();
        tail = tail.prev;
        return nodeVer;
    }
    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }
    public String exportList()
    {
        String toS = "";
        Iterator iter = this.iterator();
        DSAGraphNode temp;

        while(iter.hasNext())
        {
            temp = (DSAGraphNode)iter.next();
            toS += temp.label + ", ";
        }
        System.out.print("\n");
        return toS;
    }

    /*Private Inner Class DSALinkedListIterator*/
    public class DSALinkedListIterator implements Iterator
    {
        public DSAListNode iterNext;
        public DSALinkedListIterator(DSALinkedList list)
        {
            iterNext = list.head;
        }//Alternate Constructor for DSALinkedListIterator
        public boolean hasNext()
        {
            return (iterNext != null);
        }
        public DSAGraphNode next()
        {
            DSAGraphNode vertex;
            if(iterNext == null)
            {
                vertex = null;
            }
            else
            {
                vertex = iterNext.vertex;
                iterNext = iterNext.next;
            }
            return vertex;
        }
        public void remove()
        {
            throw new UnsupportedOperationException("Not Supported;");
        }
    }
}
