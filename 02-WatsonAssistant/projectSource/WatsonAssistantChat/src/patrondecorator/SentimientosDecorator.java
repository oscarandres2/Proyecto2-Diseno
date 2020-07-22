package patrondecorator;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.discovery.v1.model.CreateCollectionOptions.Language;
import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v3.model.Translation;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

import modelo.ICifrado;
import modelo.Mensaje;
import prueba.OperacionUsuario;

public class SentimientosDecorator extends CifradoDecorator {

	private String apiKeyToneAnalyzer = "0AC-rK7ZMahYaeGkPxmBhcCS1OWzrLLcxVyFjOLpDir2";
	private IamOptions iAmOptionsToneAnalyzer = new IamOptions.Builder().apiKey(apiKeyToneAnalyzer).build();
	private ToneAnalyzer service1 = new ToneAnalyzer("2017-09-21", iAmOptionsToneAnalyzer);
	
	private String apiKeyTranslator = "rjkF7lgBgUhHFgi51DT12C1KWf_YknVn2FlEdo6C_X-6";
	private IamOptions iAmOptionsTranslator = new IamOptions.Builder().apiKey(apiKeyTranslator).build();
	private LanguageTranslator service2 = new LanguageTranslator("2018-05-01", iAmOptionsTranslator);
	
	public SentimientosDecorator(ICifrado pDecoratedShape,OperacionUsuario operacion) {
		super(pDecoratedShape,operacion);
	}
	
	public Mensaje cifrar(Mensaje pMensaje) {
		if(isNitequette(pMensaje.getMensajeViejo())) {
			decoratedShape.cifrar(pMensaje);
			pMensaje.setMensajeCifrado(pMensaje.getMensajeCifrado()+".       ANÁLISIS DE SENTIMIENTOS: "+traducirTextoInglesAEspanol(sentimientosEncontrados(traducirTextoEspanolAIngles(pMensaje.getMensajeViejo()))));
			return pMensaje;
		}
		pMensaje.setMensajeCifrado("(OPERACIÓN NO MOSTRADA)");
		return pMensaje;
	}
	
	public Mensaje descifrar(Mensaje pMensaje) {
		if(isNitequette(decoratedShape.descifrar(pMensaje).getMensajeDescifrado())) {
			decoratedShape.descifrar(pMensaje);
			pMensaje.setMensajeDescifrado(pMensaje.getMensajeDescifrado()+".       ANÁLISIS DE SENTIMIENTOS: "+traducirTextoInglesAEspanol(sentimientosEncontrados(traducirTextoEspanolAIngles(pMensaje.getMensajeDescifrado()))));
			return pMensaje;
		}
		//pMensaje.setMensajeDescifrado(null);
		pMensaje.setMensajeDescifrado("(OPERACIÓN NO MOSTRADA)");
		return pMensaje;
	}
	
	private ArrayList<String> sentimientosEncontrados(String pTexto) {
		
		ToneOptions toneOptions = new ToneOptions.Builder()
				  .text(pTexto)
				  .build();
		ToneAnalysis tone = service1.tone(toneOptions).execute();
		com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis x = tone.getDocumentTone();
	    
		ArrayList<String> sentimientos = new ArrayList<String>();
		try {
		    JSONObject obj = new JSONObject(x);
		    JSONArray arr = obj.getJSONArray("tones");
		    
		    for (int i = 0; i < arr.length(); i++){
		        String sentimiento = arr.getJSONObject(i).getString("toneName");
		        sentimientos.add(sentimiento);
		     }
		    
		}catch(Exception e) {
		}
		System.out.println(sentimientos);
		return sentimientos;
	}
	
	private String traducirTextoInglesAEspanol(ArrayList<String> pSentimientos) {
	
	  String sentimientosEncontrados = "";
	  for(String sentimiento: pSentimientos) {
		  TranslateOptions translateOptions = new TranslateOptions.Builder()
	      		  .addText(sentimiento)
	      		  .source(Language.EN)
	      		  .target(Language.ES)
	      		  .build();
	  	com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult translationResult = service2.translate(translateOptions).execute();
	  	List<Translation> sentimientoTraducido = translationResult.getTranslations();    
      	String textoTraducido = sentimientoTraducido.get(0).getTranslationOutput().toString();
      	sentimientosEncontrados+= textoTraducido;
      }
	  return sentimientosEncontrados;
	}
	
	private String traducirTextoEspanolAIngles(String pTexto) {
	  TranslateOptions translateOptions = new TranslateOptions.Builder()
	      		  .addText(pTexto)
	      		  .source(Language.ES)
	      		  .target(Language.EN)
	      		  .build();
	  com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult translationResult = service2.translate(translateOptions).execute();
	  List<Translation> sentimientoTraducido = translationResult.getTranslations();    
      String textoTraducido = sentimientoTraducido.get(0).getTranslationOutput().toString();
      return textoTraducido;
	}
	
	public boolean isNitequette(String pTexto) {

        String textoTraducido = traducirTextoEspanolAIngles(pTexto);
		ArrayList<String> sentimientos = sentimientosEncontrados(textoTraducido);
		
		if(validarSentimientos(sentimientos)) {
			return true;
		}
		return false;
	}
	
	private boolean validarSentimientos(ArrayList<String> sentimientos) {
		if(!sentimientos.isEmpty()) {
			for(String sentimiento: sentimientos) {
				if(sentimiento.equals("Anger")) {
					return false;
				}
			}
		}
		return true;
	}

}
