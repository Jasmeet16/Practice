import java.util.Arrays;
class targetSet{

	public static void main(String[] args) {
		int[] arr = {1, 1, 1, 1, 1};
	 System.out.println( findTargetSumWays( arr , 3 , 0) );
	}

	

	//322. Coin Change

	// You are given coins of different denominations and a total amount of money amount. 
	// Write a function to compute the fewest number of coins that you need to make up that amount. 
	// If that amount of money cannot be made up by any combination of the coins, return -1.

	// You may assume that you have an infinite number of each kind of coin.

	// Input: coins = [1,2,5], amount = 11
	// Output: 3
	// Explanation: 11 = 5 + 5 + 1

	//Using 2D dp

	public static int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length+1][amount+1];
        for( int[] a : dp )Arrays.fill( a , -1 );
        int ans = cc( coins , amount , 0 , dp);
        return ans == (int)1e9 ? -1 : ans;
    }
    public static int cc( int[] coins , int  tar , int idx , int[][] dp){
        if( tar == 0 ){
            return dp[idx][tar]= 0;
        }
        if( idx == coins.length ){
            return dp[idx][tar]=(int)1e9;
        }
        if( dp[idx][tar] != -1 ) return dp[idx][tar];
        int ans = (int)1e9;
        if( tar - coins[idx] >= 0 )
          ans = Math.min( cc( coins , tar - coins[idx] , idx + 1, dp ) , cc( coins , tar-coins[idx] , idx, dp ) )+1;
        ans = Math.min( ans , cc( coins , tar , idx+1 ,dp ) );
        return dp[idx][tar] = ans;
    }

    // using permutations 1d dp
	public static int coinChangeDp(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill( dp , (int)1e8 );
        dp[0] = 0;
        for( int i = 1; i < dp.length ; i++ ){
            for( int j : coins )
             if( i - j >= 0 ) dp[i] = Math.min( dp[i] , dp[i - j]+1);
     	}
        
        return dp[amount] == (int)1e8 ? -1 : dp[amount];
    }


    ///////






	///////494. Target Sum

 	// Input: nums is [1, 1, 1, 1, 1], S is 3. 
	// Output: 5
	// Explanation: 

	// -1+1+1+1+1 = 3
	// +1-1+1+1+1 = 3
	// +1+1-1+1+1 = 3
	// +1+1+1-1+1 = 3
	// +1+1+1+1-1 = 3


    public static int findTargetSumWays(int[] nums, int S , int idx) {
    	if( idx == nums.length ){
    		if( S == 0 ) return 1;
    		return 0;
     	}

    	int count = 0 ;
    	
    	count += findTargetSumWays( nums , S - nums[idx] , idx + 1 );
    	count += findTargetSumWays( nums , S + nums[idx] , idx + 1 );

    	return count;
	}


    public static int adjust = 0;

    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for( int el : nums ) sum += el;
        adjust = sum;
        int[][] dp = new int[2*sum+1][nums.length+1];
        for( int[] a : dp ) Arrays.fill(a,-1);
        return findTargetSumWaysMemo( nums , S ,0,0 , dp );
    }
    
    public static int findTargetSumWaysMemo(int[] nums, int S , int cS , int idx , int[][] dp) {
    	if( idx == nums.length ){
    		if( S == cS ) return dp[cS+adjust][idx] = 1;
    		return dp[cS+adjust][idx] = 0;
     	}
        if( dp[cS+adjust][idx] != -1 ) return dp[cS+adjust][idx];
         

    	int count = 0 ;
    	
    	count += findTargetSumWaysMemo( nums , S , cS - nums[idx] , idx + 1 , dp );
    	count += findTargetSumWaysMemo( nums , S , cS + nums[idx] , idx + 1 , dp );

    	return dp[cS+adjust][idx] = count;
	}

	
}