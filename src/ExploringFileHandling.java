import java.io.*;

public class ExploringFileHandling {

	/**
	 * @param args
	 */

	static Result findTotalNexsanCounts(String fileName) {
		InputStream input = null;
		int countNexsan = 0;
		int countNexsanTech = 0;

		long totalBytesRead = 0;
		long remainingBytes = 0;
		Result result = new Result();
		try {

			File file = new File(fileName);

			long totalBytes = file.length();
			System.out.println(totalBytes);

			remainingBytes = totalBytes;
			byte[] buffer = new byte[100];
			char[] charBuffer = new char[buffer.length];

			while (remainingBytes > 0) {
				long lengthToRead = remainingBytes < buffer.length ? remainingBytes
						: buffer.length;

				input = new BufferedInputStream(new FileInputStream(file));
				buffer = new byte[100];
				input.skip(totalBytesRead);
				int bytesRead = input.read(buffer, (int) 0, (int) lengthToRead);
				if (bytesRead > 0) {
					totalBytesRead = totalBytesRead + bytesRead;
					remainingBytes = totalBytes - totalBytesRead;
					for (int i = 0; i < buffer.length && buffer[i] != 0; i++)
						charBuffer[i] = (char) buffer[i];

					findCount(charBuffer, result);

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	static void findCount(char[] arr, Result result) {
		int countNexsan = 0;
		int countNexsanTech = 0;
		boolean foundNexsan = false;

		boolean foundNexsanTech = false;

		int maxLength = arr.length;
		String bufferOriginal = new String(arr);
		bufferOriginal = bufferOriginal.trim();
		String bufferSmallCased = bufferOriginal.toLowerCase();

		String nexsan = "Nexsan";
		String nexsanTech = "Nexsan Technologies";

		int indexNexsan = 0;
		while (indexNexsan > -1) {
			indexNexsan = bufferOriginal.indexOf(nexsan, indexNexsan);
			int indexNexsanTech = bufferOriginal.indexOf(nexsanTech,
					indexNexsan);
			if (indexNexsan > 0) {
				if (indexNexsan == indexNexsanTech)
					countNexsanTech++;
				else
					countNexsan++;

				indexNexsan++;
			}
		}

		indexNexsan = bufferOriginal.length() - nexsanTech.length();
		while (indexNexsan > -1) {
			indexNexsan = bufferOriginal.indexOf(nexsan, indexNexsan);
			if (indexNexsan > 0) {
				countNexsan--;
				indexNexsan++;
			}
		}
		result.nexsanCount += countNexsan;
		result.nexsanTechCount += countNexsanTech;

	}

	static void write(byte[] aInput, String aOutputFileName) {
		System.out.println("Writing binary file...");
		try {
			OutputStream output = null;
			try {
				output = new BufferedOutputStream(new FileOutputStream(
						aOutputFileName));
				output.write(aInput);
			} finally {
				output.close();
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File not found.");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static void main(String args[]) throws IOException {
		char c;
		// Create a BufferedReader using System.in
		String test = "HelloThNexsanisIsBhaskarNexsanNexsan TechnologiesNexsan Nexsan";
		char[] testArr = test.toCharArray();

		System.out.println("Enter characters, 'q' to quit.");
		// read characters
		int i = 0;

		byte[] arr = new byte[testArr.length];
		for (i = 0; i < testArr.length && testArr[i] != 0; i++) {
			arr[i] = (byte) testArr[i];

		}

		write(arr, "TestFile");

		arr = new byte[20];

		Result result = findTotalNexsanCounts("TestFile");

		System.out.println(result.nexsanCount);
		System.out.println(result.nexsanTechCount);

	}
}

class Result {
	int nexsanCount = 0;
	int nexsanTechCount = 0;
}
