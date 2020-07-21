package logicadeinstanciacion;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import modelo.ICifrado;




/**
 * Clase que permite instanciar el 
 * cifrado y descifrado respectivamente
 * @author Oscar y Daniel 
 *
 */
public class SimpleCifradoFactory {
  
  /**
   * Método que crea el objeto dependiendo
   * del tipo.
   * @param pType
   * @param pSubType
   * @param pParametro
   * @return
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws SecurityException
   */
  public ICifrado crearCifradoDescifrado(String pType, String pSubType,Object pParametro) throws InstantiationException, 
      IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {	
	ICifrado iCifrado;
	System.out.println("hola4");
	if(pSubType == null) {
	  iCifrado = (ICifrado) Class.forName("modelo."+pType).newInstance();
	  return iCifrado;
	}
	System.out.println("hola5");
	Constructor construc = Class.forName("modelo."+pType).getConstructor(pParametro.getClass());
	iCifrado = (ICifrado) construc.newInstance(pParametro);
	return iCifrado;	
  }
	
  
}
