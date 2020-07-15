package logicadenegocios;
import logicadenegocios.ICifrado;




/**
 * Clase Transposicion que contiene los atributos
 * y métodos necesarios
 * @author Oscar y Daniel
 *
 */
public abstract class Transposicion implements ICifrado  {
	
  protected String invertirPalabra (String pTexto ) {	
    String palabraInvertida = "";  	
    for(int largo = pTexto.length()-1 ;largo >= 0;largo-- ) {
      palabraInvertida += pTexto.charAt(largo);
    }  	
    return palabraInvertida;	
  }

  
}
	