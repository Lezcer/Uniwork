package edu.curtin.maze;
import edu.curtin.maze.move.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * Controls the overall maze game, taking user input and coordinating the Maze (and Square), 
 * Player, Displayer and Move objects.
 */
public class GameEngine
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(GameEngine.class.getName());
    private Map<Character,Move> moves = new LinkedHashMap<>();
        
    public GameEngine()
    {
        // The set of possible moves are defined by these objects, which also specify the keystroke 
        // used to invoke them.
        for(Move m : List.of(new NorthMove(), new SouthMove(), new EastMove(), new WestMove()))
        {
            moves.put(m.getInputChar(), m);
        }
    }

    public void run(Maze maze, Player player, Displayer displayer)
    {
        boolean exit = false;        
        Scanner sc = new Scanner(System.in); // NOPMD       
        List<String> messages = new ArrayList<>();        
        
        while(!exit)
        {
            // The player 'arrives' at the current grid square. Invoke the grid square's moveTo() 
            // method to perform any appropriate actions, and accumulate and then display any 
            // resulting messages.
            logger.info(() -> "Player arrived at " + player.getLocation());
            maze.get(player.getLocation()).moveTo(player, messages);            
            displayer.display();
            for(String msg : messages)
            {
                System.out.println(msg);
            }            
            System.out.println();
            messages.clear();
            
            if(player.hasWon())
            {
                logger.info("Game won; exiting");
                exit = true;
            }
            else
            {
                // If the game is to continue, ask the user for a move, and then invoke the 
                // relevant Move object to (attempt to) make it.
                for(Move m : moves.values())
                {
                    System.out.print(m.getPrompt() + ", ");
                }
                System.out.print("or (q)uit? ");
                char ch = sc.next().charAt(0);
                sc.nextLine();
                
                Move move = moves.get(ch);
                if(move != null)
                {
                    logger.info(() -> "Executing move: " + move.getClass().getName());
                    move.perform(player, maze, messages);
                }
                else if(ch == 'q')
                {
                    exit = true;
                }
            }
        }
    }
}
