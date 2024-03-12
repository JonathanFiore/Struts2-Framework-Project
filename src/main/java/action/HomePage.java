package action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.action.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import Beans.SchoolClass;
import Beans.Student;
import Beans.Teacher;
import dbConnection.DBCPDataSource;
import model.DAO;

public class HomePage extends ActionSupport implements SessionAware {
	
	private Connection c = null;
	private DAO dbAccess = null;
	private Map<String, Object> session;
	
	
	private List<Student> students = null;
	private List<Teacher> teachers = null;
	private List<SchoolClass> schoolClasses = null;
	
	
	private Map<String, List<Student>> matchStudents = null;
	private Map<String, List<Teacher>> matchTeachers = null;
	
	
	//Error:
	private String error;
	private int errorCode;
	
	
	//Type of User:
	private static boolean typeUser;
	private boolean tu;
	
	public HomePage()
	{
		
	}
	
	@Override
	public void withSession(Map<String, Object> session) {
		this.session = session;
		
	}
	
	public String execute() {
	
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		
		//Used to create page dinamically:
		tu = typeUser;
		
		
		error = "Errore accesso db";
		errorCode = 1;
		
		try
		{
			if(c!=null)
			{
				students = dbAccess.getAllStudents();
				//teachers = dbAccess.getAllTeachers();
				//schoolClasses = dbAccess.getAllClasses();
				//matchStudents = dbAccess.getAllStudentsOfAllClasses(schoolClasses);
				//matchTeachers = dbAccess.getAllTeachersOfAllClasses(schoolClasses);
				c.close();
				
				if(students != null)
				{
					return SUCCESS;
				}
				else
				{
					return ERROR;
				}
			}
			else
			{
				return ERROR;
			}
			
		}catch (SQLException e)
		{
			return ERROR;
		}
    }

	
	public String homeTeachers()
	{
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		
		//Used to create page dinamically:
		tu = typeUser;
		
		error = "Errore accesso db";
		errorCode = 2;
		
		try
		{
			if(c!=null)
			{
				//students = dbAccess.getAllStudents();
				teachers = dbAccess.getAllTeachers();
				//schoolClasses = dbAccess.getAllClasses();
				//matchStudents = dbAccess.getAllStudentsOfAllClasses(schoolClasses);
				//matchTeachers = dbAccess.getAllTeachersOfAllClasses(schoolClasses);
				c.close();
				
				if(teachers != null)
				{
					return SUCCESS;
				}
				else
				{
					return ERROR;
				}
			}
			else
			{
				return ERROR;
			}
			
		}catch (SQLException e)
		{
			return ERROR;
		}
	}
	
	public String homeClasses()
	{
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		
		//Used to create page dinamically:
		tu = typeUser;
		
		
		error = "Errore accesso db";
		errorCode = 3;
		
		try
		{
			if(c!=null)
			{
				//students = dbAccess.getAllStudents();
				//teachers = dbAccess.getAllTeachers();
				schoolClasses = dbAccess.getAllClasses();
				//matchStudents = dbAccess.getAllStudentsOfAllClasses(schoolClasses);
				//matchTeachers = dbAccess.getAllTeachersOfAllClasses(schoolClasses);
				c.close();
				
				if(schoolClasses != null)
				{
					return SUCCESS;
				}
				else
				{
					return ERROR;
				}
			}
			else
			{
				return ERROR;
			}
			
		}catch (SQLException e)
		{
			return ERROR;
		}
	}
	
	
	
	
	
	

	public boolean isTu() {
		return tu;
	}

	public void setTu(boolean tu) {
		this.tu = tu;
	}

	public static boolean isTypeuser() {
		return typeUser;
	}

	public static void setTypeuser(boolean typeuser) {
		HomePage.typeUser = typeuser;
	}

	public List<Student> getStudents() {
		return students;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public List<SchoolClass> getSchoolClasses() {
		return schoolClasses;
	}

	public Map<String, List<Student>> getMatchStudents() {
		return matchStudents;
	}

	public Map<String, List<Teacher>> getMatchTeachers() {
		return matchTeachers;
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
