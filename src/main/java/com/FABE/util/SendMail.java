package com.FABE.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.FABE.support.ConfiguratorSupport;
import com.FABE.support.HtmlReportSupport;

import java.util.*;  

import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;

public class SendMail extends HtmlReportSupport {
	 
	  
	static ConfiguratorSupport config = new ConfiguratorSupport(
			"config.properties");

	public static void attachReportsToEmail(String zip) throws Exception {
		String strBrowser = config.getProperty("browserType");
		String strReportsFolder = "";

		if (strBrowser.equalsIgnoreCase("ie")) {
			strReportsFolder = "IE";

		} else if (strBrowser.equalsIgnoreCase("firefox")) {
			strReportsFolder = "Firefox";

		} else {
			strReportsFolder = "Chrome";

		}
		Zip.zipFolder(zip, zip+"Automation.zip");
		/*String strZipFilePath = "Results\\"
				+ strReportsFolder
				+ "Results/HTML/Screenshots/".split(strReportsFolder)[2].replace(
						"\\", "") + ".Zip";
						String strZipFilePath = "Results\\"
								+ "HTML19_Jun_2015_01_49_06_277"
								+ "Results/HTML/Screenshots/".split(strReportsFolder)[2].replace(
										"\\", "") + ".Zip";
		Zip.zipFolder("Results/HTML/Screenshots/", strZipFilePath);
		Zip.zipFolder("C:\\Users\\ctl-user\\Desktop\\Jun17Workspace\\FKartProject\\Results\\HTML22_Jun_2015_06_17_12_916", "C:\\Users\\ctl-user\\Desktop\\Jun17Workspace\\FKartProject\\Results\\HTML22_Jun_2015_06_17_12_916.zip");*/
		String[] to = {"vinod.ambati94@gmail.com"};
		String[] cc = {"vinod.ambati94@gmail.com" };
		String[] bcc = {"vinod.ambati94@gmail.com"};
		
		zip=zip+"Automation.zip";
		
		
		
		// This is for google
		SendMail.sendMail(
				config.getProperty("MailUserName"),
				config.getProperty("MailpassWord"),
				"smtp.gmail.com",
				"465",
				"true",
				"true",
				true,
				"javax.net.ssl.SSLSocketFactory",
				"false",
				to,
				cc,
				bcc,
				"Automation Reports",
				"The attached are the results of current Build",
				zip,zip);
//				zip+"Automation.zip","Automation.zip");
				/*strReportsFolder+ "Results/HTML/Screenshots/".split(strReportsFolder)[2]
								.replace("\\", "") + ".Zip");*/

	}

