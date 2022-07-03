// Reverse a LinkedList in groups.
public ListNode reverseKGroup(ListNode head, int k) {
	int size = getSize(head);
	if( size < k  || size == 1) return head;
	ListNode oh = null;
	ListNode ot = null;

	while( size >= k ){
		int K = k;
		ListNode th = null;
		ListNode tt = null;
		while( K-->0 ){
			ListNode p = head;
			head = head.next;
			ListNode d = deleteFirst( p );
			if( tt == null ){
				th = d;
				tt = d;
			}else{
				th = addFirst( th , d );
			}
			size--;
		} 

		if( oh == null ){ 
			oh = th;
			ot = tt;
		}else{
			ot.next = th;
			ot = tt;
		}
		
	}
	if( size > 0 ){
		ot.next = head;
	}
	return oh;
}

public static int getSize( ListNode node ){
	int s = 0;
	while( node != null ){
		node = node.next;
		s++;
	}
	return s;
}

public static ListNode addFirst( ListNode head , ListNode toAdd ){
	ListNode nHead = null;
	if( head == null ){
		nHead = toAdd; 
	}else{
		toAdd.next = head;
		nHead = toAdd;
	}
	return nHead;
}

public static ListNode deleteFirst( ListNode node ){
	if( node == null || node.next == null ) return node;
	node.next = null;
	return node;
}

// cycle

public boolean hasCycle(ListNode head) {
	if( head == null || head.next == null )return false;
	ListNode  f = head;
	ListNode s = head;
	while( f != null && f.next != null ){
		s= s.next;
		f = f.next.next;
		if( f == s ) return true;
	}
	return false;
}

// point of cycle

public ListNode detectCycle(ListNode head) {
	ListNode s = head;
	ListNode f = head;
	boolean isCycle = false;
	while( f != null && f.next != null ){
		s = s.next;
		f = f.next.next;
		if( s == f ){
			isCycle = true;
			break;
		} 
	}
	if( !isCycle ) return null;

	s = head;
	while( s != f ){
		s = s.next;
		f = f.next;
	}
	return s;
}

//flatten a linked list

Node flatten(Node root)
{
	if( root == null || root.next == null ) return root;
	Node mid = mid( root );
	Node l1 = root;
	Node l2 = mid.next;
	mid.next = null;

	Node sl1 = flatten( l1 );
	Node sl2 = flatten( l2 );

	return mergeTwo( sl1 , sl2 );
	
}

public static Node mid( Node head ){
	if( head == null || head.next ==null ) return head;
	Node s = head;
	Node f = head;
	while( f.next != null && f.next.next != null ){
		s = s.next;
		f = f.next.next;
	}
	return s;
}

public static Node mergeTwo( Node l1  ,Node l2 ){
	Node dummy = new Node( -1 );
	// not to forget this step
	Node ans = dummy;
	while( l1 != null && l2 != null ){
		if( l1.data <= l2.data ){
			dummy.bottom = l1;
			l1 = l1.bottom;
		}else{
			dummy.bottom = l2;
			l2= l2.bottom;
		}
		dummy = dummy.bottom;
	}
	while( l1 != null ){
		dummy.bottom = l1;
		l1 = l1.bottom;
		dummy = dummy.bottom;
	}
	while( l2 != null ){
		dummy.bottom = l2;
		l2= l2.bottom;
		dummy = dummy.bottom;
	}
	return ans.bottom;
}

//Rotate a LinkedList

public ListNode rotateRight(ListNode head, int k) {
	if( head == null || head.next == null || k == 0 ) return head;
	int size = size( head );
	k = k % size;
	if( k == 0 ) return head;
	ListNode s = head;
	ListNode e = head;
	while( k-->0 ){
		e = e.next;
	}
	while( e.next != null ){
		s = s.next;
		e = e.next;
	}
	ListNode nHead = s.next;
	s.next = null;
	e.next = head;
	return nHead;
}


public static int size( ListNode node ){
	int s = 0 ;
	while( node != null ){
		s++;
		node = node.next;
	}
	return s;
}

// Clone a Linked List with random and next pointer. 

public Node copyRandomList(Node head) {
	if( head == null  ) return head;

	Node c = head;
	Node nh = null;
	while( c != null ){
		Node forw = c.next;
		Node newNode = new Node( c.val );
		if( nh == null ) nh = c;
		c.next = newNode;
		newNode.next = forw;
		c = forw;
	}

	c = nh;
	while( c != null ){
		if(c.random != null)c.next.random = c.random.next;
		c = c.next.next;
	}
	return seperateOddEvenIndex(nh);

}

public static Node seperateOddEvenIndex( Node head ){
	Node dummy= new Node(-1);
	Node d = dummy;
	Node c = head;
	while( c != null ){
		Node f = c.next;
		d.next = f;
		c.next = f.next;
		
		c = c.next;
		d  =d.next;
	}
	return dummy.next;
}

//Remove Duplicate from Sorted array 

public int removeDuplicates(int[] nums) {
	int i = 1;
	int j = 1;
	while( i < nums.length ){
		if( nums[i-1] == nums[i] ){
			i++;
		}else{
			nums[j] = nums[i];
			i++;
			j++;
		}
	}
	return j;
}