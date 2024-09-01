package Sort;

public class MergeSort {

	public static void main(String[] args) {
		int[] arr = {8,7,6,5,4,3,2};
		
		mergeSort(arr);
		
		for(int num : arr) {
			System.out.print(num);
		}
		

	}
	
	public static void mergeSort(int[] arr) {
		if(arr == null || arr.length == 1) {
			return;
		}
		
		process(arr, 0, arr.length - 1);
		
	}
	
	public static void process(int[] arr, int left, int right) {
		if(left == right) {
			return;
		}
		
		int mid = left + ((right - left) / 2);
		process(arr, left, mid);
		process(arr, mid + 1, right);
		merge(arr, left, mid, right);
	}
	
	public static void merge(int[] arr, int left, int mid, int right) {
		int[] help = new int[right - left + 1];
		
		int p1 = left;
		int p2 = mid + 1;
		
		int i = 0;
		while(p1 <= mid && p2 <= right) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		
		while(p1 <= mid) {
			help[i++] = arr[p1++];
		}
		
		while(p2 <= right) {
			help[i++] = arr[p2++];
		}
		
		for(i = 0; i < help.length; i++) {
			arr[left + i] = help[i];
		}
 	}

}
