package algoritmo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class YouXiangChuRuDuXuLie {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入定点数和边数: ");
		String inputData = scanner.nextLine();
		int v;
		int e;
		char[] input = inputData.toCharArray();
		v = Character.getNumericValue(input[0]);
		e = Character.getNumericValue(input[2]);
		
		matrixMethod(v,e);
		
		/*
		Graph graph = new Graph();
		
		for(int i = 0; i < v; i++) {
			graph.addNode(new Node());
		}
		
		String end = "0 0";
		String inputEdge = "";
		
		System.out.print("From to: ");
		inputEdge = scanner.nextLine();
		while(!end.equals(inputEdge)) {
			char[] edge = inputEdge.toCharArray();
			
			int edgeFrom = Character.getNumericValue(edge[0]);
			int edgeTo = Character.getNumericValue(edge[2]);
			
			Node from = graph.graph.get(edgeFrom);
			Node to = graph.graph.get(edgeTo);
			
			graph.addEdge(from, to);
			System.out.print("From to: ");
			inputEdge = scanner.nextLine();
		}
		
		for(int i = 1; i <= v; i++) {
			System.out.print(graph.graph.get(i).out + "   ");
		}
		System.out.println();
		for(int i = 1; i <= v; i++) {
			System.out.print(graph.graph.get(i).in + "   ");
		}
		System.out.println();
		for(int i = 1; i <= v; i++) {
			System.out.print(i + "   ");
		}
		*/
		

	}
	
	public static void matrixMethod(int v, int e) {
		int[][] matrix = new int[v][v];
		for(int i = 0; i < v; i++) {
			for(int j = 0; j < v; j++) {
				matrix[i][j] = 0;
			}
		}
		Scanner scanner = new Scanner(System.in);
		
		String end = "0 0";
		String inputEdge = "";
		
		System.out.print("From to: ");
		inputEdge = scanner.nextLine();
		while(!end.equals(inputEdge)) {
			char[] edge = inputEdge.toCharArray();
			
			int edgeFrom = Character.getNumericValue(edge[0]);
			int edgeTo = Character.getNumericValue(edge[2]);
			
			matrix[edgeFrom - 1][edgeTo - 1] = 1;
			
			System.out.print("From to: ");
			inputEdge = scanner.nextLine();
		}
		
		for(int i = 0; i < v; i++) {
			int out = 0;
			for(int j = 0; j < v; j++) {
				out += matrix[i][j]; 
			}
			System.out.print(out + " ");
		}
		
		System.out.println();
		
		for(int i = 0; i < v; i++) {
			int in = 0;
			for(int j = 0; j < v; j++) {
				in += matrix[j][i]; 
			}
			System.out.print(in + " ");
		}
		System.out.println();
		
		
	}
	
	public static class Node{
		int out;
		int in;
		ArrayList<Node> nexts;
		ArrayList<Edge> edges;
		
		public Node() {
			this.in = 0;
			this.out = 0;
			nexts = new ArrayList<>();
			edges = new ArrayList<>();
		}
	}
	
	public static class Edge{
		Node from;
		Node to;
		
		public Edge(Node from, Node to) {
			this.from = from;
			this.to = to;
		}
	}
	
	public static class Graph{
		HashMap<Integer,Node> graph;
		HashSet<Edge> edges;
		int maxIndex;
		
		public Graph() {
			maxIndex = 0;
			graph = new HashMap<>();
			edges = new HashSet<>();
		}
		
		public void addNode(Node node) {
			graph.put(++maxIndex, node);
		}
		
		public void addEdge(Node from, Node to) {
			from.out++;
			to.in++;
			edges.add(new Edge(from,to));
		}
	}
	

}
