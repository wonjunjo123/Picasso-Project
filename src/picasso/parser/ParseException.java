package picasso.parser;

import picasso.view.ErrorHandling;

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
		String issue = "ParseException: " + message;
		ErrorHandling parseEx = new ErrorHandling(issue);
		parseEx.showError();
	}

}
