package picasso.view.commands;

import javax.swing.JComponent;

import picasso.model.Pixmap;
import picasso.parser.language.expressions.RGBColor;
import picasso.util.Command;


/**
 * Animates an expression
 * 
 * @author Wonjun Jo
 */
public class Animator implements Command<Pixmap> {
	private static final int DELAY = 400; // in milliseconds

	private boolean isDone;
	private Evaluator evaluator;
	private String add = "+[0.1,0,-0.1]";
	private final String step = "+[0.1,0,-0.1]";
	
	/**
	 * Create a command that runs the given command and updates the given view
	 * over time.
	 */
	public Animator(Evaluator evaluator) {
		//myCommand = command;
		//myView = view;
		isDone = true;
		this.evaluator = evaluator;
	}

	/**
	 * Run the command on the target or cancel it if is already running.
	 */
	public void execute(Pixmap target) {
		for (int i = 0; i < 100; i++) {
			evaluator.execute(target, add);
			add = add + step;
			try {
				Thread.sleep(100);
			} catch (InterruptedException E) {
				E.printStackTrace();
			} 
		}
	}
}
