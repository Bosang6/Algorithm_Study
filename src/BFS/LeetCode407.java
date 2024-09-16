package BFS;

import java.util.PriorityQueue;

//https://leetcode.cn/problems/trapping-rain-water-ii/description/ 

// BFS + 优先级队列（PriorityQueue）

public class LeetCode407 {
	
    public int trapRainWater(int[][] heightMap) {
    	int[] move = {-1, 0, 1, 0, -1};
    	
    	int n = heightMap.length;
    	int m = heightMap[0].length;
    	
    	boolean[][] visited = new boolean[n][m];
    	
    	PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[2] - b[2]);
    	
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			if(j == 0 || i == 0 || i == n - 1 || j == m - 1) {
    				queue.add(new int[] {i, j, heightMap[i][j]});
    				visited[i][j] = true;
    			}
    			else {
    				visited[i][j] = false;
    			}
    		}
    	}
    	
    	int ans = 0;
    	
    	while(!queue.isEmpty()) {
    		int[] cur = queue.poll(); // 第一次循环时，从边界中找到最矮格子的位置 
    		int x = cur[0];
    		int y = cur[1];
    		int w = cur[2]; 
    		ans += w - heightMap[x][y];
    		for(int i = 0; i < 4; i++) {
    			int nx = x + move[i];
    			int ny = y + move[i + 1];
    			
    			if(nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
    				// 下一个点的高度 与 当前点的水线位 PK， 较高者获胜。
    				// 当想要获取下一格的容量时， 只需要查看水线位 与 格子高度， 相减得出容量
    				queue.add(new int[] {nx, ny, Math.max(w, heightMap[nx][ny])});
    				visited[nx][ny] = true;
    			}
    		}
    	}

    	return ans;
    }

}
