import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Random;
import java.util.LinkedList;


public class SortingAndSearching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrix[] = {4,7,5,2,1,7,4,10,2,9,3};
		SortingAndSearching ss = new SortingAndSearching();
		ss.bubbleSort(matrix);
		int matrix1[] = {4,7,5,2,1,7,4,10,2,9,3};
		ss.insertionSort(matrix1);
		int matrix2[] = {4,7,5,2,1,7,4,10,2,9,3};
		ss.selectionSort(matrix2);
		int matrix3[] = {9,8,7,6,5,4,3,2,1};
		ss.quickSort(matrix3);
		int matrix4[] = {4,7,5,2,1,7,4,10,2,9,3};
		ss.mergeSort(matrix4);
		ExploringHeaps eh = new ExploringHeaps();
		int matrix5[] = {0,4,7,5,2,1,7,4,10,2,9,3};
		eh.createHeap(matrix5,11);
			
		for(int i = matrix5.length-1; i>1;i--){
			int temp = eh.removeMin(matrix5, i);
			matrix5[i] = temp;
		}
		for(int i=0; i<matrix5.length; i++)
			System.out.print(matrix5[i]+" ");
		System.out.println();
		
		int matrix6[] = {1,2,3,4,5,6,7,8,9};
		System.out.println("8th smallest element is: "+ss.quickSelect(matrix6, 0, matrix6.length-1, 8));
		
		int matrix7[] = {9,3,7,1,4,2,6,5,8,3,7,9,2,1,0,4};
		ss.countingSort(matrix7);
		
		int matrix8[] = {9233,3234,37,23431,3244,2222,4632,235,48,233,4237,2349,24542,5231,20,44};
		ss.radixSort(matrix8);
		
		int matrix9[] = {1, 2, 2, 3, 4, 4, 5, 7, 7, 9, 10}; 
		ss.binarySearch(matrix9, 3);
		
		String[] names = {"Sameer","Bhaskar","Monica","Shonar","Srushti","Gagandeep","Bhaskar","Sameer"};
		ss.quickSortString(names);
		
		String[] anagrams = {"bhaskar", "shonar", "monica", "karbhas", "skbhaar", "icamon", "narsho", "mridu"};
		
		ss.putAnagramsTogather(anagrams);
		
		String[] matrix10 = {"", "", "", "", "", "", "", "", "", ""};
		ss.searchForString(matrix10, "Zen");
		
		int[][] matrix11 = {
				{1,2,3,4,5,25},
				{6,7,10,11,12,26},
				{7,8,12,15,16,27},
				{8,12,14,19,20,28},
				{9,17,18,20,27,29}
				
				
		};
		
		if(ss.findElementInSorted2DArrayElemination(matrix11, 13))
			System.out.println("Element Found");
		else
			System.out.println("Element not found");
		
		int[] matrix12 = {1,4,7,9,12,16,71};
		System.out.println(ss.bSortTofindFirstGreatestThanN(matrix12, 2, 0, matrix12.length-1));
		
		person p1 = new person(65,100);
		person p2 = new person(70,150);
		person p3 = new person(56,90);
		person p4 = new person(75,190);
		person p5 = new person(60,95);
		person p6 = new person(68,110);
		person p7 = new person(11,6);
		person p8 = new person(11,6);
		person[] matrix13 = {p1,p2,p3,p4,p5,p6,p7,p8};
		
		ss.sortObjects(matrix13);
		
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
		root.printPreOrder(root);
		System.out.println("Rank is: "+root.rankOfK(root, 25, 0));

	}

	void bubbleSort(int[] matrix){
		
		for(int i=matrix.length-1; i>=0; i--){
			for(int j=0,k=1; k <= i ; j++, k++){
				if(matrix[j] > matrix[k]){
					int temp = matrix[j];
					matrix[j] = matrix[k];
					matrix[k] = temp;
				}
			}
		}
		for(int i=0; i<matrix.length; i++)
			System.out.print(matrix[i]+" ");
		System.out.println();
	}
	
	void insertionSort(int[] matrix){
		for(int i=0;i<matrix.length; i++){
			
			for(int j=i; j>0; j--){
				if(matrix[j] < matrix[j-1]){
					int temp = matrix[j];
					matrix[j] = matrix[j-1];
					matrix[j-1] = temp;
				}
			}
			
			
		}
		for(int i=0; i<matrix.length; i++)
			System.out.print(matrix[i]+" ");
		System.out.println();

	}
	
	void selectionSort(int[] matrix){
		for(int i=0; i<matrix.length; i++){
			for(int j=i; j<matrix.length; j++){
				if(matrix[i] > matrix[j]){
					int temp = matrix[i];
					matrix[i] = matrix[j];
					matrix[j] = temp;
				}
			}
		}
		for(int i=0; i<matrix.length; i++)
			System.out.print(matrix[i]+" ");
		System.out.println();
	}
	
	//shell sort
	//comb sort
	void quickSort(int[] matrix){
		quickSortHelper(matrix, 0, matrix.length-1);
		for(int i=0; i<matrix.length; i++)
			System.out.print(matrix[i]+" ");
		System.out.println();
	}
	
	void quickSortHelper(int[] matrix, int low, int high){
		
		if(low >= high)
			return;
		
		Random gen = new Random();
				
		int pivot = low + gen.nextInt((high-low)/2 + 1);
		int temp = matrix[pivot];
		matrix[pivot] = matrix[high];
		matrix[high] = temp;
		
		
		int left = low;
		int right = high-1;
		
		while(left <= right){
			while(left <= right && matrix[left] <= matrix[high])
				left++;
			
			while(left <= right && matrix[right] > matrix[high])
				right--;
			
			
			if(left <= right){
				int tempVar = matrix[left];
				matrix[left] = matrix[right];
				matrix[right] = tempVar;
			}			
		}
		temp = matrix[left];
		matrix[left] = matrix[high];
		matrix[high] = temp;
		
		quickSortHelper(matrix, low, left-1);
		quickSortHelper(matrix, left+1, high);
		
	}
	void mergeSort(int[] matrix){
		matrix = mergeSortHelper(matrix);
		for(int i=0; i<matrix.length; i++)
			System.out.print(matrix[i]+" ");
		System.out.println();
	}
	
	int[] mergeSortHelper(int[] matrix){
		if(matrix.length <= 1)
			return matrix;
		
		int mid = (matrix.length-1) / 2;
		
		int[] left = new int[mid+1];
		int[] right = new int[matrix.length-1-mid];
		
		int[] dest = new int[matrix.length];
		copyElements(matrix, left, 0);
		copyElements(matrix, right,mid+1);
		
		left = mergeSortHelper(left);
		right = mergeSortHelper(right);
		
		mergeArray(left, right, dest);
		
		return dest;
	}
	
	void copyElements(int[] source, int[] dest, int index){
		for(int j=index, i=0; i<dest.length; i++, j++){
			dest[i] = source[j];
		}
	}
	
	void mergeArray(int[] left, int[] right, int[] dest){

		int lPtr = 0, rPtr = 0, dPtr = 0;
		while(lPtr < left.length && rPtr < right.length){
			if(left[lPtr] < right[rPtr]){
				dest[dPtr] = left[lPtr];
				lPtr++;
				dPtr++;
			}
			else{
				dest[dPtr] = right[rPtr];
				rPtr++;
				dPtr++;
			}
		}
		
		if(lPtr == left.length){
			while(rPtr < right.length){
				dest[dPtr] = right[rPtr];
				rPtr++;
				dPtr++;
			}
				
		}
		else{
			while(lPtr < left.length){
				dest[dPtr] = left[lPtr];
				lPtr++;
				dPtr++;
			}
		}
		
	}
	
	int quickSelect(int[] matrix, int low, int high, int k){
		if(low == high)
			return matrix[low];
		
		Random gen = new Random();
		int pivot = low + gen.nextInt((high-low)/2 + 1);
		System.out.println("pivot is: "+pivot);
		int temp = matrix[pivot];
		matrix[pivot] = matrix[high];
		matrix[high] = temp;
		
		int left = low;
		int right = high-1;
		
		while(left <= right){
			
			while(left <= right && matrix[left] <= matrix[high])
				left++;
			
			while(left <= right && matrix[right] > matrix[high])
				right--;
			
			if(left <= right){
				int val = matrix[left];
				matrix[left] = matrix[right];
				matrix[right] = val;
			}
			
		}
		
		temp = matrix[left];
		matrix[left] = matrix[high];
		matrix[high] = temp;
		
		if(k-1 == left)
			return matrix[left];
		else if(k-1 > left){
			return quickSelect(matrix, left+1, high, k);
		}
		else
			return quickSelect(matrix, low, left-1, k);
				
	}

	void countingSort(int[] matrix){
		
		int[] count = new int[10];
		
		for(int i=0; i<matrix.length; i++){
			count[matrix[i]]++;
		}
		
		int sum = 0;
		for(int i=0; i<count.length;i++){
			int temp = count[i];
			count[i] = sum;
			sum = sum+temp;
		}
		
		int[] output = new int[matrix.length];
		for(int i=0; i<matrix.length; i++){
			output[count[matrix[i]]] = matrix[i];
			count[matrix[i]]++;
		}
		
		for(int i=0; i<output.length; i++)
			System.out.print(output[i]+" ");
		System.out.println();
	}
	
	void radixSort(int[] matrix){
		
		for(int i = 1; i <= 10000; i=i*10){
			matrix = radixSortHelpercountingSort(matrix, i);
		}
		for(int i=1; i<matrix.length; i++)
			System.out.print(matrix[i]+" ");
		System.out.println();
		
	}
	
	int[] radixSortHelpercountingSort(int[] matrix, int position){
		
		int[] count = new int[10];
		
		for(int i=0; i<matrix.length; i++){
			int bucket = (matrix[i] / position) % 10;
			count[bucket]++;
		}
		
		int sum = 0;
		for(int i=0; i<count.length;i++){
			int temp = count[i];
			count[i] = sum;
			sum = sum+temp;
		}
		
		int[] output = new int[matrix.length];
		for(int i=0; i<matrix.length; i++){
			int bucket = (matrix[i] / position) % 10;
			output[count[bucket]] = matrix[i];
			count[bucket]++;
		}
		
		return output;
	}
	
	void binarySearch(int[] matrix, int num){
		int pos = binarysearchHelper(matrix, num, 0, matrix.length-1);
		if( pos < 0)
			System.out.println("Item not found.");
		else
			System.out.println("Item found at index "+pos);
		
	}
	
	int binarysearchHelper(int[] matrix, int num, int low, int high){
		if(low > high)
			return -1;		
		
		int mid = low + (high-low)/2;
		
		if(matrix[mid] == num)
			return mid;
		else if(matrix[mid] < num)
			return binarysearchHelper(matrix, num, mid+1, high);
		else
			return binarysearchHelper(matrix, num, low, mid-1);
		
	}
	
	int binarysearchHelperIterative(int[] matrix, int num){
		int low = 0;
		int high = matrix.length-1;
		
		while(low <= high){
			int mid = (low+high)/2;
			if(matrix[mid] == num)
				return mid;
			else if(matrix[mid] > num)
				high = mid-1;
			else
				low = mid+1;
		}
		
		return -1;
	}
	
	
	
	void quickSortString(String[] matrix){
		quickSortStringHelper(matrix, 0, matrix.length-1);
		for(int i=0;i<matrix.length;i++){
			System.out.print(matrix[i] +"  ");
		}
		System.out.println();
	}
	
	void quickSortStringHelper(String[] matrix, int low, int high){
		if(low>high)
			return;
		Random gen = new Random();
		
		int pivot = low + gen.nextInt((high-low)/2 + 1);
		
		String temp =  matrix[pivot];
		matrix[pivot] = matrix[high];
		matrix[high] = temp;
		
		int left = low;
		int right = high-1;
		
		while(left <= right){
			int compare = matrix[left].compareTo(matrix[high]);
			
			while(left <= right && compare <= 0){
				left++;
				compare = matrix[left].compareTo(matrix[high]);
			}
			
			compare = matrix[right].compareTo(matrix[high]);
			while(left <= right && compare > 0){
				compare = matrix[right].compareTo(matrix[high]);
				right--;
			}
			
			if(left <= right){
				temp = matrix[left];
				matrix[left] = matrix[right];
				matrix[right] = temp;
			}
		}
		
		temp =  matrix[left];
		matrix[left] = matrix[high];
		matrix[high] = temp;
		
		quickSortStringHelper(matrix, low, left-1);
		quickSortStringHelper(matrix, left+1, high);
		
	}
	
	void putAnagramsTogather(String[] matrix){
		/*putAnagramsTogatherHelper(matrix, 0, matrix.length-1);
		for(int i=0;i<matrix.length;i++){
			System.out.println(matrix[i]);
		}*/
		System.out.println("----------Following is the elegant way-------------");
		putAnagramsTogatherBetterWay(matrix);
		
	}
	
	void putAnagramsTogatherHelper(String[] matrix, int low, int high){	//crude way
		if(low>high)
			return;
		Random gen = new Random();
		
		int pivot = low + gen.nextInt((high-low)/2 + 1);
		System.out.println("Pivot is "+pivot);
		String temp =  matrix[pivot];
		matrix[pivot] = matrix[high];
		matrix[high] = temp;
		
		int left = low;
		int right = high-1;
		
		while(left <= right){
			int compare = checkIfAnagrams(matrix[left],matrix[high]);
			
			while(left <= right && compare <= 0){
				left++;
				compare = checkIfAnagrams(matrix[left],matrix[high]);
			}
			
			compare = checkIfAnagrams(matrix[right],matrix[high]);
			while(left <= right && compare > 0){
				compare = checkIfAnagrams(matrix[right],matrix[high]);
				right--;
			}
			
			if(left <= right){
				temp = matrix[left];
				matrix[left] = matrix[right];
				matrix[right] = temp;
			}
		}
		
		temp =  matrix[left];
		matrix[left] = matrix[high];
		matrix[high] = temp;
		
		putAnagramsTogatherHelper(matrix, low, left-1);
		putAnagramsTogatherHelper(matrix, left+1, high);
		
	}
	
	
	int checkIfAnagrams(String a, String b){
		char[] t1 = a.toCharArray();
		char[] t2 = b.toCharArray();
		Arrays.sort(t1);
		Arrays.sort(t2);
		String s1 = new String(t1);
		String s2 = new String(t2);
		return s1.compareTo(s2);
	}
	
	void putAnagramsTogatherBetterWay(String[] matrix){
		Hashtable<String,LinkedList<String>> hash = new Hashtable<String,LinkedList<String>>();
		for(String s : matrix){
			char[] charArr = s.toCharArray();
			Arrays.sort(charArr);
			String temp = new String(charArr);
			if(!hash.containsKey(temp)){
				hash.put(temp, new LinkedList<String>());
			}
			LinkedList<String> list = hash.get(temp);
			list.add(s);
		}
		String[] output = new String[matrix.length];
		int i=0;
		for(String key : hash.keySet()){
			LinkedList<String> list = hash.get(key);
			
			for(String s : list){
				output[i++] = s;
			}
		}
		for(i=0;i<output.length;i++){
			System.out.println(output[i]);
		}
	}
	
	void searchForString(String[] matrix, String query){
		System.out.println("String found at index: "+searchForStringHelper(matrix, query, 0, matrix.length-1));
		
	}
	
	int searchForStringHelper(String[] matrix, String query, int low, int high){
		if(high<low)
			return -1;
		
		int mid = low + (high-low)/2;
		
		if(matrix[mid].equals("")){
			int left = mid-1;
			int right = mid+1;
			
				while((left >= low && right <= high) && matrix[left].equals("") && matrix[right].equals("")){
					left--;
					right++;
				}
				if(left < low && right > high)
					return -1;
				if(low <= left && !matrix[left].equals("")){
					mid = left;
				}
				else{
					mid = right;
				}		
		}
		
		if(matrix[mid].compareTo(query) == 0)
			return mid;
		else if(matrix[mid].compareTo(query) < 0)
			return searchForStringHelper(matrix, query, mid+1, high);
		else
			return searchForStringHelper(matrix, query, low, mid-1);
	
	}
	
	boolean findElementInSorted2DArrayElemination(int[][] matrix, int value){
		int row = matrix.length-1;
		int column = 0;
		
		while(true){
			if(matrix[row][column] == value)
				return true;

			if(matrix[row][column] < value)
				column++;
			else 
				row--;
			
			if(row < 0 || column > matrix[0].length-1)
				return false;
		}
		
	}
	
	void findElementInSorted2DArrayBinary(int[][] matrix, int value){
		
	}
	
	boolean findElementInSorted2DArrayBinary(int[][] matrix, int value, int rowLow, int rowHigh, int colLow, int colHigh){
		
		// TO IMPLEMENT THIS FUNCTION
		
		
		return false;
	}
	
	
	int bSortTofindFirstGreatestThanN(int[] matrix, int value, int low, int high){
		if(high < low)
			return -1;
		if((high - low) <= 1){
			if(matrix[high] < value || matrix[low] > value)
				return -1;
		
		if(matrix[low] < value)
			return high;
		}
		
		int mid = (low + high)/2;
		if(matrix[mid] > value && matrix[mid-1] < value)
			return mid;
		else if(matrix[mid] > value)
			return bSortTofindFirstGreatestThanN(matrix, value, low, mid-1);
		else
			return bSortTofindFirstGreatestThanN(matrix, value, mid+1, high);
		
		
	}
	
	
	void sortObjects(person[] matrix){
		
		Arrays.sort(matrix);
		for(person p : matrix)
			System.out.println(p.weight+" "+p.height);
		System.out.println("---------------");
		//sortObjectsHelper(matrix, 0, matrix.length-1);
		
		ArrayList<ArrayOfPersons> persons = new ArrayList<ArrayOfPersons>();
		persons = getLargestSubsequence(matrix, persons, matrix.length-1);
		
		Collections.sort(persons);
		ArrayList<person> longest= persons.get(persons.size()-1).personArr;
		for(person p : longest)
			System.out.println(p.weight+" "+p.height);
		System.out.println();
		
	}
	
	ArrayList<ArrayOfPersons> getLargestSubsequence(person[] matrix, ArrayList<ArrayOfPersons> persons, int index){ //IMPROVE THIS SOLUTION
		if(index < 0)
			return new ArrayList<ArrayOfPersons>();
		
		
		persons = getLargestSubsequence(matrix, persons, index-1);
		ArrayOfPersons tempPerson = new ArrayOfPersons();
		tempPerson.personArr.add(matrix[index]);
		tempPerson.count++;
		ArrayList<ArrayOfPersons> personsNew = (ArrayList<ArrayOfPersons>)persons.clone();
		if(!persons.contains(tempPerson)){
			persons.add(tempPerson);
			personsNew.add(tempPerson);
		}
		
		
		
		for(ArrayOfPersons p : persons){
			ArrayOfPersons tempPersonList = p.clone(p);
			ArrayList<person> pz = tempPersonList.personArr;
			if(pz.get(pz.size()-1).height < matrix[index].height && pz.get(pz.size()-1).weight < matrix[index].weight){
				tempPersonList.personArr.add(matrix[index]);
				tempPersonList.count++;
				personsNew.add(tempPersonList);
			}
		}
			
		return personsNew;
	}
	
	void sortObjectsHelper(person[] matrix, int low, int high){
		
		if(low >= high)
			return;
		
		Random gen = new Random();
				
		int pivot = low + gen.nextInt((high-low)/2 + 1);
		person temp = matrix[pivot];
		matrix[pivot] = matrix[high];
		matrix[high] = temp;
		
		
		int left = low;
		int right = high-1;
		
		while(left <= right){
			while(left <= right && matrix[left].weight <= matrix[high].weight)
				left++;
			
			while(left <= right && matrix[right].weight > matrix[high].weight)
				right--;
			
			
			if(left <= right){
				person tempVar = matrix[left];
				matrix[left] = matrix[right];
				matrix[right] = tempVar;
			}			
		}
		temp = matrix[left];
		matrix[left] = matrix[high];
		matrix[high] = temp;
		
		sortObjectsHelper(matrix, low, left-1);
		sortObjectsHelper(matrix, left+1, high);
		
	}

}

