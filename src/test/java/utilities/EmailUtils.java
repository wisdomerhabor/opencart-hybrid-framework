package utilities;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.Multipart;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;

public class EmailUtils {

    public void sendEmailWithReport(String reportPath) {
        final String fromEmail = "wisdomerhabor2@gmail.com"; // Sender Email
        final String password = "ostndwyylaijtafu"; // Use Gmail App Password
        final String toEmail = "wisdomochus1@gmail.com"; // Receiver Email

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
        props.put("mail.smtp.port", "587"); // TLS Port
        props.put("mail.smtp.auth", "true"); // Enable Authentication
        props.put("mail.smtp.starttls.enable", "true"); // Enable StartTLS

        // Create a Session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("📝 Test Execution Report");

            // Email body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hello,\n\nThe test execution has been completed.\n\n"
                    + "📌 Attached is the test report.\n\nBest Regards,\nTest Automation Team");

            // Attach the report file
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(reportPath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(source.getName());

            // Create multipart email
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // Send email
            Transport.send(message);

            System.out.println("✅ Test report email sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Failed to send test report email.");
        }
    }
    
 // 🔹 Main Method to Run the Email Sender
    public static void main(String[] args) {
    	EmailUtils eu = new EmailUtils();
        // Example: Provide the absolute path to your Extent Report
        String reportPath = "C:\\eclipse-workspace\\opencart-hybrid-framework\\logs\\automation.log"; 
        eu.sendEmailWithReport(reportPath);
    }
}
