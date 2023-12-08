/**
 * 
 */
package picasso.parser.tokens;

/**
 * Represents an image (using Strings).
 * A ImageToken is immutable: once created it doesn't change.
 * 
 * @author Robert C. Duvall
 * @author Wonjun Jo
 */
public class ImageToken extends Token {

	private final String imageName;

	/**
	 * Constructs a token representing the value
	 * 
	 * @param imageName the name of the image
	 */
	public ImageToken(String imageName) {
		super("Image Token");
		boolean error = false;
		String errorMsg = "";
		

		if (error) {
			throw new IllegalArgumentException(errorMsg);
		}

		this.imageName = imageName;
	}
	
	/*
	public static boolean isValidValue(double value) {
		return (value >= -1 && value <= 1);
	}
	*/

	/**
	 * @return true iff o is a ImageToken with same imageName
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof ImageToken)) {
			return false;
		}
		ImageToken other = (ImageToken) o;
		return imageName == other.imageName;
	}

	public String toString() {
		return super.toString() + ": " + imageName;
	}

	/**
	 * @return the image name
	 */
	public String getImageName() {
		return imageName;
	}


	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public boolean isFunction() {
		return false;
	}

}
