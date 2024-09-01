package ErFenChaZhao;

public class ErFenChaZhaoZuiDaZhi {

	public static void main(String[] args) {
		int[] arr = {8,7,6,5,4,3,2};
		
		System.out.println(searchMax(arr, 0, arr.length - 1));

	}
	
	public static int searchMax(int[] arr, int left, int right) {
		
		if(arr == null) {
			return -1;
		}
		
		if(left == right) {
			return arr[left];
		}

		int mid = left + ((right - left) / 2); 
		int numLeft = searchMax(arr, left, mid);
		int numRight = searchMax(arr, mid + 1, right);
		
		return numLeft > numRight ? numLeft : numRight;
		
	}

}
