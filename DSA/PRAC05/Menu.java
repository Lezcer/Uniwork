import java.util.*;
public class Menu
{
    //This class contains the menu and calls functions for each task.
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int option;
        DSABinarySearchTree tree = null;
        String e = "";

        do
        {
            System.out.println("Worksheet 5");
            System.out.println("1. read CSV");
            System.out.println("2. read serialized file");
            System.out.println("3. display the tree");
            System.out.println("4. write CSV file");
            System.out.println("5. write a serialized file");
            System.out.print("0. exit: ");
            option = sc.nextInt();

            switch(option)
            {
                case 1: 
                    tree = Functions.readCSV();
                break;
                case 2: 
                    tree = Functions.readSer(); 
                break;
                /*case 3:     
                    Functions.displayTree(tree); 
                break;*/
                case 4: 
                    Functions.writeCSV(tree); 
                break;
                case 5: 
                    Functions.writeSer(tree); 
                break;
                case 0: 
                    System.out.println("Bye."); 
                break;
                default:
                    System.out.print("The Menu is repeated because you haven't entered a valid option\n");
                break;
            }//This will allow the menu to repeat itself
        }
        while(option != 0);
    }
}
