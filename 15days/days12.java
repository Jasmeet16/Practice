// inOrder traversal
// w/o recursion

ArrayList<Integer> inOrder(Node root) {
	ArrayList<Integer> ans = new ArrayList<>();
	Stack<Node> st = new Stack<>();
	while( root != null ){
		st.push( root );
		root = root.left;
	}
	while( st.size() != 0 ){
		Node n = st.pop();
		ans.add( n.data );

		if( n.right != null ){
			
			Node t  =n.right;
			while( t != null ){
				st.push( t );
				t = t.left;
			}
		}
	}
	return ans;
}

// preOrder traversal
// w/o recursion

static ArrayList<Integer> preorder(Node root)
{
	ArrayList<Integer> ans = new ArrayList<>();
	Stack<Node> st = new Stack<>();
	while( root != null ){
		ans.add( root.data );
		st.push( root );
		root = root.left;
	}
	while( st.size() != 0 ){
		Node n = st.pop();
		

		if( n.right != null ){
			
			Node t  =n.right;
			while( t != null ){
				ans.add( t.data );
				st.push( t );
				t = t.left;
			}
		}
	}
	return ans;
}

// right view
// recursive
public static int l = -1;
public static List<Integer> ans;
public List<Integer> rightSideView(TreeNode root) {
	ans = new ArrayList<>();
	rightView( root , 0  );
	return ans;
}
public static void rightView( TreeNode root , int cl  ){
	if( root == null ) return;

	if( cl > l ){

		ans.add( root.val );
		l = cl;
	}
	rightView( root.right , cl + 1 );
	rightView( root.left , cl + 1  );

}

//left view
// bfs

ArrayList<Integer> leftView(Node root)
{
	ArrayList<Integer> ans = new ArrayList<>();
	if( root == null ) return ans;
	LinkedList<Node> qu = new LinkedList<>();
	qu.add( root );
	qu.add( null );

	while( qu.size() > 1 ){

		Node rn = qu.removeFirst();
		ans.add( rn.data );
		while( rn != null ){
			if( rn.left != null ) qu.addLast( rn.left );
			if( rn.right != null ) qu.addLast( rn.right );
			rn = qu.removeFirst();
		}
		qu.addLast( null );
	}
	return ans;
}

// vertical order

public List<List<Integer>> verticalTraversal(TreeNode root) {
	int[] wid = new int[2];
	width( root , 0 , wid );
	int width = wid[1] - wid[0] + 1; 
	List<List<Integer>> ans = new ArrayList<>();
	for( int i = 0 ; i < width ; i++ ){
		ans.add( new ArrayList<>() );
	}
	vertical( root, Math.abs( wid[0] ) , ans );
	return ans;
}

public static void vertical( TreeNode root , int l , List<List<Integer>> ans ){
	if( root == null ) return;
	
	ans.get( l ).add(root.val);
	
	vertical( root.right , l+1 , ans );
	vertical( root.left , l-1 , ans );
	
}

public static void width( TreeNode root , int l , int[] lminRmax ){
	if( root == null ) return;
	lminRmax[0] = Math.min( lminRmax[0] , l );
	lminRmax[1] = Math.max( lminRmax[1] , l );

	width( root.left , l-1 , lminRmax );
	width( root.right , l+1 , lminRmax );
}

// top view
// recursion / dfs will not give correct solution as we might visit new l at below levels first
class pair{
	Node node;
	int l;

	public pair( Node node , int l ){
		this.node = node;
		this.l = l;
	}

}


class Solution
{
	static ArrayList<Integer> topView(Node root)
	{
		int[] minMax = new int[2];
		width( root , 0, minMax );
		int wid = minMax[1] - minMax[0] + 1;

		ArrayList<Integer> ans = new ArrayList<>();
		for( int i = 0  ; i < wid ; i++ ){
			ans.add(-1);
		}
		top( root, Math.abs( minMax[0] ) , ans );
		return ans;
	}



