package patrondecorator;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modelo.Bitacora;
import modelo.OperacionUsuario;

public class mainPruebas {

	public static void main(String[] args) throws JAXBException, IOException, ParserConfigurationException, SAXException {
		 /*UtilBitacora.validarArchivos(); 
		 Bitacora xml = UtilBitacora.leerBitacoraXML();
		    Bitacora csv = new Bitacora();
		    Bitacora txt = new Bitacora();
		    
		    OperacionUsuario operacionUsuario = crearOperacionUsuario("cifrado", "probandooo", "codigo binario");
		    
		    xml.agregarOperacionUsuario(operacionUsuario);
			 csv.agregarOperacionUsuario(operacionUsuario);
			 txt.agregarOperacionUsuario(operacionUsuario);
			  
			 UtilBitacora.crearXML(xml);
			 UtilBitacora.crearBitacora(csv, "csv");
			 UtilBitacora.crearBitacora(txt, "txt");*/
		
		
		String tipoCriterio = "registros codificacion";
		String tipoFuente = "xml";
		
		Bitacora bitacora = UtilBitacora.determinarFuenteBitacora(tipoFuente);
		bitacora.operacionesUsuario = UtilBitacora.determinarCriterioBitacora(tipoCriterio, bitacora);
		
		//Bitacora bitacora = UtilBitacora.leerBitacoraXML();
		//bitacora.operacionesUsuario = bitacora.operacionesActuales();
		
	    
		
		//System.out.println(UtilBitacora.bitacoraToStringXML(bitacora));
		

	}

}
