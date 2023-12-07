package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the Image object in the Picasso language.
 * 
 * @author Wonjun Jo
 * 
 */
public class Image extends ExpressionTreeNode {
	
	// String filename;
	ExpressionTreeNode filename;
	ExpressionTreeNode xExpr;
	ExpressionTreeNode yExpr;
	

	/**
	 * Create a imageWrap expression that takes as a parameter the filename as well as the x and y expressions
	 * 
	 * @param left the first one to add
	 * @param right the second one to add
	 */
	public Image(ExpressionTreeNode filename) {
		this.filename = filename;

	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the addition of
	 * the operator's two arguments.
	 * 
	 * @return the color from evaluating the addition of the two arguments
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result1 = xExpr.evaluate(x, y);
		RGBColor result2 = yExpr.evaluate(x, y);
		
		double red = result1.getRed() + result2.getRed();
		double green = result1.getGreen() + result2.getGreen();
		double blue = result1.getBlue() + result2.getBlue();

		return new RGBColor(red, green, blue);
	}
	
	/*@Override
	 
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Addition)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		Addition ad = (Addition) o;

		// check if their parameters are equal
		if (this.left.equals(ad.left) && this.right.equals(ad.right)) {
			return true;
		} else {
			return false;
		}
		
		//return true;
	}
	*/
	

}
