package microsoft;
/**
 * This class is the Ternary search tree implementation.
 * It is advantageous over trie as it consumes much less memory.
 * @author Apurv
 */
public class TernarySearchTree {

	private TreeNode root;
	
	private  static class TreeNode{
		private int val;
		private char c;
		private TreeNode left, mid, right;
	}
	/**
	 * A method to put the String into the ternary search tree.
	 */
	private TreeNode put(TreeNode root, String key, int val, int d){
		char c = key.charAt(d);
		if(root == null){
			root = new TreeNode();
			root.c = c;
		}
		if(c < root.c){
			root.left = put(root.left, key, val, d);
		}
		else if(c > root.c){
			root.right = put(root.right, key, val, d);
		}
		else if(d < key.length() - 1)
			root.mid = put(root.mid, key, val, d + 1);
		else 
			root.val = val;
		return
			root;
	}
	/**
	 * Method for client to put the String into the ternary search tree.
	 */
	public void put(String key, int val){
		root = put(root, key, val, 0);
	}
	/**
	 * The logic for get method
	 */
	private TreeNode get(TreeNode root, String key, int d){
		
		if(root == null)
			return null;
		char c = key.charAt(d);
		if(c < root.c)	
			return get(root.left, key, d);
		else if(c > root.c)
			return get(root.right, key, d);
		else if(d < key.length() - 1)
			return get(root.mid, key , d + 1);
		else
			return root;
	}
	/**
	 * get method to access values corresponding to keys
	 * @return
	 */
	public Integer get(String key){
		TreeNode n = get(root, key, 0);
		if(n == null)
			return null;
		return n.val;
	}
}
