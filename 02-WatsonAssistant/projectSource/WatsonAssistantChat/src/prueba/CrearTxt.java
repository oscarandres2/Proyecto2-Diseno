package prueba;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modelo.XML;

public class CrearTxt {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
	XML prueba = new XML();
	prueba.crearBitacora();
	prueba.leerBitacora();
	}
		

}
