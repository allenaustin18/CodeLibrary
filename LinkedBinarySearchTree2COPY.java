package jsjf;

import jsjf.exceptions.ElementNotFoundException;
import jsjf.exceptions.EmptyCollectionException;
import jsjf.exceptions.NonComparableElementException;

/**
 * LinkedBinarySearchTree implements the BinarySearchTreeADT interface 
 * with links.
 * 
 * @author Java Foundations
 * @version 4.0
 */
public class LinkedBinarySearchTree2COPY<T> extends LinkedBinaryTree<T>
                                        implements BinarySearchTreeADT<T>
{
    /**
     * Creates an empty binary search tree.
     */
    public LinkedBinarySearchTree2COPY() 
    {
        super();
    }		

    /**
     * Creates a binary search with the specified element as its root.
     *
     * @param element the element that will be the root of the new binary
     *        search tree
     */
    public LinkedBinarySearchTree2COPY(T element) 
    {
        super(element);
        
        if (!(element instanceof Comparable))
            throw new NonComparableElementException("LinkedBinarySearchTree");
    }		

    /**
     * Adds the specified object to the binary search tree in the
     * appropriate position according to its natural order.  Note that
     * equal elements are added to the right.
     *
     * @param element the element to be added to the binary search tree
     */
    public void addElement(T element) 
    {
        if (!(element instanceof Comparable))
            throw new NonComparableElementException("LinkedBinarySearchTree");
        
        String str3 = element.toString();
        String string2 = str3;
    	String[] parts3 = string2.split(",");
    	String str5 = parts3[0];
        
        Comparable<T> comparableElement = (Comparable<T>)str5;

        if (isEmpty())
            root = new BinaryTreeNode<T>(element);
        else 
        {
        	String str = root.getElement().toString();
        	
        	String string = str;
        	String[] parts = string.split(",");
        	String str1 = parts[0];
        	
        	T elementCompare = (T)str1;
        	
            if (comparableElement.compareTo(elementCompare) < 0)
            {
                if (root.getLeft() == null) 
                    this.getRootNode().setLeft(new BinaryTreeNode<T>(element));
                else
                    addElement(element, root.getLeft());
            }


            else
            {
                if (root.getRight() == null) 
                    this.getRootNode().setRight(new BinaryTreeNode<T>(element));
                else
                    addElement(element, root.getRight());
            }
        }
        modCount++;
    }		

    /**
     * Adds the specified object to the binary search tree in the
     * appropriate position according to its natural order.  Note that
     * equal elements are added to the right.
     *
     * @param element the element to be added to the binary search tree
     */
    private void addElement(T element, BinaryTreeNode<T> node) 
    {
    	 String str3 = element.toString();
         String string2 = str3;
     	String[] parts3 = string2.split(",");
     	String str5 = parts3[0];
     	
        Comparable<T> comparableElement = (Comparable<T>)str5;
        
        String str = node.getElement().toString();
    	
    	String string = str;
    	String[] parts = string.split(",");
    	String str1 = parts[0];
    	
    	T elementCompare = (T)str1;
        
        if (comparableElement.compareTo(elementCompare) < 0)
        {
            if (node.getLeft() == null) 
                node.setLeft(new BinaryTreeNode<T>(element));
            else
                addElement(element, node.getLeft());
        }
        else
        {
            if (node.getRight() == null) 
                node.setRight(new BinaryTreeNode<T>(element));
            else
                addElement(element, node.getRight());
        }
    }		

    /**
     * Removes the first element that matches the specified target
     * element from the binary search tree and returns a reference to
     * it.  Throws a ElementNotFoundException if the specified target
     * element is not found in the binary search tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @throws ElementNotFoundException if the target element is not found
     */
    public T removeElement(T targetElement)
                                  throws ElementNotFoundException 
    {
        T result = null;
        
        String str3 = targetElement.toString();
        String string2 = str3;
    	String[] parts3 = string2.split(",");
    	String str5 = parts3[0];
    	str5.replace("(", "");
    	
    	String aa = root.element.toString();
    	String[] bb = aa.split(",");
    	String  cc = bb[0];
    	cc.replace("(", "");
    	
    	
        if (isEmpty())
            throw new ElementNotFoundException("LinkedBinarySearchTree");
        else
        {
            BinaryTreeNode<T> parent = null;
            if (((Comparable<T>)str5).equals(cc)) 
            {
                result =  root.element;
                BinaryTreeNode<T> temp = replacement(root);
                if (temp == null)
                    root = null;		

                else 
                {
                    root.element = temp.element;
                    root.setRight(temp.right);
                    root.setLeft(temp.left);
                }

                modCount--;
            }
            else 
            {                
                parent = root;
                if (((Comparable)str5).compareTo(cc) < 0)
                    result = removeElement(targetElement, root.getLeft(), parent);
                else
                    result = removeElement(targetElement, root.getRight(), parent);
            }
        }
        
        return result;
    }		

    /**
     * Removes the first element that matches the specified target
     * element from the binary search tree and returns a reference to
     * it.  Throws a ElementNotFoundException if the specified target
     * element is not found in the binary search tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @param node the node from which to search
     * @param parent the parent of the node from which to search
     * @throws ElementNotFoundException if the target element is not found
     */
    private T removeElement(T targetElement, BinaryTreeNode<T> node, BinaryTreeNode<T> parent)
    throws ElementNotFoundException 
    {
        T result = null;
        
        String str3 = targetElement.toString();
        String string2 = str3;
    	String[] parts3 = string2.split(",");
    	String str5 = parts3[0];
    	str5.replace("(", "");
    	
    	String aa = node.element.toString();
    	String[] bb = aa.split(",");
    	String  cc = bb[0];
    	cc.replace("(", "");
        
        if (node == null)
            throw new ElementNotFoundException("LinkedBinarySearchTree");
        else
        {
            if (((Comparable<T>)str5).equals(cc)) 
            {
                result =  node.element;
                BinaryTreeNode<T> temp = replacement(node);
                if (parent.right == node)
                    parent.right = temp;		

                else 
                    parent.left = temp;

                modCount--;
            }
            else 
            {                
                parent = node;
                if (((Comparable)str5).compareTo(cc) < 0)
                    result = removeElement(targetElement, node.getLeft(), parent);
                else
                    result = removeElement(targetElement, node.getRight(), parent);
            }
        }
        
        return result;
    }		

    /**
     * Returns a reference to a node that will replace the one
     * specified for removal.  In the case where the removed node has 
     * two children, the inorder successor is used as its replacement.
     *
     * @param node the node to be removed
     * @return a reference to the replacing node
     */
    private BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) 
    {
        BinaryTreeNode<T> result = null;
        
        if ((node.left == null) && (node.right == null))
            result = null;
        
        else if ((node.left != null) && (node.right == null))
            result = node.left;
        
        else if ((node.left == null) && (node.right != null))
            result = node.right;
        
        else
        {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;		

            while (current.left != null)
            {
                parent = current;
                current = current.left;
            }
            
            current.left = node.left;
            if (node.right != current)
            {
                parent.left = current.right;
                current.right = node.right;
            }
            
            result = current;
        }
        
        return result;
    }		

    /**
     * Removes elements that match the specified target element from 
     * the binary search tree. Throws a ElementNotFoundException if 
     * the sepcified target element is not found in this tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @throws ElementNotFoundException if the target element is not found
     */
    public void removeAllOccurrences(T targetElement)
                   throws ElementNotFoundException 
    {
        removeElement(targetElement);
        
        try
        {
            while (contains((T)targetElement))
                removeElement(targetElement);
        }
        
        catch (Exception ElementNotFoundException)
        {
        }
    }		

    /**
     * Removes the node with the least value from the binary search
     * tree and returns a reference to its element.  Throws an
     * EmptyCollectionException if this tree is empty. 
     *
     * @return a reference to the node with the least value
     * @throws EmptyCollectionException if the tree is empty
     */
    public T removeMin() throws EmptyCollectionException 
    {
        T result = null;

        if (isEmpty())
            throw new EmptyCollectionException("LinkedBinarySearchTree");
        else 
        {
            if (root.left == null) 
            {
                result = root.element;
                root = root.right;
            }
            else 
            {
                BinaryTreeNode<T> parent = root;
                BinaryTreeNode<T> current = root.left;		

                while (current.left != null) 
                {
                    parent = current;
                    current = current.left;
                }
                result =  current.element;
                parent.left = current.right;
            }

            modCount--;
        }
 
        return result;
    }		

    /**
     * Removes the node with the greatest value from the binary search
     * tree and returns a reference to its element.  Throws an
     * EmptyCollectionException if this tree is empty. 
     *
     * @return a reference to the node with the greatest value
     * @throws EmptyCollectionException if the tree is empty
     */
    public T removeMax() throws EmptyCollectionException 
    {
        T result = null;

        if (isEmpty())
            throw new EmptyCollectionException("LinkedBinarySearchTree");
        else 
        {
            if (root.right == null) 
            {
                result = root.element;
                root = root.left;
            }
            else 
            {
                BinaryTreeNode<T> parent = root;
                BinaryTreeNode<T> current = root.right;		

                while (current.right != null) 
                {
                    parent = current;
                    current = current.right;
                }
                result =  current.element;
                parent.right = current.left;
            }

            modCount--;
        }
 
        return result;
    }	
    
    public T findMax()
    {        
        if (root != null)
        {
        		BinaryTreeNode<T> current = root;
        
        		while (current.getRight() != null)
        		{
        			current = current.getRight();
        		}
        		
        		return current.getElement();
        }
        else
        {
        		return null;
        }
    }
    
    public T findMin()
    {        
        if (root != null)
        {
        		BinaryTreeNode<T> current = root;
        
        		while (current.getLeft() != null)
        		{
        			current = current.getLeft();
        		}
        		
        		return current.getElement();
        }
        else
        {
        		return null;
        }
    }
    
    public T find(T targetElement)
    {
    		return find(targetElement, root);
    }
    
    public T find(T targetElement, BinaryTreeNode<T> node)
    {
        if (!(targetElement instanceof Comparable))
            throw new NonComparableElementException("LinkedBinarySearchTree");

        Comparable<T> comparableElement = (Comparable<T>) targetElement;
    		if (node == null)
    			return null;
    		else
    		{
    			if (node.getElement().equals(targetElement))
    				return node.getElement();
    			else if (comparableElement.compareTo(node.getElement()) < 0)
    			{	
    				return find(targetElement, node.getLeft());
    			}	
    			else
    			{
    				return find(targetElement,node.getRight());
    			}
    		}
    }
}
