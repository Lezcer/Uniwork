import java.util.*;
import java.io.*;
public class DSABinarySearchTree implements Serializable
{
    private class DSATreeNode
    {
        private String key;
        private Object value;
        private DSATreeNode left;
        private DSATreeNode right;
        
        public DSATreeNode(String inKey, Object inValue)
        {
            if(inKey == null)
                throw new IllegalArgumentException("Key cannot be null");

            key = inKey;
            value = inValue; 
            left = null;
            right = null;
        }
        public String getKey()
        {
            return key;
        }
        public Object getValue()
        {
            return value;
        }
        public DSATreeNode getLeft()
        {
            return left;
        }
        public DSATreeNode getRight()
        {
            return right;
        }
        public void setLeft(DSATreeNode inLeft)
        {
            left = inLeft;
        }
        public void setRight(DSATreeNode inRight)
        {
            right = inRight;
        }
    }/*Private Inner Class DSA Tree Node*/

    private DSATreeNode root;/*DSABinarySearchTree Class Field*/

    //CONSTRUCTOR
    public DSABinarySearchTree()
    {
        root = null;
    }

    /*Public wrapper methods call their respective
    private recursive methods*/
    public Object find(String key)
    {
        return findRec(key, root);
    }
    public void insert(String key, Object value)
    {
        root = insertRec(key, value, root);
    }
    public void delete(String key)
    {
        root = deleteRec(key, root);
    }
    /*public void display()
    {
        displayRec();
    }*/
    public int height()    
    {
        return heightRec(root);
    }

    /*Private Recursive Methods*/
    private Object findRec(String key, DSATreeNode current)
    {
        Object value = null;
        if(current == null)
            throw new NoSuchElementException("Key " + key + " not found.");
        else if(key.equals(current.getKey()))
            value = current.getValue();
        else if(key.compareTo(current.getKey()) < 0)
            value = findRec(key, current.getLeft());
        else 
            value = findRec(key, current.getRight());
        return value;
    }
    private DSATreeNode insertRec(String key, Object value, DSATreeNode current)
    {
        DSATreeNode updateNode = current; 
        if(current == null)
        {
            DSATreeNode newNode = new DSATreeNode(key, value);
            updateNode = newNode;
        }
        else if(key.equals(current.getKey()))
        {
            throw new IllegalArgumentException("Key " + key + " already exists.");
        }
        else if(key.compareTo(current.getKey()) < 0)
        {
            current.setLeft(insertRec(key, value, current.getLeft()));
        }
        else
        { 
            current.setRight(insertRec(key, value, current.getRight()));
        }
        return updateNode;
    }
    private DSATreeNode deleteRec(String key, DSATreeNode current)
    {
        DSATreeNode updateNode = current;
        if (current == null)
            throw new NoSuchElementException("Key " + key + " not found.");
        else if(key.equals(current.getKey()))
            updateNode = deleteNode(key, current);
        else if(key.compareTo(current.getKey()) < 0)
            current.setLeft(deleteRec(key, current.getLeft()));
        else 
            current.setRight(deleteRec(key, current.getRight()));
        return updateNode;
        
    }
    private DSATreeNode deleteNode(String key, DSATreeNode deleted)
    {
        DSATreeNode updateNode = null;
        if ((deleted.getLeft() == null)&&(deleted.getRight() == null))
        {
            updateNode = null;
        }
        else if ((deleted.getLeft() != null)&&(deleted.getRight() == null))
        {
            updateNode = deleted.getLeft();
        }
        else if ((deleted.getLeft() == null)&&(deleted.getRight() != null))
        {
            updateNode = deleted.getRight();
        }
        else
        {
            updateNode = promoteSuccessor(deleted.getRight());
            if (updateNode != deleted.getRight())
                updateNode.setRight(deleted.getRight());
            updateNode.setLeft(deleted.getLeft());
        }
        return updateNode;
    }
    private DSATreeNode promoteSuccessor(DSATreeNode current)
    {
        //Successor will be the left most child of the right subtree
        DSATreeNode successor = current;
        if (current.getLeft() != null)
        {
            successor = promoteSuccessor(current.getLeft());
            if (successor == current.getLeft()) 
                current.setLeft(successor.getRight());
        }
        return successor;
    }
    private int heightRec(DSATreeNode current)    
    {
        int heightSoFar, lbranch, rbranch;
        if(current == null)
            heightSoFar = -1;
        else
        {
            lbranch = heightRec(current.getLeft());
            rbranch = heightRec(current.getRight());
        
            if (lbranch > rbranch)
                heightSoFar = lbranch + 1;
            else
                heightSoFar = rbranch + 1;
        }
        return heightSoFar;
    }
    public String min()
    {
        return minRec(root);
    }
    public String minRec(DSATreeNode current)
    {
        String minKey;
        if (current.getLeft() != null)
            minKey = minRec(current.getLeft());
        else
            minKey = current.getKey();
        return minKey;
    }
    public String max()
    {
        return maxRec(root);
    }
    public String maxRec(DSATreeNode current)
    {
        String maxKey;
        if (current.getRight() != null)
            maxKey = maxRec(current.getRight());
        else
            maxKey = current.getKey();
        return maxKey;
    }
    public boolean isBalanced()
    {
        return isBalancedRec(root);
    }
    public boolean isBalancedRec(DSATreeNode current)
    {
        int lh, lr;
        boolean isB;

        if(current == null)
            isB = true;
        else
        {
            lh = heightRec(current.getLeft());
            lr = heightRec(current.getRight());
            if(Math.abs(lh -lr) <= 1 
                && isBalancedRec(current.getLeft())
                && isBalancedRec(current.getRight()))
                isB = true;
            else
                isB = false;
        }   
        return isB;
    }
    /*private void printPost()
    {
        printPostRec(root);
    }
    private void printPostRec(DSATreeNode current)
    {
        FileIO.writeFile("post-ordered.txt", 
            printPostRec(current.getLeft()) +
            printPostRec(current.getLeft()) +
            System.out.print(current.getKey() + "\n")
                                            );
    }
    private void printIn()
    {
        printInRec(root);
    }
    private void printInRec(DSATreeNode current)
    {
        FileIO.writeFile("in-ordered.txt", 
            printInRec(current.getLeft()) +
            System.out.print(current.getKey() + "\n") +
            printInRec(current.getLeft())
                                            );
    }
    private void printPre()
    {
        printPreRec(root);
    }
    private void printPreRec(DSATreeNode current)
    {
        FileIO.writeFile("pre-ordered.txt", 
            System.out.print(current.getKey() + "\n") +
            printPreRec(current.getLeft()) +
            printPreRec(current.getLeft())
                                            );
    }
    private void displayRec()
    {
    }
    private void traverseTree()
    {
        traverseTreeRec(root);
    }
    private void traverseTreeRec(DSATreeNode current)
    {
        if(current != null)
        {
        }
    }*/
}
