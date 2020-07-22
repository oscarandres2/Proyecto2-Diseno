package prueba;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Bitacora {

  @XmlElement(name = "operacionUsuario")
  public ArrayList<OperacionUsuario> operacionesUsuario;

  
  public Bitacora() {
	  operacionesUsuario = new ArrayList<OperacionUsuario>();	
	  
  }
  
  public void agregarOperacionUsuario(OperacionUsuario pOperacionUsuario) {
	  operacionesUsuario.add(pOperacionUsuario);
	  
  }
  
  public ArrayList<OperacionUsuario> getOperacionesUsuario() {
	  return operacionesUsuario;	  
  }
  
  
  
//  public ArrayList<OperacionUsuario> totalOperaciones(){
//    ArrayList<OperacionUsuario> operacionesTotales = new ArrayList<OperacionUsuario>(); 
//    for(OperacionUsuario operacionUsuario : operacionesUsuario) {
//    	operacionesTotales.add(operacionUsuario);    	
//    }
//    return operacionesTotales;
//	  
//  }
  
  
  @SuppressWarnings("deprecation")
public ArrayList<OperacionUsuario> operacionesActuales(){
	  ArrayList<OperacionUsuario> operacionesActuales = new ArrayList<OperacionUsuario>();
	  Date fechaActual = new Date();
	  for(OperacionUsuario operacionUsuario : operacionesUsuario) {
		  
		if(operacionUsuario.getFechaAccion().getDate() ==  fechaActual.getDate() 
		  & operacionUsuario.getFechaAccion().getMonth() == fechaActual.getMonth()) {
			operacionesActuales.add(operacionUsuario);			
		}  
	  }
	  return operacionesActuales;
  }
  
  
  public ArrayList<OperacionUsuario> operacionesCifrado(){
	  ArrayList<OperacionUsuario> operacionesCifrado = new ArrayList<OperacionUsuario>();
	  for(OperacionUsuario operacionUsuario: operacionesUsuario ) {		  
		  if(operacionUsuario.getTipoAccion() == "cifrado") {
			  operacionesCifrado.add(operacionUsuario);			  
		  }		  
	  }
	  return operacionesCifrado;
	  
	  
  }
  
  public ArrayList<OperacionUsuario> operacionesdesCifrado(){
	  ArrayList<OperacionUsuario> operacionesDescifrado = new ArrayList<OperacionUsuario>();
	  for(OperacionUsuario operacionUsuario: operacionesUsuario ) {		  
		  if(operacionUsuario.getTipoAccion() == "descifrado") {
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
