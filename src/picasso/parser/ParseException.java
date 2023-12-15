package picasso.parser;

import picasso.view.ErrorHandling;
import picasso.view.Frame;

/**
 * Describe an exception that occurred during parsing.
 * 
 * @author Sara Sprenkle
 *
 */
@SuppressWarnings("serial")
public class ParseException extends RuntimeException {

	public ParseException(String message) {
		super("ParseException: " + message);
	}

}
