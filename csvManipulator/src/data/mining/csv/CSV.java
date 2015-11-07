package data.mining.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSV {

	public static void main(String[] args) throws IOException {

		CSV obj = new CSV();
		obj.run();

	}

	public void run() throws IOException {

		boolean useMethod = true;

		// String csvFile =
		// "/home/markus/repositories/datamining/csvManipulator/data/risk_train.csv";
		String csvFile = "/home/markus/repositories/csvManipulator/data/risk_train.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String output = "";
		int index = 11;

		/*
		 * Since this more a playground to manipulate csv files the structure is
		 * pretty hard to determine (sorry for that). anyways, easy processing
		 * is done in the 'else' part. For 'heavier' processing I started (I
		 * think too late) to define dedicated methods
		 */
		br = new BufferedReader(new FileReader(csvFile));
		if (useMethod) {
			
			normalizer(br, index, cvsSplitBy, line, 0 ,10);
			//detectOutliers(br, index, cvsSplitBy, line, 1000);
		} else {

			try {
				while ((line = br.readLine()) != null) {

					// use comma as separator
					String[] data = line.split(cvsSplitBy);

					output = data[index];

					output = output.substring(output.lastIndexOf('.') + 1);
					output = output.substring(0, output.length() - 1);
					output = output.split("\\:")[0];
					int k = output.indexOf("-");

					if (k != -1) {
						// 2002-12-05
						output = output.split("\\-")[0];
					} else {
						// 12/28/2003
						output = output.substring(output.lastIndexOf('/') + 1);
					}

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

	
	/** outputs the index of a row which is higher than outlierMax */
	public void normalizer(BufferedReader br, int index, String csvSplit, String line, double min, double max)
			throws IOException {
		double output = 0;
		int counter = 1;
		while ((line = br.readLine()) != null) {
			counter++;
			String[] data = line.split(csvSplit);
			output = Double.parseDouble(data[index]);
			output = normalizeFeaturesSCaling(output, min, max);
			System.out.println(output);
		}
	}
	
	
	/** normalizes a specific column */
	public double normalizeFeaturesSCaling(double val, double min, double max)
	{
		double result = -1;
		
		result = (val - min) / (max - min);
		
		return result;
	}
	
	/** outputs the index of a row which is higher than outlierMax */
	public void detectOutliers(BufferedReader br, int index, String csvSplit, String line, int outlierMax) throws IOException {
		double output = 0;
		int counter = 1;
		System.out.println("Row" + " \t-\t " + "Value");
		while ((line = br.readLine()) != null) {
			counter++;
			String[] data = line.split(csvSplit);
			output = Double.parseDouble(data[index]);
			
			if(output >= 800)
			{
				System.out.println(counter + " \t-\t " + output);
			}
		}
	}

}
