package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import arc180006.MergeSort;
import arc180006.MergeSort.Shuffle;

class MergeSortTest {
	

	@Test
	void testTake2() {
		Random r = new Random();
		int n = 1 +  r.nextInt(1000);
		int[] arr = getArray(n);
		Shuffle.shuffle(arr);
		MergeSort.mergeSort2(arr,new int[arr.length],0,arr.length-1);
		for (int i= 0; i < arr.length - 1; i++) {
			assertTrue(arr[i] <= arr[i+1]);
		}
	}
	
	@Test
	void testTake3() {
		Random r = new Random();
		int n = 1 +  r.nextInt(1000);
		int[] arr = getArray(n);
		Shuffle.shuffle(arr);
		int[] B=new int[arr.length];
		System.arraycopy(arr, 0, B, 0, arr.length);
		MergeSort.mergeSort3(arr,B,0,arr.length-1);
		for (int i= 0; i < arr.length - 1; i++) {
			assertTrue(arr[i] <= arr[i+1]);
		}
		
	}
	
	@Test
	void testTake4() {
		Random r = new Random();
		int n = 1 +  r.nextInt(1000);
		int[] arr = getArray(n);
		Shuffle.shuffle(arr);
		int[] B=new int[arr.length];
		System.arraycopy(arr, 0, B, 0, arr.length);
		MergeSort.mergeSort4(arr,B,0,arr.length-1, 7);
		for (int i= 0; i < arr.length - 1; i++) {
			assertTrue(arr[i] <= arr[i+1]);
		}
	}
	
	@Test
	void testTake6() {
		Random r = new Random();
//		int n = 1 +  r.nextInt(1000);
		int n = 13;
		int[] arr = getArray(n);
		Shuffle.shuffle(arr);
		MergeSort.mergeSort6(arr, 7);
		for (int i= 0; i < arr.length - 1; i++) {
			assertTrue(arr[i] <= arr[i+1]);
		}
	}
	
	int[] getArray (int n) {
		int[] arr = new int[n];
	    for(int i=0; i<n; i++) {
	    	arr[i] = i;
	    }
	    return arr;
	}
	
	
	

}
