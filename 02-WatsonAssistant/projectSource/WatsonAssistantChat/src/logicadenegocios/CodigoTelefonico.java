package logicadenegocios;
import java.util.ArrayList;
import util.ConversionAscii;



/**
 * Clase CodigoTelefonico que contiene los 
 * atributos y métodos necesarios
 * @author Oscar y Daniel
 *
 */
public class CodigoTelefonico implements ICifrado{

  public ArrayList<ArrayList<Character>> alfabeto = asignarAlfabeto();
  
  /**
   * Método que permite realizar la
   * función de cifra el mensaje.
   */
  public Mensaje cifrar(Mensaje pMensaje) {	
	String mensajeEncriptado = "";
	char[] mensajeSeparado = pMensaje.getMensajeViejo().toCharArray();	
	for(char letra: mensajeSeparado) {
	  if(letra == ' ') {
	  mensajeEncriptado += '*';
	  mensajeEncriptado += ' ';
	  } else{
	      ArrayList<Integer> letraEncriptada = encriptarLetra(letra);
		  mensajeEncriptado += letraEncriptada.get(0).toString()+letraEncriptada.get(1).toString() + ' ';
	    }
	}
	pMensaje.setMensajeCifrado(mensajeEncriptado);
	return pMensaje;
	}
  
  
  /**
   * Método que permite realizar la
   * función de descifra el mensaje.
   */
  public Mensaje descifrar(Mensaje pMensaje) {
    String mensajeDesencriptado = "";
    String[] mensajeSeparado = pMensaje.getMensajeCifrado().split(" ");
    for(String letra: mensajeSeparado) {	
      mensajeDesencriptado += desEncriptarLetra(letra).toLowerCase();
    }
    pMensaje.setMensajeDescifrado(mensajeDesencriptado);
    return pMensaje;
  }
	
	
  private ArrayList<ArrayList<Character>> asignarAlfabeto() {
    ArrayList<ArrayList<Character>> alfabeto = new ArrayList<ArrayList<Character>>();
    int contadorNumeros = 0;
	int contadorAlfabeto = 65;
	while(contadorNumeros < 8){
	  ArrayList<Character> subPosicion = new ArrayList<Character>();	
	  int contadorAgregados = 0;
	  int condicionParada = determinarCondicionParada(contadorNumeros);
	  while(contadorAgregados < condicionParada) {		
	    Character letra = ConversionAscii.asciiLetra(contadorAlfabeto);
	    subPosicion.add(letra);
	    contadorAlfabeto++;
	    contadorAgregados++;
	  }
	  alfabeto.add(subPosicion);
	  contadorNumeros++;
	}
    return alfabeto;
  }
  
  
  private String desEncriptarLetra(String pLetra) {	
    String mensajefinal = "";
	if(!pLetra.equals("*")) {
      String[] numerosStr = pLetra.split("");
	  System.out.println(pLetra);
	  return (mensajefinal += alfabeto.get(Integer.parseInt(numerosStr[0])-2).get(Integer.parseInt(numerosStr[1])-1));
	}
	return (mensajefinal = " ");
  }
  
  
  private int determinarCondicionParada(int pContadorNumeros) {
    if(pContadorNumeros == 7 || pContadorNumeros == 5) {
	  return 4;
	 }
	return 3;
  }
  
  
  private ArrayList<Integer> encriptarLetra(Character pLetra) {
	ArrayList<Integer> letra = new ArrayList<Integer>();	
	int posicion = 0;
	while(posicion < 8) {		
	  int subPosicion = 0;
	  while(alfabeto.get(posicion).size() > subPosicion) {		
	    if(alfabeto.get(posicion).get(subPosicion) == Character.toUpperCase(pLetra)) {
		  letra.add(posicion+2);
		  letra.add(subPosicion+1);
		  return letra;
		}
	  subPosicion++;
	  }		
	posicion++;
	}
	return null;	
  }
	
  
}
