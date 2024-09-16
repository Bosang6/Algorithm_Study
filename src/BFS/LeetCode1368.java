package BFS;

import java.util.ArrayDeque;

// https://leetcode.cn/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/

public class LeetCode1368 {
	
	
    public int minCost(int[][] grid) {
    	
    	int[][] move = {{}, {0, 1}, {0, -1}, {1, 0}, {-1 ,0}};
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
    		
    		if(x == n - 1 && y == m - 1) {
    			return distance[x][y];
    		}
    		
    		for(int i = 1; i < 5; i++) {
    			int nx = x + move[i][0];
    			int ny = y + move[i][1];
    			
    			
    			/*
    			if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
    				if(grid[x][y] != i) {
    					if(distance[nx][ny] > distance[x][y] + 1) {
    						distance[nx][ny] = distance[x][y] + 1;
    						deque.addLast(new int[] {nx, ny});
    					}
    				}
    				else {
    					if(distance[nx][ny] > distance[x][y]) {
    						distance[nx][ny] = distance[x][y];
    						deque.addFirst(new int[] {nx, ny});
    					}
    				}
    			}
    			*/
    			
    			int weight = grid[x][y] == i ? 0 : 1;
    			if(nx >= 0 && ny >= 0 && nx < n && ny < m && distance[nx][ny] > distance[x][y] + weight) {
    				distance[nx][ny] = distance[x][y] + weight;
    				if(weight == 0) {
    					deque.addFirst(new int[] {nx, ny});
    				}
    				else {
    					deque.addLast(new int[] {nx, ny});
    				}
    			}
    		}
    	}
    	
    	return -1;
    }
}
