public class 3BasicStateImplementation
{
    private static final int INNER_DOOR_OPEN      = 1;
    private static final int SEALED               = 2;
    private static final int SEALED_DEPRESSURISED = 3;
    private static final int OUTER_DOOR_OPEN      = 4;

    private static int state;

    private static double currentPressure;

    private static Door inner;
    private static Door outer;

    public static init()
    {
        state = INNER_DOOR_OPEN;
        // At start the inner door is open
        currentPressure = 101.3;
        // At start the airlock is at standard pressure
        inner = new Door();
        outer = new Door();
    }

    public static void depressurise()
    {
        switch(state)
        {
            case INNER_DOOR_OPEN:
                Pump.beginExtraction();
                if(currentPressure < 90)
                {
                    state = SEALED_DEPRESSURISED;
                }
                else
                {
                    state = SEALED;
                }
            break;
            case SEALED:
                Pump.beginExtraction();
                state = SEALED_DEPRESSURISED;
                if(currentPressure < 90)
                {
                    state = SEALED_DEPRESSURISED;
                }
                else
                {
                    state = SEALED;
                }
            break;
        }
    }

    public static void pressurise()
    {
        switch(state)
        {
            case OUTER_DOOR_OPEN:
                Pump.beginReturn();
                if(currentPressure < 90)
                {
                    state = SEALED_DEPRESSURISED;
                }
                else
                {
                    state = SEALED;
                }
            break;
            case SEALED_DEPRESSURISED:
                Pump.beginReturn();
                state = SEALED_DEPRESSURISED;
                if(currentPressure < 90)
                {
                    state = SEALED_DEPRESSURISED;
                }
                else
                {
                    state = SEALED;
                }
            break;
        }
    }

    public static void openOuterDoor()
    {
        if(state == SEALED)
        {
            outer.open();
        }// Ignore for all other states
    }

    public static void openInnerDoor()
    {
        if(state == SEALED)
        {
            inner.open();
        }// Ignore for all other states
    }

    public static void updatePressure(double pressure)
    {
        currentPressure = pressure;
    }
}