package logicadeinstanciacion;

import prueba.AnalisisSentimientos;

public class SingletonAnalsisSentimientos {

	private static AnalisisSentimientos instance = new AnalisisSentimientos();
	
	private SingletonAnalsisSentimientos() {
		
	}
	
	public static AnalisisSentimientos getInstance(){
		 return instance;
	}
	//es para probar
//	public static void main(String[] args) {
//		
//		AnalisisSentimientos nuevo = new AnalisisSentimientos();
//		if(nuevo.isNitequette("odio esta maldita vida")){
//			System.out.print("siuuu");
//		}else {
//			System.out.print("chupemela vulgar");
//		}
//		
//	}
	
	
}
