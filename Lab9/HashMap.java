package lab9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HashMap{

  private int TABLE_SIZE;

  HashEntry[] table;

  // Size-based constructor for hash map
  HashMap(int size) {
	  TABLE_SIZE = size;
	  table = new HashEntry[size];
  }
  
  // Retrieves value put into the hash map using standard method
  public String get(long key) {
	  long hash = key % TABLE_SIZE;
	  if (table[(int)hash] == null) {
	  	return null;
	  }
  	  while (table[(int)hash] != null && table[(int)hash].getKey() != key){
   		hash = (7*hash+1) % TABLE_SIZE;
   	  }
  	 return table[(int)hash].getValue();
  }

  // Inputs key and value pair into hash map using standard method 
  public void put(long key, String value) {
      long hash = key % TABLE_SIZE;
	  while (table[(int)hash] != null && table[(int)hash].getKey() != key) { 
	  	hash = (7*hash + 1) % TABLE_SIZE; 
	  }
	  table[(int)hash] = new HashEntry(key, value);
	}
  
  // Inputs key and value pair into hash map using linear probing
  public void linearProbe(long key, String value){
      long hash = key % TABLE_SIZE;
      int i = 0;
      while (table[(int)hash] != null && table[(int)hash].getKey() != key) {
      	hash = (hash + i) % TABLE_SIZE; 
      	i++;
  		}
	  table[(int)hash] = new HashEntry(key, value);
  }
  
  // Retrieves value put into the hash map using linear probing
  public String getLinearProbe(long key) {
	  long hash = key % TABLE_SIZE;
	  int i = 0;
	  while (table[(int)hash] != null && table[(int)hash].getKey() != key) { 
	  	hash = (hash + i) % TABLE_SIZE; 
	  	i++;
	  }
	  if (table[(int)hash] == null) { 
	  	return null; 
	  }
	  else { 
	  	return table[(int)hash].getValue(); 
	  }
  }

  // Inputs key and value pair into hash map using quadratic probing
  public void quadraticProbe(long key, String value){
      long hash = key % TABLE_SIZE;
	  int i = 0;
	  while (table[(int)hash] != null && table[(int)hash].getKey() != key) { 
		  hash = (hash + i*i) % TABLE_SIZE;
		  i++;
	  }
	  table[(int)hash] = new HashEntry(key, value);
  }
  
  // Retrieves value put into the hash map using quadratic probing
  public String getQuadraticProbe(long key) {
	  long hash = key % TABLE_SIZE;
	  int i = 0;
	  while (table[(int)hash] != null && table[(int)hash].getKey() != key) { 
		  hash = (hash + i*i) % TABLE_SIZE;
		  i++;
	  }
	  if (table[(int)hash] == null) { 
	  	return null; 
	  }
	  else { 
	  	return table[(int)hash].getValue(); 
	  }
  }

  	// Tests the input and retrieval methods implemented above
	public static void main(String[] args) throws FileNotFoundException {	

			HashMap user = new HashMap(100);
			Scanner scan = new Scanner(System.in); 
			System.out.println("Please input three keys and three values");
			System.out.println("");
			for(int i = 0; i<3; i++){

				Long key = scan.nextLong();

				String data = scan.next();
				data += scan.nextLine();
				user.put(key, data);
			}

			scan = new Scanner(System.in);
			System.out.println("Please input key to retrieve value for");
			long toGet = scan.nextLong();
			System.out.println("");
			System.out.println("Value: " + user.get(toGet));
			System.out.println("");
			System.out.println("Get() Results:");
			System.out.println("");

			HashMap testMap = new HashMap(9146201);
			scan = new Scanner(new File("UPC.csv"));
			while (scan.hasNextLine()) {
				String[] columns = scan.nextLine().split(",");
				testMap.put(Long.parseLong(columns[0]), columns[2]);
			}
			scan = new Scanner(new File("input.dat"));	
			ArrayList<HashEntry> inputs = new ArrayList<HashEntry>();
			long time = 0;
			while (scan.hasNextLine()) {
				String[] columns = scan.nextLine().split(",");
				inputs.add(new HashEntry(Long.parseLong(columns[0]), columns[1] + (" ") + columns[2]));
			}
			for (int i = 0; i < inputs.size(); i++) {
				long getsub1 = System.nanoTime();
				testMap.get(inputs.get(i).getKey());
				long getsub2 = System.nanoTime();
				time = time + (getsub2 - getsub1);
				System.out.println("Key: " + inputs.get(i).getKey() + " " + "Data: " + inputs.get(i).getValue());
			}
			System.out.println("");
			System.out.println("Get() Search Time : " + time);
			System.out.println("");
			System.out.println("Linear Probe Results:");
			System.out.println("");
			
			testMap = new HashMap(9146201);
			scan = new Scanner(new File("UPC.csv"));
			while (scan.hasNextLine()) {
				String[] columns = scan.nextLine().split(",");
				testMap.linearProbe(Long.parseLong(columns[0]), columns[2]);
			}
			time = 0;
			for (int i = 0; i < inputs.size(); i++) {
				long linsub1 = System.nanoTime();
				testMap.get(inputs.get(i).getKey());
				long linsub2 = System.nanoTime();
				time = time + (linsub2 - linsub1);
				System.out.println("Key: " + inputs.get(i).getKey() + " " + "Data: " + inputs.get(i).getValue());
			}
			System.out.println("");
			System.out.println("Linear Probe Search Time : " + time);
			System.out.println("");
			System.out.println("Quadratic Probe Results:");
			System.out.println("");

			testMap = new HashMap(9146201);
			scan = new Scanner(new File("UPC.csv"));
			while (scan.hasNextLine()) {
				String[] columns = scan.nextLine().split(",");
				testMap.quadraticProbe(Long.parseLong(columns[0]), columns[2]);
			}
			time = 0;
			for (int i = 0; i < inputs.size(); i++) {
				long quadsub1 = System.nanoTime();
				testMap.getQuadraticProbe(inputs.get(i).getKey());
				long quadsub2 = System.nanoTime();
				time = time + (quadsub2 - quadsub1);
				System.out.println("Key: " + inputs.get(i).getKey() + " " + "Data: " + inputs.get(i).getValue());
			}
			System.out.println("");
			System.out.println("Quadratic Probe Search Time : " + time);
			System.out.println("");

			scan.close();
		}

	}
