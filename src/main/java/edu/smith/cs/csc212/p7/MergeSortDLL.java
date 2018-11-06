package edu.smith.cs.csc212.p7;

import edu.smith.cs.csc212.p6.errors.*;

/*
 * MergeSort with P6 DoublyLinkedList
 */
public class MergeSortDLL {

	/*
	 * assumes input[lowEnd, mid], input(mid, highEnd] is already sorted in ascending order
	 */
	public static void merge(DoublyLinkedList<Integer> input, int lowEnd, int mid, int highEnd) {
		if(lowEnd<0||lowEnd>input.size()-1
				||highEnd<0||highEnd>input.size()-1
				||mid<0||mid>input.size()-1) {
			throw new BadIndexError();
		}
		/*
		 * create temporary lists for merging - left and right  
		 */
		DoublyLinkedList<Integer> left = new DoublyLinkedList<>();		
		DoublyLinkedList<Integer> right = new DoublyLinkedList<>();
		
		for(int i=lowEnd; i<=mid; i++) {
			left.addBack(input.getIndex(i));
		}
		for(int i=mid+1; i<=highEnd;i++) {
			right.addBack(input.getIndex(i));
		}
		
		/* 
		 * for each element in between lowEnd and highEnd in input,
		 * replace it with the smaller element between left and right at their current index 
		 */
		int indexL = 0; 
		int indexR = 0;
		int indexI = lowEnd;
		while(indexL<left.size()&&indexR<right.size()) {
			if(left.getIndex(indexL)<=right.getIndex(indexR)) {
				//do the replacement 
				input.set(indexI, left.getIndex(indexL));
				indexL++;
			}else {
				input.set(indexI, right.getIndex(indexR));
				indexR++;
			}
			indexI++;
		}
		
		/*
		 * replace in input with everything left in left or right 
		 */
		while(indexL<left.size()) {
			input.set(indexI, left.getIndex(indexL));
			indexL++;
			indexI++;
		}
		while(indexR<right.size()) {
			input.set(indexI, right.getIndex(indexR));
			indexR++;
			indexI++;
		}
	}
	
	public static void mergeSortIterative(DoublyLinkedList<Integer> input) {
		/*
		 * important stuff to know:
		 * a) current size of the to-be-merged sub-array
		 *    everyTime we merge, it is multiplied by 2
		 * b) lowEnd, mid, highEnd of the merged array
		 * c) lowEnd is the leftMost index of the to-be-merged sub-array
		 *       after merging, lowEnd = lowEnd+currentSize*2
		 *    mid is the rightMost index of the to-be-merged sub-array
		 *    highEnd is the rightMost index of the merged array (midEnd+currentSize)
		 * d) highEnd could also be input.size()-1
		 */

		int maxIndex=input.size()-1;
		for(int currentSize=1;currentSize<=maxIndex;currentSize*=2) {
			for(int lowEnd=0;lowEnd<maxIndex;lowEnd+=currentSize*2) {
				
				/*
				 * Tricky: 
				 * need to make sure mid also does not exceed the maxIndex
				 */
				int mid = Math.min(lowEnd+currentSize-1,maxIndex);
				int highEnd = Math.min(mid+currentSize, maxIndex);
				
				merge(input, lowEnd, mid, highEnd);
			}
		}
	}
	
	
	
	/*
	 * helper methods for debugging 
	 */
	public static void print(DoublyLinkedList<Integer> input) {
		for(int i=0;i<input.size();i++) {
			System.out.print(input.getIndex(i)+"-");
		}
		System.out.println(" ");
	}
	public static void printList(int low, int mid, int high) {
		System.out.print("low: "+low+"\tmid: "+mid+"\thigh: "+high);
		System.out.println(" ");
	}
	
	

}
