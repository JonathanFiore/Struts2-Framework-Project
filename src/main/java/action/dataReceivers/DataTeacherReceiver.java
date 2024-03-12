package action.dataReceivers;

import java.sql.Connection;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

import Beans.Student;
import Beans.Teacher;
import action.HomePage;
import dbConnection.DBCPDataSource;
import model.DAO;

public class DataTeacherReceiver extends ActionSupport{
	
	
	private Connection c = null;
	private DAO dbAccess = null;
	private Teacher teacher;
	
	private String error;
	private int errorCode;
	
	public DataTeacherReceiver()
	{
		
	}
	
	public String execute() {
		
		
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		errorCode = 2;
		
		try
		{
			if(c!=null)
			{
				if(HomePage.isTypeuser())
				{
					//I already have student filled with the right data
					boolean res;
					res = dbAccess.insertDataTeacher(teacher);
					c.close();
					
					if(res)
					{
						return SUCCESS;
					}
					else
					{
						error = "Errore inserimento dati in DB";
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
		String regex = "^[a-zA-Z0-9,\\s]+$";
		String regexDate = "^\\d{4}-\\d{2}-\\d{2}$";
		
		if(teacher == null)
		{
			addActionError("Teacher is null");
		}
		else
		{
			if(teacher.getNome().length() == 0)
			{
				addFieldError("teacher.nome", "Name is required.");
			}
			
			if(teacher.getCognome().length() == 0)
			{
				addFieldError("teacher.cognome", "Cognome is required.");
			}
			
			if(teacher.getCf().length() != 16)
			{
				addFieldError("teacher.cf", "CF has to have 16 characters.");
			}
			
			if(!teacher.getCf().matches(regex))
			{
				addFieldError("teacher.cf", "Error format CF, use just numbers and letters");
			}
			
			if(teacher.getIndirizzo().length() == 0)
			{
				addFieldError("teacher.indirizzo", "indirizzo is required");
			}
			
			if(!teacher.getIndirizzo().matches(regex))
			{
				addFieldError("teacher.indirizzo", "Error format indirizzo, use just numbers and letters");
			}
			
			if(teacher.getDataNascita().length() == 0)
			{
				addFieldError("teacher.dataNascita", "dataNascita is required");
			}
			
			if(!teacher.getDataNascita().matches(regexDate))
			{
				addFieldError("teacher.dataNascita", "Error format dataNascita");
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	

}
