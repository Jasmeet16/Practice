// Set matrix zeros
// O( m + n ) space
// Algo for O1 sapce
// choose a reference row and col istead of using aditional row and col array
//let say we choose 0th row and 0th  col
// take two boolean variables to check if 0th row and 0th col needs to be set zero
//and rest do the same as following just istead of markin row and col array we mark 0th row and 0th col of same given matrix
public void setZeroes(int[][] matrix) {
    boolean[] row = new boolean[ matrix.length ];
    boolean[] col = new boolean[ matrix[0].length ];
    for( int i = 0  ; i < matrix.length ; i++ ){
       for( int  j = 0 ; j < matrix[0].length ; j++ ){
           if( matrix[i][j] == 0 ){
               row[i] = true;
               col[j] = true;
           }
       }
   }
   for( int i = 0 ; i < matrix.length ; i++ ){
    for( int j = 0 ; j < matrix[0].length ; j++ ){
        if( row[i] || col[j] ) matrix[i][j] = 0;
    }
}
}

// pascal triangle

public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> def = new ArrayList<>();
    def.add(1);
    ans.add( def  );
    for( int i = 1; i < numRows ; i++ ){
        List<Integer> in = new ArrayList<>();
        in.add(1);
        for( int j  = 1 ; j < i ; j++ ){
            // getting previous rows (i-1) required elements j-1 and j
            in.add( ans.get(i-1).get(j-1) + ans.get(i-1).get(j) );
        }
        in.add(1);
        ans.add(in);
    }
    return ans;
}

// next permution
//traversing from behind if numbers are increasing then no possible arragement can make a just greater number
// in first while loop we try to find the point where numbers are decreasing ( looking from behind )
// ex 2 3 1 in this ex going from 3 to 2 its decreasing so 2 is our target
// no we find the number with which it is best to swap 2 with
// so in remaing arrray ex 3 1 traversing from behind we try to find no. just greater than 2
// swap it
// and sort remaining part
public void nextPermutation(int[] nums) {

    int i = nums.length -1;
    while( i >= 1 && nums[i-1] >= nums[i]){
        i--;
    }
    int itoswapwith = nums.length-1;
    if(i > 0){
        for( int k =  nums.length-1  ; k >= i ; k-- ){
            if(  nums[k] > nums[i-1] ){
                itoswapwith = k;
                break;
            }
        }
        swap( nums , i-1 , itoswapwith );
    }
    sort( nums , i , nums.length );

}
public void swap( int[] nums ,int i  , int j ){
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
}

public void sort( int[] arr , int s , int e ){

    for( int i = 0; i < e-s ; i++ ){
        for( int j = s ; j < e-1; j++ ){
            if( arr[j] > arr[j+1] ){
                swap( arr , j , j+1 );
            }
        }
    }
}


// count inversion
// ditto same code as merge sort
public static long count = 0;
static long inversionCount(long arr[], long N)
{
    int n= (int)N;
    solve( arr , 0 , n-1 );
    return count;
}

public static long[] solve( long[] arr , int si , int ei ){

    if( si == ei ){
        return new long[]{arr[si]};
    }

    int mid = ( si + ei ) / 2;
    long[] left = solve( arr , si  ,mid  );
    long[] right = solve( arr , mid+1 , ei );

    return countInversion( left , right );
}

public static long[] countInversion( long[] left , long[] right  ){
    int  i  = 0;
    int j  = 0;
    long[] ans = new long[ left.length  + right.length ];
    int k = 0;

    while( i<left.length && j < right.length ){
        if( left[i] <= right[j] ){
            ans[k++] = left[i++];
        }else{
            //only add this line in merge sort to track the inversions
            count += left.length  - i;
            ans[k++] = right[j++];
        }
    }
    while( i < left.length ){
        ans[k++] = left[i++];
    }
    while( j < right.length ){
        ans[k++] = right[j++];
    }
    return ans;
}

//rotate image

public void rotate(int[][] matrix) {
    int n = matrix.length;
    for( int i =  0 ; i  < n  ; i++ ){
        for( int j  = i ; j  < n ; j++ ){
            int t = matrix[i][j];
            matrix[i][j]  = matrix[j][i];
            matrix[j][i] = t;
        }
    }
    for( int row = 0 ; row < n ; row++ ){
        int i  = 0;
        int j = n-1;
        while( i < j ){
            int t = matrix[row][i];
            matrix[row][i++]  = matrix[row][j];
            matrix[row][j--] = t;
        }
    }
    
}

//excel number
public int titleToNumber(String columnTitle) {
    int n  = columnTitle.length()-1;
    int ans = 0;
    ans += (columnTitle.charAt(n) - 'A') + 1;
    int mf = 26;

    for( int i = n-1 ;i >= 0 ; i-- ){
        int ascii = (columnTitle.charAt(i) - 'A') + 1;
        ans += (ascii * mf);
        mf *= 26;
    }
    return ans;
}  

//Find n^x in log N 
public double myPow(double x, int n) {
   return n < 0 ? 1 / myPow2(x,n) : myPow2( x , n );
}

public double myPow2(double x, int n) {
    if( n == 0 ) return 1;
    double res = myPow2( x , n/2 );
    if( n % 2  == 0 ){
        return res*res;
    }
    return res * res * x;
}

// Count trailing zeros in factorial of a number 

// just find the occurances of 5 in it
// there a formula for that

//Find GCD in Log N

// recursive relation => gcd( a , b ) = gcd( b , a%b )
// base case ==> gcd( a , 0 ) => 0