package Sort;

public class SelectionSort {

	public static void main(String[] args) {

		int[] arr = {8,7,6,5,4,3,2};
		
		selectionSort(arr);
		
		for(int num : arr) {
			System.out.print(num);
		}

	}
	
	public static void selectionSort(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		
		for(int i = 0; i < arr.length - 1; i++) {
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[j] < arr[i]) {
					swap(arr,i,j);
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
