package modelo;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * 
 * @author Oscar y Daniel
 * Clase Bitácora que contiene los atributos
 * y métodos necesarios.
 */
public abstract class Bitácora {
	
  protected String fechaOperacion;
  protected String horaOperacion;
  protected String tipoOperacion;
  
  
  public String getTipoOperacion() {
    return tipoOperacion;	  
  }
  
  public void setTipoOperacion(String pTipoOperacion) {
    tipoOperacion = pTipoOperacion;	  
  }

  public String getFechaOperacion(){
    Date fecha = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MMM/yyyy");
    fechaOperacion = formato.format( fecha );
    System.out.println( fechaOperacion);
    return fechaOperacion;
   
  }


  public String getHoraOperacion(){
	   Date hora = new Date();
	    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
	    horaOperacion = formato.format( hora );
	    System.out.println( horaOperacion);
	    return horaOperacion;
	  }

  
  public abstract  void crearBitacora();
  
  public abstract String leerBitacora() throws ParserConfigurationException, SAXException, IOException;
  
  /* private void setHoraOperacion(){
  Calendar calendario;
  calendario = Calendar.getInstance();
  horaOperacion = (Date)calendario.getTime();
  }*/

 /*public String getHoraOperacion(){
 java.util.Date date = new java.util.Date();
 SimpleDateFormat mascara = new SimpleDateFormat("HH:mm:ss");
 System.out.println( horaOperacion);
 return mascara.format(horaOperacion);
  }*/


}
