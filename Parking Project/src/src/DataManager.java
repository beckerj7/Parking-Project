package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;


//Jeffrey Becker
public class DataManager extends TimerTask
{
	/**Reads the historical data file into a single array for ease of access.
	 * @return An array containing the ordered historical data.
	 */
	int last=-1;
	int hist[]=new int[672];

	public int[] read()
	{
		int i;
		String line;

		try
		{
			File file=new File("RealS.txt"); //Takes in the hard coded data from over a weeks analysis
			FileReader inputFile=new FileReader(file); //reads the data for the graph
			BufferedReader in=new BufferedReader(inputFile);
			line=in.readLine();//read line form file

			//loops through the data in order to create the official graph
			for (i=0; i<672; i++)
			{
				hist[i]=Integer.parseInt(line);//cast line to integer and save to array
				line=in.readLine();
			}
			in.close();//close reader
		}
		catch (IOException IOE)
		{
			System.out.println("Something is wrong with the dataplot file I/O!");//error message
			IOE.printStackTrace();
		}
		return hist;
	}//end of method Read



	/**Overwrites the historical data file with updated information given the array of data, a position in the array, and a new value.
	 * @param hist
	 * @param ref
	 * @param update
	 * @return Updated data array
	 * @throws IOException
	 */
	public int[] overwrite(int[] hist, int ref, int update) throws IOException
	{
		int i;

		BufferedWriter out=new BufferedWriter(new FileWriter("RealS.txt"));//create new file

		hist[ref]=update;

		for (i=0; i<672; i++) out.write(hist[i] + "\r\n");

		out.close();

		return hist;
	}//end of method Overwrite



	/**Downloads the current camera image to local storage as a .jpg file.
	 * Does not overwrite previously saved images in the same directory.
	 * @return The location of the image on disk.
	 * @throws Exception
	 */
	public String imagePull() throws Exception
	{
		int i=0;//counter variable
		boolean check;//file existence variable

		String imageURL="http://construction1.db.erau.edu/jpg/1/image.jpg";//url where image will be taken from

		//avoid overwriting existing images
		do
		{
			i++;
			check=new File("image" + i + ".jpg").exists();
		}while (check==true);

		String destinationFile="image" + i + ".jpg";//set image destination

		try
		{
			//Deron Eriksson
			/*****************************************************************/
			/* Copyright 2013 Code Strategies                                */
			/* This code may be freely used and distributed in any project.  */
			/* However, please do not remove this credit if you publish this */
			/* code in paper or electronic form, such as on a web site.      */
			/*****************************************************************/
			int length;
			URL url=new URL(imageURL); //object for image url
			InputStream is=url.openStream();
			OutputStream os=new FileOutputStream(destinationFile); //object for image destination

			byte[] b=new byte[2048];

			while ((length=is.read(b))!=-1) os.write(b, 0, length);

			is.close();
			os.close();
		}//Adapted from http://www.avajava.com/tutorials/lessons/how-do-i-save-an-image-from-a-url-to-a-file.html, by Deron Eriksson
		catch (IOException e1)//catch file I/O exceptions
		{
			System.out.println("Something went wrong with the file I/O!");//error message
			e1.printStackTrace();
		}

		return destinationFile;//return image location
	}//end of method ImagePull



	public int predict(Manager man, int d, int h, int m)
	{
		int wait;
		int c=1;
		int ref=d*96+h*4+m/15+1;
		int start=d*96+h*4+m/15;

		while (hist[ref]==0&&ref!=start)
		{
			System.out.println(hist[ref]);
			c++;
			ref++;
			if (ref==672) ref=0;
		}

		if (ref==start)
		{
			System.out.println("Zero open spots projected for the forseeable future.");
			wait=Integer.MAX_VALUE;
		}
		else wait=c*15-m%15;
		
		return wait;
	}


	@Override
	//Date and time
	//authors: Brandon Koury & Jeffrey Becker
	public void run()
	{
		int d=0;
		int ref;
		String destination=null;

		Date dAndT;
		String sDate;
		String sHour;
		String sMinute;
		SimpleDateFormat dayOfWeek;
		SimpleDateFormat hour;
		SimpleDateFormat minute;
		int iHour;
		int iMinute;

		dAndT = new Date( );
		dayOfWeek = new SimpleDateFormat ("E");//acquire day
		sDate = dayOfWeek.format(dAndT);//cast to string
		hour = new SimpleDateFormat ("kk");//acquire hour
		sHour = hour.format(dAndT);//cast to string
		minute = new SimpleDateFormat ("mm");//acquire minute
		sMinute = minute.format(dAndT);//cast to string
		iHour=Integer.parseInt(sHour);//cast to integer
		iMinute=Integer.parseInt(sMinute);//cast to integer

		if (iHour==24) sHour=String.valueOf(iHour=0);//preserve arithmetic logic

		switch (sDate)//cast day to a representative number
		{
		case "Sun": d = 0;
		case "Mon": d = 1;
		case "Tue": d = 2;
		case "Wed": d = 3;
		case "Thu": d = 4;
		case "Fri": d = 5;
		case "Sat": d = 6;
		break;
		default: System.out.println("Something went wrong with the date switch statement!");
		}

		ref=d*96+iHour*4+iMinute/15;

		if ((iMinute==0||iMinute==15||iMinute==30||iMinute==45)&&iMinute!=last)
		{
			try
			{
				destination=imagePull();
				last=iMinute;
				System.out.println("Saved image:\t" + destination);

				System.out.println(overwrite(hist, ref, AvailableSpotsEach.compare(destination)));
			}
			catch (Exception e) {e.printStackTrace();}
		}
	}//end of method run
}//end of class DataManager