
public class ExploringHeaps {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExploringHeaps ss = new ExploringHeaps();
		int matrix5[] = {0,4,7,5,2,1,7,4,10,2,9,3,-1,-1,-1};
		ss.createHeap(matrix5,11);
		int matrix6[] = {0, 1, 2, 4, 2, 3, 7, 5, 10, 4, 9, 7,-1,-1,-1};
		ss.insertHeap(matrix6, 1, 12);
		int matrix7[] = {0, 1, 2, 4, 2, 3, 7, 5, 10, 4, 9, 7,-1,-1,-1};
		System.out.println(ss.removeMin(matrix7, 11));

	}
	
	void createHeap(int matrix[], int index){
		for(int j=index; j>0; j--)
			createHeapHelper(matrix,j, index);
		for(int i=0; i<matrix.length; i++)
			System.out.print(matrix[i]+" ");
		System.out.println();
	}
	void createHeapHelper(int[] matrix, int n, int index){
		if(n>index/2)
			return;
		
		int lChild = matrix[n*2];
		int rChild = matrix[n*2 + 1];
		int minIndex = lChild < rChild ? n*2 : n*2 + 1;
		if(matrix[n] > matrix[minIndex]){
			int temp = matrix[n];
			matrix[n] = matrix[minIndex];
			matrix[minIndex] = temp;
			createHeapHelper(matrix, minIndex, index);
		}
	}
	
	void insertHeap(int[] matrix, int value, int index){
		matrix[index] = value;
		insertHelper(matrix, index);
		for(int i=0; i<matrix.length; i++)
			System.out.print(matrix[i]+" ");
		System.out.println();
	}
	
	void insertHelper(int[] matrix, int index){
		if(index < 2)
			return;
		
		if(matrix[index] < matrix[index/2]){
			int temp = matrix[index];
			matrix[index] = matrix[index/2];
			matrix[index/2] = temp;
			insertHelper(matrix, index/2);
		}
	}
	
	int removeMin(int[] matrix, int index){
		int temp = matrix[1];
		matrix[1] = matrix[index];
		matrix[index] = Integer.MAX_VALUE;
		
		removeMinHelper(matrix, 1, index-1);
		
		/*for(int i=0; i<matrix.length; i++)
			System.out.print(matrix[i]+" ");
		System.out.println();*/
		
		return temp;
	}
	
	void removeMinHelper(int[] matrix, int start, int index){
		if(start > index/2)
			return;
		
		int leftChild = matrix[start*2];
		int rightChild = matrix[start*2 + 1];
		int minPos = leftChild < rightChild ? start*2 : start*2 + 1;
		if(matrix[start] > matrix[minPos]){

			int temp = matrix[start];
			matrix[start] = matrix[minPos];
			matrix[minPos] = temp;

			removeMinHelper(matrix, minPos, index);
		}
	}

}
