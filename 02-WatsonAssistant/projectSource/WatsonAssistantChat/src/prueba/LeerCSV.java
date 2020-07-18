package prueba;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
//import com.opencsv.CSVReader;


public class LeerCSV {
	public static void main(String args[]) throws Exception {
	      
	      String csvFile = "C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\probandoo.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	      
	      
	      
	      
	      
	      
	      
	     /* //Instantiating the CSVReader class
	      CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\libro1.csv"));
	      //Reading the contents of the csv file
	      List list = reader.readAll();
	      //Getting the Iterator object
	      Iterator it = list.iterator();
	      String resultado = "";
	      while(it.hasNext()) {
	         String[] str = (String[]) it.next();
	         resultado += Arrays.toString(str);
	         System.out.println(Arrays.toString(str));
	      }
	      System.out.println("El resultado es "+ resultado);*/
	   }

}
