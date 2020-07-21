package prueba;
import java.util.Date;

public class OperacionUsuario {
	
  private Date fechaAccion;
  private String tipoAccion;
  private String textoOperacion;
  private String tipoCifradoDescifrado;
  
  
  public void agregarDatos(String pTipoAccion,String pTextoOperacion,String pTipoCifradoDescifrado) {
    setTipoAccion(pTipoAccion);
    setFechaAccion();
    setTextoOperacion(pTextoOperacion);
    setTipoCifradoDescifrado(pTipoCifradoDescifrado); 
  }
  
  
  public String getTipoAccion() {
	return tipoAccion;
   }


  public void setTipoAccion(String pTipoAccion) {
	this.tipoAccion = pTipoAccion;
  }


  public Date getFechaAccion() {
	return fechaAccion;
  }


  public void setFechaAccion() {
	this.fechaAccion = new Date();
  }


  public String getTextoOperacion() {
	return textoOperacion;
  }


  public void setTextoOperacion(String pTextoOperacion) {
	this.textoOperacion = pTextoOperacion;
  }


  public String getTipoCifradoDescifrado() {
	return tipoCifradoDescifrado;
  }


  public void setTipoCifradoDescifrado(String pTipoCifradoDescifrado) {
	this.tipoCifradoDescifrado = pTipoCifradoDescifrado;
  }
 



}
