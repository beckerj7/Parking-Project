package src;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


//Jeffrey Becker
public class DataPlot extends LineChart<String,Number>
{
	int[] hist=new int[672];

	public DataPlot(String PlotX, String PlotY)
	{
		super(new CategoryAxis(), new NumberAxis(0, 24, 3));
		this.getXAxis().setLabel(PlotX);//label x-axis
		this.getYAxis().setLabel(PlotY);//label y-axis

		setLegendVisible(false);
	}//end of method DataPlot



	public int[] plot(int d, int h, int m, Manager man, DataManager dMan)
	{
		int i=0;
		int ref=d*96+h*4+m/15;
		String day=null;
		String head1;
		String head2;
		boolean flag1=false;
		boolean flag2=false;

		hist=dMan.read();

		//prepare title string for day of week
		if (d==0) day="Sunday";
		if (d==1) day="Monday";
		if (d==2) day="Tuesday";
		if (d==3) day="Wednesday";
		if (d==4) day="Thursday";
		if (d==5) day="Friday";
		if (d==6) day="Saturday";

		setTitle(day);//set the title
		javafx.scene.chart.XYChart.Series<String, Number> series=new XYChart.Series<String, Number>();//create new plot

		try//plot each datapoint from the data files
		{
			m-=30;
			if (m<0)
			{
				m+=60;
				h--;
				if (h<0)
				{
					h=23;
					d--;
					if (d<0) d=6;
				}
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

				if (m==0) head2="0";
				else head2="";

				if (h<10) head1="0";
				else head1="";

				if (i<0)//array looping/out-of-bounds prevention
				{
					i+=672;
					flag1=true;
				}
				if (i>671)//array looping/out-of-bounds prevention
				{
					i-=672;
					flag2=true;
				}
				series.getData().add(new XYChart.Data<String, Number>(head1 + h + ":" + head2 + m, hist[i]));
				if (flag1)//array looping/out-of-bounds prevention
				{
					i-=672;
					flag1=false;
				}
				if (flag2)//array looping/out-of-bounds prevention
				{
					i+=672;
					flag2=false;
				}

				if (89<ref&&ref<98) setTitle("Sunday/Monday");//set the title
				if (185<ref&&ref<194) setTitle("Monday/Tuesday");
				if (281<ref&&ref<290) setTitle("Tuesday/Wednesday");
				if (377<ref&&ref<386) setTitle("Wednesday/Thursday");
				if (473<ref&&ref<482) setTitle("Thursday/Friday");
				if (569<ref&&ref<578) setTitle("Friday/Saturday");
				if (665<ref||ref<2) setTitle("Saturday/Sunday");
				m+=15;//advance to the next datapoint
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			man.taDisplay.appendText("\nSomething went wrong with the dataplot!");
		}
		getData().add(series);
		
		return hist;
	}//end of method plot
}//end of class DataPlot