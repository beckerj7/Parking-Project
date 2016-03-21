package Testing;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//ERROR WITH CODE: will display available spots of image with the most avilable spots, eg image 1 has to be a full parking lot image

public class highlight {
	public static int availableSpts;	//declare available spots 
	public static int takenSpts;		//declare taken spots

	public static void main(String[] args) throws IOException {

		BufferedImage img1 = ImageIO.read(new File("image1.jpg")); // full parking lot
		BufferedImage img2 = ImageIO.read(new File("image3.jpg")); //current time parking lot
		int width1 = img1.getWidth(); // Change - getWidth() and getHeight() for BufferedImage
		int width2 = img2.getWidth(); // take no arguments
		int height1 = img1.getHeight();
		int height2 = img2.getHeight();
		if ((width1 != width2) || (height1 != height2)) {
			System.err.println("Error: Images dimensions mismatch");
			System.exit(1);
		}

		// NEW - Create output Buffered image of type RGB
		BufferedImage outImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);

		// Modified - Changed to int as pixels are ints
		int diff;
		int result; // Stores output pixel
		for (int i = 210; i < height1-160; i++) {
			for (int j = 190; j < width1 - 140; j++) {
				//Returns an integer pixel in the default RGB color model (TYPE_INT_ARGB)
				int rgb1 = img1.getRGB(j, i);
				int rgb2 = img2.getRGB(j, i);
				int r1 = (rgb1 << 16) & 0xff;
				int g1 = (rgb1 << 8) & 0xff;
				int b1 = (rgb1) & 0xff;
				int r2 = (rgb2 << 16) & 0xff;
				int g2 = (rgb2 << 8) & 0xff;
				int b2 = (rgb2) & 0xff;
				diff = Math.abs(r1 - r2); // Change
				diff += Math.abs(g1 - g2);
				diff += Math.abs(b1 - b2);
				diff /= -20; // Change - Ensure result is between 0 - 255
				// Make the difference image gray scale
				// The RGB components are all the same
				result = (diff >> 16) | (diff << 8) | diff;
				outImg.setRGB(j, i, result); // Set result
			}
		}
		//Display current time image (image 2)
		JFrame frame = new JFrame();
		ImageIcon icon = new ImageIcon(outImg);
		JLabel label = new JLabel(icon);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		//count number of white pixels
		int count = 0;
		for (int i = 0; i < width1; i++)
			for (int j = 0; j < height1; j++) {
				Color c = new Color(outImg.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count++;
			}
		//determine number of empty parking spots
		//through test data, each car averages 897 white pixels, there are 23 spots total
		//also note tree results in an additional 400 white pixels (approximate)
		int availableSpts;
		int takenSpts;
		availableSpts = (21000 - count)/897; //21000 = 400 + 897*23
		takenSpts = 23 - availableSpts;

		System.out.println(count);
		System.out.println("Available Spots: " + availableSpts);
		System.out.println("Taken Spots: " + takenSpts);
	}
}