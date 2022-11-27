package edu.curtin.assignment;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Handles: Reading of input file, storing maze game data and displaying the game
 * @author Salah
 */
public class FileIO 
{
    private static Map<Character, StoreData> dataStorers = new HashMap<>();
    private static Scanner input = new Scanner(System.in);
    private static final java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(FileIO.class.getName());

    public static Maze readFile(String filename) throws IOException, FileNotFoundException
    {
        Maze maze = new Maze();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            // Read First line as it must have the dimensions for the Maze
            String line = reader.readLine();
            String[] dimensions = line.split(" ");

            maze = new Maze(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
            /* Parse parts[0] and [1] into integers and create a Maze object*/

            line = reader.readLine();
            // read next line, a valid file could have only the dimensions

            initStorers();
            // The Strategy Pattern is deployed!

            while(line != null)
            {
                String[] parts = line.split(" ", 4);
                // Split the line into a maximum of 4 separate strings

                StoreData storeInfo = dataStorers.get(parts[0].charAt(0));
                storeInfo.addInfo(maze, parts);
                // Strategy pattern used here: Besides the first line specifying the dimensions 
                // of the Maze, These two lines of code will store the; Starting location, Endi
                // -ng location, Locations that may contain keys, messages or barriers (Doors 
                // and walls, vertical and horizontal).

                line = reader.readLine();
                // Read next line for the loop, if it is null then exit loop
            }
        }
        catch(NumberFormatException e)
        {
            System.out.println("Error while parsing the first line of the file. " + e);
            /**
            * LOGGING: FATAL Warning. Error while parsing first line. Maze dimensions?
            */
            logger.severe("FATAL Warning. Error while parsing first line. Maze dimensions?");
        }
        return maze;
    }
    
    private static void initStorers()
    {
        dataStorers.put('S', new StartData());
        dataStorers.put('E', new EndData());
        dataStorers.put('K', new KeyData());
        dataStorers.put('W', new WallData());
        dataStorers.put('D', new DoorData());
        dataStorers.put('M', new MessageData());
        // Creates instances of the DataStorers and stores them in a hashmap where they can be accessed
        // by the first letter of the line in InputFile.txt
    } 
    
    public static int[] makeMove(int[] current)
    {
        int[] temp = new int[2];
        temp[0] = current[0];
        temp[1] = current[1];

        String message = "> Make a move: ";
        System.out.println(message);
        
        char move = input.next().charAt(0);
        while(move != 'n' && move != 'N' && move != 'w' && move != 'W' && move != 'e' && move != 'E' && move != 's' && move != 'S')
        {
            System.out.println("Invalid move! \n" + message);
            move = input.next().charAt(0);
        }
        
        switch(move)
        {
            case 'n': case 'N':
                temp[0]--;
            break;

            case 'e': case 'E':
                temp[1]++;
            break;

            case 's': case 'S':
                temp[0]++;
            break;

            case 'w': case 'W':
                temp[1]--;
            break;
        }

        return temp;
    }

    public static String promptUser(String message)
    {
        String str;
        System.out.print(message);
        str = input.nextLine();
        return str;
    }

    public static char movePrompt(String message)
    {
        char character = '\0';
        System.out.print(message);
        
        character = input.nextLine().charAt(0);
        while(character != 'n' || character != 'N' || character != 'w' || character != 'W' || character != 'e' || character != 'E' || character != 's' || character != 'S')
        {
            System.out.println("Invalid input! \n" + message);
            character = input.next().charAt(0);
        }
        
        return character;
    }

}
