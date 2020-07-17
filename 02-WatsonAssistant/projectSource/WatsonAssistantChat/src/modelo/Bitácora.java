package modelo;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Bitácora {
	
  protected Date fechaOperacion;
  protected Date horaOperacion;
  protected String tipoOperacion;
  
  private void setFechaOperacion(){
	    Calendar calendario;
	    calendario = Calendar.getInstance();
	    fechaOperacion = (Date)calendario.getTime();
  }

  public String getFechaOperacion(){
	    SimpleDateFormat mascara = new SimpleDateFormat("dd/MM/yy");
	    return mascara.format(fechaOperacion);
  }

  private void setHoraOperacion(){
	    Calendar calendario;
	    calendario = Calendar.getInstance();
	    horaOperacion = (Date)calendario.getTime();
   }

  public String getHoraOperacion(){
	    SimpleDateFormat mascara = new SimpleDateFormat("HH:mm:ss");
	    return mascara.format(horaOperacion);
  }
  
  public abstract  void almacenarBitacora();


}
