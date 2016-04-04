package src;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * @author Jeffrey Becker
 * Launches and manages GUI and manages the call of the program's methods
 */

//Jeffrey Becker
public class Manager extends Application
{	
	//create GUI elements
	BorderPane borderPane;
	StackPane top;
	StackPane left;
	StackPane center;
	StackPane right;
	StackPane bottom;
	
	Scene scene;

	int spots = highlight.availableSpts; //value grabbed from display class
	int taken = highlight.takenSpts; //value grabbed from display class
	int spotsA;
	int d;//numerical day indicator
	int iHour;//numerical hour indicator
	int iMinute;//numerical minute indicator
	int[] hist;
	Date dAndT;
	String sDate;
	String sHour;
	String sMinute;
	SimpleDateFormat dayOfWeek;
	SimpleDateFormat hour;
	SimpleDateFormat minute;

	HBox hbBt;//HBox for buttons
	HBox hbPics;//HBox for pictures
	VBox vbGraph;//VBox for graph
	VBox vbTA;//VBox for data display

	Button btLeft;
	Button btRight;
	Button btDayPlus;
	Button btDayMinus;
	Button btRefresh;
	Button btAck;

	String imageLocation; //string for image location on disk

	Image image; //creates image for imageView
	ImageView imageView=new ImageView(image); //node to display image

	TextArea taReport;//error reporting text area
	TextArea taDisplay;//user data display text area

	Text txtWait;
	
	DataPlot plot;

	DataManager dMan=new DataManager();

	public static void main(String args[])
	{
		Manager.launch(args);//activate GUI(?)
	}//end of main method



	@Override
	public void start(Stage Stage)
	{
		try
		{
			int i;

			plot=new DataPlot("Time", "Spots Available");//instantiate DataPlot object

			//GUI element creation
			borderPane=new BorderPane();
			left=new StackPane();
			left.setPrefSize(600, 400);

			//Buttons being used for shift through graphs and refreshing the GUI
			btLeft=new Button("<");
			btRight=new Button(">");
			btDayMinus=new Button("<<");
			btDayPlus=new Button(">>");
			btRefresh=new Button("Refresh");
			btAck=new Button("Got it!");

			hbBt=new HBox();
			hbPics=new HBox();
			vbGraph=new VBox();
			vbTA=new VBox();
			vbTA=new VBox();

			taDisplay=new TextArea();
			taDisplay.setEditable(false);

			taReport=new TextArea();
			taReport.setEditable(false);

			imageView.setFitWidth(800);//imageView formatting
			imageView.setPreserveRatio(true);

			try
			{
				imageLocation=dMan.imagePull();//download image to local storage
				//				spotsA=highlight.main(imageLocation);
				spotsA=0;
				taDisplay.setText("Number of parking spots available: " + spotsA + "\nNumber of parking spots Taken: " + taken);//set text to be displayed
				taDisplay.setFont(Font.font ("Veranda", 30));
				
				getDate();
				
				dMan.read();

				if (spotsA==0)
				{
					txtWait=new Text("Estimated time until\nnext spot is available:\n" + String.valueOf(dMan.predict(this, d, iHour, iMinute)) + " minutes.");
					txtWait.setFont(Font.font ("Veranda", 40));
					txtWait.setFill(Color.RED);
					vbTA.getChildren().addAll(txtWait, btAck, taDisplay, btRefresh);
				}
				else vbTA.getChildren().addAll(taDisplay, btRefresh);
				
				hist=plot.plot(d, iHour, iMinute, this, dMan);//create test dataplot for GUI
				vbGraph.getChildren().addAll(hbBt, plot);
			}
			catch (IOException ioe)// Catching errors that may occur with the file I/O
			{
				System.out.println("Something is wrong with the file I/O!");
				ioe.printStackTrace();
			}
			catch (Exception e)
			{
				System.out.println("Something is wrong with the image pull and I don`t know what!");
				e.printStackTrace();
			}


//			dMan.Overwrite(hist, 2, 999999000);


			//GUI assembly
			hbBt.getChildren().addAll(btDayMinus, btLeft, btRight, btDayPlus);//add buttons to HBox

			try
			{
				imageView=new ImageView(new Image(imageLocation)); //create image object in preparation to be loaded and displayed
				borderPane.setCenter(imageView);//place image in center pane
			}
			catch (Exception e)
			{
				for (i=0; i<9; i++) taReport.appendText("Failed to load image.\tFailed to load image.\tFailed to load image.\n");
				borderPane.setCenter(taReport);//place image in center pane
			}

			left.getChildren().addAll(vbTA);
			borderPane.setBottom(vbGraph);//place graph in bottom pane
			borderPane.setLeft(left);//place text area in left pane

			//button listeners
			btRefresh.setOnAction(e->refresh());
			btLeft.setOnAction(e->left(plot));
			btRight.setOnAction(e->right(plot));
			btDayPlus.setOnAction(e->next(plot));
			btDayMinus.setOnAction(e->previous(plot));
			btAck.setOnAction(e->acknowledge());

			scene=new Scene(borderPane);//lights!
			Stage.setScene(scene);//camera!
			Stage.show();//action!
		}
		catch (Exception e) {e.printStackTrace();}

		//begin autonomous image collection
		Timer timer=new Timer();
		timer.scheduleAtFixedRate(dMan, 0, 1000);
	}//end of method start



