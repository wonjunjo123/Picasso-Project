package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the floor function in the Picasso language.
 * 
 * @author Robert C. Duvall
 * @author Wonjun Jo
 * 
 */
public class Clamp extends UnaryFunction {

	/**
	 * Create a clamp expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to floor
	 */
	public Clamp(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the clamp of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the floor of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.floor(result.getRed());
		double green = Math.floor(result.getGreen());
		double blue = Math.floor(result.getBlue());

		return new RGBColor(red, green, blue);
	}

}