	public static boolean sendMail(String userName, String passWord,
			String host, String port, String starttls, String auth,
			boolean debug, String socketFactoryClass, String fallback,
			String[] to, String[] cc, String[] bcc, String subject,
			String text, String attachmentPath, String attachmentName) {

//		String strReportsFolder = "Firefox";

		Properties props = new Properties();

		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.password", passWord);
		props.put("mail.smtp.host", host);

		if (!"".equals(port))

			props.put("mail.smtp.port", port);

		if (!"".equals(starttls))

			props.put("mail.smtp.starttls.enable", starttls);

		props.put("mail.smtp.auth", auth);

		if (debug) {

			props.put("mail.smtp.debug", "true");

		} else {

			props.put("mail.smtp.debug", "false");

		}

		if (!"".equals(port))

			props.put("mail.smtp.socketFactory.port", port);

		if (!"".equals(socketFactoryClass))

			props.put("mail.smtp.socketFactory.class", socketFactoryClass);

		if (!"".equals(fallback))

			props.put("mail.smtp.socketFactory.fallback", fallback);

		try

		{

			Session session = Session.getDefaultInstance(props, null);

			session.setDebug(debug);

			MimeMessage msg = new MimeMessage(session);

			msg.setSubject(subject);

			
			//StringBuffer table3 = callhtml();
			//messageBodyPart1.setContent(table3.toString(), "text/html");

			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			DataSource source = new FileDataSource(attachmentPath);
			
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachmentName);
			
			
			multipart.addBodyPart(messageBodyPart);
			//multipart.addBodyPart(messageBodyPart1);
			msg.setContent(multipart);
			msg.setFrom(new InternetAddress(userName));

			for (int i = 0; i < to.length; i++) {

				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						to[i]));

			}

			for (int i = 0; i < cc.length; i++) {

				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
						cc[i]));

			}

			for (int i = 0; i < bcc.length; i++) {

				msg.addRecipient(Message.RecipientType.BCC,
						new InternetAddress(bcc[i]));

			}

			msg.saveChanges();

			Transport transport = session.getTransport("smtps");

			transport.connect(host, userName, passWord);

			transport.sendMessage(msg, msg.getAllRecipients());

			transport.close();

			return true;

		}

		catch (Exception mex)

		{

			mex.printStackTrace();

			return false;

		}

	}

	/*private static StringBuffer callhtml() throws UnknownHostException

	{

		StringBuffer str = new StringBuffer(
				"<TABLE border=1 align='center' cellSpacing=1 cellPadding=1 width='35%' font='arial'>"
						+ "<tr><td width=150 align='center' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Build Version</b></td>"
						+ "<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"
						+ config.getProperty("BuildVersion")
						+ "</b></td></tr>"
						+ "<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Run ID</b></td>"
						+ "<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"
						+ config.getProperty("RunID")
						+ "</b></td></tr>"
						+ "<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Run Date&Time</b></td>"
						+ "<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"
						+ ReportStampSupport.timeStamp()
						+ "</b></td></tr>"
						+ "</table><hr/>"
						+ "<DIV style='padding:20px;margin:5px;'>"
						+ "<DIV style='float:left'>"
						+ "<DIV>"
						+ "<TABLE border=1 cellSpacing=1 cellPadding=1 width='100%' font='arial'>"
						+ "<tr><td colspan='2' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Environment</b></td></tr>"
						+ "<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Host Name</b></td>"
						+ "<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"
						+ ReportStampSupport.getHostName()
						+ "</b></td></tr>"
						+ "<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>OS Name</b></td>"
						+ "<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"
						+ System.getProperty("os.name")
						+ "</b></td></tr>"
						+ "<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>OS Version</b></td>"
						+ "<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"
						+ System.getProperty("os.version")
						+ "</b></td></tr>"
						+ "</TABLE>"
						+ "</DIV></br>"
						+ "<DIV>"
						+ "<TABLE border=1 cellSpacing=1 cellPadding=1 width='100%' font='arial'>"
						+ "<TR><TD colspan='2' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Execution Status</b></TD></TR>"
						+ "<tr>"
						+ "<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Suite</b></td>"
						+ "<td width=150 align='left'><FONT FACE='Arial' SIZE=1.85><b>"
						+ currentSuit
						+ "</b></td></tr>"
						+ "<tr><td width=150 align='left'bgcolor='#153E7E' ><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>No. of  Scripts Executed</b></td>"
						+ "<td width=150 align='left' color='#153E7E'><FONT FACE='Arial' SIZE=1.85><b>"
						+ map.size()
						+ "</b></td></tr>"
						+ "<tr>"
						+ "<tr><td width=150 align='left' bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>No. of  Scripts Passed</b></td>"
						+ "<td width=150 align='left' color='#E0E0E0'><FONT FACE='Arial' SIZE=1.85><b>"
						+ HtmlReportSupport.pCount
						+ "</b></td></tr>"
						+ "<tr>"
						+ "<tr><td width=150 align='left'bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>No. of  Scripts Failed</b></td>"
						+ "<td width=150 align='left'color='red' ><FONT FACE='Arial' SIZE=1.85><b>"
						+ HtmlReportSupport.fCount
						+ "</b></td></tr>"
						+ "<TR><td width=150 align='left'bgcolor='#153E7E'><FONT COLOR='#E0E0E0' FACE='Arial' SIZE=1.85><b>Suite Execution Time</b></td>"
						+ "<TD width=150 align='left'color='red' ><FONT FACE='Arial' SIZE=1.85><b>"
						+ iSuiteExecutionTime
						+ " secs"
						+ "</b></TD></TR>"
						+ "</TR></TABLE></DIV></br>"
						+ "<DIV><TABLE border=1 cellSpacing=1 cellPadding=1 width='200%' font='arial'>"
						+ "<tr><td colspan='3' align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2><b>Summary Report</b></td></tr>"
						+ "<tr><td align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2.25><b>Module</b></td>"
						+ "<td align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2.25><b>Test Case</b></td>"
						+ "<td align='center'><FONT COLOR='#153E7E' FACE='Arial' SIZE=2.25><b>Status</b></td></tr>");

		Iterator<Entry<String, String>> iterator1 = map.entrySet().iterator();

		while (iterator1.hasNext()) {

			@SuppressWarnings("rawtypes")
			Map.Entry mapEntry1 = (Map.Entry) iterator1.next();
			key = mapEntry1.getKey().toString().split(":");
			String value = (String) mapEntry1.getValue();
			str.append("<tr>" + "<td><FONT color=#153e7e size=1 face=Arial><B>"
					+ key[0] + "</B></td>"
					+ "<td><FONT color=#153e7e size=1 face=Arial><B>" + key[1]
					+ "</B></td>");

			if (value.equals("PASS")) {
				str.append("<TD width='30%' bgcolor=green align=center><FONT color=white size=1 face=Arial>"
						+ value + "</td>");
			} else {
				str.append("<TD width='30%' bgcolor=red align=center><FONT color=white size=1 face=Arial>"
						+ value + "</td>");
			}

			str.append("</tr>");
		}
		str.append("</table></DIV></DIV>");
		return str;

	}

	public static String readEMail() {

		return currentSuit;

	}*/

}
