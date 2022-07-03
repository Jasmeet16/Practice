// Rat in a  maze
// n * n matrix
public static ArrayList<String> findPath(int[][] m, int n) {
	int[][] dir = { {1 , 0} , { 0 , -1 } , {0 , 1 } , { -1 , 0 } };
	String[] d = { "D" , "L" , "R" , "U"};
	ArrayList<String> res  = new ArrayList<>();
	if( m[0][0] == 0 ) return res;
	path(  m , n , dir , d , 0 , 0 , "" , res );
	return res;
}

public static int path( int[][] arr , int n ,  int[][] dir , String[] d , int sr , int sc , String ans , ArrayList<String> res ){
	if( sr == n-1 && sc == n-1 ){
		res.add( ans );
		return 1;
	}
	int count = 0;
	for( int i = 0 ; i < 4 ; i++ ){
		int rDisplacement = sr +  dir[i][0];
		int cDisplacement = sc + dir[i][1];
		String direction = d[i];
		if( rDisplacement >= 0 && rDisplacement < n && cDisplacement >= 0 && cDisplacement < n && arr[rDisplacement][cDisplacement] == 1 ){
			arr[sr][sc] = 0;
			count += path( arr , n , dir , d , rDisplacement , cDisplacement , ans + direction, res );
			arr[sr][sc] = 1;
		}
	}
	return count;
}

// Permutation

public List<List<Integer>> permute(int[] nums) {
	List<List<Integer>> res = new ArrayList<>();
	List<Integer> ans = new ArrayList<>();
	permutation( nums , 0 ,  new boolean[nums.length] , ans , res );
	return res;

}

public static int permutation( int[] arr , int count ,  boolean[] vis , List<Integer> ans  ,List<List<Integer>> res ){
	if( count == arr.length ){
		List<Integer> bans = new ArrayList<>( ans );
		res.add( bans );
		return 1;
	}
	int c = 0;
	for( int i =  0 ; i < arr.length ; i++ ){
		if( !vis[i] ){
			vis[i] = true;
			ans.add( arr[i] );
			c += permutation( arr , count + 1 , vis , ans  , res  );
			ans.remove( ans.size() - 1 );
			vis[i] = false;
		}
	}
	return c;
} 

//139. Word Break

public boolean wordBreak(String s, List<String> wordDict) {
	HashSet<String> hs= new HashSet<>();
	int longestWord = 0;
	for( String str : wordDict ){
		longestWord = Math.max( longestWord , str.length() );
		hs.add( str );
	}
	return wordBreakSolve( s , 0 , hs , longestWord );
}


public boolean wordBreakSolve( String s , int idx , HashSet<String> hs , int longestWord ){
	if( idx == s.length() ){
		return true;
	}
	for( int i =  idx+1  ; i  <= idx + longestWord && i <= s.length() ; i++ ){
		String subStr = s.substring( idx , i );
		if( hs.contains( subStr ) ){
			if( wordBreakSolve( s , i , hs , longestWord ) ) return true;
		}
	}
	return false;
}


// word break 2

public List<String> wordBreak(String s, List<String> wordDict) {
	HashSet<String> hs= new HashSet<>();
	int longestWord = 0;
	for( String str : wordDict ){
		longestWord = Math.max( longestWord , str.length() );
		hs.add( str );
	}
	List<String> res =new ArrayList<>();
	wordBreakSolve( s , 0 , "" , res , hs , longestWord );
	return res;
}


public void wordBreakSolve( String s , int idx , String ans , List<String> res , HashSet<String> hs , int longestWord ){
	if( idx == s.length() ){
		res.add(ans.substring( 0 , ans.length()-1 ));
		return ;
	}
	for( int i =  idx+1  ; i  <= idx + longestWord && i <= s.length() ; i++ ){
		String subStr = s.substring( idx , i );
		if( hs.contains( subStr ) ){
			wordBreakSolve( s , i , ans + subStr + " " , res ,  hs , longestWord ) ;
		}
	}
	return ;
}

