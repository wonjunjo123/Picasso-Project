package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Cos evaluator class
 * @author Nick Lagges
 */
public class Cos extends UnaryFunction {

	/**
	 * @param param
	 */
	public Cos(ExpressionTreeNode param) {
		super(param);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 * @return cosColor
	 * @see picasso.parser.language.ExpressionTreeNode#evaluate(double, double)
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		//changes x and y input into radians for cos evaluation
		x = Math.toRadians(x);
		y = Math.toRadians(y);
		double rx = Math.cos(x);
		double gx = Math.cos(x);
		double bx = Math.cos(x);	
		double ry = Math.cos(y);
		double gy = Math.cos(y);
		double by = Math.cos(y);
		//creation of RGB color
		double r;
		double g;
		double b;
		//clamps values between -1.0 and 1.0, inclusive
		r = Math.max(-1.0, Math.min(1.0, rx+ry));
		g = Math.max(-1.0, Math.min(1.0, gx+gy));
		b = Math.max(-1.0, Math.min(1.0, bx+by));
		if ( ( r < 0.0001) && ( r > -0.0001) ) {
			r = 0.0;
		}
		if ( ( g < 0.0001) && ( g > -0.0001) ) {
			g = 0.0;
		}
		if ( ( b < 0.0001) && ( b > -0.0001) ) {
			b = 0.0;
		}
		RGBColor cosColor = new RGBColor(r, g, b);
		return cosColor;
	}

}
