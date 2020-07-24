package modelo;

public class Administrador {

	private String nombre;
	private String contrasena;
	
	public Administrador(String nombre,String contrasena) {
		this.nombre = nombre;
		this.contrasena = contrasena;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getContrasena() {
		return this.contrasena;
	}
	
}
