package src;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Manager extends Application
{	
	BorderPane BorderPane;//create GUI elements
	Pane Pane;
	Scene Scene;
	
	HBox HBoxBt;
	VBox VBoxGraph;
	
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
		int g=1;
		
		//GUI assembly
		BorderPane=new BorderPane();

		Pane=new Pane();

		Button btLeft=new Button("Left");
		Button btRight=new Button("Right");
		
		HBoxBt=new HBox();
		VBoxGraph=new VBox();
		
		taDisplay=new TextArea();//text area creation/formatting
		taDisplay.setEditable(false);

		ImageView.setFitWidth(800);//imageView formatting
		ImageView.setPreserveRatio(true);

		taDisplay.setText("Number of parking spots available: " + spots);//set text to be displayed

		DataPlot.Plot(24, this);//create test dataplot for GUI
		HBoxBt.getChildren().addAll(btLeft, btRight);
		VBoxGraph.getChildren().addAll(HBoxBt, DataPlot);

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
		
//		ImageView=new ImageView(new Image(imageLocation));

		
		BorderPane.setLeft(VBoxGraph);//place graph in bottom pane
		BorderPane.setCenter(ImageView);//place image in center pane
		BorderPane.setTop(taDisplay);//place text area in left pane


		Scene=new Scene(BorderPane);//lights!
		Stage.setScene(Scene);//camera!
		Stage.show();//action!
		
		btLeft.setOnAction(e->Left(g, this));
		btRight.setOnAction(e->Right(g, this));
	}//end of method start

	
	public int Left(int g, Manager Manager)
	{
		g--;
		
		return g;
	}
	
	public int Right(int g, Manager Manager)
	{
		g++;
		
		return g;
	}
	
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