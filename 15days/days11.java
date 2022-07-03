// circular nge

public int[] nextGreaterElements(int[] nums) {
	return nge( nums );
}

public static int[] nge( int[] nums ){
	int n = nums.length;
	int[] ans =  new int[n];
	Arrays.fill( ans , -1 );

	Stack<Integer> st = new Stack<>();

	for( int i  = 0 ; i < 2*n - 1  ; i++ ){
		while( !st.isEmpty() && nums[st.peek()] < nums[i%n] ){
			ans[st.pop()] = nums[i%n];
		}
		st.push(i%n);
	}
	return ans;
}

//next smaller element

public static int[] nse( int[] arr ){
	int n = arr.length;
	int[] ans =  new int[n];
	Arrays.fill(n);
	Stack<Integer> st =  new Stack<>();

	for( int i = 0 ;  i < n ; i++ ){
		while( !st.isEmpty() && arr[ st.peek() ] > arr[i] ){
			ans[st.pop()] = arr[i];
		}
		st.push(i);
	}
	return ans;
}


//Check for balanced parentheses 

public boolean isValid(String s) {
	Stack<Character> st = new Stack<>();

	for( int i =  0 ; i < s.length() ; i++ ){
		char ch  = s.charAt(i);
		if( !st.isEmpty() && ( (  ch == ')'   && st.peek() == '(' )  ||  (  ch == '}'   && st.peek() == '{' ) || (  ch == ']'   && st.peek() == '[' ) )){
			st.pop();
		}else{
			st.push( ch );
		}
	}
	return st.isEmpty();
}

//Trapping Rain Water

public int trap(int[] height) {

	if( height.length == 0 ) return 0;
	int[] mor = maxOnRight( height );
	int[] mol = maxOnLeft(height);
	int ans = 0;
	for( int i  =0 ; i < height.length  ; i++ ){

		int waterToStoreOnI = Math.min( mor[i] , mol[i] )-height[i]; 
		ans += waterToStoreOnI;

	}
	return ans;
}

public static int[] maxOnRight( int[] arr ){
	int n = arr.length;
	int[] ans = new int[n];
	int max = arr[n-1];
	for( int i = n-1 ; i >= 0 ; i-- ){
		max = Math.max( arr[i] , max );
		ans[i] = max; 
	}
	return ans;
}

public static int[] maxOnLeft( int[] arr ){
	int n = arr.length;
	int[] ans = new int[n];
	int max = arr[0];
	for( int i = 0 ; i < n  ; i++ ){
		max = Math.max( max , arr[i] );
		ans[i] = max;
	}

	return ans;
}

// trapping rainwater stack solution

public int trap(int[] height) {
	Stack<Integer> st  = new Stack<>();
	int ans = 0;

	for( int i  = 0 ; i < height.length ; i++ ){

		while( !st.isEmpty() && height[st.peek()] < height[i] ){
			int poped = st.pop();
			if(st.isEmpty()) continue;
			int prev = st.peek();
			
			ans += ( Math.min( height[i] , height[prev] ) - height[poped] ) * (i - prev - 1) ;
		}

		st.push(i);

	}
	return ans;
}

// Lru Cache

class LRUCache {
	
	private class Node{
		int key =0 ;
		int val = 0;
		Node next = null;
		Node prev = null;
		Node( int val , int key ){
			this.val = val;
			this.key = key;
		}
	}
	private Node head = null;
	private Node tail = null;
	private int maxsize = 0;
	private int size  = 0;
	private HashMap<Integer , Node> hm = new HashMap<>();
	
	public void addLast( Node node ){
		if( this.head == null ){
			this.head = node;
			this.tail = node;
		}else{
			node.prev = this.tail;
			this.tail.next = node;
			this.tail = node;
		}
		this.size++;

	}
	
	public void removeNode( Node node ){
		if( node.prev != null && node.next != null ){
			Node prev = node.prev;
			Node next = node.next;
			prev.next = next;
			next.prev = prev;
			node.next = null;
			node.prev = null;
		}else if( this.size == 1 ){
			this.head = null;
			this.tail = null;
			node.next = null;
			node.prev = null;
		}else if( node == this.tail ){
			Node prev = node.prev;
			this.tail = prev;
			node.prev = null;
		}else if( node == this.head ){
			Node next = node.next;
			this.head = next;
			this.head.prev = null;
			node.next = null;
		}
		
		this.size--;
	}
	public LRUCache(int capacity) {
		this.maxsize = capacity;
	}
	
	public int get(int key) {
		if( hm.containsKey(key) ){
			Node n = hm.get(key);
			removeNode( n );
			addLast( n );
			return n.val;
		}else{
			return -1;
		}
	}
	
	public void put(int key, int value) {
		
		if( hm.containsKey(key) ){
			Node n = hm.get(key);
			removeNode(n);
			addLast(n);
			if( value != n.val ){
				this.tail.val = value;
			}
		}else{
			if( this.size == this.maxsize ){
				hm.remove( this.head.key );
				removeNode(this.head);
				
			}
			Node n = new Node(value , key);
			hm.put( key  , n );
			addLast( n );
			
			
		}
		
	}
}

// largest rectangle in histogram

public int largestRectangleArea(int[] heights) {
	int[] nsel = nsel( heights );
	int[] nser = nser( heights ); 
	int ans = 0;
	for( int i = 0 ; i < heights.length; i++ ){
		
		int lb = nsel[i];
		int rb = nser[i];
		int w= rb - lb -1;
		
		ans = Math.max( ans , heights[i] * w );
		
		
	}
	return ans;
}

public static int[] nser( int[] arr ){
	int n = arr.length;
	int[] ans = new int[n];
	Arrays.fill( ans , n );
	Stack<Integer> st  =new Stack<>();
	
	for( int i = 0 ; i  < n ; i++ ){
		while( !st.isEmpty() && arr[st.peek()] > arr[i] ){
			ans[ st.pop() ] = i;
		}
		st.push(i);
	}
	
	return ans;
}

public static int[] nsel( int[] arr ){
	int n = arr.length;
	int[] ans = new int[n];
	Arrays.fill( ans , -1 );
	Stack<Integer>st =  new Stack<>();
	for( int i = n-1 ; i >= 0 ; i-- ){
		while( !st.isEmpty() && arr[st.peek()] > arr[i]){
			ans[st.pop()] = i;
		}
		st.push(i);
	}
	
	return ans;
}