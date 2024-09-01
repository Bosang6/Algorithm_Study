package LeetCode;

public class NetherlandsFlagQuestion {
	
	/* 荷兰国旗问题
	 * 给定一个数组和一个整数num，令该数组中，小于num的放在数组左边，等于num放在数组中间，大于num放在数组右边
	 * 
	 * input: [3,5,6,3,4,5,2,6] , num = 3;
	 * output: [2,3,3,4,5,6,6,5]
	 * 
	 * */

	public static void main(String[] args) {
		int[] arr = new int[] {3,5,6,3,4,5,2,6};
		int num = 3;
		
		netherlandsFlagQuestion(arr,num);
		
		for(int n : arr) {
			System.out.print(n);
		}

	}
	
	public static int[] netherlandsFlagQuestion(int[] arr, int num) {
		
		int less = 0;
		int greater = arr.length;
		
		for(int i = 0; i < greater; i++) {
			while(arr[i] > num) {
				swap(arr, i, --greater);
				//swap(arr, i, greater - 1);
				//greater--;
			}
			while(arr[i] < num) {
				swap(arr, i, less++);
				//swap(arr, i, less);
				//less++;
			}
		}
		
		
		return arr;
	}
	
	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}
	
	

}
