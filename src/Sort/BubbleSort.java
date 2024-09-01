package Sort;

public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = {8,7,6,5,4,3,2};
		
		bubbleSort(arr);
		
		for(int num : arr) {
			System.out.print(num);
		}

	}
	
	public static void bubbleSort(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		
		for(int end = arr.length - 1; end > 0; end--) {
			for(int i = 0; i < end; i++) {
				if(arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
				}
			}
		}
	}
	
	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}


}
