package util;
import java.sql.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.smtp.SMTPTransport;



/**
 * Clase que permite enviar el correo
 * electrónico al usuario.
 * @author Oscar y Daniel
 *
 */
public class EnvioMensajes {
	
  /**
   * Método que realiza la conexión para
   * enviar el correo. 
   * 	
   * @param pCorreo
   * @param pMensaje
   */
  public static void enviarSms(String pCorreo, String pMensaje) {
    Properties props = new Properties();  
    //acá se crea la conexion con el ap de java para correos
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");	
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");  
    //CREA UN AUTENTICADOR EN PARA PODER REALIZAAR LA VALIDACIÓN
    Session session = Session.getInstance(props,new Authenticator(){
      @Override
      public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication("www.danqp0712@gmail.com","estebanjose");
      }
    });
    try{
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("www.danqp0712@gmail.com"));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(pCorreo));
      message.setSubject("Mensaje encriptado");
      message.setText(pMensaje);
      Transport.send(message);
    } catch(MessagingException e){
        System.out.println(e);	
      }
  }
  
  
}
