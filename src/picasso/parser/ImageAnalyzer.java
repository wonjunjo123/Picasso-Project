package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the Image function.
 * 
 * @author Robert C. Duvall
 * @author Wonjun Jo
 * 
 */
public class ImageAnalyzer implements SemanticAnalyzerInterface {
	
	// NOT THE CODE
	
	
	
	
	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the Image token
		// the parameters are the next tokens on the stack.
		// But, they need to be processed
		
		ExpressionTreeNode yETN = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode xETN = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		Image imageETN = (Image) SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		return new ImageWrap(imageETN, xETN, yETN);
	}
	

}
