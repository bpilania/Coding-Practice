
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
	
	
}


