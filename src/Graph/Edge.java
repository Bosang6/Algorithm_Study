package Graph;

public class Edge {
	private Node from;
	private Node to;
	private int weight;
	
	public Edge(Node from, Node to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}
