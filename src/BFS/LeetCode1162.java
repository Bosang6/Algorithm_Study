package BFS;

// https://leetcode.cn/problems/as-far-from-land-as-possible/description/

public class LeetCode1162 {
	
	public static int NMAX = 101;
	
	public static int MMAX = 101;
	
	public static boolean[][] visited = new boolean[NMAX][MMAX];
	
	public static int[][] queue = new int[NMAX * MMAX][2];
	
	public static int[] move = {-1, 0, 1, 0, -1};
	
	// x + move[i] y + move[i + 1]
	// 从 0 -> 3 遍历，最后一个位置i不去
	// i = 0: (x - 1, y) 上
	// i = 1: (x, y + 1) 右
	// i = 2: (x + 1, y) 下
	// i = 3: (x, y - 1) 左
	
    public int maxDistance(int[][] grid) {
    	int n = grid.length;
    	int m = grid[0].length;
    	
    	int l = 0;
    	int r = 0;
    	
    	int seas = 0;
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			if(grid[i][j] == 1) {
    				visited[i][j] = true;
    				queue[r][0] = i;
    				queue[r++][1] = j;
    			} else {
    				visited[i][j] = false;
    				seas++;
    			}
    		}
    	}
    	
    	if(seas == 0 || seas == n*m) {
    		return -1;
    	}
    	
    	int level = 0;

    	while(l < r) {	
    		level++;
    		int k = r - l;
    		for(int i = 0; i < k; i++) {
	    		int x = queue[l][0];
	    		int y = queue[l][1];
	    		l++;
	    		for (int j = 0; j < 4; j++) {
	    		    int nx = x + move[j];
	    		    int ny = y + move[j + 1];
	    		    if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
	    		        queue[r][0] = nx;
	    		        queue[r++][1] = ny;
	    		        visited[nx][ny] = true;
	    		    }
	    		}
    		}
    	}
    	
    	return level - 1;
    }
    
}
