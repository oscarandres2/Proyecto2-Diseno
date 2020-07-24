package controlador;
import java.io.IOException;
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
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import logicadeinstanciacion.SingletonControlador;
import modelo.Administrador;



@Path("/chatservice")
/**
 * Clase ChatService que sirve para establecer
 * la conexión con el servidor de IBM y realizar 
 * las operaciones necesarias.
 * @author Oscar y Daniel
 *
 */
public class ChatService {

  private Administrador administrador = new Administrador();
  private String apiKey = "fOXpM_sJMpW1iEu8GiLFj_ygAfRYdCDZREb2fKoWBDOF";
  private String assistantURL = "https://api.us-south.assistant.watson.cloud.ibm.com/instances/71acf5a8-b786-4a4e-968d-058d5044dc80" ;
  private static String workspaceId = "1d5f5b30-5cca-4c72-b694-8cdbc3bbe978";
  /**
   * Constructor de la clase.	
   */
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
	 * @throws JAXBException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	@GET
	@Produces("application/json")
  public Response getResponse(@QueryParam("conversationMsg") String conversationMsg, @QueryParam("conversationCtx") 
    String conversationCtx) throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParserConfigurationException, 
    SAXException, IOException, JAXBException {	
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
	
	//modulo administrador
	String moduloAdministrador = (String) context.get("moduloAdministrador");
	String usuario = (String) context.get("usuario");
	String contrasena;
	String moduloAdministradorTerminado = (String) context.get("moduloAdministradorTerminado");
	String tipoCriterio = (String) context.get("tipoCriterio");
	String fuenteBitacora = (String) context.get("fuenteBitacora");
	
	ArrayList<String> nuevo = new ArrayList<String>();
	ArrayList<String> validacionFiltro = new ArrayList<String>();
	
	//validacion del administrador
  if(moduloAdministrador != null) {
    contrasena = obtenerContraAdmin(assistantResponse);
	if(validacionUsuarioContrasena(usuario,contrasena)) {
	  context.put("usuarioValidado","si");
	  moduloAdministradorTerminado = buscarModuloAdminTerminado(assistantResponse);
	  if(moduloAdministradorTerminado != null) {
		fuenteBitacora = obtenerFuente(assistantResponse);
		tipoCriterio = obtenerCriterio(assistantResponse);
		context.put("historial",mostrarFuenteTipoCriterio(tipoCriterio,fuenteBitacora));
	  }	
	}
}
	//nuevo
	
  else if(validarMensajeIncompleto(terminado,operacionCompleta)) {
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
  } else if(validarMensajeCompleto(terminado,operacionCompleta)){
     msjcompleto = eliminarFinal(msjcompleto);
     nuevo.add(msjcompleto); // 0 para el mensaje
     nuevo.add(validarInstanciacion); //1 para validar la instanciacion
     nuevo.add(subtipo); // 2 para el subtipo	
     String numeroEncontrado = mensaje.replaceAll("\\D+","");	
   //añade al array para validar el tipo de sustitucion
     validacionFiltro.add(llave);
     validacionFiltro.add(numeroEncontrado);	
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
	
	
  private boolean validarUsuario(String pAdministrador, String pContrasena) {
	if(pAdministrador.equals(administrador.getNombre()) && pContrasena.equals(administrador.getContrasena())) {
	  return true;
	}
	return false;
  }

	
  private String obtenerFuente(MessageResponse pAssistantResponse) {
	try {
      JSONObject obj = new JSONObject(pAssistantResponse);
      String fuente = obj.getJSONObject("context").getString("fuenteBitacora");
      return fuente;
    } catch(Exception e) {
	    return null;
    }  
  }
  
  
  private String obtenerCriterio(MessageResponse pAssistantResponse) {
    try {
      JSONObject obj = new JSONObject(pAssistantResponse);
      String fuente = obj.getJSONObject("context").getString("tipoCriterio");
      return fuente;
    } catch(Exception e) {
	    return null;
    }  
  }
  
  
  private boolean validacionUsuarioContrasena(String pUsuario, String pContrasena) {
    if(pUsuario != null && pContrasena != null && validarUsuario(pUsuario,pContrasena)) {
	  return true;
    }
    return false;
  }
  
  
  private String obtenerCorreo(String pResponse) {
	try {
	  JSONObject obj = new JSONObject(pResponse);
	  String correo = obj.getJSONObject("context").getString("correo");
	  return correo;
	} catch(Exception e) {
	    return null;
	  } 
  }
  
  
  private String buscarModuloAdminTerminado(MessageResponse pAssistantResponse) {
    try {
      JSONObject obj = new JSONObject(pAssistantResponse);
      String correo = obj.getJSONObject("context").getString("moduloAdministradorTerminado");
      return correo;
    } catch(Exception e) {
	    return null;
    }
  }
  
  
  private String obtenerContraAdmin(MessageResponse pAssistantResponse) {
    try {
      JSONObject obj = new JSONObject(pAssistantResponse);
      String correo = obj.getJSONObject("context").getString("contrasena");
      return correo;
    } catch(Exception e) {
	    return null;
    }  
  }
  
	
  private String filtarEncontradoTextoIncompleto(ArrayList<String> pFiltro) {
	for(String posicion: pFiltro) {
	  if(posicion != null) {
		return posicion;
	  }
	}
    return "sin valor";  
  }
  

  private String filtarEncontradoTextoCompleto(ArrayList<String> pFiltro) {
    if(pFiltro.get(0) != null && !pFiltro.get(0).equals("")){
	  String llaveExtraida = filtrarLlave(pFiltro.get(0));
	  return llaveExtraida; // 3 para la llave
	}else if(!pFiltro.get(1).equals("")) {
	  return pFiltro.get(1); // 3 para el numero
	}
	return "sin valor";	
  }
  
  private void resetVariables(Context pContext) {
	pContext.put("terminado",null);
	pContext.put("operacionCompleta",null);
  }
  
  
  private void ejecutarTipoOperacion(String pTipoOperacion,Context pContext, ArrayList<String> pNuevo) {
	String cifrado;
	String descifrado;
	if(pTipoOperacion.equals("cifrado")) {
	  if(!(cifrado = llamarCifrado(pNuevo)).equals("(OPERACIÓN NO REALIZADA)")) {
		pContext.put("mensajeCifrado",cifrado);
		resetVariables(pContext);
		return;
	  }
	}
	else if(!(descifrado = llamarDescifrado(pNuevo)).equals("(OPERACIÓN NO REALIZADA)")) {
	  pContext.put("mensajeDescifrado",descifrado);
	  resetVariables(pContext);
	  return;
	}
	pContext.put("noNiquette","si");
	resetVariables(pContext);
  }
	
  
  private JSONObject terminarOperacion(MessageResponse pAssistantResponse) {
    List<String> assistantResponseList = pAssistantResponse.getOutput().getText();
	JSONObject object = new JSONObject();	
	String assistantResponseText = "";
	for (String tmpMsg : assistantResponseList)
	  assistantResponseText = assistantResponseText + System.lineSeparator() + tmpMsg;	
	  object.put("response", assistantResponseText);
	  object.put("context", pAssistantResponse.getContext());
	  return object;
  }

  
  private Assistant getService(String pConversationMsg, String pConversationCtx) {
	IamOptions iAmOptions = new IamOptions.Builder()
	.apiKey(apiKey)
	.build();
	Assistant service = new Assistant("2018-09-20", iAmOptions);
	service.setEndPoint(assistantURL);
	return service;
  }
	
  
  private Context getContext(String pConversationCtx) {
	JSONObject ctxJsonObj = new JSONObject(pConversationCtx);
	Context context = new Context();
	context.putAll(ctxJsonObj.toMap());
	return context;
  }
  
	
  private MessageResponse getAsistant(Assistant pService,String pConversationMsg,Context pContext) {	
	InputData input = new InputData.Builder(pConversationMsg).build();
	MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(pContext).build();
	MessageResponse assistantResponse = pService.message(options).execute();
	return assistantResponse;
  }
	
  
  private boolean validarMensajeIncompleto(String pTerminado, String pOperacionCompleta) {
	if(pTerminado != null && pOperacionCompleta == null) { 
	  return true;
	}
	return false;
  }
	
  
  private boolean validarMensajeCompleto(String pTerminado, String pOperacionCompleta) {
	if(pTerminado != null && pOperacionCompleta != null) {
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
	return SingletonControlador.getInstance().ejecutarCifrado(pLista);
  }
	
  
  private String llamarDescifrado(ArrayList<String> pLista)  {	
	return SingletonControlador.getInstance().ejecutarDescifrado(pLista);
  }
  
  
  private String mostrarFuenteTipoCriterio(String pTipoCriterio, String pTipoFuente) throws IOException, ParserConfigurationException, 
  SAXException, JAXBException {
	return SingletonControlador.getInstance().mostrarFuenteTipoCriterio(pTipoCriterio, pTipoFuente);	  
  }

		
}