	public static void top( Node root , int l , ArrayList<Integer> ans ){
		if( root == null ) return;

		LinkedList<pair> qu = new LinkedList<>();
		qu.add( new pair( root , l ) );

		while( qu.size() != 0 ){
			pair rn = qu.removeFirst();
			if( ans.get(rn.l) == -1 ){
				ans.set( rn.l , rn.node.data );
			}
			if( rn.node.left != null ) qu.addLast( new pair( rn.node.left , rn.l-1 ) );
			if( rn.node.right != null ) qu.addLast( new pair( rn.node.right , rn.l+1 ) );
		}
	}

	public static void width( Node root , int l , int[] minMax ){
		if( root == null ) return;

		minMax[0] = Math.min( l , minMax[0] );
		minMax[1] = Math.max( l , minMax[1] );

		width( root.left  , l-1 , minMax );
		width( root.right , l+1 , minMax );

	}
}

// bottom view

class pair{
	Node node;
	int level;
	pair( Node node , int level ){
		this.node = node;
		this.level= level;
	}
}
public ArrayList<Integer> bottomView(Node root)
{
	int[] minMax = new int[]{ (int)1e9 , 0 };
	width( root , 0 , minMax );
	ArrayList<Integer> ans = new ArrayList<>();
	int width =  minMax[1] - minMax[0] + 1;
	int[] Ans =  new int[width];
	int leveltosend = Math.abs( minMax[0] );
	bfs( root , leveltosend  , Ans);
        // + min
	for( int i = 0; i < Ans.length ; i++ ){
		ans.add( Ans[i] );
	}
	return ans;

} 
public void bfs( Node root , int level ,int[] ans ){

	LinkedList<pair> qu = new LinkedList<>();
	qu.addLast( new pair( root , level ) );
	while( qu.size() != 0 ){
		pair rp = qu.removeFirst();
		Node rn = rp.node;
		int lvl = rp.level;
		if( rn.left != null ) qu.addLast( new pair( rn.left , lvl-1 ) );
		if( rn.right != null ) qu.addLast( new pair( rn.right , lvl +1 ) );
		ans[lvl] =  rn.data ;
	}

}
public void width( Node node , int level , int[] minMax){
	if( node == null ) return;
	minMax[0] = Math.min( level , minMax[0] );
	minMax[1] = Math.max( level , minMax[1] );

	width( node.left , level - 1 , minMax );
	width( node.right , level+1 , minMax );
}


// bottom view 
// recursive

class pair{

	Node node;
	int d;
	
	pair( Node node , int d ){
		this.node = node;
		this.d = d;
		
	}
}

class Tree
{
    //Function to return a list containing the bottom view of the given tree.
	public ArrayList <Integer> bottomView(Node root)
	{
        // Code here
		
		int[] wid = new int[2];
		width( root , 0 , wid );
		int width = wid[1] - wid[0] +1;
		ArrayList<Integer> res = new ArrayList<>();
		
		pair[] ans  = new pair[width];
		
		bottom( root , 0,  Math.abs( wid[0] ) , ans );
		
		for( int i  = 0;  i< width ; i++ ){
			res.add( ans[i].node.data );
		}
		return res;
		
	}
	
	public static void bottom( Node root , int d , int l , pair[] ans ){
		if( root == null ) return;
		
		if( ans[l] == null || ans[l].d < d ){
			ans[l] = new pair( root , d );
		}
		bottom( root.right, d+1 , l+1 , ans );
		bottom( root.left , d+1 , l-1 , ans );
		
	}
	
	public static void width( Node root , int l , int[] lminRmax ){
		if( root == null ) return;
		lminRmax[0] = Math.min( lminRmax[0] , l );
		lminRmax[1] = Math.max( lminRmax[1] , l );

		width( root.left , l-1 , lminRmax );
		width( root.right , l+1 , lminRmax );
	}
}