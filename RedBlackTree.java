package lab10; 

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RedBlackTree extends BinarySearchTree {

	private RedBlackNode root;
	private RedBlackNode nil;

	// Initializes a new red-black tree with a null root
	public RedBlackTree() {

		nil = null;
		root = nil;
	}

	// Prints the elements of the tree in sorted order
	public void inOrderTraversal(RedBlackNode x) {

		if (x != null) {
			inOrderTraversal(x.left);
			System.out.print(x.key + " ");
			inOrderTraversal(x.right);
		}
	}

	// Searches for a key value in the red-black tree
	public RedBlackNode search(RedBlackNode x, long k) {

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

	// Rotates tree elements to the left
	public void leftRotate(RedBlackTree T, RedBlackNode x) {

		RedBlackNode y = x.right;
		x.right = y.left;

		if (y.left != T.nil) {
			y.left.p = x;
		}

		y.p = x.p;

		if (x.p == T.nil){
			T.root = y;
		}

		else if (x == x.p.left) {
			x.p.left = y;
		}

		else {
			x.p.right = y;
		}

		y.left = x;
		x.p = y;
	}

	// Rotates tree elements to the right
	public void rightRotate(RedBlackTree T, RedBlackNode x) {

		RedBlackNode y = x.left;
		x.left = y.right;

		if (y.right != T.nil) {
			y.right.p = x;
		}

		y.p = x.p;

		if (x.p == T.nil) {
			T.root = y;
		}

		else if (x == x.p.right) {
			x.p.right = y;
		}

		else {
			x.p.left = y;
		}

		y.right = x;
		x.p = y;
	}

	// Inserts a node into the red-black tree
	public void RBInsert(RedBlackTree T, RedBlackNode z) {

		RedBlackNode y = T.nil;
		RedBlackNode x = T.root;

		while (x != T.nil) {
			y = x;
			if (z.key < x.key) {
				x = x.left;
			}

			else {
				x = x.right;
			}
		}

		z.p = y;

		if (y == T.nil) {
			T.root = z;
		}

		else if (z.key < y.key) {
			y.left = z;
		}

		else {
			y.right = z;
		}

		z.left = T.nil;
		z.right = T.nil;
		z.red = true;
		RBInsertFixup(T,z);
	}

	// Appropriately re-colors and re-arranges nodes after a new node has been inserted
	public void RBInsertFixup(RedBlackTree T, RedBlackNode z) {

		RedBlackNode y;
		while (z != T.root && z.p.red) {
			if (z.p == z.p.p.left) {
				y = z.p.p.right;
				if (y != null && y.red == true) {
					z.p.red = false;
					y.red = false;
					z.p.p.red = true;
					z = z.p.p;	
				}

				else {

				 if (z == z.p.right) {

					z = z.p;
					leftRotate(T, z);
				}

					z.p.red = false;
					z.p.p.red = true;
					rightRotate(T, z.p.p);
			}
		}

			else {

				y = z.p.p.left;

				if (y != null && y.red) {

					z.p.red = false;
					y.red = false;
					z.p.p.red = true;
					z = z.p.p;	
				}

				else if (z == z.p.left) {
					z = z.p;
					rightRotate(T, z);
				}

				else {
					z.p.red = false;
					z.p.p.red = true;
					leftRotate(T, z.p.p);
				}
			}
		}

		T.root.red = false;
	}		

	// Tests the above methods
	public static void main(String[] args) throws FileNotFoundException {

		RedBlackNode a = new RedBlackNode(10, "salad");
		RedBlackNode b = new RedBlackNode(20, "chicken");
		RedBlackNode c = new RedBlackNode(30, "fries");
		RedBlackNode d = new RedBlackNode(40, "peanuts");
		RedBlackNode e = new RedBlackNode(50, "beef");
		RedBlackNode f = new RedBlackNode(28, "pork");
		RedBlackNode g = new RedBlackNode(29, "drink");
		RedBlackNode h = new RedBlackNode(25, "drink");

		RedBlackTree testTree = new RedBlackTree();
		testTree.RBInsert(testTree, a);
		testTree.RBInsert(testTree, b);
		testTree.RBInsert(testTree, c);
		testTree.RBInsert(testTree, d);
		testTree.RBInsert(testTree, e);
		testTree.RBInsert(testTree, f);
		testTree.RBInsert(testTree, g);
		testTree.RBInsert(testTree, h);

		System.out.println("Insert and Rotate Test :");
		System.out.println("");

		System.out.println("Inserted: " + a.key);
		System.out.println("Inserted: " + b.key);
		System.out.println("Inserted: " + c.key);
		System.out.println("Inserted: " + d.key);
		System.out.println("Inserted: " + e.key);
		System.out.println("Inserted: " + f.key);
		System.out.println("Inserted: " + g.key);
		System.out.println("Inserted: " + h.key);
		System.out.println("");
		System.out.println("root: " + testTree.root.key);
		System.out.println("is root red?: " + testTree.root.red);
		System.out.println("Node tested: " + d.key);
		System.out.println("Is node tested red?: " + d.red);

		if(d.p == null){
			System.out.println("parent: " + "null");
		}

		else {
			System.out.println("parent: " + d.p.key);
			System.out.println("is parent red?: " + d.p.red);
		}

		if(d.right == null){
			System.out.println("right child: " + "null");
		}

		else {
			System.out.println("right child: " + d.right.key);
			System.out.println("is right child red?: " + d.right.red);
		}

		if(d.left == null){
			System.out.println("left child: " + "null");
		}

		else {
			System.out.println("left child: " + d.left.key);
			System.out.println("is left child red?: " + d.left.red);
		}

		System.out.println("In order traversal: ");
		testTree.inOrderTraversal(testTree.root);
		System.out.println("");
		
		RedBlackTree rbtree = new RedBlackTree();
		Scanner scan = new Scanner(new File("UPC.csv"));
		while (scan.hasNextLine()) {
			String[] columns = scan.nextLine().split(",");
			RedBlackNode entry = new RedBlackNode(Long.parseLong(columns[0]), columns[2]);
			rbtree.RBInsert(rbtree, entry);
		}

		scan = new Scanner(new File("input.dat"));	
		ArrayList<RedBlackNode> inputs = new ArrayList<RedBlackNode>();
		long execTime = 0;

		System.out.println("");
		System.out.println("UPC key search: ");
		System.out.println("");
		System.out.println("Individual Search Times:");
		System.out.println("");
		
		while (scan.hasNextLine()) {
			String[] columns = scan.nextLine().split(",");
			long key1 = Long.parseLong(columns[0]);
			long subTime1 = System.nanoTime();
			rbtree.search(rbtree.root, key1);
			long subTime2 = System.nanoTime() - subTime1;
			System.out.println("key: " + key1 + "---" + "time(ns): " + subTime2);
			execTime = execTime + subTime2;
			inputs.add(new RedBlackNode(Long.parseLong(columns[0]), columns[1] + (" ") + columns[2]));
		}

		System.out.println("");
		System.out.println("Total Search time : " + execTime);
		System.out.println("");
		System.out.println("Results: ");
		System.out.println("");

		for (RedBlackNode outputs : inputs) {
			System.out.println("key: " + outputs.key + ", " + "data: " + outputs.data);
		}

		scan.close();
	}
}
