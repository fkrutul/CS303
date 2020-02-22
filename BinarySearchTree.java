
package lab8;

import java.util.ArrayList;
import java.io.File;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class BinarySearchTree {
	
	private BinaryTreeNode root;
	
	// Set root of binary tree to null
	public BinarySearchTree(){
		
		root = null;
	
	}	
		
	// Inserts an element into a binary tree
	public void insert(BinaryTreeNode z) {
		
		BinaryTreeNode y = null;
		BinaryTreeNode x = root;
		
		while (x != null) {

			y = x;

			if (z.key < x.key) {
				x = x.left;
			}

			else {
				x = x.right;
			}
		}

			if ( y == null) {
				root = z;
			}

			else if (z.key < y.key) {
				y.left = z;
			}

			else {
				y.right = z;
			}
	}

	// Prints elements of binary search tree in sorted order
	public static void inOrderTraversal(BinaryTreeNode x) {
		
		if (x != null) {
			inOrderTraversal(x.left);
			System.out.print(x.key + " ");
			inOrderTraversal(x.right);
		}
	}

	// Searches for a key value node in the binary search tree
	public BinaryTreeNode search(BinaryTreeNode x, long k) {
		
		if (x == null || k == x.key) {
			return x;
		}

		if (k < x.key) {
			return search(x.left, k);
		}

		else {
			return search(x.right, k);
		}
	}
   
   // Tests methods implemented above
	public static void main(String[] args) throws IOException {

		BinaryTreeNode a = new BinaryTreeNode(77, "salad");
		BinaryTreeNode b = new BinaryTreeNode(4, "chicken");
		BinaryTreeNode c = new BinaryTreeNode(120, "fries");
		BinaryTreeNode d = new BinaryTreeNode(17, "peanuts");
		BinaryTreeNode f = new BinaryTreeNode(1, "beef");

		BinarySearchTree testTree = new BinarySearchTree();
		testTree.insert(a);
		testTree.insert(b);
		testTree.insert(c);
		testTree.insert(d);
		testTree.insert(f);

		System.out.println("Insert and Traversal Test:");
		System.out.println("");
		testTree.inOrderTraversal(a);
		System.out.println("");
		System.out.println("");
		System.out.println("UPC key search: ");
		System.out.println("");
		System.out.println("");
		System.out.println("Individual Search Times:");

		BinarySearchTree binaryTree = new BinarySearchTree();
		Scanner sc = new Scanner(Paths.get("UPC.csv"));

		while (sc.hasNextLine()) {
			String[] columns = sc.nextLine().split(",");
			BinaryTreeNode entry = new BinaryTreeNode(Long.parseLong(columns[0]), columns[2]);
			binaryTree.insert(entry);	
		}

		sc = new Scanner(Paths.get("input.dat"));	
		ArrayList<BinaryTreeNode> inputs = new ArrayList<BinaryTreeNode>();
		long execTime = 0;

		while (sc.hasNextLine()) {
			String[] columns = sc.nextLine().split(",");
			long key1 = Long.parseLong(columns[0]);
			Long subTime1 = System.nanoTime();
			binaryTree.search(binaryTree.root, key1);
			Long subTime2 = System.nanoTime() - subTime1;
			System.out.println("key: " + key1 + ": " + "time(ns): " + subTime2.toString());
			execTime = execTime + subTime2;
			inputs.add(new BinaryTreeNode(Long.parseLong(columns[0]), columns[1] + (" ") + columns[2]));
		}

		System.out.println("");
		System.out.println("Total Search time : " + execTime);
		System.out.println("");
		System.out.println("Results: ");
		System.out.println("");

		for (BinaryTreeNode output : inputs)
			System.out.println("key: " + output.key + ", " + "data: " + output.data);
	
			sc.close();
	}
}
