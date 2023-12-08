/**
 * 
 */
package picasso.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 */
public class ErrorHandling {
	
	private String message;
	static JFrame errorFrame;
	static JLabel errorLabel;
	static JLabel errorLabel2;

	/**
	 * Creates a pop up window to help the user understand the error
	 */
	public ErrorHandling(String message) {
		this.message = message;
	}
	
	public void showError() {
		errorFrame = new JFrame(message);
		errorLabel = new JLabel();
		errorLabel2 = new JLabel();
		errorLabel.setHorizontalAlignment(JLabel.CENTER);
		errorLabel.setVerticalAlignment(JLabel.TOP);
		errorLabel2.setHorizontalAlignment(JLabel.CENTER);
		errorLabel2.setVerticalAlignment(JLabel.BOTTOM);
		errorLabel.setSize(500, 75);
		errorLabel2.setSize(500, 75);
		errorLabel.setText(message);
		errorLabel2.setText("Please try another input");
        errorFrame.add(errorLabel);
        errorFrame.add(errorLabel2);
        errorFrame.setSize(500, 150);  
        errorFrame.setLayout(null);  
        errorFrame.setVisible(true);
	}

}