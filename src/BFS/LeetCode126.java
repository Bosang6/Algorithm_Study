package BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.cn/problems/word-ladder-ii/description/

// BFS 建图， 反向DFS生成路径

public class LeetCode126 {
	
	// 词库， 用于快速查找 O(1)
	public static HashSet<String> dictionary;
	
	public static HashSet<String> curLevel = new HashSet<>();
	
	public static HashSet<String> nextLevel = new HashSet<>();
	
	// 反向图， 所有与 String 有关的字符串组成一个ArrayList， 用于遍历 
	public static HashMap<String, ArrayList<String>> graph = new HashMap<>();
	
	public static LinkedList<String> path = new LinkedList<>();
	
	// 所有路径
	public static List<List<String>> ans = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    	build(wordList);
    	if(!dictionary.contains(endWord)) {
    		return ans;
    	} else {
    		if(bfs(beginWord, endWord)) {
    			dfs(endWord, beginWord);
    		}
    	}
    	
    	return ans;
    }
    
    public static void build(List<String> wordList) {
    	dictionary = new HashSet<>(wordList);
    	curLevel.clear();
    	nextLevel.clear();
    	graph.clear();
    	ans.clear();
    }
    
    public static boolean bfs(String begin, String end) {
    	boolean esist = false;
    	curLevel.add(begin);
    	
    	while(!curLevel.isEmpty()) {
    		for(String str : curLevel) {
    			// 在词库内删除 当前遍历层的所有单词
    			dictionary.removeAll(curLevel);
    			char[] word = str.toCharArray();
    	      	for(int i = 0; i < word.length; i++) {
    	      		// 对单词上每个字符依次进行修改，然后在词库在查看是否存在。 若存在，加入下级遍历层
            		char old = word[i];
            		for(char j = 'a'; j <= 'z'; j++) {
            			word[i] = j;
            			String convert = String.valueOf(word);
            			if(dictionary.contains(convert) && !begin.equals(convert)) {
            				if(convert.equals(end)) {
            					esist = true;
            				}
            				// 建立反向图
            				graph.putIfAbsent(convert, new ArrayList<>());
            				graph.get(convert).add(str);
            				// 将词库存在的词加入下级遍历层
            				nextLevel.add(convert);
            			}
            		}
            		// 将i位置上的字符改回去
            		word[i] = old;
            	}
    		}
	      	if(esist) {
	      		return true;
	      	}
	      	else {
	      		HashSet<String> tmp = curLevel;
	      		curLevel = nextLevel;
	      		nextLevel = tmp;
	      		// java 自动回收机制
	      		nextLevel.clear();
	      	}
    		
    		
    	}
    	
    	return false;
    }
    
    
    // 递归形式来反向遍历图， 找到一个路径后加入到ans。 ans会储存所有路径
    public static void dfs(String word, String begin) {
    	path.addFirst(word);
    	if(word.equals(begin)) {
    		ans.add(new ArrayList<>(path));
    	} else if(graph.containsKey(word)){
    		for(String str : graph.get(word)) {
    			dfs(str, begin);
    		}
    	}
    	// java 回收机制
    	path.removeFirst();
    }
}
