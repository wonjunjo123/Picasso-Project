package picasso.parser.language;

import picasso.parser.language.expressions.RGBColor;

import java.lang.Math;

/**
 * expression tree for cos expression
 * @author Nick Lagges
 */
public class Cos extends ExpressionTreeNode {

	/**
	 * 
	 */
	public Cos() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 * @return RGB Color based on cos expression
	 * @see picasso.parser.language.ExpressionTreeNode#evaluate(double, double)
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		//variables for cos evaluation
		double rx = Math.cos(x);
		double gx = Math.cos(x);
		double bx = Math.cos(x);	
		double ry = Math.cos(y);
		double gy = Math.cos(y);
		double by = Math.cos(y);
		//creation of RGB color
		RGBColor cosColor = new RGBColor(rx+ry, gx+gy, bx+by);
		return cosColor;
	}

}
