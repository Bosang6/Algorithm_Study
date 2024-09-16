package BFS;

import java.util.ArrayDeque;

// https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner/solutions/

public class BFS01 {
	
	public static int[] move = {-1, 0, 1, 0, -1};
	
    public int minimumObstacles(int[][] grid) {
    	int n = grid.length;
    	int m = grid[0].length;
    	
    	int[][] distance = new int[n][m];
    	
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			distance[i][j] = Integer.MAX_VALUE;
    		}
    	}
    	
    	distance[0][0] = 0;
    	
    	ArrayDeque<int[]> deque = new ArrayDeque<>();
    	
    	deque.addFirst(new int[] {0,0});
    	
    	while(!deque.isEmpty()) {
    		int[] cur = deque.pollFirst();
    		
    		int x = cur[0];
    		int y = cur[1];
    		
    		if(x == n - 1 && y == m -1) {
    			return distance[x][y];
    		}
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = x + move[i];
    			int ny = y + move[i + 1];
    			
    			if(nx >= 0 && nx < n && ny >= 0 && ny < m && distance[nx][ny] > distance[x][y] + grid[nx][ny]) {
    				distance[nx][ny] = distance[x][y] + grid[nx][ny];
    				if(grid[nx][ny] == 1) {
    					deque.addLast(new int[] {nx, ny});
    				}
    				else {
    					deque.addFirst(new int[] {nx, ny});
    				}
    			}
    		}
    	}
    	
    	return -1;
    }
}
