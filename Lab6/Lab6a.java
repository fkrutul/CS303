import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab6a {

	// Creates an array list of integers from a text file
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

	// Swaps two elements in an array
	public static void exchange(int[] A, int x, int y) {
		int a = A[x];
		int b = A[y];
		A[x] = b;
		A[y] = a;
	}

	// Sorts array by recursively swapping minimum element with the first index and the maximum element with the last index
	public static void exchangeSort(int[] Array, int p, int r) {
		if (r > p) {
			int minimum = p;
			int maximum = p;
			for (int i = p + 1; i <= r; i++) {
				if (Array[i] < Array[minimum]) { 
					minimum = i; 
				} 
				if (Array[i] >= Array[maximum]) { 
					maximum = i; 
				} 
			}
			exchange(Array,p,minimum); 
			exchange(Array,r,maximum); 
			exchangeSort(Array,p+1,r-1);	
		}
	}

	// Tests the algorithm on specified inputs
	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Initial test:");
		System.out.println("");
		ArrayList<Integer> test1 = readArray("input_16.txt");
		int[] X = test1.stream().mapToInt(i -> i).toArray(); 
		System.out.println("Unsorted array: " + Arrays.toString(X));
		System.out.println("");
		exchangeSort(X,0,X.length-1);
		System.out.println("Sorted array: " + Arrays.toString(X));
		System.out.println("");

		int[] size = { 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192 };	
		for (int j = 0; j < size.length; j++) {
			ArrayList<Integer> test2 = readArray("input_" + Integer.toString(size[j]) + ".txt");
			int[] Y = test2.stream().mapToInt(i -> i).toArray(); 
			
			System.out.println(Integer.toString(size[j]) + " integer array:");
			long execTime = System.nanoTime();
			exchangeSort(Y,0,Y.length-1); 
			execTime = System.nanoTime() - execTime;
			System.out.println("Sorting time: " + execTime + " nanosec"); 
			System.out.println("");
		}
	}
}
