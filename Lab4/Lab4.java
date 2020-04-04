import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab4 {
	
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
	
	// Tests the program by initializing specified arrays and performing the desired algorithms
	public static void main(String[] args) throws FileNotFoundException {
		int[] size = { 100, 1000, 5000, 10000, 50000 };	
		ArrayList<Integer> test1 = readArray("input_100.txt");
		int[] L = test1.stream().mapToInt(i -> i).toArray(); 
		
		heapSort(L);
		System.out.println(Arrays.toString(L));
		System.out.println("");
		
		for (int j = 0; j < 5; j++) {
			ArrayList<Integer> test2 = readArray("input_" + Integer.toString(size[j]) + ".txt");
			int[] A = test2.stream().mapToInt(i -> i).toArray(); 
			int[] B = A.clone();
			int[] C = A.clone();
			int[] temp = A.clone();
			
			System.out.println(Integer.toString(size[j]) + " integers");
			
			long execTime = System.nanoTime();
			heapSort(A); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Heap sort: " + execTime);
			
			execTime = System.nanoTime();
			mergeSort(B,temp,0,B.length-1); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Merge sort: " + execTime);
			
			execTime = System.nanoTime();
			insertionSort(C); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Insertion sort: " + execTime);
			
			System.out.println("");
			
		}
		
	}

}
