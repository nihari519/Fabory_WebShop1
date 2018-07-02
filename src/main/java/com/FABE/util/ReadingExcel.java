package com.FABE.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.FABE.support.ConfiguratorSupport;


public class ReadingExcel {
	
	public static ConfiguratorSupport configProps = new ConfiguratorSupport(
			"config.properties");
	
	public static String testDataFolderPath = configProps.getProperty("TestData");

	//Pass parameters to method columnData 
	//input column index as parameter 1
	//input sheet index value(index starts with 0) sheet1=0(sheet index), sheet2=1(sheet index) .... so on...
	//input the path of the excel file (in xlsx format)
	public ArrayList<String> columnData(int colindex,String sheetName)
	{
		ArrayList<String> columndata = null;
		try {
			File f = new File(testDataFolderPath);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			//XSSFSheet sheet = workbook.getSheetAt(sheetindex);
			Iterator<Row> rowIterator = sheet.iterator();
			columndata = new ArrayList<String>();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if(row.getRowNum() > 0){ //To filter column headings
						if(cell.getColumnIndex() == colindex){// To match column index
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								columndata.add(cell.getNumericCellValue()+"");
								break;
							case Cell.CELL_TYPE_STRING:
								columndata.add(cell.getStringCellValue());
								break;
							}
						}
					}
				}
			}
			ios.close();
			//System.out.println(columndata);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//returns the complete column data
		return columndata;

	}

	public ArrayList<String> rowData(int rowIndex, int endColIndex,int sheetIndex,String path){
		ArrayList<String> rowData = new ArrayList<String>();
		try {
			File f = new File(path);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			for(int colStartPosition=0; colStartPosition<=endColIndex; colStartPosition++){
				Cell cell = sheet.getRow(rowIndex).getCell(colStartPosition);

				switch(cell.getCellType()) {
				case Cell.CELL_TYPE_STRING: 
					rowData.add(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:  
					rowData.add(cell.getNumericCellValue()+"");
					break;
				}
			}

			ios.close();   
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowData;
	}

	/** Retrieve the data present in the excel cell.
	 * 
	 * @param rowNumber
	 * @param columnNumber
	 * @param path
	 * @param sheetIndex
	 * @return
	 */
	public static String getCell(int rowNumber,int columnNumber,String path, String sheetName){

		String cellData = null;
		try {
			File f = new File(path);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet=workbook.getSheet(sheetName);
			//XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			Cell cell = sheet.getRow(rowNumber).getCell(columnNumber);

			switch(cell.getCellType()) {
			case Cell.CELL_TYPE_STRING: 
				cellData = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:  
				cellData = ""+cell.getNumericCellValue();
				break;
			}
		}catch (Exception e) {
			System.out.println("Exception occured while reading the cell data "+e);
			e.printStackTrace();
		}

		return cellData;
	}

	
	
	/** Update the Numeric Value present in the String.
	 *   
	 * @param stringValue
	 * @return - Returns updated numeric value by 1, in String.
	 */
	private static String incrementStringValueByOne(String stringValue){

		String sUpdatedString = null;
		try{
			String strValue = stringValue;
			String firstNumber = strValue.replaceFirst(".*?(\\d+).*", "$1");
			sUpdatedString = strValue.replaceAll(firstNumber, ""+(Integer.parseInt(firstNumber)+1));
		}catch (Exception e) {
			System.out.println("Unable to update the String '"+stringValue+"'. "+e);
			e.printStackTrace();
		}

		return sUpdatedString;
	}

	/** Updates the Value present in the excel cell.
	 * 
	 * @param rowNumber
	 * @param columnNumber
	 * @param path
	 * @param sheetIndex
	 * @param cellValueToUpdate
	 */
	private static void updateCell(int rowNumber,int columnNumber,String path, String sheetName, String cellValueToUpdate){
		try {
			File f = new File(path);
			FileInputStream ios = new FileInputStream(f);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			//XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

			Cell cell = sheet.getRow(rowNumber).getCell(columnNumber);
			XSSFRow row  = sheet.getRow(rowNumber);

			cell = row.getCell(columnNumber, Row.RETURN_BLANK_AS_NULL);

			if (cell != null) {
				//cell = row.createCell(0);
				cell.setCellValue(cellValueToUpdate);
			} else {
				throw new Exception("CELL is blank.");
			}

			FileOutputStream fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		}catch (Exception e) {
			System.out.println("Unable to update the cell. "+e);
			e.printStackTrace();
		}
	}

	/** Fetche's the test data from the excel file, increment's the test data value by 1 and 
	 * writes the updated test data value into excel file.
	 * 
	 * @param rowNumber
	 * @param columnNumber
	 * @param excelFilePath
	 * @param sheetIndex
	 */
	public static void updateCellValue(int rowNumber, int columnNumber, String excelFilePath, String sheetName){
		String cellValue = null;
		String updateCellValue = null;

		//Fetche's value present in the cell based on row & column number.
		cellValue = getCell(rowNumber, columnNumber,excelFilePath,sheetName);

		//Increment the fetched excel value by 1 
		updateCellValue = incrementStringValueByOne(cellValue);

		//Update the cell value in to excell sheet, after incrementing value present in the cell.
		updateCell(rowNumber,columnNumber,excelFilePath, sheetName, updateCellValue);
	}


	/** Retrieves the absolute file path.
	 *
	 * @param relativePath
	 * @return
	 * @throws IOException
	 */
	public static String getAbsoulteFilePath(String relativePath) throws IOException{
		String absolutePath = new File("").getAbsolutePath()+relativePath;
		return absolutePath;
	}


	/** Updates the Value present in the excel cell by using the sheetName.
     * 
      * @param rowNumber
     * @param columnNumber
     * @param path
     * @param sheetIndex
     * @param cellValueToUpdate
     */
     public static void updateCellInSheet(int rowNumber,int columnNumber,String path, String sheetName, String cellValueToUpdate){
            try {
                   File f = new File(path);
                   FileInputStream ios = new FileInputStream(f);
                   XSSFWorkbook workbook = new XSSFWorkbook(ios);
                   XSSFSheet sheet = workbook.getSheet(sheetName);

                   Cell cell = sheet.getRow(rowNumber).getCell(columnNumber);
                   XSSFRow row  = sheet.getRow(rowNumber);

                   cell = row.getCell(columnNumber, Row.RETURN_BLANK_AS_NULL);

                   if (cell != null) {
                         //cell = row.createCell(0);
                         cell.setCellValue(cellValueToUpdate);
                   } else {
                         throw new Exception("CELL is blank.");
                   }

                   FileOutputStream fileOut = new FileOutputStream(path);
                   workbook.write(fileOut);
                   fileOut.flush();
                   fileOut.close();

            }catch (Exception e) {
                   System.out.println("Unable to update the cell. "+e);
                   e.printStackTrace();
            }
     }

   
	public static ArrayList<String> columnDataBasedOnSheetName(int colindex,String sheetName,String path)
	{
		ArrayList<String> columndata = null;
		try {
			File f = new File(path);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Iterator<Row> rowIterator = sheet.iterator();
			columndata = new ArrayList<String>();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if(row.getRowNum() > 0){ //To filter column headings
						if(cell.getColumnIndex() == colindex){// To match column index

							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								columndata.add(cell.getNumericCellValue()+"");
								break;
							case Cell.CELL_TYPE_STRING:
								columndata.add(cell.getStringCellValue());
								break;
							}
						}
					}
				}
			}
			ios.close();
			//System.out.println(columndata);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//returns the complete column data
		return columndata;

	}

	public static String columnDataByHeaderName(String colHeader, String sheetName, String excelFile)
	{
		String cellData = null;
		
		try {
			File f = new File(testDataFolderPath+excelFile+".xlsx");
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Iterator<Row> rowIterator = sheet.iterator();
			
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String finalcell=""+cell;
					int colnum=cell.getColumnIndex();
					//cell.getRichStringCellValue().equals(colHeader);
					//cell.getRichStringCellValue().getString().equalsIgnoreCase(colHeader)
					
					if(finalcell.equalsIgnoreCase(colHeader)){// To match column value
						
						//cellData=sheet.getRow(1).getCell(colnum).toString();
						
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							//cellData=""+sheet.getRow(1).getCell(colnum).getNumericCellValue();
							cellData=sheet.getRow(1).getCell(colnum).toString();
							//columndata.add(cell.getNumericCellValue()+"");
							break;
						case Cell.CELL_TYPE_STRING:
							cellData=sheet.getRow(1).getCell(colnum).toString();
							//columndata.add(cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_BLANK:
							try{
								System.out.println("Blank space");
							}catch (Exception e) {
								e.printStackTrace();
							}
							
							
							}
						}
					}
				}
			
			ios.close();
			//System.out.println(columndata);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//returns the complete column data
		return cellData;		
	}
	
	/** Remove used VST codes.
	 * 
	 * @param path
	 * @param sheetIndex
	 */
	public static boolean removeUsedVSTCode(String path, String sheetName){
		boolean flag= false;
		try {
		   File f = new File(path);
           FileInputStream ios = new FileInputStream(f);
           XSSFWorkbook workbook = new XSSFWorkbook(ios);
          // XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
           XSSFSheet sheet = workbook.getSheet(sheetName);

           int firstRowNum=1;
           int lastRowNum = sheet.getLastRowNum();
           if(lastRowNum >= firstRowNum){
        	   sheet.shiftRows(firstRowNum, lastRowNum, -1);  
        	   flag = true;
           }else{
        	   try{
        		   sheet.removeRow(sheet.getRow(lastRowNum));
        		   flag = true;
        	   }catch (Exception e) {
        		   System.out.println("No more VST codes available. "+e);
        		   e.printStackTrace();
        		   flag = false;
        	   }
    	  }
    	   
           FileOutputStream fileOut = new FileOutputStream(path);
           workbook.write(fileOut);
           fileOut.flush();
           fileOut.close();
	           
		}catch (Exception e) {
			System.out.println("Unable to update the cells. "+e);
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}

}

