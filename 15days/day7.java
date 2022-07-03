//M coloring Problem 

public static boolean graphColoring(List<Integer>[] G, int[] color, int i, int C) 
{
	Arrays.fill( color , -1 );
	return solve( G, color , 0 , C , G.length );
}

public static boolean solve( List<Integer>[] G , int[] color , int cn , int C , int totalNodes ){

	if( totalNodes == cn ) return true;

	for( int c = 0 ; c < C ; c++ ){
		//instead of  calling on the neighbours we call on the next node ie cn + 1
		if( isSafe( G , cn , c , color) ){
			color[cn] = c;
			if( solve( G , color , cn+1 , C , totalNodes ) ) return true;
			color[cn] = -1;
		}

	}
	return false;
}

public static boolean isSafe( List<Integer>[] G , int node , int colorToDo , int[] color  ){

	for( int e :G[node] ){
		if( color[e] == colorToDo ) return false;
	}
	return true;

}