import java.util.*;
public class LinkedListTestharness
{
    public static void main(String args[])
    {
        DSALinkedList list = new DSALinkedList();//Default constructor test
        final int LENGTH = 15;
        final int MAX_VAL = 29;
        Random rand = new Random();

        System.out.println("The List is created and is now empty. This is: " + list.isEmpty());
        
        for(int i=0;i<LENGTH;i++)
        {   
            int num = rand.nextInt(MAX_VAL);
            list.insertFirst((Integer) num);
        }//added integers to the list

        System.out.println("The list now contains " + LENGTH + " random integers.");

        System.out.println();
        
        System.out.println("The list is shown below: ");
        list.displayList();
        
    }
}
