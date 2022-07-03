 // Find the element that appears once in sorted array, and rest element appears twice
// just observe the pattern of odd even index elements
public int singleNonDuplicate(int[] arr) {
    if( arr.length == 1 ){
        return arr[0];
    }
    if( arr[0] != arr[1] ) return arr[0];
    if( arr[ arr.length-1 ] != arr[arr.length-2] ) return arr[arr.length-1];
    int lo = 0;
    int hi = arr.length-1;
    while( lo <= hi ){
        int mid = ( lo + hi ) / 2;

        if( arr[mid] != arr[mid-1] && arr[mid] != arr[mid+1] ){
            return arr[mid];
        }

        if(  mid % 2 == 0 ){
            if( arr[mid] == arr[mid+1] ){
                lo = mid+1;
            }else{
                hi = mid-1;
            }  
        }else{
            if( arr[mid] == arr[mid+1] ){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
    }
    return -1;
}

//Search element in a sorted and rotated array

public int search(int[] nums, int target) {

    int lo = 0;
    int hi  = nums.length-1;
    
    while( lo <= hi ){
        int mid = ( lo + hi ) / 2;
        if( nums[mid] == target ) return mid;
        
        if( nums[lo] <= nums[mid] ){
            if( target >= nums[lo] && target <= nums[mid] ){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }else{
            if( target >= nums[mid] && target <= nums[hi] ){
                lo = mid+1;
            }else{
             hi = mid-1;

         }
     }
 }
 return -1;
}

//Sliding Window maximum

public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] ans = new int[n-k+1];
    int[] nger = nger( nums );
    for( int i = 0 ; i <= n-k ; i++ ){
        int j = i;
        ans[i] = nums[j];
        while( j < i+k ){
            ans[i] = nums[j];
            j = nger[j];
        }
    }
    return ans;
}

public static int[] nger( int[] arr ){
    int n = arr.length;
    int[] ans = new int[n];
    Arrays.fill( ans ,n );
    Stack<Integer> st = new Stack<>();
    for( int i = 0  ; i < n ; i++ ){
        while( !st.isEmpty() && arr[st.peek()] < arr[i] ){
            ans[st.pop()] = i;
        }
        st.push(i);
    }
    return ans;
}

//min stack

class MinStack {

    /** initialize your data structure here. */
    Stack<Long> st;
    Long min;
    public MinStack() {
        st = new Stack<>();
        min = (long)0;
    }
    
    public void push(int val) {
        Long x = (long)val;
        if( st.size() == 0 ){
            st.push(x);
            min = x;
            return;
        }
        
        if( x < min ){
            // while pushing
            // 2  * val - prevMin
            st.push( (x-min) + x );
            min = x;
        }else{
            st.push(x);
        }
    }
    
    public void pop() {
        if( st.peek() < min ){
            //  if min is removed 
            // new min will be
            // 2 * min - remove val
            min = ( min - st.peek()) + min;
        }
        st.pop();
    }
    
    public int top() {
        if( st.peek() < min ){
            return (int)(long)min;
        }
        return (int)(long)st.peek();
    }
    
    public int getMin() {
        return (int)(long)min;
    }
}

