class Coordinate implements Cloneable {
	public int row;
	public int column;
	public Coordinate(int r, int c) {
		row = r;
		column = c;
	}

	public boolean inbounds(int[][] matrix) {
		return 	row >= 0 &&
				column >= 0 &&
				row < matrix.length &&
				column < matrix[0].length;
	}

	public boolean isBefore(Coordinate p) {
		return row <= p.row && column <= p.column;
	}

	public Object clone() { 
		return new Coordinate(row, column);
	}

	public void moveDownRight() {
		row++;
		column++;
	}

	public void setToAverage(Coordinate min, Coordinate max) {
		row = (min.row + max.row) / 2;
		column = (min.column + max.column) / 2;
	}
}
public class test1 {

	public static Coordinate partitionAndSearch(int[][] matrix, Coordinate origin, Coordinate dest, Coordinate pivot, int elem) {
		Coordinate lowerLeftOrigin = new Coordinate(pivot.row, origin.column);
		Coordinate lowerLeftDest = new Coordinate(dest.row, pivot.column - 1);
		Coordinate upperRightOrigin = new Coordinate(origin.row, pivot.column);
		Coordinate upperRightDest = new Coordinate(pivot.row - 1, dest.column);

		Coordinate lowerLeft = findElement(matrix, lowerLeftOrigin, lowerLeftDest, elem);
		if (lowerLeft == null) {
			return findElement(matrix, upperRightOrigin, upperRightDest, elem);
		}
		return lowerLeft;
	}

	public static Coordinate findElement(int[][] matrix, Coordinate origin, Coordinate dest, int x) {
		if (!origin.inbounds(matrix) || !dest.inbounds(matrix)) {
			return null;
		}
		if (matrix[origin.row][origin.column] == x) {
			return origin;
		} else if (!origin.isBefore(dest)) {
			return null;
		}

		/* Set start to start of diagonal and end to the end of the diagonal. Since
		 * the grid may not be square, the end of the diagonal may not equal dest.
		 */
		Coordinate start = (Coordinate) origin.clone();
		int diagDist = Math.min(dest.row - origin.row, dest.column - origin.column);
		Coordinate end = new Coordinate(start.row + diagDist, start.column + diagDist);
		Coordinate p = new Coordinate(0, 0);

		/* Do binary search on the diagonal, looking for the first element greater than x */
		while (start.isBefore(end)) {
			p.setToAverage(start, end);
			if (x > matrix[p.row][p.column]) {
				start.row = p.row + 1;
				start.column = p.column + 1;
			} else {
				end.row = p.row - 1;
				end.column = p.column - 1;
			}
		}

		/* Split the grid into quadrants. Search the bottom left and the top right. */ 
		return partitionAndSearch(matrix, origin, dest, start, x);
	}

	public static Coordinate findElement(int[][] matrix, int x) {
		Coordinate origin = new Coordinate(0, 0);
		Coordinate dest = new Coordinate(matrix.length - 1, matrix[0].length - 1);
		return findElement(matrix, origin, dest, x);
	}

	public static void main(String[] args) {
		int[][] matrix = {{1,2,3,4}, 
				 	 	  {6,7,8,9},
				 	 	  {11,12,13,14}};

		AssortedMethods.printMatrix(matrix);
		int m = matrix.length;
		int n = matrix[0].length;

			int i = 9;
			Coordinate c = findElement(matrix, i);
			if(c == null)
				System.out.println("Item not found");
			else
				System.out.println(i + ": (" + c.row + ", " + c.column + ")");
			
		}
	
	
		
	}

