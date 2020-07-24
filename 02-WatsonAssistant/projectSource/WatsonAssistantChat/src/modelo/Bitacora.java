package modelo;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Bitacora {

  @XmlElement(name = "operacionUsuario")
  private ArrayList<OperacionUsuario> operacionesUsuario;

  
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
  

  
  public ArrayList<OperacionUsuario> operacionesActuales(){
	  ArrayList<OperacionUsuario> operacionesActuales = new ArrayList<OperacionUsuario>();
	  Date fechaActual = new Date();
	  for(OperacionUsuario operacionUsuario : operacionesUsuario) {
		  
		if(validarFechas(operacionesActuales,operacionUsuario,fechaActual)) {
			operacionesActuales.add(operacionUsuario);			
		}  
	  }
	  return operacionesActuales;
  }

  @SuppressWarnings("deprecation")
  private boolean validarFechas(ArrayList<OperacionUsuario> operacionesActuales, OperacionUsuario pOperacionUsuario, Date pFechaActual) {
	  if(pOperacionUsuario.getFechaAccion().getDate() ==  pFechaActual.getDate() 
			  & pOperacionUsuario.getFechaAccion().getMonth() == pFechaActual.getMonth()) {
		return true;
	  }
	  return false;
  }
  
  
  public ArrayList<OperacionUsuario> operacionesCifrado(){
	  ArrayList<OperacionUsuario> operacionesCifrado = new ArrayList<OperacionUsuario>();
	  for(OperacionUsuario operacionUsuario: operacionesUsuario ) {		  
		  if(operacionUsuario.getTipoAccion().equals("Cifrado")) {
			  operacionesCifrado.add(operacionUsuario);			  
		  }		  
	  }
	  return operacionesCifrado;
	  
	  
  }
  
  public ArrayList<OperacionUsuario> operacionesDescifrado(){
	  ArrayList<OperacionUsuario> operacionesDescifrado = new ArrayList<OperacionUsuario>();
	  for(OperacionUsuario operacionUsuario: operacionesUsuario ) {		  
		  if(operacionUsuario.getTipoAccion().equals("Descifrado")) {
			  operacionesDescifrado.add(operacionUsuario);			  
		  }		  
	  }
	  return operacionesDescifrado;  
  }
  
  
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
