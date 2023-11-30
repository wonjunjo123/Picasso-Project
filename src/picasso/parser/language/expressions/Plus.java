package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the plus function in the Picasso language.
 * 
 * @author Robert C. Duvall
 * @author Wonjun Jo
 * 
 */
public class Plus extends ExpressionTreeNode {
	
	ExpressionTreeNode param1;
	ExpressionTreeNode param2;

	/**
	 * Create a plus expression that takes as a parameter the given expression
	 * 
	 * @param param1 the first one to add
	 * @param param2 the second one to add
	 */
	public Plus(ExpressionTreeNode param1, ExpressionTreeNode param2) {
		this.param1 = param1;
		this.param2 = param2;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the floor of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the floor of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result1 = param1.evaluate(x, y);
		RGBColor result2 = param2.evaluate(x, y);
		
		double red = result1.getRed() + result2.getRed();
		double green = result1.getGreen() + result2.getGreen();
		double blue = result1.getBlue() + result2.getBlue();

		return new RGBColor(red, green, blue);
	}

}
