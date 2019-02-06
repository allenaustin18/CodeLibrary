
//Test Class to check Queue and Dequeue methods.
public class Tester 
{ 
	public static void main(String [] args)
	{
		LimitedPriorityQueue<Integer> queue = new LimitedPriorityQueue<Integer>();
		
		queue.enqueue(2, LimitedPriorityQueue.NONE);
		queue.enqueue(8, LimitedPriorityQueue.NONE);
		queue.enqueue(28, LimitedPriorityQueue.SEC);
		queue.enqueue(40,LimitedPriorityQueue.NONE);
		queue.enqueue(32, LimitedPriorityQueue.NONE);
		queue.enqueue(18, LimitedPriorityQueue.VP);
		queue.enqueue(7, LimitedPriorityQueue.PRES);
		
		System.out.println(queue);
		
		int element = queue.dequeue();
		
		
	}

}
