import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class encrypt {
	
	final static String fileName= "Files\\img.jpg";
	final static String fileKey = "Files\\img_key";

	public static void main(String[] args) throws IOException {
		
		
		encrypt();
		
	}
	
	
	static void encrypt() throws IOException {
		File lockedImg = new File(fileName);
		File key = new File(fileKey);
		
		byte[] arrayLockedImg = Files.readAllBytes(lockedImg.toPath());
		byte[] arrayKey = Files.readAllBytes(key.toPath());
		
		System.out.println(arrayLockedImg.length);
		System.out.println(arrayKey.length);
		
		byte[] unlocked = new byte[arrayKey.length];
		for(int i=0; i<unlocked.length; i++) 
			unlocked[i]= (byte) (arrayKey[i] ^ arrayLockedImg[i]);
		
		FileOutputStream encryptOutput = new FileOutputStream(fileName);
		encryptOutput.write(unlocked);
		encryptOutput.close();
		Files.delete(key.toPath());
	}

}
