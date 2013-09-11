import java.util.Stack;


public class DelKElement {
	
public static void main(String args[]){
	LinkedList l1 = new LinkedList(5);
	l1.insert(5);
	l1.insert(5);
	
	
	LinkedList l2 = new LinkedList(6);
	l2.insert(6);
	

	LinkedList l3 = l1.addNumbers(l1, l2);
	while(l3 != null)
	{
		System.out.println(l3.value);
		l3 = l3.next;
	}
	
}
	
	

}
class LinkedList{
	LinkedList next;
	int value;
	
	LinkedList(int i){
		this.value = i;
		this.next = null;
	}
	
	void insert(int a){
		
		LinkedList temp = this;
		
		
		while(temp.next != null)
			temp = temp.next;
		
		temp.next = new LinkedList(a);
		
		
	}
	
	LinkedList kThToLast(int k){
		
		A node = kThToLastHelper(this, k);
		
		return node.node;
	}
	
	A kThToLastHelper(LinkedList ll, int k){
		System.out.println("in fn");
		if(ll==null){
			A node = new A();
			node.counter = 0;
			node.node = null;
			return node;
		}
		
		A node = kThToLastHelper(ll.next, k);
		node.counter++;
		System.out.println(node.counter);
		if(node.counter == k){
			System.out.println("In if "+node.counter);
			node.node = ll;
		}
				
		return node;
	}
	
	boolean isPalindrome(){
		LinkedList slow = this;
		LinkedList fast = this;
		System.out.println("Initial value is "+slow.value);

		Stack<Integer> stack = new Stack<Integer>();
		while(fast.next != null && fast.next.next != null){
			System.out.println("inserting "+slow.value+" in stack");
			stack.push(slow.value);
			fast = fast.next.next;
			slow = slow.next;
		}
		
		if(fast.next == null)
			slow = slow.next;
		
		if(fast.next != null){
			System.out.println("inserting "+slow.value+" in stack");
			stack.push(slow.value);
			slow = slow.next;
		}
			
		
		
		
		while(slow !=null){
			int val = stack.pop();
			System.out.println(slow.value+" "+val);
			if(slow.value != val)
				return false;
			slow = slow.next;
		}
		
		return true;
	}
	
	LinkedList addNumbers(LinkedList l1, LinkedList l2){
		LinkedList lenL1 = l1;
		LinkedList lenL2 = l2;
		
		
		int l1Count=0, l2Count=0;
		while(lenL1 !=null){
			lenL1 = lenL1.next;
			l1Count++;
		}
		
		while(lenL2 !=null){
			lenL2 = lenL2.next;
			l2Count++;
		}
		
		
		int diff = l1Count - l2Count;
		
		if(diff != 0 && diff < 0){
			while(diff !=0){
				LinkedList temp = new LinkedList(0);
				temp.next = l1;
				l1 = temp;
				diff++;
			}
			
		}
		if(diff != 0 && diff > 0){
			while(diff !=0){
				LinkedList temp = new LinkedList(0);
				temp.next = l2;
				l2 = temp;
				diff--;
			}
			
		}
		

		
		System.out.println("----------------");
		
		
		return addNumberHelper(l1,l2).node;
		
	}
	
	B addNumberHelper(LinkedList l1, LinkedList l2){
		if(l1 == null)
			return null;
		
		B list = addNumberHelper(l1.next, l2.next);
		
		
			int val = l1.value + l2.value;
			if(list !=null)
				val += list.carry;
			LinkedList listHelper = new LinkedList(val%10);
			if(list !=null)
				listHelper.next = list.node;
			B b = new B();
			b.node = listHelper;
			b.carry = val/10;
		
			return b;
	}
}

class A{
	int counter;
	LinkedList node;
	
}

class B{
	int carry;
	LinkedList node;
	
}
