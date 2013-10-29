import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExploringFileHandling {


	/*
	 * 
	 * This function reads 4K buffer iteratively till end of file. Each time it reads the buffer, it calls findCount function
	 * to find counts of relevant strings
	 */
	static ResultCounts findTotalNexsanCounts(String fileName) {
		int fileBufferSize = 4 * 1024;
		
		InputStream input = null;
		long totalBytesRead = 0;
		long remainingBytes = 0;

		ResultCounts result = new ResultCounts();
		
		try {

			File file = new File(fileName);

			long totalBytes = file.length();
			System.out.println(totalBytes);

			remainingBytes = totalBytes;
			byte[] buffer = new byte[fileBufferSize];
			char[] charBuffer = new char[buffer.length];

			while (remainingBytes > 0) {
				long lengthToRead = remainingBytes < buffer.length ? remainingBytes
						: buffer.length;

				input = new BufferedInputStream(new FileInputStream(file));
				buffer = new byte[fileBufferSize];
				charBuffer = new char[buffer.length];
				input.skip(totalBytesRead);
				int bytesRead = input.read(buffer, (int) 0, (int) lengthToRead);
				if (bytesRead > 0) {
					totalBytesRead = totalBytesRead + bytesRead;
					remainingBytes = totalBytes - totalBytesRead;

					for (int i = 0; i < buffer.length && buffer[i] != 0; i++)
						charBuffer[i] = (char) buffer[i];

					findCount(charBuffer, result, remainingBytes);
					
					/* In case there are more characters to read i.e. if buffer is not full, we will rollback 19 bytes 
					so that we can count any missed/incomplete 'Nexsan' and 'Nexsan Technologies' 
					
					In this process I may count Nexsan twice since it can fit in 18 bytes and could have been counted earlier.
					To ensure this does not happen, findCount method does not count "Nexsan" strings that are in last 18 bytes of buffer. 
					They are counted only when they appear next time at starting of buffer since I am rolling back buffer by 18 bytes.
					
					But this done only if i have remaining bytes to read. When there is nothing to read, I do not roll back and hence count
					whatever is there which is the correct behaviour. 
					*/
					
					if (remainingBytes > 0) {
						totalBytesRead = totalBytesRead - 18;
						remainingBytes = totalBytes - totalBytesRead;
					}

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			System.out.println("Error Accessing File!");
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				System.out.println("Error Closing File!");
			}
		}

		return result;
	}

	/*
	 * findCount function returns the case sensitive and case insensitive counts of Nexsan and Nexsan Technologies 
	 * 
	 * RegEx are not allowed. I have not used them. But I am assuming library functions are allowed to find index 
	 * of a string in the buffer. I still have to parse the occurrence to check if it has valid delimiters.
	 * 
	 * Logic: 
	 * 1. Reads a buffer of 4KB and finds all case sensitive and case insensitive counts of Nexsan and Nexsan Technologies 
	 * 2. Some incomplete part of Nexsan and Nexsan Technologies may occur at end of buffer. To take care of that,
	 * 		2.1  I use an overlapping window of buffers. Each new buffer read starts from (currentPosition - 18) bytes
	 *		2.2  This helps in counting any string which was cut in last buffer
	 *		2.3  I am reading previous 18 bytes but not 19 because in case of 19 bytes "Nexsan Technologies" would have 
	 *			 completely fit
	 *		2.4  
	 * 
	 */
	static void findCount(char[] arr, ResultCounts result, long remainingBytes) {
		String bufferOriginal = new String(arr);
		bufferOriginal = bufferOriginal.trim();
		
		//function call which gets case sensitive and case insensitive counts in result object
		findCountHelper(bufferOriginal, true, result, remainingBytes);
		findCountHelper(bufferOriginal, false, result, remainingBytes);

		//In following loop I am counting number of Complete occurrences of "valid delimited strings"


	}
	
	static void findCountHelper(String bufferOriginal, boolean isCaseSensitive, ResultCounts result, long remainingBytes){
		int countNexsan = 0;
		int countNexsanTech = 0;

		String nexsan = "Nexsan";
		String nexsanTech = "Nexsan Technologies";
		if(!isCaseSensitive){
			bufferOriginal = bufferOriginal.toLowerCase();
			nexsan = nexsan.toLowerCase();
			nexsanTech = nexsanTech.toLowerCase();
		}

		int indexNexsan = 0;
		int indexNexsanTech = 0;
		boolean isNexsanCorrect = false;
		boolean isNexsanTechCorrect = false;
		System.out.println("CP1");
		while (indexNexsan > -1 || indexNexsanTech > -1) {
			indexNexsan = bufferOriginal.indexOf(nexsan, indexNexsan);
			if(indexNexsan > -1){
				isNexsanCorrect = nexsanDelimitChecker(bufferOriginal, indexNexsan);
				if(!isNexsanCorrect)
					indexNexsan = -1;
			}
			else
				isNexsanCorrect = false;
			
			indexNexsanTech = bufferOriginal.indexOf(nexsanTech, indexNexsanTech);
			if(indexNexsanTech > -1){
				isNexsanTechCorrect = nexsanTechDelimitChecker(bufferOriginal, indexNexsanTech);
				if(!isNexsanTechCorrect)
					indexNexsanTech = -1;
			}
			else
				isNexsanTechCorrect = false;
			
			
			if (isNexsanCorrect || isNexsanTechCorrect) {
				if (indexNexsan == indexNexsanTech){
					countNexsanTech++;
					indexNexsanTech += 19;
					indexNexsan = indexNexsanTech;
				}
				else if(indexNexsan > -1){
					countNexsan++;
					indexNexsan += 6;
					indexNexsanTech = indexNexsan;
				}
				else if(indexNexsan < 0){
					countNexsanTech++;
					indexNexsanTech += 19;
					indexNexsan = indexNexsanTech;				
				}

			}
		}
		System.out.println("CP2");

		indexNexsan = bufferOriginal.length() - nexsanTech.length() + 1;
		while (remainingBytes > 0 && indexNexsan > -1) {
			indexNexsan = bufferOriginal.indexOf(nexsan, indexNexsan);
			if (indexNexsan > 0) {
				countNexsan--;
				indexNexsan++;
			}
		}
		if(isCaseSensitive){
			result.setNexsanCountCS(result.getNexsanCountCS() + countNexsan);
			result.setNexsanTechCountCS(result.getNexsanTechCountCS() + countNexsanTech);
		}else{
			result.setNexsanCountNCS(result.getNexsanCountNCS() + countNexsan);
			result.setNexsanTechCountNCS(result.getNexsanTechCountNCS() + countNexsanTech);
		}
			
	}
	
	static boolean nexsanDelimitChecker(String str, int index){
		
		if(index > (str.length() - 1) - 19)
			return false;						
		
		int endChar = str.charAt(index+6);
		if((endChar >= 48 && endChar <= 57) || (endChar >= 65 && endChar <= 90) || (endChar >= 97 && endChar <= 122))
			return false;
		
		if(index > 0){
			int startChar = str.charAt(index-1);
			if((startChar >= 48 && startChar <= 57) || (startChar >= 65 && startChar <= 90) || (startChar >= 97 && startChar <= 122))
				return false;
		}
			
		return true;
	}
	
	static boolean nexsanTechDelimitChecker(String str, int index){
		
		if(index > (str.length() - 1) - 19)
			return false;						
		
		int endChar = str.charAt(index+19);
		if((endChar >= 48 && endChar <= 57) || (endChar >= 65 && endChar <= 90) || (endChar >= 97 && endChar <= 122))
			return false;
		
		if(index > 0){
			int startChar = str.charAt(index-1);
			if((startChar >= 48 && startChar <= 57) || (startChar >= 65 && startChar <= 90) || (startChar >= 97 && startChar <= 122))
				return false;
		}
			
		return true;

	}

	public static void main(String args[]) throws IOException {
		String fileName;
		try{
		fileName = args[0];
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Filename not specified in argument!");
			System.exit(0);
		}
		
		ResultCounts result = findTotalNexsanCounts("TestFile");
		
		/*Priority of case insensitive strings is kept lower. i.e. If a string has been counted as 
		already been counted in case sensitive, it cannot be shown again in case insensitive.
		To ensure this, I have subtracted the case sensitive counts from insensitive counts*/ 
		
		System.out.println("Total Case-Sensitive Nexsan Count is: "+result.getNexsanCountCS());

		System.out.println("Total Case-Sensitive Nexsan Technologies Count is: "+result.getNexsanTechCountCS());
		

		
		System.out.println("Total Case-InSensitive Nexsan Count is: "+(result.getNexsanCountNCS() - result.getNexsanCountCS()));

		System.out.println("Total Case-InSensitive Nexsan Technologies Count is: "+(result.getNexsanTechCountNCS() - result.getNexsanTechCountCS()));

	}
}


/* 
 *  Wrapper Result Class which stores all relevant counts of strings.
 */
class ResultCounts {
	private int nexsanCountCS;
	private int nexsanTechCountCS;
	private int nexsanCountNCS;
	private int nexsanTechCountNCS;
	
	public ResultCounts(){
		nexsanCountCS = 0;
		nexsanTechCountCS = 0;
		nexsanCountNCS = 0;
		nexsanTechCountNCS = 0;	
	}

	public int getNexsanCountCS() {
		return nexsanCountCS;
	}

	public void setNexsanCountCS(int nexsanCountCS) {
		this.nexsanCountCS = nexsanCountCS;
	}

	public int getNexsanTechCountCS() {
		return nexsanTechCountCS;
	}

	public void setNexsanTechCountCS(int nexsanTechCountCS) {
		this.nexsanTechCountCS = nexsanTechCountCS;
	}

	public int getNexsanCountNCS() {
		return nexsanCountNCS;
	}

	public void setNexsanCountNCS(int nexsanCountNCS) {
		this.nexsanCountNCS = nexsanCountNCS;
	}

	public int getNexsanTechCountNCS() {
		return nexsanTechCountNCS;
	}

	public void setNexsanTechCountNCS(int nexsanTechCountNCS) {
		this.nexsanTechCountNCS = nexsanTechCountNCS;
	}

}
