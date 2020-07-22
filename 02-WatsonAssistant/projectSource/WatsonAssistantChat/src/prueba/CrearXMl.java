package prueba;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import modelo.Bitácora;

//public class CrearXMl extends Bitácora {




//		public void crearBitacora() {
//			 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//		        DocumentBuilder dBuilder;
//		        try {
//		            dBuilder = dbFactory.newDocumentBuilder();
//		            Document documento = dBuilder.newDocument();
//		            // add elements to Document
//		            Element rootElement = documento.createElement("Operacion");
//		            // append root element to document
//		            documento.appendChild(rootElement);
//
//		            // append first child element to root element
//		            rootElement.appendChild(createUserElement(documento, "Codificacion", getFechaOperacion(), getHoraOperacion()));
//
//
//		            // for output to file, console
//		            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		            Transformer transformer = transformerFactory.newTransformer();
//		            // for pretty print
//		            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//		            DOMSource source = new DOMSource(documento);
//
//		            // write to console or file
//		            StreamResult console = new StreamResult(System.out);
//		            StreamResult file = new StreamResult(new File("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\formato.xml"));
//
//		            // write data
//		            transformer.transform(source, console);
//		            transformer.transform(source, file);
//
//		        } catch (Exception e) {
//		            e.printStackTrace();
//		        }
//			
//		}

//		@Override
//		public String leerBitacora() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//	    private static Node createUserElement(Document doc, String tipoOperacion, String fechaOperacion, String horaOperacion) {
//	        Element operacion = doc.createElement("Operacion");
//
//	        // set id attribute
//	        operacion.setAttribute("TipoOperación", tipoOperacion);
//
//	        // create firstName element
//	        operacion.appendChild(createUserElements(doc, operacion, "fechaOperacion", fechaOperacion));
//
//	        // create lastName element
//	        operacion.appendChild(createUserElements(doc, operacion, "horaOperación", horaOperacion));
//
//
//	        return operacion;
//	    }
//	    
//
//	    /**
//	     * Utiliza para crear el texto del nodo
//	     * a implementar.
//	     * @param documento
//	     * @param element
//	     * @param nombre
//	     * @param fechaOperacion
//	     * @return
//	     */
//	    private static Node createUserElements(Document documento, Element element, String nombre, String fechaOperacion) {
//	        Element node = documento.createElement(nombre);
//	        node.appendChild(documento.createTextNode(fechaOperacion));
//	        return node;
//	    }
//
//	
//      
//    	    	  
//    	  
//    	  
//    	  
//    	  
//    	  
//      
		//}

/*  try {
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
elemento2.setTextContent("2020");
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
}*/
