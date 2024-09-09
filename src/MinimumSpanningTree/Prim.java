package MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));	

		while(in.nextToken() != StreamTokenizer.TT_EOF) {
			ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
			int nodes = (int) in.nval;
			for(int i = 0; i <= nodes; i++) {
				graph.add(new ArrayList<>());
			}
			
			in.nextToken();
			int e = (int) in.nval;
			for(int i = 0, u, v, w; i < e; i++) {
				in.nextToken();
				u = (int) in.nval;
				in.nextToken();
				v = (int) in.nval;
				in.nextToken();
				w = (int) in.nval;
				
				graph.get(u).add(new int[] {v, w});
				graph.get(v).add(new int[] {u, w});
			}

			PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
			//将第一个节点的所有边加入小根堆
			for(int[] edge : graph.get(1)) {
				heap.add(edge);
			}
			
			boolean[] set = new boolean[nodes + 1];
			int ansNodes = 1;
			set[1] = true;
			int ansWeigth = 0;
			
			while(!heap.isEmpty()) {
				int[] cur = heap.poll();
				
				int next = cur[0];
				int w = cur[1];
				
				if(!set[next]) {
					ansNodes++;
					set[next] = true;
					ansWeigth += w;
					for(int[] edge : graph.get(next)) {
						heap.add(edge);
					}
				}
			}
			
			out.print(ansNodes == nodes ? ansWeigth : "orz");

		}
		br.close();
		out.flush();
		out.close();
		
	}

}
