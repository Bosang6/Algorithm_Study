package Sort;

public class MergeSortApplication {

	public static void main(String[] args) {
		int[] arr = new int[] {1,3,4,2,5};

		System.out.println(XiaoHeWenTi(arr));
	}
	
	/* 小和问题：给定一个Int数组，求每个位置上，左侧小于自身的总和
	 * [1,3,4,2,5]
	 * 
	 * [0,1,4,1,10] --> 求和 16
	*/
	public static int XiaoHeWenTi(int[] arr) {
		if(arr == null) {
			return -1;
		}
		
		if(arr.length == 1) {
			return 0;
		}
		
		int left = 0;
		int right = arr.length - 1;
		
		return process(arr, left, right);
		
	}
	
	public static int process(int[] arr, int left, int right) {
		if(left == right) {
			return 0;
		}
		
		int res = 0;
		int mid = left + (right - left) / 2;
		res += process(arr, left, mid);
		res += process(arr, mid + 1, right);
		
		return res += merge(arr, left, mid, right);
		
		
	}
	
	public static int merge(int[] arr, int left, int mid, int right) {
		int res = 0;
		int p1 = left;
		int p2 = mid + 1;
		
		for(p2 = mid + 1; p2 <= right; p2++) {
			for(p1 = left; p1 <= mid; p1++) {
				if(arr[p2] > arr[p1]) {
					res += arr[p1];
				}
			}
		}
		
		return res;
	}

}
