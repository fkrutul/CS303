import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab6b {

	// Creates an array list of strings from a text file
	private static ArrayList<String> readStringArray(String filePath) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(filePath));
		ArrayList<String> AList = new ArrayList<String>();
		while (scan.hasNextLine() ) {
			String string = scan.nextLine();
            AList.add(string);  
        }
		scan.close();
		return AList;
	}

	// Sorts locations while preserving the order of the flight times
	public static void novelSort(String[] Array) {
		for (int j = 1; j < Array.length; j++) {
			String key = Array[j];
			int i = j - 1;
			String firstLetter1 = Array[j].substring(0,1);	
			String firstLetter2 = Array[i].substring(0,1);	
			int comparison = firstLetter2.compareTo(firstLetter1);
			while (i >= 0 && comparison > 0) {
				Array[i+1] = Array[i]; 
				i = i - 1;  
				if (i >= 0) {
					firstLetter2 = Array[i].substring(0,1);
					comparison = firstLetter2.compareTo(firstLetter1);
				}
			}
			Array[i+1] = key; 
		}
	}

	// Tests both algorithms on specified inputs
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> test3 = readStringArray("NovelSortInput.txt");
		String[] arr = new String[test3.size()];
		String[] Z = test3.toArray(arr); 
		
		System.out.println("Novel sort output:");
		System.out.println("");
		novelSort(Z);
		for (int i = 0; i < Z.length; i++) {
			System.out.println(Z[i]);
		}
	}
}
