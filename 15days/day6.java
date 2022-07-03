// N meeting in one room
public static int maxMeetings(int start[], int end[], int n)
{
	int[][] arr  = new int[n][2];
	for( int i = 0 ;  i< n ;i++ ){
		arr[i][0] = start[i];
		arr[i][1] = end[i];
	}
	Arrays.sort( arr , (a,b)->{
		return a[1] - b[1];
	} );
	int ans = 1;
	int lastChosen  = arr[0][1];
	for( int  i = 1 ;  i< n ; i++ ){
		if( lastChosen < arr[i][0] ){
			ans++;
			lastChosen = arr[i][1];
		}
	}
	return ans;
}

//Activity Selection  
// same problem

// min coin denomination

public static minCoin( int amount ,  int[] coins ){
	Arrays.sort( coins );
	int ans = 0;
	int n = coins.length - 1;
	for( int i = n ;  i>=0 ; i-- ){
		while( amount - coins[i] >= 0 ){
			ans++;
		}
	}
	return ans;
}


//Minimum Platforms
//all the trains arrive on the same day and leave on the same day.

static int findPlatform(int arr[], int dep[], int n)
{
	Arrays.sort( arr );
	Arrays.sort( dep );
	int maxP = 1;
	int i=1;
	int j= 0;
	int trains = 1;
	while( i < n ){
		if( arr[i] <= dep[j] ){
			trains++;
			i++;
		}else{
			trains--;
			j++;
		}
		maxP = Math.max( maxP , trains );
	}
	return maxP;

}

//Job sequencing Problem
// algo
// we try to do the job on the last day
// so that we can accomodate more and more jobs in previous days
// because we want to eearn more profit we sort the array in dec order wrt profit
int[] JobScheduling(Job arr[], int n)
{
	//get the last deadline
	int ld = 0;
	for( Job j : arr ){
		ld = Math.max( ld , j.deadline );
	}
	// will store job ids in the order in which jobs are done
	int[] jobSeq = new int[ld+1];
	Arrays.fill( jobSeq , -1 );
	jobSeq[0] = 0;

	Arrays.sort( arr , (a,b)->{
		return b.profit - a.profit; 
	});

	int pro = 0;
	int num = 0;
	
	for( Job j : arr ){
		int dl = j.deadline;
		while(  dl > 0  && jobSeq[dl] != -1 ) dl--;
		//System.out.println(dl);
		if( dl != 0 ){
			jobSeq[ dl ] = j.id;
			pro += j.profit;
			num++;
		} 
	}
	return new int[]{num,pro};
}

//Fractional Knapsack Problem 

double fractionalKnapsack(int W, Item arr[], int n) 
{
	Arrays.sort( arr  , ( a , b )->{
		if(  ((double)b.value / (double)b.weight) - ( (double)a.value / (double)a.weight ) <= 0 ) {
			return -1;
		}else{
			return 1;
		}
	} );
	double profit = 0;
	int i = 0;
	while( W != 0 && i < n ){
		Item it = arr[i];
		if( it.weight <= W ){
			profit += it.value;
			W -= it.weight;
		}else{
			break;
		}
		i++;
	}
	if( i < n  ){
        // System.out.println( ( arr[i].value / arr[i].weight ) * W ); 
		profit += (double)( (double)arr[i].value / (double)arr[i].weight ) * W;
	}
	return profit;
}


// N Queens ( total number of combinations )

static int count;
public int totalNQueens(int n) {
	count = 0;
	boolean[][] bo  =new boolean[n][n];
	getComb( bo , n , 0 );
	return count;

}
public static void getComb( boolean[][] bo ,  int q , int r  ){
	if( q == 0 ){
		count++;
		return;
	}
	for( int c = 0 ; c < bo[0].length ; c++ ){
		if( isSafe( bo , r , c ) ){
			bo[r][c] = true;
			getComb( bo ,  q-1 , r + 1 );
			bo[r][c] = false;
		}
	}
}
public static boolean isSafe( boolean[][] bo , int r , int c ){
        //check col
	for( int i = r-1 ; i >= 0 ; i-- ){
		if( bo[i][c] ) return false;
	}
        //check left diagnol
	for( int i = r-1 , j = c-1 ; i >= 0 && j >= 0 ; i--,j-- ){
		if( bo[i][j] ) return false;
	}
        // check right diagnol
	for( int i = r-1 , j = c+1 ; i >= 0 && j < bo[0].length ; i-- ,j++ ){
		if( bo[i][j] ) return false;
	}
	return true;
}

// valid sudoku

public boolean isValidSudoku(char[][] board) {
	boolean[][] rows= new boolean[9][10];
	boolean[][] cols = new boolean[9][10];
	boolean[][] grid3 = new boolean[9][10];
	
	for( int i = 0  ; i < 9 ; i++ ){
		for( int j = 0 ; j < 9 ; j++ ){
			if( board[i][j] != '.' ){
				int num = board[i][j] - '0';
				
                //mark row
				if( rows[i][ num ] ){
                    //already marked
					return false;
				}else{
					rows[i][num] = true; 
				}
				
                //mark col 
				if( cols[j][ num ] ){
                    //already marked
					return false;
				}else{
					cols[j][num] = true; 
				}
				
                //grid 3 x 3
				
				int gridRow = 3 * ( i / 3 ) + ( j /3 );
				if( grid3[ gridRow ][ num ] ){
                    //already marked
					return false;
				}else{
					grid3[ gridRow ][num] = true; 
				}
			}
		}
	}
	return true;
}