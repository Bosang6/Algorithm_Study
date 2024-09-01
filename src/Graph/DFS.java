package Graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFS {

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
		graph.edgeConnectUndirect(node4, node3);
		graph.edgeConnectUndirect(node5, node1);
		graph.edgeConnectUndirect(node2, node5);
		
		DFS(node1);

	}
	
	public static void DFS(Node node) {
		Stack<Node> stack = new Stack<>();
		Set<Node> set = new HashSet<>();
		
		stack.push(node);
		set.add(node);
		System.out.println(node.value);
		
		while(!stack.isEmpty()) {
			Node cur = stack.pop();
			for(Node next : cur.nexts) {
				if(!set.contains(next)) {
					stack.push(cur);
					stack.push(next);
					set.add(next);
					System.out.println(next.value);
					break;
				}
			}
		}
	}

}
