package com.FABE.sqlqueries;

import com.FABE.util.DBConnection;

public class SQL_Queries {

	// This query gives the Company ID
	public static String getCompanyID(String strEmpID) throws Throwable {
		String sqlQuery = "select compID from dbo.tblPerson where EmpID='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}
	
	
	// This query gives the Company ID
	public static String getCompanyName(String strEmpID) throws Throwable {
		String sqlQuery = "select compID from dbo.tblPerson where EmpID='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}
	
	// This query gives the Employee ID
	public static String getEmployeeID(String strEmpName) throws Throwable {
		String sqlQuery = "select empid from dbo.tblperson where empName='"+strEmpName+"'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getSuppPay() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getStdHours() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getWorkState() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getCurrYearPTO() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getAccruedPTO() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getPTORecorded() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getLastTimeCard() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getTermDate() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getAddlPTO() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getPTOHoursDue() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getHourlyRate() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getPaidThruDate() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getPayGroup() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Supplement Pay
	public static String getCreatedDate() throws Throwable {
		String sqlQuery = "Select * from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}
	
	// This query gives the State Code
	public static String getStateCode(String strEmpID) throws Throwable {
		String sqlQuery = "Select stcode from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}
	// This query gives the Pay Type
	public static String getPayType() throws Throwable {
		String sqlQuery = "Select stcode from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Status
	public static String getStatus() throws Throwable {
		String sqlQuery = "Select stcode from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}

	// This query gives the Status
	public static String getPTOGrant() throws Throwable {
		String sqlQuery = "Select stcode from table where empid='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}
	
	// This query gives the Company ID
	public static String getEligibilityDate(String strEmpID) throws Throwable {
		String sqlQuery = "select compID from dbo.tblPerson where EmpID='strEmpID'";
		return DBConnection.executeSQLQuery(sqlQuery);
	}
	
}
