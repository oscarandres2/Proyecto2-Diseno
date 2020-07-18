package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import modelo.CSV;
import modelo.TramaPlana;
import modelo.XML;

public class APl {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
	XML prueba = new XML();
	prueba.crearBitacora();
	prueba.leerBitacora();
	
	CSV nuevo = new CSV();
	nuevo.crearBitacora();
	nuevo.leerBitacora();
	
	TramaPlana txt = new TramaPlana();
	txt.crearBitacora();
	txt.leerBitacora();

	}

}
