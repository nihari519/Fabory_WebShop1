package com.FABE.util;  

public class Constants {
	  static String userHome=	System.getProperty("user.home");
	static   String custumRulesfile= userHome+
			   "\\My Documents"
			   +"\\Fiddler2"
			   +"\\Scripts"
			   +"\\CustomRules.js";
	public static   String FiddlerCapturePath=userHome+
			   "\\My Documents"
			   +"\\Fiddler2"+
			   "\\Captures";
	static String ext=".saz";
	//static String fiddlerExePath="C:\\Program Files (x86)\\Fiddler2\\Fiddler.exe";
	static String fiddlerExePath="C:\\Program Files\\Fiddler2\\Fiddler.exe";
	
public 	static String errMsg="Hi,<br><br>Blinkx.com is not accessible.Temporarily the page is down.Please find the attached failure screenshot."
			+"<br>URL to reach the Page is:<a href='>currentURL'>Click here</a><br>";
}
