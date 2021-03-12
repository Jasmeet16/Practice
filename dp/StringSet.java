import java.util.Arrays;
class StringSet{
    public static void main(String[] args) {
        //longestPalindromeSubseq( "geekeeks" );
        //longestCommonSubsequence( "abcde" , "abc" );
        //countPS( "abca" );
        //numDistinct(  "rabbbit"  , "rabbit" );
        //distinctSubseqII( "abcbc" );
        // System.out.println(AiBjCk( "abcabc" ));
        // System.out.println(minDistance("horse" , "ros"));
        // System.out.println(isMatch("horse" , "*rs?"));
        System.out.println( numDecodings("1233245224431") );

        
        
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



    //Count subsequences of type a^i, b^j, c^k
    

    // public static int fun(String s){
        
    // }

    public static int AiBjCk( String s ){

    	int ai = 0;
    	int abi = 0;
    	int abci = 0;

    	for( int  i = 0 ;  i < s.length() ; i++ ){
    		char ch = s.charAt(i);
    		if( ch == 'a' ){
    			ai = 2 * ai + 1;
    		}else if( ch  == 'b' ){
    			abi = 2 * abi + ai;
    		}else{
    			abci = 2 * abci + abi;
    		}
    		System.out.print( i + " ->  " );
    		System.out.print( "ai -> " +  ai);
    		System.out.print( "  abi -> " +  abi);
    		System.out.print( "  abci -> " +  abci);
    		System.out.println();

    	}
    	return abci;
    }

    // --------------------- output ------------------------------

	//      0 ->  ai -> 1  abi -> 0  abci -> 0
	// 		1 ->  ai -> 1  abi -> 1  abci -> 0
	//	 	2 ->  ai -> 1  abi -> 1  abci -> 1
	// 		3 ->  ai -> 3  abi -> 1  abci -> 1
	// 		4 ->  ai -> 3  abi -> 5  abci -> 1
	// 		5 ->  ai -> 3  abi -> 5  abci -> 7

	// 				7




    //72. Edit Distance

 	// Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

	// You have the following three operations permitted on a word:

	// Insert a character
	// Delete a character
	// Replace a character
	 

	// Example 1:

	// Input: word1 = "horse", word2 = "ros"
	// Output: 3
	// Explanation: 
	// horse -> rorse (replace 'h' with 'r')
	// rorse -> rose (remove 'r')
	// rose -> ros (remove 'e')

    public static int minDistance(String word1, String word2) {
    	int[][] dp  = new int[word1.length()+1][word2.length()+1];
    	for( int[] a : dp ) Arrays.fill( a , -1);
        int ans = minUpdationsMemo( word1 , word2 , word1.length() , word2.length() ,dp);
    	print2d(dp);
    	return ans;
    }

    // recursive 
    public static int minUpdations( String s1 , String s2  , int n  , int  m ){
    	if( m == 0 ) return n;
    	if( n == 0 ) return m;
    	

    	int ans = (int)1e9;

    	if( s1.charAt( n-1 ) == s2.charAt(m-1) ){
    		ans =  minUpdations( s1  , s2, n-1, m-1 ) ;
    	}  	
    	else{
    		// replace
    		int replace  = minUpdations( s1 , s2 , n-1, m-1 );
    		//delete
    		int delete = minUpdations( s1 , s2, n-1 , m );
    		//insert
    		int insert = minUpdations( s1 , s2 , n , m-1 );
    		// Math.min(  replace , delete , insert );
    		ans = Math.min( replace  , Math.min(  delete , insert ) ) + 1;

    	}
    	return ans;
    }

    public static int minUpdationsMemo( String s1 , String s2  , int n  , int  m  , int[][] dp){
    	if( m == 0 ) return dp[n][m] = n;
    	if( n == 0 ) return dp[n][m] = m;
    	
    	if( dp[n][m] != -1 ) return dp[n][m];
    	int ans = (int)1e9;

    	if( s1.charAt( n-1 ) == s2.charAt(m-1) ){
    		ans =  minUpdationsMemo( s1  , s2, n-1, m-1 ,dp ) ;
    	}  	
    	else{
    		// replace
    		int replace  = minUpdationsMemo( s1 , s2 , n-1, m-1,dp );
    		//delete
    		int delete = minUpdationsMemo( s1 , s2, n-1 , m ,dp);
    		//insert
    		int insert = minUpdationsMemo( s1 , s2 , n , m-1,dp );
    		// Math.min(  replace , delete , insert );
    		ans = Math.min( replace  , Math.min(  delete , insert) ) + 1;

    	}
    	return dp[n][m] = ans;
    }

 	// --------------------- output ------------------------------


    // horse ros
	// 0 1 -1 -1 
	// 1 1 -1 -1 
	// 2 2 1 -1 
	// 3 2 2 -1 
	// 4 3 3 2 
	// 5 4 4 3 
	// 3


// 44. Wildcard Matching
// Hard


// Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

// '?' Matches any single character.
// '*' Matches any sequence of characters including the empty


	public static boolean isMatch(String s, String p) {
		int[][] dp = new int[s.length()+1][p.length()+1];
        
        char prev = '.';
        StringBuilder sb = new StringBuilder();
        for( int i  =0 ; i < p.length() ; i++ ){
            char ch  = p.charAt(i);
            if( ch == '*' && prev == '*' ) continue;
            sb.append(ch);
            prev = ch;
        }
        p = sb.toString();
		for( int[] a : dp ) Arrays.fill( a , -1 );
        int ans = isMatchMemo( s , p , s.length() , p.length() ,dp );
        return ans == 1 ? true : false;
    }

 public static boolean  isMatch( String s , String   p , int n , int m ){

    	if( n == 0 ){
    		if( m == 0 ) return true;
    		else if( m == 1 && p.charAt(m-1) == '*' ) return true;
    		else return false; 
    	}
    	if( m == 0 ) return false;

    	boolean ans  = false;
    	int c1 = s.charAt(n-1);
    	int c2 = p.charAt(m-1);
    	if( c1 == c2 || c2 == '?' ){
    		ans  = ans || isMatch( s ,p  ,n-1, m-1 );
    	}
    	if( c2 == '*' ){
    		ans  = ans ||  isMatch( s ,p  ,n, m-1 ) || isMatch( s ,p  ,n-1, m );
    	}
    	return ans;
    }


    public static int  isMatchMemo( String s , String   p , int n , int m , int[][] dp ){
    	if( n == 0 ){
    		if( m == 0 ) return dp[n][m] = 1;
    		else if( m == 1 && p.charAt(m-1) == '*' ) return dp[n][m] =  1;
    		else return dp[n][m] = 0; 
    	}
    	if( m == 0 ) return dp[n][m] =  0;

    	if( dp[n][m] != -1) return dp[n][m];

    	int ans  = 0;
    	int c1 = s.charAt(n-1);
    	int c2 = p.charAt(m-1);
    	if( c1 == c2 || c2 == '?' ){
    		ans  =  isMatchMemo( s ,p  ,n-1, m-1 ,dp );
    		if( ans == 1 ) return dp[n][m] = 1;
    	}
    	if( c2 == '*' ){
    		ans  = isMatchMemo( s ,p  ,n, m-1 , dp );
    		if( ans == 1 ) return dp[n][m] = 1;
    		ans =  isMatchMemo( s ,p  ,n-1, m ,dp ); 
    	}
    	return dp[n][m] = ans;
    }


// 91. Decode Ways

 	//Input: s = "12"
	// Output: 2
	// Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).

	public int numDecodings(String s) {
        int[] dp = new int[s.length()+1];
        Arrays.fill(dp , -1);
        return numDecodingsMemo( s , 0,  dp );
    }

    public static int numDecodingsRec( String s ,  int idx ){
    	if( idx == s.length() ){
    		return 1;
    	}

    	char ch = s.charAt( idx );
    	int num  = ch - '0';
    	if( num == 0 ) return 0;
    	int count = 0 ;
    	count += numDecodingsRec( s , idx+1 );


    	if( num <= 2 ){
    		if( idx + 1 < s.length() ){
    			char ch2 = s.charAt( idx+1 );
    			int num2 = ch2 - '0';
    			if( ( num * 10 + num2 )<= 26 ){
    				count += numDecodingsRec( s , idx + 2 );
    			}
    		}
    	}
    	return count;
    }


    public int numDecodingsMemo( String s ,  int idx  ,int[] dp){
    	if( idx == s.length() ){
    		return dp[idx] = 1;
    	}

    	if( dp[idx] != -1 ) return dp[idx];
    	char ch = s.charAt( idx );
    	int num  = ch - '0';
    	if( num == 0 ) return 0;
    	int count = 0 ;
    	count += numDecodingsMemo( s , idx+1, dp );


    	if( num <= 2 ){
    		if( idx + 1 < s.length() ){
    			char ch2 = s.charAt( idx+1 );
    			int num2 = ch2 - '0';
    			if( ( num * 10 + num2 )<= 26 ){
    				count += numDecodingsMemo( s , idx + 2, dp );
    			}
    		}
    	}
    	return dp[idx] = count;
    }

	// 639. Decode Ways II

	// In addition to the mapping above, an encoded message may contain the '*' character, which can represent any digit from
	// '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded messages 
	// "11", "12", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to decoding any of the encoded
	//  messages it can represent.
   
    public int numDecodings2(String s) {
        int[] dp = new int[s.length()+1];
        Arrays.fill(dp, -1);
        
        return nd( s , 0, dp );
    }
    

   public int nd( String s , int i , int[] dp ){
        int mod = (int)1e9 + 7;
        if( i >= s.length() ) return dp[i] = 1;
        if( s.charAt(i) == 0 ) return dp[i] = 0;
        if( dp[i] != -1 ) return dp[i];
        char c1 = s.charAt(i);
        int count =  0;
         if( c1 >= '1' && c1 <= '9' ) count = (count%mod + nd( s , i+1 ,dp )%mod)%mod;
        if( c1 == '*' ) count = (count%mod + ( 9 * nd( s , i+1 , dp ) )%mod)%mod;

        if( i < s.length()-1 ){
             char c2 = s.charAt(i+1);
            if( c1 != '*' && c2 != '*' ){
                int num = (int)( (c1-'0')*10 + (c2 - '0') );
                if( num>=10&&num <= 26 )count = (count%mod + nd( s , i+2, dp )%mod)%mod;
            }else if( c1 == '*' && c2 != '*' ){
                if(  c2 >= '0' && c2 <= '6' ) count = (count%mod +  ( 2 * nd( s , i+2 , dp ))%mod);
                else count = (count%mod + nd( s  , i+2 , dp )%mod);

            }else if( c1 != '*' && c2 == '*' ){
                if( c1 == '1' ){
                    count = (count%mod + ( 9 * nd( s , i+2 ,dp ) )%mod)%mod;
                }else if( c1 == '2' ){
                    count = (count%mod + ( 6 * nd( s , i+2 ,dp ) )%mod);
                }  
            }else{
                count = (count%mod + ( 15 * nd( s , i+2 , dp ))%mod)%mod;
            }
        }
        
        return dp[i] = count;
        
    }





}