//Populate Next Right pointers of Tree

//dfs 

public Node connect(Node root) {
	connectNodes( root );
	return root;
}
public void connectNodes( Node root ){
	if( root == null || root.left == null ) return;

	if( root.left.next == null ){
		root.left.next = root.right;
	}
	if( root.right.next == null && root.next !=  null ){
		root.right.next = root.next.left;
	}
	connectNodes( root.left );
	connectNodes( root.right );
}

// bfs

public Node connect(Node root) {
	if( root == null ) return root;
	LinkedList<Node> qu = new LinkedList<>();
	qu.addLast( root );
	qu.addLast( null );
	while( qu.size() != 1 ){
		int size = qu.size();
		while( size-->1 ){
			Node rn = qu.removeFirst();
			rn.next = qu.getFirst();
			if( rn.left != null ) qu.addLast( rn.left );
			if( rn.right != null ) qu.addLast( rn.right );
		}
		qu.addLast( qu.removeFirst() );
	}
	return root;
}


// search  in a bst

public TreeNode searchBST(TreeNode root, int val) {
	TreeNode node = root;

	while( node != null ){
		if( node.val == val ) return node;

		if( node.val > val ) node = node.left;
		else node = node.right;
	}
	return null;
}

// bst from preorder

public TreeNode bstFromPreorder(int[] preorder) {
	int[] nge = nge( preorder );
	return buildTree( preorder , nge , 0 , preorder.length-1 );
}

public static TreeNode buildTree( int[] preorder , int[] nge , int si , int ei ){
	if( si > ei ) return null;
	TreeNode node = new TreeNode( preorder[si] );

	int div = nge[si];

	TreeNode l = buildTree( preorder , nge , si+1 , div-1 );
	TreeNode r = buildTree( preorder , nge , div , ei );
	node.left = l;
	node.right = r;
	return node;
}

public static int[] nge( int[] arr ){
	int n = arr.length;
	Stack<Integer> st = new Stack<>();
	int[] ans = new int[n];
	Arrays.fill( ans , n );
	for( int i = 0 ; i < n ; i++ ){
		while( !st.isEmpty() && arr[st.peek()] < arr[i] ){
			ans[st.pop()] = i;
		}
		st.push(i);
	}
	return ans;
}


// validate bst

public boolean isValidBST(TreeNode root) {
	long lMin = (long)1e12;
	long rMax = -(long)1e12;
	return check(root, lMin , rMax);
}

public static boolean check( TreeNode root , long lMin , long rMax ){
	if( root == null ) return true;

	if( ( root.left != null && root.left.val >= root.val ) || root.val >= lMin ) return false;
	if( ( root.right != null && root.right.val <= root.val) || root.val <= rMax ) return false;

	return check( root.left, root.val , rMax ) && check( root.right,lMin , root.val );
}

// inorder traversal solution

TreeNode prev;
public boolean isValidBST(TreeNode root) {
	prev = null;
	return check( root );

}
public boolean check( TreeNode root ){
	if( root == null ) return true;

	if( !check( root.left ) ){
		return false;
	}

	if( prev != null  && prev.val >= root.val ) return false;

	prev = root;

	if( !check( root.right ) ){
		return false;
	}

	return true;

}

// lca of bst
// = to sign plays an important role
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	TreeNode node = root;

	while( node != null ){
		if( ( p.val >= node.val && q.val  <= node.val ) || ( p.val <= node.val && q.val >= node.val ) ){
			return node;
		}

		if( p.val >= node.val && q.val >= node.val ) node = node.right;
		if( p.val <= node.val && q.val <= node.val) node = node.left;

	}
	return null;
}


//

public static Node pre;
public static Node succ;
public static void findPreSuc(Node root, Res p, Res s, int key)
{
	pre = new Node( -1 );
	succ =  new Node( -1 );
	setPreSuc( root , key );
	p.pre = pre;


	s.succ = succ;
}

public static boolean setPreSuc( Node root , int key ){
	if( root == null ) return false;

	if( setPreSuc( root.left , key ) ) return true;



	if( root.data > key  ){
		succ = root;
		return true;
	} 
	//most important test case
	if( root.data != key ) pre = root;

	if( setPreSuc( root.right , key ) ) return true;

	return false;
}