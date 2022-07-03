 // levelorder traversal

public List<List<Integer>> levelOrder(TreeNode root) {
   List<List<Integer>> res = new ArrayList<>();
   if( root == null )return res;
   LinkedList<TreeNode> qu = new LinkedList<>();

   qu.addLast( root );
   while( qu.size() > 0 ){
    int size = qu.size();
    List<Integer> ans = new ArrayList<>();
    while( size-->0 ){
        TreeNode rn = qu.removeFirst();
        ans.add( rn.val );
        if( rn.left != null  ) qu.addLast( rn.left );
        if( rn.right != null ) qu.addLast( rn.right );
    }
    res.add(ans);
}
return res;
}

// levelorder traversal 
// leaf to root

public List<List<Integer>> levelOrderBottom(TreeNode root) {

    List<List<Integer>> res  = new ArrayList<>();
    if( root == null ) return res;

    int height  = height( root );
    for( int i = 0 ; i < height ; i++ ){
        res.add( new ArrayList<>() );
    }
    LinkedList<TreeNode> qu = new LinkedList<>();

    qu.add( root );

    while( qu.size() != 0 ){
        int size = qu.size();
        List<Integer> ans = res.get( height - 1 );
        while( size-->0 ){
            TreeNode rn = qu.removeFirst();
            ans.add( rn.val );
            if( rn.left != null ) qu.addLast( rn.left );
            if( rn.right != null ) qu.addLast( rn.right );
        }
        height--;
    }
    return res;
}
// wrt nodes
public static int height( TreeNode root ){
    if( root == null ) return 0;
    int lh = height(root.left );
    int rh = height( root.right );
    return Math.max( lh , rh ) + 1;
}

//Level order traversal in spiral form

public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if( root == null ) return res;
    Stack<TreeNode> st1 = new Stack<>();
    st1.push( root );
    int lvl  =0;
    while( st1.size() > 0 ){
        int size = st1.size();

        Stack<TreeNode> st2 = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        while( size-->0 ){
            TreeNode rn = st1.pop();
            ans.add( rn.val );
            if( lvl % 2 == 0 ){
                if( rn.left != null ) st2.push( rn.left );
                if( rn.right != null ) st2.push( rn.right );

            }else{

                if( rn.right != null ) st2.push( rn.right );
                if( rn.left != null ) st2.push( rn.left );
            }

        }
        lvl++;
        st1 = st2;
        res.add( ans );
    }
    return res;
}

// height of tree
// wrt edges
public static int height( TreeNode root ){
    if( root == null ) return -1;
    int lh = height(root.left );
    int rh = height( root.right );
    return Math.max( lh , rh ) + 1;
}

//diameter

public int diameterOfBinaryTree(TreeNode root) {
    int[] dia = dia( root );
    return dia[1];
}
    //0 -> h
    //1 -> d
public int[] dia( TreeNode root ){
    if( root == null ) return new int[]{-1, 0};
    int[] l = dia( root.left );
    int[] r = dia( root.right );
    int[] ans = new int[2];
    ans[0] = Math.max( l[0] , r[0] ) + 1;
    ans[1] = Math.max(l[0] + r[0] + 2 , Math.max( l[1] , r[1] ));
    return ans;
}


//Check if Binary tree is height balanced or not
// can be done with global variable

public boolean isBalanced(TreeNode root) {
    int[] ans = check( root );
    return ans[1] == 1 ? true:false;
}

public int[] check( TreeNode root ){

    if( root == null ) return new int[]{ -1, 1 };

    int[] l =  check( root.left );
    int[] r = check( root.right );
    int[] ans = new int[2];
    ans[0] = Math.max( l[0] , r[0] ) + 1;
    ans[1] =  1;
    if( l[0]  -  r[0] > 1 || r[0] - l[0] > 1 || l[1] == 0 || r[1] == 0 ){
        ans[1] = 0;
    }
    return ans;
}

// lowest common node
// w/o space

TreeNode ans = null;
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    lca( root , p  ,q );
    return ans;
}

public int lca( TreeNode root , TreeNode p , TreeNode q ){

    if( root == null ) return 0;

    int l = lca( root.left , p , q );
    int r = lca( root.right , p , q );
    
    if( l == 1 && r == 1 ||  ( ( l == 1 || r == 1 ) && root == p )  || ( (l == 1 || r == 1) && root == q ) ){
        if( ans == null ) ans = root;
    }
    if( root == p || root == q ) return 1;
    if( l == 1 || r == 1 ) return 1;
    return 0;
}

// if two binary trees are same 

public boolean isSameTree(TreeNode p, TreeNode q) {
    if( p == null && q == null ) return true;
    if( p == null || q == null ) return false;
    if( p.val != q.val ) return false;

    boolean lt = isSameTree( p.left , q.left );
    boolean rt = isSameTree( p.right , q.right );

    return lt && rt;

}

// max path sum

int omax = -(int)(1e9);
public int maxPathSum(TreeNode root) {
    if( root == null ){
        return 0;
    }
    max( root );
    return omax;

}
public int max( TreeNode node ){


    int lmax = -(int)(1e9);
    int rmax = -(int)(1e9);


    if( node.left != null )  lmax = max( node.left );
    if( node.right != null )  rmax = max( node.right );

    int rootWithLeft =  lmax + node.val;
    int rootWithRight = rmax + node.val ;

    int rootWithBoth = lmax + rmax + node.val;

    omax = Math.max( omax , Math.max( rootWithLeft , Math.max( rootWithRight , Math.max( rootWithBoth , node.val ) ) ));

    return Math.max( rootWithLeft , Math.max( rootWithRight , node.val ));
}