package captcha;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class CombineJPG implements Runnable{
	
	private String imagename;
	
	public CombineJPG(String imagename) {
		this.imagename = imagename;
	}

	@Override
	public void run() {
		
		try(OutputStream outputStream = new FileOutputStream(new File("finalcap/"+imagename+".gif"))) {
		List<GifFrames> gifFrames = new ArrayList<GifFrames>();
		
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		
		images.add(ImageIO.read( new File("cap1/"+imagename+".jpg")));
		images.add(ImageIO.read( new File("cap2/"+imagename+".jpg")));

		for(BufferedImage image: images)
		{
		      int transparantColor = 0xFF00FF; // purple
		      BufferedImage gif = ImageUtilS.convertRGBAToGIF(image, transparantColor);
		      long delay = 1000; // every frame takes 100ms
		      String disposal = GifFrames.RESTORE_TO_BGCOLOR; // make transparent pixels not 'shine through'
		      gifFrames.add(new GifFrames(gif, delay, disposal));
		}

		   int loopCount = 0; // loop indefinitely
		
		ImageUtilS.saveAnimatedGIF(outputStream , gifFrames, loopCount);
		System.out.println(imagename);
		}catch (Exception e) {
			e.printStackTrace();
			CaptchaGen.addError(imagename+"\n");
		}
		
	}

}
