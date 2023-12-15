package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Constant;
import picasso.parser.tokens.NumberToken;
import picasso.parser.tokens.Token;
import picasso.view.ErrorHandling;

/**
 * Analyzer for a constant, must be between [-1, 1]
 * 
 * @author Sara Sprenkle
 * 
 */
public class ConstantAnalyzer implements SemanticAnalyzerInterface {

	private static ConstantAnalyzer singleton;

	public static ConstantAnalyzer getInstance() {
		if (singleton == null) {
			singleton = new ConstantAnalyzer();
		}
		return singleton;
	}

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		NumberToken token = (NumberToken) tokens.pop();

		// Check that the number is a valid number
		double value = token.value();

		// Would violate the preconditions of the constant
		if (value < -1 || value > 1) {
			String message = "Constant is out of range  [-1, 1]";
			ErrorHandling parseEx = new ErrorHandling(message);
			parseEx.showError();
			throw new ParseException(message);
		}

		return new Constant(value);
	}
}
