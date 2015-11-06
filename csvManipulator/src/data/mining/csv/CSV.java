package data.mining.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSV {

  public static void main(String[] args) {

	CSV obj = new CSV();
	obj.run();

  }

  public void run() {

//	String csvFile = "/home/markus/repositories/datamining/csvManipulator/data/risk_train.csv";
	String csvFile = "/home/markus/repositories/datamining/csvManipulator/data/risk_test.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	String output="";
	int index = 9;
	
	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {

		        // use comma as separator
			String[] data = line.split(cvsSplitBy);
			
			output = data[index];
			
			output = output.substring(output.lastIndexOf('.') + 1);
			//output = output.substring(0, output.length() - 1);
//			output = output.split("\\:")[0];	
//			int k = output.indexOf("-");
//						
//			if(k != -1)
//			{
//				// 2002-12-05
//				output = output.split("\\-")[0];
//			}
//			else{
//				// 12/28/2003
//				output = output.substring(output.lastIndexOf('/') + 1);
//			}

			System.out.println("?");

		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
  }

}
