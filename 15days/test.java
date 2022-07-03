class test{
	
	public static class TreeNode{
		TreeNode left;
		TreeNode right;
		int val;

		TreeNode( int val ){
			this.left = null;
			this.right = null;
			this.val = val;
		}
	}
	public static TreeNode ca ;

	public static void main(String[] args) {
		ca = null;
		TreeNode root = new TreeNode( 5 );
		TreeNode lc1 = new TreeNode( 2 );
		TreeNode rc1 = new TreeNode( 12 );
		root.left = lc1;
		root.right = rc1;
		
		TreeNode lc2 = new TreeNode( 1 );
		TreeNode rc2 = new TreeNode( 3 );
		
		lc1.left = lc2;
		lc1.right = rc2;

		TreeNode lc3 = new TreeNode( 9 );
		TreeNode rc3 = new TreeNode( 21 );
		
		rc1.left = lc3;
		rc1.right = rc3;

		commonNode( root , lc2 , rc2 );

		int d1 = getDistance( ca , lc2 )-1;


		int d2 = getDistance( ca , rc3 )-1;
System.out.println( ca.val );
		System.out.println( d1+d2 );

	}

	
	public static void commonNode( TreeNode root , TreeNode f , TreeNode s ){
		if( root == null ) return;

		if( f.val < root.val && s.val  > root.val){
			if( ca == null ) ca = root;
		}

		if( f.val < root.val && s.val  < root.val ){
			commonNode( root.left , f , s );
		}

		if( f.val > root.val && s.val  > root.val ){
			commonNode( root.right , f , s );
		}
	}

	public static int getDistance( TreeNode root , TreeNode tar ){
		if( root == null ) return 0;
		if( root.val == tar.val ){
			return 1;
		}

		int dis = 0;

		int lT = getDistance( root.left , tar );
		if( lT > 0 ){
			return lT+1;
		}
		int rT = getDistance( root.right , tar );
		if( rT > 0 ){
			return rT+1;
		}
		return dis;

	}

}