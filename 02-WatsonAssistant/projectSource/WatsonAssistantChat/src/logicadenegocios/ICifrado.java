package logicadenegocios;
import logicadenegocios.Mensaje;



/**
 * Interface ICifrado que permite asignar 
 * la implementaci�n de m�todos espec�ficos.
 * @author Oscar y Daniel
 */
public interface ICifrado {
	
  /**
   * Clase que busca implementar 
   * el cifrado posteriormente.
   * @param pMensaje
   * @return
   */
  public abstract Mensaje cifrar(Mensaje pMensaje ) ;
  
  /**
   * Clase que busca implementar 
   * el descifrado posteriormente.
   * @param pMensaje
   * @return
   */
  public abstract Mensaje descifrar(Mensaje pMensaje ) ;

  
}
