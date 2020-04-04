package lab12;

import java.io.BufferedReader;
import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DFS {

	private Vertex[] array;
	private int time; 
	private LinkedList<Vertex> toputil;

	// Constructs the DFS
	public DFS(UndirectedGraph G){
		array = new Vertex[G.getSize()];
		for (int i = 0; i < G.getSize(); i++) {
			array[i] = new Vertex(i);
			array[i].setColor(Color.WHITE);
			array[i].setParent(null);
		}
		
		time = 0; 
		for (int i = 0; i < G.getSize(); i++) {
			if (array[i].getColor() == Color.WHITE) {
				DFSVisit(G, array[i]);
			}
		}
	}

	// Visits a vertex as a part of the DFS algorthm
	public void DFSVisit(UndirectedGraph G, Vertex u) {
		toputil = new LinkedList<>();
		time = time + 1;
		int w = u.getValue();
		array[w].setDistance(time);
		array[w].setColor(Color.GRAY);
		for (int i = 0; i < G.adjacencyList[w].size(); i++) {
			int v = G.adjacencyList[w].get(i).getValue();
			if (array[v].getColor() == Color.WHITE){
				array[v].setParent(array[w]);
				DFSVisit(G, array[v]);
			}
		array[w].setColor(Color.BLACK);
		time = time + 1;
		array[w].f = time;
		toputil.addFirst(array[w]);
		}
	}

	// Stores vertices in linked list in order visited by DFS
	public LinkedList<Vertex> topologicalSort(UndirectedGraph G) {
		DFS search = new DFS(G);
		System.out.println(toputil.toString());
		return toputil;
	}
	
	// Prints the path between vertices with passed in keys
	public void printPath(UndirectedGraph G, int s, int v) {
		if (v == s) { 
			System.out.print(s + ", "); 
		}

		else if (array[v].getParent() == null) { 
			System.out.print("No path from " + Integer.toString(s) + " to " + Integer.toString(v) + " exists." + ", "); 
	}

		else { 
			printPath(G,s,array[v].getParent().getValue());
			System.out.print(v + ",");
		}
	}
	
	// Tests performance of DFS and Topological Sort on input files
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

		long start = System.nanoTime();
		DFS search = new DFS(udgraph);
		start = System.nanoTime() - start;
		build2 = build2 + start;
		System.out.println("");
		System.out.println("Time for building medium graph and DFS: " + build2);

		long time = 0;
		for(int i = 0; i < udgraph.getSize(); i++) {
			System.out.println("");
			System.out.println("Path from " + 0 + " to " + i + ":");
			System.out.println("");
			long time1 = System.nanoTime();
			search.printPath(udgraph, 0, i);
			System.out.println("");
			long time2 = System.nanoTime() - time1;
			time = time + time2;
		} 

		System.out.println("");
		System.out.println("Time for printing paths: " + time);
		System.out.println("");
		System.out.println("Tiny directed graph: ");
		System.out.println("");

		UndirectedGraph dgraph = new UndirectedGraph();
		read = new BufferedReader(new FileReader("tinyDG.txt"));
		long build3 = System.nanoTime();
		dgraph.Graph(read);
		long build4 = System.nanoTime() - build3;
		System.out.println(dgraph.tostring());
		System.out.println("");
		System.out.println("Time to build tiny directed graph: " + build4);
		System.out.println("");
		System.out.println("Topological Sort: ");
		System.out.println("");
		search = new DFS(dgraph);
		search.topologicalSort(dgraph);
	}	
}
