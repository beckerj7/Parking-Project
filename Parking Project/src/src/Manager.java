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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Manager extends Application
{	
	BorderPane BorderPane;//create GUI elements
	Pane Pane;
	Scene Scene;
	int g=0;

	HBox HBoxBt;
	VBox VBoxGraph;
	VBox VBoxDisplay;

	int spots = display.spotsAvailable;
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

		Pane=new Pane();

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

		taDisplay.setText("Number of parking spots available: " + spots);//set text to be displayed

		for (i=0; i<7; i++) Plots[i].Plot(i, this);//create test dataplot for GUI
		HBoxBt.getChildren().addAll(btLeft, btRight);
		VBoxDisplay.getChildren().addAll(taDisplay, btRefresh);

		VBoxGraph.getChildren().addAll(HBoxBt, Plots[g]);

		try 
		{
			imageLocation=ImagePull();
			System.out.println(imageLocation);
			Date DandT = new Date( );
			SimpleDateFormat DayOfWeek = new SimpleDateFormat ("E");
			String sDate = DayOfWeek.format(DandT);
			SimpleDateFormat Hour = new SimpleDateFormat ("kk");
			String sHour = Hour.format(DandT);
			SimpleDateFormat Minute = new SimpleDateFormat ("mm");
			String sMinute = Minute.format(DandT);
			taDisplay.appendText("\n" + sDate + " " + sHour + ":" + sMinute);
			int d;
			switch (sDate){
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
		btRefresh.setOnAction(e->Refresh());
		btLeft.setOnAction(e->Left(g, Plots));
		btRight.setOnAction(e->Right(g, Plots));
	}//end of method start

	public void Refresh()
	{
		int i;
		
		try 
		{
			imageLocation=ImagePull();
			System.out.println(imageLocation);
			Date DandT = new Date( );
			SimpleDateFormat DayOfWeek = new SimpleDateFormat ("E");
			String sDate = DayOfWeek.format(DandT);
			SimpleDateFormat Hour = new SimpleDateFormat ("kk");
			String sHour = Hour.format(DandT);
			SimpleDateFormat Minute = new SimpleDateFormat ("mm");
			String sMinute = Minute.format(DandT);
			taDisplay.appendText("\n" + sDate + " " + sHour + ":" + sMinute);
			int d;
			switch (sDate){
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
	}

	public void Left(int g, DataPlot Plots[])
	{
		g--;
		if (g<0) g=6;

		VBoxGraph.getChildren().clear();
		VBoxGraph.getChildren().addAll(HBoxBt, Plots[g]);

		this.g=g;
	}

	public void Right(int g, DataPlot Plots[])
	{
		g++;
		if (g>6) g=0;

		VBoxGraph.getChildren().clear();
		VBoxGraph.getChildren().addAll(HBoxBt, Plots[g]);

		this.g=g;
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