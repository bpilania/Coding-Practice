import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;


public class RecursionAndDP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(countWaysToClimbStaircase(4, new Hashtable<Integer, Integer>()));
		
		
		ArrayList<ArrayList<String>> main = new ArrayList<ArrayList<String>>();
		System.out.println(pathsOfARobot(2,1, new ArrayList<String>(), main));
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
		printProperArrangedBrackets(2, listBrackets);
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
		ArrayList<ArrayList<Integer>> listCents = new ArrayList<ArrayList<Integer>>();
		countWaysToNCents(10, currList, listCents, -1);
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
		if(n<0)
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
	
	static void countWaysToNCents(int total, ArrayList<Integer> currList, ArrayList<ArrayList<Integer>> list, int value){
		if(total < 0)
			return;
		
		if(value > 0)
			currList.add(value);
		ArrayList<Integer> newCurrList = new ArrayList<Integer>(currList);
		Collections.sort(newCurrList);
		if(total == 0){
				if(!list.contains(newCurrList)){
				list.add(newCurrList);
				}
				currList.remove(currList.size()-1);
				return;
		}
		
		countWaysToNCents(total-3, currList, list, 3);
		countWaysToNCents(total-2, currList, list, 2);
		countWaysToNCents(total-1, currList, list, 1);
		
		if(!currList.isEmpty()) currList.remove(currList.size()-1);
	}
	
	static void eightQueensProblem(int[][] board, int j){
		if(j > 7){
			System.out.println("---------------------");

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
	
	
	
}
