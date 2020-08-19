package anagrams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;

	private void buildLetterTable() {
		letterTable = new HashMap<Character,Integer>();
		letterTable.put('a', primes[0]);
		letterTable.put('b', 3);
		letterTable.put('c', 5);
		letterTable.put('d', 7);
		letterTable.put('e', 11);
		letterTable.put('f', 13);
		letterTable.put('g', 17);
		letterTable.put('h', 19);
		letterTable.put('i', 23);
		letterTable.put('j', 29);
		letterTable.put('k', 31);
		letterTable.put('l', 37);
		letterTable.put('m', 41);
		letterTable.put('n', 43);
		letterTable.put('o', 47);
		letterTable.put('p', 53);
		letterTable.put('q', 59);
		letterTable.put('r', 61);
		letterTable.put('s', 67);
		letterTable.put('t', 71);
		letterTable.put('u', 73);
		letterTable.put('v', 79);
		letterTable.put('w', 83);
		letterTable.put('x', 89);
		letterTable.put('y', 97);
		letterTable.put('z', 101);
	}
	
	

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	
	/**
	 * 
	 * @param s - the string that is to be added to the anagramTable
	 */
	public void addWord(String s) {
		long hashcode = myHashCode(s);
		if(anagramTable.containsKey(hashcode)){
			anagramTable.get(hashcode).add(s);
		}
		else {
			ArrayList<String> newarr = new ArrayList<String>();
			newarr.add(s);
			anagramTable.put(hashcode,newarr);
		}
	}
	
	
	/**
	 * 
	 * @param s - String that is converted to hashcode
	 * @return returns the hashcode of the string as a long
	 */
	public long myHashCode(String s) {
		long ans = 1;
		for(int i = 0; i< s.length();i++) {
			ans *= letterTable.get(s.charAt(i));
		}
		return ans;
		
		
	}
	
	/**
	 * 
	 * @param s - file location of the .txt file to be analyzed 
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	/**
	 * 
	 * @return returns an Array list of all map entries where the ArrayList in the map entry is of maximum length
	 * 
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long,ArrayList<String>>> ans = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
	    int max = 0;
	    for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
	    	if (entry.getValue().size() > max) {
	    		max = entry.getValue().size();
	    	}
	    }
	    for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
	    	if (entry.getValue().size() == max) {
	    		ans.add(entry);
	    	}
	    }
	    return ans;
	    
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		
		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
		
	}
}
