package logicadenegocios;
import java.util.ArrayList;
import util.ConversionAscii;

/**
 * Clase SustitucionClave que contiene los atributos
 * y métodos necesarios.
 * @author Oscar y Daniel.
 *
 */
public abstract class SustitucionClave implements ICifrado {

  protected ArrayList<Character> alfabeto = asignarAlfabeto();
  
  private ArrayList<Character> asignarAlfabeto(){
    ArrayList<Character> alfabeto = new ArrayList<Character>();	
	alfabeto.add(' ');	
	int contador = 65;
	while(contador < 91) {		
	  Character letra = ConversionAscii.asciiLetra(contador);
	  alfabeto.add(letra);
	  contador++;
	}	
	return alfabeto;
  }
  
  
  protected char validarCaps(char pLetraActual,char pLetraEncriptada) {
    if(Character.isLowerCase(pLetraActual)) {
	  pLetraEncriptada = Character.toLowerCase(pLetraEncriptada);
	  return pLetraEncriptada;
	}
	return pLetraEncriptada;
  }
  
  
}
