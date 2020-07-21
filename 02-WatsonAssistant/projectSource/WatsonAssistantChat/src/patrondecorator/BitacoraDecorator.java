package patrondecorator;

import modelo.ICifrado;
import modelo.Mensaje;

public class BitacoraDecorator extends SentimientosDecorator{

	public BitacoraDecorator(ICifrado pDecoratedShape) {
		super(pDecoratedShape);
	}

	public Mensaje cifrar(Mensaje pMensaje) {
		super.cifrar(pMensaje);
		//agregarLlamado de bitacora
		return pMensaje;
	}
	
	public Mensaje descifrar(Mensaje pMensaje) {
		super.descifrar(pMensaje);
		//agregarLlamado de bitacora
		return pMensaje;
	}
	
}
