package UnionFindSet;

import java.util.HashMap;

/* 移除石头问题
 * https://leetcode.cn/problems/most-stones-removed-with-same-row-or-column/
 * 
 * 
 * 
 * */

public class LeetCode947 {
	
	public static int NMAX = 1001;
	
	public static int[] father = new int[NMAX];
	
	public static int sets;
	
	public static HashMap<Integer,Integer> firstCol = new HashMap<>();
	public static HashMap<Integer,Integer> firstRow = new HashMap<>();

	public int removeStones(int[][] stones) {
		int n = stones.length;
		build(n);
		for(int i = 0; i < n; i++) {
			int r = stones[i][0];
			int c = stones[i][1];
			if(!firstRow.containsKey(r)) {
				firstRow.put(r, i);
			}
			else {
				union(firstRow.get(r), i);
			}
			
			if(!firstCol.containsKey(c)) {
				firstCol.put(c, i);
			}
			else {
				union(firstCol.get(c), i);
			}
			
		}
		
		return n - sets;
    }
	
	public static void build(int n) {
		firstCol.clear();
		firstRow.clear();
		for(int i = 0; i < n; i++) {
			father[i] = i;
		}
		
		sets = n;
	}
	
	public static void union(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		
		if(fx != fy) {
			father[fx] = fy;
			sets--;
		}
	}
	
	public static int find(int i) {
		if(father[i] != i) {
			father[i] = find(father[i]);
		}
		return father[i];
	}

}