class person implements Comparable<person>{
	int weight;
	int height;
	public person(int a, int b){
		this.weight = a;
		this.height = b;
	}
	
	@Override
	public int compareTo(person p2) {
		if(this.weight < p2.weight)
			return -1;
		else if(this.weight > p2.weight)
			return 1;
		else
			return 0;
	}
}

class ArrayOfPersons implements Comparable<ArrayOfPersons>, Cloneable, Comparator<ArrayOfPersons>{
	ArrayList<person> personArr= new ArrayList<person>();
	int count = 0;
	
	@Override
	public int compareTo(ArrayOfPersons p2) {
		if(this.count < p2.count)
			return -1;
		else if(this.count > p2.count)
			return 1;
		else
			return 0;
	}
	
	public ArrayOfPersons clone(ArrayOfPersons original){
		ArrayOfPersons copy = new ArrayOfPersons();
		copy.personArr = new ArrayList<person>(original.personArr);
		copy.count = original.count;
		return copy;
	}

	public ArrayOfPersons() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(ArrayOfPersons o1, ArrayOfPersons o2) {
		if(o1.count < o2.count)
			return -1;
		else if(o1.count > o2.count)
			return 1;
		else
			return 0;
	}
	
}
class tree{
	int value;
	tree left = null;
	tree right = null;
	int leftVal = 0;
	public tree(int value){
		this.value = value;
	}
	
	tree insert(tree root, int newnode){
		if(root == null){
			return new tree(newnode);
		}
		
		if(root.value > newnode){
			root.left = insert(root.left, newnode);
			root.leftVal++;
		}
		else
			root.right = insert(root.right, newnode);
		
		return root;
	}
	
	void printPreOrder(tree root){
		if(root == null)
			return;
		
		printPreOrder(root.left);
		System.out.println(root.value+" "+root.leftVal);
		printPreOrder(root.right);
		
	}
	
	int rankOfK(tree root, int value, int carry){
		if(root == null)
			return 0;
		
		if(root.value == value){
			return carry + 1 + root.leftVal;
		}
		int recv = 0;
		if(value < root.value)
			recv = rankOfK(root.left, value, carry);
		else{
			recv = rankOfK(root.right, value, carry + 1 + root.leftVal);
		}
		return recv;
	}
}
