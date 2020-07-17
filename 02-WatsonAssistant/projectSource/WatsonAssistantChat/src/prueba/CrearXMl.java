package prueba;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class CrearXMl {
	
	private Date fechaOperacion;
	private Date horaOperacion;
	
	  private void setFechaOperacion(){
		    Calendar calendario;
		    calendario = Calendar.getInstance();
		    fechaOperacion = (Date)calendario.getTime();
	  }
	  
	  public String getFechaOperacion(){
		    SimpleDateFormat mascara = new SimpleDateFormat("dd/MM/yy");
		    return mascara.format(fechaOperacion);
	  }
	  
	  private void setHoraOperacion(){
		    Calendar calendario;
		    calendario = Calendar.getInstance();
		    horaOperacion = (Date)calendario.getTime();
	  }
	  
	  public String getHoraOperacion(){
		    SimpleDateFormat mascara = new SimpleDateFormat("HH:mm:ss");
		    return mascara.format(horaOperacion);
	  }
	  
	
      public static void main(String argv[]) {
    	  
    	  
        try {
          DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
          //Elemento raíz
          Document doc = docBuilder.newDocument();
          Element rootElement = doc.createElement("root");
          doc.appendChild(rootElement);
          //Primer elemento
          Element elemento1 = doc.createElement("elemento1");
          rootElement.appendChild(elemento1);
          //Se agrega un atributo al nodo elemento y su valor
          Attr atributo1 = doc.createAttribute("Tipo operación");
          atributo1.setValue("Codificacion");
          elemento1.setAttributeNode(atributo1);
          
          Element elemento2 = doc.createElement("Fecha");
          elemento2.setTextContent(get);
          rootElement.appendChild(elemento2);
          //Se escribe el contenido del XML en un archivo
          TransformerFactory transformerFactory = TransformerFactory.newInstance();
          Transformer transformer = transformerFactory.newTransformer();
          DOMSource source = new DOMSource(doc);
          StreamResult result = new StreamResult(new File("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\hola.xml"));
          transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
          pce.printStackTrace();
        } catch (TransformerException tfe) {
          tfe.printStackTrace();
        }
      }
    }
