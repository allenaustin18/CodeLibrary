import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 * @author Allen Austin -  CS 320 - Data Structures Programming Assignment 2 -
 *         Limited Priority Queues. The type of queue will have the 
 *         same functionalities of a regular queue, but it will also
 *	       add a “priority” component to some of the elements that it will store.
 *
 */
//Limited Priority Queue class gives priority to elements stored in doubly linked list.
//Variables to allow precedence over which node is Queued and Dequeued.
public class LimitedPriorityQueue<T>
{
		public static final int PRES = 1;
		public static final int VP = 3;
		public static final int SEC = 18;
		public static final int NONE = 24;
	
		private DoubleNode<T> head, tail, pres, vp, sec;
	    private int count;
			  
//Method to perform setup of Queue
//Starting count is set to 0
	public LimitedPriorityQueue()
	{
		head = tail;
		count = 0;
	}

//Enqueue method used to add element to the tail.
//Sets the element only if the node is empty	
//Both Queueing and Dequeueging Begin with Pres and and wtih Sec	
	public void enqueue(T element, int priority)
	{
		 DoubleNode<T> node = new DoubleNode<T>(element);

	     if (isEmpty())
	    	 	head = node;
	     else
	     {
	    	 	node.setPrevious(tail);    
	        	tail.setNext(node);
	     }
	       
	     tail = node;
	     count++;
	
	  //To give the queue precedence with nodes.
	     switch(priority)
	     {
	       case PRES:  
	    	   pres = node;
           break;
	       case VP:  
	    	   vp = node;
           break;
	       case SEC: 
	       sec =  node;
           break;
	     }
	              
	}
	//Checks to see if list is empty
		private boolean isEmpty()
		{
			if(count == 0)
		    {
		        return true;
		    }
		    else
		    {
		        return false;
		    }
		}
		
	//Checks the count variable to give the size of list
		private int size()
		{
			return count;
		}
		
	
		
//
		public T dequeue()
		{
			
			if (isEmpty())
				throw new Error("Empty Queue");
			
			if(pres != null)
				{
				T element = dequeuePriority(pres);
				pres = null;
				return element;
				}
				else if (vp != null)
				{
				T element = dequeuePriority(vp);
				vp = null;
				return element;
				}
				else if (sec != null)
				{
				T element = dequeuePriority(sec);
				sec = null;
				return element;
				}
				
	
		T results = head.getElement();
		head = head.getNext();
		count--;
	
		return results;
			
		}
//Method sets precedence for the dequeue of elements that will be return to user. 
//To insure precedence is followed- Nodes previous and next are checked before dequeueing and returning.
//Both Queueing and Dequeueing Begin with Pres and end wtih Sec		
	private T dequeuePriority(DoubleNode<T> node) 
	{
				
		T element = node.getElement();
			
		if(node.getNext() != null)
		node.getNext().setPrevious(node.getPrevious()); 
		else
		tail = tail.getPrevious();
		
		if(node.getPrevious() != null)
		node.getPrevious().setNext(node.getNext());
			
		else
		head = head.getNext();
			
		return element;
				
	}
//Returns the element with the highest priority and returns head if there is no priority	
	public T highest()
	{
		if(isEmpty())
			return null;
			
			
		if(pres != null)
		{
			return pres.getElement();
		}
		else if (vp != null)
		{

			return vp.getElement();
		}
		else if (sec != null)
		{
			
			return sec.getElement();
		}
		else 
			return head.getElement();
			
	}
		
	//Every element in queue returned as string
	//does not take into account precedence
	public String toString()
	{
		if(isEmpty ())
			return "Queue is empty";
		String queue = "" + head.getElement();
		
		DoubleNode<T> current = head;
		while (current.getNext() !=null)
		{
			current = current.getNext();
			queue += ", " + current.getElement();
		}
		return queue;
		
	}
}
	