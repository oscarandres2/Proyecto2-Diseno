package patrondecorator;

import modelo.ICifrado;
import modelo.Mensaje;
import modelo.OperacionUsuario;

public class CifradoDecorator implements ICifrado {
	
	protected ICifrado decoratedShape; 
	protected OperacionUsuario operacion;
	
	public CifradoDecorator(ICifrado pDecoratedShape,OperacionUsuario operacion) {
		this.decoratedShape = pDecoratedShape;
		this.operacion = operacion;
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
