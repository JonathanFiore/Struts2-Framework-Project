package webServices;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import jakarta.annotation.Resource;

public class ClientWebService {
	
	
	 public static String operation() {
		 try {
	            // Specifica l'URL del servizio WSDL
	            String wsdlUrl = "http://www.dneonline.com/calculator.asmx?WSDL";

	            // Crea client dinamico a partire dal WSDL
	            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
	            Client client = dcf.createClient(wsdlUrl);

	            

	            // Chiamata al metodo 'Add' del servizio
	            Object[] resultSum = client.invoke("Add", 10, 20);
	            String result = resultSum[0].toString();
	            return result;
	            
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

}
