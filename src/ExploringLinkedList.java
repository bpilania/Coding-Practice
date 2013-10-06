public class ExploringLinkedList {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list = new LinkedList(1);
		list.insert(2);

		
		if(isPalindrome(list, 2).result)
			System.out.println("Is Palindrome");
		else
			System.out.println("Not a Palindrome");
	}
	
	static Pack isPalindrome(LinkedList list, int length){
		if(length < 0) return new Pack(true,null);
		if(length == 1) return new Pack(true,list.next);
		if(length == 0) return new Pack(true,list);
				
		Pack pack = isPalindrome(list.next, length-2);
		
		if(pack.result == false || pack.next.value != list.value)
			return new Pack(false,null);;
		
		return new Pack(true, pack.next.next);
		
	}

}

class Pack{
	boolean result;
	LinkedList next; 
	public Pack(boolean res, LinkedList node){
		result = res;
		next = node;
	}
}
