package src;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DataPlot extends LineChart<Number,Number>
{
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

		setTitle(getYAxis().getLabel() + " vs " + getXAxis().getLabel());//set the title
		XYChart.Series<Number, Number> series=new XYChart.Series<Number, Number>();//create new plot

		try {for (i=0; i<c; i++) series.getData().add(new XYChart.Data<Number, Number>(i, i+c));}//plot the datapoint
		catch (Exception e) {Manager.taDisplay.appendText("Something went wrong with the dataplot!");}

		getData().add(series);
	}//end of method Plot
}//end of class DataPlot