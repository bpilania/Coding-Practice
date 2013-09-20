public class test{
	
	static void plot(double x, double y, double rad){
		for(double i=0;i <= 2*rad+1; i += .1){
			for(double j=0;j <= 2*rad+1; j += .1){
				double dist = Math.sqrt(Math.pow((x-i), 2) + Math.pow((y-j),2));
				System.out.println(dist);
						if(dist + .1 == rad || dist - .1 == rad)
							System.out.print("X");
						else
							System.out.print(" ");
			}
			System.out.println();
			
		}
	}
	
	public static void main(String args[]){
		plot(10, 10, 3);
	}
}