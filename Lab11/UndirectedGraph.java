package lab10;

import java.io.BufferedReader;
import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")

public class UndirectedGraph {
	public int v;
	public int e;
	public LinkedList<Vertex>[] adjacencyList;

	// Constructs an undirected graph
	public UndirectedGraph() {
		v = 0;
		e = 0;
	}

	// Creates an adjacency list representation of a graph
	public void Graph(BufferedReader buffy) throws IOException {
        String read = buffy.readLine();
        v = Integer.parseInt(read);
        read = buffy.readLine();
        e = Integer.parseInt(read);
        adjacencyList = new LinkedList[v];

        for (int i = 0; i < v; i++) { 
        	adjacencyList[i] = new LinkedList<Vertex>(); 
        }

        while ((read = buffy.readLine()) != null) {
            int a, b;
            StringTokenizer token = new StringTokenizer(read, " ");
            a = Integer.parseInt(token.nextToken());
            b = Integer.parseInt(token.nextToken());
            addEdge(a, b);
        }
    }
	
	// Adds edge to graph
	public void addEdge(int v, int w) {
		adjacencyList[v].add(new Vertex(w));
		adjacencyList[w].add(new Vertex(v));
	}
	
	// Returns size of adjacency list
	public int getSize() { 
        return adjacencyList.length; 
    }
	
	// Creates string representation of an adjacency list
	public String tostring() {
        String string = new String();
        string = "Vertices: " + v + ", Edges: " + e + "\n";
        for (int i = 0; i < v; i++) {
            string = string + i +": ";
            for (int j = 0; j < adjacencyList[i].size(); j++) { 
            	string = string + adjacencyList[i].get(j).toString() + " "; 
            }

            string = string + "\n";  
        }

        return string;
    } 
}
