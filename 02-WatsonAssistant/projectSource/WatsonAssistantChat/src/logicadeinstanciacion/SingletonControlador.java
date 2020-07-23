package logicadeinstanciacion;

import logicadecontroladorREVISAR.ControladorCifradoDescifrado;

public class SingletonControlador {

private static ControladorCifradoDescifrado instance = new ControladorCifradoDescifrado();
	
	private SingletonControlador() {
		
	}
	
	public static ControladorCifradoDescifrado getInstance(){
		 return instance;
	}
	
}
