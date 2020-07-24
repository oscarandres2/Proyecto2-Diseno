package logicaestructural;
import modelo.ICifrado;
import modelo.Mensaje;
import modelo.OperacionUsuario;



/**
 * Clase CifradoDecorador con los
 * atributos y m�todos necesarios para
 * la implementaci�n del patr�n decorador.
 * @author Oscar y Daniel
 *
 */
public class CifradoDecorator implements ICifrado {
	
  protected ICifrado decoratedShape; 
  protected OperacionUsuario operacion;
	
  /**
   * Constructor de la clase
   * @param pDecoratedShape
   * @param operacion
   */
  public CifradoDecorator(ICifrado pDecoratedShape,OperacionUsuario pOperacion) {
	this.decoratedShape = pDecoratedShape;
	this.operacion = pOperacion;
  }

  /**
   * Permite la operaci�n de 
   * cifrar el mensaje.
   */
  public Mensaje cifrar(Mensaje pMensaje) {
	decoratedShape.cifrar(pMensaje);
	return pMensaje;
  }

  /**
   * Permite la operaci�n de 
   * descifrar el mensaje.
   */
  public Mensaje descifrar(Mensaje pMensaje) {
	decoratedShape.descifrar(pMensaje);
	return pMensaje;
  }

}
