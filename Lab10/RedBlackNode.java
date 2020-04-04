package lab10;

public class RedBlackNode {

	long key;
	RedBlackNode left, right, p;
	String data;
	boolean red;

	// Allows for a new red-black node to be created and initialized
	public RedBlackNode(long keyValue, String dataValue) {
		key = keyValue;
		data = dataValue;
		left = null;
		right = null;
		p = null;
		red = false;
	}
}
