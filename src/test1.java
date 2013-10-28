import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


class Trie {
	 
    private Node root = new Node("");

    public Trie() {}

    public Trie(List<String> argInitialWords) {
            for (String word:argInitialWords) {
                    addWord(word);
            }
    }

    public void addWord(String argWord) {
            char argChars[] = argWord.toCharArray();
            Node currentNode = root;

            for (int i = 0; i < argChars.length; i++) {
                    if (!currentNode.containsChildValue(argChars[i])) {
                            currentNode.addChild(argChars[i], new Node(currentNode.getValue() + argChars[i]));
                    }

                    currentNode = currentNode.getChild(argChars[i]);
            }

            currentNode.setIsWord(true);
    }

    public boolean containsPrefix(String argPrefix) {
            return contains(argPrefix, false);
    }

    public boolean containsWord(String argWord) {
            return contains(argWord, true);
    }

    public Node getWord(String argString) {
            Node node = getNode(argString);
            return node != null && node.isWord() ? node : null;
    }

    public Node getPrefix(String argString) {
            return getNode(argString);
    }


    private boolean contains(String argString, boolean argIsWord) {
            Node node = getNode(argString);
            return (node != null && node.isWord() && argIsWord) || (!argIsWord && node != null);
    }

    private Node getNode(String argString) {
            Node currentNode = root;
            char argChars[] = argString.toCharArray();
            for (int i = 0; i < argChars.length && currentNode != null; i++) {
                    currentNode = currentNode.getChild(argChars[i]);

                    if (currentNode == null) {
                            return null;
                    }
            }

            return currentNode;
    }
}


class Node {

    private final String value;
    private Map<Character, Node> children = new HashMap<Character, Node>();
    private boolean isValidWord;

    public Node(String argValue) {
            value = argValue;
    }

    public boolean addChild(char c, Node argChild) {
            children.put(c, argChild);
            return true;
    }

    public boolean containsChildValue(char c) {
            return children.containsKey(c);
    }

    public String getValue() {
            return value.toString();
    }

    public Node getChild(char c) {
            return children.get(c);
    }

    public boolean isWord() {
            return isValidWord;
    }

    public void setIsWord(boolean argIsWord) {
            isValidWord = argIsWord;

    }

    public String toString() {
            return value;
    }

}
class a<T>{
	int aa;
	public a(){
		System.out.println("In a");
	}
	public void abc(){
		System.out.println("In abc of a");
	}
	public int geta(){
		return aa;
	}
	public void seta(int a){
		aa = a;
	}
}

class b extends a{
	public b(){
		System.out.println("In b");
	}
	public void abc(){
		System.out.println(aa);
	}
}


public class test1 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char numberToAlpha[][]={{'a','b','c'},{'d','e'},{'g','h','i'}};
    static List <String> s123 = new ArrayList<String>();

    static void stringToNumbers(String s,int index){
    	if(index >= numberToAlpha.length)
    		return;
    	for(int j = 0;j < numberToAlpha[index].length;j++){
    		String y = s + numberToAlpha[index][j]; 
    		if(y.length() == 3){
    			s123.add(y);
    			y = y.substring(0,y.length()-1);
    		}
    		stringToNumbers(y,index+1);
    	}	
    }
    public static void main(String[] args) {
    	stringToNumbers(new String(), 0);
    	System.out.println(s123);
    }

    public static boolean confirm( String question )
      throws IOException
    {
            while (true) {
                    System.out.print(question + " ");
                    String ans = br.readLine().trim();
                    if (ans.equalsIgnoreCase("N") || ans.equalsIgnoreCase("NO"))
                            return false;
                    else if (ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("YES"))
                            return true;
                    System.out.println("Please answer Y, YES, or N, NO");
            }
    }
}

