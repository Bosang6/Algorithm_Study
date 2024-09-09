package MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class LuoguP2330 {
	
	public static int NMAX = 301;
	
	public static int MMAX = 8001;
	
	public static int[] father = new int[NMAX];
	
	public static int[][] edges = new int[MMAX][3];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
		while(in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			in.nextToken();
			int m = (int) in.nval;
			
			build(n);
			
			for(int i = 0; i < m; i++) {
				in.nextToken();
				int u = (int) in.nval;
				in.nextToken();
				int v = (int) in.nval;
				in.nextToken();
				int w = (int) in.nval;
				
				edges[i][0] = u;
				edges[i][1] = v;
				edges[i][2] = w;
			}
			
			Arrays.sort(edges, 0, m, (a, b) -> a[2] - b[2]);
			
			int ans = 0;
			int cnt = 0;
			
			for(int[] edge : edges) {
				if(union(edge[0],edge[1])) {
					ans = Math.max(ans, edge[2]);
				}
				if(cnt == n - 1) {
					break;
				}
			}
			
			out.print(n - 1 + " " + ans);
		}
		
		out.flush();
		out.close();
		br.close();

	}
	
	public static void build(int n) {
		for(int i = 1; i <= n; i++) {
			father[i] = i;
		}
	}
	
	public static boolean union(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		
		if(fx != fy) {
			father[fx] = fy;
			return true;
		}
		
		return false;
	}
	
	public static int find(int i) {
		if(father[i] != i) {
			father[i] = find(father[i]);
		}
		
		return father[i];
	}

}
