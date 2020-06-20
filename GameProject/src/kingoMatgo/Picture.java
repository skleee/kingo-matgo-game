package kingoMatgo;
/**
 * Enumeration class for Picture
 * @author SKLEE
 *
 */
public enum Picture {
	BRIGHT("Bright"),
	ANIMAL("Animal"),
	RIBBON("Ribbon"),
	JUNK1("Junk1"),
	JUNK2("Junk2"),
	DOUBLEJUNK("DoubleJunk");
	
	private final String pictureText;
	
	// Constructor
	private Picture(String pictureText) {
		this.pictureText = pictureText;
	}
	
	// Public method
	public String printPicture() {
		return pictureText;
	}
}
