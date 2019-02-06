package com.test.coding;

import jsjf.BinaryTreeNode;
import jsjf.LinkedBinarySearchTree;
import jsjf.MyMapEntry;

/**
 * 
 * @author Allen Austin - CS 320 - FA18 - Data Structures Programming Assignment
 *         4 - Map that uses the BinarySearchTree The program implements a Map
 *         data structure using a BinarySearchTree as its underlying data
 *         structure
 *
 */

public class MyMap<K, V> 
{
	LinkedBinarySearchTree<MyMapEntry<K, V>> linkedBinarySearchTree = new LinkedBinarySearchTree();
	
	//Method checks if the map contains an entry with the given key as its key. 
	//If it does then it returns true, otherwise it returns false. 
	public boolean containsKey(K key) 
	{
		MyMapEntry<K, V> myMapEntry = new MyMapEntry(key, null);
		return linkedBinarySearchTree.contains(myMapEntry);
	}

	//Checks if the map contains an entry with the given key as its key.  
	//If it does then it returns true, otherwise it returns false. 
	//Uses findNodeByValue as helper method to locate target element. 
	public boolean containsValue(V val) 
	{
		boolean isValueExist = false;
		MyMapEntry<K, V> myMapEntry = new MyMapEntry(null, val);
		if (this.findNodeByValue(myMapEntry, linkedBinarySearchTree.getRootNode()) == null)
		return isValueExist;
		else 
		{
			isValueExist = true;
		}
		return true;
	}

	/**
	 * Returns the number of elements in the map
	 * @return int
	 */
	public int size() 
	{
		return linkedBinarySearchTree.size();
	}

	private <T> BinaryTreeNode<T> findNodeByValue(T targetElement, BinaryTreeNode<T> next) 
	{

		if (next == null)
			return null;

		String binaryTreeNodeNextVal = next.getElement().toString();
		String[] splitValArray = binaryTreeNodeNextVal.split(",");
		String nextElementValue = splitValArray[1];

		String targetElementVal = targetElement.toString();
		String[] targetElementSplitArray = targetElementVal.split(",");
		String targetElementValue = targetElementSplitArray[1];

		if (nextElementValue.equals(targetElementValue))
			return next;
		//Traversing to the left nodes searching for target element
		BinaryTreeNode<T> temp = findNodeByValue(targetElement, next.getLeft());
		//traversing to the right if temp is null
		if (temp == null)
			temp = findNodeByValue(targetElement, next.getRight());

		return temp;
	}

	private <T> BinaryTreeNode<T> getValueByKey(T targetElement, BinaryTreeNode<T> next) 
	{

		if (next == null)
			return null;
	
		String binaryTreeNodeNextVal = next.getElement().toString();
		String[] splitValArray = binaryTreeNodeNextVal.split(",");
		String nextElementValue = splitValArray[0];

		String targetElementVal = targetElement.toString();
		String[] targetElementSplitArray = targetElementVal.split(",");
		String targetElementValue = targetElementSplitArray[0];

		if (nextElementValue.equals(targetElementValue))
			return next;
		//Traversing to the left nodes searching for target element
		BinaryTreeNode<T> temp = getValueByKey(targetElement, next.getLeft());
		//traversing to the right if temp is null
		if (temp == null)
			temp = getValueByKey(targetElement, next.getRight());

		return temp;
	}
/**
 * Put method checks if the map has an entry with the given key.
	If target key is in tree, then it removes that entry from the map. 
	Then new entry is inserted to the map which has the given (key, value) pair.	
 * @param key
 * @param value
 */
	public void put(K key, V value) 
	{
		MyMapEntry<K, V> myMapEntry = new MyMapEntry(key, value);
		
		//save 
		linkedBinarySearchTree.addElement(myMapEntry);		
	}
	/**
	 * returns a String representation of the map.
		Displays node from linkedbinarysearchtree
	 */
	public String toString() 
	{
		return linkedBinarySearchTree.toString();
	}
	/**
	 * Checks if the map has an entry with the given key.
		then returns the value that is associated with the key.
		If that entry does not exist, then it returns null.
	 * @param key
	 * @return String
	 */
	public String get(K key) 
	{

		MyMapEntry<K, V> myMapEntry = new MyMapEntry(key, null);
		BinaryTreeNode<MyMapEntry<K, V>> binaryTreeNode = getValueByKey(myMapEntry, linkedBinarySearchTree.getRootNode());

		MyMapEntry<K, V> myMapEntryValue = binaryTreeNode.getElement();

		return myMapEntryValue.getValue().toString();
	}
	
	
	/**
	 * Returns true if the map is empty and false otherwise.
	 * @return boolean
	 */
	public boolean isEmpty() 
	{
		return linkedBinarySearchTree.isEmpty();
	}

	/**
	 * 
	 * Returns true if the map is empty and false otherwise.
	 * if it exists in the map then the entry is removed and the value associated with that key is returned.
	 * If an entry with that key does not exist in the map, then null is returned.
	 * @return boolean
	 */
	 
	public String remove(K key) 
	{
		
		MyMapEntry<K, V> myMapEntry = new MyMapEntry(key, null);
		BinaryTreeNode<MyMapEntry<K, V>> binaryTreeNode = getValueByKey(myMapEntry,
		linkedBinarySearchTree.getRootNode());

		linkedBinarySearchTree.removeElement(binaryTreeNode.getElement());

		return linkedBinarySearchTree.toString();
	}
	
	
	

}

// Test file

package com.test.coding;

/**
 * 
 * @author Allen Austin - CS 320 - FA18 - Data Structures Programming Assignment
 *         4 - Map that uses the BinarySearchTree The program implements a Map
 *         data structure using a BinarySearchTree as its underlying data
 *         structure
 *
 */
/**
 * Test Class contains the main method.
	Main method tests all methods in MyMap class
	mymap.put will populate BST
 * 
 *
 */
public class Test 
{
	public static void main(String args[]) 
	{
		
		MyMap<String,Integer> myMap = new MyMap<>();
		
		 myMap.put("test1", 123);
		
		System.out.println(myMap.containsKey("test1"));
		System.out.println(myMap.containsValue(123));
		System.out.println(myMap.size());
		System.out.println(myMap.toString());
		System.out.println(myMap.get("test1"));
		System.out.println(myMap.isEmpty());
		System.out.println(myMap.remove("test1"));
		
	}
}

