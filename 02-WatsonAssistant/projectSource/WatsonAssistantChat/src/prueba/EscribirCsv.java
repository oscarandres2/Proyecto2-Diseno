package prueba;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

public class EscribirCsv {
	public static void main(String args[]) throws Exception {
	      //Crea el archivo
		 PrintWriter pw= new PrintWriter(new File("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\prueba.csv"));
		//Se agarra el path del archivo creado para escribir la informacion
		 CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\prueba.csv"));
	      //Writing data to a csv file
	      String line1[] = {"cedula", "nombre",  "fecha"};
	      String line2[] = {"1", "Oscar",  "2012-01-01"};
	      String line3[] = {"2", "Daniel", "2013-02-26"};
	      String line4[] = {"3", "Maria",  "2016-10-10"};
	      String line5[] = {"4", "Rquel",  "2012-01-01"};
	      //Instantiating the List Object
	      List list = new ArrayList();
	      list.add(line1);
	      list.add(line2);
	      list.add(line3);
	      list.add(line4);
	      list.add(line5);
	      //Writing data to the csv file
	      writer.writeAll(list);
	      writer.flush();
	      System.out.println("Datos ingresados");
	   }

}
