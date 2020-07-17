package modelo;

/**
 * Clase Vigenere que contiene los atributos
 * y métodos necesarios.
 * @author Oscar y Daniel 
 *
 */
public class Vigenere extends SustitucionClave {
	
  private String cifra;
  
  public Vigenere(String pCifra) {
    this.cifra = pCifra;
  }
  

  /**
   * Método que permite realizar la
   * función de cifrar el mensaje.
   */
  public Mensaje cifrar(Mensaje pMensaje) {	
	String mensajeEncriptado = "";
	char[] mensajeSeparado = pMensaje.getMensajeViejo().toCharArray();
	int posicionCifra = 1;	
	for(char letra: mensajeSeparado) {
	  if(letra == ' ') {
	  mensajeEncriptado += ' ';
	  posicionCifra = cambiarPosicionCifra(2);		
	  } else{
		  int indexLetraActual = alfabeto.indexOf(Character.toUpperCase(letra));	
		  int indexLetraEncriptada = getIndexLetraEncriptada(posicionCifra,indexLetraActual);	
		  validarTamanoEncriptar(indexLetraEncriptada);	
		  mensajeEncriptado += validarCaps(letra,alfabeto.get(indexLetraEncriptada));	
		  posicionCifra = cambiarPosicionCifra(posicionCifra);
		}	
	}
	pMensaje.setMensajeCifrado(mensajeEncriptado);
	return pMensaje;
  }
  
  
  /**
   * Método que permite realizar la función
   * de descifrar el mensaje.
   */
  public Mensaje descifrar(Mensaje pMensajeCifrado) {	
	String mensajeDesencriptado = "";
	char[] mensajeSeparado = pMensajeCifrado.getMensajeCifrado().toCharArray();
	int posicionCifra = 1;	
	for(char letra: mensajeSeparado) {		
	  if(letra == ' ') {
	    mensajeDesencriptado += ' ';
		posicionCifra = cambiarPosicionCifra(2);		
	  } else{
		  int indexLetraActual = alfabeto.indexOf(Character.toUpperCase(letra));	
		  int indexLetraEncriptada = getIndexLetraDesencriptada(posicionCifra,indexLetraActual);	
		  indexLetraEncriptada = validarTamanoDesencriptar(indexLetraEncriptada);	
		  mensajeDesencriptado += validarCaps(letra,alfabeto.get(indexLetraEncriptada));	
		  posicionCifra = cambiarPosicionCifra(posicionCifra);
	    }
	}	
	pMensajeCifrado.setMensajeDescifrado(mensajeDesencriptado);
	return pMensajeCifrado;	
  }
  
  
  private int validarTamanoEncriptar(int pIndexLetraEncriptada) {
	if(pIndexLetraEncriptada < 26) {
	  pIndexLetraEncriptada -= 26;
	}
	return pIndexLetraEncriptada;
  }
  
  
  private int validarTamanoDesencriptar(int pIndexLetraDesencriptada) {
    if(pIndexLetraDesencriptada < 0) {
	  pIndexLetraDesencriptada += 26;
	}
	return pIndexLetraDesencriptada;
  }
  
  
  private int cambiarPosicionCifra(int pPosicionActual) {
    if(pPosicionActual == 1) {
	  return 2;
	}
	return 1;
  }
  
  
  private int getIndexLetraDesencriptada(int pPosicionCifra,int pIndexLetraActual) {	
	int indexLetraDesencriptada = 0;	
	if(pPosicionCifra == 1) {		
	  indexLetraDesencriptada = pIndexLetraActual -Integer.parseInt(cifra)/10;
	  pPosicionCifra = 2;
	  return indexLetraDesencriptada;	
	}	
	indexLetraDesencriptada =  pIndexLetraActual - Integer.parseInt(cifra)%10; 
	return indexLetraDesencriptada;
  }
  
  
  private int getIndexLetraEncriptada(int pPosicionCifra,int pIndexLetraActual) {	
	int indexLetraEncriptada = 0;	
	if(pPosicionCifra == 1) {		
	  indexLetraEncriptada = Integer.parseInt(cifra)/10 + pIndexLetraActual;
	  pPosicionCifra = 2;
	  return indexLetraEncriptada;	
	}	
	indexLetraEncriptada = Integer.parseInt(cifra)%10 + pIndexLetraActual; 
	return indexLetraEncriptada;
	
  }

  
}
