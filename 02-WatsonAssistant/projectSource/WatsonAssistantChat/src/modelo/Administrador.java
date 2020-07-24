package modelo;


/**
 * Clase Administrador con los
 * atributos y métodos necesarios para
 * su implementación.
 * @author Oscar y Daniel.
 *
 */
public class Administrador {

  private String nombre = "daniel";
  private String contrasena = "123";
	
  /**
   * Método constructor.
   * @param pNombre
   * @param pContrasena
   */
  public Administrador() {
  }
	
  public String getNombre() {
	return this.nombre;
  }

  public String getContrasena() {
	return this.contrasena;
  }
	
  
}
