package Sandbox;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


//Jeffrey Becker
class DataPlotTest extends LineChart<String,Number>
{
	int[] hist=new int[672];

	public DataPlotTest(String PlotX, String PlotY)
	{
		super(new CategoryAxis(), new NumberAxis(0, 24, 3));
		this.getXAxis().setLabel(PlotX);//label x-axis
		this.getYAxis().setLabel(PlotY);//label y-axis

		setLegendVisible(false);
	}//end of method DataPlotTest



	public int[] plot(int d, int h, int m, ManagerTest man, DataManagerTest dMan)
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
			if (m<15) m=0;
			else if (m<30) m=15;
			else if (m<45) m=30;
			else if (m<60) m=45;

			for (i=(ref); i<(ref+9); i++)
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

				if (87<ref&&ref<96) setTitle("Sunday/Monday");//set the title
				if (183<ref&&ref<192) setTitle("Monday/Tuesday");
				if (279<ref&&ref<288) setTitle("Tuesday/Wednesday");
				if (375<ref&&ref<384) setTitle("Wednesday/Thursday");
				if (471<ref&&ref<480) setTitle("Thursday/Friday");
				if (567<ref&&ref<576) setTitle("Friday/Saturday");
				if (663<ref||ref<0) setTitle("Saturday/Sunday");
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
}//end of class DataPlotTest