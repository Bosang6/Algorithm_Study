package algoritmo;

public class Nqueen {

	public static void main(String[] args) {
		System.out.println(num(6));

	}
	
	public static int num(int n) {
		if(n < 1) {
			return 0;
		}
		int[] record = new int[n];
		return process(0,record,n);
	}
	
	public static int process(int i, int[] record, int n) {
		if(i == n) {
			return 1;
		}
		int res = 0;
		
		for(int j = 0; j < n; j++) {
			if(isValid(j, record, i)) {
				record[i] = j;
				res += process(i + 1, record, n);
			}
		}
		
		return res;
	}
	
	public static boolean isValid(int j, int[] record, int i) {
		for(int k = 0; k < i; k++) {
			if(record[k] == j || Math.abs(record[k] - j) == Math.abs(i - k)) {
				return false;
			}
		}
		return true;
	}

}
