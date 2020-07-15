package util;




/**
 * Clase que realiza la conversión
 * Ascii.
 * @author Oscar
 *
 */
public class ConversionAscii {

  /**
   * Método que convierte de código
   * ascii a una letra.
   * @param pNumero
   * @return
   */
  public static char asciiLetra(int pNumero) {
	return (char)pNumero;
  }
	

  /**
   * Método que convierte una letra
   * a código ascii.
   * @param pLetra
   * @return
   */
  public static int letraAscii(char pLetra) {
	pLetra = Character.toUpperCase(pLetra);
	return (int)pLetra;
  }

	
}
