package edu.smith.cs.csc212.p7;
import java.util.*;

/*
 * cited source for mergeSortInterative: 
 * https://www.geeksforgeeks.org/iterative-merge-sort/ 
 * 
 * cited source for mergeSortRecursive:
 * https://www.geeksforgeeks.org/iterative-merge-sort/ 
 */

public class MergeSort {
	
	/*
	 * assumes input[low,mid] and input(mid, high] is already sorted in ascending order
	 */
	public static void merge(List<Integer> input, int low, int mid, int high) {
		//create temporary lists for merging 
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		
		/*  
		 * separate elements in input into left and right
		 * left has input[low,mid]
		 * right has input(mid,high]
		 */
		for(int i=low;i<=mid;i++) {
			left.add(input.get(i));
		}
		for(int i=mid+1;i<=high;i++) {
			right.add(input.get(i));
		}
		
		/*
		 * for each element in input between low and high 
		 * it is replaced with the smaller element between left and right
		 */
		int indexL = 0;
		int indexR = 0;
		int indexI = low;		
		while(indexL<left.size()&&indexR<right.size()) {
			if(left.get(indexL)<=right.get(indexR)) {
				input.set(indexI, left.get(indexL));
				indexL++;
			}else {				
				input.set(indexI, right.get(indexR));
				indexR++;
			}
			indexI++;
		}
		
		/*
		 * if there's anything left in left or right, replacement is not complete 
		 */
		while(indexL<left.size()) {
			input.set(indexI, left.get(indexL));
			indexI++;
			indexL++;
		}
		while(indexR<right.size()) {
			input.set(indexI, right.get(indexR));
			indexR++;
			indexI++;
		}
		
		
	}
	public static void mergeSortIterative(List<Integer> input) {
		int maxIndex = input.size()-1;
		
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
		for(int currentSize=1;currentSize<=maxIndex;currentSize*=2) {
			for(int lowEnd = 0;lowEnd<maxIndex;lowEnd+=currentSize*2) {
				int mid = lowEnd+currentSize-1;
				
				int highEnd=mid+currentSize;
				//to prevent it from throwing IndexOutOfBound Exception:
				if(mid+currentSize>maxIndex) {
					highEnd=maxIndex;
				}
				
				merge(input,lowEnd,mid,highEnd);
			}
		}
	}
	
	public static void mergeSortRecursive(List<Integer> input, int lowEnd, int highEnd) {
		/*
		 * termination condition: lowEnd==highEnd 
		 */
		int mid = (lowEnd+highEnd)/2;
		
		/*
		 * sort the first half and then second half
		 * then merge both of them
		 */
		if(lowEnd!=highEnd) {
			mergeSortRecursive(input, lowEnd, mid);
			mergeSortRecursive(input,mid+1, highEnd);
			merge(input, lowEnd, mid, highEnd);
		}
	}
}
