package MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class PrimHeapImprove {
	
	public static int NMAX = 5001;
	
	public static int MMAX = 400001;
	
	public static int[] head = new int[NMAX];
	
	public static int[] next = new int[MMAX];
	
	public static int[] to = new int[MMAX];
	
	public static int[] weigth = new int[MMAX];
	
	
	// where[i] = -1  说明节点还没入过heap
	// where[i] = -2  说明节点已经如果heap
	// where[i] > 0   代表i节点在heap的的位置（数组实现heap）
 	public static int[] where = new int[NMAX];
	
 	// heap的每一个节点为一个int[2]结构的数组， int[0] 为 to节点， int[1]为 权重
	public static int[][] heap = new int[NMAX][2];
	
	public static int heapSize;
	
	public static int cnt;
	
	public static int n;
	
	public static int m;
	
	public static int ansCnt; // 用于检查最小生成树的节点个数是否等于所有节点数

	public static int u;

	public static int w;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(System.out);

		while(in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			m = (int) in.nval;
			build();
			for(int i = 0, u, v, w; i < m; i++) {
				in.nextToken();
				u = (int) in.nval;
				in.nextToken();
				v = (int) in.nval;
				in.nextToken();
				w = (int) in.nval;
				
				addEdge(u,v,w);
				addEdge(v,u,w);
			}
			int ans = prim();
			out.print(ansCnt == n ? ans : "orz");
		}
		
		out.flush();
		br.close();
		out.close();
	}
	
	
	public static int prim() {
		ansCnt = 1;
		where[1] = -2;
		
		for(int ei = head[1]; ei > 0; ei = next[ei]) {
			addOrUpdateOrIgnore(ei);
		}
		
		int ans = 0;
		while(!isEmpty()) {
			pop();
			ans += w;
			for(int ei = head[u]; ei > 0; ei = next[ei]) {
				addOrUpdateOrIgnore(ei);
			}
		}
		
		return ans;
	}
	
	public static boolean isEmpty() {
		return heapSize == 0;
	}
	
	public static void addOrUpdateOrIgnore(int ei) {
		int v = to[ei];
		int w = weigth[ei];
		
		if(where[v] == -1) { // Add
			heap[heapSize][0] = v;
			heap[heapSize][1] = w;
			where[v] = heapSize++;
			heapInsert(where[v]);
		}
		else if(where[v] >= 0) { // Update
			heap[where[v]][1] = Math.min(heap[where[v]][1], w);
			heapInsert(where[v]);
		}
	}
	
	public static void heapInsert(int i) {
		while(heap[i][1] < heap[(i - 1) / 2][1]) {
			swap(i, (i - 1) / 2);
			i = (i - 1) / 2;
		}
	}
	
	public static void pop() {
		u = heap[0][0];
		w = heap[0][1];
		swap(0, --heapSize);
		heapify(0);
		where[u] = -2;
		ansCnt++;
	}
	
	public static void heapify(int i) {
		int l = 1;
		while(l < heapSize) {
			int best = l + 1 < heapSize && heap[l + 1][1] < heap[l][1] ? l + 1 : l;
			best = heap[best][1] < heap[i][1] ? best : i; 
			if(best == i) {
				break;
			}
			swap(best,i);
			i = best;
			l = i * 2 + 1;
		}
	}
	
	public static void swap(int i, int j) {
		int a = heap[i][0];
		int b = heap[j][0];
		where[a] = j;
		where[b] = i;
		
		
		int[] tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}
	
	public static void build() {
		ansCnt = 0;
		
		// 链式前向星
		cnt = 1;
		Arrays.fill(head, 1, n + 1, 0);
		
		// heap
		heapSize = 0;
		Arrays.fill(where, 1, n + 1, -1);
	}
	
	public static void addEdge(int u, int v, int w) {
		next[cnt] = head[u];	//新增的线指向节点的头线
		head[u] = cnt;	// u节点指向新的线         u --> cnt(新的边编号) --> 原先的边
		to[cnt] = v;
		weigth[cnt++] = w;
	}

}
