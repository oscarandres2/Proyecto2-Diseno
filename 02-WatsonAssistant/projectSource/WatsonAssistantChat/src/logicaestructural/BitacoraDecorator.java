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
 * Clase que contiene los métodos y
 * atributos necesarios para implementar
 * el patrón decorador.
 * @author Oscar y Daniel
 *
 */
public class BitacoraDecorator extends SentimientosDecorator{
  /**
   * Método constructor.
   * @param pDecoratedShape
   * @param operacion
   */
  public BitacoraDecorator(ICifrado pDecoratedShape,OperacionUsuario pOperacion) {
    super(pDecoratedShape,pOperacion);
  }


  /**
   * Permite la operación de 
   * cifrar el mensaje.
   */
  public Mensaje cifrar(Mensaje pMensaje) {
	super.cifrar(pMensaje);
	try {
      añadirBitacora(operacion.getTipoAccion(),operacion.getTextoOperacion(), operacion.getTipoCifradoDescifrado());
	} catch (Exception e) {}
	return pMensaje;
	
  }
		

  /**
   * Permite la operación de 
   * descifrar el mensaje.
   */
  public Mensaje descifrar(Mensaje pMensaje) {
	super.descifrar(pMensaje);
	try {
      añadirBitacora(operacion.getTipoAccion(),operacion.getTextoOperacion(), operacion.getTipoCifradoDescifrado());
	} catch (Exception e) {
		
	}
	return pMensaje;
  }
	
  private void añadirBitacora(String pTipoAccion, String pTextoOperacion, String pTipoCifradoDescifrado) throws ParserConfigurationException, SAXException, IOException, JAXBException {
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
