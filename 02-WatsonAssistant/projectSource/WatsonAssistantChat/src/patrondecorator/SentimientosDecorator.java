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

public class SentimientosDecorator extends CifradoDecorator {

	public SentimientosDecorator(ICifrado pDecoratedShape) {
		super(pDecoratedShape);
	}
	
	public Mensaje cifrar(Mensaje pMensaje) {
		if(isNitequette(pMensaje.getMensajeViejo())) {
			decoratedShape.cifrar(pMensaje);
			return pMensaje;
		}
		return pMensaje;
	}
	
	public Mensaje descifrar(Mensaje pMensaje) {
		if(isNitequette(decoratedShape.descifrar(pMensaje).getMensajeDescifrado())) {
			decoratedShape.descifrar(pMensaje);
			return pMensaje;
		}
		pMensaje.setMensajeDescifrado(null);
		return pMensaje;
	}
	
	private boolean isNitequette(String pTexto) {
		
		String apiKeyToneAnalyzer = "0AC-rK7ZMahYaeGkPxmBhcCS1OWzrLLcxVyFjOLpDir2";
		IamOptions iAmOptionsToneAnalyzer = new IamOptions.Builder().apiKey(apiKeyToneAnalyzer).build();
		ToneAnalyzer service1 = new ToneAnalyzer("2017-09-21", iAmOptionsToneAnalyzer);
		
		String apiKeyTranslator = "rjkF7lgBgUhHFgi51DT12C1KWf_YknVn2FlEdo6C_X-6";
		IamOptions iAmOptionsTranslator = new IamOptions.Builder().apiKey(apiKeyTranslator).build();
		LanguageTranslator service2 = new LanguageTranslator("2018-05-01", iAmOptionsTranslator);
		
		TranslateOptions translateOptions = new TranslateOptions.Builder()
	      		  .addText(pTexto)
	      		  .source(Language.ES)
	      		  .target(Language.EN)
	      		  .build();
			
		com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult translationResult = service2.translate(translateOptions).execute();
		List<Translation> sentimientoTraducido = translationResult.getTranslations();    
        String textoTraducido = sentimientoTraducido.get(0).getTranslationOutput().toString();
		
		
		ToneOptions toneOptions = new ToneOptions.Builder()
				  .text(textoTraducido)
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
		    
		}catch(Exception e) {}
		
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
