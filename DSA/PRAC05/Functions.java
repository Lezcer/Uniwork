import java.util.*;
public class Functions
{
    public static DSABinarySearchTree readCSV()
    {   
        Scanner sc = new Scanner(System.in);
        String filename;
        final int ROWS = 7000;  
        final int COLUMNS = 2;
        String[][] data = new String[ROWS][COLUMNS];
        DSABinarySearchTree tree = new DSABinarySearchTree();
        

        System.out.println("Please enter the file's name, including the extension.");
        filename = sc.nextLine();
        data = FileIO.readFile(filename);//Read File

        for(int i=0; i<ROWS; i++)
        { 
                tree.insert(data[i][0], data[i][1]);
        }//Insert the data
        System.out.println("Your File has been read and will now be saved.");
        System.out.println("Please DON'T Select option 1 again!");
        return tree;
    }

    public static DSABinarySearchTree readSer()
    {
        Scanner sc = new Scanner(System.in);
        String filename;

        System.out.println("Please enter the file's name, including the extension.");
        filename = sc.nextLine();
        DSABinarySearchTree tree = FileIO.load(filename);//Read File
        return tree;
    }

    public static void displayTree()
    {
    }

    public static void writeCSV(DSABinarySearchTree tree)
    {
        Scanner sc = new Scanner(System.in);
        int option;
        String e ="", filename;
        do
        {
            System.out.println(e+ "Would you like it to be:");
            System.out.print("1. inorder, 2. preorder OR 3. postorder traversal: ");
            e = "Please enter either 1, 2 or 3 to select, 0 to exit.\n";
            option = sc.nextInt();
        }
        while(option != 0);

        System.out.println("Please enter a name for the file you wish to create.");
        filename = sc.nextLine();

        switch(option)
        {
            case 1: 
            break;
            case 2: 
            break;
            case 3: 
            break;
            case 0: break;
        }
    }

    public static void writeSer(DSABinarySearchTree tree)
    {
        Scanner sc = new Scanner(System.in);
        String filename;

        System.out.println("Please enter a name for the file you wish to create.");
        filename = sc.nextLine(); 
        FileIO.save(tree, filename);
        System.out.println("File created!");
    }
}
