
public class ExploringArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = {
				{1,2,3,4},
				{12,1,2,5},
				{11,4,3,6},
				{10,9,8,7}				
				
				
		};
		matrixTranspose(matrix);		
		for(int i=0; i < matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				System.out.print(matrix[i][j]+" ");
		}
		System.out.println();
		}
		
		
		int[][] mat1 = {
				{1,1,1},
				{1,2,1},
				{1,1,1},
				{1,1,1}
				
		};
		
		int[][] mat2 = {
				{1,1,1},
				{1,2,1},
				{1,1,1}
								
		};
		try{
		int[][] result = matrixMul(mat1, mat2);
		System.out.println("--------------------");
		for(int i=0; i < result.length; i++){
			for(int j=0; j<result[0].length; j++){
				System.out.print(result[i][j]+" ");
		}
		System.out.println();
		}
		}catch(Exception e){
			System.out.println("Matrix cannopt be multiplied");
		}
		
		int[] a1 = {1,2,2,2,2,4, 7,8,10};
		System.out.println(findNumber(a1, 8,0,11));
		
		}
	
	static void matrixTranspose(int[][] matrix){
		int n = matrix.length -1;
		for(int i=0; i<=n/2;i++){
			for(int j=i; j <= n - 1 - i; j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n-j][i];
				matrix[n-j][i] = matrix[n-i][n-j];
				matrix[n-i][n-j] = matrix[j][n-i];
				matrix[j][n-i] = temp;
				
			}
		}
	}
	
	static int[][] matrixMul(int[][] mat1, int[][] mat2) throws Exception{
		if(mat1[0].length != mat2.length) throw new Exception();
		int[][] result = new int[mat1.length][mat2[0].length];
		for(int i=0; i<mat1.length; i++){
			for(int j=0; j<mat2[0].length;j++){
				for(int k=0; k<mat2.length;k++){
					result[i][j] = result[i][j] + mat1[i][k] * mat2[k][j];
				}
			}
		}
		return result;
	}
	
	static int findNumber(int[] x, int y, int low, int high){
		if(low > high) return -1;
		int mid = (low + high)/2;
		
		if(x[mid] >= y && x[mid-1] < y)
			return x[mid-1];
		
		if(x[mid] >= y)
			mid = findNumber(x, y, low, mid-1);
		else
			mid = findNumber(x, y, mid+1, high);
		
		return mid;
	}
	
	static int findFirstMissing(int array[], int start, int end) {
		 if(end < start)
			 return end+1;
	    
		if(start > array.length) return start;
		
		int mid = (start + end)/2;
		
		if(mid == array[mid])
			return findFirstMissing(array, mid+1, end);
		else
			return findFirstMissing(array, start, mid-1);
	}
	 
	
	/* For a given array arr[], returns the maximum j – i such that
    arr[j] > arr[i] */
	static int maxIndexDiff(int arr[], int n)
	{
	    int maxDiff;
	    int i, j;
	 
	    int LMin[] = new int[n];
	    int RMax[] = new int[n];
	 
	   /* Construct LMin[] such that LMin[i] stores the minimum value
	       from (arr[0], arr[1], ... arr[i]) */
	    LMin[0] = arr[0];
	    for (i = 1; i < n; ++i)
	        LMin[i] = Math.min(arr[i], LMin[i-1]);
	 
	    /* Construct RMax[] such that RMax[j] stores the maximum value
	       from (arr[j], arr[j+1], ..arr[n-1]) */
	    RMax[n-1] = arr[n-1];
	    for (j = n-2; j >= 0; --j)
	        RMax[j] = Math.max(arr[j], RMax[j+1]);
	 
	    /* Traverse both arrays from left to right to find optimum j - i
	        This process is similar to merge() of MergeSort */
	    i = 0;
	    j = 0;
	    maxDiff = -1;
	    
	    while (j < n && i < n)
	    {
	        if (LMin[i] < RMax[j])
	        {
	            maxDiff = Math.max(maxDiff, j-i);
	            j = j + 1;
	        }
	        else
	            i = i+1;
	    }
	 
	    return maxDiff;
	}
	
	static void printMatrixSpirally(int[][] arr){
		int m = arr.length;
		int n = arr[0].length;
		
		for(int i=0; i<m/2; i++){
			int temp = i;
			int j = i;
			
			while(j < n-1-temp){
				System.out.print(arr[i][j]+" ");
				j++;
			}
			
			while(i < m-1-temp){
				System.out.print(arr[i][j]+" ");
				i++;
			}
			
			while(j > temp){
				System.out.print(arr[i][j]+" ");
				j--;
			}
			
			while(i > temp){
				System.out.print(arr[i][j]+" ");
				i--;
			}
						
		}
	}


	
	
}


