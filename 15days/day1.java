
//find the duplicate
//given integer from range 1 to n 
// nums.length == n + 1
public int findDuplicate(int[] nums) {
	public int findDuplicate(int[] nums) {
        //initilize s and f in this way only
		int s = nums[0];
		int f = nums[ nums[0] ];
        //  System.out.println( " s at idx " + s + " val " + nums[s]  );
        //  System.out.println( " f at idx " + f + " val " + nums[f]  );
		while( s != f ){
			s = nums[s];
			f = nums[ nums[f] ];
     		//  System.out.println( " s at idx " + s + " val " + nums[s]  );
        	// System.out.println( " f at idx " + f + " val " + nums[f]  );
		}
		// set s to 0  not nums[0] this will give tle
		s = 0;
		while( s != f ){
			s = nums[s];
			f = nums[f];
		}
		// return pointer index not element at that index
		return s;
	}
}

//Sort an array of 0’s 1’s 2’s without using extra space or sorting algo 

// we assure that everything between p0 and p1 is sorted and we approach the rest for array with pointers ant correct location
public void sortColors(int[] nums) {
	int p0 = 0;
	int p1 = 0;
	int p2 = nums.length-1;
	while( p1 <= p2 ){
		if( nums[p1] == 0 ){
			swap( nums , p0 , p1 );
			p0++;
			p1++;
		}else if( nums[ p1 ] == 2 ){
			swap( nums , p1 , p2 );
			p2--;
		}else{
			p1++;
		}
	} 
}

public static void swap( int[] arr , int i , int j ){
	int temp  = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
}

// 3. Repeat and Missing Number 
public int missingNumber(int[] nums) {
	int n = nums.length;
	int reqSum = n;
	int acSum  = 0;

	for( int i = 0 ; i < n  ; i++ ){
		reqSum += i;
		acSum  += nums[i];
	}
	return reqSum - acSum;
}
// with xor
//If anyone needs explanation for XOR solution, a number XOR itself will become 0, any number XOR with 0 will stay unchanged.
// So if every number from 1...n XOR with itself except the missing number, the result will be the missing number.
public int missingNumber(int[] nums) { //xor
	int res = nums.length;
	for(int i=0; i<nums.length; i++){
		res ^= i;
		res ^= nums[i];
	}
	return res;
}

//Merge two sorted Arrays without extra space 

public static void merge(long arr1[], long arr2[], int n, int m) 
{

	//we start comparing from last element because both the arrays are sorted
	// if while tarversing back an element in arr1 is found which is geater than el at j idx
	// that means that ele should be in arr2 so we swap 
	// and start finding the correct loction for the el with which it is swapped
	int i = n-1;
	int j = m-1;
	while( j >= 0 ){
		long l = arr2[j];
		if( arr1[i] > arr2[j] ){
			arr2[j] = arr1[i];
			int k = i;
                // to shift the first arr till correct position for el ( l ) isfound
			while( k > 0 && arr1[k-1] > l ){
				long t = arr1[k-1];
				arr1[k-1] = arr1[k];
				arr1[k--] = t;
			}
                // after this while loop k will be at correct location
			arr1[k] = l;
		}
		j--;
	}
}

//Kadane’s Algorithm 

public int maxSubArray(int[] nums) {
	int gSum = nums[0];
	int sum = nums[0];
	for( int i =1 ; i < nums.length ; i++ ){

        	// we check which is better option
        	// to contribute to running sum
        	// or to start a new sum from ith point
		if( (sum + nums[i]) > nums[i]){
			sum+= nums[i];
		}else{
			sum = nums[i];
		}
		gSum = Math.max( gSum , sum );
	}
	return gSum;
}

//Merge Overlapping Subintervals 

public int[][] merge(int[][] intervals) {
	Arrays.sort( intervals , ( a , b )->{
		return a[0] - b[0]; 
	} );
	Stack<int[]> st = new Stack<>();
	for( int[] a : intervals ){
		if( !st.isEmpty() ){
			int[] top = st.peek();
			int sTime = top[0];
			int eTime = top[1];
			if( a[0] <= eTime ){
				st.pop();
				st.push( new int[]{ sTime , Math.max( eTime , a[1] ) } );
				continue;
			}
		}
		st.push( a );
	}
	int[][] ans = new int[ st.size() ][2];
	Stack<int[]> st2 = new Stack<>();
	
	while( !st.isEmpty() ){
		st2.push( st.pop() );
	}
	int i = 0;
	while( !st2.isEmpty() ){
		ans[i++]  = st2.pop();
	}
	return ans;
}