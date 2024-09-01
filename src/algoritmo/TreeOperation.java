package algoritmo;

public class TreeOperation {
	
	public static int getTreeDepth(RBTree.RBNode root) {
		return root == null ? 0 : ( 1 + Math.max(getTreeDepth(root.getLeft()),getTreeDepth(root.getRight())));
	}
	
	private static void writeArray(RBTree.RBNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
		
		if(currNode == null) return;
		
		res[rowIndex][columnIndex] = String.valueOf(currNode.getKey() /*+ "-" + (currNode.getColor() ? "R" : "B") + ""*/);
		
		int currLevel = ((rowIndex + 1) / 2);
		
		if(currLevel == treeDepth) return;
		
		int gap = treeDepth - currLevel - 1;
		
		if(currNode.getLeft() != null) {
			res[rowIndex + 1][columnIndex - gap] = "/";
			writeArray(currNode.getLeft(),  rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
		}
		
		if(currNode.getRight() != null) {
			res[rowIndex + 1][columnIndex - gap] = "\\";
			writeArray(currNode.getRight(),  rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
		}
	}
	
	public static void show(RBTree.RBNode root) {
		if(root == null) System.out.println("EMPTY!");
		
		int treeDepth = getTreeDepth(root);
		
		int arrayHeight = treeDepth * 2 - 1;
		int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
		
		String[][] res = new String[arrayHeight][arrayWidth];
		
		for(int i = 0; i < arrayHeight; i++) {
			for( int j = 0; j < arrayWidth; j++) {
				res[i][j] = " ";
			}
		}
		
		writeArray(root, 0, arrayWidth / 2, res, treeDepth);
		
		for(String[] line:res) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < line.length; i++) {
				sb.append(line[i]);
				if(line[i].length() > 1 && i <= line.length - 1) {
					i += line[i].length() > 4 ? 2: line[i].length() - 1;					
				}
			}
			System.out.println(sb.toString());
		}
		
	}
}
