import java.util.Stack;


public class ExploringStacks {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> s1 = new Stack<Integer>();
		s1.push(4);
		s1.push(3);
		s1.push(2);
		s1.push(1);
		System.out.println(s1);
		Stack<Integer> s3 = new Stack<Integer>();
		
		towerOfHanoi(s1, new Stack<Integer>(), s3, s1.size());
		System.out.println(s3);

	}

	static void towerOfHanoi(Stack<Integer> source, Stack<Integer> intermediate, Stack<Integer> destination, int length){
		System.out.println("S and A size: "+source.size()+" "+intermediate.size());
		if(length <= 0){
			return;
		}
		towerOfHanoi(source, destination, intermediate, length-1);
		destination.push(source.pop());
		towerOfHanoi(intermediate, source, destination, length-1);
	}
}

