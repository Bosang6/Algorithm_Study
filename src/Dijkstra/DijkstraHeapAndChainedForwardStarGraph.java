package Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstraHeapAndChainedForwardStarGraph {
	
	// 邻接表法动态建图，现成堆操作
	
    public int networkDelayTime(int[][] times, int n, int k) {
    	
    	ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    	
    	for(int i = 0; i <= n; i++) {
    		graph.add(new ArrayList<>());
    	}
    	
    	for(int[] edge : times) {
    		graph.get(edge[0]).add(new int[] {edge[1],edge[2]});
    	}
    	
    	boolean[] visited = new boolean[n + 1];
    	
    	int[] distance = new int[n + 1];
    	Arrays.fill(distance, Integer.MAX_VALUE);
    	
    	PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]); 
    	
    	distance[k] = 0;
    	
    	heap.add(new int[] {k, 0});
    	
    	while(!heap.isEmpty()) {
    		int u = heap.poll()[0];
    
    		if(visited[u]) {
    			continue;
    		}

    		visited[u] = true;
    		
    		for(int[] edge : graph.get(u)) {
    			int v = edge[0];
    			int w = edge[1];
    			
    			if(!visited[v] && distance[u] + w < distance[v]) {
    				distance[v] = distance[u] + w;
    				heap.add(new int[] {v, distance[v]});
    			}
    		}
    	}
    	
    	int ans = Integer.MIN_VALUE;
    	
    	for(int i = 1; i <= n; i++) {
    		if(distance[i] == Integer.MAX_VALUE) {
    			return -1;
    		}
    		ans = Math.max(ans, distance[i]);
    	}
    	
    	return ans;

    }
   
    
    
    
    
    // 链式前向星建图， 手写堆 -> 每个节点只进出一次堆， O(ElogV)
    
    
    //链式前向星
    public static int NMAX = 101;
    
    public static int EMAX = 6001;
    
    public static int cnt; // 边的编号
    
    public static int[] head = new int[NMAX]; // 边的序列
    
    public static int[] next = new int[EMAX]; // 每一条边的下一条边
    
    public static int[] to = new int[EMAX]; // 每条边指向的节点
    
    public static int[] weight = new int[EMAX]; // 每条边的权重
    
    //手写堆
    
    public static int[] heap = new int[NMAX];
    
    public static int[] where = new int[NMAX];
    
    public static int[] distance = new int[NMAX];
    
    public static int heapSize;
    
    public int networkDelayTime1(int[][] times, int n, int k) {
    	build(n);
    	for(int[] edge : times) {
    		addEdge(edge);
    	}
    	
    	addOrUpdateOrIgnore(k, 0);
    	
    	while(heapSize > 0) {
    		int u = pop();
    		for(int ei = head[u]; ei > 0; ei = next[ei]) {
    			addOrUpdateOrIgnore(to[ei], distance[u] + weight[ei]);
    		}
    	}
    	
    	int ans = Integer.MIN_VALUE;
    	
    	for(int i = 1; i <= n; i++) {
    		if(distance[i] == Integer.MAX_VALUE) {
    			return -1;
    		}
    		ans = Math.max(ans, distance[i]);
    	}
    	
    	return ans;
    }
    
    public static void build(int n) {
    	heapSize = 0;
    	cnt = 1;
    	
    	Arrays.fill(where, 1 , n + 1, -1); // -1 代表没有任何的节点加入到heap中
    	Arrays.fill(distance, 1, n + 1, Integer.MAX_VALUE);
    	Arrays.fill(head, 1, n + 1, 0);
    }
    
    public static void addEdge(int[] edge) {
    	int u = edge[0];
    	int v = edge[1];
    	int w = edge[2];
    	next[cnt] = head[u];
    	to[cnt] = v;
    	weight[cnt] = w;
    	head[u] = cnt++;
    }
    
    public static void addOrUpdateOrIgnore(int s, int c) {
    	if(where[s] == -1) { // 在堆中加入节点
    		heap[heapSize] = s;
    		distance[s] = c;
    		where[s] = heapSize++;
    		heapInsert(where[s]);
    	}
    	if(where[s] >= 0) { // 在堆中更新节点
    		distance[s] = Math.min(distance[s], c);
    		heapInsert(where[s]);
    	}
    }
    
    
    public static void heapInsert(int i) {
    	while(distance[heap[i]] < distance[heap[(i - 1) / 2]]) {
    		swap(i, (i - 1) / 2);
    		i = (i - 1) / 2;
    	}
    }
    
    public static void swap(int index1, int index2) {
    	int tmp = heap[index1];
    	heap[index1] = heap[index2];
    	heap[index2] = tmp;
    	where[heap[index1]] = index1;
    	where[heap[index2]] = index2;
    }
    
    public static void heapify(int i) {
    	int l = 1;
    	while(l < heapSize) {
	    	int best = l + 1 < heapSize && distance[l + 1] < distance[l] ? l + 1 : l;
	    	best = distance[heap[i]] > distance[heap[best]] ? best : i;
	    	
	    	if(best == i) {
	    		break;
	    	}
	    	swap(i, best);
	    	i = best;
	    	l = i * 2 + 1;
    	}
    }
    
    public static int pop() {
    	int ans = heap[0];
    	swap(0, --heapSize);
    	heapify(0);
    	where[ans] = -2;
    	return ans;
    }
    
}
