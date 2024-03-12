package action;

import com.opensymphony.xwork2.ActionSupport;

public class InsertRequestAction extends ActionSupport{
	
	
	private String error;
	private int errorCode;
	
	public InsertRequestAction()
	{
		
	}
	
	public String insertStudent()
	{
		if(HomePage.isTypeuser())
		{
			return SUCCESS;
		}
		else
		{
			error = "Non puoi fare questa operazione";
			errorCode = 1;
			return ERROR;
		}
	}
	
	public String insertTeacher()
	{
		if(HomePage.isTypeuser())
		{
			return SUCCESS;
		}
		else
		{
			error = "Non puoi fare questa operazione";
			errorCode = 2;
			return ERROR;
		}
	}
	
	public String insertClass()
	{
		if(HomePage.isTypeuser())
		{
			return SUCCESS;
		}
		else
		{
			error = "Non puoi fare questa operazione";
			errorCode = 3;
			return ERROR;
		}
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
	

}
