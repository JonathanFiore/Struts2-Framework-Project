package action.modifyDataReceivers;

import java.sql.Connection;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import Beans.Student;
import Beans.Teacher;
import action.HomePage;
import dbConnection.DBCPDataSource;
import model.DAO;

public class ModifyDataTeacherReceiver extends ActionSupport implements Preparable{
	
	
	private Connection c = null;
	private DAO dbAccess = null;
	private Teacher teacherBean;
	
	private static String cf;
	private Teacher teacherToModify;
	private String errorMessage;
	private String error;
	private int errorCode;

	public ModifyDataTeacherReceiver()
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
			
				teacherToModify = dbAccess.getTeacher(cf);
				c.close();
				
				if(teacherToModify== null)
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
			errorMessage = "Errore apertura connessione DB";
		}
		
	}
	
	
	
	public String execute() {
		
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		boolean res;
		errorCode = 2;
		
		try
		{
			if(c!=null)
			{
				if(HomePage.isTypeuser())
				{
					teacherBean.setCf(cf);
					res = dbAccess.modifyTeacher(teacherBean);
					c.close();
					
					if(res)
					{
						return SUCCESS;
					}
					else
					{
						error = "Errore modifica insegnante";
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
		
		if(teacherBean != null)
		{
			if(teacherBean.getNome().length() == 0)
			{
				addFieldError("teacherBean.nome", "Name is required.");
			}
			
			if(teacherBean.getCognome().length() == 0)
			{
				addFieldError("teacherBean.cognome", "Cognome is required.");
			}
			
			if(teacherBean.getIndirizzo().length() == 0)
			{
				addFieldError("teacherBean.indirizzo", "indirizzo is required");
			}
			
			if(!teacherBean.getIndirizzo().matches(regex))
			{
				addFieldError("teacherBean.indirizzo", "Error format indirizzo, use just numbers and letters");
			}
			
			if(teacherBean.getDataNascita().length() == 0)
			{
				addFieldError("teacherBean.dataNascita", "dataNascita is required");
			}
			
			if(!teacherBean.getDataNascita().matches(regexDate))
			{
				addFieldError("teacherBean.dataNascita", "Error format dataNascita");
			}
		}
		else
		{
			addActionError("Teacher is null");
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


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public Teacher getTeacherToModify() {
		return teacherToModify;
	}


	public void setTeacherToModify(Teacher teacherToModify) {
		this.teacherToModify = teacherToModify;
	}


	public static String getCf() {
		return cf;
	}

	public static void setCf(String cf) {
		ModifyDataTeacherReceiver.cf = cf;
	}

	public Teacher getTeacherBean() {
		return teacherBean;
	}

	public void setTeacherBean(Teacher teacherBean) {
		this.teacherBean = teacherBean;
	}

}
