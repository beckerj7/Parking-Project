package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataManager
{
	public int[] Read()
	{
		int i;
		int Hist[]=new int[672];
		String line;
		
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
		return Hist;
	}//end of method Read
}