package lab10;

import java.io.BufferedReader;
import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class BFS {

	private Vertex[] array;

	// Constructs the BFS
	public BFS(UndirectedGraph G, int s){
		array = new Vertex[G.getSize()];
		for (int i = 0; i < G.getSize(); i++) {
			array[i] = new Vertex(i);
			if (array[i].getValue() != s) {
				array[i].setColor(Color.WHITE);
				array[i].setDistance(Integer.MAX_VALUE);
				array[i].setParent(null);
			}
		}
		
		array[s].setColor(Color.GRAY);
		array[s].setDistance(0);
		array[s].setParent(null);	
		
		Queue<Vertex> queue = new LinkedList<Vertex>(); // Queue DS
		queue.add(array[s]);
		while (!queue.isEmpty()) {
			Vertex w = queue.remove();
			int u = w.getValue();
			for (int i = 0; i < G.adjacencyList[u].size(); i++) {
				int v = G.adjacencyList[u].get(i).getValue();
				if (array[v].getColor() == Color.WHITE) {
					array[v].setColor(Color.GRAY);
					array[v].setDistance(array[u].getDistance() + 1);
					array[v].setParent(array[u]);
					queue.add(array[v]);
				}
			}

			array[u].setColor(Color.BLACK);
		}
	}
	
	// Prints the path between vertices with passed in keys
	public void printPath(UndirectedGraph G, int s, int v) {
		if (v == s) { 
			System.out.println(s); 
		}

		else if (array[v].getParent() == null) { 
			System.out.println("No path from " + Integer.toString(s) + " to " + Integer.toString(v) + " exists."); 
	}

		else { 
			printPath(G,s,array[v].getParent().getValue());
			System.out.println(v);
		}
	}
	
	// Tests performance of BFS on input files
	public static void main(String[] args) throws IOException {
		
		UndirectedGraph udgraph = new UndirectedGraph();
		BufferedReader read = new BufferedReader(new FileReader("mediumG.txt"));
		long build1 = System.nanoTime();
		udgraph.Graph(read);
		long build2 = System.nanoTime() - build1;
		System.out.println(udgraph.tostring());
		System.out.println("");
		System.out.println("Time to build medium graph: " + build2);
		System.out.println("");
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input the source (0 - 249): ");
		int source = scan.nextInt();

		long start = System.nanoTime();
		BFS search = new BFS(udgraph, source);
		start = System.nanoTime() - start;
		build2 = build2 + start;
		System.out.println("");
		System.out.println("Time for building medium graph and BFS: " + build2);

		long time = 0;
		for(int i = 0; i < udgraph.getSize(); i++) {
			System.out.println("");
			System.out.println("Path from " + source + " to " + i + ":");
			System.out.println("");
			long time1 = System.nanoTime();
			search.printPath(udgraph, source, i);
			long time2 = System.nanoTime() - time1;
			time = time + time2;
		} 

		System.out.println("");
		System.out.println("Time for searching medium graph: " + time);
		System.out.println("");
		System.out.println("Please stand by while large graph is being built");
		System.out.println("");

		udgraph = new UndirectedGraph();
		read = new BufferedReader(new FileReader("largeG.txt"));
		long build = System.nanoTime();
		udgraph.Graph(read);
		long build3 = System.nanoTime() - build;
		System.out.println("Time for building large graph: " + build3);
		System.out.println("");
		/*System.out.println(udgraph.tostring());*/
		scan = new Scanner(System.in);
		System.out.println("Please input the source (0 - 1000000): ");
		source = scan.nextInt();

		long searchlarge = System.nanoTime();
		search = new BFS(udgraph, source);
		searchlarge = System.nanoTime() - searchlarge;
		build3 = build3 + searchlarge;
		System.out.println("Time for building large graph and BFS: " + build3);

		time = 0;
		for(int i = 0; i < udgraph.getSize(); i++) {
			System.out.println("");
			System.out.println("Path from " + source + " to " + i + ":");
			System.out.println("");
			long time3 = System.nanoTime();
			search.printPath(udgraph, source, i);
			long time4 = System.nanoTime() - time3;
			time = time + time4;
		}

		System.out.println("");
		System.out.println("Time for searching large graph: " + time);
		System.out.println("");
	}	
}
