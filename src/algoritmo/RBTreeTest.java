package algoritmo;

import java.util.Scanner;

public class RBTreeTest {

	public static void main(String[] args) {
		boolean next = false;
		boolean end = false;
		Scanner scanner = new Scanner(System.in);
		Scanner nextIns = new Scanner(System.in);
		
		RBTree<String, Object> rbt = new RBTree();
		RBTree<String, Object> rbt1 = new RBTree();
		
		while(!next) {
			System.out.println("请输入key:");
			String key = scanner.next();
			System.out.println();
			rbt.insert(key, null);
			
			rbt.inOrderPrint();
			
			System.out.println("Go next Tree? Y/N");
			String nextTree = nextIns.next();
			if(nextTree.equals("Y"))
				next = true;
				
			//TreeOperation.show(rbt.getRoot());
		}
		
		while(!end) {
			System.out.println("请输入key:");
			String key = scanner.next();
			System.out.println();
			rbt1.insert(key, null);
			
			rbt1.inOrderPrint();
			
			System.out.println("End insert? Y/N");
			String endTree = nextIns.next();
			if(endTree.equals("Y"))
				end = true;
		}
		
	}

}
