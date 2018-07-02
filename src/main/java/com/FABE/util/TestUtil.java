package com.FABE.util;



import java.util.Hashtable;

public class TestUtil {
	// true - Y
	// false - N
	public static boolean isTestCaseExecutable(String testCase, Xls_Reader xls){
		
		for(int rNum=2;rNum<=xls.getRowCount("Test Cases");rNum++){
			if(testCase.equals(xls.getCellData("Test Cases", "TCID", rNum))){
				// check runmode
				if(xls.getCellData("Test Cases", "Runmode", rNum).equals("Y"))
					return true;
				else
					return false;
			}
				
		}
		
		return false;
		
	}
	
	
	public static Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"/Data/TestData.xlsx");
	synchronized public static Hashtable<String, String> getData(String testCase, String sheetName){
		System.out.println("*************");
		// find the test in xls
		// find number of cols in test
		// number of rows in test
		// put the data in hashtable and put hashtable in object array
		// return object array
		
		int testCaseStartRowNum=0;
		for(int rNum=1;rNum<=xls.getRowCount(sheetName);rNum++){
			if(testCase.equals(xls.getCellData(sheetName, 1, rNum))){
				testCaseStartRowNum = rNum;
				break;
			}
		}
		System.out.println("Test Starts from row -> "+ testCaseStartRowNum);
		
	
		
		int cols=12;
		Hashtable<String,String> table=null;
		
		// print the test data
		
		table=new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				table.put(xls.getCellData(sheetName, cNum, 1),xls.getCellData(sheetName, cNum, testCaseStartRowNum));
				System.out.print(xls.getCellData(sheetName, cNum, testCaseStartRowNum)+" - ");
			}
			

		return table;// dummy
		
		
		
		
	}
	

	
	

}
