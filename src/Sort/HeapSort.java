package Sort;

public class HeapSort {

	public static void main(String[] args) {
		int[] arr = new int[] {3,5,6,3,4,5,2,6};
		
		heapSort(arr);
		
		for(int n : arr) {
			System.out.print(n);
		}
	}
	
	public static void heapSort(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			heapInsert(arr, i);
		}
		
		for(int end = arr.length - 1; end >= 0; end--) {
			heapify(arr, 0, end);
		}
	}

	public static void heapInsert(int[] arr, int index) {
		while(index > 0 && arr[index] > arr[(index - 1) / 2]){
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}
	
	public static void heapify(int[] arr, int index, int heapSize) {
		swap(arr, 0, heapSize);
		
		int left = index * 2 + 1;
		
		while(left < heapSize) {
			int greaterIndex = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left; 

			greaterIndex = arr[greaterIndex] > arr[index] ? greaterIndex : index;

			if(greaterIndex == index) {
				break;
			}
			swap(arr, index, greaterIndex);
			index = greaterIndex;
			left = index * 2 + 1;
		}
	}
	
	
	
	
	
	
	
	
	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}
}
