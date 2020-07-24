package modelo;


/**
 * Clase Administrador con los
 * atributos y m�todos necesarios para
 * su implementaci�n.
 * @author Oscar y Daniel.
 *
 */
public class Administrador {

  private String nombre = "daniel";
  private String contrasena = "123";
	
  /**
   * M�todo constructor.
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
