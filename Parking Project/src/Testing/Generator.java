package Testing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {
	public static void main(String args[])
	{
		int j;
		int max=22;
		int min=0;
		int rand;
		BufferedWriter out;


			try 
			{
				out=new BufferedWriter(new FileWriter("Dummy.txt"));
				for (j=0; j<672; j++)
				{
					rand=min+(int)(Math.random()*max);
					out.write(rand + "\r\n");
					System.out.println(j + "\t" + rand);
				}
				out.close();
			}
			catch (IOException e) {e.printStackTrace();}
		}
}