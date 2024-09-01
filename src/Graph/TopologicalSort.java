package Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class TopologicalSort {
	
	public static void topologicalSort(AdjacencyListGraph graph) {
		int[] in = graph.getIn(); // 获取入度表
		int[] inDegree =  Arrays.copyOf(in, in.length); // 在移除一个节点时，修改to节点的入度，确保原入读表不变
		int[] queue = new int[100]; // 队列，r：用于输入，l：用于取出数据，cnt：用于保存拓扑顺序
		
		int l = 0;
		int r = 0;
		
		for(int i = 1; i < in.length; i++) { // 对入度表进行循环，找到入度为0的点，加入到队列中
			if(in[i] == 0) {
				queue[r++] = i;
			}
		}
		
		ArrayList<ArrayList<int[]>> graphList = graph.getList(); // 获取邻接表用于遍历邻居
		
		int cnt = 0; // 记录有几个节点已经完成排序
		while(l < r) { // l < r 说明队列的内容还没取完
			int cur = queue[l++]; // 取出一个入度为0的节点
			queue[cnt++] = cur; // 按顺序保存入度为0的节点
			ArrayList<int[]> nexts = graphList.get(cur); // 获取邻居
			for(int[] next : nexts) { // 对于每个邻居而言
				if(--inDegree[next[0]] == 0) { // 在移除from节点后，入度为0的话
					queue[r++] = next[0]; // 将该to节点加入队列
				}
			}
		}
		
		if(cnt == in.length - 1) { // 如果完成拓扑排序的节点数 等于 入度表的节点数， 说明完成拓扑排序，没有循环节点
			for(int i = 0; i < cnt; i++) {
				System.out.print(queue[i] + " ");
			}
			System.out.println();
		}
		else {
			System.out.println("拓扑排序不存在！");
		}
	}


	public static void main(String[] args) {
		int n = 4;
		AdjacencyListGraph graph = new AdjacencyListGraph(n);
		
		graph.addEdge(1,new int[]{3,5});
		graph.addEdge(1,new int[]{4,7});
		graph.addEdge(1,new int[]{2,9});
		graph.addEdge(2,new int[]{4,8});
		graph.addEdge(4,new int[]{3,6});
		
		topologicalSort(graph);
	}
	
	

}
