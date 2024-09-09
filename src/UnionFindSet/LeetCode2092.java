package UnionFindSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode2092 {
	
	public static int NMAX = 100001;
	
	public static int[] father = new int[NMAX];
	
	public static boolean[] knows = new boolean[NMAX];
    
    public static void toTiscretize(int n) {
    	for(int i = 0; i < n; i++) {
    		if(knows[find(i)] == false) {
    			father[i] = i;
    		}
    	}
    }
    
    public static void Sort(int[][] meetings) {
    	int end = meetings.length;
    	for(int i = 0; i < end - 1; i++) {
    		for(int j = 0; j < end - 1; j++) {
    			if(meetings[j][2] > meetings[j + 1][2]) {
    				swap(meetings, j, j + 1);
    			}
    		}
    	}
    }
    
    public static void swap(int[][] matrix, int index1, int index2) {
    	int[] tmp = matrix[index1];
    	matrix[index1] = matrix[index2];
    	matrix[index2] = tmp;
    }
    
    public static void build(int n, int firstPerson) {
    	for(int i = 0; i < n; i++) {
    		father[i] = i;
    		knows[i] = false;
    	}
    	
    	father[firstPerson] = 0;
    	
    	knows[0] = true;
    	knows[firstPerson] = true;
    }
    
    public static int find(int i) {
    	if(i != father[i]) {
    		father[i] = find(father[i]);
    	}
    	return father[i];
    }
    
    public static void union(int x, int y) {
    	int fx = find(x);
    	int fy = find(y);
    	
    	if(fx != fy) {
    		father[fx] = fy;
    		if(knows[fx] || knows[fy]) {
    			knows[fx] = true;
    			knows[fy] = true;
    		}
    	}
    }
    
    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
    	build(n, firstPerson);
    	
    	//Sort(meetings);
    	
    	Arrays.sort(meetings, (a,b) -> a[2] - b[2]);
    	
    	int meetingTimes = meetings.length;
    	
    	int times = meetings[0][2];
    	
    	for(int i = 0; i < meetingTimes; i++) {
    		int p1 = meetings[i][0];
    		int p2 = meetings[i][1];
    		
    		if(times != meetings[i][2]) {
    			toTiscretize(n); //拆散不知道秘密的集合，重新开始
    			times = meetings[i][2];
    		}
    		union(p1,p2);
    	}
    	
    	List<Integer> result = new LinkedList<>();
    	
    	for(int i = 0; i < n; i++) {
    		if(knows[find(i)]) {
    			result.add(i);
    		}
    	}
    	
    	return result;

    }
    
    public static void main(String[] string) {
    	int[][] me = {{3,1,3},{1,2,2},{0,3,3}};
    	List<Integer> list = findAllPeople(4,me,3);
    	
    	for(int i : list) {
    		System.out.print(i);
    	}
    }
}
