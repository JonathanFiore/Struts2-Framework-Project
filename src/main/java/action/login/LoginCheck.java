package action.login;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.action.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import Beans.User;
import action.HomePage;
import dbConnection.DBCPDataSource;
import hash.HashGenerator;
import model.DAO;

public class LoginCheck extends ActionSupport implements SessionAware {
	
	private Connection c = null;
	private DAO dbAccess = null;
	private Map<String, Object> session;
	
	private User utente;
	private User u;
	private String error = "";
	
	public LoginCheck()
	{
		
	}
	
	
	@Override
	public void withSession(Map<String, Object> session) {
		this.session = session;
	}
	
	
	public String execute()
	{
		
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		
		try
		{
			if(c!=null)
			{
				u = dbAccess.getUser(utente.getUser());
				if(u!=null)
				{
					//Check if user is valid:
					if(HashGenerator.generateSHA256(utente.getPass()).equals(u.getPass()))
					{
						c.close();
						//Session update:
						session.put("username", u.getUser());
						HomePage.setTypeuser(u.isPs());
						return SUCCESS;
					}
					else
					{
						c.close();
						error = "Credenziali errate";
						return ERROR;
					}
				}
				else
				{
					c.close();
					error = "Utente non trovato";
					return ERROR;
				}
			}
			else
			{
				error = "error connection DB";
				return ERROR;
			}
		}
		catch(SQLException | NoSuchAlgorithmException e)
		{
			error = "error dati DB";
			return ERROR;
		}
		
	}
	
	
	
	
	
	public void validate()
	{
		if(utente!=null)
		{
			if(utente.getUser().length() == 0)
			{
				addFieldError("utente.user", "username is required");
			}
			
			if(utente.getPass().length() == 0)
			{
				addFieldError("utente.pass", "password is required");
			}
		}
		else
		{
			addActionError("User is null");
		}
	}
	
	
	
	
	

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}
	
	
	
	

}
