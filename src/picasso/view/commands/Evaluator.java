package picasso.view.commands;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.util.Command;
import picasso.view.Frame;

/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 * @author Sara Sprenkle
 */
public class Evaluator implements Command<Pixmap> {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	public String s;

	/**
	 * Evaluate an expression for each point in the image.
	 */
	public void execute(Pixmap target) {
		// create the expression to evaluate just once
		//execute(target, "");
		String text= Frame.getExpressionText();
		ExpressionTreeNode expr = createExpression(text);
		// evaluate it for each pixel
		if (expr != null) {
			Dimension size = target.getSize();
			for (int imageY = 0; imageY < size.height; imageY++) {
				double evalY = imageToDomainScale(imageY, size.height);
				for (int imageX = 0; imageX < size.width; imageX++) {
					double evalX = imageToDomainScale(imageX, size.width);
					Color pixelColor = expr.evaluate(evalX, evalY).toJavaColor();
					target.setColor(imageX, imageY, pixelColor);
				}
			}
		}
	}
	
	public void execute(Pixmap target, String step) {
		// create the expression to evaluate just once
		String text = Frame.getExpressionText() + step;
		ExpressionTreeNode expr = createExpression(text);
		// evaluate it for each pixel
		if (expr != null) {
			Dimension size = target.getSize();
			for (int imageY = 0; imageY < size.height; imageY++) {
				double evalY = imageToDomainScale(imageY, size.height);
				for (int imageX = 0; imageX < size.width; imageX++) {
					double evalX = imageToDomainScale(imageX, size.width);
					Color pixelColor = expr.evaluate(evalX, evalY).toJavaColor();
					target.setColor(imageX, imageY, pixelColor);
				}
			}
		}
	}

	/**
	 * Convert from image space to domain space.
	 */
	protected double imageToDomainScale(int value, int bounds) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) value / bounds) * range + DOMAIN_MIN;
	}

	/**
	 * 
	 * A place holder for a more interesting way to build the expression.
	 */
	private ExpressionTreeNode createExpression(String text) {
		// Note, when you're testing, you can use the ExpressionTreeGenerator to
		// generate expression trees from strings, or you can create expression
		// objects directly (as in the commented statement below).

		
		
		
		ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
		
		return expTreeGen.makeExpression(text);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}