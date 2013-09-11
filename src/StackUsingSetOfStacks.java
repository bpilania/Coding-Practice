import java.util.*;


public class StackUsingSetOfStacks {
public static void main(String args[]){
	stack stack = new stack();
	stack.insert(5);
	stack.insert(4);
	stack.insert(5);
	stack.insert(6);
	stack.insert(7);
	stack.insert(8);
	stack.insert(0);
	try{
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	System.out.println(stack.pop());
	}catch(Exception e){
		System.out.println("No value in stack");
	}
}
}



class stack {
ArrayList<Stack<Integer>> listOfStack= new ArrayList<Stack<Integer>>();
int stackCounter = -1;
int max = 2;
int top = -1;


void insert(int value){
	if(stackCounter < 0 || top == max){
		Stack<Integer> newStack = new Stack<Integer>();
		newStack.add(value);
		listOfStack.add(newStack);
		stackCounter++;
		top=0;
	}
	else{
		Stack<Integer> temp = listOfStack.get(stackCounter);
		temp.add(value);
		top++;		
	}
}

int pop() throws Exception{
	if(stackCounter < 0){
		throw new Exception();
	}
	if(top == -1){
		listOfStack.remove(stackCounter);
		top = 2;
		stackCounter--;
	}
	top--;
	return listOfStack.get(stackCounter).pop();
}
}
