import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Lab1 {

	// Reads a text file of integers and returns an array of those integers
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

	// Checks each element in an array until the desired element is found
	public static int linearSearch(int[] Array, int key) {
		for (int i = 0; i < Array.length; i++) { 
			if (Array[i] == key){
				return i; 
			}
		}

		return -1; 
	}

	// Checks the array for key by halving each successive interval
	public static int binarySearch(int[] Array, int low, int high, int key) {
		if (high >= low) { 
			int mid = (low + high)/2; 
            if (Array[mid] == key) { 
            	return mid; 
         	}
            
            else if (Array[mid] > key) { 
            	int lower = binarySearch(Array, low, mid - 1, key);
            	return lower; 
            }
            
            else { 
            	int higher = binarySearch(Array, mid + 1, high, key);
            	return higher; 
            }
		}

		return -1; 
	}

	// Tests the program by initializing specified arrays and performing the required algorithms
	public static void main(String args[]) throws FileNotFoundException { 
		ArrayList<Integer> List = readArray("inputGenerated.txt");
		for (int i = 4; i <= 25; i++) {
			Random randNum = new Random();
    		int N = (int) Math.pow(2,i);
    		int[] Array = new int[N];
    		for (int j = 0; j < N; j++) {
    			Array[j] = randNum.nextInt(N+1);
    		}

    		Random randInt = new Random();
    		int e = List.get((randInt.nextInt(1000)));
    		
    		long startlinearTime = System.nanoTime();
    		linearSearch(Array, e);
    		long endlinearTime = System.nanoTime();
    		long linearTime = endlinearTime - startlinearTime;

    		Arrays.sort(Array); 

    		long startbinaryTime = System.nanoTime();
    		binarySearch(Array, 0, Array.length-1, e);
    		long endbinaryTime = System.nanoTime();
    		long binaryTime = endbinaryTime - startbinaryTime;
    		System.out.println("Array size " + Integer.toString(N) + ":");
    		System.out.println("Linear search time: " + Long.toString(linearTime));
    		System.out.println("Binary search time: " + Long.toString(binaryTime));
    		System.out.println("");
    	}
