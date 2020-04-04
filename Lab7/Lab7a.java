import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab7a {

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

	// Swaps two elements in an array
	public static void exchange(int[] A, int x, int y) {
		int a = A[x];
		int b = A[y];
		A[x] = b;
		A[y] = a;
	}

	// Sorts array by finding the minimum element and placing it at the first index
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			if (i != min){
				exchange(array, min, i);
			}
		}
	}

	// Sorts by swapping adjacent elements if they're not in correct order
	public static void bubbleSort(int[] array) {
		for(int i = 0; i < array.length - 1; i++){
			for(int j = 0; j < array.length - i - 1; j++){
				if (array[j] > array[j+1]) {
					exchange(array, j+1, j);
				}
			}
		}
	}

	// Tests selection sort and bubble sort on several inputs
	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Selection Sort Test:");
		System.out.println(" ");
		ArrayList<Integer> test = readArray("input_Sorted.txt");
		int[] A = test.stream().mapToInt(i -> i).toArray();
		int[] D = A.clone(); 
		long execTime = System.nanoTime();
		selectionSort(A);
		execTime = System.nanoTime() - execTime;
		System.out.println("Sorted Input: " + execTime);

		ArrayList<Integer> test1 = readArray("input_ReversedSorted.txt");
		int[] B = test1.stream().mapToInt(i -> i).toArray(); 
		int[] E = B.clone(); 
		long execTime1 = System.nanoTime();
		selectionSort(B);
		execTime1 = System.nanoTime() - execTime1;
		System.out.println("Reverse Sorted Input: " + execTime1);

		ArrayList<Integer> test2 = readArray("input_Random.txt");
		int[] C = test2.stream().mapToInt(i -> i).toArray(); 
		int[] F = C.clone(); 
		long execTime2 = System.nanoTime();
		selectionSort(C);
		execTime2 = System.nanoTime() - execTime2;
		System.out.println("Random Input: " + execTime2);
		System.out.println(" ");

		System.out.println("Bubble Sort Test:");
		System.out.println(" ");
		long execTime3 = System.nanoTime();
		bubbleSort(D);
		execTime3 = System.nanoTime() - execTime3;
		System.out.println("Sorted Input: " + execTime3);

		long execTime4 = System.nanoTime();
		bubbleSort(E);
		execTime4 = System.nanoTime() - execTime4;
		System.out.println("Reverse Sorted Input: " + execTime4);

		long execTime5 = System.nanoTime();
		bubbleSort(F);
		execTime5 = System.nanoTime() - execTime5;
		System.out.println("Random Sorted Input: " + execTime5);
	}	
}
