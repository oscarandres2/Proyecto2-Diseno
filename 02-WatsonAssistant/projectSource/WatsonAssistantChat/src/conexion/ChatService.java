package conexion;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.json.JSONObject;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import logicadecontrolador.ControladorCifradoDescifrado;




@Path("/chatservice")
/**
 * Clase ChatService que sirve para establecer
 * la conexión con el servidor de IBM y realizar 
 * las operaciones necesarias.
 * @author Oscar y Daniel
 *
 */
public class ChatService {

  private String apiKey = "fOXpM_sJMpW1iEu8GiLFj_ygAfRYdCDZREb2fKoWBDOF";
  private String assistantURL = "https://api.us-south.assistant.watson.cloud.ibm.com/instances/71acf5a8-b786-4a4e-968d-058d5044dc80" ;
  private static String workspaceId = "6165712a-44c1-4f61-b215-1ec03809a760";
	
	public ChatService(){
	  try{
	  } catch (Exception e) {
		  e.printStackTrace();
		}
	}
	
	/**
	 * Método que recibe la respuesta del servidor
	 * de Watson establece las variables necesarias.
	 * @param conversationMsg
	 * @param conversationCtx
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@GET
	@Produces("application/json")
  public Response getResponse(@QueryParam("conversationMsg") String conversationMsg, @QueryParam("conversationCtx") 
    String conversationCtx) throws InstantiationException, IllegalAccessException, ClassNotFoundException {	
		
	Assistant service = getService( conversationMsg, conversationCtx);
	Context context = getContext(conversationCtx);		
	InputData input = new InputData.Builder(conversationMsg).build();	
	MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();	
	MessageResponse assistantResponse = getAsistant(service,conversationMsg, context);	
	//Las siguientes variables sirven para obtener todas las variables de watson
	String tipoOperacion = (String) context.get("tipoOperacion");
	String mensaje = (String) context.get("mensajeNormal");
	String llave = (String) context.get("llave");
	String subtipo = (String) context.get("subtipo");
	String cifra = (String) context.get("cifra");
	String posiciones = (String) context.get("posiciones");
	String msjcompleto = (String) context.get("mensajeCompleto");
	String operacionCompleta = (String) context.get("operacionCompleta");
	String terminado = (String) context.get("terminado");
	String validarInstanciacion = (String) context.get("validarInstanciacion");
	
	ArrayList<String> nuevo = new ArrayList<String>();
	ArrayList<String> validacionFiltro = new ArrayList<String>();
	
	
	if(validarMensajeIncompleto(terminado,operacionCompleta)) {
	  nuevo.add(mensaje); // 0 para el mensaje
	  nuevo.add(validarInstanciacion); //1 para validar la instanciacion
	  nuevo.add(subtipo); // 2 para el subtipo	
	  //En esta parte se añaden al array para validar el tipo de sustitucion
	  
	  validacionFiltro.add(llave);
	  validacionFiltro.add(cifra);
	  validacionFiltro.add(posiciones);
	  
	  nuevo.add(filtarEncontradoTextoIncompleto(validacionFiltro));//3
	  nuevo.add(obtenerCorreo(assistantResponse.toString()));//4 correo
	  
	  ejecutarTipoOperacion(tipoOperacion,context,nuevo);	
	
	  
	  
	}else if(validarMensajeCompleto(terminado,operacionCompleta)){
	   msjcompleto = eliminarFinal(msjcompleto);
	   nuevo.add(msjcompleto); // 0 para el mensaje
	   nuevo.add(validarInstanciacion); //1 para validar la instanciacion
	   nuevo.add(subtipo); // 2 para el subtipo	
	   String numeroEncontrado = mensaje.replaceAll("\\D+","");	
	   //añade al array para validar el tipo de sustitucion
	   
	  
	   validacionFiltro.add(llave);
	   validacionFiltro.add(numeroEncontrado);	
	   
	   System.out.println(validacionFiltro);
	   
	   nuevo.add(filtarEncontradoTextoCompleto(validacionFiltro));//3
	   nuevo.add(obtenerCorreo(assistantResponse.toString()));//4 correo
	   
	   ejecutarTipoOperacion(tipoOperacion,context,nuevo);
	 }	
	 input = new InputData.Builder(conversationMsg).build();
     options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
     assistantResponse = service.message(options).execute();  
	 JSONObject object = terminarOperacion(assistantResponse);
	 return Response.status(Status.OK).entity(object.toString()).build();
  }
	
	
  private String obtenerCorreo(String response) {
	  try {
	    JSONObject obj = new JSONObject(response);
	    String correo = obj.getJSONObject("context").getString("correo");
	    return correo;
	  } catch(Exception e) {
		  return null;
	  }
	  
  }
	
  private String filtarEncontradoTextoIncompleto(ArrayList<String> filtro) {
	  for(String posicion: filtro) {
	  if(posicion != null) {
		return posicion;
	  }
	}
    return "sin valor";  
  }

  private String filtarEncontradoTextoCompleto(ArrayList<String> filtro) {
    if(filtro.get(0) != null && !filtro.get(0).equals("")){
	  String llaveExtraida = filtrarLlave(filtro.get(0));
	  return llaveExtraida; // 3 para la llave
	}else if(!filtro.get(1).equals("")) {
	  return filtro.get(1); // 3 para el numero
	}
	return "sin valor";	
  }
	
  
  private void ejecutarTipoOperacion(String pTipoOperacion,Context pContext, ArrayList<String> pNuevo) {
    if(pTipoOperacion.equals("cifrado")) {
	  pContext.put("mensajeCifrado",llamarCifrado(pNuevo));
	  return;
	}
	pContext.put("mensajeDescifrado",llamarDescifrado(pNuevo));
  }
	
  
  private JSONObject terminarOperacion(MessageResponse assistantResponse) {
    List<String> assistantResponseList = assistantResponse.getOutput().getText();
	JSONObject object = new JSONObject();	
	String assistantResponseText = "";
	for (String tmpMsg : assistantResponseList)
	  assistantResponseText = assistantResponseText + System.lineSeparator() + tmpMsg;	
	  object.put("response", assistantResponseText);
	  object.put("context", assistantResponse.getContext());
	  return object;
  }

  
  private Assistant getService(String conversationMsg,String conversationCtx) {
	IamOptions iAmOptions = new IamOptions.Builder()
	.apiKey(apiKey)
	.build();
	Assistant service = new Assistant("2018-09-20", iAmOptions);
	service.setEndPoint(assistantURL);
	return service;
  }
	
  
  private Context getContext(String conversationCtx) {
	JSONObject ctxJsonObj = new JSONObject(conversationCtx);
	Context context = new Context();
	context.putAll(ctxJsonObj.toMap());
	return context;
  }
  
	
  private MessageResponse getAsistant(Assistant service,String conversationMsg,Context context) {	
	InputData input = new InputData.Builder(conversationMsg).build();
	MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
	MessageResponse assistantResponse = service.message(options).execute();
	return assistantResponse;
  }
	
  
  private boolean validarMensajeIncompleto(String terminado, String operacionCompleta) {
	if(terminado != null && operacionCompleta == null) { 
	  return true;
	}
	return false;
  }
	
  
  private boolean validarMensajeCompleto(String terminado, String operacionCompleta) {
	if(terminado != null && operacionCompleta != null) {
	  return true;
	}
	return false;
  }
  
	
  private String eliminarFinal(String pMensaje) {
    String mensajeFiltrado = "";
	String[] array = pMensaje.split(" ");
	int contador = 0;
	while(array.length > contador){
	  if(array[contador] == array[array.length-1]) {
	  int contador2 = 0;
	  String ultimaPalabra = "";
	  while(array[contador].length()-1 > contador2) {
		ultimaPalabra += array[contador].charAt(contador2);
		contador2++;
	  }
	  mensajeFiltrado += ultimaPalabra;
	  break;
	  }
	mensajeFiltrado += array[contador];
	mensajeFiltrado += " ";
	contador++;
	}	
	return mensajeFiltrado;	
  }
  
	
  private String filtrarLlave(String pLlave) {	
    if(pLlave == null) {
	  return "";
	}	
	Matcher matcher = Pattern.compile(" \"(.*?)\"").matcher(pLlave);
	if(matcher.find()){
	  return matcher.group(1);
	}
	return "";
  }
  
	
  private String llamarCifrado(ArrayList<String> pLista) {	
	ControladorCifradoDescifrado controlador = new ControladorCifradoDescifrado();
   	return controlador.ejecutarCifrado(pLista);
  }
	
  
  private String llamarDescifrado(ArrayList<String> pLista)  {	
	ControladorCifradoDescifrado controlador = new ControladorCifradoDescifrado(); 
	return controlador.ejecutarDescifrado(pLista);
  }

		
}
