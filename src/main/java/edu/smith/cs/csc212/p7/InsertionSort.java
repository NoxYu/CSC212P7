package edu.smith.cs.csc212.p7;
import java.util.*;

public class InsertionSort {

	
	/*
	 * helper method
	 * insert the item between i and j
	 */
	public static void insert(List<Integer> items, int targetIndex, int newIndex) {
		/*
		 * targetIndex: the index at which the element to be inserted elsewhere in items 
		 * newIndex: the index at which the element is inserted 
		 * use List's built-in method add(int index, E element)
		 * insert element at index 
		 */
		if(targetIndex==newIndex) {
			return;
		}
		int temp = items.remove(targetIndex);
		if(newIndex>=items.size()) {
			items.add(temp);
		}else {
			items.add(newIndex, temp);		
		}
	}
	
	/*
	 * binary search for where to insert the element
	 * assumes input is a sorted list in ascending order from lowIndex to highIndex
	 */
	public static int binarySearch(List<Integer> input, int lowIndex, int highIndex, int targetIndex) {
		int midIndex;
		while(lowIndex<highIndex) {
			midIndex=(lowIndex+highIndex)/2;
			if(input.get(midIndex)>input.get(targetIndex)) {
				highIndex=midIndex-1;
			}else if(input.get(midIndex)<input.get(targetIndex)) {
				lowIndex=midIndex+1;
			}else {
				return midIndex;
			}
		}
		if(lowIndex==highIndex) {
			/*
			 * pre-termination condition looks like this:
			 * [...,a(low),b(mid),c(high),...]
			 * whether target is lower or higher than mid, low and high end up equal to each other 
			 * if b>target, then post-termination condition looks like this:[...,a(high, low),b(mid),c,...] 
			 * a is either lower or higher than target
			 * if a>target, target should be inserted before a; so return index of a (low)
			 * if a<target, target should be inserted after a; so return (index of a(low))+1
			 * 
			 * if b<target, then post-termination condition looks like this: [...,a,b(mid),c(high,low),...]
			 * if c>target, target should be inserted before c; so return index of c (low)
			 * if c<target, target should be inserted after c; so return (index of c (low))+1
			 */
			if(input.get(lowIndex)>input.get(targetIndex)) {
				return lowIndex;
			}else {
				return lowIndex+1;
			}
		}else {
			/*
			 * pre-termination condition looks like this:
			 * [...a,b(low, mid),c(high),d...]
			 * whether target is lower or higher than mid, low will end up greater than high
			 * if target<b, then [...,a(high),b(low, mid),c,d...], a<=target (a was smaller then b(low))
			 * so target should be inserted before b; so return index of b (low)
			 * 
			 * if target>b, then [...a,b(mid),c(high,low),d,...]
			 * high end up equal to low (see comments in the previous if statement)
			 * 
			 */
			
			return lowIndex;
		}
		
	}
	
	/*
	 * list input will be sorted in ascending order
	 * binary search is used to find the place of insertion
	 */
	public static void insertionSortBinary(List<Integer> input) {
		int size = input.size();
		for(int i=1;i<size;i++) {
			insert(input, i, binarySearch(input, 0, i-1,i));
		}
	}
	
	
	
	/*
	 * linear search for the position of insertion
	 */
	public static int linearSearch(List<Integer> input, int lowIndex, int highIndex, int targetIndex) {
		int low = input.get(lowIndex);
		int high = input.get(highIndex);
		int target = input.get(targetIndex);
		
		int insertionIndex=targetIndex;
		if(target<=low) {
			insertionIndex=lowIndex;
		}else {
			for(int i=lowIndex; i<=highIndex;i++) {
				/*
				 * indertionIndex is the index of the first occurrence of an element larger than target
				 */
				int current = input.get(i);
				if(current>=target) {
					insertionIndex=i;
					break;
				}
			}
		}		
		return insertionIndex;
	}
	
	/*
	 * linear search is used to find the place of insertion
	 */
	public static void insertionSortLinear(List<Integer> input) {
		int size=input.size();
		for(int i=1;i<size;i++) {
			insert(input, i, linearSearch(input, 0, i-1,i));
		}
	}
	
	
	/*
	 * helper method for debugging 
	 */
	public static void print(List<Integer> input) {
		for(int i=0;i<input.size();i++) {
			System.out.print(input.get(i)+"-");
		}
		System.out.println("");
	}
}
