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


	@Override
	public void crearBitacora() {
	  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder dBuilder;
	  try {
	    dBuilder = dbFactory.newDocumentBuilder();
	    Document documento = dBuilder.newDocument();
	     //Agrega elemento al documento
        Element elementoRaiz = documento.createElement("Operacion");
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

	@Override
	public String leerBitacora() throws ParserConfigurationException, SAXException, IOException {
		String resultado = "";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		 
		//Build Document
		Document document = builder.parse(new File("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\formato.xml"));
		 
		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();
		 
		//Here comes the root node
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());
		 
		//Get all employees
		NodeList nList = document.getElementsByTagName("Operacion");
		System.out.println("============================");
		 
		for (int temp = 0; temp < nList.getLength(); temp++){
		 Node node = nList.item(temp);
		 System.out.println("");    //Just a separator
		 if (node.getNodeType() == Node.ELEMENT_NODE){
			 
		    //Print each employee's detail
		    Element eElement = (Element) node;
		    resultado += eElement.getAttribute("TipoOperación")+ "\n";
		    resultado += eElement.getElementsByTagName("fechaOperacion").item(0).getTextContent();
		    resultado += eElement.getElementsByTagName("horaOperación").item(0).getTextContent();
		    System.out.println("El tipo operacion es : "    + eElement.getAttribute("TipoOperación"));
		    System.out.println("Fecha : "  + eElement.getElementsByTagName("fechaOperacion").item(0).getTextContent());
		    System.out.println("Hora : "   + eElement.getElementsByTagName("horaOperación").item(0).getTextContent());
		    
		 }
		}
		return resultado;
		
	}
	
    private static Node createUserElement(Document doc, String tipoOperacion, String fechaOperacion, String horaOperacion) {
        Element operacion = doc.createElement("Operacion");

        operacion.setAttribute("TipoOperación", tipoOperacion);
        operacion.appendChild(createUserElements(doc, operacion, "fechaOperacion", fechaOperacion));
        operacion.appendChild(createUserElements(doc, operacion, "horaOperación", horaOperacion));
        return operacion;
    }
    


    private static Node createUserElements(Document documento, Element element, String nombre, String fechaOperacion) {
        Element node = documento.createElement(nombre);
        node.appendChild(documento.createTextNode(fechaOperacion));
        return node;
    }


  
	    



}
