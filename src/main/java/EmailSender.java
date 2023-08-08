import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    EmailSender() {

        try{
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader("src/main/resources/credentials.json"));
            JSONObject jsonObject =  (JSONObject) obj;

            String username = (String) jsonObject.get("username");
            String password = (String) jsonObject.get("password");

            String toEmail = "apiszczek14@gmail.com";
            String subject = "Harmonogram wywozu śmieci";
            String body = "W załączniku dołączony jest harmonogram wywozu śmieci dla podanego adresu.";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.debug", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body);

            MimeBodyPart attachmentPart = new MimeBodyPart();
            String filePath = "output.png";
            attachmentPart.attachFile(filePath);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email with attachment sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}