package captcha;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CaptchaGen {
	
	
	private static List<String> allerror = new ArrayList<String>();

	public static void main(String[] args) {
		
		//xI2gmPpMdTECL7anH0wQhuVB5vfZ36soYqy4ez9AUiXkrWRJF8tlDjOSbK1c
		
		char[] set1 = "xI2gmPpMdT".toCharArray();
		char[] set2 = "ECL7anH0wQ".toCharArray();
		char[] set3 = "huVB5vfZ36".toCharArray();
		char[] set4 = "soYqy4ez9A".toCharArray();
		char[] set5 = "UiXkrWRJF8".toCharArray();
		//char[] set6 = "tlDjOSbK1c".toCharArray();
		
		
		ExecutorService pool = Executors.newFixedThreadPool(100);
		
		
		
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					for (int l = 0; l < 10; l++) {
						for (int m = 0; m < 10; m++) {
							
							//First run this code 1 and then the code 2
							
							
							/* Code 1 to create JPG in cap1 and cap2 folder
							String captchatext = String.valueOf(""+set1[i]+set2[j]+set3[k]+set4[l]+set5[m]);
							String filename = String.valueOf(""+i+j+k+l+m);
							//System.out.println(captchatext);
							pool.submit(new CaptchaTask(captchatext, filename));
							pool.submit(new CaptchaTask2(captchatext, filename));
							*/
							
							//Code 2 to combine JPG in cap1 folder and cap2 folder in one GIF
							String filename = String.valueOf(""+i+j+k+l+m);
							pool.submit(new CombineJPG(filename));
							
							
						}
					}
				}
			}
		}
		//pool.submit(new DownloadTask(second, file));
		
		
		System.out.println("Shuting down...");
		pool.shutdown();
		try {
			System.out.println("awaiting termination");
		    if (!pool.awaitTermination(1, TimeUnit.DAYS)) {
		    	pool.shutdownNow();
		    } 
		} catch (InterruptedException e) {
			pool.shutdownNow();
		}
		
		System.out.println("Writing error");
		StringBuilder ebuilder = new StringBuilder();
		
		for (String errString : allerror) {
			ebuilder.append(errString + "\n");
		}
		
		ebuilder.append("End of Line");
		
		try {
			Files.write(Paths.get("errorDownload.txt"), ebuilder.toString().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("DONE");
		

	}
	
	public static Void addError(String string) {
		
		allerror.add(string);
		
		
		
		return null;
	}
	
	
}
