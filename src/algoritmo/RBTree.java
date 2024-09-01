package algoritmo;

public class RBTree<K extends Comparable<K>,V> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	/**
	 * 树根
	 * */
	
	private RBNode root;
	
	public RBNode getRoot() {
		return root;
	}
	
	/**
	 * 获取当前节点的父节点
	 * @param node
	 * */
	
	private RBNode parentOf(RBNode node) {
		if(node != null) {
			return node.parent;
		}
		return null;
	}
	
	/**
	 * 节点是否为红色
	 * @param node
	 * */
	
	private boolean isRed(RBNode node) {
		if(node != null) {
			return node.color == RED;
		}
		return false;
	}
	/**
	 * 节点是否为黑色
	 * @param node
	 * */
	
	private boolean isBlack(RBNode node) {
		if(node != null) {
			return node.color == BLACK;
		}
		return false;
	}
	
	
	/**
	 * 设置节点为红色
	 * */
	
	private void setRed(RBNode node) {
		if(node != null) {
			node.color = RED;
		}
	}
	
	/**
	 * 设置节点为黑色
	 * */
	
	private void setBlack(RBNode node) {
		if(node != null) {
			node.color = BLACK;
		}
	}
	
	/**
	 * 中序打印二叉树
	 * */
	
	public void inOrderPrint() {
		inOrderPrint(this.root);
		System.out.println();
	}
	
	private void inOrderPrint(RBNode node) {
		if(node != null) {
			inOrderPrint(node.left);
			System.out.print(node.key);
			inOrderPrint(node.right);
		}
	}
	
	/**
	 * 左旋
	 * 
	 * 		P			P
	 *		|			|
	 *		x			y
	 *	   / \   -->   / \
	 *	  lx  y		  x   ry
	 *		 / \     / \
	 *      ly  ry  lx  ly
	 *      
	 *  1. 将ly的父节点更新为x，x的右子节点指向ly
	 *  2. 当x的父节点不为null，更新y的父节点为x的父节点，把P指向y
	 *  3. 把x的父节点更新为y，把y的左节点指向x
	 * */
	
	private void leftRotate(RBNode x) {
		RBNode y = x.right; //获取y节点
		//1.x的右子节点指向ly
		x.right = y.left;
		if(y.left != null) { //防止y的左子节点为null
			y.left.parent = x; //将 ly的父节点更新为x
		}
		
		//2.当x的父节点不为null，更新y的父节点为x的父节点，把P指向y
		if(x.parent != null) {
			y.parent = x.parent;
			
			if(x == x.parent.left) { //如果x在爹的左边
				x.parent.left = y;	 //爹的左边变成y
			}
			else {
				x.parent.right = y;	 //否则，爹的右边变成y
			}
		}
		else { //说明x为根节点root，需要更新y为根节点
			this.root = y;
			this.root.parent = null;
		}
		
		//3. 把x的父节点更新为y，把y的左节点指向x
		x.parent = y;
		y.left = x;	
	}
	
	/**
	 * 公开插入
	 * @param key
	 * @param value
	 * */
	
	public void insert(K key, V value) {
		RBNode node = new RBNode();
		node.setKey(key);
		node.setValue(value);
		//新节点，一定时红色！
		node.setColor(RED);
		insert(node);
	}
	
	private void insert(RBNode node) {
		//第一步：查看当前node的父节点
		RBNode parent = null;
		RBNode x = this.root;
		
		while(x != null) {
			parent = x;
			
			//cmp > 0 说明node.key 大于 x.key 需要到x的右子树查找
			//cmp == 0 说明node.key 等于 x.key 需要进行交替操作
			//cmp 《 0 说明node.key 大于 x.key 需要到x的左子树查找
			int cmp = node.key.compareTo(x.key);
			if(cmp > 0) {
				x = x.right;
			}
			else if(cmp == 0) {
				x.setValue(node.getValue());
				return;
			}
			else {
				x = x.left;
			}
		}
		
		node.parent = parent;
		
		//判断node与parent 的key谁大
		if(parent != null) {
			int cmp = node.key.compareTo(parent.key);
			if(cmp > 0) {//当前node的key比parent的key大，
				parent.right = node;
			}
			else {
				parent.left = node;
			}
		}
		else {
			this.root = node;
		}
		
		//修复红黑树平衡方法
		
		insertFixUp(node);
	}
	
	/**
	 * 修复平衡
	 * */
	
	private void insertFixUp(RBNode node) {
		this.root.setColor(BLACK);
		
		RBNode parent = parentOf(node);
		RBNode gparent = parentOf(parent);
		
		if(parent != null && isRed(parent)) {
			//如果父节点存在，那么一定存在父节点，因为根节点不可能是红色
			RBNode uncle = null;
			
			if(parent == gparent.left) {
				uncle = gparent.right;
				
				if(uncle != null && isRed(uncle)) {
					setBlack(parent);
					setBlack(uncle);
					setRed(gparent);
					insertFixUp(gparent);
					return;
				}
				
				if(uncle == null || isBlack(uncle)) {
					if(node == parent.left) {
						setBlack(parent);
						setRed(gparent);
						rightRotate(gparent);
						return;
					}
					
					if(node == parent.right) {
						leftRotate(parent);
						insertFixUp(parent);
						return;
					}
				}
			}
			else {
				
				uncle = gparent.left;
				if(uncle != null && isRed(uncle)) {
					setBlack(parent);
					setBlack(uncle);
					setRed(gparent);
					insertFixUp(gparent);
					return;
				}
				
				if(uncle == null || isBlack(uncle)) {
					if(node == parent.right) {
						setBlack(parent);
						setRed(gparent);
						leftRotate(gparent);
						return;
					}
					
					if(node == parent.left) {
						rightRotate(parent);
						insertFixUp(parent);
						return;
					}
				}
			}
		}
	}
	
	/**
	 * 右旋
	 * 
	 * 		P				P
	 * 		|				|
	 * 		y				x
	 * 	   / \   -->	   / \
	 * 	  x  ry			 lx   y
	 * 	 / \			 	 / \
	 *  lx	ly			    ly  ry
	 * 
	 * 获取x节点
	 * 1.y的左节点指向ly，更新ly的父节点为y
	 * 2.y的父节点不为null时，更新x的父节点为P,P指向x
	 * 3.更新x的右节点为y，更新y的父节点为x
	 * 
	 * */
	
	private void rightRotate(RBNode y) {
		RBNode x = y.left;
		y.left = x.right;
		if(x.right != null) {
			x.right.parent = y;
		}
		
		if(y.parent != null) {
			x.parent = y.parent;
			
			if(y == y.parent.left) {
				y.parent.left = x;
			}
			else {
				y.parent.right = x;
			}
		}
		else {
			this.root = x;
			this.root.parent = null; 
		}
		
		y.parent = x;
		x.right = y;
	}
	
	static class RBNode <K extends Comparable<K>,V>{

		private RBNode parent;
		private RBNode left;
		private RBNode right;
		private boolean color;
		private K key;
		private V value;
		
		public RBNode() {
			
		}
		
		public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.color = color;
			this.key = key;
			this.value = value;
		}

		public void setParent(RBNode parent) {
			this.parent = parent;
		}

		public void setLeft(RBNode left) {
			this.left = left;
		}

		public void setRight(RBNode right) {
			this.right = right;
		}

		public void setColor(boolean color) {
			this.color = color;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public RBNode getParent() {
			return parent;
		}
		
		public RBNode getLeft() {
			return left;
		}
		
		public RBNode getRight() {
			return right;
		}
		
		public boolean getColor() {
			return color;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setBlack(RBNode node) {
			node.color = BLACK;
		}
		
		public void setRed(RBNode node) {
			node.color = RED;
		}
	}

}
