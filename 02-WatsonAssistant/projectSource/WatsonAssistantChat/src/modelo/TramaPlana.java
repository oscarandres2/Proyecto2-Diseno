package modelo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


/**
 * @author Oscar y Daniel
 * Clase TramaPlana que contiene los atributos y 
 * m�todos necesarios.
 *
 */
public class TramaPlana extends Bit�cora {

	@Override
	public void crearBitacora(String pTipo, String pNombre) {
		try {
		      FileWriter archivo = new FileWriter("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\"+pNombre+".txt");
		      archivo.write("TipoOperaci�n");
		      archivo.write(",");
		      archivo.write("FechaOperaci�n");
		      archivo.write(",");
		      archivo.write("HoraOperaci�n");
		      archivo.write("\r\n");
		      archivo.write(pTipo);
		      archivo.write(",");
		      archivo.write(getFechaOperacion());
		      archivo.write(",");
		      archivo.write(getHoraOperacion());
		      archivo.write("\r\n");
		      archivo.close();
		      System.out.println("Etsito");
		    } catch (IOException e) {
		      System.out.println("Se present� un error al crear el archivo");
		      e.printStackTrace();
		    }
		
	}

	@Override
	public String leerBitacora() throws ParserConfigurationException, SAXException, IOException {
		String informacion = "";
		try {
			File myObj = new File("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\final.txt");
			Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		     informacion = myReader.nextLine();
		     System.out.println(informacion);
		      }
		      myReader.close();
		      
	  
	    } catch (FileNotFoundException e) {
	      System.out.println("Se present� un error al crear el archivo");
	      e.printStackTrace();
	    }
		return informacion;
	}
	

}
