import java.util.Arrays;
class StringSet{
    public static void main(String[] args) {
        //longestPalindromeSubseq( "geekeeks" );
        //longestCommonSubsequence( "abcde" , "abc" );
        //countPS( "abca" );
        //numDistinct(  "rabbbit"  , "rabbit" );
        distinctSubseqII( "abcbc" );
    }

    public static void print2d( int[][] arr ){
    	for( int  i = 0 ;  i < arr.length ; i++ ){
    		for( int j = 0  ; j  < arr[0].length ; j++ ){
    			System.out.print( arr[i][j] +  " " );
    		}
    		System.out.println();
    	}
    }
    public static void print1d( int[] arr ){
    	for( int  i = 0 ;  i < arr.length ; i++ ){
    		
    			System.out.print( arr[i] +  " " );
    		
    		
    	}
    	System.out.println();
    }

    //516. Longest Palindromic Subsequence

    public static int longestPalindromeSubseq(String s) {
        int[][] dp  = new int[s.length()][s.length()];
        int ans = lpsubseqMemo( s , 0 , s.length()-1 , dp );
        //print2d(dp);
        return ans;
    }
    // recursive
    public static int lpsubseq( String s , int  i ,  int  j  ){

    	if( i == j ) return 1;
    	if( i > j ) return 0;

    	int len = 0;

    	if( s.charAt(i) == s.charAt(j) ) len = lpsubseq( s , i + 1 , j - 1 ) + 2;
    	else{
    		len = Math.max( lpsubseq( s , i + 1 , j ) , lpsubseq( s , i , j-1 ) );
    	}
    	return len;
    }

    public static int lpsubseqMemo( String s , int  i ,  int  j , int[][] dp ){

    	if( i == j ) return dp[i][j] = 1;
    	if( i > j ) return dp[i][j] = 0;
    	if( dp[i][j] != 0 ) return dp[i][j];

    	int len = 0;

    	if( s.charAt(i) == s.charAt(j) ) len = lpsubseqMemo( s , i + 1 , j - 1 ,dp ) + 2;
    	else{
    		len = Math.max( lpsubseqMemo( s , i + 1 , j , dp ) , lpsubseqMemo( s , i , j-1 , dp ) );
    	}
    	return dp[i][j] = len;
    }

    // ------- output ----------------


    //    g e e k e e k s
 	// g  1 1 2 2 3 5 5 5 
	// e  0 1 2 2 3 5 5 5 
	// e  0 0 1 1 3 3 4 4 
	// k  0 0 0 1 1 0 4 4 
	// e  0 0 0 0 1 2 2 2 
	// e  0 0 0 0 0 1 1 1 
	// k  0 0 0 0 0 0 1 1 
	// s  0 0 0 0 0 0 0 1 



	//1143. Longest Common Subsequence

	public static int longestCommonSubsequence(String text1, String text2) {
		int[][] dp = new int[text1.length()+1][text2.length()+1];
        int ans = lcsMemo( text1 , text2, text1.length()  ,text2.length() , dp);
        print2d(dp);
        return ans;
    }
    ///recursive
    public  static int lcs( String s1  , String s2 , int n , int m ){
    	if( m == 0 || n == 0 ) return 0;

    	int len = 0;

    	if( s1.charAt( n - 1 ) == s2.charAt( m - 1 ) ) len = lcs( s1 , s2 , n - 1 , m-1 )+1;
    	else {
    		len = Math.max( lcs( s1, s2 , n-1 ,m ) , lcs( s1, s2,  n , m-1 ) );
    	}
    	return len;
    }

    public static int lcsMemo( String s1  , String s2 , int n , int m , int[][] dp ){

    	if( m == 0 || n == 0 ) return dp[n][m] = 0;
    	if( dp[n][m] != 0 ) return dp[n][m];
    	int len = 0;

    	if( s1.charAt( n - 1 ) == s2.charAt( m - 1 ) ) len = lcsMemo( s1 , s2 , n - 1 , m-1 ,dp )+1;
    	else {
    		len = Math.max( lcsMemo( s1, s2 , n-1 ,m , dp) , lcsMemo( s1, s2,  n , m-1 , dp ) );
    	}
    	return dp[n][m] = len;
    }

 // --------------------- output ------------------------------
 //      ' 'a b c
 //    '' 0 0 0 0 
//      a 0 1 0 0 
//      b 0 1 2 0 
//      c 0 1 2 3 
//      d 0 1 2 3 
//      e 0 1 2 3



    //Count Palindromic Subsequences
    
