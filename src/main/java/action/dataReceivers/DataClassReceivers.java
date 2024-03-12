package action.dataReceivers;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.math.NumberUtils;

import com.opensymphony.xwork2.ActionSupport;

import Beans.SchoolClass;
import action.HomePage;
import dbConnection.DBCPDataSource;
import model.DAO;

public class DataClassReceivers extends ActionSupport{
	
	
	private Connection c = null;
	private DAO dbAccess = null;
	private SchoolClass classBean;
	
	private String error;
	private int errorCode;

	public DataClassReceivers()
	{
		
	}
	
	public String execute() {
		
		
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		boolean res;
		errorCode = 3;
		
		try
		{
			if(c!=null)
			{
				if(HomePage.isTypeuser())
				{
					//I already have classBean filled with the right data
					res = dbAccess.insertDataClass(classBean);
					c.close();
					
					if(res)
					{
						return SUCCESS;
					}
					else
					{
						error = "Errore inserimento in DB";
						return ERROR;
					}
				}
				else
				{
					c.close();
					error = "Non puoi effettuare questa operazione";
					return ERROR;
				}
			}
			else
			{
				error = "Errore apertura connessione DB";
				return ERROR;
			}
			
		}catch(SQLException e)
		{
			error = "Eccezzione - Errore";
			return ERROR;
		}
	}
	
	
	public void validate()
	{
		String regex = "\\d\\^[A-Za-z]";
		
		if(classBean == null)
		{
			addActionError("Student is null");
		}
		else
		{
			if(classBean.getNome().length() == 0)
			{
				addFieldError("classBean.nome", "Name is required.");
			}
			
			if(!classBean.getNome().matches(regex))
			{
				addFieldError("classBean.nome", "Name format incorrect!.");
			}
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

	public SchoolClass getClassBean() {
		return classBean;
	}

	public void setClassBean(SchoolClass classBean) {
		this.classBean = classBean;
	}
	
}