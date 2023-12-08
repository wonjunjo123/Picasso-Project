package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.ImageToken;

/**
 * Handles parsing the Image function.
 * 
 * @author Robert C. Duvall
 * @author Wonjun Jo
 * 
 */
public class ImageAnalyzer implements SemanticAnalyzerInterface {
	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		ImageToken imageToken = (ImageToken) tokens.pop(); // Remove the Image token
		// the parameters are the next tokens on the stack.
		// But, they need to be processed
		
		String filename = imageToken.getImageName();
		return new Image(filename);
	}

}
