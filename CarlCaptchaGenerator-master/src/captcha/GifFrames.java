package captcha;
import java.awt.image.BufferedImage;

public class GifFrames
{
   public static final String NONE                = "none";
   public static final String DO_NOT_DISPOSE      = "doNotDispose";
   public static final String RESTORE_TO_BGCOLOR  = "restoreToBackgroundColor";
   public static final String RESTORE_TO_PREVIOUS = "restoreToPrevious";

   public final BufferedImage img;
   public final long          delay; // in millis
   public final String        disposalMethod;

   public GifFrames(BufferedImage img, long delay)
   {
      this(img, delay, NONE);
   }

   public GifFrames(BufferedImage img, long delay, String disposalMethod)
   {
      this.img = img;
      this.delay = delay;
      this.disposalMethod = disposalMethod;
   }
}