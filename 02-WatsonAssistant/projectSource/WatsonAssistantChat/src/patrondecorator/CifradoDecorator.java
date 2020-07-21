package patrondecorator;

import modelo.ICifrado;
import modelo.Mensaje;

public class CifradoDecorator implements ICifrado {
	
	protected ICifrado decoratedShape; 
	
	public CifradoDecorator(ICifrado pDecoratedShape) {
		this.decoratedShape = pDecoratedShape;
	}

	@Override
	public Mensaje cifrar(Mensaje pMensaje) {
		decoratedShape.cifrar(pMensaje);
		return pMensaje;
	}

	@Override
	public Mensaje descifrar(Mensaje pMensaje) {
		decoratedShape.descifrar(pMensaje);
		return pMensaje;
	}

}
