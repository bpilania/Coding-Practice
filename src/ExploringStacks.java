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

		int[] arr = {1,2,15,16,8,12,13,4,3,2,5,6,20};
		printNextGreatest(arr);
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
	
	static void printNextGreatest(int[] arr){
		if(arr.length < 0){ 
			System.out.println("Array is empty"); 
			return; 
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> temp = new Stack<Integer>();
		
		for(Integer i : arr){
			if(stack.isEmpty())
				stack.add(i);
			else{
				temp.push(i);
				
				while(!stack.isEmpty()){
					int value = stack.pop();
					if(i > value)
						System.out.println(i+" > "+value);
					else
						temp.push(value);
				}
				while(!temp.isEmpty()){
					stack.push(temp.pop());
				}
			}
			
		}
		
		while(!stack.isEmpty())
			System.out.println("Nothing > "+stack.pop());
		
	}
}

