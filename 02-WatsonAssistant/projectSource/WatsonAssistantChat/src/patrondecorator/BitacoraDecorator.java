package patrondecorator;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modelo.Bitacora;
import modelo.ICifrado;
import modelo.Mensaje;
import modelo.OperacionUsuario;
import util.UtilBitacora;

public class BitacoraDecorator extends SentimientosDecorator{

	public BitacoraDecorator(ICifrado pDecoratedShape,OperacionUsuario operacion) {
		super(pDecoratedShape,operacion);
	}

	public Mensaje cifrar(Mensaje pMensaje) {
		super.cifrar(pMensaje);
		try {
			aņadirBitacora(operacion.getTipoAccion(),operacion.getTextoOperacion(), operacion.getTipoCifradoDescifrado());
		} catch (Exception e) {
			
		}
		return pMensaje;
	}
	
	public Mensaje descifrar(Mensaje pMensaje) {
		super.descifrar(pMensaje);
		try {
			aņadirBitacora(operacion.getTipoAccion(),operacion.getTextoOperacion(), operacion.getTipoCifradoDescifrado());
		} catch (Exception e) {
			
		}
		return pMensaje;
	}
	
	private void aņadirBitacora(String pTipoAccion, String pTextoOperacion, String pTipoCifradoDescifrado) throws ParserConfigurationException, SAXException, IOException, JAXBException {
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
