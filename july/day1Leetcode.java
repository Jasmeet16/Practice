//200. Number of Islands
public int numIslands(char[][] grid) {
    int[][] dir = {{0,1} , {1,0} , {0,-1} , {-1,0} };
    int count = 0;
    for( int i = 0  ; i < grid.length ; i++ ){
        for( int j = 0 ; j < grid[0].length ; j++ ){
            if( grid[i][j] == '1' ){
                dfs( grid , i , j , dir );
                count++;
            }
        }

    }
    return count;
}

public static void dfs( char[][] grid , int i , int j , int[][] dir ){

    grid[i][j] = '0';
    for( int[] d : dir ){
        int r = i + d[0];
        int c = j + d[1];

        if( r >= 0 && c >=0 && r < grid.length && c < grid[0].length && grid[r][c] == '1' ){
            dfs( grid , r , c, dir );
        }
    }
}

//

public int maxAreaOfIsland(int[][] grid) {
    int[][] dir = {{0,1} , {1,0} , {0,-1} , {-1,0} };
    int ans = 0;
    for( int i = 0  ; i < grid.length ; i++ ){
        for( int j = 0 ; j < grid[0].length; j++ ){
            if( grid[i][j] == 1 ){
                int count = dfs( grid , i , j , dir );
                ans = Math.max( ans , count );
            }
        }
    }
    return ans;
}

public static int dfs( int[][] grid , int i , int j , int[][] dir ){

    grid[i][j] = 0;
    int count= 0;
    for( int[] d: dir ){
        int r = i + d[0];
        int c = j + d[1];
        if( r>=0 && c>=0 && r<grid.length && c<grid[0].length && grid[r][c] == 1 ){
            count += dfs( grid , r , c , dir );
        }
    }
    return count+1;

}