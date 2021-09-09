import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;


public class AnagramService {
	/**N choose K**/
	public int nCk(int n, int k) {
		if (k>n-k)
            k=n-k;

        int result = 1;
        for (int i=1, m=n; i<=k; i++, m--)
            result = result*m/i;
        return result;
	}
    public List<AnagramCounter> compute(String dictionaryLocation) throws IOException {
        List<String> words = FileUtils.readLines(new File(dictionaryLocation));
        //used to store anagrams
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        //used to track number of anagrams counter for words of certain length
        Map<Integer, Integer> counts = new HashMap<>();
        //placeholder for anagram counter
        ArrayList<AnagramCounter> anagramCounter = new ArrayList<>();
        
        /**We sort each word and use it as a key
         * If the key already exist we add to value of the key, List
         * otherwise we insert it as a new key and add it to its List**/
        for (String word : words) {
        	/**convert the word into char array
        	 * sort the array
        	 * convert it back to a string**/
        	char[] char_word = word.toCharArray();
        	Arrays.sort(char_word);
        	String sorted_word = new String(char_word);
        	
        	/**check if the sorted string is already a key
        	 * if so we add it to the list; map[sorted string]->[...,sorted string]
        	 * **/
        	if(map.containsKey(sorted_word)) {
        		map.get(sorted_word).add(word);
        	}
        	/**Otherwise we make the new key
        	 * map[sorted string]->[sorted string]**/
        	else {
        		ArrayList<String> temp_words = new ArrayList<>();
        		temp_words.add(word);
        		map.put(sorted_word, temp_words);
        	}
        }
        /**keys with list greater than 1 mean they have anagrams
         * we combine the number of anagrams for keys with same length
         * **/
        for (String s : map.keySet()) {
        	if(map.get(s).size() > 1) {
	        	if(counts.containsKey(s.length())) {
	        		counts.merge(s.length(), nCk(map.get(s).size(), 2), Integer::sum);
	        	}else {
	        		counts.put(s.length(), nCk(map.get(s).size(),2));
	        	}
        	}
        }       
        //loop through the counts to create the anagram counters
        for(Map.Entry<Integer, Integer>entry: counts.entrySet()) {
        	//System.out.println(entry.getValue());
        	anagramCounter.add(new AnagramCounter(entry.getKey(), entry.getValue()));
        }
        return anagramCounter;
    }
}