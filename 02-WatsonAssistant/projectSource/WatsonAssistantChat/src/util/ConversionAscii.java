package util;




/**
 * Clase que realiza la conversi�n
 * Ascii.
 * @author Oscar
 *
 */
public class ConversionAscii {

  /**
   * M�todo que convierte de c�digo
   * ascii a una letra.
   * @param pNumero
   * @return
   */
  public static char asciiLetra(int pNumero) {
	return (char)pNumero;
  }
	

  /**
   * M�todo que convierte una letra
   * a c�digo ascii.
   * @param pLetra
   * @return
   */
  public static int letraAscii(char pLetra) {
	pLetra = Character.toUpperCase(pLetra);
	return (int)pLetra;
  }

	
}
