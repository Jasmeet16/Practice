//2 Sum problem 

public int[] twoSum(int[] nums, int target) {
	int[] ans = new int[2];
	HashMap<Integer, Integer> hm = new HashMap<>();
	for( int i = 0 ; i < nums.length ; i++ ){
		if( hm.containsKey( target - nums[i] ) ){
			ans[0] = hm.get( target - nums[i] );
			ans[1] = i;
			break; 
		}
		hm.put( nums[i] , i );
	}
	return ans;
}

// 2 sum for a sorted array

public int[] twoSum(int[] numbers, int target) {
	int i = 0 ;
	int j = numbers.length-1;
	int[] ans = new int[2];
	while( i < j ){
		if( numbers[i] + numbers[j] < target ){
			i++;
		}else if( numbers[i] + numbers[j] > target ){
			j--;
		}else {
			ans[0] = i;
			ans[1] = j;
			break;
		}
	}
	return ans;
}

//4 Sum problem

//generalized k sum 


public List<List<Integer>> fourSum(int[] nums, int target) {
	Arrays.sort( nums );
	return kSum( nums , 4 , 0 , target );
}
public static List<List<Integer>> twoSumList( int[] arr , int si , int ei , int tar ){
	List<List<Integer>> res = new ArrayList<>();
	while( si < ei ){
		if( arr[si] + arr[ei] < tar ){
			si++;
		}else if( arr[si] + arr[ei] > tar ){
			ei--;
		}else{
			List<Integer> ans = new ArrayList<>();
			ans.add(arr[si++]);
			ans.add( arr[ei--] );
			// to skip  duplicates
			while( si < ei && arr[si-1] == arr[si] ) si++;
			while( si < ei && arr[ei] == arr[ei+1] ) ei--;
			res.add(ans);
		}
	}
	return res;
}

public static List<List<Integer>> kSum( int[] arr , int k , int si , int tar ){
	List<List<Integer>> ans = new ArrayList<>();
	if( k == 2 ){
		List<List<Integer>> twoSum =  twoSumList( arr , si , arr.length - 1 , tar );
		return twoSum;
	}else{
		for( int i = si ; i < arr.length-(k-1) ; i++ ){
			//skip duplicates focus  on si It needs to be si not > 0 but > si
			if( i > si && arr[i-1] == arr[i] ) continue;
			//Main Logic
			List<List<Integer>> res = kSum( arr , k - 1 , i+1 , tar - arr[i] );
			if( res != null  ){
				for( List<Integer> ls : res ) ls.add( 0 , arr[i] );
			}
		ans.addAll(res);
	}
}
return ans;
}

//longest consecutive sequence

public int longestConsecutive(int[] nums) {
	HashSet<Integer> hs = new HashSet<>();
	for( int a : nums ){
		hs.add(a);
	}
	int max = 0;
	for( int i = 0 ;i < nums.length ; i++ ){
		int len = 1;
		if( hs.contains(nums[i]) ){
			int nxt = nums[i]+1;
			int prev = nums[i]-1;
			while( hs.contains(nxt) ){
				hs.remove(nxt++);
				len++;
			}
			while( hs.contains(prev) ){
				hs.remove(prev--);
				len++;
			}

		} 
		max = Math.max( max, len );
	}
	return max;
}

//Longest Subarray with 0 sum 
// exmaple -1 1 -1 1
// at 3 idx CSum gets 0 this is why we put 0 ,  -1 at first in map
//put statement must always be in else because we do not want to update existing index in map
int maxLen(int arr[], int n)
{
	HashMap< Integer , Integer > hm = new HashMap<>();
	//initializing map
	hm.put( 0 , -1 );
	int max = 0;
	int cSum = 0;
	for( int i = 0 ; i < n ; i++ ){
		cSum += arr[i];
		if( hm.containsKey( cSum ) ){
			int len = i - hm.get( cSum );
			max = Math.max( len , max );
		}else{
			hm.put( cSum , i );
		}
	}
	return max;
} 

// xor Queries in range
// if x = y ^ k
//then k = x ^ y
//  ____x___
//  prefix xor array 
// _y_ __k__

public int[] xorQueries(int[] arr, int[][] queries) {
	int n = arr.length;
	int[] pre = new int[n];
	int cXor= 0;
	for( int i = 0 ; i < n ; i++ ){
		cXor = (cXor ^ arr[i]);
		pre[i] = cXor;
	}
	int[] ans = new int[queries.length];
	int i = 0;
	for( int[] q : queries ){
		int si = q[0];
		int ei = q[1];
		if( si == 0 ){
			ans[i++] = pre[ei];
		}else{
			ans[i++] = (pre[ei] ^ pre[si-1]);

		}
	}
	return ans;
}


//Longest substring without repeat 
// mix  of two pointer and hs
//algo
// we move j  pointer till there are no duplicates
// when a duplicate is found we start moving pointer i 
// and move it till that char is removed from hs
// in this way substring is ensured and max number of char present in hs at anytime is ans
public int lengthOfLongestSubstring(String s) {
	int n = s.length();
	HashSet<Character> hs = new HashSet<>();
	int count = 0;
	int i = 0;
	int j = 0;
	while( i < n ){
		if( j < n  && !hs.contains( s.charAt(j) ) ){
			hs.add( s.charAt(j) );
			count = Math.max( count , hs.size() );
			j++;
		}else{
			hs.remove( s.charAt(i++) );
		}
	}
	return count;
}