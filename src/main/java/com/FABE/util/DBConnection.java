package com.FABE.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.FABE.accelerators.TestEngine;

public class DBConnection {

	private static String dbusername;
	private static String dbpassword;

	// Should be defined as jdbc:mysql://host:port/database name
	private static String databaseURLQA = "jdbc:sqlserver://10.235.80.20:1433;DatabaseName=etcd";
	private static String databaseURLPTPP = "jdbc:mysql://stagehost:2020/easyDB";

	public static String executeSQLQuery(String sqlQuery) {
		String connectionUrl = "";
		Connection connection=null;
		String resultValue = "";
		ResultSet rs;

		// To connect with PTRP Environment Database
		if (TestEngine.environment.equalsIgnoreCase("QA")) {
			connectionUrl = databaseURLQA;
			dbusername = "ETCDTEST.SQL";
			dbpassword = "VYE86543t";
		}
		// To connect with PTPP Environment Database
		else if (TestEngine.environment.equalsIgnoreCase("PTPP")) {
			connectionUrl = databaseURLPTPP;
			dbusername = "root";
			dbpassword = "stagepassword";
		}

		try {

			// Register the driver class
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

			// Creating connection
			connection = DriverManager.getConnection(connectionUrl, dbusername,dbpassword);

			if (connection != null) {
				System.out.println("Connected to the database...");
			} else {
				System.out.println("Database connection failed to" + TestEngine.environment+ "Environment");
			}

			// Creating statement
			Statement stmt = connection.createStatement();

			// Executing queries
			rs = stmt.executeQuery(sqlQuery);

			try {
				while(rs.next()){
					resultValue = rs.getString(1).toString();	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException err) {
				System.out.println("No Records obtained for this specific query");
				err.printStackTrace();
			}
			connection.close();
		} catch (SQLException sqlEx) {
			System.out.println("SQL Exception:" + sqlEx.getStackTrace());
		}
		return resultValue;
	}
	
}
