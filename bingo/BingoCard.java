package bingo;

import java.util.Random;

public class BingoCard 
{
	private int[][] numbers = new int[5][5]; 
	private boolean[][] marked = new boolean[5][5];
	private Random r = new Random();
	
	public BingoCard()
	{	
		marked[2][2] = true;
		// Generates a Bingo card by assigning numbers to each position in the board
		for (int i = 0; i < numbers.length; i++)
		{
			fillArrayUniqueInts(numbers[i], (i*15)+1, (i+1)*15);
		}
	}
	
	public boolean[][] getMarked()
	{
	    // Returns the marked array.
		return marked;
	}
	
	public void mark(int num)
	{
		// We assume that the called number is between 1 and 75.
		// This method will mark the card with the number that was called.
		
		int group = ((num-1)/15);
		mark(num, numbers[group], marked[group]);
	}
	
	private void mark(int call, int[] arr, boolean[] arrMarked)
	{
		for (int i = 0; i < arr.length; i++)
		{
			if (call == arr[i])
			{
				arrMarked[i] = true;
				break;
			}
		}
	}
	
	private boolean contains(int a, int[] arr)
	{
		// Returns true if a is currently contained in the array arr and
		// false otherwise.
		
		for (int i = 0; i < arr.length; i++)
		{
			if (a == arr[i])
			{
				return true;
			}
		}
		
		return false;
	}
	
	private void fillArrayUniqueInts(int[] arr, int lowerBound, int upperBound)
	{
		// Fills array arr with non-repeated numbers between the bounds.
		
		int range = upperBound-lowerBound+1;
		
		for (int i = 0; i < arr.length; i++)
		{
			boolean done = false;
			while (!done)
			{
				// Generate a new random number between the bounds (inclusive).
				int a = r.nextInt(range)+lowerBound;
				
				// If the number is not currently in the list then put it in the list.
				if ( !contains(a,arr) )
				{
					arr[i] = a;
					done = true;
				}
			}			
		}
	}
	
	public void resetCard()
	{
		// This resets the card to prepare for a new game.
		marked = new boolean[5][5];
		marked[2][2] = true;
	}
	
	public String toString()
	{
		String s = "";
		s += "B   I   N   G   O\n";
		for (int i = 0; i < numbers[0].length; i++)
		{
			for (int j = 0; j < numbers.length; j++)
			{
				if (marked[j][i])
				{
					s += "X   ";
				}
				else
				{
					s += (numbers[j][i] + "  ");
					if (numbers[j][i] < 10)
					{
						s += " ";
					}
				}
			}
			s += "\n";
		}
		System.out.println(s);
		return s;
	}
}

