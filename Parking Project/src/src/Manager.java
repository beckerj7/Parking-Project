package src;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Manager extends Application
{	
	BorderPane BorderPane;//create GUI elements
	Pane Pane;
	Scene Scene;
	
	int spots = display.spotsAvailable;
	String imageLocation;

	Text txtSpots;

	DataPlot DataPlot=new DataPlot("Time", "Spots");

	Image Image;
	ImageView ImageView=new ImageView(Image);

	TextArea taDescription;
	TextArea taDisplay;

	public static void main(String args[])
	{
		Manager.launch(args);//activate GUI(?)
	}//end of main method


	@Override
	public void start(Stage Stage)
	{
		//GUI assembly
		BorderPane=new BorderPane();

		Pane=new Pane();

		txtSpots=new Text("Number of parking spots available: " +spots);

		taDisplay=new TextArea();//text area creation/formatting
		taDisplay.setEditable(false);

		ImageView.setFitWidth(800);//imageView formatting
		ImageView.setPreserveRatio(true);

		taDisplay.setText("Number of parking spots available: ");//set text to be displayed

		DataPlot.Plot(24, this);//create test dataplot for GUI

		try 
		{
			imageLocation=ImagePull();
			System.out.println(imageLocation);
		}
		catch (IOException IOE)
		{
			System.out.println("Something is wrong with the file I/O!");
			IOE.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Something is wrong with the image pull and I don`t know what!");
			e.printStackTrace();
		}
		
		ImageView=new ImageView(new Image(imageLocation));

		
		BorderPane.setCenter(DataPlot);
		BorderPane.setTop(txtSpots);//place text area in top pane
		BorderPane.setLeft(ImageView);//place image in center pane

		Scene=new Scene(BorderPane);//lights!
		Stage.setScene(Scene);//camera!
		Stage.show();//action!
	}//end of method start

	public static String ImagePull() throws Exception
	{
		int i=0;//counter variable
		boolean check;//file existence variable

		String imageUrl="http://construction1.db.erau.edu/jpg/1/image.jpg";

		do//avoid overwriting existing images
		{
			i++;
			check=new File("image" + i + ".jpg").exists();
		}while (check==true);

		String destinationFile="image" + i + ".jpg";//set image destination
		
		try {saveImage(imageUrl, destinationFile);}//download and save image
		catch (IOException e1)//catch file I/O exceptions
		{
			System.out.println("Something went wrong with the file I/O!");//error notification message
			e1.printStackTrace();
		}

		destinationFile="image" + i + ".jpg";
		System.out.println(destinationFile);
		return destinationFile;//return image location
	}


	public static void saveImage(String imageUrl, String destinationFile) throws IOException
	{
		URL url=new URL(imageUrl);
		InputStream is=url.openStream();
		OutputStream os=new FileOutputStream(destinationFile);

		byte[] b=new byte[2048];
		int length;

		while ((length=is.read(b))!=-1) os.write(b, 0, length);

		is.close();
		os.close();
	}
}//end of class Manager