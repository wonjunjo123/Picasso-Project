package picasso.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.*;

import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.util.ThreadedCommand;
import picasso.view.commands.*;

/**
 * Main container for the Picasso application
 *
 * @author Robert Duvall (rcd@cs.duke.edu)
 * 
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	private static JTextField expressionTextField;
	private JButton validateButton;
	public Frame(Dimension size) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// create GUI components
		Canvas canvas = new Canvas(this);
		canvas.setSize(size);
		
		
		// add commands to test here
		
		ButtonPanel commands = new ButtonPanel(canvas);
		expressionTextField = new JTextField(40);
		
		ExpressionTreeGenerator expGen = new ExpressionTreeGenerator();
		
		commands.add("Open", new Reader());
		commands.add("Evaluate", new ThreadedCommand<Pixmap>(canvas, new Evaluator()));
		commands.add("Save", new Writer());
		commands.add(expressionTextField);
		expressionTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThreadedCommand help = new ThreadedCommand<Pixmap>(canvas, new Evaluator());
				help.execute(e);
				System.out.println(expressionTextField.getText());
		    }
		});	
	
		// add our container to Frame and show it
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(commands, BorderLayout.NORTH);
		pack();
	}
	
	public static String getExpressionText() {
		return expressionTextField.getText();
	}
  
}

