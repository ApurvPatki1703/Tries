package microsoft;

import java.util.LinkedList;


/**
 * This is an implementation of trie data structure. 
 * This is implementation from Robert Sedgewick's course Algorithms 2 on coursera
 * This can be used as a map for String.
 * @author Apurv
 */
public class Tries {
	/**
	 * Extended ASCII considered
	 */
	private static final int R = 256;
	/**
	 * Root of the trie
	 */
	private TreeNode root = new TreeNode();
	/**
	 * The node of the trie
	 */
	private static class TreeNode{
		private Object value;
		private TreeNode[] next = new TreeNode[R];
	}
	/**
	 * code to put the the string into trie
	 */
	public void put(String key, int val){
		root = put(root, key, val , 0);
	}
	/**
	 * code to put the the string into trie
	 */
	private TreeNode put(TreeNode x, String key, int val, int d){
		if(x == null) x = new TreeNode();
		if(d == key.length()){
			x.value = val;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d + 1);
		return x;
	}
	/**
	 * contains implementation for checking if the node is there in trie
	 */
	public boolean contains(String key){
		return get(key) != null;
	}
	/**
	 * code to get the value corresponding to String
	 */
	public Integer get(String key){
		TreeNode x = get(root, key ,0);
		if(x == null) 
			return null;
		return (int)x.value;
	}
	/**
	 * code to get the value corresponding to String
	 */
	private TreeNode get(TreeNode x, String key, int d){
		if(x ==  null)
			return null;
		if(d == key.length())
			return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d + 1);
	}
	/**
	 * Get all the keys in the trie
	 * @return
	 */
	public Iterable<String> keys(){
		LinkedList<String> queue = new LinkedList<String>();
		collect(root, "",queue);
		return queue;
	}
	/**
	 * This method collects the keys in the trie.
	 */
	private void collect(TreeNode root, String prefix, LinkedList<String> q){
		if(root == null)return;
		if(root.value != null)q.add(prefix);
		for(char c = 0; c < R; c++){
			collect(root.next[c], prefix + c, q);
		}
	}
	/**
	 * This method gets all the keys with a given prefix. This is a very interesting interview question
	 */
	public Iterable<String> keysWithPrefix(String prefix){
		//DS to store all the nodes
		LinkedList<String> queue = new LinkedList<String>();
		//Search the node where prefix ends (if at all it exists)
		TreeNode x = get(root, prefix, 0);
		//collect all the nodes from the above found node
		collect(x, prefix, queue);
		return queue;
	}
	/**
	 * We are given a String. We have to find the longest prefix of the string that exists in the trie.
	 */
	public String longestPrefixOf(String query){
		int length = search(root, query, 0 ,0);
		return query.substring(0, length);
	}
	/**
	 * This method gets the index of longest prefix.
	 */
	private int search(TreeNode root, String query, int d, int length){
		if(root == null) return length;
		if(root.value != null) length = d;
		if(d == query.length()) return length;
		char c = query.charAt(d);
		return search(root.next[c], query, d + 1, length);
	}
}

