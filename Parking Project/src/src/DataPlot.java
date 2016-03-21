package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DataPlot extends LineChart<String,Number>
{
	/**
	 * @param PlotX
	 * @param PlotY
	 */
	public DataPlot(String PlotX, String PlotY)
	{
		super(new CategoryAxis(), new NumberAxis());
        this.getXAxis().setLabel(PlotX);//label x-axis
		this.getYAxis().setLabel(PlotY);//label y-axis
		
        setLegendVisible(false);
	}//end of method DataPlot


	/**
	 * @param i
	 * @param Manager
	 */
	public void Plot(int i, Manager Manager)
	{
		int j;
		int k;
		int[][] Hist=new int[7][96];
		String line;
		String day=null;
		String time="0";
		String head;

		try
		{
			File file=new File("Day" + i + " Dummy.txt"); //Takes in the hard coded data from over a weeks analysis
			FileReader inputFile=new FileReader(file); //reads the data for the graph
			BufferedReader in=new BufferedReader(inputFile);
			line=in.readLine();//read line form file

			//loops through the data in order to create the official graph
			for (j=0; j<96; j++)
			{
				Hist[i][j]=Integer.parseInt(line);//cast line to integer and save to array
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
			k=0;
			
			for (j=0; j<96; j++)
			{
				k++;
				
				time=String.valueOf(Integer.parseInt(time)+15);
				if (k==4)
				{
					k=0;
					time=String.valueOf(Integer.parseInt(time)+40);
				}
				if (Integer.parseInt(time)<100) head="00";
				else if (Integer.parseInt(time)<1000) head="0";
				else head="";
				
				series.getData().add(new XYChart.Data<String, Number>(head + time, Hist[i][j]));
			}
		}
		catch (Exception e) {Manager.taDisplay.appendText("\nSomething went wrong with the dataplot!");}

		getData().add(series);
	}//end of method Plot
}//end of class DataPlot