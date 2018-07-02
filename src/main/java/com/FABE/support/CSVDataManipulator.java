package com.FABE.support;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVDataManipulator {
	/**
	 * Returns an Array of ArrayList (String type) with all the CSV data of
	 * proper size of m x n. Each columns is represented in an ArrayList with
	 * first column represented by ArrayList[0], second one with ArrayList[1],
	 * and so on...
	 * 
	 * @param filePath
	 *            : FileName along with its path to read data from.
	 * @return ArrayList<String>[] inputData.
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String>[] getAllRecordsAsArrayList(String filePath)
			throws IOException {

		BufferedReader br = null;
		ArrayList<String>[] inputData = null;
		String row;
		String[] cellValues;
		int n = 0;

		try {
			br = new BufferedReader(new FileReader(filePath));
			row = br.readLine();
			if (row != null) {
				cellValues = row.split(","); // cellValues holds the all fields
												// of first row which is used to
												// represent the number of
												// columns
				n = cellValues.length; // Number of Columns.
				System.out.println(n);
				inputData = new ArrayList[n]; // we are initializing the Array
												// (of ArrayList) with size n.
				for (int i = 0; i < n; i++)
					inputData[i] = new ArrayList<String>();
			}

			while (row != null) {
				if (row.contains(",")) {
					cellValues = row.split(",");
					if (cellValues.length == n || cellValues.length > n) {
						// If the number of fields in the row equals or greater
						// than num of columns. If ignore the extra fields.
						for (int i = 0; i < n; i++) {
							if (!cellValues[i].isEmpty()) // If any cell value
															// is empty
								inputData[i].add(cellValues[i]); // Sending the
																	// each cell
																	// value of
																	// a given
																	// row to
																	// all
																	// ArrayLists.
							else
								inputData[i].add(""); // If any cell value in
														// the middle of a given
														// row is empty then
														// fill with empty
														// string.
						}
					} else { // If last cell value is missing in the given row,
								// then we fill it with empty string.
						for (int i = 0; i < n - 1; i++) {
							if (!cellValues[i].isEmpty())
								inputData[i].add(cellValues[i]); // Sending the
																	// each cell
																	// value of
																	// a given
																	// row to
																	// all
																	// ArrayLists.
							else
								inputData[i].add(""); // If any cell value in
														// the middle of a given
														// row is empty then
														// fill with empty
														// string.
						}
						inputData[n - 1].add("");
					}
				} else if (row.isEmpty()) {
					for (int i = 0; i < n; i++)
						inputData[i].add(""); // If entire row is empty (any
												// line breaks) then fill the
												// cells with empty string
				} else {
					for (int i = 0; i < n; i++)
						if (i == 0)
							inputData[i].add(row); // If any row lacks commas
													// then send the entire row
													// to first field.
						else
							inputData[i].add(""); // And fill remaining fields
													// with empty strings.
				}

				row = br.readLine();
			}

			return inputData;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return inputData;
		} catch (Exception e) {
			e.printStackTrace();
			return inputData;
		} finally {
			br.close();
		}
	}

	/**
	 * Returns a 2 Dimensional String Array with all the CSV data of proper size
	 * of m x n. Each columns is represented in an ArrayList with first column
	 * represented by ArrayList[0], second one with ArrayList[1], and so on...
	 * 
	 * @param filePath
	 *            : FileName along with its path.
	 * @return
	 * @throws IOException
	 */
	public String[][] getAllRecordsAs2DArray(String filePath)
			throws IOException {
		return getAllRecordsAs2DArray(filePath, true);
	}

	/**
	 * Returns a 2 Dimensional String Array with all the CSV data of proper size
	 * of m x n. Each columns is represented in an ArrayList with first column
	 * represented by ArrayList[0], second one with ArrayList[1], and so on...
	 * 
	 * @param filePath
	 *            : FileName along with its path to read data from.
	 * @param ignoreHeaders
	 *            : True by default, if false then it reads all data including
	 *            headers.
	 * @return
	 * @throws IOException
	 */
	public String[][] getAllRecordsAs2DArray(String filePath,
			boolean ignoreHeaders) throws IOException {

		String[][] data = null;
		try {
			ArrayList<String>[] inputData = new CSVDataManipulator()
					.getAllRecordsAsArrayList(filePath);
			int cols = inputData.length;
			int rows = inputData[0].size();
			data = new String[rows][cols];
			int i;
			if (ignoreHeaders)
				i = 1;
			else
				i = 0;
			for (; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					data[i][j] = inputData[j].get(i);
					System.out.print(data[i][j] + "|");
				}
				System.out.println();
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return data;
		}
	}

	/**
	 * Reads the line at specified line Number and returns an array with cell
	 * values.
	 * 
	 * @param lineNumber
	 *            : An integer representing the line number.
	 * @param filePath
	 *            : : FileName along with its path to read data from.
	 * @return
	 * @throws IOException
	 */
	public String[] readSpecifiedLine(int lineNumber, String filePath)
			throws IOException {
		BufferedReader br = null;
		String row;
		String[] cellValues = null;
		int i = 0;
		try {
			br = new BufferedReader(new FileReader(filePath));
			row = br.readLine();

			while (row != null) {
				i++;
				if (i == lineNumber) {
					if (row.contains(",")) {
						cellValues = row.split(","); // cellValues holds the all
														// fields of first row
														// which is used to
														// represent the number
														// of columns
					} else if (row.isEmpty())
						cellValues = new String[] { "" };
					else
						cellValues = new String[] { row };
					break;
				}
				row = br.readLine();
			}
			if (lineNumber > i) {
				System.out.println("No Such line number exists");
				cellValues = new String[] { "" };
			}
			return cellValues;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return cellValues;
		} catch (Exception e) {
			e.printStackTrace();
			return cellValues;
		} finally {
			br.close();
		}
	}

	/**
	 * Writes the data to a csv file with data passed by an Array of ArrayList
	 * parameter .
	 * 
	 * @param data
	 *            : An Array of ArrayList parameter representing each column in
	 *            a ArrayList.
	 * @param filePath
	 *            : FileName along with its path to write data to.
	 * @return
	 * @throws IOException
	 */

	public boolean writeData(ArrayList<String>[] data, String filePath)
			throws IOException {

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filePath));
			for (int rowNum = 0; rowNum < data[0].size(); rowNum++) {
				String row = "";
				for (int j = 0; j < data.length; j++) {
					row = row + data[j].get(rowNum); // Concatenating all fields
														// of a record to a row
					if (j != data.length - 1) // Dont concatenate the symbol ','
												// (comma) to the last element.
						row = row + ",";
				}
				bw.write(row);
				if (rowNum != data[0].size() - 1) { // Go to next line if its
													// not the last row.
					bw.newLine();
				}
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			bw.close();
		}
	}

	/**
	 * Appends the data to a csv file with data passed by an Array of ArrayList
	 * parameter .
	 * 
	 * @param data
	 *            : An Array of ArrayList parameter representing each column in
	 *            a ArrayList.
	 * @param filePath
	 *            : FileName along with its path to write data to.
	 * @return
	 * @throws IOException
	 */
	public boolean appendData(ArrayList<String>[] data, String filePath)
			throws IOException {

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filePath, true));
			for (int rowNum = 0; rowNum < data[0].size(); rowNum++) {
				String row = "";
				for (int j = 0; j < data.length; j++) {
					row = row + data[j].get(rowNum); // Concatenating all fields
														// of a record to a row
					if (j != data.length - 1) // Dont concatenate the symbol ','
												// (comma) to the last element.
						row = row + ",";
				}
				if (rowNum != data[0].size() - 1) { // Go to next line if its
													// not the last row.
					bw.newLine(); // For appending, A line break should come
									// first.
				}
				bw.write(row);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			bw.close();
		}
	}

}
