package logicadenegocios;
import logicadenegocios.Transposicion;




public class MensajeInverso extends Transposicion {
	
  public Mensaje cifrar(Mensaje pMensaje) {	  
	return mensajeInverso(pMensaje);
  }
	
  
  public Mensaje descifrar(Mensaje pMensaje) {
    mensajeInverso(pMensaje);
    pMensaje.setMensajeDescifrado(pMensaje.getMensajeCifrado());
    return pMensaje;
  }
  
  
  private Mensaje mensajeInverso(Mensaje pMensaje) {
	String textoMensaje = pMensaje.getMensajeViejo();
    String mensajeInvertido = "";
    String [] listaPalabras = textoMensaje.split("\\s+");
    for(int largoMensaje = listaPalabras.length - 1; largoMensaje >=0;largoMensaje--) {
      mensajeInvertido += invertirPalabra(listaPalabras[largoMensaje]) + " ";  	
    }
    pMensaje.setMensajeCifrado(mensajeInvertido);
    return pMensaje;
  }
		

}
