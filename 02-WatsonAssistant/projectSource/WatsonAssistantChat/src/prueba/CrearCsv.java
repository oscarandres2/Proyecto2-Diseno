package prueba;
import java.io.File;
import java.io.PrintWriter;

public class CrearCsv {
	public static void main(String[] args) {
		 try {
			String nombre = "probandoo";
			String tipoArchivo = ".csv";
			nombre += tipoArchivo;
		   PrintWriter pw= new PrintWriter(new File("C:\\Users\\Oscar\\OneDrive\\Escritorio\\prueba\\"+nombre));
		   StringBuilder sb=new StringBuilder();
		   sb.append("Asunto");
		   sb.append(",");
		   sb.append("Fecha");
		   sb.append(",");
		   sb.append("Mensaje");
		   sb.append("\r\n");
		   sb.append("Codificacion");
		   sb.append(",");
		   sb.append("22/2/2 ");
		   sb.append(",");
		   sb.append("Probando");
		   sb.append("\r\n");
		   pw.write(sb.toString());
		   pw.close();
		   System.out.println("Finalizado");
		   } catch (Exception e) {
		      // TODO: handle exception
		   }
		}

}
