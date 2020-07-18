package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class TramaPlana extends Bitácora {

	@Override
	public void crearBitacora() {
		try {
		      FileWriter archivo = new FileWriter("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\final.txt");
		      archivo.write("TipoOperación");
		      archivo.write(",");
		      archivo.write("FechaOperación");
		      archivo.write(",");
		      archivo.write("HoraOperación");
		      archivo.write("\r\n");
		      archivo.write("Descodificacion");
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
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		return informacion;
	}
	

}
