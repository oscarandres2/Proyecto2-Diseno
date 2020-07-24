package modelo;


/**
 * Clase Administrador con los
 * atributos y métodos necesarios para
 * su implementación.
 * @author Oscar y Daniel.
 *
 */
public class Administrador {

  private String nombre;
  private String contrasena;
	
  /**
   * Método constructor.
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
