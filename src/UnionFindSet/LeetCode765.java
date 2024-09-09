package UnionFindSet;

/*
 *  情侣牵手问题
 *  
 *  (a - 1) + (b - 1) + (c - 1) = a + b + c - 3;
 *  
 *  a+b+c ==> 所有集合内的情侣对数
 *  
 */

class LeetCode765 {

    public static int NMAX = 31;
	
	public static int[] father = new int[NMAX];
	
	public static int sets;

    public int minSwapsCouples(int[] row) {
        int n = row.length;

        build(n / 2);

        for(int i = 0; i < n; i += 2) {
			union(row[i] / 2 , row[i + 1] / 2);
		}

        return n / 2 - sets;
    }

    public static void build(int n) {
		for(int i = 0; i < n; i++) {
			father[i] = i;
		}
        sets = n;
	}
	
	public static int find(int x) {
		if(father[x] != x) {
			father[x] = find(father[x]);
		}
		
		return father[x];
	}
	
	public static void union(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		
		if(fx != fy) {
			father[fx] = fy;
			sets--;
		}
	}
}