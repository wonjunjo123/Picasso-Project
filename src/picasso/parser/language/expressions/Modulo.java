package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the modulo operator in the Picasso language.
 * 
 * @author Zach Moore
 * 
 */
public class Modulo extends ExpressionTreeNode {
	
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	/**
	 * Create a modulo expression
	 * 
	 * @param left the numerator
	 * @param right the divisor
	 */
	public Modulo(ExpressionTreeNode left, ExpressionTreeNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the modulo of
	 * the operator's two arguments.
	 * 
	 * @return the color from evaluating the modulo of the two arguments
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result1 = left.evaluate(x, y);
		RGBColor result2 = right.evaluate(x, y);
		
		double red = result1.getRed() % result2.getRed();
		double green = result1.getGreen() % result2.getGreen();
		double blue = result1.getBlue() % result2.getBlue();
		
		if (result2.getRed() == 0) { // Handles divide by zero concerns by returning unchanged result1 value
			red = result1.getRed();
		}
		
		
		if (result2.getGreen() == 0) {
			green = result1.getGreen();
		}
	
		
		if (result2.getBlue() == 0) {
			blue = result1.getBlue();
		}
		
		return new RGBColor(red, green, blue);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Modulo)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		Modulo div = (Modulo) o;

		// check if their parameters are equal
		if (this.left.equals(div.left) && this.right.equals(div.right)) {
			return true;
		} else {
			return false;
		}
		
	}

}