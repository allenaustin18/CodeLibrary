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
