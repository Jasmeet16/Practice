// Construct Binary Tree from inorder and preorder 

public TreeNode buildTree(int[] preorder, int[] inorder) {

	HashMap< Integer , Integer > hm = new HashMap<>();
	int n = preorder.length;
	for( int i = 0 ; i< inorder.length ; i++ ){
		hm.put( inorder[i] , i );
	}

	return buildTree( preorder , 0 , n-1 , 0 , n-1 , hm  );
}

public static TreeNode buildTree( int[] preorder , int psi , int pei , int insi  , int inei , HashMap<Integer,Integer> hm ){

	if( psi > pei ) return null;

	TreeNode node = new TreeNode( preorder[psi] );

	int idx = hm.get( preorder[psi] );

	int len = idx - insi;

	TreeNode lch = buildTree( preorder , psi + 1 , psi + len , insi , idx-1 , hm );
	TreeNode rch = buildTree( preorder , psi + len + 1 , pei , idx + 1 , inei , hm );

	node.left = lch;
	node.right = rch;

	return node;

}

//Construct Binary Tree from Inorder and Postorder 

public TreeNode buildTree(int[] inorder, int[] postorder) {
	HashMap<Integer ,  Integer> hm = new HashMap<>();
	int n = inorder.length;
	for( int i = 0 ; i < n ; i++ ){
		hm.put( inorder[i] , i );
	}
	return buildTree( postorder , 0 , n-1 , 0 , n-1 , hm );
}

public static TreeNode buildTree( int[] postorder , int psi ,int pei , int isi , int iei , HashMap<Integer,Integer> hm ){
	if( psi > pei ) return null;
	TreeNode node = new TreeNode( postorder[pei] );

	int idx = hm.get( postorder[pei] );
	int len = idx - isi;

	TreeNode rch = buildTree( postorder , psi + len , pei - 1 , idx + 1 , iei , hm );
	TreeNode lch = buildTree( postorder , psi , psi + len - 1 , isi , idx -1 , hm );

	node.left = lch;
	node.right = rch;

	return node;
}

//

public boolean isSymmetric(TreeNode root) {
	if( root == null ) return true;
	return check( root.left , root.right );
}

public boolean check( TreeNode l , TreeNode r ){
	if( l == null && r == null ) return true;
	if( l == null || r == null ) return false;

	if( l.val != r.val ){
		return false;
	}

	return check( l.left , r.right ) && check( l.right , r.left );
}

//flatten a binnary tree

public static TreeNode prev;
public void flatten(TreeNode root) {
	prev = null;
	flattenTree( root );
}

public static void flattenTree( TreeNode root ){
	if( root == null ) return;
	
	flattenTree( root.right );
	flattenTree( root.left );
	
	root.right = prev;
	root.left = null;
	
	prev = root;
}