//Reverse a LinkedList

public ListNode reverseList(ListNode head) {
	if( head == null || head.next == null ) return head;
	ListNode p = null;
	ListNode c = head;
	while( c != null ){
		ListNode n = c.next;
		c.next = p;
		p = c;
		c = n;
	}
	return p;
}

//Middle of a linkedlist
///for even list b/w [l,r] r is middle
public ListNode middleNode(ListNode head) {
	if( head == null || head.next == null ) return head;
	ListNode s = head;
	ListNode f = head;
	while( f != null && f.next != null ){
		s = s.next;
		f = f.next.next;
	}
	return s;
}

// Merge two sorted LinkedList

public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	if( l1 == null ) return l2;
	if( l2 == null ) return l1;
	ListNode i = l1;
	ListNode j = l2;
	ListNode dummy = new ListNode(-1);
	ListNode ans = dummy;
	while( i != null && j != null ){
		if( i.val <= j.val ){
			ans.next = i;
			i = i.next;
		}else{
			ans.next = j;
			j = j.next;
		}
		ans = ans.next;
	}
	while( i != null ){
		ans.next = i;
		i = i.next;
		ans = ans.next;
	}
	while( j != null ){
		ans.next = j;
		j = j.next;
		ans = ans.next;
	}
	return dummy.next;
}

//Remove N-th node from back of LinkedList

public ListNode removeNthFromEnd(ListNode head, int n) {
	if( head == null ) return head;
	ListNode s = head;
	ListNode e = head;
	while( n-->0 ){
		e = e.next;
	}
	ListNode p = null;
	while( e != null ){
		p = s;
		s = s.next;
		e = e.next;
	}
	//for case first node needs to be removed
	if( p != null ) p.next  = s.next;
	else return s.next;
	s.next  = null;
	return head;
}

// Write a function to delete a node in a singly-linked list.
 // You will not be given access to the head of the list, instead you will be given access to the node to be deleted directly.
// It is guaranteed that the node to be deleted is not a tail node in the list.

public void deleteNode(ListNode node) {
	node.val = node.next.val;
	node.next = node.next.next;
}

//Add two numbers as LinkedList  

public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	ListNode dummy = new ListNode(-1);
	ListNode ans = dummy;
	int c = 0;
	while( l1 != null && l2 != null ){
		int s = l1.val + l2.val + c; 
		c = s / 10;
		int rem = s % 10;
		ListNode cn = new ListNode(rem);
		ans.next = cn;
		ans = ans.next;
		l1 = l1.next;
		l2 = l2.next;
	}
	while( l1 != null ){
		int s = l1.val + c;
		c = s / 10;
		int rem = s % 10;
		ListNode cn = new ListNode(rem);
		ans.next = cn;
		ans = ans.next;
		l1 = l1.next;
	}
	while( l2 != null ){
		int s = l2.val + c;
		c = s / 10;
		int rem = s % 10;
		ListNode cn = new ListNode(rem);
		ans.next = cn;
		ans = ans.next;
		l2 = l2.next;
	}
	if( c  != 0 ){
		ListNode cn = new ListNode(c);
		ans.next = cn;
	}
	return dummy.next;
}

//Find intersection point of Y LinkedList 

public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	int s1 = size( headA );
	int s2 = size( headB );
	if( s1 > s2 ){
		int gap = s1 -s2;
		return getIntersection( headA , headB , gap ); 
	}
	int gap = s2 - s1;
	return getIntersection( headB , headA , gap );


}
public static int size( ListNode node ){
	int s = 0;
	while( node != null ){
		node = node.next;
		s++;
	}
	return s;
}
public ListNode getIntersection( ListNode l1 , ListNode l2 , int gap ){
	while( gap-->0 ){
		l1 = l1.next;
	}
	while( l1 != null  ){
		if( l1 == l2 ) break;
		l1 = l1.next;
		l2 = l2.next;    
	}

	return l1;
}

// is palindrome

public boolean isPalindrome(ListNode head) {
	ListNode mid = mid( head );
	ListNode l2 = mid.next;
	mid.next = null;
	l2 = rev( l2 );
	ListNode c1 = head;
	ListNode c2 = l2;
	while( c1 != null && c2 != null ){
		if( c1.val != c2.val ) return false;
		c1= c1.next;
		c2 =c2.next;
	}
	return true;
}

public static ListNode mid( ListNode node ){
	ListNode s = node;
	ListNode f = node;
	while( f.next != null && f.next.next != null ){
		s = s.next;
		f= f.next.next;
	}
	return s;
}
public static ListNode rev( ListNode node ){
	ListNode p = null;
	ListNode c = node;
	while( c != null ){
		ListNode n = c.next;
		c.next = p;
		p = c;
		c = n;
	}
	return p;
}