package Sandbox;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//Analyzes open spots in image 2 using image 1 as reference

//ERROR WITH CODE: will display available spots of image with the most available spots, EX image 1 has to be a full parking lot image

public class CompareTest {

	public static int compare(String image, String empty, boolean display) throws IOException {

		BufferedImage img1 = ImageIO.read(new File(empty)); // empty parking lot
		BufferedImage img2 = ImageIO.read(new File(image)); //current time parking lot
		int width1 = img1.getWidth(); // Change - getWidth() and getHeight() for BufferedImage
		int width2 = img2.getWidth(); // take no arguments
		int height1 = img1.getHeight();
		int height2 = img2.getHeight();
		if ((width1 != width2) || (height1 != height2)) {
			System.err.println("Error: Images dimensions mismatch");
			System.exit(1);
		}

		////

		//Output image, default RGB color model (TYPE_INT_ARGB)
		BufferedImage outImg1 = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);

		// Covert to gray scale
		int diff;
		int result; //output pixel
		for (int i = 0; i < height1; i++) {
			for (int j = 0; j < width1; j++) {

				int rgb1 = img1.getRGB(j, i);
				int rgb2 = img2.getRGB(j, i);
				int r1 = (rgb1 << 16) & 0xff;
				int g1 = (rgb1 << 8) & 0xff;
				int b1 = (rgb1) & 0xff;
				int r2 = (rgb2 << 16) & 0xff;
				int g2 = (rgb2 << 8) & 0xff;
				int b2 = (rgb2) & 0xff;
				diff = Math.abs(r1 - r2); 
				diff += Math.abs(g1 - g2);
				diff += Math.abs(b1 - b2);
				diff /= -20;
				// Make the difference image gray scale
				// the RGB components are all the same
				result = (diff >> 16) | (diff << 8) | diff;
				outImg1.setRGB(j, i, result); // Set result
			}
		}
		//each spot on average 1600 pixels
		//count number of white pixels for parking spot1
		int count1 = 0;
		for (int i = 190; i < width1 -570; i++)
			for (int j = 210; j < height1-230; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count1++;
			}
		//count number of white pixels for parking spot2
		int count2 = 0;
		for (int i = 230; i < width1 -535; i++)
			for (int j = 210; j < height1-235; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count2++;
			}

		//count number of white pixels for parking spot3
		int count3 = 0;
		for (int i = 265; i < width1 -495; i++)
			for (int j = 210; j < height1-235; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count3++;
			}
		//count number of white pixels for parking spot4
		int count4 = 0;
		for (int i = 305; i < width1 -460; i++)
			for (int j = 210; j < height1-235; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count4++;
			}

		//count number of white pixels for parking spot6
		int count6 = 0;
		for (int i = 380; i < width1 -380; i++)
			for (int j = 220; j < height1-235; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count6++;
			}
		//count number of white pixels for parking spot7
		int count7 = 0;
		for (int i = 415; i < width1 -345; i++)
			for (int j = 230; j < height1-230; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count7++;
			}
		//count number of white pixels for parking spot8
		int count8 = 0;
		for (int i = 455; i < width1 -315; i++)
			for (int j = 230; j < height1-230; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count8++;
			}
		//count number of white pixels for parking spot9
		int count9 = 0;
		for (int i = 490; i < width1 -270; i++)
			for (int j = 230; j < height1-230; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count9++;
			}
		//count number of white pixels for parking spot10
		int count10 = 0;
		for (int i = 525; i < width1 -240; i++)
			for (int j = 230; j < height1-235; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count10++;
			}
		//count number of white pixels for parking spot11
		int count11 = 0;
		for (int i = 555; i < width1 -225; i++)
			for (int j = 240; j < height1-225; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count11++;
			}
		//count number of white pixels for parking spot12
		int count12 = 0;
		for (int i = 190; i < width1 -575; i++)
			for (int j = 270; j < height1-175; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count12++;
			}
		//count number of white pixels for parking spot13
		int count13 = 0;
		for (int i = 240; i < width1 -530; i++)
			for (int j = 270; j < height1-175; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count13++;
			}
		//count number of white pixels for parking spot14
		int count14 = 0;
		for (int i = 285; i < width1 -480; i++)
			for (int j = 270; j < height1-170; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count14++;
			}
		//count number of white pixels for parking spot15
		int count15 = 0;
		for (int i = 325; i < width1 -435; i++)
			for (int j = 270; j < height1-170; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count15++;
			}
		//count number of white pixels for parking spot16
		int count16 = 0;
		for (int i = 370; i < width1 -390; i++)
			for (int j = 270; j < height1-170; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count16++;
			}

