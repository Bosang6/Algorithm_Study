package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		
		graph.nodeInsert(node1);
		graph.nodeInsert(node2);
		graph.nodeInsert(node3);
		graph.nodeInsert(node4);
		graph.nodeInsert(node5);
		
		graph.edgeConnectUndirect(node1, node2);
		graph.edgeConnectUndirect(node3, node2);
		graph.edgeConnectUndirect(node4, node2);
		graph.edgeConnectUndirect(node5, node2);
		
		BFS(node2);
	}
	
	public static void BFS(Node node) {
		if(node == null) {
			return;
		}
		
		Queue<Node> queue = new LinkedList(); 
		HashSet<Node> set = new HashSet<>();
		
		queue.add(node);
		set.add(node);
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			
			for(Node next : cur.nexts) {
				if(!set.contains(next)) {
					set.add(next);
					queue.add(next);
				}
			}	
		}
	}
}
