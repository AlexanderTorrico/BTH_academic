/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consola;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author MarceloVillca
 */
public class SendEmail{

    public SendEmail() {
    }

    public Boolean SendEmail(String correoDestino, String tituloAsunto, String _mensaje) {
        
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");

        Session sesion = Session.getDefaultInstance(propiedad);

        String correoEnvia = "merx.desarrollo@gmail.com";
        String contrasena = "merxdev7.";
        String destinatario = correoDestino;
        String asunto = tituloAsunto;
        String mensaje = _mensaje;

        MimeMessage mail = new MimeMessage(sesion);

        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.CC, new InternetAddress(destinatario));
            mail.setSubject(asunto);
            mail.setContent(mensaje,"text/html");

            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEnvia, contrasena);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.CC));
            transporte.close();
            return true;

        } catch (AddressException ex) {
           System.out.println("error");
           return false;
        } catch (MessagingException ex) {
           System.out.println("error");
           return false;
        }

    }

}
