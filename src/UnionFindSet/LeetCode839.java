package UnionFindSet;

public class LeetCode839 {
	
	public static int NMAX = 301;
	
	public static int[] father = new int[NMAX];
	
	public static int sets;

	public int numSimilarGroups(String[] strs) {
		int dim = strs.length;
		build(dim);
		int strLength;
		for(int i = 0; i < dim; i++) {
			strLength = strs[i].length();
			for(int j =  i + 1; j < dim; j++) {
				if(find(i) != find(j)) {
					int diff = 0;
					for(int k = 0; k < strLength && diff < 3; k++) {
						if(strs[i].charAt(k) != strs[j].charAt(k)) {
							diff++;
						}
					}
					if(diff == 0 || diff == 2) {
						union(i,j);
					}
				}
			}
		}
		
		return sets;
		
	}
	
	public static void build(int dim) {
		for(int i = 0; i < dim; i++) {
			father[i] = i;
		}
		sets = dim;
	}
	
	public static void union(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if(fx != fy) {
			father[fx] = fy;
			sets--;
		}
	}
	
	public static int find(int x) {
		if(father[x] != x) {
			father[x] = find(father[x]);
		}
		return father[x];
	}
	
	

}
