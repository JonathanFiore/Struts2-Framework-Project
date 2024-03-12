package action.details;

import java.sql.Connection;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

import Beans.Student;
import Beans.Teacher;
import dbConnection.DBCPDataSource;
import model.DAO;

public class Details extends ActionSupport{
	
	private Connection c = null;
	private DAO dbAccess = null;
	
	private String cf;
	private Student s;
	private Teacher t;
	
	private String error;
	private int errorCode;
	
	public Details()
	{
		
	}
	
	public String studentDetails()
	{
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		errorCode = 1;
		
		try
		{
			if(c!= null)
			{
				if(cf != null && cf.length() > 0 && cf.length() == 16)
				{
					s = dbAccess.getStudent(cf);
					c.close();
					
					if(s!=null)
					{
						if(s.getSc().equals("0"))
						{
							s.setSc("Nessuna classe di appartenenza");
						}
						
						return SUCCESS;
					}
					else
					{
						error = "Errore ricezione dati studente da DB";
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
				error = "Errore apertura connessione DB";
				return ERROR;
			}
		}catch(SQLException e)
		{
			error = "Eccezzione - Errore";
			return ERROR;
		}
		
		
	}
	
	public String teacherDetails()
	{
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		
		errorCode = 2;
		
		try
		{
			if(c!= null)
			{
				if(cf != null && cf.length() > 0 && cf.length() == 16)
				{
					t = dbAccess.getTeacher(cf);
					c.close();
					
					if(t!=null)
					{
						return SUCCESS;
					}
					else
					{
						error = "Errore ricezione dati insegnante DB";
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
				error = "Errore accesso DB";
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

	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}

	public Teacher getT() {
		return t;
	}

	public void setT(Teacher t) {
		this.t = t;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}
	
	
	
	
	
}
