
/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * Define a binary node of a binary tree.  A node has a reference to a left and right child (null if a child doesn't 
 * exist).  A node also has data (of type char) and a point specifying where the center of the node exists in an 
 * in-order graphical representation. 
 * 
 * @author Rob Bauer
 * @version 1.0  2005-OCT-21
 */
public class BinaryNode 
{
	
	/**
	 * reference to the left node (null if it doesn't exist) 
	 */
	private BinaryNode leftNode;			

	/**
	 * reference to the right node (null if it doesn't exist) 
	 */
	private BinaryNode rightNode;
	
	/**
	 * Node's data 
	 */
	private char data;
	
	/**
	 * Point locating the node in a binary tree graphic  
	 */
	private Point center;
	
	
	/**
	 * Setup a node using the supplied character as the node's data.  Assign the children to null.
	 * 
	 * @param nodeData a character to be stored in the node
	 */
	public BinaryNode(char nodeData)
	{
		data = nodeData;
		
		// currently, the node doesn't have any children
		leftNode = null;
		rightNode = null;
		center = null;
	}  // end public BinaryNode()
	
	
	/**
	 * Insert data into node.  If the left child is null, insert a new node with the supplied data there.  Otherwise
	 * setup a new node as the right child.
	 * 
	 * @param insertValue data to insert 
	 */
	public void insert(char insertValue)
	{
		if(leftNode == null)
		{
			leftNode = new BinaryNode(insertValue);
		}  // end if(leftNode == null)
		else
			rightNode = new BinaryNode(insertValue);
			
	}  // end public void insert(char insertValue)
	
	
	
	/**
	 * Insert data into the node, for the left child only.  Check if the left child is null or not.  If it is, then 
	 * add a new node using the supplied insert value.  If its not null, then 
	 * 
	 * @param insertValue data to insert
	 */
	public void insertLeft(char insertValue)
	{
		// check if the left node is null
		
		if(leftNode == null)												// check the left child
		{
			leftNode = new BinaryNode(insertValue);							// add a new node
		}  // end if(leftNode == null)
		else
		{
			// insert this data as a node further down the tree
			leftNode.insertLeft(insertValue);								 
		}  // end else
	}  // end public void insert(Object insertValue)

	
	/**
	 * Insert data into the node, for the right child only.  Check if the rigth child is null or not.  If it is, then 
	 * add a new node using the supplied insert value.  If its not null, then 
	 *
	 * @param insertValue data to insert
	 */
	public void insertRight(char insertValue)
	{
		// check if the left node is null
		
		if(rightNode == null)												// check the right child
		{
			rightNode = new BinaryNode(insertValue);						// add a new node
		}  // end if(leftNode == null)
		else
		{
			// insert this data as a node further down the tree
			rightNode.insertRight(insertValue);								 
		}  // end else
	}  // end public void insert(Object insertValue)

	
	
	/**
	 * Set the left child to the supplied node
	 * 
	 * @param node BinaryNode to set to
	 */
	public void setLeftNode(BinaryNode node)
	{
		leftNode = node;
	}  // end public void setLeftNode(BinaryNode node)

	
	/**
	 * Set the right child to the supplied node
	 * 
	 * @param node BinaryNode to set to
	 */
	public void setRightNode(BinaryNode node)
	{
		rightNode = node;
	}  // end public void setLeftNode(BinaryNode node)

	
	
	/**
	 * Get the left node
	 * 
	 * @return BinaryNode containing the left node
	 */
	public BinaryNode getLeftNode()
	{
		return leftNode;
	}  // end public BinaryNode getLeft()


	/**
	 * Get the right node
	 * 
	 * @return BinaryNode containing the right node
	 */
	public BinaryNode getRightNode()
	{
		return rightNode;
	}  // end public BinaryNode getLeft()
	
	
	
	/**
	 * Retrieve the node's data
	 * 
	 * @return char data
	 */
	public char getData()
	{
		return data;
	}  // end public char getData()
	
	
	/**
	 * Add a node.
	 *  
	 * @param node BinaryNode to add
	 */
	public void addNode(BinaryNode node)
	{
		if(leftNode == null)									// check if the left node is available
			setLeftNode(node);									// it is, so add the node here
		else													// nope
			setRightNode(node);									// try the right node
	}  // end public void addNode()
	
	
	/**
	 * Check if the current node is a leaf or not.  It is a leaf if both left and right children are null.
	 * 
	 * @return true if there's a leaf, otherwise false
	 */
	public boolean isLeaf()
	{
		if(leftNode == null && rightNode == null)				// check if left and right references are null
			return true;										// this is a leaf
		return false;											// not a leaf
	}  // end public boolean isLeaf()
	
	
	/**
	 * Set the point location for a node in the binary tree graphic.
	 * 
	 * @param x integer x value
	 * @param y integer y value
	 */
	public void setCenterPoint(int x, int y)
	{
		center = new Point(x, y);								// create a new point and store it
	}  // end public void setCenterPoint(int x, int y)

	
	/**
	 * Get the node's graphical location used in the binary tree graphic.
	 * 
	 * @return Point containing the center x and y location of the node in the binary tree graphic
	 */
	public Point getCenterPoint()
	{
		return center;
	}  // end public Point getCenterPoint()
	
}  // end public class BinaryNode
