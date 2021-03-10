//LeetCode 62
public class lesson1 {

    public static void main(String[] args) {

    }

    // recursive
    public int uniquePaths(int m, int n) {
        return uniquePaths(0, 0, m - 1, n - 1);
    }

    public int uniquePaths(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec)
            return 1;

        int count = 0;
        // move right
        if (sr + 1 <= er)
            count += uniquePaths(sr + 1, sc, er, ec);
        // move down
        if (sc + 1 <= ec)
            count += uniquePaths(sr, sc + 1, er, ec);

        return count;
    }

    // memoization
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return uniquePaths(0, 0, m - 1, n - 1, dp);
    }

    public int uniquePaths(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec)
            return dp[sr][sc] = 1;
        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;
        // move right
        if (sr + 1 <= er)
            count += uniquePaths(sr + 1, sc, er, ec, dp);
        // move down
        if (sc + 1 <= ec)
            count += uniquePaths(sr, sc + 1, er, ec, dp);

        return dp[sr][sc] = count;
    }

    // Leetcode 63
    // with obstacles
    // recursive
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return uniquePaths(0, 0, obstacleGrid.length - 1, obstacleGrid[0].length - 1, obstacleGrid);
    }

    public int uniquePaths(int sr, int sc, int er, int ec, int[][] grid, int[][] dp) {
        if (sr == er && sc == ec)
            return 1;

        int count = 0;

        if (sr + 1 <= er && grid[sr + 1][sc] != 1)
            count += uniquePaths(sr + 1, sc, er, ec, grid);
        if (sc + 1 <= ec && grid[sr][sc + 1] != 1)
            count += uniquePaths(sr, sc + 1, er, ec, grid);

        return count;
    }

    // memoization
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] == 1)
            return 0;
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        return uniquePaths(0, 0, obstacleGrid.length - 1, obstacleGrid[0].length - 1, obstacleGrid, dp);
    }

    public int uniquePaths(int sr, int sc, int er, int ec, int[][] grid, int[][] dp) {
        if (sr == er && sc == ec)
            return dp[sr][sc] = 1;
        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;

        if (sr + 1 <= er && grid[sr + 1][sc] != 1)
            count += uniquePaths(sr + 1, sc, er, ec, grid, dp);
        if (sc + 1 <= ec && grid[sr][sc + 1] != 1)
            count += uniquePaths(sr, sc + 1, er, ec, grid, dp);

        return dp[sr][sc] = count;
    }

    // Rat in maze with multiple jumps

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        while (t-- > 0) {
            int n = scn.nextInt();
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = scn.nextInt();
                }
            }
            int[][] ans = new int[n][n];
            if (arr[0][0] == 0 || arr[n - 1][n - 1] == 0 || solve(arr, 0, 0, n - 1, n - 1, ans) == 0) {
                System.out.println("-1");
            }
        }

    }

    public static int solve(int[][] arr, int sr, int sc, int er, int ec, int[][] ans) {
        ans[sr][sc] = 1;
        if (sr == er && sc == ec) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(ans[i][j] + " ");
                }
                System.out.println();
            }
            return 1;
        }
        int count = 0;
        for (int jmp = 1; jmp <= arr[sr][sc]; jmp++) {
            if (sc + jmp <= ec && arr[sr][sc + jmp] > 0) {
                count += solve(arr, sr, sc + jmp, er, ec, ans);
                if (count > 0) {
                    return count;
                }
            }
        }
        for (int jmp = 1; jmp <= arr[sr][sc]; jmp++) {
            if (sr + jmp <= er && arr[sr + jmp][sc] > 0) {
                count += solve(arr, sr + jmp, sc, er, ec, ans);
                if (count > 0) {
                    return count;
                }
            }
        }

        ans[sr][sc] = 0;
        return count;
    }

    // gfg Special Matrix
    public int FindWays(int n, int m, int[][] blocked_cells) {

        int[][] arr = new int[n][m];
        int[][] dp = new int[n][m];
        for (int[] a : blocked_cells) {
            int i = a[0] - 1;
            int j = a[1] - 1;
            if ((i == n - 1 && j == m - 1) || (i == 0 && j == 0))
                return 0;
            arr[i][j] = 1;
        }
        return solve(0, 0, n - 1, m - 1, arr, dp);
    }

    public int solve(int sr, int sc, int er, int ec, int[][] arr, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }
        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;
        if (sr + 1 <= er && arr[sr + 1][sc] == 0) {
            count += solve(sr + 1, sc, er, ec, arr, dp);
        }
        if (sc + 1 <= ec && arr[sr][sc + 1] == 0) {
            count += solve(sr, sc + 1, er, ec, arr, dp);
        }
        return dp[sr][sc] = count;
    }

    // LeetCode 1219

    public int getMaximumGold(int[][] grid) {
        int max = -(int) 1e9;
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, getMax(grid, i, j, dir));
            }
        }
        return max;
    }

    public int getMax(int[][] arr, int sr, int sc, int[][] dir) {
        int temp = arr[sr][sc];
        arr[sr][sc] = -1;

        int max = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];
            if (r >= 0 && c >= 0 && r < arr.length && c < arr[0].length && arr[r][c] > 0) {
                max = Math.max(getMax(arr, r, c, dir), max);
            }
        }
        arr[sr][sc] = temp;
        return max + arr[sr][sc];
    }

    // leetcode uniqe paths 3
    // every zero needs to be visited
    public int uniquePathsIII(int[][] grid) {
        int sr = 0, sc = 0, er = 0, ec = 0;
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int countOfZeros = 2;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    sr = i;
                    sc = j;
                }
                if (grid[i][j] == 0)
                    countOfZeros++;
                if (grid[i][j] == 2) {
                    er = i;
                    ec = j;
                }
            }
        }
        return uniquePaths(sr, sc, er, ec, grid, dir, countOfZeros - 1);
    }

    public int uniquePaths(int sr, int sc, int er, int ec, int[][] grid, int[][] dir, int countOfZeros) {
        if (sr == er && sc == ec)
            return countOfZeros == 0 ? 1 : 0;
        int temp = grid[sr][sc];
        grid[sr][sc] = -1;
        int count = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];
            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] >= 0) {
                count += uniquePaths(r, c, er, ec, grid, dir, countOfZeros - 1);
            }
        }
        grid[sr][sc] = temp;
        return count;
    }

    // Keypad Combination

    public List<String> letterCombinations(String digits) {
        String[] codeArr = { "", "-", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> ans = new ArrayList<>();

        letterCombination(digits, 0, codeArr, "", ans);
        return digits.length() == 0 ? new ArrayList<>() : ans;
    }

    public void letterCombination(String str, int idx, String[] codeArr, String sans, List<String> ans) {
        if (idx == str.length()) {
            ans.add(sans);
            return;
        }

        int digit = str.charAt(idx) - '0';

        String code = codeArr[digit];

        for (int i = 0; i < code.length(); i++) {

            letterCombination(str, idx + 1, codeArr, sans + code.charAt(i), ans);

        }
    }

    // permutations

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        perm(nums, 0, new boolean[nums.length], sans, ans);
        return ans;
    }

    public void perm(int[] nums, int count, boolean[] vis, List<Integer> sans, List<List<Integer>> ans) {

        if (count == nums.length) {
            List<Integer> bans = new ArrayList<>(sans);
            ans.add(bans);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                sans.add(nums[i]);
                perm(nums, count + 1, vis, sans, ans);
                sans.remove(sans.size() - 1);
                vis[i] = false;
            }
        }
    }

    // unique permutation

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        uniquePermutations(nums, new boolean[nums.length], 0, ans, sans);
        return ans;
    }

    public void uniquePermutations(int[] nums, boolean[] blockedCells, int count, List<List<Integer>> ans,
            List<Integer> sans) {
        if (count == nums.length) {
            List<Integer> bans = new ArrayList<>(sans);
            ans.add(bans);
            return;
        }

        // range -9 to 10
        boolean[] alreadyCalled = new boolean[20];

        for (int i = 0; i < nums.length; i++) {
            if (!blockedCells[i] && !alreadyCalled[nums[i] + 10]) {
                sans.add(nums[i]);
                blockedCells[i] = true;
                alreadyCalled[nums[i] + 10] = true;
                uniquePermutations(nums, blockedCells, count + 1, ans, sans);
                sans.remove(sans.size() - 1);
                blockedCells[i] = false;
            }
        }

    }

    public int combinations(int[] arr, int k, int idx, List<List<Integer>> Ans, List<Integer> sans) {
        if (sans.size() == k) {
            List<Integer> bans = new ArrayList<>(sans);
            Ans.add(bans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            sans.add(arr[i]);
            count += combinations(arr, k, i + 1, Ans, sans);
            sans.remove(sans.size() - 1);
        }
        return count;
    }

    public int combinationSum(int[] arr, int idx, List<List<Integer>> Ans, List<Integer> sans, int tar) {
        if (tar == 0) {
            List<Integer> bans = new ArrayList<>(sans);
            Ans.add(bans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {

                sans.add(arr[i]);
                count += combinationSum(arr, i, Ans, sans, tar - arr[i]);
                sans.remove(sans.size() - 1);

            }
        }
        return count;
    }

    public int combinationsSumNoDuplicates(int[] arr, int idx, int tar, List<List<Integer>> Ans, List<Integer> sans) {
        if (tar == 0) {
            List<Integer> bans = new ArrayList<>(sans);
            Ans.add(bans);
            return 1;

        }
        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                if (i > idx && i > arr.length - 1 && i < arr.length && arr[i - 1] == arr[i])
                    continue;

                sans.add(arr[i]);
                count += combinationsSumNoDuplicates(arr, i + 1, tar - arr[i], Ans, sans);
                sans.remove(sans.size() - 1);
            }
        }
        return count;

    }

    public int combinationSum3(int k, int idx, int tar, List<List<Integer>> Ans, List<Integer> sans) {
        if (tar == 0) {
            if (sans.size() == k) {
                List<Integer> bans = new ArrayList<>(sans);
                Ans.add(bans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        for (int i = idx; i <= 9; i++) {
            if (tar - i >= 0) {
                sans.add(i);
                count += combinationSum3(k, i + 1, tar - i, Ans, sans);
                sans.remove(sans.size() - 1);
            }
        }
        return count;
    }

    public int powSet( int[] nums , int idx , List<Integer> ans , List<List<Integer>> Ans ){
        int count = 0;
        List<Integer> bans = new ArrayList<>(ans);
        Ans.add( bans );
        for( int i  = idx ; i < nums.length ; i++ ){
            if( i > idx && nums[i-1] == nums[i] ) continue;
            ans.add( nums[i] );
            count += powSet( nums , i + 1 , ans , Ans );
            ans.remove( ans.size() - 1 );
        }
         return count;
    }

    // dp
    //leetcode 70

    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        return csTab( n , dp );
    }
    
    public int csRec( int n , int idx ){
        if( idx == n ){
            return 1;
        }
        
        int count = 0;
        
        if( idx + 1 <= n ) count += csRec( n , idx  +1 );
        if( idx + 2 <= n ) count += csRec( n , idx + 2 );
        return count;
    } 
    
    public int csMem( int n , int idx , int[] dp){
        if( idx == n ){
            return dp[idx] = 1;
        }
        if( dp[idx] != 0 ){
            return dp[idx];
        }
        int count = 0;
        
        if( idx + 1 <= n ) count += csMem( n , idx + 1 , dp );
        if( idx + 2 <= n ) count += csMem( n , idx + 2 , dp );
        return dp[idx] = count;
    } 
    
    public int csTab( int n , int[] dp ){
        for( int idx  = n ; idx >= 0 ; idx-- ){
            if( idx == n ){
                 dp[idx] = 1;
                continue;
            }
            
            int count = 0;

            if( idx + 1 <= n ) count += dp[idx + 1];
            if( idx + 2 <= n ) count += dp[idx+2];
             dp[idx] = count;
        }
        return dp[0];
    }

//746. Min Cost Climbing Stairs

public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        return Math.min( minCostCSMem(cost , 0 , dp) , minCostCSMem(cost , 1 , dp));
    }
    

    
    public int minCostCSMem( int[] arr , int idx, int[] dp ){
        if( idx == arr.length ){
            return 0;
        }
        if( dp[idx] != 0 ) return dp[idx];
        
        int single = 100000;
        int doubl = 100000;

        if( idx + 1 <= arr.length )  single = minCostCSMem( arr ,idx+1 , dp );
        if( idx + 2 <= arr.length )  doubl = minCostCSMem( arr ,idx+2 , dp );

        
        return dp[idx] = arr[idx] + Math.min( single , doubl );
    }

//64. Minimum Path Sum

    public int minPathSum(int[][] grid) {
        
        int[][] dp =new int[grid.length][grid[0].length];
        return minPathSumMemo( grid , 0 , 0 ,dp );
        
    }
    public int minPathSumRecursive( int[][] arr , int sr , int sc  ){
        if( sr == arr.length-1 && sc == arr[0].length-1 ){
            return arr[sr][sc];
        }
        
        int right = (int)1e9;
        int down = (int)1e9;
        
        if( sr + 1 < arr.length ) down = minPathSumRecursive( arr ,sr+1, sc );
        if( sc + 1 < arr[0].length ) right = minPathSumRecursive( arr ,sr, sc+1 );
        
        return arr[sr][sc] + Math.min( right , down );

    }
    
    public int minPathSumMemo( int[][] arr , int sr , int sc , int[][] dp ){
        if( sr == arr.length-1 && sc == arr[0].length-1 ){
            return dp[sr][sc] = arr[sr][sc];
        }
        if( dp[sr][sc] != 0 ) return dp[sr][sc];
        
        int right = (int)1e9;
        int down = (int)1e9;
        
        if( sr + 1 < arr.length ) down = minPathSumMemo( arr ,sr+1, sc , dp );
        if( sc + 1 < arr[0].length ) right = minPathSumMemo( arr ,sr, sc+1 , dp );
        
        return dp[sr][sc] = arr[sr][sc] + Math.min( right , down );

    }



    ///Gold Mine Problem

    static int maxGold(int n, int m, int M[][])
    {
        int[][] dp  =new int[n][m];
        int ans = -(int)1e9;
        for( int r = 0 ; r < n ; r++ ){
            ans = Math.max( ans,  maxGoldMemo( M , r, 0 , dp ));
        }
        
        // for( int i  = 0 ; i  < n  ; i++ ){
        //     for( int  j  = 0 ; j  < m  ; j++ ){
        //         System.out.print( M[i][j] + " " );
        //     }
        //     System.out.println();
        // }
        // for( int i  = 0 ; i  < n  ; i++ ){
        //     for( int  j  = 0 ; j  < m  ; j++ ){
        //         System.out.print( dp[i][j] + " " );
        //     }
        //     System.out.println();
        // }
        return ans;
    }
    
    static int maxGold( int[][] arr  , int r , int c ){
        if( c == arr[0].length-1 ){
            return arr[r][c];
        }
        
        int dUp = -(int)1e9;
        int right = -(int)1e9;
        int dDown = -(int)1e9;
        
        if( r - 1 >= 0 && c + 1 < arr[0].length ) dUp = maxGold( arr, r-1 , c+1 );
        if( c + 1 < arr[0].length ) right = maxGold( arr, r , c+1 );
        if( r + 1 < arr.length && c + 1 < arr[0].length ) dDown = maxGold( arr, r+1 , c+1 );
        
        return arr[r][c] + Math.max( dUp , Math.max( right  , dDown ) );
    }
    static int maxGoldMemo( int[][] arr  , int r , int c , int[][] dp ){
        if( c == arr[0].length-1 ){
            return dp[r][c] = arr[r][c];
        }
        if( dp[r][c] != 0 ) return dp[r][c];
        
        int dUp = -(int)1e9;
        int right = -(int)1e9;
        int dDown = -(int)1e9;
        
        if( r - 1 >= 0 && c + 1 < arr[0].length ) dUp = maxGoldMemo( arr, r-1 , c+1 , dp );
        if( c + 1 < arr[0].length ) right = maxGoldMemo( arr, r , c+1 ,dp );
        if( r + 1 < arr.length && c + 1 < arr[0].length ) dDown = maxGoldMemo( arr, r+1 , c+1 ,dp );
        
        return dp[r][c] = arr[r][c] + Math.max( dUp , Math.max( right  , dDown ) );
    }


    //Friends Pairing

    static int mod = (int)1e9 + 7;
    public long countFriendsPairings(int n) 
    { 
       long[] dp = new long[n+1];
       return friendsPairingsDp( n  ,dp ) % mod;
    }
    
    public long friendsPairings( int n , long[] dp ){
        if( n <= 1 ) return dp[n] = 1;
        if( dp[n] != 0 ) return dp[n];
        long single = friendsPairings( n-1  , dp);
        long makePair  = friendsPairings( n-2 , dp) * (n-1);
        
        return dp[n] = single + makePair;
    }
    
    public long friendsPairingsDp( int N , long[] dp ){
        for( int n = 0 ; n <= N ; n++){
            if( n <= 1 ){ 
                dp[n] = 1;
                continue;
            }
            
            long single = dp[n-1];
            long makePair  = dp[n-2] * (n-1);
            dp[n] = ( single + makePair ) % mod;
        }
        return dp[N];
    }

    

    
}
