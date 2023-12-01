/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;

/**
 * Tests of the evaluation of expression trees
 * 
 * @author Sara Sprenkle
 * 
 */
public class EvaluatorTests {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testConstantEvaluation() {
		ExpressionTreeNode e = new RGBColor(1, -1, 1);
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(1, -1, 1), e.evaluate(i, i));
		}
	}

	@Test
	public void testXEvaluation() {
		X x = new X();
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), x.evaluate(i, i));
		}
	}

	@Test
	public void testFloorEvaluation() {
		Floor myTree = new Floor(new X());

		// some straightforward tests
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.999, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-.7, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double floorOfTestVal = Math.floor(testVal);
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}

	@Test
	public void testCeilvaluation() {
		Ceil myTree = new Ceil(new X());
   
    // some straightforward tests
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(.999, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-.7, -1));
    
		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}
		
		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double ceilOfTestVal = Math.ceil(testVal);
			assertEquals(new RGBColor(ceilOfTestVal, ceilOfTestVal, ceilOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(ceilOfTestVal, ceilOfTestVal, ceilOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}

  @Test
	public void testCosEvaluation() {
		Cos myTree = new Cos(new X());
		
		//Some basic tests
		RGBColor black = new RGBColor(-1, -1, -1);
		RGBColor gray = new RGBColor(0, 0, 0);
		RGBColor white = new RGBColor(1, 1, 1);
		
		RGBColor[] colors = {black, gray, white};
		
		RGBColor cosBlack = myTree.evaluate(Math.PI, Math.PI);
		RGBColor cosGray = myTree.evaluate(Math.PI/2, Math.PI/2);
		RGBColor cosWhite = myTree.evaluate(0, 0);
		
		RGBColor[] cosCols = {cosBlack, cosGray, cosWhite};
		
		for (int i = 0; i < 3; i++) {
			assertEquals(colors[i].getRed(), cosCols[i].getRed(), 0.0001);
			assertEquals(colors[i].getGreen(), cosCols[i].getGreen(), 0.0001);
			assertEquals(colors[i].getBlue(), cosCols[i].getBlue(), 0.0001);
		}
  }
    
  @Test
	public void testClampEvaluation() {
		Clamp myTree = new Clamp(new X());
		// some straightforward tests
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1.4, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-1.999, -1));
		assertEquals(new RGBColor(-0.7, -0.7, -0.7), myTree.evaluate(-.7, -1));
		assertEquals(new RGBColor(0.7, 0.7, 0.7), myTree.evaluate(.7, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -1.7, -1, -.00001, 0, .000001, 1, 1.5 };

		for (double testVal : tests) {
			double clampOfTestVal = testVal;
			if (clampOfTestVal > 1) {
				clampOfTestVal = 1;
			} else if (clampOfTestVal < -1) {
				clampOfTestVal = -1;
			}
			assertEquals(new RGBColor(clampOfTestVal, clampOfTestVal, clampOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(clampOfTestVal, clampOfTestVal, clampOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}
	
	@Test
	public void testPlusEvaluation() {
		Plus myTree = new Plus(new X(), new Y());

		// some straightforward tests
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, 0));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(0, -1));
		assertEquals(new RGBColor(-0.7, -0.7, -0.7), myTree.evaluate(-0.6, -0.1));
		assertEquals(new RGBColor(0.7, 0.7, 0.7), myTree.evaluate(0.6, 0.1));
		assertEquals(new RGBColor(-0.5, -0.5, -0.5), myTree.evaluate(-0.6, 0.1));
		assertEquals(new RGBColor(0.5, 0.5, 0.5), myTree.evaluate(0.6, -0.1));
		
		// test the ints; 
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(2*i, 2*i, 2*i), myTree.evaluate(i, i));

		}

		double[] tests = { -1.7, -1, -.00001, 0, .000001, 1, 1.5 };
		
		for (double testVal : tests) {
			double plusOfTestVal = testVal;
			
			assertEquals(new RGBColor(plusOfTestVal, plusOfTestVal, plusOfTestVal), myTree.evaluate(testVal, 0));
			assertEquals(new RGBColor(2*plusOfTestVal, 2*plusOfTestVal, 2*plusOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
		
	}
  
	@Test
	public void testAbsEvaluation() {
		Abs myTree = new Abs(new X());

		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0.5)); 
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -0.2));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(-1, 0.3));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, -0.7));

		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.abs(i), Math.abs(i), Math.abs(i)), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(Math.abs(i), Math.abs(i), Math.abs(i)), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double absOfTestVal = Math.abs(testVal);
			assertEquals(new RGBColor(absOfTestVal, absOfTestVal, absOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(absOfTestVal, absOfTestVal, absOfTestVal), myTree.evaluate(testVal, testVal));
		}
	}

  
  @Test
	public void testSineEvaluation() {
	  Sine myTree = new Sine(new X());
		
		//Some basic tests
		RGBColor black = new RGBColor(-1, -1, -1);
		RGBColor gray = new RGBColor(0, 0, 0);
		RGBColor white = new RGBColor(1, 1, 1);
		
		RGBColor[] colors = {black, gray, white};
		
		RGBColor sinBlack = myTree.evaluate(Math.PI/-2, Math.PI/-2);
		RGBColor sinGray = myTree.evaluate(0, 0);
		RGBColor sinWhite = myTree.evaluate(Math.PI/2, Math.PI/2);
		
		RGBColor[] sinCols = {sinBlack, sinGray, sinWhite};
		
		for (int i = 0; i < 3; i++) {
			assertEquals(colors[i].getRed(), sinCols[i].getRed(), 0.0001);
			assertEquals(colors[i].getGreen(), sinCols[i].getGreen(), 0.0001);
			assertEquals(colors[i].getBlue(), sinCols[i].getBlue(), 0.0001);
		}
	}
}

