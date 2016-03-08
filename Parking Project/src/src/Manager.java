package src;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Manager extends Application
{	
	//create GUI elements
	BorderPane BorderPane;
	Scene Scene;

	int d;//numerical day indicator
	int spots = display.spotsAvailable;
	int taken = display.spotsTaken;

	HBox HBoxBt;//HBox for buttons
	VBox VBoxGraph;//VBox for graph
	VBox VBoxDisplay;//

	String imageLocation;

	Text txtSpots;

	DataPlot DataPlot=new DataPlot("Time", "Spots");

	Image Image;
	ImageView ImageView=new ImageView(Image);

	TextArea taReport;
	TextArea taDisplay;

	public static void main(String args[])
	{
		Manager.launch(args);//activate GUI(?)
	}//end of main method


	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage Stage)
	{
		int i;
		DataPlot[] Plots=new DataPlot[7];
		for (i=0; i<7; i++) Plots[i]=new DataPlot("Time", "Spots");

		//GUI assembly
		BorderPane=new BorderPane();

		Button btLeft=new Button("<---");
		Button btRight=new Button("--->");
		Button btRefresh=new Button("Refresh");

		HBoxBt=new HBox();
		VBoxGraph=new VBox();
		VBoxDisplay=new VBox();

		taDisplay=new TextArea();//text area creation/formatting
		taDisplay.setEditable(false);

		taReport=new TextArea();
		taReport.setEditable(false);

		ImageView.setFitWidth(800);//imageView formatting
		ImageView.setPreserveRatio(true);

		taDisplay.setText("Number of parking spots available: " + spots + "\nNumber of parking spots Taken: " + taken);//set text to be displayed

		for (i=0; i<7; i++) Plots[i].Plot(i, this);//create test dataplot for GUI
		HBoxBt.getChildren().addAll(btLeft, btRight);//add buttons to HBox
		VBoxDisplay.getChildren().addAll(taDisplay, btRefresh);//text area and refresh button to HBox

		try
		{
			imageLocation=ImagePull();//download image to local storage
			Date DandT = new Date( );
			SimpleDateFormat DayOfWeek = new SimpleDateFormat ("E");//acquire day
			String sDate = DayOfWeek.format(DandT);//cast to string
			SimpleDateFormat Hour = new SimpleDateFormat ("kk");//acquire hour
			String sHour = Hour.format(DandT);//cast to string
			SimpleDateFormat Minute = new SimpleDateFormat ("mm");//acquire minute
			String sMinute = Minute.format(DandT);//cast to string
			taDisplay.appendText("\n" + sDate + " " + sHour + ":" + sMinute);//display day and time

			switch (sDate){//cast day to a representative number
			case "Sun": d = 0;
			break;
			case "Mon": d = 1;
			break;
			case "Tue": d = 2;
			break;
			case "Wed": d = 3;
			break;
			case "Thu": d = 4;
			break;
			case "Fri": d = 5;
			break;
			case "Sat": d = 6;
			break;
			default: System.out.println("Something went wrong with the switch statement!");
			}
			VBoxGraph.getChildren().addAll(HBoxBt, Plots[d]);
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

		try
		{
			ImageView=new ImageView(new Image(imageLocation));
			BorderPane.setCenter(ImageView);//place image in center pane
		}
		catch (Exception E)
		{
			for (i=0; i<9; i++) taReport.appendText("Failed to load image.\tFailed to load image.\tFailed to load image.\n");
			BorderPane.setCenter(taReport);//place image in center pane
		}

		BorderPane.setBottom(VBoxGraph);//place graph in bottom pane
		BorderPane.setLeft(VBoxDisplay);//place text area in left pane

		Scene=new Scene(BorderPane);//lights!
		Stage.setScene(Scene);//camera!
		Stage.show();//action!
		btRefresh.setOnAction(e->Refresh());//refresh button listener
		btLeft.setOnAction(e->Left(Plots));//cycle graph left button listener
		btRight.setOnAction(e->Right(Plots));//cycle graph right button listener
	}//end of method start

	
	
	public void Refresh()//update to current camera image
	{
		int i;

		try 
		{
			imageLocation=ImagePull();//download image to local storage
			Date DandT = new Date( );
			SimpleDateFormat DayOfWeek = new SimpleDateFormat ("E");//acquire day
			String sDate = DayOfWeek.format(DandT);//cast day to string
			SimpleDateFormat Hour = new SimpleDateFormat ("kk");//acquire hour
			String sHour = Hour.format(DandT);//cast hour to string
			SimpleDateFormat Minute = new SimpleDateFormat ("mm");//acquire minute
			String sMinute = Minute.format(DandT);//cast minute to string
			taDisplay.clear();//clear the text area
			taDisplay.setText("Number of parking spots available: " + spots + "\nNumber of parking spots Taken: " + taken);//set text to be displayed
			taDisplay.appendText("\n" + sDate + " " + sHour + ":" + sMinute);//add date and time to text area

			switch (sDate){//cast day to representative number
			case "Sun": d = 0;
			break;
			case "Mon": d = 1;
			break;
			case "Tue": d = 2;
			break;
			case "Wed": d = 3;
			break;
			case "Thu": d = 4;
			break;
			case "Fri": d = 5;
			break;
			case "Sat": d = 6;
			break;
			}
		}
		catch (IOException IOE)
		{
			System.out.println("Something is wrong with the file I/O!");//error message
			IOE.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Something is wrong with the image pull and I don`t know what!");//error message
			e.printStackTrace();
		}
		try
		{
			ImageView=new ImageView(new Image(imageLocation));
			BorderPane.setCenter(ImageView);//place image in center pane
		}
		catch (Exception E)
		{
			taReport.clear();
			for (i=0; i<9; i++) taReport.appendText("Failed to load image.\tFailed to load image.\tFailed to load image.\n");//error message
			BorderPane.setCenter(taReport);//place image in center pane
		}
	}//end of method Refresh
	
	

	public void Left(DataPlot Plots[])
	{
		d--;
		if (d<0) d=6;

		VBoxGraph.getChildren().clear();//clear VBox
		VBoxGraph.getChildren().addAll(HBoxBt, Plots[d]);//reload VBox
	}//end of method Left

	
	
	public void Right(DataPlot Plots[])
	{
		d++;
		if (d>6) d=0;

		VBoxGraph.getChildren().clear();
		VBoxGraph.getChildren().addAll(HBoxBt, Plots[d]);
	}//end of method Right

	
	
	public static String ImagePull() throws Exception
	{
		int i=0;//counter variable
		boolean check;//file existence variable

		String imageURL="http://construction1.db.erau.edu/jpg/1/image.jpg";

		do//avoid overwriting existing images
		{
			i++;
			check=new File("image" + i + ".jpg").exists();
		}while (check==true);

		String destinationFile="image" + i + ".jpg";//set image destination

		try {saveImage(imageURL, destinationFile);}//download and save image
		catch (IOException e1)//catch file I/O exceptions
		{
			System.out.println("Something went wrong with the file I/O!");//error message
			e1.printStackTrace();
		}

		return destinationFile;//return image location
	}//end of method ImagePull

	

	/*****************************************************************/
	/* Copyright 2013 Code Strategies                                */
	/* This code may be freely used and distributed in any project.  */
	/* However, please do not remove this credit if you publish this */
	/* code in paper or electronic form, such as on a web site.      */
	/*****************************************************************/
	public static void saveImage(String imageURL, String destinationFile) throws IOException
	{
		int length;
		URL url=new URL(imageURL);
		InputStream is=url.openStream();
		OutputStream os=new FileOutputStream(destinationFile);

		byte[] b=new byte[2048];

		while ((length=is.read(b))!=-1) os.write(b, 0, length);

		is.close();
		os.close();
	}//end of method SaveImage
	//Adapted from http://www.avajava.com/tutorials/lessons/how-do-i-save-an-image-from-a-url-to-a-file.html, by Deron Eriksson
}//end of class Manager