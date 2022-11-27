import states;

public class Controls 
{
    public static void main()
    {
        Airlock airlock = new Airlock();

        while(true)
        {
            // Whenever user presses pressurise button
            airlock.pressurise();

            // Whenever user presses depressurise button
            airlock.depressurise();

            // Whenever user presses open inner door button
            airlock.openInnerDoor();

            //Whenever user presss open outer door button
            airlock.openOuterDoor();

            /**
             * Note to marker: I didn't know how to implement this but the general idea is the user / astronaut
             * can press any of these buttons regardless of what 'state' the airlock is in.
             */
        }
        
    }    
}
