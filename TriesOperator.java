package microsoft;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Sample uses of tries and family
 * @author Apurv
 *
 */
public class TriesOperator {

	public static void main(String[] args){
		HybridTrie hbt = new HybridTrie();
		String[] arr = {"abc", "abcd", "abcde", "bcde", "bce", "eofl", "cctv"};
		for(int i = 0; i < arr.length; i++){
			String s = arr[i];
			hbt.put(s, i + 1);
		}
		Integer result = hbt.get("cctv");
		LinkedList<String> resultList = (LinkedList<String>) hbt.keys();
		for(int i = 0; i< resultList.size(); i++){
			System.out.println(resultList.get(i));
		}
		System.out.println("--------|||||||||||-------------");
		LinkedList<String> resultList2 = (LinkedList<String>) hbt.keysWithPrefix("abc");
		for(int i = 0; i< resultList2.size(); i++){
			System.out.println(resultList2.get(i));
		}
		System.out.println("--------MARKER---------");
		String res = hbt.longestPrefixOf("abcdef");
		System.out.println(res);
		System.out.println("---------------SUFFIX TREE DEMO----------");
		SuffixTree sft = new SuffixTree();
		String data = "abcbcabcdab";
		for(int i=0 ; i < data.length(); i++){
			String s = data.substring(i);
			sft.put(s, i);
		}
		ArrayList<Integer> resultIndex = sft.get("bc");
		for(int i = 0 ; i < resultIndex.size(); i++){
			System.out.println(resultIndex.get(i));
		}
	}
}


