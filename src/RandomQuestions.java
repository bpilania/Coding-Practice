
public class RandomQuestions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomQuestions rq = new RandomQuestions();
		rq.swapNumbersWithoutTempVar(-9, -19);
		rq.findLargerNumber(-9,13);
		rq.convertNumberToWords(133211136);
		int[] matrix = {2,3,-8,-1,2,4,-2,3};
		rq.findContigiousSequenceWithLargestSum(matrix);

		SortingAndSearching ss = new SortingAndSearching();
		tree root = new tree(10);
		root.insert(root, 5);
		root.insert(root, 7);
		root.insert(root, 3);
		root.insert(root, 1);
		root.insert(root, 4);
		root.insert(root, 20);
		root.insert(root, 15);
		root.insert(root, 30);
		root.insert(root, 25);
		root.insert(root, 35);	
		System.out.println("\nPrinting Tree");
		root.printPreOrder(root);
		convertBtreeToFoublyLinkedList(root);
		System.out.println("Done");
		rq.printLL(root);
	}
	
	
	void swapNumbersWithoutTempVar(int a, int b){
		System.out.println("A is: "+a+" B is: "+b);
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println("A is: "+a+" B is: "+b);
		
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("A is: "+a+" B is: "+b);
	}
	
	void findLargerNumber(int a, int b){
		int c = a - b;
		int sa = (a >> 31) & 1;
		int sb = (b >> 31) & 1;
		int sc = (c >> 31) & 1;
		
		int is_sign_not_same = sa ^ sb;
		int is_sign_same = is_sign_not_same ^ 1;
		
		int sign = is_sign_same * sc +  is_sign_not_same * sa;
		
		
		int invSign = sign ^ 1;
		
		int big = sign * b + invSign * a;
		System.out.println("Bigger is: "+big);
	}
	
	void convertNumberToWords(int n){
		String value = "";
		String[] bigs = {"", " Thousand "," Million "};
		int count = 0;
		while(n > 0){
			
				value = convertNumberToWordsHelper(n%1000) + bigs[count] + value;
				count++;
				n = n/1000;
			
		}
		System.out.println(value);
		}
	
	String convertNumberToWordsHelper(int a){
		
		String[] ones = {"One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
		String teens[] = {"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", 
				"Seventeen", "Eighteen", "Nineteen"};
		String[] tens = {"Ten", "Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy",
				"Eighty", "Ninety"};
		
		String value = "";
		if(a/100 > 0)
			value = ones[a/100 - 1] + " Hundred ";
		
		a = a%100;
		
		if(a == 0)
			return value;
		
		if(a < 10)
			return value + ones[a-1];
		
		if(a % 10 == 0)
			return value + tens[a/10 - 1];
		
		
		if(a < 20)
			return value + teens[a%10 - 1];
		
		return value + tens[a/10 - 1] + " " + ones[a%10 - 1];
		
	}
	
	void findContigiousSequenceWithLargestSum(int[] matrix){
		int[] helper = findContigiousSequenceWithLargestSumHelper(matrix, 0);
		
		int maxIndex = 0;
		for(int i=0;i<matrix.length-1; i++){
			if(helper[i] > helper[maxIndex])
				maxIndex = i;
		}
		System.out.print("Sequence is: ");
		for(int j = maxIndex; helper[maxIndex] > 0 ; j++){
			System.out.print(" "+matrix[j]);
			helper[maxIndex] -=matrix[j];
		}
		
	}
	
	int[] findContigiousSequenceWithLargestSumHelper(int[] matrix, int index){
		if(index > matrix.length - 1){
			int[] helper = new int[matrix.length];
			return helper;
		}
		
		int[] helper = findContigiousSequenceWithLargestSumHelper(matrix, index+1);
		
		if(index == matrix.length - 1)
			helper[index] = matrix[index];
		else
			helper[index] = Math.max(matrix[index] + helper[index+1], matrix[index]);
		
		return helper;
	
	}
	
	static tree convertBtreeToFoublyLinkedList(tree root){
		if(root == null) return null;
		
		tree tempNode;
		tempNode = convertBtreeToFoublyLinkedList(root.left);
		if(tempNode != null){
			while(tempNode.right != null)
				tempNode = tempNode.right;
			tempNode.right = root;
		}
		root.left = tempNode;
		
		
		tempNode = convertBtreeToFoublyLinkedList(root.right);
		if(tempNode != null){
			while(tempNode.left != null)
				tempNode = tempNode.left;
			tempNode.left = root;
		}
		root.right = tempNode;
		
		
		return root;
	}
	
	void printLL(tree root){
		while(root.left != null)
			root = root.left;
		System.out.println("Printing queue");
		while(root.right != null){
			System.out.println(root.value);
			root = root.right;
		}
		System.out.println(root.value);

	}
	
	
	}
	

	
	


