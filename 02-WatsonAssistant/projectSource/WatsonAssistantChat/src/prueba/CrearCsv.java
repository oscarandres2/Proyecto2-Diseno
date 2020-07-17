package prueba;
import java.io.File;
import java.io.PrintWriter;

public class CrearCsv {
	public static void main(String[] args) {
		 try {
		   PrintWriter pw= new PrintWriter(new File("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\prueba9.xml"));
		   StringBuilder sb=new StringBuilder();
		   sb.append("sl_no");
		   sb.append(",");
		   sb.append("book name");
		   sb.append(",");
		   sb.append("category");
		   sb.append("\r\n");
		   sb.append("1");
		   sb.append(",");
		   sb.append("book name 1");
		   sb.append(",");
		   sb.append("category 1");
		   sb.append("\r\n");
		   sb.append("2");
		   sb.append(",");
		   sb.append("book name 2");
		   sb.append(",");
		   sb.append("category 2");
		   sb.append("\r\n");
		   pw.write(sb.toString());
		   pw.close();
		   System.out.println("finished");
		   } catch (Exception e) {
		      // TODO: handle exception
		   }
		}

}
