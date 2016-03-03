package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DataPlot extends LineChart<Number,Number>
{
	/**
	 * @param PlotX
	 * @param PlotY
	 */
	public DataPlot(String PlotX, String PlotY)
	{
		super(new NumberAxis(), new NumberAxis());
		this.getXAxis().setLabel(PlotX);//label x-axis
		this.getYAxis().setLabel(PlotY);//label y-axis
		setLegendVisible(false);
	}//end of method DataPlot


	public void Plot(int c, Manager Manager)
	{
		int i;
		int j;
		int[][] Hist=new int[7][96];
		String line;

		try
		{
			for (i=0; i<7; i++)
			{
				File fil=new File("Day" + i + ".txt");
				FileReader inputFil=new FileReader(fil);
				BufferedReader in=new BufferedReader(inputFil);
				line=in.readLine();

				for (j=0; j<96; j++)
				{
					while(line!=null)
					{
						Hist[i][j]=Integer.parseInt(line); //this is line 19
						System.out.println(Hist[i][j]);
						line=in.readLine();
					}				
				}
				in.close();
			}
		}
		catch (IOException IOE)
		{
			System.out.println("Something is wrong with the dataplot file I/O!");
			IOE.printStackTrace();
		}

		setTitle(getYAxis().getLabel() + " vs " + getXAxis().getLabel());//set the title
		XYChart.Series<Number, Number> series=new XYChart.Series<Number, Number>();//create new plot

		try {for (i=0; i<c; i++) series.getData().add(new XYChart.Data<Number, Number>(i, i+c));}//plot the datapoint
		catch (Exception e) {Manager.taDisplay.appendText("Something went wrong with the dataplot!");}

		getData().add(series);
	}//end of method Plot
}//end of class DataPlot