package modelo;

public class Administrador {

	private String nombre;
	private String contrasena;
	
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
