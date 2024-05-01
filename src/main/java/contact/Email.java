package contact;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
		static final String form = "kienhien200418@gmail.com";
		static final String password = "lyoopzjddtacekxi";
		// properties
		public  void sendMessage(String to) {
			try {
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable","true");
				//create authenticator
				Authenticator auth = new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(form, password);
					}
				};
				Session session = Session.getInstance(props,auth);
				MimeMessage msg = new MimeMessage(session);
					msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
					msg.setFrom(form);
					msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
					msg.setSubject("Đơn hàng từ kinn");
					msg.setSentDate(new Date());
			        LocalTime myTime = LocalTime.now();
			        myTime = myTime.plusMinutes(15);
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			        String timeString = myTime.format(formatter);
					msg.setText("Đợi chút nhé, đơn hàng sẽ được giao tới bạn vào lúc "+timeString+" hãy giữ liên lạc với chúng tôi.", "UTF-8");
					Transport.send(msg);
					
					
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		

}
