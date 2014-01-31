package microsoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

/**
 * This class is implementation of the suffixtree.
 * @author Apurv
 */
public class SuffixTree {

	private TreeNode root = new TreeNode();
	/**
	 * Container class for suffixtree
	 * @author Apurv
	 */
	private static class TreeNode{
		private ArrayList<Integer> value = new ArrayList<Integer>();
		//instead of R-Way we use hashmap to reduce wastage due to nulls.
		private HashMap<Character, TreeNode> children = new HashMap<Character, TreeNode>();
	}
	
	/**
	 * Put the data into hybrid trie
	 */
	public void put(String key, int val){
		root = put(root, key, val, 0);
	}
	/**
	 * Put data into bybrid trie
	 */
	private TreeNode put(TreeNode root, String key, int val, int d){
		//null then terminate
		if(root == null)
			root = new TreeNode();
		//if end of the input string
		if(d == key.length()){
			root.value.add(val);
			return root;
		}
		//we put the indexes where the character occurs
		root.value.add(val); //this is different from tries.
		//current character
		char c = key.charAt(d);
		//if already exists in the DS
		if(root.children.containsKey(c)){
			TreeNode temp = root.children.get(c);
			temp = put(temp, key, val, d + 1);
			root.children.put(c, temp);
		}
		//if current character does not exist in the DS
		else{
			TreeNode temp = new TreeNode();
			temp = put(temp, key, val, d + 1);
			root.children.put(c, temp);
		}
		return root;
	}
	/**
	 * get method to get the value corresponding to key
	 */
	public ArrayList<Integer> get(String key){
		TreeNode x = get(root, key , 0);
		if(x == null)
			return null;
		return x.value;
	}
	/**
	 * code to retrive value corresponding to a key
	 */
	private TreeNode get(TreeNode root, String key, int d) {
		if(root == null)
			return null;
		if(d == key.length())
			return root;
		char c = key.charAt(d);
		if(root.children.containsKey(c)){
			TreeNode temp = root.children.get(c);
			return get(temp, key, d + 1);
		}
		else
			return null;
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
		Iterator<Entry<Character, TreeNode>> childrenIterator = root.children.entrySet().iterator();
		while(childrenIterator.hasNext()){
			Entry<Character, TreeNode> e = childrenIterator.next();
			Character c = e.getKey();
			TreeNode child = e.getValue();
			collect(child, prefix + c, q);
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
		if(root == null) 
			return length;
		if(root.value != null) 
			length = d;
		if(d == query.length()) 
			return length;
		char c = query.charAt(d);
		if(root.children.containsKey(c)){
			return search(root.children.get(c), query, d + 1, length);
		}
		else
			return d;
	}
}
