package Graph;

import java.util.ArrayList;

public class AdjacencyListGraph {
	
	private ArrayList<ArrayList<int[]>> adjacencyList;
	private int n;
	private int[] in;
	
	public AdjacencyListGraph(int n) {
		this.n = n;
		in = new int[n + 1];
		for(int i = 0; i <= n; i++) {
			in[i] = 0;
		}
		adjacencyList = new ArrayList<ArrayList<int[]>>();
		for(int i = 0; i <= n; i++) {
			adjacencyList.add(new ArrayList<>());
		}
	}
	
	public void addEdge(int from, int[] toWeight) {
		adjacencyList.get(from).add(toWeight);
		in[toWeight[0]]++;
	}
	
	public void addNode() {
		adjacencyList.add(new ArrayList<>());
		n++;
	}
	
	public void traversal() {
		for(int i = 1; i <= n; i++) {
			ArrayList<int[]> nexts = adjacencyList.get(i);
			for(int[] nextWeight : nexts) {
				System.out.println("Edge: (" + i + " -> " + nextWeight[0] + ") Weight: " + nextWeight[1]);
			}
		}
	}
	
	public int[] getIn() {
		return in;
	}
	
	public ArrayList<ArrayList<int[]>> getList(){
		return adjacencyList;
	}

	public static void main(String[] args) {
		int n = 4;
		AdjacencyListGraph graph = new AdjacencyListGraph(n);
		
		graph.addEdge(1,new int[]{3,5});
		graph.addEdge(1,new int[]{4,7});
		graph.addEdge(1,new int[]{2,9});
		graph.addEdge(2,new int[]{4,8});
		graph.addEdge(4,new int[]{3,6});
		
		graph.traversal();

	}

}
