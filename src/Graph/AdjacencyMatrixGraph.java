package Graph;

public class AdjacencyMatrixGraph {
	
	private int[][] adjacencyMatrix;
	private int nodes;
	
	public AdjacencyMatrixGraph(int n) {
		adjacencyMatrix = new int[10][10];
		nodes = n;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				adjacencyMatrix[i][j] = Integer.MAX_VALUE;
			}
		}
		addNode();
	}
	
	public void addNode() {
		for(int i = 0; i <= nodes; i++) {
			adjacencyMatrix[i][i] = 0;
		}
	}
	
	public void addEdge(int u, int v, int w) {
		adjacencyMatrix[u][v] = w;
	}
	
	public void trasversal() {
		for(int i = 1; i <= nodes; i++) {
			for(int j = 0; j <= nodes; j++) {
				if(adjacencyMatrix[i][j] != Integer.MAX_VALUE && adjacencyMatrix[i][j] != 0) {
					System.out.println("Edge:(" + i + " -> " + j +")" + " weight: " + adjacencyMatrix[i][j]);
				}
			}
		}
	}

	public static void main(String[] args) {
		int n = 4;
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(n);
		
		graph.addEdge(1,3,5);
		graph.addEdge(1,4,7);
		graph.addEdge(1,2,9);
		graph.addEdge(2,4,8);
		graph.addEdge(4,3,6);
		
		graph.trasversal();

	}

}
