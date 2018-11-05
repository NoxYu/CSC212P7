package edu.smith.cs.csc212.p7;
import java.util.*;

public class SelectionSort {

	
	/*
	 * helper method
	 * swap two items in a list
	 */
	public static void swap(List<Integer> items, int i, int j) {
		int temp = items.get(j);
		items.set(j, items.get(i));
		items.set(i, temp);
	}
	
	/*
	 * list input is sorted in ascending order
	 * i.e. smallest index holds the smallest integer
	 */
	public static void selectionSort(List<Integer> input) {
		int size = input.size();
		for(int i=0;i<size-1;i++) {
			int min = input.get(i);
			int minIndex = i;
			for(int j=i+1;j<size;j++) {
				if(input.get(j)<min) {
					min=input.get(j);
					minIndex=j;
				}
			}
			swap(input,i,minIndex);
		}
	}
}
