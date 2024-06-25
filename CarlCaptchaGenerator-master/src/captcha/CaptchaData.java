package captcha;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CaptchaData {
	
	
	private static final String[] fontnamelist = {"Arial","Arial Black","Arial Narrow","Bahnschrift","Book Antiqua","Bookman Old Style","Calibri","Calibri Light","Cambria","Cambria Math","Candara","Candara Light","Century","Comic Sans MS","Consolas","Constantia","Corbel","Corbel Light","Courier New","Dialog","DialogInput","Ebrima","Franklin Gothic Medium","Gabriola","Gadugi","Garamond","Georgia","Impact","Javanese Text","Leelawadee","Leelawadee UI","Leelawadee UI Semilight","Lucida Bright","Lucida Console","Lucida Sans","Lucida Sans Typewriter","Lucida Sans Unicode","Malgun Gothic","Malgun Gothic Semilight","Microsoft Himalaya","Microsoft JhengHei","Microsoft JhengHei Light","Microsoft JhengHei UI","Microsoft JhengHei UI Light","Microsoft New Tai Lue","Microsoft PhagsPa","Microsoft Sans Serif","Microsoft Tai Le","Microsoft Uighur","Microsoft YaHei","Microsoft YaHei Light","Microsoft YaHei UI","Microsoft YaHei UI Light","Microsoft Yi Baiti","MingLiU-ExtB","MingLiU_HKSCS-ExtB","Mongolian Baiti","Monospaced","Monotype Corsiva","MS Gothic","MS PGothic","MS Reference Sans Serif","MS UI Gothic","MV Boli","Myanmar Text","Nirmala UI","Nirmala UI Semilight","NSimSun","Palatino Linotype","PMingLiU-ExtB","SansSerif","Segoe Print","Segoe Script","Segoe UI","Segoe UI Black","Segoe UI Emoji","Segoe UI Historic","Segoe UI Light","Segoe UI Semibold","Segoe UI Semilight","Segoe UI Symbol","Serif","SimSun","SimSun-ExtB","Sitka Banner","Sitka Display","Sitka Heading","Sitka Small","Sitka Subheading","Sitka Text","Sylfaen","Tahoma","Times New Roman","Trebuchet MS","Verdana","Yu Gothic","Yu Gothic Light","Yu Gothic Medium","Yu Gothic UI","Yu Gothic UI Light","Yu Gothic UI Semibold","Yu Gothic UI Semilight"};
	
	private static Color[] colorList = {Color.black, Color.blue, Color.DARK_GRAY, Color.green,  Color.MAGENTA, Color.RED, new Color(92, 95, 165),new Color(202, 54, 54),new Color(85, 0, 85),new Color(0, 85, 0)};
	
	
	
	
	private static String getRandomFontName() {
		int ran = new Random().nextInt(fontnamelist.length-1);
		return fontnamelist[ran];
	}
	
	public static Font getRandomFont() {
		
		int size = ThreadLocalRandom.current().nextInt(25, 40);
		
		Font font = new Font(getRandomFontName(),  Font.BOLD, 40);
		
		return font;
	}
	
	public static Color getRandomColor() {
		return colorList[new Random().nextInt(colorList.length-1)];
	}
	

}
