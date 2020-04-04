package lab10;

import java.awt.Color;

public class Vertex {
	private int key; 
	private int distance; 
	private Vertex parent; 
	private Color color;

	// Vertex constructor 
	public Vertex(int keyVal) {
		key = keyVal;
		distance = Integer.MAX_VALUE;
		color = Color.WHITE;
		parent = null;
	}
	
	//Returns the key of a vertex
	public int getValue() { 
		return key; 
	}

	// Returns the distance
	public int getDistance() { 
		return distance; 
	}

	// Returns the color of a vertex
	public Color getColor() {
	 return color; 
	}

	// Returns the parent of a vertex
	public Vertex getParent() { 
		return parent; 
	}
	
	// Sets the key to passed in value
	public void setValue(int k) { 
		key = k; 
	}

	// Sets the distance to passed in value
	public void setDistance(int d) { 
		distance = d; 
	}

	// Sets the color to passed in value
	public void setColor(Color c) { 
		color = c; 
	}	

	//Sets th parent to passed in value
	public void setParent(Vertex v) { 
		parent = v; 
	}
	
	// Prints string representation of a key
	public String toString() { 
		return Integer.toString(key); 
	}
}
