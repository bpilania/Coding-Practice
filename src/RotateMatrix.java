
public class RotateMatrix {
public static void main(String args[]){
	int n = 3;
	int m = n-1;
	int[][] matrix = new int[n][n];
	
	int i=0;
	for(int j=0;j<m;j++){
		int temp = matrix[i][j];
		matrix[i][j] = matrix[i+m][j];
		matrix[i+m][j] = matrix[i+m][j+m];
		matrix[i+m][j+m] = matrix[i][j+2];
		matrix[i][j+2] = temp;
		
	}
	
}
}
