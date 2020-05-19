package ab2;

import ab2.impl.Nachnamen.Ab2Impl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class Ab2Test {

	private Random rand = new Random(System.currentTimeMillis());

	private static Ab2 ab2Impl = new Ab2Impl();

	private static int pts = 0;

	private static int NUM_TESTS = 100;
	private static int ARRAY_SIZE_SMALL = 20000;
	private static int ARRAY_SIZE_LARGE = 500000;
	private static int HASHSET_SIZE = 6691; // prime number

	//assertEquals
	//assertTrue
	//assertArrayEquals

	@Test
	public void testHeapSmall()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			ab2Impl.toHeap(test);
			assertTrue(checkHeapCondition(test, 0));
		}
	}

	@Test
	public void testHeapLarge()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_LARGE);
			ab2Impl.toHeap(test);
			assertTrue(checkHeapCondition(test, 0));
		}
	}

	@Test
	public void testHeapSorted()
	{
		
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			Arrays.sort(test);
			ab2Impl.toHeap(test);
			assertTrue(checkHeapCondition(test, 0));
		}
	}

	@Test
	public void testHeapCornerCases()
	{
		// empty array
		int[] test = new int[0];
		ab2Impl.toHeap(test);

		// array of size 1
		test = getRandomArray(1);
		ab2Impl.toHeap(test);
		assertTrue(checkHeapCondition(test, 0));

		// array with all elements zero
		test = new int[ARRAY_SIZE_SMALL];
		ab2Impl.toHeap(test);
		assertTrue(checkHeapCondition(test, 0));
	}

	@Test
	public void testQuickSelectSingleCall()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_LARGE);
			int[] sorted = Arrays.copyOf(test, test.length);
			Arrays.sort(sorted);
			assertEquals(sorted[test.length/2], ab2Impl.quickselect(test, test.length/2 + 1));
		}
	}

	@Test
	public void testQuickSelectMultipleCalls()
	{
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			int[] sorted = Arrays.copyOf(test, test.length);
			Arrays.sort(sorted);
			for(int k = 0; k < test.length; k += 5)
				assertEquals(sorted[k], ab2Impl.quickselect(test, k+1));
		}
	}

	@Test
	public void testQuickSelectCornerCases()
	{
		// one-element array
		int[] test = new int[1];
		assertEquals(test[0], ab2Impl.quickselect(test, 1));

		// all elements equal array
		test = new int[ARRAY_SIZE_SMALL];
		assertEquals(test[0], ab2Impl.quickselect(test, test.length/2+1));
	}

	@Test
	public void testQuickSelectErrors()
	{
		// empty array
		int[] test = new int[0];
		try { ab2Impl.quickselect(test, 0); assertTrue(false); } // should throw exception
		catch(Exception e) { assertTrue(true); }

		// out of bounds index
		test = new int[100];
		try { ab2Impl.quickselect(test, 101); assertTrue(false); } // should throw exception
		catch(Exception e) { assertTrue(true); }
	}

	@Test
	public void testHashSet()
	{
		int[] test = new int[HASHSET_SIZE]; 
		HashSet<Integer> hashSetRef = new HashSet<>();
		
		for(int i = 0; i < test.length; ++i)
			test[i] = -1; // initialize 

		for (int i = 0; i < HASHSET_SIZE; i++) {
		    int val = rand.nextInt(30*HASHSET_SIZE);

		    ab2Impl.insertIntoHashSet(test, val);
		    hashSetRef.add(val);

		    int val2 = rand.nextInt(30*HASHSET_SIZE);

		    assertEquals(hashSetRef.contains(val2), ab2Impl.containedInHashSet(test, val2));
		}
	}

	@Test
	public void testHashSetCollisions()
	{
		int[] test = new int[HASHSET_SIZE]; 
		HashSet<Integer> hashSetRef = new HashSet<>();
		
		for(int i = 0; i < test.length; ++i)
			test[i] = -1; // initialize

		for (int i = 0; i < HASHSET_SIZE; i++) {
		    int val = rand.nextInt(30*HASHSET_SIZE);

		    ab2Impl.insertIntoHashSet(test, val);
		    ab2Impl.insertIntoHashSet(test, val);
		    ab2Impl.insertIntoHashSet(test, val);
		    hashSetRef.add(val);
		    hashSetRef.add(val);
		    hashSetRef.add(val);

		    int val2 = rand.nextInt(30*HASHSET_SIZE);

		    assertEquals(hashSetRef.contains(val), ab2Impl.containedInHashSet(test, val));
		    assertEquals(hashSetRef.contains(val2), ab2Impl.containedInHashSet(test, val2));
		}
	}

	private int[] getRandomArray(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++)
			arr[i] = Math.abs(rand.nextInt(2 * size));
		return arr;
	}

	private boolean checkHeapCondition(int[] data, int pos)
	{
		int l = 2*pos + 1;
		int r = 2*pos + 2;

		// either the index is out-of-bounds, or the heap condition is OK for the current pos
		// and (recursively) for the subtrees on the left and right
		return  (l >= data.length || data[l] <= data[pos] && checkHeapCondition(data, l))
		     && (r >= data.length || data[r] <= data[pos] && checkHeapCondition(data, r));
	}
}
