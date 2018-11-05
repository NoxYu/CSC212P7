package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestSorting {
	/**
	 * This is useful for testing whether sort algorithms work!
	 * @param items - the list of integers.
	 * @return true if it is sorted, false if not.
	 */
	private static boolean checkSorted(List<Integer> items) {
		for (int i=0; i<items.size()-1; i++) {
			if (items.get(i) >= items.get(i+1)) {
				System.err.println("items out of order: "+items.get(i)+", "+items.get(i+1) + " at index="+i);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Here's some data!
	 */
	private static int[] data = {9,8,7,6,5,4,3,2,1};
	
	@Test
	public void testBubbleSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	@Test
	public void testSelectionSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	@Test
	public void testBinarySearch() {
		ArrayList<Integer> searchMe = new ArrayList<>();
		for (int i=1;i<=15;i++) {
			searchMe.add(i);
		}
		searchMe.remove(12);
		searchMe.add(13);
		//1 2 3 4 5 6 7 8 9 10 11 12 14 15 13
		//0 1 2 3 4 5 6 7 8 09 10 11 12 13 14
		Assert.assertEquals(12, InsertionSort.binarySearch(searchMe, 0, 13, 14));
		Assert.assertEquals(13, InsertionSort.binarySearch(searchMe, 0, 12, 13));
		Assert.assertEquals(7, InsertionSort.binarySearch(searchMe, 0, 6, 7));
		searchMe.add(0);
		//1 2 3 4 5 6 7 8 9 10 11 12 14 15 13 0
		//0 1 2 3 4 5 6 7 8 09 10 11 12 13 14 15
		Assert.assertEquals(0, InsertionSort.binarySearch(searchMe, 0, 14, 15));
		
		searchMe.remove(6);
		searchMe.add(7);
		//1 2 3 4 5 6 8 9 10 11 12 14 15 13 00 07
		//0 1 2 3 4 5 6 7 08 09 10 11 12 13 14 15
		Assert.assertEquals(6, InsertionSort.binarySearch(searchMe, 0, 14, 15));				
	}	
	
	@Test
	public void testInsert() {
		ArrayList<Integer> searchMe = new ArrayList<>();
		for (int i=1;i<=5;i++) {
			searchMe.add(i);
		}
		// 1 2 3 4 5
		InsertionSort.insert(searchMe, 0, 4);
		Assert.assertEquals((Integer)1, searchMe.get(4));
		// 2 3 4 5 1
		InsertionSort.insert(searchMe, 1, 3);
		// 2 4 5 3 1
		Assert.assertEquals((Integer)3, searchMe.get(3));
	}
	
	
	@Test
	public void testInsertionSortBinary() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		InsertionSort.insertionSortBinary(sortMe);		
		Assert.assertTrue(checkSorted(sortMe));
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);		
		InsertionSort.insertionSortBinary(sortMe);
		Assert.assertTrue(checkSorted(sortMe)); 
	}
	
	
	
	@Test
	public void testInsertionSortLinear() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		InsertionSort.insertionSortLinear(sortMe);		
		Assert.assertTrue(checkSorted(sortMe));
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		InsertionSort.insertionSortLinear(sortMe);
		Assert.assertTrue(checkSorted(sortMe)); 
	}

}
