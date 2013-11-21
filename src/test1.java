import java.util.LinkedList;
import java.util.Stack;

public class test1<T> {
	Stack<TreeNode> stack = new Stack<TreeNode>();
	TreeNode current = null;
	
	public test1(TreeNode root){
		if(root == null)
			return;
		
		while(root != null){
			stack.push(root);
			root = root.left;
		}
		current = root;
	}
	
	
	void next() {
		if (!hasNext())
			return;

		TreeNode next = null;
		while(current != null){
			stack.push(current);
			current = current.left;
		}
		next = stack.pop();
		current = next.right;
		System.out.println("Next value is "+next.value);
	}
	
	boolean hasNext()
	{
		return !stack.isEmpty() || current != null;
	}
	/* Driver program to check above functions */
	public static void main(String args[])
	{
		Tree tree = new Tree();
		TreeNode root = tree.insert(10);
		tree.insert(5);
		tree.insert(20);
		tree.insert(1);
		tree.insert(7);
		tree.insert(13);
		tree.insert(21);
		
		test1 iterator = new test1(root);
		
		while(iterator.hasNext())
			iterator.next();
		
	}
}