/**
 * DSA Final Assessment Question 1 - FA_BinarySearchTree.java
 *
 * Name Salah Mahamod: 
 * ID   20152428:
 *
 **/

public class FA_BinarySearchTree {   
	// Inner class FA_TreeNode
	private class FA_TreeNode {
		public int value;
		public FA_TreeNode left;
		public FA_TreeNode right;
		
		public FA_TreeNode(int inVal)
		{
			value = inVal;
			left = null;
			right = null;
		}
	}
	// Class FA_BinarySearchTree
	private FA_TreeNode root;
	
	public FA_BinarySearchTree()
	{
		root = null;
	}
	
	public void insert(int val)
	{
		if (isEmpty())
		{
			root = new FA_TreeNode(val);
		}
		else
		{
			root = insertRec(val, root);
		}
	}

	public Boolean isEmpty()
	{
		return root == null;
	}

	private FA_TreeNode insertRec(int inVal, FA_TreeNode cur)
	{
		if (cur == null)
		{
			cur = new FA_TreeNode(inVal);
		}
		else
		{
			if (inVal < cur.value)
			{
				cur.left = insertRec(inVal, cur.left);
			}
			else	
			{
				cur.right = insertRec(inVal, cur.right);
			}
		}
		return cur;
	}
	public void printEvenValues()
    {
    }
    public void printEvenLevels()
    {
    }
  
}
