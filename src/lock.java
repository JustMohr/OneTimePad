import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class lock {
	
	final static String fileName= "Files\\img.jpg";
	final static String fileKey = "Files\\img_key";
	
	
	static byte[] keyFile;
	
	public static void main(String[]args) throws IOException {
		
		File file = new File(fileName);
		
		drawFile(lockFile(file), keyFile);
		
	}
	
	
	public static byte[] lockFile(File file) throws IOException {
		
		byte[] fileArray=Files.readAllBytes(file.toPath());
		
		Random random = new Random();
		byte[] keyArray = new byte[fileArray.length]; //-->key
		random.nextBytes(keyArray);
		keyFile=keyArray;
		
		byte[] result = new byte[fileArray.length];
		
		for(int i=0; i<result.length; i++) {
			result[i] = (byte) (fileArray[i] ^ keyArray[i]);
		}
	
		return result;
		
	}
	
	
	public static void drawFile(byte[] lockedFile, byte[] keyFile) throws IOException {
		
		FileOutputStream fileOutStreamFile = new FileOutputStream(fileName);
		fileOutStreamFile.write(lockedFile);
		fileOutStreamFile.close();
		
		FileOutputStream fileOutStreamKey = new FileOutputStream(fileKey);
		fileOutStreamKey.write(keyFile);
		fileOutStreamKey.close();	
	}
	
	
	
	
}
