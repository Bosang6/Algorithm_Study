package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DAG_SP_algoritm {
	
	
	/* DFS实现拓扑排序，不需要牵扯入度,因为DFS会一直递归到入度最大的节点，入栈。此时栈底中保持入度较大的节点
	 * 
	 * */
	public static void topologicalSort(int v, boolean[] visited, Stack<Integer> stack, ArrayList<ArrayList<EdgeDAG>> adjList) {
		visited[v] = true;
		
		for(EdgeDAG edge : adjList.get(v)) {
			topologicalSort(edge.to, visited, stack, adjList);
		}
		
		stack.push(v);
	}
	
	public static int[] DAG_SP(GraphDAGSP graph, int s) {
		
		int[] dist = new int[graph.V + 1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[s] = 0;
		
		boolean[] visited = new boolean[graph.V + 1];
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 1; i <= graph.V; i++) {
			topologicalSort(i, visited, stack, graph.adjList);
		}
		
		while(!stack.isEmpty()) {
			int v = stack.pop();
			
			if(dist[v] != Integer.MAX_VALUE) {
				for(EdgeDAG edge : graph.adjList.get(v)) {
					if(dist[edge.to] > dist[v] + edge.weight) {
						dist[edge.to] = dist[v] + edge.weight;
					}
				}
			}
		}
		
		return dist;
	}

	public static void main(String[] args) {
	
		GraphDAGSP graph = new GraphDAGSP(5);
		
		graph.addEdge(1, 2, 3);
		graph.addEdge(1, 5, 1);
		graph.addEdge(1, 3, 7);
		graph.addEdge(1, 4, 10);
		graph.addEdge(5, 2, 1);
		graph.addEdge(3, 2, 8);
		graph.addEdge(4, 3, -4);
		
		int[] minDist = DAG_SP(graph, 1);
		
		for(int i = 1; i <= 5; i++) {
			System.out.println("节点: 1 -> 节点: " + i + " 的最短距离为:" + minDist[i]);
		}

	}

}

class GraphDAGSP{
	int V; // 节点数
	public ArrayList<ArrayList<EdgeDAG>> adjList = new ArrayList<>(); // 邻接表
	
	public GraphDAGSP(int v) {
		V = v;
		
		for(int i = 0; i <= V; i++) // 在数组中创建几个节点用于存储Edge边
			adjList.add(new ArrayList<>());
	}
	
	public void addEdge(int from, int to, int weight) {
		adjList.get(from).add(new EdgeDAG(to, weight));
	}
	
}

class EdgeDAG{
	int to;
	int weight;
	
	public EdgeDAG(int to, int w) {
		this.to = to;
		weight = w;
	}
}
