package captcha;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class CaptchaTask implements Runnable{
	
	private String captchatext;
	private String filename;
	public CaptchaTask(String captchatext, String filename) {
		super();
		this.captchatext = captchatext;
		this.filename = filename;
	}
	@Override
	public void run() {
		
		
		File outputfile = new File("cap1/"+filename+".jpg");
		
		BufferedImage image = getCaptchaImage();
		
		if (image!=null) {
			try {
				ImageIO.write(getCaptchaImage(), "jpg", outputfile);
				System.out.println(filename);
			} catch (IOException e) {
				CaptchaGen.addError(captchatext + " - " + filename);
				e.printStackTrace();
			}
			
		}
    	
		
		
	}
	
	private BufferedImage getCaptchaImage() {
        try {
            Color backgroundColor = Color.white;
            Color borderColor = Color.white;
            Color textColor = Color.black;
            Color circleColor = new Color(190, 160, 150);
            
           
            
            Font textFont = new Font("Verdana", Font.BOLD,40);
            int charsToPrint = 5;
            int width = 180;
            int height = 90;
            int circlesToDraw = 20;
            float horizMargin = 20.0f;
            double rotationRange = 0.7; 
            BufferedImage bufferedImage = ImageIO.read( new File("cap1.jpg"));//new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
            g.setColor(backgroundColor);
            g.fillRect(0, 0, width, height);

            // lets make some noisey circles
            g.setColor(circleColor);
            for (int i = 0; i < circlesToDraw; i++) {
                int L = (int) (Math.random() * height / 2.0);
                int X = (int) (Math.random() * width - L);
                int Y = (int) (Math.random() * height - L);
                g.draw3DRect(X, Y, L * 2, L * 2, true);
            }
            
            g.setColor(textColor);
            g.setFont(textFont);
            
            FontMetrics fontMetrics = g.getFontMetrics();
            int maxAdvance = fontMetrics.getMaxAdvance();
            int fontHeight = fontMetrics.getHeight();

            // i removed 1 and l and i because there are confusing to users...
            // Z, z, and N also get confusing when rotated
            // this should ideally be done for every language...
            // 0, O and o removed because there are confusing to users...
            // i like controlling the characters though because it helps prevent confusion
            
            char[] chars = captchatext.toCharArray();
            float spaceForLetters = -horizMargin * 2 + width;
            float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);
            StringBuffer finalString = new StringBuffer();
            for (int i = 0; i < charsToPrint; i++) {
                
                char characterToShow = chars[i];
                finalString.append(characterToShow);

                // this is a separate canvas used for the character so that
                // we can rotate it independently
                int charWidth = fontMetrics.charWidth(characterToShow);
                int charDim = Math.max(maxAdvance, fontHeight);
                int halfCharDim = (int) (charDim / 2);
                BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
                Graphics2D charGraphics = charImage.createGraphics();
                charGraphics.translate(halfCharDim, halfCharDim);
                double angle = (Math.random() - 0.5) * rotationRange;
                charGraphics.transform(AffineTransform.getRotateInstance(angle));
                charGraphics.translate(-halfCharDim, -halfCharDim);
                
				charGraphics.setColor(CaptchaData.getRandomColor());
				
                
                charGraphics.setFont(CaptchaData.getRandomFont());
                
                int charX = (int) (0.5 * charDim - 0.5 * charWidth);
                charGraphics.drawString("" + characterToShow, charX, (int) ((charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent()));
                float x = horizMargin + spacePerChar * (i) - charDim / 1.9f;
                int y = (int) ((height - charDim) / 2);
                g.drawImage(charImage, (int) x, y, charDim, charDim, null, null);
                charGraphics.dispose();
            }
            g.setColor(borderColor);
            g.drawRect(0, 0, width - 1, height - 1);
            g.dispose();
            
            return bufferedImage;
        } catch (Exception ioe) {
            CaptchaGen.addError(captchatext + " - " + filename);
            ioe.printStackTrace();
        }
        
        return null;
	
	}

}
