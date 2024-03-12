package action.modify;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.action.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import Beans.SchoolClass;
import Beans.Student;
import Beans.Teacher;
import action.HomePage;
import action.modifyDataReceivers.ModifyDataClassReceiver;
import action.modifyDataReceivers.ModifyDataStudentReceiver;
import action.modifyDataReceivers.ModifyDataTeacherReceiver;
import dbConnection.DBCPDataSource;
import model.DAO;

public class ModifyRequestAction extends ActionSupport {
	
	private Connection c = null;
	private DAO dbAccess = null;
	
	
	private SchoolClass classToModify;
	private String id;
	
	private Student studentToModify;
	private SchoolClass sc = null;
	private Teacher teacherToModify;
	private String cf;
	
	//About Matching operations:
	private List<Student> studentsWithoutClass;
	private List<Teacher> teachersOutOfThisClass;
	
	private List<Student> studentsWithClass;
	private List<Teacher> teachersWithClass;
	
	//Error:
	
	private String error;
	private int errorCode;
	

	public ModifyRequestAction()
	{
		
	}
	
	
	public String modifyClass() {
		
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		errorCode = 3;
		
		try
		{
			if(c!=null)
			{
				if(HomePage.isTypeuser())
				{
					if(id != null && NumberUtils.isCreatable(id) && String.valueOf(Integer.parseInt(id)).equals(id))
					{
						classToModify = dbAccess.getClass(Integer.parseInt(id));
						studentsWithoutClass = dbAccess.getAllStudentsWithoutClass();
						studentsWithClass = dbAccess.getAllStudentsWithClass(Integer.parseInt(id));
						teachersOutOfThisClass = dbAccess.getAllTeachersOutOfThisClass(Integer.parseInt(id));
						teachersWithClass = dbAccess.getAllTeachersOfThisClass(Integer.parseInt(id));
						
						c.close();
						
						if(classToModify!= null && studentsWithoutClass != null && teachersOutOfThisClass != null
								&& studentsWithClass!=null && teachersOutOfThisClass!=null && teachersWithClass!=null)
						{
							ModifyDataClassReceiver.setId(id);
							return SUCCESS;
						}
						else
						{
							error = "Errore ricezione dati da DB";
							return ERROR;
						}
					}
					else
					{
						error = "Erore formattazione dati";
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
	
	
	
	public String modifyStudent() {
		
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		errorCode = 1;
		
		try
		{
			
			if(c!=null)
			{
				if(HomePage.isTypeuser())
				{
					if(cf != null && cf.length() > 0)
					{
						studentToModify = dbAccess.getStudent(cf);
						
						if(studentToModify!= null)
						{	
							sc = new SchoolClass();
							if(!studentToModify.getSc().equals("0"))
							{
								sc = dbAccess.getClass( Integer.parseInt(studentToModify.getSc()));
							}
							else
							{
								sc.setId("0");
								sc.setNome("Nessuna");
							}
							
							ModifyDataStudentReceiver.setCf(cf);
							ModifyDataStudentReceiver.setSchool(sc);
							
							c.close();
							return SUCCESS;
						}
						else
						{
							error = "Errore ricezione insegnante";
							c.close();
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
			
		}catch(SQLException e)
		{
			error = "Eccezzione - Errore";
			return ERROR;
		}
	}


	
	public String modifyTeacher() {
		
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		errorCode = 2;
		
		try
		{
			
			if(c!=null)
			{
				if(HomePage.isTypeuser())
				{
					if(cf != null && cf.length() > 0)
					{
						teacherToModify = dbAccess.getTeacher(cf);
						c.close();
						
						if(teacherToModify!= null)
						{
							ModifyDataTeacherReceiver.setCf(cf);
							return SUCCESS;
						}
						else
						{
							error = "Errore ricezione insegnante";
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


	public SchoolClass getSc() {
		return sc;
	}


	public void setSc(SchoolClass sc) {
		this.sc = sc;
	}



	public List<Teacher> getTeachersWithClass() {
		return teachersWithClass;
	}



	public void setTeachersWithClass(List<Teacher> teachersWithClass) {
		this.teachersWithClass = teachersWithClass;
	}



	public List<Teacher> getTeachersOutOfThisClass() {
		return teachersOutOfThisClass;
	}



	public List<Student> getStudentsWithClass() {
		return studentsWithClass;
	}



	public void setStudentsWithClass(List<Student> studentsWithClass) {
		this.studentsWithClass = studentsWithClass;
	}



	public List<Student> getStudentsWithoutClass() {
		return studentsWithoutClass;
	}



	public void setStudentsWithoutClass(List<Student> studentsWithoutClass) {
		this.studentsWithoutClass = studentsWithoutClass;
	}



	public List<Teacher> getTeachersWithOutClass() {
		return teachersOutOfThisClass;
	}



	public void setTeachersOutOfThisClass(List<Teacher> teachersOutOfThisClass) {
		this.teachersOutOfThisClass = teachersOutOfThisClass;
	}



	public Teacher getTeacherToModify() {
		return teacherToModify;
	}



	public void setTeacherToModify(Teacher teacherToModify) {
		this.teacherToModify = teacherToModify;
	}
	
	

	public Student getStudentToModify() {
		return studentToModify;
	}



	public void setStudentToModify(Student studentToModify) {
		this.studentToModify = studentToModify;
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



	public SchoolClass getClassToModify() {
		return classToModify;
	}



	public void setClassToModify(SchoolClass classToModify) {
		this.classToModify = classToModify;
	}	

}
