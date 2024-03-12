package action.modifyDataReceivers;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.math.NumberUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import Beans.SchoolClass;
import Beans.Student;
import action.HomePage;
import dbConnection.DBCPDataSource;
import model.DAO;

public class ModifyDataStudentReceiver extends ActionSupport  implements Preparable{ 
	
	private Connection c = null;
	private DAO dbAccess = null;
	private Student studentBean;
	
	private static String cf;
	private static SchoolClass school; 
	private SchoolClass sc;
	private Student studentToModify;
	private String errorMessage;
	
	private String error;
	private int errorCode;
 
	public ModifyDataStudentReceiver()
	{
		
	}
	
	
	@Override
	public void prepare() throws Exception {
		
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		
		try
		{
			if(c!=null)
			{	
				studentToModify = dbAccess.getStudent(cf);
				c.close();
				
				sc = new SchoolClass();
				sc.setId(school.getId());
				sc.setNome(school.getNome());
				
				if(studentToModify== null)
				{		
					errorMessage = "Errore accesso DB";
				}
			}
			else
			{
				errorMessage = "Errore apertura connessione DB";
			}
			
		}catch(SQLException e)
		{
			errorMessage = "Errore chiusura connessione DB";
		}
		
	}
	
	
	
	
	public String execute() {
		
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
					studentBean.setCf(cf);
					res = dbAccess.modifyStudent(studentBean);
					c.close();
					
					if(res)
					{
						return SUCCESS;
					}
					else
					{
						error = "Errore modifica studente";
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
	
	
	public void validate()
	{
		String regex = "^[a-zA-Z0-9,\\s]+$";
		String regexDate = "^\\d{4}-\\d{2}-\\d{2}$";
		
		if(studentBean!=null)
		{
			if(studentBean.getNome().length() == 0)
			{
				addFieldError("studentBean.nome", "Name is required.");
			}
			
			if(studentBean.getCognome().length() == 0)
			{
				addFieldError("studentBean.cognome", "Cognome is required.");
			}
			
			if(studentBean.getIndirizzo().length() == 0)
			{
				addFieldError("studentBean.indirizzo", "indirizzo is required");
			}
			
			if(!studentBean.getIndirizzo().matches(regex))
			{
				addFieldError("studentBean.indirizzo", "Error format indirizzo, use just numbers and letters");
			}
			
			if(studentBean.getDataNascita().length() == 0)
			{
				addFieldError("studentBean.dataNascita", "dataNascita is required");
			}
			
			if(!studentBean.getDataNascita().matches(regexDate))
			{
				addFieldError("studentBean.dataNascita", "Error format dataNascita");
			}
		}
		else
		{
			addActionError("student is null");
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


	public static SchoolClass getSchool() {
		return school;
	}


	public static void setSchool(SchoolClass school) {
		ModifyDataStudentReceiver.school = school;
	}


	public SchoolClass getSc() {
		return sc;
	}


	public void setSc(SchoolClass sc) {
		this.sc = sc;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public Student getStudentToModify() {
		return studentToModify;
	}


	public void setStudentToModify(Student studentToModify) {
		this.studentToModify = studentToModify;
	}

	public static String getCf() {
		return cf;
	}

	public static void setCf(String cf) {
		ModifyDataStudentReceiver.cf = cf;
	}

	public Student getStudentBean() {
		return studentBean;
	}

	public void setStudentBean(Student studentBean) {
		this.studentBean = studentBean;
	}

}
