// Graph basic

// notes->

//Arrays that are independent of the
//data type and whose type of information is evaluated at runtime are Generic arrays.

//the generics information is removed from JVM and thus, 
//the array whose memory allocation is done at runtime does not know which type is to be
// assigned to the array. Thus, arrays and generics do not go well together in Java.

import java.util.*;

class day1{

	public static void main( String[] args ){
		makeGraph();
		printGraph();
		// removeVertex(1);
		// printGraph();


	}

	// always make inside class static
	// else while newing this inside class 
	// we need instance of the outer class in
	// which we we want to new the class
	public static class Edge{
		int v = 0;
		int w = 0;

		public Edge( int v , int w ){
			this.v = v;
			this.w = w;
		}
	}
	
	
	public static ArrayList<Edge>[] graph;

	public static int n = 7;

	public static void addEdge( int u , int v , int w ){
		graph[u].add( new Edge( v , w ) );
		graph[v].add( new Edge( u , w ) );
	}

	public static void makeGraph(){
		graph = new ArrayList[n];
		for( int i = 0 ;  i < n ; i++ ){
			graph[i] = new ArrayList<>();
		}
		
		addEdge( 0 , 1 , 10 );
		addEdge( 0 , 2 , 10 );
		addEdge( 1 , 3 , 5 );
		addEdge( 2 , 3 , 5 );
		addEdge( 1 , 4 , 9 );
		addEdge( 4 , 5 , 11 );
		addEdge( 4 , 6 , 1 );
		addEdge( 5 , 6 , 5 );
	}

	public static void printGraph(){
		StringBuilder sb = new StringBuilder();
		for( int i = 0  ; i < n  ; i++ ){
			sb.append(  i + " ->  " );
			for( Edge e : graph[i] ){
				sb.append( "( " + e.v + ", " + e.w + " ) , " );
			}
			sb.append("\n");
		}
		System.out.println( sb.toString() );
	}

	public static int find(int vtx , int toFind){
		int idx = -1;

		for( int i = 0 ; i < graph[vtx].size() ; i++ ){
			Edge e = graph[vtx].get(i);
			if( e.v == toFind ){
				return i;
			}
		}
		return -1;

	}

	public static void removeEdge( int from , int to ){
		
		int idx1 = find( from , to );
		int idx2 = find( to , from );

		if( idx1 != -1 ){
			graph[from].remove( idx1 );
			graph[to].remove( idx2 );			
		}
	}


	public static void removeVertex( int v ){

		while( graph[v].size() != 0 ){
			removeEdge( v ,  graph[v].get( graph[v].size() - 1 ).v );
		}
	}
	
	// we dont backtrack here to find other paths
	public boolean hasPath( int src ,  int dest , int[] vis ){
		if( src == dest ) return true;
		vis[src] = true;
		boolean ans = false;
		for( Edge e : graph[src] ){
			if( !vis[e.v] ){
				ans = ans || hasPath( e.v , dest , vis );
			}
		}
		return ans;
	}
	
// but here we do back track
	//  to find all the paths
	public static int allPaths( int src , int dest  , boolean[] vis , String ans ){

		if( src ==  dest ){
			ans += src;
			System.out.println( ans );
			return 1;
		}

		vis[src] = true;
		int count = 0;
		for( Edge e : graph[src] ){
			if( !vis[e.v] ){
				count += allPaths( e.v , dest , vis , ans + e.v + " " );
			}
		}
		vis[src] = false;
		return count;
	}
	
	//no destination is given
	// we need to traverse all nodes and check if last visted node links with original src 
	public static int hamiltonianPath( int src , int osrc , int numNodes , boolean[] vis){
		
		if( numNodes == graph.length-1 ){
			for( Edge e : graph[src] ){
				if( e.v == osrc ){
					System.out.println( "cycle" );
				}
			}
			//print path here
			// this  path will be hamil path
			return 1;
		}	
		
		int count = 0;

		vis[src] = true;

		for( Edge e : graph[src] ){
			if( !vis[e.v] ){
				count += hamiltonianPath(  e.v , osrc , numNodes+1 , vis);
			}
		}
		vis[src] = false;
		return count;
	}

}