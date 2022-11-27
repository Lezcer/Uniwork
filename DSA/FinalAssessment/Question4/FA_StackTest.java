/**
 * DSA Final Assessment Question 4 - FA_StackTest.java                             4
 *
 * Name : Salah Mahamod 
 * ID   : 20152428
 *
 **/

import java.util.*;
public class FA_StackTest
{
    public static void main( String[] args ) 
    {
        // Test Variables
        int stackSize = 50;
        int testsPassed = 0;
        int numTests = 0; 
        Stack<Integer> testStack = null;
        Object stackElement;

        System.out.println("**** Question $: Testing Built-in Stacks ****");

        try
        {
            numTests++;
            System.out.print( "Test 1 - stack created             : " );
            testStack = new Stack<Integer>();

            /*if ( testStack.elementCount != 0 )
            {
                throw new PracExamException( "Error: elementCount should be 0" );
            }*/
            /**Built in ADT Stack doesn't have an getCount() method*/

            if ( !testStack.empty() )
            {
                throw new PracExamException( "Error: empty should be true" );
            }

            /*if ( testStack.isFull() )
            {
                throw new PracExamException( "Error: isFull should be false" );
            }*/
            /**Built in ADT Stack doesn't have an isFull() method*/

            testsPassed++;
            System.out.println( "Passed" );
        }
        catch(PracExamException e)
        {
            System.out.println( "Failed" + e.getMessage() );
        } 

        try
        {
            numTests++;

            System.out.print( "Test 2 - stack created set size    : " );
            /*testStack = new Stack(stackSize);*/
            /**Built in ADT Stack doesn't have an alternate constructor*/
            testStack = new Stack<Integer>();

            /*if ( testStack.elementCount != 0 )
            {
                throw new PracExamException( "Error: getCount should be 0" );
            }*/
            /**Built in ADT Stack doesn't have an getCount() method*/

            if ( !testStack.empty() )
            {
                throw new PracExamException( "Error: empty should be true" );
            }

            /*if ( testStack.isFull() )
            {
                throw new PracExamException( "Error: isFull should be false" );
            }*/
            /**Built in ADT Stack doesn't have an isFull() method*/

            testsPassed++;
            System.out.println( "Passed" );
        }
        catch(PracExamException e)
        {
            System.out.println( "Failed" + e.getMessage() );
        } 

        try 
        {
            numTests++;

            System.out.print( "Test 3 - pushing values     (10,20,30)       : " );

            testStack.push( 10 );
            testStack.push( 20 );
            testStack.push( 30 );

            if ( !testStack.peek().equals(30) )
            {
                throw new PracExamException( 
                        "peek() returned incorrect element" );
            }
            testsPassed++;
            System.out.println( "Passed" );
		}
        catch ( PracExamException e ) {
            System.out.println( "Test " + numTests + " Failed: "+ e.getMessage() );
        }

        /*try 
        {
            numTests++;

            System.out.print( "Test <REMOVED> - pushing values to limit   : " );

            for ( int ii = 3; ii <= testStack.size(); ii++ )
            {
                testStack.push( ii );
            }

            testStack.push( 100 );
            System.out.println( "Test " + numTests + " Failed: push() didn't throw exception" );

        } 
        catch( Exception e )
        {
                testsPassed++;
                System.out.println( "Passed" );
        }*/
        /**Built in Stack ADT doesn't have a 'max' capacity so you can't push to the
            limit*/

        try 
        {
            numTests++;
            System.out.print( "Test 4 - popping values            : " );

            testStack.pop();
            if ( testStack.empty() )
            {
                throw new IllegalArgumentException( 
                    "pop() didn't remove value" );
            }
            testsPassed++;
            System.out.println( "Passed" );
            }
        catch( Exception e )
        {
             System.out.println( "Test " + numTests + " Failed: push() didn't throw exception" );
        }
        
        


        System.out.println( "------ FA Stack Test Harness ------" );
        System.out.println( "Number of Tests:        " + numTests );
        System.out.println( "Number of Tests Passed: " + testsPassed );
        System.out.println( "              % Passed: " + 100*testsPassed/numTests + "%");
        System.out.println("**** Tests Complete ****");


    }

}
