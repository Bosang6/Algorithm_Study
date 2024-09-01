package Sort;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = new int[] {3,5,6,3,4,5,2,6};
		
		quickSort(arr);
		
		for(int n : arr) {
			System.out.print(n);
		}

	}
	
	public static void quickSort(int[] arr) {
		quickSort3_0(arr, 0, arr.length -1);
	}
	
	public static void quickSort3_0(int[] arr, int left, int right) {
		if(left < right) {
			swap(arr, left + (int)(Math.random() * (right - left + 1)), right); //从数组中，任意选择一个数字作为num来进行partition
			int[] p = partition_prova(arr, left, right);
			
			/* p[0] - 1 和 p[1] + 1 的含义
			 * 
			 * [--<--,--==--,-->--]
			 *      ^        ^
			 *      |        |
			 *    p[0]-1  p[1]+1  
			 *    
			 * */
			quickSort3_0(arr, left, p[0] - 1);
			quickSort3_0(arr, p[1] + 1, right);
		}
		
	}
	
	public static int[] partition(int[] arr, int left, int right) {
		int less = left - 1;
		int greater = right;
		while(left < greater) {
			if(arr[left] < arr[right]) {
				swap(arr, left++, ++less);
			}
			else if(arr[left] > arr[right]) {
				swap(arr, left, --greater);
			}
			else {
				left++;
			}
		}
		swap(arr, greater, right);
		
		return new int[] {less + 1, greater};
	}
	
	
	
	public static int[] partition_prova(int arr[], int L, int R) {
		int less = L - 1;
		int more = R;
		
		while(L < more) {
			if(arr[L] < arr[R]) {
				swap(arr, L++, ++less);
			} else if(arr[L] > arr[R]) {
				swap(arr, L, --more); // 注意L不++，因为需要重新判断arr[L]是否小于arr[R]
			} else {
				L++;
			}
		}
		
		swap(arr, R, more);
		
		return new int[] {less + 1, more};
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

}
