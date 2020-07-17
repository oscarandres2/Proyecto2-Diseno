package modelo;
import util.ConversionAscii;



/**
 * Clase CodificaciónBinaria la cual
 * contiene los atributos y métodos necesarios.
 * 
 * @author Oscar y Daniel
 */
public class CodificacionBinaria implements ICifrado {
	
  /**
   * Método que permite realizar la función
   * de cifrar el mensaje.
   */
  public Mensaje cifrar(Mensaje pMensaje) {
    pMensaje.setMensajeCifrado(textoBinario(pMensaje.getMensajeViejo()));	
    return pMensaje;
  }
  
	
  /**
   * Método que permite realizar la función
   * de descifrar el mensaje.
   */
  public Mensaje descifrar(Mensaje pMensaje) {
    pMensaje.setMensajeDescifrado(binarioTexto(pMensaje.getMensajeCifrado()));
    return pMensaje;
  }
  
	
  private String cambiarCaracterBinario(int pValorLetra) {		
    String letraConvertida;
    if(pValorLetra == 32) {
      return "*";
    } else {
	    letraConvertida = Long.toBinaryString(pValorLetra-97);	
	    return validarCambioBinario(letraConvertida);
	  }		
  }
	
	
  private char cambiarBinarioCaracter(String pTexto) {
    int valorLetra = 16;
    int valorNumerico = 0;
    for(int contador = 0;contador<pTexto.length();contador++) {
   	  valorNumerico += Character.getNumericValue(pTexto.charAt(contador)) *valorLetra;
	  valorLetra = valorLetra / 2;
	 }
	 return ConversionAscii.asciiLetra(valorNumerico+97);
  } 
	
	
  private char validarEspacioBlanco(String pTexto) {
    if(pTexto.contentEquals("*")) {
      return ' ';   
    } else {
	    return cambiarBinarioCaracter(pTexto);  
	   }
  }
	
	
  private String validarCambioBinario(String pLetraConvertida) {
    if(pLetraConvertida.length() == 5) {
      return pLetraConvertida;  
	} else {
	    while(pLetraConvertida.length()<5) {
    	  pLetraConvertida = "0" + pLetraConvertida; 
	    }
	  }
	  return pLetraConvertida;  
	}
	
  private String textoBinario(String pTexto){
    byte[] listaBytes = pTexto.getBytes();
    String textoCifrado = "";
    for(byte byteActual : listaBytes){
	     textoCifrado += cambiarCaracterBinario(byteActual)+" ";
	 }
	 return textoCifrado;
  }
	  
  private String binarioTexto(String pTexto) {
    String textoCifrado = "";
    String[] textoSeparado = pTexto.split(" ");
    for (String separadoActual : textoSeparado) {
      textoCifrado += validarEspacioBlanco(separadoActual);
    }
    return textoCifrado;
  }
	
  	
}
