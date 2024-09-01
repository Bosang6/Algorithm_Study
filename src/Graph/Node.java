package Graph;

import java.util.HashMap;
import java.util.HashSet;

public class Node {
	int value;
	int in;
	int out;
	public HashSet<Node> nexts;
	public HashSet<Edge> edges;
	
	public Node(int value) {
		nexts = new HashSet<>();
		edges = new HashSet<>();
		this.value = value;
		this.in = 0;
		this.out = 0;
	}
}
