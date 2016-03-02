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

	
	public void Plot(int time, int spots, Manager Manager)
	{
		setTitle(getYAxis().getLabel() + " vs " + getXAxis().getLabel());//set the title
		XYChart.Series<Number, Number> series=new XYChart.Series<Number, Number>();//create new plot

		try {series.getData().add(new XYChart.Data<Number, Number>(time, spots));}//plot the datapoint
		catch (Exception e) {Manager.taDisplay.appendText("Something went wrong with the dataplot!");}

		getData().add(series);
	}//end of method Plot
	
	
	public void Plot(int i, long it, Manager Manager)
	{
		setTitle(getYAxis().getLabel() + " vs " + getXAxis().getLabel());//set the title
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();//create new plot

		try {series.getData().add(new XYChart.Data<Number, Number>(i, it));}//plot the datapoint
		catch (Exception e) {Manager.taDisplay.appendText("Something went wrong with the dataplot!");}

		getData().add(series);
	}//end of method Plot
}//end of class DataPlot