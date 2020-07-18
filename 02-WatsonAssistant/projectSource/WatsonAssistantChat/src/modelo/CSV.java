package modelo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
//import com.opencsv.CSVReader;

import com.opencsv.CSVReader;


/**
 * @author Oscar y Daniel
 * Clase CSV que contiene los atributos y 
 * métodos necesarios.
 *
 */
public class CSV extends Bitácora  {

  /**
   * Método que permite crear la bitácora 
   * de tipo CSV.
   */
  public void crearBitacora() {
	try{
	  String nombre = "probandoo";
	  String tipoArchivo = ".csv";
	  nombre += tipoArchivo;
	  PrintWriter pw= new PrintWriter(new File("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\"+nombre));
	  StringBuilder sb=new StringBuilder();
	  sb.append("TipoOperación");
	  sb.append(",");
	  sb.append("FechaOperacion");
	  sb.append(",");
	  sb.append("HoraOperacion");
	  sb.append("\r\n");
	  sb.append("Codificacion");
	  sb.append(",");
	  sb.append(getFechaOperacion());
	  sb.append(",");
	  sb.append(getHoraOperacion());
	  sb.append("\r\n");
	  pw.write(sb.toString());
	  pw.close();
	  System.out.println("Finalizado");
	   } catch (Exception e) {
	      // TODO: handle exception
	   }
		
	}

  	/**
  	 * Método que permite leer la bitácora 
     * de tipo CSV.
  	 */
	public String leerBitacora() throws ParserConfigurationException, SAXException, IOException {
		//Inicializa el lector del csv
	      CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\probandoo.csv"));
	      //Lee todos los contenidos del documento
	      List list = reader.readAll();
	      //Obtiene el objeto iterador
	      Iterator it = list.iterator();
	      String resultado = "";
	      while(it.hasNext()) {
	         String[] str = (String[]) it.next();
	         resultado += Arrays.toString(str) +"\n";
	         //System.out.println(Arrays.toString(str));
	      }
	      System.out.println(resultado);
		return resultado;
	}

}
