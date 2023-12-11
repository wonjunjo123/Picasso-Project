package picasso.parser.language.expressions;

import java.io.File;
import java.io.IOException;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

import javax.imageio.ImageIO;

import picasso.parser.language.ExpressionTreeNode;
import picasso.model.Pixmap;


/**
 * Represents the Image object in the Picasso language.
 * 
 * @author Wonjun Jo
 * 
 */
public class Image extends ExpressionTreeNode {
	
	// In a sense, Image is a type of Pixmap
	String filename;
	BufferedImage myImage;
	Dimension mySize;
	File directory;
	
	/**
	 * Create a image ETN that takes as a parameter the filename
	 * 
	 * @param filename the name of the file
	 */
	public Image(String filename) {
		this.filename = filename;
		this.directory = new File("images/"); 
		// One of the method overloads for File is taking a parent file object as arg1 and String of child file as arg2
		
		try {
			this.myImage = ImageIO.read(new File(directory, filename));
			this.mySize = new Dimension(myImage.getWidth(), myImage.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Determine if the given (x,y) coordinate is within the bounds of this image.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if the given (x,y) coordinate is within the bounds of this
	 *         image.
	 */
	public boolean isInBounds(int x, int y) {
		return (0 <= x && x < mySize.width) && (0 <= y && y < mySize.height);
	}
	
	/**
	 * Returns the color of the pixel at the given (x,y) coordinate if the
	 * coordinate is within the bounds of the image; otherwise returns the default
	 * color
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the color of the pixel at the given (x,y) coordinate if the
	 *         coordinate is within the bounds of the image; otherwise returns the
	 *         default color
	 */
	public Color getColor(int x, int y) {
		if (isInBounds(x, y))
			return new Color(myImage.getRGB(x, y));
		else
			return Color.BLACK;
	}
	
	/**
	 * Dummy method, since .evaluate of Image doesn't matter
	 * 
	 * @return the color [0,0,0] as dummy placeholder
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		// make getColor
		return new RGBColor(0, 0, 1);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Image)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		Image img = (Image) o;

		// check if their parameters are equal
		if (this.filename.equals(img.filename)) {
			return true;
		} else {
			return false;
		}
		
		//return true;
	}
	
	

}
