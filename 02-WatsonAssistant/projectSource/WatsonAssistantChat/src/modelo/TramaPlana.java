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
 * métodos necesarios.
 *
 */
public class TramaPlana extends Bitácora {

	@Override
	public void crearBitacora(String pTipo, String pNombre) {
		try {
		      FileWriter archivo = new FileWriter("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\"+pNombre+".txt");
		      archivo.write("TipoOperación");
		      archivo.write(",");
		      archivo.write("FechaOperación");
		      archivo.write(",");
		      archivo.write("HoraOperación");
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
		      System.out.println("Se presentó un error al crear el archivo");
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
	      System.out.println("Se presentó un error al crear el archivo");
	      e.printStackTrace();
	    }
		return informacion;
	}
	

}
