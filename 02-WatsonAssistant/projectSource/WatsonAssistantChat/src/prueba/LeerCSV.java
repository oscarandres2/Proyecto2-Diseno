package prueba;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import com.opencsv.CSVReader;


public class LeerCSV {
	public static void main(String args[]) throws Exception {
	      //Instantiating the CSVReader class
	      CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\libro1.csv"));
	      //Reading the contents of the csv file
	      List list = reader.readAll();
	      //Getting the Iterator object
	      Iterator it = list.iterator();
	      while(it.hasNext()) {
	         String[] str = (String[]) it.next();
	         System.out.println(Arrays.toString(str));
	      }
	   }

}
