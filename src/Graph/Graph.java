package Graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	public HashMap<Integer, Node> nodes;
	public HashSet<Edge> edges;
	private int nodeTot;
	
	public Graph() {
		this.nodes = new HashMap<>();
		this.edges = new HashSet<>();
		this.nodeTot = 0;
	}
	
	public void nodeInsert(Node node) {
		nodes.put(++nodeTot, node);
	}
	
	public void edgeConnectUndirect(Node from, Node to) {
		Edge fromTo = new Edge(from,to,0);
		Edge toFrom = new Edge(to,from,0);
		
		edges.add(fromTo);
		edges.add(toFrom);
		
		from.nexts.add(to);
		to.nexts.add(from);
		from.edges.add(fromTo);
		to.edges.add(toFrom);
		
		from.out++;
		from.in++;
		to.in++;
		to.out++;
	}
	
	public void edgeConnectDirect(Node from, Node to) {
		Edge fromTo = new Edge(from,to,0);
		edges.add(new Edge(from,to,0));
		
		from.edges.add(fromTo);
		
		from.out++;
		to.in++;
	}
}
