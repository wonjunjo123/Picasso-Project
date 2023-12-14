package picasso.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
//import picasso.parser.language.expressions.X;
//import picasso.parser.language.expressions.Y;
import picasso.parser.tokens.*;
import picasso.parser.tokens.functions.*;
import picasso.parser.tokens.operations.*;

import javax.swing.*;
import java.util.Random;

/**
 * Handle an word token 
 * 
 * @author Nick Lagges
 *
 */
public class WordAnalyzer implements SemanticAnalyzerInterface {

	static Map<String, ExpressionTreeNode> wordToExpression = new HashMap<String, ExpressionTreeNode>();
	
	final JFrame parent = new JFrame();
	static {
		// We always have x and y defined.
		wordToExpression.put("x", new X());
		wordToExpression.put("y", new Y());
	}
	
	/**
	 * creates an expression tree node from a stack of tokens. Will prompt the user
	 * to enter a different input if there are any parsing errors
	 * 		will throw a ParseException if unrecognized identifier analyzer
	 * 
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		Token token = tokens.pop();
		WordToken t = (WordToken) token;
		String id = t.getName();
		//System.out.println(id);
		ExpressionTreeNode mapped = wordToExpression.get(id);
		System.out.println(id);
		if (mapped != null) {
			return mapped;
		}
		//System.out.println("test");
		String[] vars = {"x", "y"};
		FunctionToken[] exprTokens = {new AbsToken(), new AtanToken(), new CeilToken(), 
									 new ClampToken(), new CosToken(), new FloorToken(), 
									 new SinToken(), new TanToken()};
		if (mapped == null) {
			Random random = new Random();
			int i = 0;
			int randnum;
			while (i < id.length()) {
				tokens.push(new IdentifierToken(vars[i%2]));
				if (i >0) {
					randnum = random.nextInt(exprTokens.length);
					tokens.push(new PlusToken());
					tokens.push(exprTokens[randnum]);
				}
				i++;
			}
			System.out.println(tokens);
			ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
			Cos cosETN = new Cos(paramETN);
			return cosETN;
			/**if (id.length() == 3) {
				tokens.push(new IdentifierToken("x"));
				ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
				Cos cosETN = new Cos( new Floor(paramETN));
				return cosETN;
			}*/
			//old code
			/**String word = token.toString();
			word = word.substring(word.indexOf(": ")+2, word.length());
			//System.out.println(word);
			char[] letters = new char[word.length()];
			int i = 0;
			while (i < word.length()) {
				char a = word.charAt(i);
				letters[i] = a;
				i++;
				//System.out.println(i);
			}
			System.out.println(Character.getNumericValue(letters[0]));
			System.out.println(Character.getNumericValue(letters[1]));
			for (char c : letters) {
				if ( ( Character.getNumericValue(c) > 9 &&  Character.getNumericValue(c) < 36)) {
					id = "x";
				}
			}
			mapped = idToExpression.get(id);
			return mapped;*/
			//String message = "Unrecognized Identifier Variable: " + id;
			//throw new ParseException(message);
		}

		// TODO : What should we do if we don't recognize the identifier?
		// Is that an error? Or, could there a valid reason?
		return null;
	}
	
	public static void storeAssignmentResult(String variable, ExpressionTreeNode result) {
		wordToExpression.put(variable, result); 
	}

}
