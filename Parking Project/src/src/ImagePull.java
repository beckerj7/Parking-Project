package src;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


public class ImagePull
{
	public static String main(String[] args) throws Exception
	{
		int i=0;//counter variable
		boolean check;//file existence variable


		String imageUrl="http://construction1.db.erau.edu/jpg/1/image.jpg";

		i=0;

		do//avoid overwriting existing images
		{
			i++;
			check=new File("image" + i + ".jpg").exists();
		}while (check==true);

		String destinationFile="image" + i + ".jpg";//set image destination

		try {saveImage(imageUrl, destinationFile);}//download and save image
		catch (IOException e1)//catch file I/O exceptions
		{
			System.out.println("Something went wrong with the file I/O!");//error notification message
			e1.printStackTrace();
		}
		
		return destinationFile;//return image location
	}


	public static void saveImage(String imageUrl, String destinationFile) throws IOException
	{
		URL url=new URL(imageUrl);
		InputStream is=url.openStream();
		OutputStream os=new FileOutputStream(destinationFile);

		byte[] b=new byte[2048];
		int length;

		while ((length=is.read(b))!=-1) os.write(b, 0, length);

		is.close();
		os.close();
	}


	public java.lang.String main() {
		// TODO Auto-generated method stub
		return null;
	}
}