package src;

import java.io.IOException;
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
import javafx.stage.Stage;

public class Manager extends Application
{	
	//create GUI elements
	BorderPane borderPane;
	Scene Scene;

	int d;//numerical day indicator
	int ref=0;
	int spots = highlight.availableSpts; //value grabbed from display class
	int taken = highlight.takenSpts; //value grabbed from display class
	int iHour;
	int iMinute;
	int[] hist;
	Date DandT;
	String sDate;
	String sHour;
	String sMinute;
	SimpleDateFormat DayOfWeek;
	SimpleDateFormat Hour;
	SimpleDateFormat Minute;

	HBox hbBt;//HBox for buttons
	HBox hbPics;//HBox for pictures
	VBox vbGraph;//VBox for graph
	VBox vbDisplay;//VBox for data display

	String imageLocation; //string for image location on disk

	Image image; //creates image for imageView
	ImageView imageView=new ImageView(image); //node to display image

	TextArea taReport;//error reporting text area
	TextArea taDisplay;//user data display text area
	
	DataManager dMan=new DataManager();

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
		try
		{
			int i;

			DataPlot plot=new DataPlot("Time", "Spots Available");//instantiate DataPlot object

			//GUI element creation
			borderPane=new BorderPane();

			//Buttons being used for shift through graphs and refreshing the GUI
			Button btLeft=new Button("<");
			Button btRight=new Button(">");
			Button btDayPlus=new Button(">>");
			Button btDayMinus=new Button("<<");
			Button btRefresh=new Button("Refresh");

			hbBt=new HBox();
			hbPics=new HBox();
			vbGraph=new VBox();
			vbDisplay=new VBox();

			taDisplay=new TextArea();
			taDisplay.setEditable(false);

			taReport=new TextArea();
			taReport.setEditable(false);

			imageView.setFitWidth(800);//imageView formatting
			imageView.setPreserveRatio(true);

			taDisplay.setText("Number of parking spots available: " + spots + "\nNumber of parking spots Taken: " + taken);//set text to be displayed

			try
			{
				imageLocation=dMan.ImagePull();//download image to local storage
				DandT = new Date( );
				DayOfWeek = new SimpleDateFormat ("E");//acquire day
				sDate = DayOfWeek.format(DandT);//cast to string
				Hour = new SimpleDateFormat ("kk");//acquire hour
				sHour = Hour.format(DandT);//cast to string
				Minute = new SimpleDateFormat ("mm");//acquire minute
				sMinute = Minute.format(DandT);//cast to string
				iHour=Integer.parseInt(sHour);//cast to integer
				iMinute=Integer.parseInt(sMinute);//cast to integer

				if (iHour==24)//preserve arithmetic logic
				{
					iHour=0;
					sHour=String.valueOf(iHour);
				}
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
				default: System.out.println("Something went wrong with the date switch statement!");
				}

				hist=plot.Plot(d, iHour, iMinute, this, dMan);//create test dataplot for GUI
				vbGraph.getChildren().addAll(hbBt, plot);
			}
			catch (IOException IOE)// Catching errors that may occur with the file I/O
			{
				System.out.println("Something is wrong with the file I/O!");
				IOE.printStackTrace();
			}
			catch (Exception e)
			{
				System.out.println("Something is wrong with the image pull and I don`t know what!");
				e.printStackTrace();
			}

			
			dMan.Overwrite(hist, 10, 10000000);
			
			
			//GUI assembly
			hbBt.getChildren().addAll(btDayMinus, btLeft, btRight, btDayPlus);//add buttons to HBox
			vbDisplay.getChildren().addAll(taDisplay, btRefresh);//text area and refresh button to HBox

			try
			{
				imageView=new ImageView(new Image(imageLocation)); //create image object in preparation to be loaded and displayed
				borderPane.setCenter(imageView);//place image in center pane
			}
			catch (Exception E)
			{
				for (i=0; i<9; i++) taReport.appendText("Failed to load image.\tFailed to load image.\tFailed to load image.\n");
				borderPane.setCenter(taReport);//place image in center pane
			}

			borderPane.setBottom(vbGraph);//place graph in bottom pane
			borderPane.setLeft(vbDisplay);//place text area in left pane

			//button listeners
			btRefresh.setOnAction(e->Refresh());
			btLeft.setOnAction(e->Left(plot));
			btRight.setOnAction(e->Right(plot));
			btDayPlus.setOnAction(e->Next(plot));
			btDayMinus.setOnAction(e->Previous(plot));

			Scene=new Scene(borderPane);//lights!
			Stage.setScene(Scene);//camera!
			Stage.show();//action!
		}
		catch (Exception E) {E.printStackTrace();}
	}//end of method start



	public void Refresh()//update to current camera image
	{
		int i;

		try 
		{
			imageLocation=dMan.ImagePull();//download image to local storage
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
			imageView=new ImageView(new Image(imageLocation)); //create image object in preparation to be loaded and displayed
			borderPane.setCenter(imageView);//place image in center pane
		}
		catch (Exception E)
		{
			taReport.clear();
			for (i=0; i<9; i++) taReport.appendText("Failed to load image.\tFailed to load image.\tFailed to load image.\n");//error message
			
			borderPane.setCenter(taReport);//place image in center pane
		}
	}//end of method Refresh


	
	/**Changes plot to the next day at the same time.
	 * @param Plot
	 */
	public void Next(DataPlot Plot)
	{
		d++;
		if (d>6) d=0;
		Plot=new DataPlot("Time", "Spots Available");
		Plot.Plot(d, iHour, iMinute, this, dMan);//create test dataplot for GUI

		vbGraph.getChildren().clear();//clear VBox
		vbGraph.getChildren().addAll(hbBt, Plot);//reload VBox
	}//end of method Next
	
	
	
	/**Changes plot to the previous day at the same time.
	 * @param Plot
	 */
	public void Previous(DataPlot Plot)
	{
		d--;
		if (d<0) d=6;
		Plot=new DataPlot("Time", "Spots Available");
		Plot.Plot(d, iHour, iMinute, this, dMan);//create test dataplot for GUI

		vbGraph.getChildren().clear();//clear VBox
		vbGraph.getChildren().addAll(hbBt, Plot);//reload VBox
	}//end of method Previous
	
	

	/**Loads the next datapoint to the left.
	 * @param Plot
	 */
	public void Left(DataPlot Plot)
	{
		iMinute-=15;
		if (iMinute<0)
		{
			iMinute+=60;
			iHour--;
			if (iHour<0)
			{
				iHour=23;
				d--;
				if (d<0) d=6;
			}
		}
		Plot=new DataPlot("Time", "Spots Available");
		Plot.Plot(d, iHour, iMinute, this, dMan);//create test dataplot for GUI

		vbGraph.getChildren().clear();//clear VBox
		vbGraph.getChildren().addAll(hbBt, Plot);//reload VBox
	}//end of method Left



	/**Loads the next datapoint to the right.
	 * @param Plot
	 */
	public void Right(DataPlot Plot)
	{
		iMinute+=15;
		if (iMinute>59)
		{
			iMinute-=60;
			iHour++;
			if (iHour>23)
			{
				iHour=0;
				d++;
				if (d>6) d=0;
			}
		}

		Plot=new DataPlot("Time", "Spots Available");
		Plot.Plot(d, iHour, iMinute, this, dMan);//create test dataplot for GUI

		vbGraph.getChildren().clear();
		vbGraph.getChildren().addAll(hbBt, Plot);
	}//end of method Right
}//end of class Manager