package logicadenegocios;
import util.ConversionAscii;




/**
 * Clase SustitucionCesar que contiene los
 * atributos y métodos necesarios.
 * @author Oscar y Daniel
 *
 */
public class SustitucionCesar implements ICifrado {
	
  private String cantidadPosiciones;
	
  /**
   * Constructor de la clase.
   * @param pCantidadPosiciones
   */
  public SustitucionCesar(String pCantidadPosiciones) {
	this.cantidadPosiciones = pCantidadPosiciones;
  }
	
  /**
   * Método que permite realizar la
   * función de cifrar el mensaje.	
   */
  public Mensaje cifrar(Mensaje pMensaje) {		
    char[] mensajeSeparado = pMensaje.getMensajeViejo().toCharArray();
	String mensajeCifrado = "";
	for(char letra: mensajeSeparado) {		
	  if(letra == ' '){
	  mensajeCifrado +=" ";
	  } else{
		  int posicionLetraAscii = ConversionAscii.letraAscii(letra)+Integer.parseInt(cantidadPosiciones);	
		  posicionLetraAscii = validarAsciiLetra(letra,posicionLetraAscii);	
		  char letraCifrada = ConversionAscii.asciiLetra(posicionLetraAscii);	
		  mensajeCifrado += validarCaps(letra,letraCifrada);	
		}
	  }
	pMensaje.setMensajeCifrado(mensajeCifrado);	
	return pMensaje;		
  }
  
  
  /**
   * Método que permite realizar la función
   * de descifrar el mensaje.
   */
  public Mensaje descifrar(Mensaje pMensaje) {
	char[] mensajeSeparado = pMensaje.getMensajeCifrado().toCharArray();
	String mensajeDescifrado = "";
	  for(char letra: mensajeSeparado) {	
	  if(letra == ' '){
	    mensajeDescifrado +=" ";
	  } else{
		  int numeroLetraAscii = ConversionAscii.letraAscii(letra)-Integer.parseInt(cantidadPosiciones);	
		  numeroLetraAscii = validarLetraAscii(letra,numeroLetraAscii);		
		  char letraDescifrada = ConversionAscii.asciiLetra(numeroLetraAscii);	
		  mensajeDescifrado += validarCaps(letra,letraDescifrada);	
		}
	  }
    pMensaje.setMensajeDescifrado(mensajeDescifrado);	
	return pMensaje;
  }
  
  
  private char validarCaps(char pLetraActual,char pLetraEncriptada) {
    if(Character.isLowerCase(pLetraActual)) {
	  pLetraEncriptada = Character.toLowerCase(pLetraEncriptada);
	  return pLetraEncriptada;
	}
	return pLetraEncriptada;
  }
  
  
  private int validarLetraAscii(char pLetra, int pNumeroLetraAscii) {
    if(ConversionAscii.letraAscii(pLetra)-Integer.parseInt(cantidadPosiciones) < 65){
	  return (pNumeroLetraAscii = ConversionAscii.letraAscii(pLetra)-Integer.parseInt(cantidadPosiciones) + 26);
    }
	return pNumeroLetraAscii;
  }
  
  
  private int validarAsciiLetra(char pLetra, int pNumeroAsciiLetra) {
	if(ConversionAscii.letraAscii(pLetra)+Integer.parseInt(cantidadPosiciones) > 90){
	  pNumeroAsciiLetra = ConversionAscii.letraAscii(pLetra)+Integer.parseInt(cantidadPosiciones) - 26;
	}
	return pNumeroAsciiLetra;
  }
	
  
}
