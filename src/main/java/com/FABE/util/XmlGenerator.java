package com.FABE.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XmlGenerator {
	static FileWriter fos;
	public static void main(String[] args) throws Exception
	{
		System.out.println("Arguments passed: "+args.length);
		createXML(args[0], args[1], args[2], args[3]);
	}

	public static void createXML(String browserTypes, String groups, String testType, String views) throws Exception {
		try {

			String[] browsers = browserTypes.split(",");
			String[] groupNames = groups.split(",");
			String[] view = views.split(",");

			System.out.println("Browsers: "+browsers.length);
			System.out.println("Groups: "+groupNames.length);
			System.out.println("Views: "+view.length);

			String outputFileName = System.getProperty("user.dir")+"\\testng.xml";

			fos = new FileWriter(outputFileName);
			fos.write("<?xml version='1.0' encoding='UTF-8'?>\n");
			fos.write("<!DOCTYPE suite SYSTEM 'http://testng.org/testng-1.0.dtd'>\n");
			fos.write("<suite name='VisualIVR scripts Execution' verbose='0' parallel='tests' thread-count='2'>\n\n");

			for(int a=0; a < view.length; a++)
			{
				for (int i = 0; i < browsers.length; i++) {
					String browser = browsers[i].trim(); 
					System.out.println("Browser: "+browser);

					String browserDetail[] = browser.split(" ");
					if (browserDetail[0].equalsIgnoreCase("firefox")|| browserDetail[0].equalsIgnoreCase("IE") || browserDetail[0].equalsIgnoreCase("Chrome")) {
						if (browserDetail[0].equalsIgnoreCase("firefox")) {
							fos.write("<test name=\"Firefox " + browserDetail[1] + " execution in "+view[a]+" view\">\n");
							fos.write("\t<parameter name=\"browser\" value=\"Firefox\" />\n");
						} else if (browserDetail[0].equalsIgnoreCase("IE")) {
							fos.write("<test name=\"IE " + browserDetail[1] + " execution in "+view[a]+" view\">\n");
							fos.write("\t<parameter name=\"browser\" value=\"IE\" />\n");
						} else {
							fos.write("<test name=\"Chrome " + browserDetail[1] + " execution in "+view[a]+" view\">\n");
							fos.write("\t<parameter name=\"browser\" value=\"Chrome\" />\n");
						}

						fos.write("\t<parameter name=\"browser_version\" value=\"" + browserDetail[1] + "\" />\n");
						fos.write("\t<parameter name=\"os\" value=\"Windows\" />\n");
						fos.write("\t<parameter name=\"os_version\" value=\"7\" />\n");

					}else if(browserDetail[0].equalsIgnoreCase("iPhone")) {
						fos.write("<test name=\"IPhone " + browserDetail[1] + " execution in "+view[a]+" view\">\n");
						fos.write("\t<parameter name=\"browser\" value=\"iPhone\" />\n");
						fos.write("\t<parameter name=\"browser_version\" value=\"" + browserDetail[1] + "\" />\n");

						if(browserDetail[1].equals("6")){
							fos.write("\t<parameter name=\"os\" value=\"iPhone 5\" />\n");
						}
						else if(browserDetail[1].equals("7")){
							fos.write("\t<parameter name=\"os\" value=\"iPhone 5s\" />\n");
						}
						fos.write("\t<parameter name=\"os_version\" value=\""+browserDetail[1]+"\" />\n");

					}else{
						fos.write("<test name=\"" + browser + " execution in "+view[a]+" view\">\n");
						fos.write("\t<parameter name=\"browser\" value=\"android\" />\n");
						fos.write("\t<parameter name=\"browser_version\" value=\"4.X\" />\n");
						fos.write("\t<parameter name=\"os\" value=\""+browser+"\" />\n");
						fos.write("\t<parameter name=\"os_version\" value=\"4.X\" />\n");
					}
					fos.write("\t<parameter name=\"view\" value=\"" + view[a] + "\" />\n\n");

					if(!groups.equalsIgnoreCase("All")){
						fos.write("\t<groups>\n");
						fos.write("\t<run>\n");

						for (int j = 0; j < groupNames.length; j++) {
							fos.write("\t\t<include name=\""+groupNames[j].trim()+"\" />\n");
						}

						fos.write("\t</run>\n");
						fos.write("\t</groups>\n\n");
					}

					fos.write("\t<packages>\n");

					if (testType.equalsIgnoreCase("Smoke Tests")) {
						fos.write("\t<package name=\"com.jacada.testScripts.smoke.*\" />\n");
					} 
					else if(testType.equalsIgnoreCase("Sanity Tests")){
						fos.write("\t<package name=\"com.jacada.testScripts.sanity.*\" />\n");
					} 
					else if(testType.equalsIgnoreCase("Regression Tests")){
						fos.write("\t<package name=\"com.jacada.testScripts.regression.*\" />\n");
					} 
					else if(testType.equalsIgnoreCase("Full Run")){
						fos.write("\t<package name=\"com.jacada.testScripts.*\" />\n");
					}
					fos.write("\t</packages>\n");
					fos.write("</test>\n\n");
				}

			}

			fos.write("</suite>");
			fos.flush();

			if (new File(outputFileName).exists()) {
				System.out.println("Test suite successfully generated in" + outputFileName);
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}


