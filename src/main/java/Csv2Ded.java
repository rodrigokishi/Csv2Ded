import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.opencsv.CSVReader;


public class Csv2Ded 
{
	//Usage: <in: csv file> <out: ded txt file>
	public static void main(String[] args) throws Exception 
	{
		CSVReader reader = new CSVReader(new FileReader(args[0]), ',');
		FileWriter sceneWriter = new FileWriter(args[1]);
		List<String[]> lines = reader.readAll();
		for(int j = 0; j < lines.size(); j++)
		{	
			String [] line = lines.get(j);
			String num1 = line[0];
			String num2 = line[1];
			num1 = num1.replaceAll(" ", "");
			num2 = num2.replaceAll(" ", "");
			/*MKLab DED format consists of 0's for all shots, except ones that are last shots of a scene, 
			which, in this case are marked as 1's.
			*/
			for(int i = Integer.parseInt(num1); i < Integer.parseInt(num2); i++ )
			{
				sceneWriter.write("0" + "\r\n");
			}			
			sceneWriter.write("1");
			
			//Prevents blank lines after the end of data
			if( (j + 1) < lines.size())
			{
				sceneWriter.write("\r\n");
			}
		}
		reader.close();
		sceneWriter.close();

	}

}