	/**
	 * 
	 */
	public void refresh()//update to current camera image
	{
		int i;
		
		taDisplay.clear();
		vbTA.getChildren().clear();

		try
		{
			imageLocation=dMan.imagePull();//download image to local storage
			//				spotsA=highlight.main(imageLocation);
			spotsA=0;

			taDisplay.setText("Number of parking spots available: " + spotsA + "\nNumber of parking spots Taken: " + taken);//set text to be displayed
			taDisplay.setFont(Font.font ("Veranda", 30));
			
			getDate();
			
			dMan.read();

			if (spotsA==0)
			{
				txtWait=new Text("Estimated time until\nnext spot is available:\n" + String.valueOf(dMan.predict(this, d, iHour, iMinute)) + " minutes.");
				txtWait.setFont(Font.font ("Veranda", 40));
				txtWait.setFill(Color.RED);
				vbTA.getChildren().addAll(txtWait, btAck, taDisplay, btRefresh);
			}
			else vbTA.getChildren().addAll(taDisplay, btRefresh);
		}
		catch (IOException ioe)// Catching errors that may occur with the file I/O
		{
			System.out.println("Something is wrong with the file I/O!");
			ioe.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Something is wrong with the image pull and I don`t know what!");
			e.printStackTrace();
		}
		try
		{
			imageView=new ImageView(new Image(imageLocation)); //create image object in preparation to be loaded and displayed
			borderPane.setCenter(imageView);//place image in center pane
		}
		catch (Exception e)
		{
			for (i=0; i<9; i++) taReport.appendText("Failed to load image.\tFailed to load image.\tFailed to load image.\n");
			borderPane.setCenter(taReport);//place image in center pane
		}
	}//end of method refresh



	/**Changes plot to the next day at the same time.
	 * @param Plot
	 */
	public void next(DataPlot Plot)
	{
		d++;
		if (d>6) d=0;
		Plot=new DataPlot("Time", "Spots Available");
		Plot.plot(d, iHour, iMinute, this, dMan);//create test dataplot for GUI

		vbGraph.getChildren().clear();//clear VBox
		vbGraph.getChildren().addAll(hbBt, Plot);//reload VBox
	}//end of method next



	/**Changes plot to the previous day at the same time.
	 * @param Plot
	 */
	public void previous(DataPlot Plot)
	{
		d--;
		if (d<0) d=6;
		Plot=new DataPlot("Time", "Spots Available");
		Plot.plot(d, iHour, iMinute, this, dMan);//create test dataplot for GUI

		vbGraph.getChildren().clear();//clear VBox
		vbGraph.getChildren().addAll(hbBt, Plot);//reload VBox
	}//end of method previous



	/**Loads the next datapoint to the left.
	 * @param Plot
	 */
	public void left(DataPlot Plot)
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
		Plot.plot(d, iHour, iMinute, this, dMan);//create test dataplot for GUI

		vbGraph.getChildren().clear();//clear VBox
		vbGraph.getChildren().addAll(hbBt, Plot);//reload VBox
	}//end of method left



	/**Loads the next datapoint to the right.
	 * @param Plot
	 */
	public void right(DataPlot Plot)
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
		Plot.plot(d, iHour, iMinute, this, dMan);//create test dataplot for GUI

		vbGraph.getChildren().clear();
		vbGraph.getChildren().addAll(hbBt, Plot);
	}//end of method right



	public void acknowledge()
	{
		try
		{
			vbTA.getChildren().clear();
			vbTA.getChildren().addAll(taDisplay, btRefresh);
			borderPane.setLeft(left);
		}
		catch (Exception E) {E.printStackTrace();}
	}
	
	
	
	/**Obtains the current date and time from the device running the program for use in the Manager class.
	 * @author Brandon Koury
	 * 
	 */
	public void getDate()
	{
		//Brandon Koury
		dAndT = new Date( );
		dayOfWeek = new SimpleDateFormat ("E");//acquire day
		sDate = dayOfWeek.format(dAndT);//cast to string
		hour = new SimpleDateFormat ("kk");//acquire hour
		sHour = hour.format(dAndT);//cast to string
		minute = new SimpleDateFormat ("mm");//acquire minute
		sMinute = minute.format(dAndT);//cast to string
		iHour=Integer.parseInt(sHour);//cast to integer
		iMinute=Integer.parseInt(sMinute);//cast to integer

		if (iHour==24) sHour=String.valueOf(iHour=0);//preserve arithmetic logic
		
		taDisplay.appendText("\n" + sDate + " " + sHour + ":" + sMinute);//display day and time
		
		//cast day to a representative number
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
		default: System.out.println("Something went wrong with the date switch statement!");
		}
	}
}//end of class Manager