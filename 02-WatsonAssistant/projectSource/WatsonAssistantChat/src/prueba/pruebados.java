package prueba;

//Imports the Google Cloud client library
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

public class pruebados {
	public static void main(String[] args) throws Exception {
		String apiKey = "0AC-rK7ZMahYaeGkPxmBhcCS1OWzrLLcxVyFjOLpDir2";
		IamOptions iAmOptions = new IamOptions.Builder()
				.apiKey(apiKey)
				.build();
		ToneAnalyzer service = new ToneAnalyzer("2017-09-21", iAmOptions);

		String text =
				  "I know the times are difficult! Our sales have been "
				      + "disappointing for the past three quarters for our data analytics "
				      + "product suite. We have a competitive data analytics product "
				      + "suite in the industry. But we need to do our job selling it! "
				      + "We need to acknowledge and fix our sales challenges. "
				      + "We can’t blame the economy for our lack of execution! "
				      + "We are missing critical sales opportunities. "
				      + "Our product is in no way inferior to the competitor products. "
				      + "Our clients are hungry for analytical tools to improve their "
				      + "business outcomes. Economy has nothing to do with it.";

				// Call the service and get the tone
				ToneOptions toneOptions = new ToneOptions.Builder()
				  .text(text)
				  .build();

				ToneAnalysis tone = service.tone(toneOptions).execute();
				System.out.println(tone);
	}
}