		//count number of white pixels for parking spot17
		int count17 = 0;
		for (int i = 410; i < width1 -350; i++)
			for (int j = 270; j < height1-175; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count17++;
			}

		//count number of white pixels for parking spot18
		int count18 = 0;
		for (int i = 450; i < width1 -310; i++)
			for (int j = 280; j < height1-175; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count18++;
			}


		//count number of white pixels for parking spot19
		int count19 = 0;
		for (int i = 485; i < width1 -275; i++)
			for (int j = 285; j < height1-170; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count19++;
			}
		//count number of white pixels for parking spot20
		int count20 = 0;
		for (int i = 520; i < width1 -240; i++)
			for (int j = 275; j < height1-170; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count20++;
			}
		//count number of white pixels for parking spot21
		int count21 = 0;
		for (int i = 550; i < width1 -210; i++)
			for (int j = 275; j < height1-170; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count21++;
			}
		//count number of white pixels for parking spot22
		int count22 = 0;
		for (int i = 585; i < width1 -170; i++)
			for (int j = 275; j < height1-175; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count22++;
			}
		//count number of white pixels for parking spot23
		int count23 = 0;
		for (int i = 615; i < width1 -150; i++)
			for (int j = 280; j < height1-175; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count23++;
			}
		//count number of white pixels for parking spot24 //Error due to tree at this spot
		int count24 = 0;
		for (int i = 650; i < width1 -115; i++)
			for (int j = 285; j < height1-170; j++) {
				Color c = new Color(outImg1.getRGB(i, j));
				if (c.equals(Color.WHITE))
					count24++;
			}

		//determine number of empty parking spots
		//through test data, each car averages 897 white pixels, there are 23 spots total
		//also note tree results in an additional 400 white pixels (approximate)
		int takenSpts = 0;
		int availableSpts;
		if (count1 > 500)
			takenSpts++;
		if (count2 > 500)
			takenSpts++;
		if (count3 > 500)
			takenSpts++;
		if (count4 > 500)
			takenSpts++;
		if (count6 > 500)
			takenSpts++;
		if (count7 > 500)
			takenSpts++;
		if (count8 > 500)
			takenSpts++;
		if (count9 > 500)
			takenSpts++;
		if (count10 > 500)
			takenSpts++;
		if (count11 > 500)
			takenSpts++;
		if (count12 > 500)
			takenSpts++;
		if (count13 > 500)
			takenSpts++;
		if (count14 > 500)
			takenSpts++;
		if (count15 > 500)
			takenSpts++;
		if (count16 > 500)
			takenSpts++;
		if (count17 > 500)
			takenSpts++;
		if (count18 > 500)
			takenSpts++;
		if (count19 > 500)
			takenSpts++;
		if (count20 > 500)
			takenSpts++;
		if (count21 > 500)
			takenSpts++;
		if (count22 > 500)
			takenSpts++;
		if (count23 > 200)
			takenSpts++;
		if (count24 > 200)
			takenSpts++;


		//Available Spots       
		availableSpts = 23 - takenSpts;

		//Display current time image (image 1)
		JFrame frame = new JFrame();
		ImageIcon icon = new ImageIcon(outImg1);
		JLabel label = new JLabel(icon);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(display);

//		System.out.println(count1);
//		System.out.println(count2);
//		System.out.println(count3);
//		System.out.println(count4);
//		System.out.println(count6);
//		System.out.println(count7);
//		System.out.println(count8);
//		System.out.println(count9);
//		System.out.println(count10);
//		System.out.println(count11);
//		System.out.println(count12);
//		System.out.println(count13);
//		System.out.println(count14);
//		System.out.println(count15);
//		System.out.println(count16);
//		System.out.println(count17);
//		System.out.println(count18);
//		System.out.println(count19);
//		System.out.println(count20);
//		System.out.println(count21);
//		System.out.println(count22);
//		System.out.println(count23);


		System.out.println("Available Spots: " + availableSpts);
		System.out.println("Taken Spots: " + takenSpts);
		
		return availableSpts;
	}
	
	public int taken(int n)
	{
		int taken = 23 - n;
		return taken;
	}
}