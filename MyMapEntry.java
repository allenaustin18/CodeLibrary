package jsjf;

import jsjf.exceptions.NonComparableElementException;

public class MyMapEntry<K, V> implements Comparable<K>
{
	public K key;
	public V value;
	
	public MyMapEntry(K k, V v)
	{
		key = k;
		value = v;
	}
	
	public boolean equals(Object o)
	{
		if (!(o instanceof MyMapEntry))
		{
			return false;
		}
		else
		{
			MyMapEntry<K,V> input = (MyMapEntry<K,V>)o;
			return (key == input.getKey());
		}
	}
	
	public K getKey()
	{
		return key;
	}
	
	public V getValue()
	{
		return value;
	}
	
	public void setValue(V v)
	{
		value = v;
	}
	
	public int compareTo(K k)
	{
        if (!(k instanceof Comparable))
            throw new NonComparableElementException("LinkedBinarySearchTree");

		Comparable<K> comparableKey;
		comparableKey = (Comparable<K>)this.getKey();
		return comparableKey.compareTo(k);
	}
	
	public String toString()
	{
		return ("(" + key + "," + value + ")");
	}
}
