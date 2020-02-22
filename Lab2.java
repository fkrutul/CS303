import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab2 {

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
	
	// Reads a text file of integers and returns an array of those integers
	private static ArrayList<Integer> readArray(String filePath) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filePath));
		ArrayList<Integer> List = new ArrayList<Integer>();
		while (scan.hasNextInt()){
            int i = scan.nextInt();
            List.add(i); 
        }
		scan.close();
		return List;
	}
	
	// Tests the program by initializing specified arrays and performing the insertion sort algorithm
	public static void main(String[] args) throws FileNotFoundException {
		String filePath = "input_100.txt";
		ArrayList<Integer> List = readArray(filePath);
		int[] Array = List.stream().mapToInt(i -> i).toArray();
		System.out.println("Unsorted Array:");
		System.out.println(" ");
		System.out.println(Arrays.toString(Array));
		System.out.println(" ");
		insertionSort(Array); 
		System.out.println("Sorted Array:");
		System.out.println(" ");
		System.out.println(Arrays.toString(Array)); 
		System.out.println(" ");
		
		System.out.println("Time Comparison:");
		System.out.println(" ");
		
		int[] size = { 100, 1000, 5000, 10000, 50000, 100000, 500000 };	
		for (int j = 0; j < 7; j++) {
			ArrayList<Integer> read = readArray("input_" + Integer.toString(size[j]) + ".txt");
			int[] Arr = read.stream().mapToInt(i -> i).toArray(); 
			long startTime = System.nanoTime();
			insertionSort(Arr); 
			long endTime  = System.nanoTime();
			long executionTime = endTime - startTime; 
			System.out.println(Integer.toString(size[j]) + " numbers: " + executionTime);
		}
	
	}
}
