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
	private String add;
	private final String step = "+[-0.05,0.05,0.025]";
	
	/**
	 * Create a command that runs the given command and updates the given view
	 * over time.
	 */
	public Animator(Evaluator evaluator) {
		this.isDone = true;
		this.evaluator = evaluator;
	}

	/**
	 * Run the command on the target or cancel it if is already running.
	 */
	public void execute(Pixmap target) {
		add = "+[-0.05,0.05,0.025]";
		for (int i = 0; i < 50; i++) {
			evaluator.execute(target, add);
			add = add + step;
			/*try {
				Thread.sleep(50);
			} catch (InterruptedException E) {
				E.printStackTrace();
			} */
		}
		System.out.println("Done animating");
	}
}
