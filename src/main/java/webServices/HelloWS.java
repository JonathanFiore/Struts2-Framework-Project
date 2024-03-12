package webServices;


//import javax.jws.WebMethod;
//import javax.jws.WebService;

import com.opensymphony.xwork2.ActionSupport;

//@WebService
public class HelloWS extends ActionSupport{
	
	String result;
	

	//@WebMethod
	public String hello(String name)
	{
		return "Hello " + name;
	}
	
	//@WebMethod
	public int sum(int a, int b)
	{
		return a + b;
	}
	
	
	public String execute()
	{
		String result = ClientWebService.operation();
		return SUCCESS;
	}
	
	
	
	
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	

}
