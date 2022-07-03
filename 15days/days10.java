// 1/N-th root of an integer
class root{
	public static void main( String[] args ){
		root( 34, 24354357 );
	}

	// m^(1/n)
	public static void root( int n , int m ){
		double lo = 1.0;
		double hi = m;
	//two digits accuracy
		double eps = 0.001;

		while( ( hi - lo ) > eps ){
			double mid = (lo + hi) / 2.0;
			if( Math.pow( mid , n ) > m ){
				hi = mid;
			}else{
				lo = mid;
			}
		}

		System.out.println( lo );
		System.out.println( hi );
		System.out.println( Math.pow( m , (double)(1.0/(double)n) ) );
	}
}

//K-th element of two sorted arrays 

public long kthElement( int arr1[], int arr2[], int n, int m, int k) {
	
	if( m < n ){
		return kthElement( arr2  ,arr1 , m , n , k );
	}
	
	int lo = Math.max( 0 , k-m );
	int hi = Math.min( n , k );
	
	while( lo <= hi ){
		
		int mid = ( lo + hi ) / 2;
		
		int elsFromSecond = k - ( mid  );
		
		int x1 = mid == 0 ? -(int)1e9 :  arr1[mid-1];
		int x2 = elsFromSecond==0 ? -(int)1e9 : arr2[elsFromSecond-1];
		
		int y1 = mid == n ? (int)1e9 : arr1[mid];
		int y2 = elsFromSecond == m ? (int)1e9 : arr2[ elsFromSecond ];
		
		
		if( x2 >  y1 ){
			lo = mid+1;
		}else if( x1 > y2 ){
			hi = mid-1;
		}else{
			return Math.max( x1 , x2 );
		}
		
	}
	return 1;
	
}

//find Median Sorted Arrays

public double findMedianSortedArrays(int[] nums1, int[] nums2) {

	int n = nums1.length;
	int m  = nums2.length;

	if( m < n ){
		return findMedianSortedArrays( nums2 , nums1 );
	}
	int k = ( (  n + m + 1) / 2 );
	boolean even = false;
	if(  ( m + n ) % 2 == 0 ){
		even = true;

	}


	int lo = Math.max( 0, k-m );
	int hi = Math.min( n , k );

	while( lo <= hi ){

		int mid = (lo + hi) / 2;
		int elFromSec = k - mid;

		int x1 = mid == 0 ? -(int)1e9 : nums1[mid-1];
		int x2 = elFromSec == 0 ? -(int)1e9 : nums2[elFromSec-1];

		int y1 = mid == n ? (int)1e9 : nums1[mid];
		int y2 = elFromSec == m ? (int)1e9 : nums2[elFromSec];

		if( x2 > y1 ){
			lo = mid+1;
		}else if( x1 > y2 ){
			hi = mid-1;
		}else{
			if( even ){
				double ans = ( Math.max( x1 , x2 ) + Math.min( y1, y2 ) ) / 2.0;
				return ans;
			}
			return Math.max( x1 , x2 );
		}

	}
	return 0.0;

}