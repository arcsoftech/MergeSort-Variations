/** Sample starter code for SP3.
 *  @author Cuong Ngo
 */

package arc180006;

import java.util.Random;

public class MergeSortGeneric<T extends Comparable<? super T>> {
	public static Random random = new Random();
	public static int numTrials = 50;
	public static int threshold = 8;

	public static void main(String[] args) {
		int n = 10;
		int choice = 1 + random.nextInt(4);
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		if (args.length > 1) {
			choice = Integer.parseInt(args[1]);
		}

		// array of generic type T
		Integer[] arr = new Integer[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		Timer timer = new Timer();
		switch (choice) {
		case 1: //take2
			for (int i = 0; i < numTrials; i++) {
				Shuffle.shuffle(arr);
				mergeSort1(arr);
			}
			break;
		case 2: //take3
			for (int i = 0; i < numTrials; i++) {
				Shuffle.shuffle(arr);
				mergeSort2(arr);
			}
			break;
			
		case 3: //take4
			for (int i = 0; i < numTrials; i++) {
				Shuffle.shuffle(arr);
				mergeSort3(arr);
			}
			break;
			
		case 4: //take6
			for (int i = 0; i < numTrials; i++) {
				Shuffle.shuffle(arr);
				mergeSort4(arr);
			}
			break;
		}
		

		timer.end();
		timer.scale(numTrials);

		System.out.println("Choice: " + choice + "\n" + timer);
	}
	
	public static <T> void insertionSort(T[] arr, int start, int end) {
		int n = (arr.length - 1) < end ? arr.length : end + 1;
		T key;
		int j = 0;
		for (int i = start; i < n; i++) {
			key = arr[i];
			j = i - 1;
			
			while (j >= start && ((Comparable<? super T>) arr[j]).compareTo(key) >= 0) {
				arr[j+1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}

	// merge sort take 2 with generic array
	public static <T> void mergeSort1(T[] arr) {
		@SuppressWarnings("unchecked")
		T[] arrayB = (T[]) new Object[arr.length];
		mergeSort1(arr, arrayB, 0, arr.length - 1);
	}

	// merge sort take 3
	public static <T> void mergeSort2(T[] arr) {
		@SuppressWarnings("unchecked")
		T[] arrayB = (T[]) new Object[arr.length];
		System.arraycopy(arr, 0, arrayB, 0, arr.length);
		mergeSort2(arr, arrayB, 0, arr.length - 1);
	}
	
	// merge sort take 4
	public static <T> void mergeSort3(T[] arr) {
		@SuppressWarnings("unchecked")
		T[] arrayB = (T[]) new Object[arr.length];
		System.arraycopy(arr, 0, arrayB, 0, arr.length);
		mergeSort3(arr, arrayB, 0, arr.length - 1);
	}
	
	// merge sort take 6 - iterative approach
	public static <T> void mergeSort4(T[] arr) {
		@SuppressWarnings("unchecked")
		T[] arrayB = (T[]) new Object[arr.length];
		System.arraycopy(arr, 0, arrayB, 0, arr.length);    
		
		int n = arr.length; 
		
		for (int j = 0; j < n; j = j + threshold ) {
			insertionSort(arr, j, j + threshold - 1);
		}
		
		@SuppressWarnings("unchecked")
		T[] inp = (T[]) new Object[arr.length];
		System.arraycopy(arr, 0, inp, 0, arr.length);   
		
		for (int i = threshold; i < n; i = 2 * i) {
			for (int j = 0; j < n; j = j + 2 * i) {
				merge4(arrayB, inp, j, j+i-1, j+2*i-1);
			}
			T[] t = inp;
			inp = arrayB;
			arrayB = t;
		}
		if(arr != inp) {
			System.arraycopy(inp, 0, arr, 0, arr.length);
		}
	}

	// merge sort take 2 with generic array
	public static <T> void mergeSort1(T[] a, T[] b, int p, int r) {
		if (p < r) {
			int q = p + (r - p) / 2;
			mergeSort1(a, b, p, q);
			mergeSort1(a, b, q + 1, r);
			merge1(a, b, p, q, r);
		}
	}
	
	// merge sort take 3 with generic array
	public static <T> void mergeSort2(T[] a, T[] b, int p, int r) {
		// precondition: b[p..r] is the same as a[p..r]
		if (p < r) {
			int q = p + (r - p) / 2;
			mergeSort2(b, a, p, q);
			mergeSort2(b, a, q + 1, r);
			merge2(a, b, p, q, r);
		}
	}
	
	// merge sort take 4 with generic array
	public static <T> void mergeSort3(T[] a, T[] b, int p, int r) {
		// precondition: b[p..r] is the same as a[p..r]
		if (r - p + 1 < threshold) {
			insertionSort(a, p, r);
		} else {
			if (p < r) {
				int q = p + (r - p) / 2;
				mergeSort2(b, a, p, q);
				mergeSort2(b, a, q + 1, r);
				merge2(a, b, p, q, r);
			}
		}

	}

	// merge take 2 with generic array
	public static <T> void merge1(T[] a, T[] b, int p, int q, int r) {
		// precondition: a[p..q] and A[q+1..r] are sorted
		// post condition: a[p..r] is sorted
		System.arraycopy(a, p, b, p, r - p + 1);
		int i = p;
		int k = p;
		int j = q + 1;

		while (i <= q && j <= r) {
			if (((Comparable<? super T>) b[i]).compareTo(b[j]) <= 0) {
				a[k++] = b[i++];
			} else {
				a[k++] = b[j++];
			}
		}
		while (i <= q) {
			a[k++] = b[i++];
		}
		while (j <= r) {
			a[k++] = b[j++];
		}
	}
	
	// merge take 3 with generic array
	// avoid array copy
	public static <T> void merge2(T[] a, T[] b, int p, int q, int r) {
		// precondition: b[p..q] and b[q+1..r] are sorted
		// post condition: a[p..r] is sorted
		int i = p;
		int k = p;
		int j = q + 1;

		while (i <= q && j <= r) {
			if (((Comparable<? super T>) b[i]).compareTo(b[j]) <= 0) {
				a[k++] = b[i++];
			} else {
				a[k++] = b[j++];
			}
		}
		while (i <= q) {
			a[k++] = b[i++];
		}
		while (j <= r) {
			a[k++] = b[j++];
		}
	}
	
	// merge take 3 with generic array
	// avoid array copy
	public static <T> void merge4(T[] a, T[] b, int p, int q, int r) {
		// precondition: b[p..q] and b[q+1..r] are sorted
		// post condition: a[p..r] is sorted
		int i = p;
		int k = p;
		int j = q + 1;
		r = a.length - 1 < r ? a.length -1 : r;
		q = a.length - 1 < q ? a.length -1 : q;


		while (i <= q && j <= r) {
			if (((Comparable<? super T>) b[i]).compareTo(b[j]) <= 0) {
				a[k++] = b[i++];
			} else {
				a[k++] = b[j++];
			}
		}
		while (i <= q) {
			a[k++] = b[i++];
		}
		while (j <= r) {
			a[k++] = b[j++];
		}
	}

	/**
	 * Timer class for roughly calculating running time of programs
	 * 
	 * @author rbk Usage: Timer timer = new Timer(); timer.start(); timer.end();
	 *         System.out.println(timer); // output statistics
	 */

	public static class Timer {
		long startTime, endTime, elapsedTime, memAvailable, memUsed;
		boolean ready;

		public Timer() {
			startTime = System.currentTimeMillis();
			ready = false;
		}

		public void start() {
			startTime = System.currentTimeMillis();
			ready = false;
		}

		public Timer end() {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			memAvailable = Runtime.getRuntime().totalMemory();
			memUsed = memAvailable - Runtime.getRuntime().freeMemory();
			ready = true;
			return this;
		}

		public long duration() {
			if (!ready) {
				end();
			}
			return elapsedTime;
		}

		public long memory() {
			if (!ready) {
				end();
			}
			return memUsed;
		}

		public void scale(int num) {
			elapsedTime /= num;
		}

		public String toString() {
			if (!ready) {
				end();
			}
			return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed / 1048576) + " MB / "
					+ (memAvailable / 1048576) + " MB.";
		}
	}

	/**
	 * @author rbk : based on algorithm described in a book
	 */

	/* Shuffle the elements of an array arr[from..to] randomly */
	public static class Shuffle {

		public static void shuffle(int[] arr) {
			shuffle(arr, 0, arr.length - 1);
		}

		public static <T> void shuffle(T[] arr) {
			shuffle(arr, 0, arr.length - 1);
		}

		public static void shuffle(int[] arr, int from, int to) {
			int n = to - from + 1;
			for (int i = 1; i < n; i++) {
				int j = random.nextInt(i);
				swap(arr, i + from, j + from);
			}
		}

		public static <T> void shuffle(T[] arr, int from, int to) {
			int n = to - from + 1;
			Random random = new Random();
			for (int i = 1; i < n; i++) {
				int j = random.nextInt(i);
				swap(arr, i + from, j + from);
			}
		}

		static void swap(int[] arr, int x, int y) {
			int tmp = arr[x];
			arr[x] = arr[y];
			arr[y] = tmp;
		}

		static <T> void swap(T[] arr, int x, int y) {
			T tmp = arr[x];
			arr[x] = arr[y];
			arr[y] = tmp;
		}

		public static <T> void printArray(T[] arr, String message) {
			printArray(arr, 0, arr.length - 1, message);
		}

		public static <T> void printArray(T[] arr, int from, int to, String message) {
			System.out.print(message);
			for (int i = from; i <= to; i++) {
				System.out.print(" " + arr[i]);
			}
			System.out.println();
		}

		// merge sort take 2
		public static void mergeSort1(int[] arr) {
			// B is a scratch array
			int[] arrayB = new int[arr.length];
			mergeSort(arr, arrayB, 0, arr.length - 1);
		}
		
		// mergeSort take 2 with int array
		public static void mergeSort(int[] a, int[] b, int p, int r) {

			if (p < r) {
				int q = p + (r - p) / 2;
				mergeSort(a, b, p, q);
				mergeSort(a, b, q + 1, r);
				merge(a, b, p, q, r);
			}
		}
		
		public static void merge(int[] a, int[] b, int p, int q, int r) {
			// precondition: a[p..q] and A[q+1..r] are sorted
			// post condition: a[p..r] is sorted

			System.arraycopy(a, p, b, p, r - p + 1);
			int i = p;
			int k = p;
			int j = q + 1;

			while (i <= q && j <= r) {
				if (b[i] <= b[j]) {
					a[k++] = b[i++];
				} else {
					a[k++] = b[j++];
				}
			}
			while (i <= q) {
				a[k++] = b[i++];
			}
			while (j <= r) {
				a[k++] = b[j++];
			}
		}
	}
}