	public static int countPS(String str)
    {
        int[][] dp = new int[str.length()][str.length()];
        return countPalinSubSeq( str , dp );
    }
    //memoized
    public static int countPsMemo(String str,int i,int j,int[][] dp){
        if(i>=j){
            return dp[i][j] = ( i == j ) ? 1 : 0;
        }

        int x = countPsMemo(str,i+1,j-1,dp);
        int y = countPsMemo(str,i,j-1,dp);
        int z = countPsMemo(str,i+1,j,dp);
        
        if(str.charAt(i) == str.charAt(j)) dp[i][j] = (x + 1) + (y + z - x);
        else dp[i][j] = (y + z - x);

        return dp[i][j];
    }

    //tabulated
    public static int countPalinSubSeq( String s ,  int[][] dp){
    	for( int gap =  0 ; gap  < dp.length  ; gap++ ){
    		for( int  i = 0 ,  j = gap  ; i < dp.length && j < dp.length; i++ , j++ ){
    			if( i == j ){
    			 	dp[i][j] = 1;
    			 	continue;
    			}
    			int x = dp[i+1][j-1];
    			int y = dp[i+1][j];
    			int z = dp[i][j-1];

    			if( s.charAt( i ) == s.charAt(j) ) dp[i][j] = y + z + 1;
    			else dp[i][j] = y + z - x;
    		}
    	}
    	print2d(dp);
    	return dp[0][dp[0].length-1];
    }

     // --------------------- output ------------------------------
     // abca

 	// 1 2 3 7 
	// 0 1 2 3 
	// 0 0 1 2 
	// 0 0 0 1 



	// 115. Distinct Subsequences

	public static int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for( int[] a : dp) Arrays.fill(a, -1);
        int ans =  disSubSeqMemo( s , t , s.length() , t.length() , dp );
    	print2d(dp);
    	return ans;
    }
    // recursive
    public static int disSubSeq( String s1 ,String s2 , int n ,  int m ){
    	if( m == 0 ){
    		return 1;
    	}
    	if( n == 0 || n < m ) return 0;

    	int count = 0;
    	if( s1.charAt(n-1)  == s2.charAt(  m-1 ) ){
    		count += disSubSeq( s1,  s2  , n - 1 , m -1 );
    	}
    	count += disSubSeq( s1 , s2 , n -1 , m );
    		
    	
    	return count;
    }

    public static int disSubSeqMemo( String s1 ,String s2 , int n ,  int m , int[][] dp){
    	if( m == 0 ){
    		return dp[n][m] = 1;
    	}
    	if( n == 0  || n < m ) return dp[n][m] = 0;
    	
    	if( dp[n][m] != -1 ) return dp[n][m];

    	int count = 0;
    	if( s1.charAt(n-1)  == s2.charAt(  m-1 ) ){
    		count += disSubSeqMemo( s1,  s2  , n - 1 , m -1 , dp);
    	}
    	count += disSubSeqMemo( s1 , s2 , n -1 , m ,dp );
    		
    	
    	return dp[n][m] = count;
    }

 // --------------------- output ------------------------------

    // s = rabbbit 
    // t = rabbit

	// 1 0 -1 -1 -1 -1 -1 
	// -1 1 0 -1 -1 -1 -1 
	// -1 -1 1 0 -1 -1 -1 
	// -1 -1 1 1 0 -1 -1 
	// -1 -1 -1 2 1 0 -1 
	// -1 -1 -1 -1 3 0 0 
	// -1 -1 -1 -1 -1 3 0 
	// -1 -1 -1 -1 -1 -1 3 
	    

	// 940. Distinct Subsequences II
     public static int mod = (int)1e9 + 7;

	public static int distinctSubseqII(String S) {
     	String updatedToMatchDryRun  = "$" + S;
     	int[] vis = new int[26];
        Arrays.fill( vis, -1 );
     	int[] dp = new int[S.length()+1];
     	return distinctSubSeq( updatedToMatchDryRun , dp , vis );
    }

    public static int distinctSubSeq( String s  , int[] dp , int[] vis ){
        dp[0] = 1;
    	for( int i =  1 ;  i < s.length()  ; i++ ){
    		char ch = s.charAt(i);
            
    		dp[i] = (2 * dp[i-1])%mod;
    		
    		if( vis[ch-'a'] != -1 ){
    			int prevIndex = vis[ch-'a']; 
    			dp[i] = ( dp[i] % mod - dp[prevIndex-1] % mod + mod)%mod;
    		}
            vis[ch-'a']  = i;
    	}
    	print1d(dp);
    	return dp[dp.length-1]-1;
    }  

// --------------------- output ------------------------------

	// ''  a  b  c   b  c

    // 1   2  4  8  14  24 
    

}