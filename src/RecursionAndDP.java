import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.SortedSet;
import java.util.TreeSet;


public class RecursionAndDP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Fib of N is: "+fib(30, new int[100]));
		System.out.println("Count Stairs New: "+countSteps(10));
		System.out.println("Count Stairs Old: "+countWaysToClimbStaircase(10, new Hashtable<Integer, Integer>()));
		boolean[][] allowed = new boolean[3][3];
		allowed[1][0] = true;
		ArrayList<ArrayList<String>> listofpath = new ArrayList<ArrayList<String>>();
		int[][] cache = new int[3][3];
		for(int[] row : cache)
			Arrays.fill(row, -1);
		System.out.println("Robot ways new are: "+countRobotWays(0,0,cache, allowed, new ArrayList<String>(), listofpath));
		char[] arr = {'a','b','c'};
		ArrayList<ArrayList<Character>> main1 = new ArrayList<ArrayList<Character>>();
		findAllSubsets(arr, main1, 0);
		ArrayList<StringBuffer> main2 = new ArrayList<StringBuffer>();
		findAllPermutations(arr, main2, 1);
		
		ArrayList<StringBuffer> setOfStrings = new ArrayList<StringBuffer>();
		ArrayList<String> question = new ArrayList<String>();
		question.add("ABC");
		question.add("DE");
		question.add("FGH");
		findAllSetsOf2DStrings(setOfStrings, question);
		
		
		ArrayList<ArrayList<String>> main = new ArrayList<ArrayList<String>>();
		System.out.println("Robot ways old are: "+pathsOfARobot(2,2, new ArrayList<String>(), main));
		System.out.println(main);
		
		int[] values = {-10,-5,2,2,2,3,4,7,9,12,13};
		System.out.println(findMagicNumber(values, 0, values.length-1));
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		ArrayList<ArrayList<Integer>> set = new ArrayList<ArrayList<Integer>>();
		set.add(new ArrayList<Integer>());
		allSubsetsOfASet(list, set);
		System.out.println(set);
		
		ArrayList<String> listStr = new ArrayList<String>();
		StringBuilder strBuf = new StringBuilder();
		StringBuilder str = new StringBuilder();
		allPermutationsOfAString(str.append("ABCD"), strBuf, listStr);
		System.out.println(listStr);
		
		
		
		ArrayList<StringBuilder> listBrackets = new ArrayList<StringBuilder>();
		printProperArrangedBrackets(3, listBrackets);
		System.out.println(listBrackets);
		
		
		int[][] colorbox = {{1,1,1,1,1,1,1,1,1,1},
		{1,1,0,0,0,1,0,0,0,1},
		{1,0,0,0,0,0,0,0,0,1},
		{1,0,1,0,1,0,0,1,0,1},
		{1,0,0,0,1,0,0,0,0,1},
		{1,1,1,1,0,1,1,1,1,1}};
		for(int i=0; i<colorbox.length; i++){
			for(int j=0; j<colorbox[0].length; j++){
				System.out.print(colorbox[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		colorFill(colorbox, 3, 1, 2);
		for(int i=0; i<colorbox.length; i++){
			for(int j=0; j<colorbox[0].length; j++){
				System.out.print(colorbox[i][j]+" ");
			}
			System.out.println();
		}
		
		
		ArrayList<Integer> currList = new ArrayList<Integer>();
		HashSet<ArrayList<Integer>> listCents = new HashSet<ArrayList<Integer>>();
		countWaysToNCents(5, currList, listCents, -1);
		System.out.println(listCents);
		System.out.println(listCents.size());
		
		int[][] board = new int[8][8];
		for(int i=0; i<board.length; i++){
			for(int k=0; k<board[0].length; k++){
				System.out.print(board[i][k]+" ");
			}
			System.out.println();
		}
		eightQueensProblem(board, 0);
		System.out.println(count);
		int[][] listOfBoxes = {
				{2,4,4},
				{2,0,3},
				{3,4,5},
				{3,1,2},
				{4,2,5},
				{5,6,7},
				{6,4,5},
				{5,2,3},
				{6,3,4},
				{7,4,5},
				{8,5,6},
				{10,10,10}
				};
		ArrayList<int[]> finalList = new ArrayList<int[]>();
		finalList = placeBoxes(listOfBoxes, new ArrayList<int[]>(), finalList, 0);
		System.out.println("------------------------");
		for(int[] temp : finalList){
			System.out.println(temp[0]+" "+temp[1]+" "+temp[2]);
		}
		HashSet<ArrayList<Integer>> set1 = new HashSet<ArrayList<Integer>>();
		ArrayList<Integer> current = new ArrayList<Integer>();
		findNumberofWays(50, set1,current);
		System.out.println(set1.size());
		
		
		System.out.println("Longest Subseq. is: "+findLongestSubSequence("BBABCBCAB", 0, 8, new Hashtable<String, Integer>()));
		
	}
	
	public static void findNumberofWays(int cash, HashSet<ArrayList<Integer>> set, ArrayList<Integer> current){
		if(cash < 0) {
			current.remove(current.size()-1);
			return;
		}
		if(cash == 0){ 
			Collections.sort(current);
			if(!set.contains(current))
				set.add((ArrayList<Integer>)current.clone()); 
			current.remove(current.size()-1);
			return; 
			}
		
		current.add(10);
		findNumberofWays(cash - 10, set, current);
		
		current.add(20);
		findNumberofWays(cash - 20, set, current);
		
		current.add(30);
		findNumberofWays(cash - 30, set, current);

		if(current.size() > 0)
		current.remove(current.size()-1);
		
		return;
	}
	
	
	static int countWaysToClimbStaircase(int stairs, Hashtable<Integer,Integer> hash){
		if(stairs < 0)
			return 0;
		if(stairs == 0)
			return 1;
		if(hash.containsKey(stairs))
			return hash.get(stairs);
		
		int count_1 =  countWaysToClimbStaircase(stairs - 1, hash) ;
		int count_2 =  countWaysToClimbStaircase(stairs - 2, hash) ;
		int count_3 =  countWaysToClimbStaircase(stairs - 3, hash);
		int count = count_1 + count_2 + count_3;
		
		hash.put(stairs, count);
		return count;
	}
	
	static int pathsOfARobot(int x, int y, ArrayList<String> temp, 
			ArrayList<ArrayList<String>> main){ 
		temp.add(x+" "+y);
		if(x < 0 || y < 0){
			return 0;
		}
		
		if(x == 0 && y ==0){
			main.add((ArrayList<String>)temp.clone());
			return 1;
		}
		System.out.println("*******"+x+", "+y);
		int count_x = pathsOfARobot(x-1,y, temp, main);
		temp.remove(temp.size()-1);
		int count_y = pathsOfARobot(x,y-1, temp,main);
		temp.remove(temp.size()-1);
		int count = count_x + count_y;
		return count;
	}
	
	static int findMagicNumber(int[] values, int low, int high){
		if(low > high)
			return -1;
		
		int mid = (low+high)/2;
		
		if(values[mid] == mid)
			return mid;
		
		int index = findMagicNumber(values, low, Math.min(mid-1, values[mid]));
		if(index >= 0)
			return index;
		
		index = findMagicNumber(values, Math.max(mid+1, values[mid]), high);
		if(index >= 0)
			return index;
		
		
		return index;
	}
	
	static void allSubsetsOfASet(ArrayList<Integer> list, ArrayList<ArrayList<Integer>> set){
		if(list.isEmpty()){
			return;
			
		}
			
		if(!set.contains(list))
			set.add(new ArrayList<Integer>(list));
		
		for(Integer i : list){
			ArrayList<Integer> temp = new ArrayList<Integer>(list);
			int index = temp.indexOf(i);
			temp.remove(index);
			allSubsetsOfASet(temp, set);
		}	
		
	}
	
	static void allPermutationsOfAString(StringBuilder str, StringBuilder newStr, 
			ArrayList<String> list){ //Solve using other method
		if(str.length() == 0){
			list.add(new String(newStr));
			newStr.deleteCharAt(newStr.length()-1);
			return;
		}
		
		for(int i=0; i<str.length(); i++){
			newStr = newStr.append(str.charAt(i));
			StringBuilder buff = new StringBuilder(str);
			buff.deleteCharAt(i);
			allPermutationsOfAString(buff, newStr, list);
		}
		
		if(newStr.length() > 0) newStr.deleteCharAt(newStr.length()-1);

	}
	
	static void printProperArrangedBrackets(int n, ArrayList<StringBuilder> list){ //Check problem with contains method
		if(n<=0)
			return;
		ArrayList<StringBuilder> tempBuff = new ArrayList<StringBuilder>(list);
		for(StringBuilder s : tempBuff){
			list.remove(s);
			
			for(int i=0; i<s.length();i++){
				StringBuilder temp = new StringBuilder(s);
				temp.insert(i,"()");
				if(list.size() > 1)
				System.out.println(temp.hashCode() + " " + list.get(1).hashCode());
				if(!list.contains(temp)) 
						list.add(temp);
				
				
			}
			
		}
		
		if(list.isEmpty())
			list.add(new StringBuilder().append("()"));
		
		printProperArrangedBrackets(n-1,list);
	}
	
	static void colorFill(int[][] matrix, int x, int y, int value){
		if(x < 0 || y < 0 || x > matrix.length || y > matrix[0].length || matrix[x][y] != 0)
			return;
		
		matrix[x][y] = value;
		
		colorFill(matrix, x-1, y, value);
		colorFill(matrix, x+1, y, value);
		colorFill(matrix, x, y-1, value);
		colorFill(matrix, x, y+1, value);
	}
	
	static void countWaysToNCents(int total, ArrayList<Integer> currList, HashSet<ArrayList<Integer>> list, int value){
		if(total < 0)
			return;
		
		if(value > 0)
			currList.add(value);
		ArrayList<Integer> newCurrList = new ArrayList<Integer>(currList);
		Collections.sort(newCurrList);
		if(total == 0){
				list.add(newCurrList);
				currList.remove(currList.size()-1);
				return;
		}
		
		countWaysToNCents(total-3, currList, list, 3);
		countWaysToNCents(total-2, currList, list, 2);
		countWaysToNCents(total-1, currList, list, 1);
		
		if(!currList.isEmpty()) currList.remove(currList.size()-1);
	}
	
	static int count = 0;
	static void eightQueensProblem(int[][] board, int j){
		if(j > 7){
			System.out.println("---------------------");
			count++;
			for(int i=0; i<board.length; i++){
				for(int k=0; k<board[0].length; k++){
					System.out.print(board[i][k]+" ");
				}
				System.out.println();
			}

			return;
		}
		
		for(int i=0; i<8; i++){
			if(checkPlacement(board, i, j)){
				board[i][j] = 1;
				eightQueensProblem(board, j+1);
			}
			if(i<8)
				board[i][j] = 0;
			
		}
			
	}
	static boolean checkPlacement(int[][] board, int x, int y){
		for(int j=0; j<7; j++){
			if(board[x][j] == 1 || board[j][y] == 1)
				return false;
		}
		
		for(int i=x, j=y; i>=0 && j>=0; i--, j--){
			if(board[i][j]==1)
				return false;
		}
		
		for(int i=x, j=y; i<=7 && j<=7; i++, j++){
			if(board[i][j]==1)
				return false;
		}
		
		for(int i=x, j=y; i>=0 && j<=7; i--, j++){
			if(board[i][j]==1)
				return false;
		}
		
		for(int i=x, j=y; i<=7 && j>=0; i++, j--){
			if(board[i][j]==1)
				return false;
		}
		
		return true;
	}
	
	static  ArrayList<int[]> placeBoxes(int[][] list, ArrayList<int[]> temp, ArrayList<int[]> finalStack, int j){
		if(j>list.length-1){
			return finalStack;
		}
		
		for(int i = j; i<list.length; i++){
			
			int[] lastEntry = new int[3];
			if(temp.size() > 0)
				lastEntry = temp.get(temp.size()-1);
						
			if((list[i][0] > lastEntry[0] && list[i][1] > lastEntry[1] && list[i][2] > lastEntry[2]) || temp.size() == 0){
				temp.add(list[i]);
				finalStack = placeBoxes(list, temp, finalStack, i+1);
				if(finalStack.size() < temp.size()){
					finalStack = new ArrayList<int[]>(temp);
				}
				if(!temp.isEmpty())
					temp.remove(temp.size()-1);	
			}
		
		}
		
		
		return finalStack;
		
	}
	
	public void printCube(int[][] matrix, ArrayList<Integer> list){//Write this function
		
		
	}
	
	static int fib(int a, int[] cache){
		if(a == 0) return 0;
		if(a == 1) return 1;
		
		if(cache[a] > 0)
			return cache[a];
		cache[a] = fib(a-1, cache) + fib(a-2, cache);			
		
		return cache[a];
		
	}
	
	static int countSteps(int n){
		if(n < 0 ) return 0;
		if(n == 0) return 1;
		return countSteps(n-1) + countSteps(n-2) + countSteps(n-3);
	}
	
	static int countRobotWays(int x, int y, int[][] cache, boolean[][] allowed, ArrayList<String> path, ArrayList<ArrayList<String>> listofpath){
		if( x > 2) return 0;
		if(y > 2) return 0;
		if(x == 2 && y == 2){ 
			path.add(x+","+y);
			listofpath.add((ArrayList<String>)path.clone());
			path.remove(path.size()-1);
			return 1;
		}
		
		path.add(x+","+y);
		if(cache[x][y] >= 0) return cache[x][y];
		
		cache[x][y] = countRobotWays(x+1, y, cache, allowed, path, listofpath) + countRobotWays(x, y+1, cache, allowed, path, listofpath);
		System.out.println("*******NEW"+x+", "+y);
		path.remove(path.size()-1);
		return cache[x][y];
		
	}
	/*ABC
	 * A
	 * 
	 * 
	 */
	static void findAllSubsets(char[] arr, ArrayList<ArrayList<Character>> main, int index){
		if(main.size() == 0) main.add(new ArrayList<Character>());
		if(index == arr.length) return;
		ArrayList<ArrayList<Character>> temp = new ArrayList<ArrayList<Character>>();
		for(ArrayList<Character> list : main){
			ArrayList<Character> tempList = new ArrayList<Character>(list);
			tempList.add(arr[index]);
			temp.add(tempList);
		}
		
		for(ArrayList<Character> list : temp)
			main.add(list);
		
		findAllSubsets(arr, main, index+1);
		
	}
	
	static void findAllPermutations(char[] arr, ArrayList<StringBuffer> main, int index){
		if(index == arr.length)
			return;
		char temp = arr[index];
		if(main.size() == 0)
			main.add(new StringBuffer(String.valueOf(arr[0])));
		
		
		
		ArrayList<StringBuffer> tempBuffer = new ArrayList<StringBuffer>();
		for(StringBuffer buffer : main){
			for(int i=0;i<buffer.length()+1; i++){
				StringBuffer copyBuffer = new StringBuffer(buffer);
				copyBuffer.insert(i, temp);
				tempBuffer.add(copyBuffer);
			}
		}
		main.clear();
		
		for(StringBuffer buffer : tempBuffer)
			main.add(buffer);
		
		findAllPermutations(arr, main, index+1);
			
		
	}
	//ABC
	//DE
	
	static void findAllSetsOf2DStrings(ArrayList<StringBuffer> result, ArrayList<String> question){
		
		result.add(new StringBuffer());
		for(String str : question){
			findAllSetsOf2DStringsHelper(result, str, 0);
		}
	}
	

	static void findAllSetsOf2DStringsHelper(ArrayList<StringBuffer> result, String toAdd, int index){
		if(index == toAdd.length()){
			result.clear();
			return;
		}
		
		ArrayList<StringBuffer> newResult = new ArrayList<StringBuffer>();
		
		for(StringBuffer str : result){
			StringBuffer sb = new StringBuffer(str);
			newResult.add(sb.append(toAdd.charAt(index)));
		}
		findAllSetsOf2DStringsHelper(result, toAdd, index+1);
		
		for(StringBuffer str : newResult){
			result.add(str);
		}
	}
	
	static int findLongestSubSequence(String str, int i, int j, Hashtable<String, Integer> hash){
		if(i == j) return 1;
		
		if(i+1 == j && str.charAt(i) == str.charAt(j))
			return 2;
		
		if(hash.contains(i+"-"+j)){
			System.out.println("Contains");
			return hash.get(i+"-"+j);
		}
		if(str.charAt(i) == str.charAt(j)){
			int temp = findLongestSubSequence(str, i+1, j-1, hash);
			hash.put((i+1)+"-"+(j-1), temp);
			return  temp + 2;
		}
		
		int max1 = findLongestSubSequence(str, i+1, j, hash);
		hash.put((i+1)+"-"+(j), max1);
		int max2 = findLongestSubSequence(str, i, j-1, hash);
		hash.put((i)+"-"+(j-1), max2);

		return Math.max(max1,max2);
		
		
		
	}
		
	
}
