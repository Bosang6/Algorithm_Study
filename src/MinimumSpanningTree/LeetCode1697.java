package MinimumSpanningTree;

import java.util.Arrays;

public class LeetCode1697 {
	
	public static int NMAX = 100001;
	
	public static int[][] questions = new int[NMAX][4];
	
	public static int[] father = new int[NMAX];
	
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
    	
    	Arrays.sort(edgeList,(a, b) -> a[2] - b[2]);
    	
    	int k = queries.length;
    	int e = edgeList.length;
    	
    	for(int i = 0; i < k; i++) {
    		questions[i][0] = queries[i][0];
    		questions[i][1] = queries[i][1];
    		questions[i][2] = queries[i][2];
    		questions[i][3] = i;
    	}
    	
    	Arrays.sort(questions, 0, k, (a,b) -> a[2] - b[2]);
    	
    	build(n);
    	
    	boolean[] ans = new boolean[k];
    	
    	for(int i = 0, j = 0; i < k; i++) {
    		for(; j < e && edgeList[j][2] < questions[i][2]; j++) {
    			union(edgeList[j][0], edgeList[j][1]);
    		}
    		ans[questions[i][3]] = isSameSet(questions[i][0], questions[i][1]);
    	}
    	
    	return ans;
    }
    
    public static void build(int n) {
    	for(int i = 0; i < n; i++) {
    		father[i] = i;
    	}
    }
    
    public static void union(int x, int y) {
    	int fx = find(x);
    	int fy = find(y);
    	
    	if(fx != fy) {
    		father[fx] = fy;
    	}
    }
    
    public static int find(int i) {
    	if(father[i] != i) {
    		father[i] = find(father[i]);
    	}
    	
    	return father[i];
    }
    
    public static boolean isSameSet(int x, int y) {
    	return find(x) == find(y);
    }
    
    
}
