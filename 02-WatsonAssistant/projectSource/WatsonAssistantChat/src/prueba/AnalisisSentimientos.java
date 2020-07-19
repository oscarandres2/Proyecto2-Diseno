package prueba;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

//Imports the Google Cloud client library
import com.ibm.watson.developer_cloud.discovery.v1.model.CreateCollectionOptions.Language;
import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v3.model.Translation;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;


public class AnalisisSentimientos {
	
	private  String apiKeyToneAnalyzer = "0AC-rK7ZMahYaeGkPxmBhcCS1OWzrLLcxVyFjOLpDir2";
	private  IamOptions iAmOptionsToneAnalyzer = new IamOptions.Builder().apiKey(apiKeyToneAnalyzer).build();
	private  ToneAnalyzer service1 = new ToneAnalyzer("2017-09-21", iAmOptionsToneAnalyzer);
	
	private String apiKeyTranslator = "rjkF7lgBgUhHFgi51DT12C1KWf_YknVn2FlEdo6C_X-6";
	private  IamOptions iAmOptionsTranslator = new IamOptions.Builder().apiKey(apiKeyTranslator).build();
	private  LanguageTranslator service2 = new LanguageTranslator("2018-05-01", iAmOptionsTranslator);
	
	
	public boolean isNitequette(String texto) {
		
		TranslateOptions translateOptions = new TranslateOptions.Builder()
      		  .addText(texto)
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
	
	
//	public static void main(String[] args) throws Exception {
//
//		nitequette("La vida es muy hermosa");
//		
//		String text =
//				  "I know the times are difficult! Our sales have been "
//				      + "disappointing for the past three quarters for our data analytics "
//				      + "product suite. We have a competitive data analytics product "
//				      + "suite in the industry. But we need to do our job selling it! "
//				      + "We need to acknowledge and fix our sales challenges. "
//				      + "We can’t blame the economy for our lack of execution! "
//				      + "We are missing critical sales opportunities. "
//				      + "Our product is in no way inferior to the competitor products. "
//				      + "Our clients are hungry for analytical tools to improve their "
//				      + "business outcomes. Economy has nothing to do with it.";
//
//	// Call the service and get the tone
//		ToneOptions toneOptions = new ToneOptions.Builder()
//		  .text(text)
//		  .build();
//	
//		ToneAnalysis tone = service1.tone(toneOptions).execute();
//		com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis x = tone.getDocumentTone();
//		
//		try {
//		    JSONObject obj = new JSONObject(x);
//		    JSONArray arr = obj.getJSONArray("tones");
//		    ArrayList<String> sentimientosProcentaje = new ArrayList<String>();
//		    
//		    for (int i = 0; i < arr.length(); i++)
//		    {
//		        String sentimientoingles = arr.getJSONObject(i).getString("toneName");
//		        
//		        //traducir
//		        TranslateOptions translateOptions = new TranslateOptions.Builder()
//		        		  .addText(sentimientoingles)
//		        		  .source(Language.EN)
//		        		  .target(Language.ES)
//		        		  .build();
//		        com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult translationResult = service2.translate(translateOptions).execute();
//		        
//		        List<Translation> sentimientoTraducido = translationResult.getTranslations();
//			    
//		        String sentimientoTRaducidoFinal = sentimientoTraducido.get(0).getTranslationOutput().toString();
//		        //fin traducir
//		        
//		        
//		        Double porcentaje = arr.getJSONObject(i).getDouble("score");
//		        sentimientosProcentaje.add(sentimientoTRaducidoFinal+" "+porcentaje.toString());
//		    }
//		    System.out.println(sentimientosProcentaje);
//
//		  } catch(Exception e) {
//			  System.out.println(e);
//		  }

}