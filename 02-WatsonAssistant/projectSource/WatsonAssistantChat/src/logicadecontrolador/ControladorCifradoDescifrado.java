package logicadecontrolador;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import logicadeinstanciacion.SimpleCifradoFactory;
import modelo.ICifrado;
import modelo.Mensaje;
import patrondecorator.BitacoraDecorator;
import patrondecorator.SentimientosDecorator;
import prueba.OperacionUsuario;


/**
 * Clase que sirve como contralador del
 * cifrado y el descifrado.
 * @author Oscar y Daniel
 *
 */
public class ControladorCifradoDescifrado {
	
  private SimpleCifradoFactory factory = new SimpleCifradoFactory();
  private ICifrado strategy;
  private OperacionUsuario operacion = new OperacionUsuario();
  
  public ControladorCifradoDescifrado() {}
  /**
   * M�todo que crea el cifrado o el descifrado
   * dependiendo del tipo.
   * @param pTipo
   * @param subTipo
   * @param parametro
   * @return
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws SecurityException
   */
  private ICifrado crearCifradoDescifrado(String pTipo, String subTipo, Object parametro) throws InstantiationException,
    IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    ICifrado cifrado;

    cifrado = factory.crearCifradoDescifrado(pTipo, subTipo, parametro);
    
    return cifrado;
  }
  
  //metodo nuevo
  private ICifrado crearCifradoDescifrado(String pTipo, String subTipo, Object parametro,OperacionUsuario operacion) throws InstantiationException,
  IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	  ICifrado cifrado;
	
	  cifrado = new BitacoraDecorator(factory.crearCifradoDescifrado(pTipo, subTipo, parametro),operacion);
	  
	  return cifrado;
	}
  
  private void asignarStrategy(String pTipo, String pSubTipo, String parametro) {
	  try {
		strategy = crearCifradoDescifrado(pTipo, pSubTipo, parametro);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
			| InvocationTargetException | NoSuchMethodException | SecurityException e) {
		e.printStackTrace();
	}
  }
  
  // metodo nuevo
  private void asignarStrategy(String pTipo, String pSubTipo, String parametro,OperacionUsuario operacion) {
	  try {
		strategy = crearCifradoDescifrado(pTipo, pSubTipo, parametro,operacion);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
			| InvocationTargetException | NoSuchMethodException | SecurityException e) {
		e.printStackTrace();
	}
  }
  
  
  /**
   * M�todo que permite ejecutar 
   * el cifrado.
   * @param pLista
   * @return
   */
  public String ejecutarCifrado(ArrayList<String> pLista) {
	Mensaje mensaje = new Mensaje(pLista.get(0));	

    operacion.agregarDatos("Cifrado", mensaje.getMensajeViejo(), pLista.get(2));
	
	//asignarStrategy(pLista.get(2), pLista.get(1),pLista.get(3));
    asignarStrategy(pLista.get(2), pLista.get(1),pLista.get(3),operacion);
	strategy.cifrar(mensaje);
	return mensaje.getMensajeCifrado(); 
  }
  
  
  /**
   * M�todo que permite ejecutar 
   * el descifrado.
   * @param pLista
   * @return
   */
  public String ejecutarDescifrado(ArrayList<String> pLista) {
	Mensaje mensaje = new Mensaje(pLista.get(0));
	mensaje.setMensajeCifrado(pLista.get(0));
	
    operacion.agregarDatos("Descifrado", mensaje.getMensajeViejo(), pLista.get(2));
	
	//asignarStrategy(pLista.get(2), pLista.get(1),pLista.get(3));
    asignarStrategy(pLista.get(2), pLista.get(1),pLista.get(3),operacion);
	strategy.descifrar(mensaje);
	return mensaje.getMensajeDescifrado();	
  }

}
