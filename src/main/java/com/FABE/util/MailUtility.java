package com.FABE.util;
/*package com.wk.utilities; 



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.internal.PropertiesFile;



public class MailUtility {

	public static Properties configProps =  new Properties();
	
	
	public static  void sendNotification() throws FileNotFoundException, IOException {
		configProps.load(new FileInputStream("./\\resources\\TestConfig.properties"));

		// Recipient's email ID needs to be mentioned.
		String[] toMailerList = configProps.getProperty("ToAddresses").split(",");
		System.out.println(configProps.getProperty("ToAddresses"));
		
		String[] ccMailerList = configProps.getProperty("CCAddresses").split(",");
		System.out.println(configProps.getProperty("CCAddresses"));
		
		final String username = configProps.getProperty("UserName");
		final String password = configProps.getProperty("Password");
		
		String from = configProps.getProperty("FromAddresses");

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
	//	props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "susday236.corp.ncr.com");
		props.put("mail.smtp.host", "legacy.ncr.com"); .mail.ClientHost.com.au.  .SUSDAY240.corp.ncr.com. 
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props);
		
		Session session = Session.getInstance(props,
			      new javax.mail.Authenticator() {
			        protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(username, password);
			        }
			      });
		
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			
			javax.mail.internet.InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[toMailerList.length];

			for (int i = 0; i < toMailerList.length; i++) {
				addressTo[i] = new javax.mail.internet.InternetAddress(
						toMailerList[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.TO,
					addressTo);
			
			javax.mail.internet.InternetAddress[] addressCC = new javax.mail.internet.InternetAddress[ccMailerList.length];
			for (int i = 0; i < ccMailerList.length; i++) {
				addressCC[i] = new javax.mail.internet.InternetAddress(
						ccMailerList[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.CC,
					addressCC);
			// Set Subject: header field
			message.setSubject("Test Mail From Send Mail Utility");//("Project Titles are Exceeded  .... ");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart
					.setContent(
									"Hi, <br><br>"
									+configProps.getProperty("body")
									
								
									+ "<br><br>Thanks,<br>Offshore Team<br><br>",
							"text/html; charset=utf-8");
			// Create a multipart message
			Multipart multipart = new MimeMultipart();
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
public static  void sendReportAsMailBody(String filePath, String subject) throws Throwable {
		
	
		File fis = new File(filePath);
		FileReader fileReader = new FileReader(fis);
		//jsoup code 
		Document doc = Jsoup.parse(fis, "UTF-8", "http://example.com/");
		Element newsHeadlines = doc.getElementById("footer");
		Elements header = doc.select("head");
		String jsonStringBuffer = "<html>"+header.html()+"<body> <table id='footer'>"+newsHeadlines.html()+"</table></body></html>";
        Document newFooter = Jsoup.parse(jsonStringBuffer);
        Element remLogos = doc.getElementById("Logos");
		remLogos.remove();
        Element remFooter = doc.getElementById("footer");
		remFooter.remove();
		//**********
        BufferedReader reader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = reader.readLine()) != null) {
				//String newLine[] = line.split("(?<=footer).*?(?=</tfoot>)");
				String newLine[] = line.split("(?i)(<table id='footer'.*?>)(.+?)(</tfoot>)");
				System.out.println(newLine[0]);
				String logoLess[]=newLine[0].split("(?i)(<table id='Logos'.*?>)(.+?)(main)");
				System.out.println(logoLess[0]);
				stringBuffer.append(newLine[0]);
		}
		//stringBuffer.append(stbfooter.toString());
		System.out.println("Contents of file:");
		System.out.println(stringBuffer.toString());
		fileReader.close();
	
		// Recipient's email ID needs to be mentioned.
		String[] toMailerList = configProps.getProperty("To").split(",");
		System.out.println(configProps.getProperty("To"));
		String[] ccMailerList = configProps.getProperty("CC").split(",");
		System.out.println(configProps.getProperty("CC"));
		final String username = configProps.getProperty("UserName");
		final String password = configProps.getProperty("Password");
		String from = configProps.getProperty("From");

		Properties props = new Properties();
		props.put("mail.smtp.host", "SUSDAY240.corp.ncr.com");
		props.put("mail.smtp.port", "25");
		
		Session session = Session.getInstance(props);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			
			javax.mail.internet.InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[toMailerList.length];

			for (int i = 0; i < toMailerList.length; i++) {
				addressTo[i] = new javax.mail.internet.InternetAddress(
						toMailerList[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.TO,
					addressTo);
			
			javax.mail.internet.InternetAddress[] addressCC = new javax.mail.internet.InternetAddress[ccMailerList.length];
			for (int i = 0; i < ccMailerList.length; i++) {
				addressCC[i] = new javax.mail.internet.InternetAddress(
						ccMailerList[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.CC,
					addressCC);
			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart
					.setContent(
							newFooter.html()+stringBuffer.toString(),
							"text/html; charset=utf-8");
			// Create a multipart message
			Multipart multipart = new MimeMultipart();
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	public static void main(String a[]) throws FileNotFoundException, IOException{
		
		sendNotification();
	}

}*/