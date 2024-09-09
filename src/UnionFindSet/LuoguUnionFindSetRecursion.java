package UnionFindSet;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class LuoguUnionFindSetRecursion {
	
	/* https://www.luogu.com.cn/problem/P3367
	 */
	
	public static int[] father = new int[10001];
	public static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			build();
			in.nextToken();
			int m = (int) in.nval;
			for (int i = 0; i < m; i++) {
				in.nextToken();
				int z = (int) in.nval;
				in.nextToken();
				int x = (int) in.nval;
				in.nextToken();
				int y = (int) in.nval;
				if (z == 1) {
					union(x, y);
				} else {
					out.println(isSameSet(x, y) ? "Y" : "N");
				}
			}
		}
		out.flush();
		out.close();
		br.close();
	}
	
	public static void build() {
		for(int i = 0; i < n; i++) {
			father[i] = i;
		}
	}
	
	public static boolean isSameSet(int x, int y) {
		return find(x) == find(y);
	}
	
	public static int find(int x) {	
		if(x != father[x]) {
			father[x] = find(father[x]);
		}
		
		return father[x];
	}
	
	public static void union(int x, int y) {
		father[find(x)] = find(y);
	}
}
