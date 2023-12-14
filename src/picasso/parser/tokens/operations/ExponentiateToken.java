package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the multiply sign token
 * 
 */
public class ExponentiateToken extends CharToken implements OperationInterface {
	public ExponentiateToken() {
		super(CharConstants.CARET);
	}
}