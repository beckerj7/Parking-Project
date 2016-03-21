package Testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DataPlotTest extends LineChart<String,Number>
{
	/**
	 * @param PlotX
	 * @param PlotY
	 */
	public DataPlotTest(String PlotX, String PlotY)
	{
		super(new CategoryAxis(), new NumberAxis());
		this.getXAxis().setLabel(PlotX);//label x-axis
		this.getYAxis().setLabel(PlotY);//label y-axis

		setLegendVisible(false);
	}//end of method DataPlotTest


	/**
	 * @param i
	 * @param ManagerTest
	 */
	public void Plot(int d, int h, int m, int ref, ManagerTest ManagerTest)
	{
		int i=0;
		int k;
		int iTime=0;
		int[] Hist=new int[672];
		String line;
		String day=null;
		String time="0";
		String head1;
		String head2;

		try
		{
			File file=new File("Dummy.txt"); //Takes in the hard coded data from over a weeks analysis
			FileReader inputFile=new FileReader(file); //reads the data for the graph
			BufferedReader in=new BufferedReader(inputFile);
			line=in.readLine();//read line form file

			//loops through the data in order to create the official graph
			for (i=0; i<672; i++)
			{
				Hist[i]=Integer.parseInt(line);//cast line to integer and save to array
				line=in.readLine();			
			}
			in.close();//close reader
		}
		catch (IOException IOE)
		{
			System.out.println("Something is wrong with the dataplot file I/O!");//error message
			IOE.printStackTrace();
		}

		//Logic for identifying the data for the day of the week
		if (i==0) day="Sunday";
		if (i==1) day="Monday";
		if (i==2) day="Tuesday";
		if (i==3) day="Wednesday";
		if (i==4) day="Thursday";
		if (i==5) day="Friday";
		if (i==6) day="Saturday";

		setTitle(day);//set the title
		javafx.scene.chart.XYChart.Series<String, Number> series=new XYChart.Series<String, Number>();//create new plot

		try//plot each datapoint from the data files
		{
			m-=30;
			if (m<0)
			{
				m+=60;
				h--;
			}
			if (m<15) m=0;
			else if (m<30) m=15;
			else if (m<45) m=30;
			else if (m<60) m=45;
			
			for (i=(ref-2); i<(ref+7); i++)
			{
				if (m==60)
				{
					m=0;
					h++;
					if (h==24)
					{
						h=0;
						d++;
						if (d>6) d=0;
					}
				}
				
				
				time=String.valueOf(h*100+m);
				
				if (h==0) head1="00";
				else if (h<10) head1="0";
				else head1="";
//				System.out.println(iTime);

				series.getData().add(new XYChart.Data<String, Number>(head1 + time, Hist[i]));
				m+=15;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ManagerTest.taDisplay.appendText("\nSomething went wrong with the dataplot!");
		}

		getData().add(series);
	}//end of method Plot
}//end of class DataPlotTest