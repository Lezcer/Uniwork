public class Kernel
{
        /*
         * HORIZONTAL - A kernel that detects horizontal lines.
         */
    public static final int[][] HORIZONTAL =
        {
                { 1,  1,  1},
                { 0,  0,  0},
                {-1, -1, -1}
        }; 
        /*
         * VERTICAL - A kernel that detects vertical lines.
         */
    public static final int[][] VERTICAL =
        {
                { 1,  0, -1},
                { 1,  0, -1},
                { 1,  0, -1}
        }; 

}
