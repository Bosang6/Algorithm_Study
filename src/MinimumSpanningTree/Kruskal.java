package MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Kruskal {
	
	public static int NMAX = 5001;
	
	public static int EMAX = 200001;
	
	public static int[] father = new int[NMAX];
	
	public static int[][] edges = new int[EMAX][3];
	
	public static int nodes;
	
	public static int e;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while(in.nextToken() != StreamTokenizer.TT_EOF) {
			nodes = (int) in.nval;
			in.nextToken();
			e = (int) in.nval;
			build();
			
			for(int i = 0; i < e; i++) {
				in.nextToken();
				edges[i][0] = (int) in.nval;
				in.nextToken();
				edges[i][1] = (int) in.nval;
				in.nextToken();
				edges[i][2] = (int) in.nval;
			}
			System.out.println(kruskal());
		}

	}
	
	public static String kruskal() {
		Arrays.sort(edges, (a , b) -> a[2] - b[2]);
		int edgeCounter = 0;
		int minWeigth = 0;
		for(int[] edge : edges) {
			int from = find(edge[0]);
			int to = find(edge[1]);
			
			if(union(from ,to)) {
				edgeCounter++;
				minWeigth += edge[2];
			}
		}
		
		return edgeCounter == nodes - 1 ? minWeigth + "" : "orz";
	}
	
	public static void build() {
		for(int i = 0; i < nodes; i++) {
			father[i] = i;
		}
	}
	
	public static int find(int i) {
		if(father[i] != i) {
			father[i] = find(father[i]);
		}
		
		return father[i];
	}
	
	public static boolean union(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		
		if(fx == fy) {
			return false;
		}
		else {
			father[fx] = fy;
			return true;
		}
	}

}
