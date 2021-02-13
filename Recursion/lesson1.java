//LeetCode 62

//recursive
public int uniquePaths(int m, int n) {
        return uniquePaths( 0 , 0, m-1 , n-1 );
    }
public int uniquePaths( int sr , int sc , int er , int ec ){
        if( sr == er && sc == ec ) return 1;
        
        int count = 0;
        // move right
        if( sr + 1 <= er ) count += uniquePaths( sr +1 , sc , er , ec );
        //move down
        if( sc +1 <= ec ) count += uniquePaths( sr , sc +1 , er , ec );
        
         return count;
  }

//memoization
  public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return uniquePaths( 0 , 0, m-1 , n-1 , dp );
    }
    public int uniquePaths( int sr , int sc , int er , int ec , int[][] dp ){
        if( sr == er && sc == ec ) return dp[sr][sc]=1;
        if( dp[sr][sc] != 0 ) return dp[sr][sc];
        int count = 0;
        // move right
        if( sr + 1 <= er ) count += uniquePaths( sr +1 , sc , er , ec , dp);
        //move down
        if( sc +1 <= ec ) count += uniquePaths( sr , sc +1 , er , ec , dp );
        
         return dp[sr][sc] = count;
    }

//Leetcode 63
//with obstacles
    //recursive
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return uniquePaths( 0 , 0 , obstacleGrid.length  -1 , obstacleGrid[0].length-1 , obstacleGrid  );
    }
    public int uniquePaths( int sr  , int sc , int er , int ec , int[][] grid ,int[][] dp ){
        if( sr == er && sc == ec ) return 1;
        
        int count = 0;
        
        if( sr +1 <= er && grid[sr+1][sc] != 1 ) count += uniquePaths( sr +1  , sc, er , ec , grid  );
        if( sc +1 <= ec && grid[sr][sc+1] != 1 ) count += uniquePaths( sr, sc +1 , er , ec , grid );
        
        return count;
    }

    //memoization
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if( obstacleGrid[0][0] == 1 || obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1] == 1 )return 0;
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        return uniquePaths( 0 , 0 , obstacleGrid.length  -1 , obstacleGrid[0].length-1 , obstacleGrid ,  dp );
    }
    public int uniquePaths( int sr  , int sc , int er , int ec , int[][] grid ,int[][] dp ){
        if( sr == er && sc == ec ) return dp[sr][sc]=1;
        if( dp[sr][sc] != 0 ) return dp[sr][sc];
        int count = 0;
        
        if( sr +1 <= er && grid[sr+1][sc] != 1 ) count += uniquePaths( sr +1  , sc, er , ec , grid , dp );
        if( sc +1 <= ec && grid[sr][sc+1] != 1 ) count += uniquePaths( sr, sc +1 , er , ec , grid , dp);
        
        return dp[sr][sc]=count;
    }

