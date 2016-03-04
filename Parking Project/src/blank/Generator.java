package blank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generator {
	public static void main(String args[])
	{
		int i;
		int j;
		int f;
		int max=22;
		int min=0;
		int rand;
		Random rn = new Random();
		int n=max-min+1;
		BufferedWriter out;

		for (i=0; i<7; i++)
		{
			try 
			{
				out=new BufferedWriter(new FileWriter("Day" + i + " Dummy.txt"));
				for (j=0; j<96; j++)
				{
					f=rn.nextInt()%n;
					rand=min+(int)(Math.random()*max);
					out.write(rand + "\r\n");
					System.out.println(j + "\t" + rand);
				}
				out.close();
			}
			catch (IOException e) {e.printStackTrace();}
		}
	}
}