
public class ExploringBitManipulation {

	/**
	 * @param args
	 */
	
	static String convertToBits(double n) throws Exception{
		if(n >= 1 || n < 0)
			throw new Exception("Error");
		
		StringBuilder sb = new StringBuilder();
		sb.append(".");
		while(n > 0){
			double r = n * 2;
			if(sb.length() == 32){
				throw new Exception("Outside scope");
			}
			if(r >= 1){
				sb.append("1");
				n = r - 1;
			}
			else{
				sb.append("0");
				n = r;
			}
			
		}
		
		return new String(sb);
		
	}
	
	public static String printBinary(double num) {
		if (num >= 1 || num <= 0) {
			return "ERROR";
		}

		StringBuilder binary = new StringBuilder();
		binary.append(".");
		while (num > 0) {
			/* Setting a limit on length: 32 characters */
			if (binary.length() > 32) {
				return "ERROR";
			}
			double r = num * 2;
			if (r >= 1) {
				binary.append(1);
				num = r - 1;
			} else {
				binary.append(0);
				num = r;
			}
		}
		return binary.toString();
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			System.out.println(convertToBits(.125));
			System.out.println(printBinary(.125));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
