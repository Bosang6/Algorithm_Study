package algoritmo;

import java.util.Arrays;

public class Havel_Hakimi_LakeFrog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] xuLie = {4,3,1,5,4,2,1};
		System.out.println(isEsist(xuLie));
		
		int[] xuLie1 = {4,3,1,4,2,0};
		System.out.println(isEsist(xuLie1));
		

	}
	
	public static boolean isEsist(int[] xuLie) {
		int dim = xuLie.length;
		
		int[][] matrix = new int[dim][dim];
		
		heapSort(xuLie);
		
		
		while(true) {
		
			int first = xuLie[0];
			
			int[] arr = new int[--dim];

			
			for(int i = 0; i < dim; i++) {
				arr[i] = xuLie[i + 1];
			} 
			

			for(int i = 0; i < first; i++) {
				arr[i] = arr[i] - 1;
				if(arr[i] < 0) {
					return false;
				}
			}
			
			heapSort(arr);
			

			xuLie = arr;
				
			boolean yes = true;
			while(yes) {
				for(int i = 0; i < dim; i++) {
					if(arr[i] != 0) {
						yes = false;
						break;
					}
				}
				if(yes) {
					return yes;
				}
			}
		}
		
	}
	
	public static void heapSort(int[] arr) {
		int heapSize = arr.length;
		for(int i = 0; i < heapSize; i++) {
			heapInsert(arr, i);
		}
		swap(arr, 0, --heapSize);
		while(heapSize > 0) {
			heapify(arr, 0, heapSize);
			swap(arr, 0, --heapSize);
		}
		
		
	}
	
	// 小根堆
	public static void heapInsert(int[] arr, int index) {
		while(arr[index] < arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) / 2 );
			index = (index - 1) / 2;
		}
	}
	
	public static void heapify(int[] arr, int index, int heapSize) {
		int left = index * 2 + 1;
		while(left < heapSize) {
			int lowest = (left + 1 < heapSize) && arr[left + 1] < arr[left] ? left + 1 : left;
			lowest = arr[lowest] < arr[index] ? lowest : index;
			if(arr[lowest] == arr[index]) {
				break;
			}
			swap(arr, index, lowest);
			index = lowest;
			left = index * 2 + 1;
		}
		
	}
	
	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}
	

}
