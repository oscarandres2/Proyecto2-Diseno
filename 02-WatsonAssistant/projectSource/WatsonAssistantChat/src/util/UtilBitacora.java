package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import modelo.Bitacora;
import modelo.OperacionUsuario;


/**
 * Clase utilitaria de bitácora 
 * con los métodos y atributos necesarios.
 * @author Oscar y Daniel
 */
public class UtilBitacora {
	
  private static String path = "C:\\Users\\Oscar\\OneDrive\\Escritorio\\bitacoras\\bitacora";
  //private static String path = "C:\\Users\\danqp\\Desktop\\bitacoras\\bitacora";

  
  public static void validarArchivos() throws JAXBException, IOException {
	System.out.println(path);
    File xml = new File(path+".xml");
    File csv = new File(path+".csv");
    File txt = new File(path+".txt");
    
    Bitacora bitacora = new Bitacora();
    
    if(!xml.exists()) {
    	System.out.println("entro");

      crearXML(bitacora);
    }
    if(!csv.exists()) {
    	System.out.println("buenas");
      crearBitacora(bitacora, "csv");	
    }
    if(!txt.exists()) {
    	System.out.println("buenas");
	  crearBitacora(bitacora, "txt");		
    }
	  
  }
  
  
  //cammbioooos
  private static void crearBitacora(Bitacora pBitacora,String pTipoSeparador, String pTipoArchivo) throws IOException {
	  FileWriter file= new FileWriter(path+"."+pTipoArchivo,true);  
	  System.out.println(pBitacora.operacionesUsuario.size());
	  for(OperacionUsuario operacionUsuario: pBitacora.getOperacionesUsuario() ) {
		  file.append(operacionUsuario.getFechaAccion().toString());
		  file.append(pTipoSeparador);
		  file.append(operacionUsuario.getTipoAccion());
		  file.append(pTipoSeparador);
	    file.append(operacionUsuario.getTextoOperacion());
	    file.append(pTipoSeparador);
	    file.append(operacionUsuario.getTipoCifradoDescifrado());
	    file.append(pTipoSeparador);
	    file.append("\n");
	  }
	  
	  file.flush();
	  file.close();
  }
  
  
  //arreglaaar
  
  public static void crearBitacora(Bitacora pBitacora, String pTipoArchivo) throws IOException {
	if(pTipoArchivo.equals("txt")) {
	  	crearBitacora(pBitacora, "\t", pTipoArchivo);
	} else {
	    crearBitacora(pBitacora, ",", pTipoArchivo);
	} 
	 
  }
  public static void crearXML(Bitacora pBitacoraXML) throws JAXBException {
	    File file = new File(path+".xml");
	    JAXBContext jaxbContext = JAXBContext.newInstance(Bitacora.class);

	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	    //System.out.println(pBitacoraXML.toString());
	    jaxbMarshaller.marshal(pBitacoraXML, file);// this line create customer.xml file in specified path.

	  }
  
  public static Bitacora leerBitacoraXML() throws ParserConfigurationException, SAXException, IOException, JAXBException{

    File file = new File(path+".xml");
    JAXBContext jaxbContext = JAXBContext.newInstance(Bitacora.class);

    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    Bitacora bitacora = (Bitacora) jaxbUnmarshaller.unmarshal(file);

    System.out.println(bitacora.toString());
    return bitacora;

  }
  
  public static String bitacoraToStringXML(Bitacora pBitacoraXML) throws JAXBException {

    JAXBContext jaxbContext = JAXBContext.newInstance(Bitacora.class);

    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    StringWriter sw = new StringWriter();
    jaxbMarshaller.marshal(pBitacoraXML, sw);
    String xmlString = sw.toString();

    return xmlString;
  }
  
  
  public static String leerBitacora(Bitacora pBitacora,String pTipoArchivo) {
    if(pTipoArchivo.equals("txt")) {
      return bitacoraToString(pBitacora,"\t");
    } else {
       return bitacoraToString(pBitacora, ",");
    }
	  
  }
  public static String bitacoraToString(Bitacora pBitacora, String pTipoSeparador) {
	String resultado = "";
	
	for(OperacionUsuario operacionUsuario: pBitacora.getOperacionesUsuario()){
		
	  resultado += operacionUsuario.getFechaAccion().toString();
	  resultado += pTipoSeparador;
	  resultado += operacionUsuario.getTipoAccion();
	  resultado += pTipoSeparador;
	  resultado += operacionUsuario.getTextoOperacion();
	  resultado += pTipoSeparador;
	  resultado += operacionUsuario.getTipoCifradoDescifrado();
	  resultado += pTipoSeparador;
	  resultado += "\n";		
	}
	return resultado;
  }
  
  
  protected static Bitacora leerBitacora(String pTipoArchivo) throws IOException {
	if(pTipoArchivo.equals("csv")) {
		return leerInformacionBitacora("csv", ",");
	} else {
		return leerInformacionBitacora("txt", "\t");
	} 
	  
  }
  
  public static Bitacora leerInformacionBitacora(String pTipoArchivo,String pTipoSeparador) throws IOException {
    BufferedReader buffered = new BufferedReader(new FileReader(path+"."+pTipoArchivo));
    Bitacora bitacora = new Bitacora();
    String linea = "";
     while((linea = buffered.readLine())!= null) {
       String[] resultado = linea.split(pTipoSeparador) ;
       OperacionUsuario operacionUsuario = new OperacionUsuario();
       operacionUsuario.agregarDatos(resultado[1], resultado[2], resultado[3]);
       operacionUsuario.setFechaAccion(new Date(resultado[0]));
       bitacora.agregarOperacionUsuario(operacionUsuario);
    	 
     }
     buffered.close();
     return bitacora;
    
  }
  
  
  public static ArrayList<OperacionUsuario> determinarCriterioBitacora(String pCriterioBitacora,Bitacora pBitacora){
	if(pCriterioBitacora.equals("registros de hoy")) {
	  return pBitacora.operacionesActuales();	
	}
	if(pCriterioBitacora.equals("registros codificacion")) {
		  return pBitacora.operacionesCifrado();	
	}
    if(pCriterioBitacora.equals("registros descodificacion")) {
        return pBitacora.operacionesDescifrado();
    }
    else {
        return pBitacora.getOperacionesUsuario();
    }	  
	  
  }
  
  public static Bitacora determinarFuenteBitacora(String pFuenteBitacora) throws IOException, ParserConfigurationException, SAXException, JAXBException{
    if(pFuenteBitacora.equals("csv")) {
      return UtilBitacora.leerBitacora("csv");
    } 
    if(pFuenteBitacora.equals("txt")) {
      return UtilBitacora.leerBitacora("txt");
    } else {
    	return UtilBitacora.leerBitacoraXML();
    }
  }
  
  
  
}
