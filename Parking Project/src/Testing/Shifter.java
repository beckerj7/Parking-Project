package Testing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Shifter
{
	public static void main(String args[])
	{
		int i;
		int[] hist=new int[672];
		String line;

		try
		{
			File file=new File("Real.txt"); //Takes in the hard coded data from over a weeks analysis
			BufferedWriter out=new BufferedWriter(new FileWriter("RealS.txt"));//create new file
			FileReader inputFile=new FileReader(file); //reads the data for the graph
			BufferedReader in=new BufferedReader(inputFile);
			line=in.readLine();//read line form file

			//loops through the data in order to create the official graph
			for (i=0; i<672; i++)
			{
				hist[i]=Integer.parseInt(line);//cast line to integer and save to array
				if (hist[i]==1) hist[i]=0;
				line=in.readLine();
			}
			in.close();//close reader
			
			for (i=0; i<672; i++) out.write(hist[i] + "\r\n");
			out.close();
		}
		catch (IOException IOE)
		{
			System.out.println("Something is wrong with the dataplot file I/O!");//error message
			IOE.printStackTrace();
		}
	}
}