/// Combination 1

public List<List<Integer>> combinationSum(int[] candidates, int target) {
	List<List<Integer>> res = new ArrayList<>();
	List<Integer> ans = new ArrayList<>();
	comb1( candidates , 0 , target , ans , res );
	return res;
}

public static void comb1( int[] arr , int idx , int tar, List<Integer> ans , List<List<Integer>> res ){
	if( tar == 0 ){
		List<Integer> bans = new ArrayList<>(ans);
		res.add( bans );
		return;
	}
	for( int i = idx ;  i< arr.length ; i++ ){
		if( tar - arr[i] >= 0  ){
			ans.add( arr[i] );
			comb1( arr , i , tar - arr[i] , ans , res );
			ans.remove( ans.size() - 1 );
		}
	}
	return;
}

// Combination 2
// no duplicates

public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	Arrays.sort( candidates );
	List<List<Integer>> res = new ArrayList<>();
	List<Integer> ans = new ArrayList<>();
	comb2( candidates , 0 , target , ans , res );
	return res;
}

public static void comb2( int[] arr , int idx , int tar,  List<Integer> ans , List<List<Integer>> res ){
	if( tar == 0 ){
		List<Integer> bans = new ArrayList<>(ans);
		res.add( bans );
		return;
	}
	HashSet<Integer> hs = new HashSet<>();
	for( int i = idx ;  i< arr.length ; i++ ){
		if( !hs.contains( arr[i] ) && tar - arr[i] >= 0 ){
			hs.add( arr[i] );
			ans.add( arr[i] );
			comb2( arr , i+1 , tar- arr[i] , ans , res );
			ans.remove( ans.size()-1 );
		}
	}
	return;
}

// Palindrome partitioning

public List<List<String>> partition(String s) {
	List<List<String>> res  = new ArrayList<>();
	List<String> ans = new ArrayList<>();
	solve( s,  0 , ans , res );
	return res;
}

public static void solve( String s , int idx , List<String> ans , List<List<String>> res){
	if( idx == s.length() ){
		List<String> bans =new ArrayList<>(ans);
		res.add( bans );
		return;
	}

	for( int i = idx ; i < s.length() ; i++ ){
		if( isPalin( s , idx , i ) ){
			ans.add( s.substring( idx , i+1 ) );
			solve( s , i+1 , ans, res );
			ans.remove( ans.size() - 1 );
		}
	}
}
public static boolean isPalin( String str , int si  , int ei ){
	while( si <= ei ){
		if( str.charAt(si++) != str.charAt(ei--) ) return false;
	}
	return true;
}


//Given a list(Arr) of N integers, print sums of all subsets in it. Output should be printed in increasing order of sums.

ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){

	ArrayList<Integer> ans = new ArrayList<>();
	subset( arr , 0 , 0 , ans );
	Collections.sort( ans );
	return ans;
}

public static void subset( ArrayList<Integer> arr , int idx , int sum , ArrayList<Integer> ans ){

	if( idx  == arr.size() ){
		ans.add( sum );
		return;
	}

	subset( arr , idx + 1 , sum ,ans );
	subset( arr , idx + 1 , sum + arr.get(idx) , ans );
}

//subsetsum 2

public List<List<Integer>> subsetsWithDup(int[] nums) {
	Arrays.sort( nums );
	List<List<Integer>> res = new ArrayList<>();
	List<Integer> ans = new ArrayList<>();
	subsetNoDup( nums , 0 , ans ,res );
	return res;
}

public static void subsetNoDup( int[] arr , int idx , List<Integer> ans, List<List<Integer>> res ){

	List<Integer> bans = new ArrayList<>(ans);
	res.add(bans);

	for( int i  = idx ; i < arr.length ; i++ ){
		if( i > idx && arr[i-1] == arr[i] ) continue;
		ans.add( arr[i] );
		subsetNoDup( arr , i+1 , ans , res );
		ans.remove( ans.size() -1 ); 
	}
}