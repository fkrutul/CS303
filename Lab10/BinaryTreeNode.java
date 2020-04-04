package lab8;

public class BinaryTreeNode {

        long key;
        BinaryTreeNode left, right;
        String data;
        
        public BinaryTreeNode (long keyValue, String dataValue) {
            key = keyValue;
            data = dataValue;
            left = null;
            right = null;
        }
}   
