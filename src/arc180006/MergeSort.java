/** Sample starter code for SP3.
 *  @author rbk
 */

package arc180006;
import java.util.Random;

public class MergeSort {
    public static Random random = new Random();
    public static int numTrials = 50;
    public static void main(String[] args) {
	int n = 13;  
	// int choice = 1 + random.nextInt(6);
	int choice = 4;
	int T= 7;
	if(args.length > 0) { n = Integer.parseInt(args[0]); }
	if(args.length > 1) { choice = Integer.parseInt(args[1]); }
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
	    arr[i] = i;
	}
	Timer timer = new Timer();
	switch(choice) {
	case 1:
		for(int i=0; i<numTrials; i++) {
		Shuffle.shuffle(arr);
		mergeSort2(arr,new int[arr.length],0,arr.length-1);
	    }
	    break;
	case 2:
	    for(int i=0; i<numTrials; i++) {
		Shuffle.shuffle(arr);
		int[] B=new int[arr.length];
		System.arraycopy(arr, 0, B, 0, arr.length);
		mergeSort3(arr,B,0,arr.length-1);
	    }
	    break;  // etc
	
	case 3:
	    for(int i=0; i<numTrials; i++) {
		Shuffle.shuffle(arr);
		int[] B=new int[arr.length];
		System.arraycopy(arr, 0, B, 0, arr.length);
		mergeSort4(arr,B,0,arr.length-1, T);
	    }
		break;  // etc
	case 4:
	    for(int i=0; i<numTrials; i++) {
		Shuffle.shuffle(arr);
		mergeSort6(arr, T);
	    }
	    break;  // etc
	}
	timer.end();
	timer.scale(numTrials);

	System.out.println("Choice: " + choice + "\n" + timer);
    }

    public static void insertionSort(int[] arr,int p , int r) { 
        for (int i = p+1; i <= r; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 

            while (j >= p && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 
    }

    public static void mergeSort2(int[] A, int[] B, int p, int r) {
		if (p < r)
		{
			int q = p +(r - p) / 2;
			mergeSort2(A, B, p, q);
			mergeSort2(A, B, q+1, r); 
			merge2(A, B, p, q, r);
		} 

	}
	

	
	public static void mergeSort3(int[] A, int[] B, int p, int r) {

		if (p < r)
		{
			int q = p +(r - p) / 2;
			mergeSort3(B, A, p, q);
			mergeSort3(B, A, q+1, r); 
			merge3(A, B, p, q, r);
		} 
	}
	
	public static void mergeSort4(int[] A, int[] B, int p, int r,int T) {
		if (r-p+1 < T) 
			insertionSort(A, p, r);
		if (p < r)
		{
			int q = p +(r - p) / 2;
			mergeSort4(B, A, p, q,T);
			mergeSort4(B, A, q+1, r, T); 
			merge3(A, B, p, q, r);
		} 
	}
	public static void mergeSort6(int[] A, int T) {
		int[] B = new int[A.length];
		int n = A.length;
		int[] inp = A;
		for (int j = 0; j < n; j = j + T) 
		{
			int r = (j+T-1)>(n-1) ? n-1 : j+T-1;
			insertionSort(A, j,r );
		}  
		for(int i = T; i < n; i = 2*i)
		{
			for(int j = 0; j < n; j = j+2*i){
				int q = (j+i-1)>(n-1)?n-1:j+i-1;
				int r = (j+2*i-1 )> (n-1) ? n-1 : j+2*i-1 ;
				merge3(B, inp, j, q, r);
			}
			int[] t = inp; 
			inp = B;
			B = t;
		} 
		if (A != inp)
			System.arraycopy(inp, 0, A, 0, inp.length);		
    }


	public static void merge2(int[] A, int[] B, int p, int q , int r) {
		System.arraycopy(A, p, B, p, r-p+1);
		int i = p;
		int k = p;
		int j = q + 1;
		while ((i <= q) && (j <= r)) {
			if (B[i] <= B[j]) 
			{
				A[k++] = B[i++];
			 }
			else {
				A[k++] = B[j++];
			 } 
		}
		while (i <= q) A[k++] = B[i++] ; 
		while (j <= r) A[k++] = B[j++] ;
	}

	public static void merge3(int[] A, int[] B, int p, int q , int r) {
		int i = p;
		int k = p;
		int j = q + 1;
		while ((i <= q) && (j <= r)) {
			if (B[i] <= B[j]) 
			{
				A[k++] = B[i++];
			 }
			else {
				A[k++] = B[j++];
			 } 
		}
		while (i <= q) A[k++] = B[i++] ; 
		while (j <= r) A[k++] = B[j++] ;
	}

   /** Timer class for roughly calculating running time of programs
     *  @author rbk
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
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
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() { if(!ready) { end(); }  return elapsedTime; }

        public long memory()   { if(!ready) { end(); }  return memUsed; }

	public void scale(int num) { elapsedTime /= num; }
	
        public String toString() {
            if(!ready) { end(); }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }
    
    /** @author rbk : based on algorithm described in a book
     */


    /* Shuffle the elements of an array arr[from..to] randomly */
    public static class Shuffle {
	
	public static void shuffle(int[] arr) {
	    shuffle(arr, 0, arr.length-1);
	}

	public static<T> void shuffle(T[] arr) {
	    shuffle(arr, 0, arr.length-1);
	}

	public static void shuffle(int[] arr, int from, int to) {
	    int n = to - from  + 1;
	    for(int i=1; i<n; i++) {
		int j = random.nextInt(i);
		swap(arr, i+from, j+from);
	    }
	}

	public static<T> void shuffle(T[] arr, int from, int to) {
	    int n = to - from  + 1;
	    Random random = new Random();
	    for(int i=1; i<n; i++) {
		int j = random.nextInt(i);
		swap(arr, i+from, j+from);
	    }
	}

	static void swap(int[] arr, int x, int y) {
	    int tmp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = tmp;
	}
	
	static<T> void swap(T[] arr, int x, int y) {
	    T tmp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = tmp;
	}

	public static<T> void printArray(T[] arr, String message) {
	    printArray(arr, 0, arr.length-1, message);
	}

	public static<T> void printArray(T[] arr, int from, int to, String message) {
	    System.out.print(message);
	    for(int i=from; i<=to; i++) {
		System.out.print(" " + arr[i]);
	    }
	    System.out.println();
	}
    }
}

