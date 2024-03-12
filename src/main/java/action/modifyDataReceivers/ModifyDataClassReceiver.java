package action.modifyDataReceivers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import Beans.SchoolClass;
import Beans.Student;
import Beans.Teacher;
import action.HomePage;
import dbConnection.DBCPDataSource;
import model.DAO;

public class ModifyDataClassReceiver extends ActionSupport implements Preparable{
	
	
	private Connection c = null;
	private DAO dbAccess = null;
	private SchoolClass classBean;
	private List<String> selectedStudentsItems = new ArrayList<String>(); //Contiene gli esatti studenti che dovranno esserci nella classe
	private List<String> oldStudents = new ArrayList<String>(); //Contiene gli esatti studenti presenti attualmente nella classe
	private List<String> selectedTeachersItems = new ArrayList<String>(); //Contiene gli esatti studenti che dovranno esserci nella classe
	private List<String> oldTeachers = new ArrayList<String>(); //Contiene gli esatti studenti presenti attualmente nella classe
	
	
	
	
	//Data to repopulate:
	
	private SchoolClass classToModify;
	
	private List<Student> studentsWithoutClass;
	private List<Teacher> teachersOutOfThisClass;
	
	private List<Student> studentsWithClass;
	private List<Teacher> teachersWithClass;
	
	private static String id = null;
	private String errorMessage = "";
	private String error;
	private int errorCode;
	
	
	public ModifyDataClassReceiver()
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
				
				classToModify = dbAccess.getClass(Integer.parseInt(id));
				studentsWithoutClass = dbAccess.getAllStudentsWithoutClass();
				studentsWithClass = dbAccess.getAllStudentsWithClass(Integer.parseInt(id));
				teachersOutOfThisClass = dbAccess.getAllTeachersOutOfThisClass(Integer.parseInt(id));
				teachersWithClass = dbAccess.getAllTeachersOfThisClass(Integer.parseInt(id));
				
				c.close();
				
			}
			else
			{
				
				
				errorMessage = "Errore accesso DB";
			}
			
		}catch(SQLException e)
		{
			
			errorMessage = "Errore accesso DB";
		}
	}
	
	
	
	
	public String execute() {
		
		c = DBCPDataSource.getConnection();
		dbAccess = new DAO(c);
		boolean res;
		String ris;
		errorCode = 3;
		
		try
		{
			if(c!=null)
			{
				
				if(HomePage.isTypeuser())
				{
					classBean.setId(id);
					res = dbAccess.modifyClass(classBean);
					oldStudents = dbAccess.getAllCFStudentsWithClass(Integer.parseInt(classBean.getId()));
					oldTeachers = dbAccess.getAllCFTeachersOfThisClass(Integer.parseInt(classBean.getId()));
					
					if(res && oldStudents !=null && oldTeachers != null)
					{
						//Studenti da aggiungere
						List<String> newStudents = selectedStudentsItems.stream()
				                .filter(student -> !oldStudents.contains(student))
				                .collect(Collectors.toList());
						
						//Studenti da togliere
						List<String> deleteStudents = oldStudents.stream()
								.filter(student -> !selectedStudentsItems.contains(student))
								.collect(Collectors.toList());
						
						
						
						
						//Insegnanti da aggiungere
						List<String> newTeachers = selectedTeachersItems.stream()
				                .filter(student -> !oldTeachers.contains(student))
				                .collect(Collectors.toList());
						
						//Insegnanti da togliere
						List<String> deleteTeachers = oldTeachers.stream()
								.filter(student -> !selectedTeachersItems.contains(student))
								.collect(Collectors.toList());
						
						
						//Operazioni su DB
						res = dbAccess.match(Integer.parseInt(classBean.getId()), newStudents, deleteStudents, newTeachers, deleteTeachers); //Aggiungere liste per insegnanti
						
						if(res)
						{
							
							ris = SUCCESS;
						}
						else
						{
							error = "Errore creazione match";
							ris = ERROR;
						}
						
						c.close();
						return ris;
					}
					else
					{
						error = "Errore ricezione dati da DB";
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
				error = "Errore apertura connessione";
				return ERROR;
			}
			
		}catch(SQLException e)
		{
			error = "Eccezzione - operazioni su DB non riuscite";
			return ERROR;
		}
	}
	
	
	public void validate()
	{
		
		if(classBean!=null)
		{
			String regex = "\\d\\^[A-Za-z]";
			if(classBean.getNome().length() == 0)
			{
				addFieldError("classBean.nome", "Name is required");
			}
			
			if(!classBean.getNome().matches(regex))
			{
				addFieldError("classBean.nome", "Name format incorrect!");
			}
		}
		else
		{
			addActionError("class is null");
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


	public static String getId() {
		return id;
	}


	public static void setId(String id) {
		ModifyDataClassReceiver.id = id;
	}


	public SchoolClass getClassToModify() {
		return classToModify;
	}


	public void setClassToModify(SchoolClass classToModify) {
		this.classToModify = classToModify;
	}


	public List<Student> getStudentsWithoutClass() {
		return studentsWithoutClass;
	}


	public void setStudentsWithoutClass(List<Student> studentsWithoutClass) {
		this.studentsWithoutClass = studentsWithoutClass;
	}


	public List<Teacher> getTeachersOutOfThisClass() {
		return teachersOutOfThisClass;
	}


	public void setTeachersOutOfThisClass(List<Teacher> teachersOutOfThisClass) {
		this.teachersOutOfThisClass = teachersOutOfThisClass;
	}


	public List<Student> getStudentsWithClass() {
		return studentsWithClass;
	}


	public void setStudentsWithClass(List<Student> studentsWithClass) {
		this.studentsWithClass = studentsWithClass;
	}


	public List<Teacher> getTeachersWithClass() {
		return teachersWithClass;
	}


	public void setTeachersWithClass(List<Teacher> teachersWithClass) {
		this.teachersWithClass = teachersWithClass;
	}


	public List<String> getOldStudents() {
		return oldStudents;
	}

	public void setOldStudents(List<String> oldStudents) {
		this.oldStudents = oldStudents;
	}

	public List<String> getOldTeachers() {
		return oldTeachers;
	}

	public void setOldTeachers(List<String> oldTeachers) {
		this.oldTeachers = oldTeachers;
	}

	public List<String> getSelectedTeachersItems() {
		return selectedTeachersItems;
	}

	public void setSelectedTeachersItems(List<String> selectedTeachersItems) {
		this.selectedTeachersItems = selectedTeachersItems;
	}

	public List<String> getSelectedStudentsItems() {
		return selectedStudentsItems;
	}

	public void setSelectedStudentsItems(List<String> selectedStudentsItems) {
		this.selectedStudentsItems = selectedStudentsItems;
	}

	public SchoolClass getClassBean() {
		return classBean;
	}

	public void setClassBean(SchoolClass classBean) {
		this.classBean = classBean;
	}

}
