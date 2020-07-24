package modelo;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class OperacionUsuario {
	
  private Date fechaAccion;
  private String tipoAccion;
  private String textoOperacion;
  private String tipoCifradoDescifrado;
  
  
  public void agregarDatos(String pTipoAccion,String pTextoOperacion,String pTipoCifradoDescifrado) {
    setTipoAccion(pTipoAccion);
    setFechaAccion(new Date());
    setTextoOperacion(pTextoOperacion);
    setTipoCifradoDescifrado(pTipoCifradoDescifrado); 
  }
  
  
  public String getTipoAccion() {
	return tipoAccion;
   }

  @XmlElement
  public void setTipoAccion(String pTipoAccion) {
	this.tipoAccion = pTipoAccion;
  }


  public Date getFechaAccion() {
	 return fechaAccion;
  }

  @XmlElement
  public void setFechaAccion(Date pDate) {
	 this.fechaAccion = pDate;
  }


  public String getTextoOperacion() {
	return textoOperacion;
  }

  @XmlElement
  public void setTextoOperacion(String pTextoOperacion) {
	this.textoOperacion = pTextoOperacion;
  }


  public String getTipoCifradoDescifrado() {
	return tipoCifradoDescifrado;
  }

  @XmlElement
  public void setTipoCifradoDescifrado(String pTipoCifradoDescifrado) {
	this.tipoCifradoDescifrado = pTipoCifradoDescifrado;
  }
 



}
