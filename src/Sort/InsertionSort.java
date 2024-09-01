package Sort;

public class InsertionSort {

	public static void main(String[] args) {
		int[] arr = {8,7,6,5,4,3,2};
		
		insertionSort(arr);
		
		for(int num : arr) {
			System.out.print(num);
		}

	}
	
	public static void insertionSort(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		
		for(int i = 1; i < arr.length; i++) {
			for(int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}
	
	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

}
