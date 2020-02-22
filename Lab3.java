import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Lab3 {


	// Reads a text file and creates an array with its elements
	private static ArrayList<Integer> readArray(String filePath) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(filePath));
		ArrayList<Integer> L = new ArrayList<Integer>();
		while (sc.hasNextInt()){
            int i = sc.nextInt();
            L.add(i); 
        }
		sc.close();
		return L;
	}

	// Insertion sort from lab 2 modified to take in the index of the first and last element to be sorted
	public static void insertionSort(int[] A, int p, int r) {
		for (int j = p; j <= r; j++) {
			int key = A[j];
			int i = j - 1;
			while (i>=0 && A[i]>key) {
				A[i+1] = A[i]; 
				i = i - 1;  
			}
			A[i+1] = key; 
		}
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
	
	// If the length of the array exceeds the cutoff, insertion sort is called
	public static void modMergeSort(int[] A, int[] temp, int p, int r, int cutoff) {
		if (p < r) {
			if (r - p + 1 <= cutoff) { 
				insertionSort(A,p,r); 
			}
		    else {
		    	int q = (int) (p+r)/2;
				mergeSort(A, temp, p, q);
				mergeSort(A, temp, q+1, r);
				merge(A, temp, p, q, r);
		    }
		}
	}
	
	// Driver program to test the performance of merge sort, insertion sort, and the modified merge sort
	public static void main(String[] args) throws FileNotFoundException {
		
		int[] size = { 100, 1000, 5000, 10000, 50000, 100000, 500000 };	
		for (int j = 0; j < size.length; j++) {
			ArrayList<Integer> res = readArray("input_" + Integer.toString(size[j]) + ".txt");
			int[] A = res.stream().mapToInt(i -> i).toArray(); 
			int[] B = A.clone();
			int[] C = A.clone();
			int[] D = A.clone();
			int[] E = A.clone();
			int[] F = A.clone();
			int[] temp1 = A.clone();
			int[] temp2 = A.clone();
			int[] temp3 = A.clone();
			int[] temp4 = A.clone();
			int[] temp5 = A.clone();
			int c = 10;
			int d = 20;
			int e = 30;
			int f = 40;
			
			System.out.println(Integer.toString(size[j]) + " numbers");
			
			long execTime = System.nanoTime();
			mergeSort(A,temp1,0,A.length-1); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Merge sort: " + execTime);
			
			execTime = System.nanoTime();
			insertionSort(B,0,B.length-1); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Insertion sort: " + execTime);
			
			execTime = System.nanoTime();
			modMergeSort(C,temp2,0,C.length-1,c); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Modified merge sort (" + Integer.toString(c) + "): " + execTime);
			
			execTime = System.nanoTime();
			modMergeSort(D,temp3,0,D.length-1,d); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Modified merge sort (" + Integer.toString(d) + "): " + execTime);
			
			execTime = System.nanoTime();
			modMergeSort(E,temp4,0,E.length-1,e); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Modified merge sort (" + Integer.toString(e) + "): " + execTime);
			
			execTime = System.nanoTime();
			modMergeSort(F,temp5,0,F.length-1,f); 
			execTime = System.nanoTime() - execTime; 
			System.out.println("- Modified merge sort (" + Integer.toString(f) + "): " + execTime);
			
			System.out.println("");
			
		}
	}
}
