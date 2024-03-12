package action.remove;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.math.NumberUtils;

import com.opensymphony.xwork2.ActionSupport;

import action.HomePage;
import dbConnection.DBCPDataSource;
import model.DAO;

public class Remove extends ActionSupport{
	
	
	private Connection c = null;
	private DAO dbAccess = null;
	private String cf;
	private String id;
	
	private String error;
	private int errorCode;

	public Remove()
	{
		
	}
	
	public String removeStudent() {
		
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		boolean res;
		errorCode = 1;
		
		try
		{
			if(c!=null)
			{
				if(HomePage.isTypeuser())
				{
					if(cf != null && cf.length() > 0 && cf.length() == 16)
					{
						res = dbAccess.removeStudent(cf);
						c.close();
						
						if(res)
						{
							return SUCCESS;
						}
						else
						{
							error = "Errore rimozione studente";
							return ERROR;
						}
					}
					else
					{
						error = "Errore formattazione dati";
						c.close();
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
				error = "Errore conessione DB";
				return ERROR;
			}
			
		}
		catch(SQLException e){
			error = "Eccezzione - Errore";
			return ERROR;
		}
		
	}
	
	public String removeTeacher()
	{
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		boolean res;
		errorCode = 2;
		
		try
		{
			if (c!= null)
			{
				if(HomePage.isTypeuser())
				{
					if(cf != null && cf.length() > 0 && cf.length() == 16)
					{
						res = dbAccess.removeTeacher(cf);
						c.close();
						
						if(res)
						{
							return SUCCESS;
						}
						else
						{
							error = "Errore rimozione insegnante";
							return ERROR;
						}
					}
					else
					{
						error = "Errore formattazione dati";
						c.close();
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
				error = "Errore accesso DB";
				return ERROR;
			}
			
		}catch(SQLException e)
		{
			error = "Eccezzione - Errore";
			return ERROR;
		}
	}
	
	
	public String removeClass()
	{
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
					if(id != null && NumberUtils.isCreatable(id) && String.valueOf(Integer.parseInt(id)).equals(id))
					{
						res = dbAccess.removeClass(Integer.parseInt(id));
						c.close();
						
						if(res)
						{
							return SUCCESS;
						}
						else
						{
							error = "Errore rimozione classe";
							return ERROR;
						}
					}
					else
					{
						error = "Errore formattazione dati";
						c.close();
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
				error = "Errore connessione DB"; 
				return ERROR;
			}
			
		}catch(SQLException e)
		{
			error = "Eccezzione - Errore";
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

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
