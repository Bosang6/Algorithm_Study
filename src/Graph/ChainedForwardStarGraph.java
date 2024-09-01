package Graph;

import java.util.Arrays;

public class ChainedForwardStarGraph {
	
	public static int[] head = new int[11];
	
	public static int[] next = new int[11];
	
	public static int[] to = new int[11];
	
	public static int[] weight = new int[11]; // 
	
	public static int cnt;
	
	public ChainedForwardStarGraph() {
		cnt = 1;
		iniziatialGraph();
	}
	
	public static void iniziatialGraph() {
		Arrays.fill(head, 0);
	}
	
	public static void addEdge(int u, int v, int w) {
		next[cnt] = head[u];
		head[u] = cnt;
		to[cnt] = v;
		weight[cnt++] = w;
	}
	
	public static void traversal(int n) {
		System.out.println("遍历图：");
		for(int i = 1; i <= n; i++) {
			for(int ei = head[i]; ei > 0; ei = next[ei]) {
				System.out.println("Edge:(" + i + " -> " + to[ei] +")" + " weight: " + weight[ei]);
			}
		}
	}

	public static void main(String[] args) {
		
		ChainedForwardStarGraph graph = new ChainedForwardStarGraph();
		
		graph.addEdge(1, 3, 5);
		graph.addEdge(1, 4, 7);
		graph.addEdge(1, 2, 9);
		graph.addEdge(4, 3, 6);
		graph.addEdge(2, 4, 8);
		
		graph.traversal(4);
	}
	

}
