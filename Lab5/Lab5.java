import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab5 {

	private static int heapSize;
	private static int largest;
	
	// Swaps two elements in an array structure
	public static void exchange(int[] A, int x, int y) {
		int a = A[x];
		int b = A[y];
		A[x] = b;
		A[y] = a;
	}

	// Arranges passed in node in proper max heap position
	private static void maxHeapify(int[] A, int i) {
		int l = 2*i + 1; 
		int r = 2*i + 2; 
		largest = i;
		if (l <= heapSize && A[l] > A[i]) {
		 	largest = l; }
		if (r <= heapSize && A[r] > A[largest]) {
			largest = r; }
		if (largest != i) {
			exchange(A,i,largest);
			maxHeapify(A,largest);
		}
	}

	// Creates a max heap structure
	private static void buildMaxHeap(int[] A) {
		heapSize = A.length - 1;
		for (int i = (int) (A.length-1)/2; i >= 0; i--) { 
			maxHeapify(A,i); }
	}

	// Sorts an array by swapping root with last node in the heap and removing it, and then rebuilding the max heap
	public static void heapSort(int[] A) {
		buildMaxHeap(A);
		for (int i = A.length-1; i > 0; i--) {
			exchange(A,0,i);
			heapSize = heapSize - 1;
			maxHeapify(A,0);
		}
	}

	// Creates array list from input text file
	public static ArrayList<Integer> readArray(String filePath) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filePath));
		ArrayList<Integer> Alist = new ArrayList<Integer>();
		while (scan.hasNextInt()){ 
			int i = scan.nextInt();
			Alist.add(i); 
		}

		scan.close();
		return Alist;

	}

	// Sorts an array by comparing the element at each successive index to elements with lower indices
	public static int[] insertionSort(int[] Array) {
		for (int j = 1; j < Array.length; j++) {
			int key = Array[j];
			int i = j - 1;
			while (i>=0 && Array[i]>key) {
				Array[i+1] = Array[i]; 
				i = i - 1;  
			}
			Array[i+1] = key; 
		}
		return Array;
	}

	// Merges two sub-arrays of the main array
	public static void merge(int[] A, int[] temp, int p, int q, int r) {
		int i = p;
		int j = q + 1;
		for (int k = p; k < r+1; k++) { temp[k] = A[k]; }
		
		for (int k = p; k < r+1; k++) { 
			if (i > q) {	
				A[k] = temp[j];
				j++;
			}
			else if (j > r) {	
				A[k] = temp[i];
				i++;
			}
			else if (temp[j] < temp[i]) {	
				A[k] = temp[j];
				j++;
			}
			else {	
				A[k] = temp[i];
				i++;
			}
		}
	}

	// Sorts an array by repeatedly halving it, sorting its elements, and merging the sub-arrays together
	public static void mergeSort(int[] A, int[] temp, int p, int r) {
		if (p < r) {
			int q = (int) (p+r)/2;
			mergeSort(A, temp, p, q); 
			mergeSort(A, temp, q+1, r); 
			merge(A, temp, p, q, r); 
		}
	}
	
	// Sorts array using the quick sort algorithm and last element as pivot
	public static void quickSort(int[] A, int p, int r) {
		if (p < r) {
			int q = partition(A,p,r);
			quickSort(A,p,q-1);
			quickSort(A,q+1,r);
		}
	}
	
	// Sorts array using the quick sort algorithm and median of three as pivot
	public static void modQuickSort(int[] A, int p, int r) {
		if (p < r) {
			int N = r - p + 1;
			int m = median(A,p,p+N/2,r);
			exchange(A, m, r);
			int q = partition(A,p,r);
			modQuickSort(A,p,q-1);
			modQuickSort(A,q+1,r);
		}
	}
	
	// Partitions the array into sub-arrays recursively sorted via quick sort
	private static int partition(int[] A, int p, int r) {
		int x = A[r];
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (A[j] <= x) {
				i++;
				exchange(A,i,j);
			}
		}
		exchange(A, i+1, r);
		return i + 1;
	}
	
	// Returns index of the median of three
	private static int median(int[] Arr, int a, int b, int c) {
		int[][] Sub = {{Arr[a],a}, {Arr[b],b}, {Arr[c],c}};
		for (int i = 1; i < Sub.length; i++) {
			int[] key = Sub[i];
			int j = i - 1;
			while (j >= 0 && Sub[j][0] > key[0]) {
				Sub[j+1] = Sub[j]; 
				j = j - 1;  
			}
			Sub[j+1] = key; 
		}
		return Sub[1][1];
	}
	
	// Tests both quick sort variants and previously implemented algorithms on arrays of various sizes
	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Initial Quicksort Test:");
		System.out.println(" ");
		ArrayList<Integer> test = readArray("input_16.txt");
		int[] L = test.stream().mapToInt(i -> i).toArray(); 
		System.out.println("Unsorted Array: " + Arrays.toString(L));
		System.out.println(" ");
		quickSort(L,0,L.length-1);
		System.out.println("Sorted Array: " + Arrays.toString(L));
		System.out.println(" ");

		System.out.println("Quicksort Test with Three Cases:");
		System.out.println(" ");
		ArrayList<Integer> test1 = readArray("Input_Sorted.txt");
		int[] X = test1.stream().mapToInt(i -> i).toArray();
		int[] O = X.clone();
		long exec1 = System.nanoTime();
		quickSort(X, 0, X.length-1);
		exec1 = System.nanoTime() - exec1; 
		System.out.println("Sorted: " + exec1);

		ArrayList<Integer> test2 = readArray("Input_ReversedSorted.txt");
		int[] Y = test2.stream().mapToInt(i -> i).toArray();
		int[] M = Y.clone();
		long exec2 = System.nanoTime();
		quickSort(Y, 0, Y.length-1);
		exec2 = System.nanoTime() - exec2; 
		System.out.println("ReverseSorted: " + exec2);

		ArrayList<Integer> test3 = readArray("Input_Random.txt");
		int[] Z = test3.stream().mapToInt(i -> i).toArray();
		int[] N = Z.clone();
		long exec3 = System.nanoTime();
		quickSort(Z, 0, Z.length-1);
		exec3 = System.nanoTime() - exec3; 
		System.out.println("Random: " + exec3);
		System.out.println(" ");

		System.out.println("Quicksort vs Modified Quicksort with Three Cases:");
		System.out.println(" ");
		long exec4 = System.nanoTime();
		modQuickSort(O, 0, O.length-1);
		exec4 = System.nanoTime() - exec4; 
		System.out.println("Basic Sorted: " + exec1);
		System.out.println("Modified Sorted: " + exec4);
		System.out.println(" ");

		long exec5 = System.nanoTime();
		modQuickSort(M, 0, M.length-1);
		exec5 = System.nanoTime() - exec5; 
		System.out.println("Basic Reverse Sorted: " + exec2);
		System.out.println("Modified Reverse Sorted: " + exec5);
		System.out.println(" ");

		long exec6 = System.nanoTime();
		modQuickSort(N, 0, N.length-1);
		exec6 = System.nanoTime() - exec6; 
		System.out.println("Basic Random: " + exec3);
		System.out.println("Modified Random: " + exec6);
		System.out.println(" ");


		int[] size = { 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192 };	
		
		for (int j = 0; j < 10; j++) {
			ArrayList<Integer> res2 = readArray("input_" + Integer.toString(size[j]) + ".txt");
			int[] A = res2.stream().mapToInt(i -> i).toArray(); 
			int[] B = A.clone();
			int[] C = A.clone();
			int[] D = A.clone();
			int[] E = A.clone();
			int[] temp1 = A.clone();
			
			System.out.println(Integer.toString(size[j]) + " numbers");
			
			long execTime = System.nanoTime();
			quickSort(A,0,A.length-1); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Quick sort: " + execTime);
			
			execTime = System.nanoTime();
			modQuickSort(B,0,B.length-1); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Modified quick sort: " + execTime);
			
			execTime = System.nanoTime();
			heapSort(C); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Heap sort: " + execTime);
			
			execTime = System.nanoTime();
			mergeSort(D,temp1,0,D.length-1); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Merge sort: " + execTime);
			
			execTime = System.nanoTime();
			insertionSort(E); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Insertion sort: " + execTime);
			
			System.out.println("");
			
		}
	}

}
