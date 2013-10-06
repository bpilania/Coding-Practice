public class Question {

	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}
	
	public static void printLine(Line l) {
		System.out.println(l.start.x + "\t" + l.start.y);
		System.out.println(l.end.x + "\t" + l.end.y);
	}
	
	public static void printSquare(Square s) {
		System.out.println(s.left + "\t" + s.top + "\t" + s.size);
	}	
	
	public static boolean isApproxEqual(double d1, double d2) {
		double epsilon = .001;	
		if (Math.abs(d1 - d2) < epsilon) {
			return true;
		}
		return false;
	}
	
	public static boolean isApproxEqual(Point p1, Point p2) {
		return isApproxEqual(p1.x, p2.x) && isApproxEqual(p1.y, p2.y); 
	}
	
	public static boolean doTest(Square s1, Square s2, Point start, Point end) {
		Line line = s1.cut(s2);
		boolean r = (isApproxEqual(line.start, start) && isApproxEqual(line.end, end)) || (isApproxEqual(line.start, end) && isApproxEqual(line.end, start));
		if (!r) {
			printSquare(s1);
			printSquare(s2);
			printLine(line);
			System.out.println(start.toString());
			System.out.println(end.toString());
			System.out.println();
			return r;
		}
		return r;
	}
	
	public static boolean doTestFull(Square s1, Square s2, Point start, Point end) {
		return doTest(s1, s2, start, end) && doTest(s2, s1, start, end);
	}
	
	public static void doTests() {
		// Equal
		doTestFull(new Square(1, 1, 5), new Square(1, 1, 5), new Point(3.5, 1), new Point(3.5, 6));	
		
		// Concentric
		doTestFull(new Square(1, 1, 5), new Square(2, 2, 3), new Point(3.5, 1), new Point(3.5, 6));
		
		// Partially overlapping -- side by side
		doTestFull(new Square(10, 10, 10), new Square(8, 10, 10), new Point(8, 15), new Point(20, 15));
		
		// Partially overlapping -- corners
		doTestFull(new Square(10, 10, 10), new Square(8, 7, 7), new Point(8.777777, 7), new Point(18.8888888, 20));		
		
		// Partially overlapping -- on top of each other
		doTestFull(new Square(10, 10, 10), new Square(8, 7, 15), new Point(8, 22), new Point(23, 7));		
		
		// Not overlapping -- side by side
		doTestFull(new Square(10, 10, 10), new Square(19, 25, 4), new Point(12.5, 10), new Point(22, 29));				
		
		// Not overlapping -- on top of each other
		doTestFull(new Square(10, 10, 10), new Square(4, 4, 3), new Point(4, 4), new Point(20, 20));		
		
		// Contained
		doTestFull(new Square(10, 10, 10), new Square(12, 14, 3), new Point(10, 16.66666), new Point(20, 13.333));			
	}
	
	public static void main(String[] args) {
		/* For an easy way to test these, open up Square Cut Tester.xlsx
		 * in the Chapter 7, Problem 5 folder. Copy and paste the exact 
		 * output from below into the file (including all tabs).
		 */
		doTests();
	}

}

class Point {
	public double x;
	public double y;
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isEqual(Point p) {
		return (p.x == x && p.y == y);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}

class Square {
	public double left;
	public double top;
	public double bottom;
	public double right;
	public double size;
	public Square(double left, double top, double size) {
		this.left = left;
		this.top = top;
		this.bottom = top + size;
		this.right = left + size;
		this.size = size;
	}
	
	public Point middle() {
		return new Point((this.left + this.right)/2.0, (this.top + this.bottom)/2.0);
	}
	
	public boolean contains(Square other) {
		if (this.left <= other.left && this.right >= other.right && this.top <= other.top && this.bottom >= other.bottom) {
			return true;
		}
		return false;
	}
	
	/* Return the point where the line segment connecting mid1 and
	 * mid2 intercepts the edge of square 1. That is, draw a line 
	 * from mid2 to mid1, and continue it out until the edge of
	 * the square. */
	public Point extend(Point mid1, Point mid2, double size) {
		/* Find what direction the line mid2 -> mid1 goes */
		double xdir = mid1.x < mid2.x ? -1 : 1;
		double ydir = mid1.y < mid2.y ? -1 : 1;
		
		/* If mid1 and mid2 have the same x value, then the slope
		 * calculation will throw a divide by 0 exception. So, we
		 * compute this specially. */
		if (mid1.x == mid2.x) {
			return new Point(mid1.x, mid1.y + ydir * size / 2.0);
		}
		double slope = (mid1.y - mid2.y) / (mid1.x - mid2.x);
		double x1 = 0;
		double y1 = 0;
		
		/* Calculate slope using the equation (y1 - y2) / (x1 - x2).
		 * Note: if the slope is �steep� (>1) then the end of the
		 * line segment will hit size / 2 units away from the middle
		 * on the y axis. If the slope is �shallow� (<1) the end of
		 * the line segment will hit size / 2 units away from the
		 * middle on the x axis. */
		if (Math.abs(slope) == 1) {
			x1 = mid1.x + xdir * size / 2.0;
			y1 = mid1.y + ydir * size / 2.0;
		} else if (Math.abs(slope) < 1) {
			x1 = mid1.x + xdir * size / 2.0;
			y1 = slope * (x1 - mid1.x) + mid1.y; 
		} else {
			y1 = mid1.y + ydir * size / 2.0;
			x1 = (y1 - mid1.y) / slope + mid1.x;
		}
		return new Point(x1, y1);
	}
	
	public Line cut(Square other) {
		/* Calculate where a line between each middle would collide with the edges of the squares */
		Point p1 = extend(this.middle(), other.middle(), this.size);
		Point p2 = extend(this.middle(), other.middle(), -1 * this.size);
		Point p3 = extend(other.middle(), this.middle(), other.size);
		Point p4 = extend(other.middle(), this.middle(), -1 * other.size);
	
		/* Of above points, find start and end of lines. Start is farthest left (with top most as a tie breaker)
		 * and end is farthest right (with bottom most as a tie breaker */
		Point start = p1;
		Point end = p1;		
		Point[] points = {p2, p3, p4};
		for (int i = 0; i < points.length; i++) {
			if (points[i].x < start.x || (points[i].x == start.x && points[i].y < start.y)) {
				start = points[i];
			} else if (points[i].x > end.x || (points[i].x == end.x && points[i].y > end.y)) {
				end = points[i];
			}
		}
			
		return new Line(start, end);
	}
	
	public String toString() {
		return "(" + left + ", " + top + ")|(" + right + "," + bottom + ")";
	}
}


class Line {
	public Point start;
	public Point end;
	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public String toString() {
		return "Line from " + start.toString() + " to " + end.toString();
	}
}

