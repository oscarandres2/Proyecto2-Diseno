package modelo;


/**
 * Clase Administrador con los
 * atributos y m�todos necesarios para
 * su implementaci�n.
 * @author Oscar y Daniel.
 *
 */
public class Administrador {

  private String nombre;
  private String contrasena;
	
  /**
   * M�todo constructor.
   * @param pNombre
   * @param pContrasena
   */
  public Administrador(String pNombre,String pContrasena) {
	this.nombre = pNombre;
	this.contrasena = pContrasena;
  }
	
  public String getNombre() {
	return this.nombre;
  }

  public String getContrasena() {
	return this.contrasena;
  }
	
  
}
