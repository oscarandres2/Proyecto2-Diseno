package logicadeinstanciacion;
import controlador.ControladorCifradoDescifrado;




/**
 * Clase SingletonControlar que contiene los atributos y
 * m�todos necesarios para la implementaci�n del patr�n
 * Singleton.
 * @author Oscar y Daniel
 */
public class SingletonControlador {

private static ControladorCifradoDescifrado instance = new ControladorCifradoDescifrado();
	
  private SingletonControlador() {	
  }
	
  public static ControladorCifradoDescifrado getInstance(){
	return instance;
  }
  
	
}
