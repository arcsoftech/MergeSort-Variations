package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import arc180006.MergeSortGeneric;
import arc180006.MergeSortGeneric.Shuffle;


class MergeSortGenericTest {
	

	@Test
	void testTake2() {
		Random r = new Random();
		int n = 1 +  r.nextInt(1000);
		Integer[] arr = getArray(n);
		Shuffle.shuffle(arr);
		MergeSortGeneric.mergeSort1(arr);
		for (int i= 0; i < arr.length - 1; i++) {
			assertTrue(arr[i] <= arr[i+1]);
		}
	}
	
	@Test
	void testTake3() {
		Random r = new Random();
		int n = 1 +  r.nextInt(1000);
		Integer[] arr = getArray(n);
		Shuffle.shuffle(arr);
		MergeSortGeneric.mergeSort2(arr);
		for (int i= 0; i < arr.length - 1; i++) {
			assertTrue(arr[i] <= arr[i+1]);
		}
		
	}
	
	@Test
	void testTake4() {
		Random r = new Random();
		int n = 1 +  r.nextInt(1000);
		Integer[] arr = getArray(n);
		Shuffle.shuffle(arr);
		MergeSortGeneric.mergeSort3(arr);
		for (int i= 0; i < arr.length - 1; i++) {
			assertTrue(arr[i] <= arr[i+1]);
		}
	}
	
	@Test
	void testTake6() {
		Random r = new Random();
		int n = 1 +  r.nextInt(1000);
		Integer[] arr = getArray(n);
		Shuffle.shuffle(arr);
		MergeSortGeneric.mergeSort4(arr);
		for (int i= 0; i < arr.length - 1; i++) {
			assertTrue(arr[i] <= arr[i+1]);
		}
	}
	
	Integer[] getArray (int n) {
		Integer[] arr = new Integer[n];
	    for(int i=0; i<n; i++) {
	    	arr[i] = i;
	    }
	    return arr;
	}
}