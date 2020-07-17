package modelo;



/**
 * Clase Mensaje que contiene los atributos y
 * métodos necesarios.
 * @author Oscar y Daniel
 *
 */
public class Mensaje {
	
  private String mensajeViejo;
  private String mensajeCifrado;
  private String mensajeDescifrado;
  
  /**
   * Constructor de la clase Mensaje.
   * @param pMensajeViejo
   */
  public Mensaje(String pMensajeViejo) {
    mensajeViejo = pMensajeViejo;    
  }
  
  public String getMensajeViejo() {
    return mensajeViejo;
  }
  
  public String getMensajeCifrado() {
    return mensajeCifrado;
  }
  
  public void setMensajeCifrado(String pMensajeCifrado) {
	mensajeCifrado = pMensajeCifrado; 
  }
  
  public void setMensajeDescifrado(String pMensajeDescifrado) {
	mensajeDescifrado = pMensajeDescifrado; 
  }
  
  public String getMensajeDescifrado() {
    return mensajeDescifrado;
  }
  
  
}
