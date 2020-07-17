package prueba;
import java.io.FileWriter;
import com.opencsv.CSVWriter;

public class EscribirEnCSV {
	public static void main(String args[]) throws Exception {
	      //Instantiating the CSVWriter class
	      CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\libro1.csv"));
	      //Writing data to a csv file
	      String line1[] = {"cedula", "nombre",  "fecha", "dept"};
	      String line2[] = {"1", "Oscar",  "2012-01-01", "IT"};
	      String line3[] = {"2", "Daniel", "2013-02-26", "Operations"};
	      String line4[] = {"3", "Maria",  "2016-10-10", "HR"};
	      String line5[] = {"4", "Rquel",  "2012-01-01", "IT"};
	      //Writing data to the csv file
	      writer.writeNext(line1);
	      writer.writeNext(line2);
	      writer.writeNext(line3);
	      writer.writeNext(line4);
	      //Flushing data from writer to file
	      writer.flush();
	      System.out.println("Datos ingresados");
	   }

}
