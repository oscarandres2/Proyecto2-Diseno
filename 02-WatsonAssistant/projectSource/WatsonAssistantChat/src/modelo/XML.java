package modelo;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML extends Bitácora {

  /**
   * Método que sirve para crear las
   * bitácoras de tipo XML.	
   */
  public void crearBitacora() {
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
	try {
    dBuilder = dbFactory.newDocumentBuilder();
    Document documento = dBuilder.newDocument();
     //Agrega elemento al documento
    Element elementoRaiz = documento.createElement("Operaciones");
    // agrega el elementoRaiz al documento
    documento.appendChild(elementoRaiz);

    // agrega el elemento al documento
    elementoRaiz.appendChild(createUserElement(documento, "Codificacion", getFechaOperacion(), getHoraOperacion()));

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    // usa para mejorar la forma
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    DOMSource source = new DOMSource(documento);

    // escribe el documento
    StreamResult console = new StreamResult(System.out);
    StreamResult file = new StreamResult(new File("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\formato.xml"));

    //escribe los datos
    transformer.transform(source, console);
    transformer.transform(source, file);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}

  /**
   * Método que sirve para leer las
   * bitácoras de tipo XML.	
   */
  public String leerBitacora() throws ParserConfigurationException, SAXException, IOException {
	String resultado = "";
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	//Construye el documento
	Document document = builder.parse(new File("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\formato.xml"));
	//Normaliza la estructrura del xml
	document.getDocumentElement().normalize();
	//crea el nodo raiz o base
	Element root = document.getDocumentElement();
	//Obtiene el nodo de operacion
	NodeList nList = document.getElementsByTagName("Operacion");
	for (int temp = 0; temp < nList.getLength(); temp++){
	 Node node = nList.item(temp);
	 if (node.getNodeType() == Node.ELEMENT_NODE){
	    Element eElement = (Element) node;
	    resultado += "El tipo operación es : " + eElement.getAttribute("TipoOperación")+ "\n";
	    resultado += "Fecha  : "  +eElement.getElementsByTagName("FechaOperacion").item(0).getTextContent()+ "\n";
	    resultado += "Hora : "   +eElement.getElementsByTagName("HoraOperación").item(0).getTextContent()+ "\n";		    
	 }
	}
	System.out.println(resultado);
	return resultado;
		
	}
	
    private static Node createUserElement(Document doc, String tipoOperacion, String fechaOperacion, String horaOperacion) {
        Element operacion = doc.createElement("Operacion");
        operacion.setAttribute("TipoOperación", tipoOperacion);
        operacion.appendChild(createUserElements(doc, operacion, "FechaOperacion", fechaOperacion));
        operacion.appendChild(createUserElements(doc, operacion, "HoraOperación", horaOperacion));
        return operacion;
    }
    

    private static Node createUserElements(Document documento, Element element, String nombre, String fechaOperacion) {
        Element node = documento.createElement(nombre);
        node.appendChild(documento.createTextNode(fechaOperacion));
        return node;
    }

    
}