//Rat in maze with multiple jumps

  
    public static void main (String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        while( t-->0 ){
            int n = scn.nextInt();
            int[][] arr = new int[n][n];
            for( int i = 0 ; i < n ; i++ ){
                for( int j = 0 ; j < n ; j++ ){
                    arr[i][j] = scn.nextInt();
                }
            }
            int[][] ans = new int[n][n];
            if( arr[0][0] == 0 || arr[n-1][n-1] == 0 || solve( arr , 0 , 0 , n-1, n-1, ans ) == 0 ){
                System.out.println("-1");
            }
        }
        
    }
    public static int solve( int[][] arr , int sr , int sc , int er , int ec , int[][] ans ){
        ans[sr][sc] = 1;
        if( sr == er && sc == ec){
            for( int i = 0 ; i < arr.length ; i++ ){
                for( int j = 0 ; j < arr.length ; j++ ){
                    System.out.print(ans[i][j] + " " );
                }
                System.out.println();
            }
            return 1;
        }
        int count = 0;
        for( int jmp = 1 ; jmp <= arr[sr][sc] ; jmp++ ){
            if( sc + jmp <= ec && arr[sr][sc+jmp] > 0 ) {
                count += solve( arr , sr , sc+jmp , er, ec ,ans );
                if( count > 0 ){
                    return count;
                }
            }
        }
        for( int jmp = 1 ; jmp <= arr[sr][sc] ; jmp++ ){
            if( sr + jmp <= er && arr[sr+jmp][sc] > 0 ) {
                count += solve( arr , sr+jmp , sc , er, ec ,ans );
                if( count > 0 ){
                    return count;
                }
            }
        }
        
        ans[sr][sc] = 0;
        return count;
    }

    //gfg Special Matrix
    public int FindWays(int n, int m, int[][] blocked_cells)
    {
        
        int[][] arr = new int[n][m];
        int[][] dp = new int[n][m];
        for( int[] a: blocked_cells ){
            int i = a[0]-1;
            int j = a[1]-1;
            if( (i == n-1 && j == m-1) || (i==0 && j==0) ) return 0;
            arr[i][j] = 1;
        }
        return solve(0 , 0,  n-1, m-1 , arr ,dp);
    }
    public int solve( int sr , int sc , int er , int ec , int[][] arr , int[][] dp ){
        if( sr == er && sc == ec ){
            return dp[sr][sc] = 1;
        }
        if( dp[sr][sc] != 0 ) return dp[sr][sc];
        int count = 0;
        if( sr+1 <= er && arr[sr+1][sc] == 0 ){
            count += solve( sr +1 , sc ,er ,ec ,arr  , dp);
        }
        if( sc +1 <= ec && arr[sr][sc+1] == 0 ){
            count += solve( sr , sc+1 , er , ec ,arr , dp);
        }
        return dp[sr][sc] = count;
    }



//LeetCode 1219

    public int getMaximumGold(int[][] grid) {
        int max = -(int)1e9;
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        for( int i  = 0 ; i < grid.length ; i++ ){
            for( int j = 0 ; j < grid[0].length ; j++ ){
                max = Math.max( max, getMax( grid , i , j , dir ) );
            }
        }
        return max;
    }
    
    public int getMax( int[][] arr , int sr , int sc , int[][] dir ){
        int temp = arr[sr][sc];
        arr[sr][sc] = -1;
        
        int max = 0;
        for( int[] d : dir ){
            int r = sr + d[0];
            int c = sc + d[1];
            if( r >= 0 && c >= 0 && r < arr.length && c < arr[0].length && arr[r][c] > 0 ){
                max = Math.max( getMax( arr , r , c , dir )  , max );
            }
        }
        arr[sr][sc] = temp;
        return max + arr[sr][sc];
    }

// leetcode uniqe paths 3
    // every zero needs to be visited
public int uniquePathsIII(int[][] grid) {
        int sr = 0 , sc = 0, er=0 ,ec=0;
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        int countOfZeros = 2;
        for( int i  = 0 ; i  < grid.length  ; i++ ){
            for( int j = 0 ; j < grid[0].length ; j++ ){
                if( grid[i][j] == 1 ){
                    sr = i;
                    sc = j;
                }
                if( grid[i][j] == 0 ) countOfZeros++;
                if( grid[i][j] == 2 ){
                    er = i;
                    ec = j;
                }
            }
        }
        return uniquePaths( sr , sc , er ,ec , grid , dir , countOfZeros-1 );
    }
    public int uniquePaths( int sr , int sc ,int er , int ec , int[][] grid , int[][] dir , int countOfZeros  ){
        if( sr == er && sc == ec ) return countOfZeros == 0 ? 1 : 0;
        int temp = grid[sr][sc];
        grid[sr][sc] = -1;
        int count = 0;
        for( int[] d : dir ){
            int r = sr + d[0];
            int c = sc + d[1];
            if( r  >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] >= 0 ){
                count += uniquePaths( r ,c , er , ec , grid , dir , countOfZeros-1 );
            }
        }
        grid[sr][sc] = temp;
        return count;
    }