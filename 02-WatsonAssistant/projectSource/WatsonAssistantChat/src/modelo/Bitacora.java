package modelo;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.*;


/**
 * Clase Bitacora que contiene los atributos y
 * métodos necesarios.
 * @author Oscar y Daniel
 *
 */
@XmlRootElement
public class Bitacora {

  @XmlElement(name = "operacionUsuario")
  private ArrayList<OperacionUsuario> operacionesUsuario;

  /**
   * Constructor de la clase.
   */
  public Bitacora() {
	  operacionesUsuario = new ArrayList<OperacionUsuario>();	 
  }
  
  
  public void agregarOperacionUsuario(OperacionUsuario pOperacionUsuario) {
	  operacionesUsuario.add(pOperacionUsuario);  
  }
  
  
  public ArrayList<OperacionUsuario> getOperacionesUsuario() {
	  return operacionesUsuario;	  
  }
  
  
  public void setOperacionesUsuario(ArrayList<OperacionUsuario> pOperaciones) {
	  operacionesUsuario = pOperaciones;	  
  }
  
  /**
   * Método que permite determinar las
   * operaciones realizadas en el día actual.
   * @return
   */
  public ArrayList<OperacionUsuario> operacionesActuales(){
	ArrayList<OperacionUsuario> operacionesActuales = new ArrayList<OperacionUsuario>();
	Date fechaActual = new Date();
	for(OperacionUsuario operacionUsuario : operacionesUsuario) {	  
	  if(validarFechas(operacionUsuario,fechaActual)) {
		operacionesActuales.add(operacionUsuario);			
	  }  
	 }
	 return operacionesActuales;
  }

  @SuppressWarnings("deprecation")
  private boolean validarFechas(OperacionUsuario pOperacionUsuario, Date pFechaActual) {
	if(pOperacionUsuario.getFechaAccion().getDate() ==  pFechaActual.getDate() 
	  & pOperacionUsuario.getFechaAccion().getMonth() == pFechaActual.getMonth()) {
	    return true;
	}
	return false;
  }
  
  /**
  * Método que permite determinar las
  * operaciones de cifrado realizadas.
  * @return
  */
  public ArrayList<OperacionUsuario> operacionesCifrado(){
	ArrayList<OperacionUsuario> operacionesCifrado = new ArrayList<OperacionUsuario>();
	for(OperacionUsuario operacionUsuario: operacionesUsuario ) {		  
	  if(operacionUsuario.getTipoAccion().equals("Cifrado")) {
	    operacionesCifrado.add(operacionUsuario);			  
	  }		  
	}
	return operacionesCifrado; 
  }
  
  /**
   * Método que permite determinar las
   * operaciones de descifrado realizadas.
   * @return
   */
  public ArrayList<OperacionUsuario> operacionesDescifrado(){
	ArrayList<OperacionUsuario> operacionesDescifrado = new ArrayList<OperacionUsuario>();
	for(OperacionUsuario operacionUsuario: operacionesUsuario ) {		  
	  if(operacionUsuario.getTipoAccion().equals("Descifrado")) {
	    operacionesDescifrado.add(operacionUsuario);			  
	  }		  
	}
	return operacionesDescifrado;  
  }
  
  /**
   * Método brinda la información
   * de la clase.
   */
  public String toString() {
	String resultado = "";
	for(OperacionUsuario operacionUsuario: operacionesUsuario ) {
	  resultado += "\nFecha operación : " + operacionUsuario.getFechaAccion();
	  resultado += "\nTipo operación : " +  operacionUsuario.getTipoAccion();
	  resultado += "\nTexto original : "+operacionUsuario.getTextoOperacion();
	  resultado += "\nSubtipo: " + operacionUsuario.getTipoCifradoDescifrado();
		
	}
	return resultado;  
  }
  

}
