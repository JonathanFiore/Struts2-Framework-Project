package action.dataReceivers;

import java.sql.Connection;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

import Beans.SchoolClass;
import Beans.Student;
import action.HomePage;
import dbConnection.DBCPDataSource;
import model.DAO;

public class DataStudentReceivers extends ActionSupport{
	
	
	private Connection c = null;
	private DAO dbAccess = null;
	private Student student;
	
	private String error;
	private int errorCode;
	
	public DataStudentReceivers()
	{
		
	}
	
	public String execute() {
		
		
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		errorCode = 1;
		
		try 
		{
			if(c!=null)
			{
				if(HomePage.isTypeuser())
				{
					//I already have student filled with the right data
					boolean res;
					res = dbAccess.insertDataStudents(student);
					c.close();
					
					if(res)
					{
						return SUCCESS;
					}
					else
					{
						error = "Errore inserimento dati DB";
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
		
		if(student == null)
		{
			addActionError("Student is null");
		}
		else
		{
			if(student.getNome().length() == 0)
			{
				addFieldError("student.nome", "Name is required.");
			}
			
			if(student.getCognome().length() == 0)
			{
				addFieldError("student.cognome", "Cognome is required.");
			}
			
			if(student.getCf().length() != 16)
			{
				addFieldError("student.cf", "CF has to have 16 characters.");
			}
			
			if(!student.getCf().matches(regex))
			{
				addFieldError("student.cf", "Error format CF, use just numbers and letters");
			}
			
			if(student.getIndirizzo().length() == 0)
			{
				addFieldError("student.indirizzo", "indirizzo is required");
			}
			
			if(!student.getIndirizzo().matches(regex))
			{
				addFieldError("student.indirizzo", "Error format indirizzo, use just numbers and letters");
			}
			
			if(student.getDataNascita().length() == 0)
			{
				addFieldError("student.dataNascita", "dataNascita is required");
			}
			
			if(!student.getDataNascita().matches(regexDate))
			{
				addFieldError("student.dataNascita", "Error format dataNascita");
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	

}
