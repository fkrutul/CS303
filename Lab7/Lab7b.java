import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab7b {

	private static int heapSize;
	private static int largest;

	// Sorts by swapping adjacent elements if they're not in correct order
	public static void bubbleSort(int[] array) {
		for(int i = 0; i < array.length - 1; i++){
			for(int j = 0; j < array.length - i - 1; j++){
				if (array[j] < array[j+1]) {
					exchange(array, j+1, j);
				}
			}
		}
	}

	// Sorts array by finding the maximum element and placing it at the first index
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] > array[min]) {
					min = j;
				}
			}
			if (i != min){
				exchange(array, min, i);
			}
		}
	}
	
	// Swaps two elements in an array structure
	public static void exchange(int[] A, int x, int y) {
		int a = A[x];
		int b = A[y];
		A[x] = b;
		A[y] = a;
	}

	// Arranges passed in node in proper min heap position
	private static void maxHeapify(int[] A, int i) {
		int l = 2*i + 1; 
		int r = 2*i + 2; 
		largest = i;
		if (l <= heapSize && A[l] < A[i]) {
		 	largest = l; }
		if (r <= heapSize && A[r] < A[largest]) {
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
			while (i>=0 && Array[i]<key) {
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
			else if (temp[j] > temp[i]) {	
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
	
	// Partitions the array into sub-arrays recursively sorted via quick sort
	private static int partition(int[] A, int p, int r) {
		int x = A[r];
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (A[j] >= x) {
				i++;
				exchange(A,i,j);
			}
		}
		exchange(A, i+1, r);
		return i + 1;
	}
	
	// Tests both quick sort variants and previously implemented algorithms on arrays of various sizes
	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("test: ");
		System.out.println("");
		ArrayList<Integer> arr = readArray("input_100.txt");
		int[] X = arr.stream().mapToInt(i -> i).toArray(); 
		int[] L = X.clone();
		int[] M = X.clone();
		int[] N = X.clone();
		int[] O = X.clone();
		int[] Z = X.clone();
		int[] temp = X.clone();
		quickSort(X, 0, X.length-1);
		System.out.println("Reversed Quick Sort");
		System.out.println("");
		System.out.println(Arrays.toString(X));
		System.out.println("");
		selectionSort(L);
		System.out.println("Reversed Selection Sort");
		System.out.println("");
		System.out.println(Arrays.toString(L));
		System.out.println("");
		heapSort(M);
		System.out.println("Reversed Heap Sort");
		System.out.println("");
		System.out.println(Arrays.toString(M));
		System.out.println("");
		mergeSort(N, temp, 0, N.length-1);
		System.out.println("Reversed Merge Sort");
		System.out.println("");
		System.out.println(Arrays.toString(N));
		System.out.println("");
		insertionSort(O);
		System.out.println("Reversed Insertion Sort");
		System.out.println("");
		System.out.println(Arrays.toString(O));
		System.out.println("");
		bubbleSort(Z);
		System.out.println("Reversed Bubble Sort");
		System.out.println("");
		System.out.println(Arrays.toString(Z));
		System.out.println("");

		int[] size = { 100, 1000, 5000, 10000, 50000 };	
		
		for (int j = 0; j < 5; j++) {
			ArrayList<Integer> arr2 = readArray("input_" + Integer.toString(size[j]) + ".txt");
			int[] A = arr2.stream().mapToInt(i -> i).toArray(); 
			int[] B = A.clone();
			int[] C = A.clone();
			int[] D = A.clone();
			int[] E = A.clone();
			int[] F = A.clone();
			int[] temp1 = A.clone();
			
			System.out.println(Integer.toString(size[j]) + " numbers");
			
			long execTime = System.nanoTime();
			quickSort(A,0,A.length-1); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Reversed Quick sort: " + execTime);

			execTime = System.nanoTime();
			selectionSort(B); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Reversed Selection sort: " + execTime);
			
			execTime = System.nanoTime();
			heapSort(C); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Reversed Heap sort: " + execTime);
			
			execTime = System.nanoTime();
			mergeSort(D,temp1,0,D.length-1); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Reversed Merge sort: " + execTime);
			
			execTime = System.nanoTime();
			insertionSort(E); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Reversed Insertion sort: " + execTime);

			execTime = System.nanoTime();
			bubbleSort(F); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Reversed Bubble sort: " + execTime);
			
			System.out.println("");
			
		}
	}

}
