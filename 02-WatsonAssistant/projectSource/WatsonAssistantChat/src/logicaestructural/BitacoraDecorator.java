package logicaestructural;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import logicaaccesodatos.UtilBitacora;
import modelo.Bitacora;
import modelo.ICifrado;
import modelo.Mensaje;
import modelo.OperacionUsuario;



/**
 * Clase que contiene los m�todos y
 * atributos necesarios para implementar
 * el patr�n decorador.
 * @author Oscar y Daniel
 *
 */
public class BitacoraDecorator extends SentimientosDecorator{
  /**
   * M�todo constructor.
   * @param pDecoratedShape
   * @param operacion
   */
  public BitacoraDecorator(ICifrado pDecoratedShape,OperacionUsuario pOperacion) {
    super(pDecoratedShape,pOperacion);
  }


  /**
   * Permite la operaci�n de 
   * cifrar el mensaje.
   */
  public Mensaje cifrar(Mensaje pMensaje) {
	super.cifrar(pMensaje);
	try {
      a�adirBitacora(operacion.getTipoAccion(),operacion.getTextoOperacion(), operacion.getTipoCifradoDescifrado());
	} catch (Exception e) {}
	return pMensaje;
	
  }
		

  /**
   * Permite la operaci�n de 
   * descifrar el mensaje.
   */
  public Mensaje descifrar(Mensaje pMensaje) {
	super.descifrar(pMensaje);
	try {
      a�adirBitacora(operacion.getTipoAccion(),operacion.getTextoOperacion(), operacion.getTipoCifradoDescifrado());
	} catch (Exception e) {
		
	}
	return pMensaje;
  }
	
  private void a�adirBitacora(String pTipoAccion, String pTextoOperacion, String pTipoCifradoDescifrado) throws ParserConfigurationException, SAXException, IOException, JAXBException {
    UtilBitacora.validarArchivos();  
    Bitacora xml = UtilBitacora.leerBitacoraXML();
    Bitacora csv = new Bitacora();
    Bitacora txt = new Bitacora();
	OperacionUsuario operacionUsuario = crearOperacionUsuario(pTipoAccion, pTextoOperacion, pTipoCifradoDescifrado); 
	xml.agregarOperacionUsuario(operacionUsuario);
	csv.agregarOperacionUsuario(operacionUsuario);
	txt.agregarOperacionUsuario(operacionUsuario);  
	UtilBitacora.crearXML(xml);
	UtilBitacora.crearBitacora(csv, "csv");
	UtilBitacora.crearBitacora(txt, "txt");
}
	
  private OperacionUsuario crearOperacionUsuario(String pTipoAccion, String pTextoOperacion, String pTipoCifradoDescifrado) {
    String tipoAccion = pTipoAccion;
    String textoOperacion = pTextoOperacion;
    String tipoCifradoDescifrado = pTipoCifradoDescifrado;
    OperacionUsuario operacionUsuario  = new OperacionUsuario();
    operacionUsuario.agregarDatos(tipoAccion, textoOperacion, tipoCifradoDescifrado);
    return operacionUsuario;		 
  }
  
	
}
