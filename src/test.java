import java.util.*;
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String a = "aabbbbccaaa";
		char temp = a.charAt(0);
		StringBuffer sb = new StringBuffer();
		int count = 0;
		for(int i=1;i<a.length();i++){
			
			if(temp != a.charAt(i)){
				sb.append(temp);
				sb.append(count);
				temp = a.charAt(i);
				count = 0;
			}
			count++;
		}
		sb.append(temp);
		sb.append(count);
		System.out.println(sb);
	}

}
