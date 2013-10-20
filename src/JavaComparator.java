import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class JavaComparator {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		perswon[] perswonarr = new perswon[4];
		perswonarr[0] = new perswon("Bhaskar", 20);
		perswonarr[1] = new perswon("Abhimanyu", 24);
		perswonarr[2] = new perswon("Sagar", 18);
		perswonarr[3] = new perswon("Abhinav", 17);
		
		Arrays.sort(perswonarr, new Comparator<perswon>(){
			public int compare(perswon p1, perswon p2){
				
				return p1.name.compareTo(p2.name);
			}			
		});
		
		System.out.println(perswonarr);
		
		Arrays.sort(perswonarr, new perswon());
		
		System.out.println(perswonarr);
		
		
		
		
	}
	


}

class perswon  implements Comparator<perswon>{
	String name;
	int age;
	public perswon(String p, int a){
		name = p;
		age = a;
	}
	
	public perswon() {
		// TODO Auto-generated constructor stub
	}

	public int compareTo(perswon p1){
		
		return p1.name.compareTo(p1.name);
	}
	
	public int compare(perswon p1, perswon p2){
		
		return p1.age > p2.age? 1 : -1;
	}

}


class compare1 implements Comparator<perswon>{
	public int compare(perswon p1, perswon p2){
		
		return p1.name.compareTo(p2.name);
	}
}
