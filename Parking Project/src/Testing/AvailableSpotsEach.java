package Testing;

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

public class AvailableSpotsEach {
    public static void main(String[] args) throws IOException {
    
    BufferedImage img1 = ImageIO.read(new File("image1.png")); // full parking lot
    BufferedImage img2 = ImageIO.read(new File("image2.jpg")); //current time parking lot
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
    int i1=0;
    int j1=0;
    for (i1 = 190; i1 < width1 -570; i1++)
        for (j1 = 210; j1 < height1-230; j1++) {
            Color c = new Color(outImg1.getRGB(i1, j1));
            if (c.equals(Color.WHITE))
                count1++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count1 < 500)
            	img2.setRGB(i1, j1, rgb);
            }
    
    //count number of white pixels for parking spot2
    int count2 = 0;
    int i2=0;
    int j2=0;
    for (i2 = 230; i2 < width1 -535; i2++)
        for (j2 = 210; j2 < height1-235; j2++) {
            Color c = new Color(outImg1.getRGB(i2, j2));
            if (c.equals(Color.WHITE))
                count2++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count2 < 500)
            	img2.setRGB(i2, j2, rgb);
            }
    //count number of white pixels for parking spot3
    int count3 = 0;
    int i3=0;
    int j3=0;
    for (i3 = 265; i3 < width1 -495; i3++)
        for (j3 = 210; j3 < height1-235; j3++) {
            Color c = new Color(outImg1.getRGB(i3, j3));
            if (c.equals(Color.WHITE))
                count3++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count3 < 500)
            	img2.setRGB(i3, j3, rgb);
            }
    //count number of white pixels for parking spot4
    int count4 = 0;
    int i4=0;
    int j4=0;
    for (i4 = 305; i4 < width1 -460; i4++)
        for (j4 = 210; j4 < height1-235; j4++) {
            Color c = new Color(outImg1.getRGB(i4, j4));
            if (c.equals(Color.WHITE))
                count4++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count4 < 500)
            	img2.setRGB(i4, j4, rgb);
            }
    
    //count number of white pixels for parking spot6
    int count6 = 0;
    int i6=0;
    int j6=0;
    for (i6 = 380; i6 < width1 -380; i6++)
        for (j6 = 220; j6 < height1-235; j6++) {
            Color c = new Color(outImg1.getRGB(i6, j6));
            if (c.equals(Color.WHITE))
                count6++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count6 < 500)
            	img2.setRGB(i6, j6, rgb);
            }
    //count number of white pixels for parking spot7
    int count7 = 0;
    int i7=0;
    int j7=0;
    for (i7 = 415; i7 < width1 -345; i7++)
        for (j7 = 230; j7 < height1-230; j7++) {
            Color c = new Color(outImg1.getRGB(i7, j7));
            if (c.equals(Color.WHITE))
                count7++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count7 < 500)
            	img2.setRGB(i7, j7, rgb);
            }
    //count number of white pixels for parking spot8
    int count8 = 0;
    int i8=0;
    int j8=0;
    for (i8 = 455; i8 < width1 -315; i8++)
        for (j8 = 230; j8 < height1-230; j8++) {
            Color c = new Color(outImg1.getRGB(i8, j8));
            if (c.equals(Color.WHITE))
                count8++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count8 < 500)
            	img2.setRGB(i8, j8, rgb);
            }
    //count number of white pixels for parking spot9
    int count9 = 0;
    int i9=0;
    int j9=0;
    for (i9 = 490; i9 < width1 -270; i9++)
        for (j9 = 230; j9 < height1-230; j9++) {
            Color c = new Color(outImg1.getRGB(i9, j9));
            if (c.equals(Color.WHITE))
                count9++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count9 < 500)
            	img2.setRGB(i9, j9, rgb);
            }
    //count number of white pixels for parking spot10
    int count10 = 0;
    int i10=0;
    int j10=0;
    for (i10 = 525; i10 < width1 -240; i10++)
        for (j10 = 230; j10 < height1-235; j10++) {
            Color c = new Color(outImg1.getRGB(i10, j10));
            if (c.equals(Color.WHITE))
                count10++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count10 < 500)
            	img2.setRGB(i10, j10, rgb);
            }
    //count number of white pixels for parking spot11
    int count11 = 0;
    int i11=0;
    int j11=0;
    for (i11 = 555; i11 < width1 -225; i11++)
        for (j11 = 240; j11 < height1-225; j11++) {
            Color c = new Color(outImg1.getRGB(i11, j11));
            if (c.equals(Color.WHITE))
                count11++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count11 < 500)
            	img2.setRGB(i11, j11, rgb);
            }
    //count number of white pixels for parking spot12
    int count12 = 0;
    int i12=0;
    int j12=0;
    for (i12 = 190; i12 < width1 -575; i12++)
        for (j12 = 270; j12 < height1-175; j12++) {
            Color c = new Color(outImg1.getRGB(i12, j12));
            if (c.equals(Color.WHITE))
                count12++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count12 < 500)
            	img2.setRGB(i12, j12, rgb);
            }
    //count number of white pixels for parking spot13
    int count13 = 0;
    int i13=0;
    int j13=0;
    for (i13 = 240; i13 < width1 -530; i13++)
        for (j13 = 270; j13 < height1-175; j13++) {
            Color c = new Color(outImg1.getRGB(i13, j13));
            if (c.equals(Color.WHITE))
                count13++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count13 < 500)
            	img2.setRGB(i13, j13, rgb);
            }
    //count number of white pixels for parking spot14
    int count14 = 0;
    int i14=0;
    int j14=0;
    for (i14 = 285; i14 < width1 -480; i14++)
        for (j14 = 270; j14 < height1-170; j14++) {
            Color c = new Color(outImg1.getRGB(i14, j14));
            if (c.equals(Color.WHITE))
                count14++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count14 < 500)
            	img2.setRGB(i14, j14, rgb);
            }
    //count number of white pixels for parking spot15
    int count15 = 0;
    int i15=0;
    int j15=0;
    for (i15 = 325; i15 < width1 -435; i15++)
        for (j15 = 270; j15 < height1-170; j15++) {
            Color c = new Color(outImg1.getRGB(i15, j15));
            if (c.equals(Color.WHITE))
                count15++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count15 < 500)
            	img2.setRGB(i15, j15, rgb);
            }
    //count number of white pixels for parking spot16
    int count16 = 0;
    int i16=0;
    int j16=0;
    for (i16 = 370; i16 < width1 -390; i16++)
        for (j16 = 270; j16 < height1-170; j16++) {
            Color c = new Color(outImg1.getRGB(i16, j16));
            if (c.equals(Color.WHITE))
                count16++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count16 < 500)
            	img2.setRGB(i16, j16, rgb);
            }
    
    //count number of white pixels for parking spot17
    int count17 = 0;
    int i17=0;
    int j17=0;
    for (i17 = 410; i17 < width1 -350; i17++)
        for (j17 = 270; j17 < height1-175; j17++) {
            Color c = new Color(outImg1.getRGB(i17, j17));
            if (c.equals(Color.WHITE))
                count17++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count17 < 500)
            	img2.setRGB(i17, j17, rgb);
            }
    
    //count number of white pixels for parking spot18
    int count18 = 0;
    int i18=0;
    int j18=0;
    for (i18 = 450; i18 < width1 -310; i18++)
        for (j18 = 280; j18 < height1-175; j18++) {
            Color c = new Color(outImg1.getRGB(i18, j18));
            if (c.equals(Color.WHITE))
                count18++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count18 < 500)
            	img2.setRGB(i18, j18, rgb);
            }
          
   
    //count number of white pixels for parking spot19
    int count19 = 0;
    int i19=0;
    int j19=0;
    for (i19 = 485; i19 < width1 -275; i19++)
        for (j19 = 285; j19 < height1-170; j19++) {
            Color c = new Color(outImg1.getRGB(i19, j19));
            if (c.equals(Color.WHITE))
                count19++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count19 < 500)
            	img2.setRGB(i19, j19, rgb);
            }
    
    //count number of white pixels for parking spot20
    int count20 = 0;
    int i20=0;
    int j20=0;
    for (i20 = 520; i20 < width1 -240; i20++)
        for (j20 = 275; j20 < height1-170; j20++) {
            Color c = new Color(outImg1.getRGB(i20, j20));
            if (c.equals(Color.WHITE))
                count20++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count20 < 500)
            	img2.setRGB(i20, j20, rgb);
            }
    //count number of white pixels for parking spot21
    int count21 = 0;
    int i21=0;
    int j21=0;
    for (i21 = 550; i21 < width1 -210; i21++)
        for (j21 = 275; j21 < height1-170; j21++) {
            Color c = new Color(outImg1.getRGB(i21, j21));
            if (c.equals(Color.WHITE))
                count21++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count21 < 500)
            	img2.setRGB(i21, j21, rgb);
            }
    //count number of white pixels for parking spot22
    int count22 = 0;
    int i22=0;
    int j22=0;
    for (i22 = 585; i22 < width1 -170; i22++)
        for (j22 = 275; j22 < height1-175; j22++) {
            Color c = new Color(outImg1.getRGB(i22, j22));
            if (c.equals(Color.WHITE))
                count22++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count22 < 500)
            	img2.setRGB(i22, j22, rgb);
            }
    //count number of white pixels for parking spot23
    int count23 = 0;
    int i23=0;
    int j23=0;
    for (i23 = 615; i23 < width1 -150; i23++)
        for (j23 = 280; j23 < height1-175; j23++) {
            Color c = new Color(outImg1.getRGB(i23, j23));
            if (c.equals(Color.WHITE))
                count23++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count23 < 500)
            	img2.setRGB(i23, j23, rgb);
            }
    //count number of white pixels for parking spot24 //Error due to tree at this spot
    int count24 = 0;
    int i24=0;
    int j24=0;
    for (i24 = 650; i24 < width1 -115; i24++)
        for (j24 = 285; j24 < height1-170; j24++) {
            Color c = new Color(outImg1.getRGB(i24, j24));
            if (c.equals(Color.WHITE))
                count24++;
            Color red = new Color(255,0,0,0);
            int rgb = red.getRGB();
            if (count24 < 500)
            	img2.setRGB(i24, j24, rgb);
            	
            }
    //Color red = new Color(255,0,0,0);
    //int rgb = red.getRGB();
    //if (count24 < 500)
    	//img2.setRGB(i24, j24, rgb);
   
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
    availableSpts = 22 - takenSpts;
    
    //Display current time image (image 1)
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon(img2);
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
// for testing purposes
    System.out.println(count1);
    System.out.println(count2);
    System.out.println(count3);
    System.out.println(count4);
    System.out.println(count6);
    System.out.println(count7);
    System.out.println(count8);
    System.out.println(count9);
    System.out.println(count10);
    System.out.println(count11);
    System.out.println(count12);
    System.out.println(count13);
    System.out.println(count14);
    System.out.println(count15);
    System.out.println(count16);
    System.out.println(count17);
    System.out.println(count18);
    System.out.println(count19);
    System.out.println(count20);
    System.out.println(count21);
    System.out.println(count22);
    System.out.println(count23);
    
    System.out.println("Available Spots: " + availableSpts);
    System.out.println("Taken Spots: " + takenSpts);
    }
    
    
    }

