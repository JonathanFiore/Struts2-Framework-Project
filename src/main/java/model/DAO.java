package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Beans.SchoolClass;
import Beans.Student;
import Beans.Teacher;
import Beans.User;

public class DAO {
	
	Connection c = null;
	
	public DAO(Connection c)
	{
		this.c = c;
	}
	
	
	public User getUser(String username) throws SQLException
	{
		User u = null;
		
		String sql = "Select * from utente where user = ?";
		PreparedStatement preparedStatement = c.prepareStatement(sql);
		preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
	
        	
        	
        // Process the results
        while (resultSet.next()) 
        {
            // Retrieve data from the result set
        	u = new User();
        	u.setUser(resultSet.getString("user"));
        	u.setPass(resultSet.getString("password"));
        	u.setMail(resultSet.getString("email"));
        	u.setPs(resultSet.getBoolean("ps"));
        } 
		
		return u;
	}
	
	
	
	//GETTERS OF STUDENTS, TEACHERS AND CLASSES
	
	public List<Student> getAllStudents()
	{
		List<Student> list = new ArrayList<Student>();
		
		String sql = "Select * from scuola.alunno";
		
		try (PreparedStatement preparedStatement = c.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
        	
        	
            // Process the results
            while (resultSet.next()) 
            {
                // Retrieve data from the result set
            	Student s = new Student();
            	
            	s.setCf(resultSet.getString("CF"));
            	s.setNome(resultSet.getString("nome"));
            	s.setCognome(resultSet.getString("cognome"));
            	s.setIndirizzo(resultSet.getString("indirizzo"));
            	s.setDataNascita(resultSet.getDate("dataNascita").toString());
            	
            	list.add(s);
            } 
            
        }catch (SQLException e) {
        	list = null;
		}
		
		return list;
		
	}
	
	public List<Teacher> getAllTeachers()
	{
		List<Teacher> list = new ArrayList<Teacher>();
		
		String sql = "Select * from scuola.insegnante";
		
		try (PreparedStatement preparedStatement = c.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
        	
        	
            // Process the results
            while (resultSet.next()) 
            {
                // Retrieve data from the result set
            	Teacher t = new Teacher();
            	
            	t.setCf(resultSet.getString("CF"));
            	t.setNome(resultSet.getString("nome"));
            	t.setCognome(resultSet.getString("cognome"));
            	t.setIndirizzo(resultSet.getString("indirizzo"));
            	t.setDataNascita(resultSet.getDate("dataNascita").toString());
            	
            	list.add(t);	
            }
            
            
        }catch (SQLException e) {
        	list = null;
		}
		
		return list;
	}
	
	public List<SchoolClass> getAllClasses()
	{
		List<SchoolClass> list = new ArrayList<SchoolClass>();
		
		String sql = "Select * from scuola.classe";
		
		try (PreparedStatement preparedStatement = c.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
        	
        	
            // Process the results
            while (resultSet.next()) 
            {
                // Retrieve data from the result set
            	SchoolClass c = new SchoolClass();
            	
            	c.setId(String.valueOf(resultSet.getInt("id")));
            	c.setNome(resultSet.getString("nome"));
            	
            	list.add(c);
            	
            }
            
            
        }catch (SQLException e) {
        	list = null;
		}
		
		return list;
	}
	
	//---------------------------------------------------------------------------------
	
	//SINGLE GETTERS:
	
	public Student getStudent(String cf)
	{
		Student s = null;
		String sql = "Select * from scuola.alunno where cf = ?";
		
		try 
		{
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, cf);
            ResultSet resultSet = preparedStatement.executeQuery();
        	
        	
            // Process the results
            while (resultSet.next())
            {
                // Retrieve data from the result set
            	s = new Student();
            	//s.setCf(String.valueOf(resultSet.getString("cf")));
            	s.setCf(resultSet.getString("cf"));
            	s.setNome(resultSet.getString("nome"));
            	s.setCognome(resultSet.getString("cognome"));
            	s.setIndirizzo(resultSet.getString("indirizzo"));
            	s.setDataNascita(resultSet.getDate("dataNascita").toString());
            	s.setSc(String.valueOf(resultSet.getInt("classe")));
            }
            
            
        }catch (SQLException e) {
        	s = null;
		}
		
		return s;
	}
	
	public Teacher getTeacher(String cf)
	{
		
		Teacher t = null;
		String sql = "Select * from scuola.insegnante where cf = ?";
		
		
		try 
		{
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, cf);
            ResultSet resultSet = preparedStatement.executeQuery();
        	
        	
            // Process the results
            while (resultSet.next())
            {
                // Retrieve data from the result set
            	t = new Teacher();
            	t.setCf(String.valueOf(resultSet.getString("cf")));
            	t.setNome(resultSet.getString("nome"));
            	t.setCognome(resultSet.getString("cognome"));
            	t.setIndirizzo(resultSet.getString("indirizzo"));
            	t.setDataNascita(resultSet.getDate("dataNascita").toString());
            }
            
            
        }catch (SQLException e) {
        	t = null;
		}
		
		return t;
	}
	
	public SchoolClass getClass(int id)
	{
		
		SchoolClass sc = null;
		String sql = "Select * from scuola.classe where id = ?";

		try 
		{
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
        	
            // Process the results
            while (resultSet.next())
            {
            	sc = new SchoolClass();
                // Retrieve data from the result set
            	sc.setId(String.valueOf(resultSet.getInt("id")));
            	sc.setNome(resultSet.getString("nome"));
            }  
            
            
        }catch (SQLException e) {
        	sc = null;
		}
		
		return sc;
	}
	
	//---------------------------------------------------------------------------------
	
	//UPDATE:
	
	public boolean modifyStudent(Student s)
	{
		
		boolean res;
		String sql = "UPDATE alunno SET nome = ?, cognome = ?, indirizzo = ?, dataNascita = ? where CF = ?;";
		
		try {
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, s.getNome());
			preparedStatement.setString(2, s.getCognome());
			preparedStatement.setString(3, s.getIndirizzo());
			preparedStatement.setDate(4, Date.valueOf(s.getDataNascita()));
			preparedStatement.setString(5, s.getCf());
			preparedStatement.executeQuery();
			
			res = true;
			
		} catch (SQLException e) {
			
			res = false;
		}
		
		return res;
	}
	
	public boolean modifyTeacher(Teacher t)
	{
		boolean res;
		String sql = "UPDATE insegnante SET nome = ?, cognome = ?, indirizzo = ?, dataNascita = ? where CF = ?;";
		

		try {
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, t.getNome());
			preparedStatement.setString(2, t.getCognome());
			preparedStatement.setString(3, t.getIndirizzo());
			preparedStatement.setDate(4, Date.valueOf(t.getDataNascita()));
			preparedStatement.setString(5, t.getCf());
			preparedStatement.executeQuery();
			
			res = true;
			
		} catch (SQLException e) {
			
			res = false;
		}
			
		
		return res;
	} 
	
	public boolean modifyClass(SchoolClass sc)
	{
		boolean res;
		String sql = "UPDATE classe SET nome = ? where id = ?;";
		
	
		try {
			
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, sc.getNome());
			preparedStatement.setInt(2, Integer.parseInt(sc.getId()));
			preparedStatement.executeQuery();
			
			res = true;
			
		} catch (SQLException e) {
			
			res = false;
		}
			
		
		return res;
	} 
	
	
	//---------------------------------------------------------------------------------
	
	
	//INSERT OF STUDENT, TEACHERS AND CLASSES:
	
	public boolean insertDataStudents(Student s)
	{
		
		boolean res;
		

		try
		{
			String sql = "INSERT INTO alunno (CF, nome, cognome, indirizzo, dataNascita, classe) "
					+ "VALUES (?,?,?,?,?, NULL);";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, s.getCf());
			preparedStatement.setString(2, s.getNome());
			preparedStatement.setString(3, s.getCognome());
			preparedStatement.setString(4, s.getIndirizzo());
			preparedStatement.setDate(5, Date.valueOf(s.getDataNascita()));
			preparedStatement.executeQuery();
			
			
			res = true;
		}
		catch(SQLException | IllegalArgumentException | NullPointerException e)
		{
			res = false;
		}
		
		return res;
	}
	
	public boolean insertDataTeacher(Teacher t)
	{
		
		boolean res;
		

		try
		{
			String sql = "INSERT INTO insegnante (CF, nome, cognome, indirizzo, dataNascita) "
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, t.getCf());
			preparedStatement.setString(2, t.getNome());
			preparedStatement.setString(3, t.getCognome());
			preparedStatement.setString(4, t.getIndirizzo());
			preparedStatement.setDate(5, Date.valueOf(t.getDataNascita()));
			preparedStatement.executeQuery();
			
			
			res = true;
		}
		catch(SQLException | IllegalArgumentException | NullPointerException e)
		{
			res = false;
		}
		
		return res;
	}
	
	public boolean insertDataClass(SchoolClass sc)
	{
		
		boolean res;
		

		try
		{
			String sql = "INSERT INTO classe (nome) VALUES (?);";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, sc.getNome());
			preparedStatement.executeQuery();
			
			res = true;
		}
		catch(SQLException | IllegalArgumentException e)
		{
			res = false;
		}
	
		return res;
		
	}	
	
	
	//---------------------------------------------------------------------------------
	
	
	//REMOVE OF STUDENTS, TEACHERS AND CLASSES:
	
	
	public boolean removeStudent(String cf)
	{
		boolean res;
		String sql = "DELETE from alunno where CF = ?;";
		
		try {
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, cf);
			preparedStatement.executeQuery();
			
			res = true;
			
		} catch (SQLException e) {
			
			res = false;
		}
		
		return res;
	}
	
	public boolean removeTeacher(String cf)
	{
		boolean res;
		String sql = "DELETE from insegnante where CF = ?;";
		
		try {
			
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, cf);
			preparedStatement.executeQuery();
			
			res = true;
			
		} catch (SQLException e) {
			
			res = false;
		}
		
		return res;
	}
	
	public boolean removeClass(int id)
	{
		boolean res;
		String sql = "DELETE from classe where ID = ?;";
			
		try {
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeQuery();
			
			res = true;
			
		} catch (SQLException e) {
			
			res = false;
		}
		
		return res;
	}

	
	
	//---------------------------------------------------------------------------------
	
	//USEFULL OPERATIONS FOR MATCHING:
	
	public List<Student> getAllStudentsWithoutClass()

	{
		
		List<Student> list = new ArrayList<Student>();
		
		String sql = "SELECT * FROM scuola.alunno A WHERE A.classe IS NULL;";
		
		try (PreparedStatement preparedStatement = c.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
        	
        	
            // Process the results
            while (resultSet.next())
            {
                // Retrieve data from the result set
            	Student s = new Student();
            	
            	s.setCf(resultSet.getString("CF"));
            	s.setNome(resultSet.getString("nome"));
            	s.setCognome(resultSet.getString("cognome"));
            	s.setIndirizzo(resultSet.getString("indirizzo"));
            	s.setDataNascita(resultSet.getDate("dataNascita").toString());
            	
            	list.add(s);
            } 
            
        }catch (SQLException e) {
        	list = null;
		}
		
		return list;
	}

	public List<Student> getAllStudentsWithClass(int idClass)

	{
		
		List<Student> list = new ArrayList<Student>();
		
		String sql = "SELECT * FROM scuola.alunno A WHERE A.classe = ?;";
		
		try
		{
			
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, idClass);
            ResultSet resultSet = preparedStatement.executeQuery();
        	
        	
            // Process the results
            while (resultSet.next())
            {
                // Retrieve data from the result set
            	Student s = new Student();
            	
            	s.setCf(resultSet.getString("CF"));
            	s.setNome(resultSet.getString("nome"));
            	s.setCognome(resultSet.getString("cognome"));
            	s.setIndirizzo(resultSet.getString("indirizzo"));
            	s.setDataNascita(resultSet.getDate("dataNascita").toString());
            	
            	list.add(s);
            } 
            
        }catch (SQLException e) {
        	list = null;
		}
		
		return list;
	}
	
	public List<String> getAllCFStudentsWithClass(int idClass)
	{
		
		List<String> list = new ArrayList<String>();
		
		String sql = "SELECT * FROM scuola.alunno A WHERE A.classe = ?;";
		
		try
		{
			
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, idClass);
            ResultSet resultSet = preparedStatement.executeQuery();
        	
        	
            // Process the results
            while (resultSet.next())
            {
                // Retrieve data from the result set
            	String s = new String();
            	s = resultSet.getString("CF");
            	list.add(s);
            } 
            
        }catch (SQLException e) {
        	list = null;
		}
		
		return list;
	}

	public List<Teacher> getAllTeachersOutOfThisClass(int id)
	{
		List<Teacher> list = new ArrayList<Teacher>();
		
		
		String sql = "Select * from scuola.insegnante I where I.CF NOT IN (Select scuola.possiede.CFInsegnante from scuola.possiede where scuola.possiede.IDClasse = ?);";
		
		try {
			
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
        	
            // Process the results
            while (resultSet.next()) 
            {
                // Retrieve data from the result set
            	Teacher t = new Teacher();
            	
            	t.setCf(resultSet.getString("CF"));
            	t.setNome(resultSet.getString("nome"));
            	t.setCognome(resultSet.getString("cognome"));
            	t.setIndirizzo(resultSet.getString("indirizzo"));
            	t.setDataNascita(resultSet.getDate("dataNascita").toString());
            	
            	list.add(t);	
            }
            
            
        }catch (SQLException e) {
        	list = null;
		}
		
		return list;
	}
	
	public List<Teacher> getAllTeachersOfThisClass(int id)
	{
		List<Teacher> list = new ArrayList<Teacher>();
		
		
		String sql = "Select * from scuola.insegnante I where I.CF IN (Select scuola.possiede.CFInsegnante from scuola.possiede where scuola.possiede.IDClasse = ?);";
		
		try {
			
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
        	
            // Process the results
            while (resultSet.next()) 
            {
                // Retrieve data from the result set
            	Teacher t = new Teacher();
            	
            	t.setCf(resultSet.getString("CF"));
            	t.setNome(resultSet.getString("nome"));
            	t.setCognome(resultSet.getString("cognome"));
            	t.setIndirizzo(resultSet.getString("indirizzo"));
            	t.setDataNascita(resultSet.getDate("dataNascita").toString());
            	
            	list.add(t);	
            }
            
            
        }catch (SQLException e) {
        	list = null;
		}
		
		return list;
	}

	public List<String> getAllCFTeachersOfThisClass(int id)
	{
		List<String> list = new ArrayList<String>();
		
		String sql = "SELECT * FROM scuola.possiede A WHERE A.IDClasse = ?;";
		
		try
		{
			
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
        	
        	
            // Process the results
            while (resultSet.next())
            {
                // Retrieve data from the result set
            	String t = new String();
            	t = resultSet.getString("CFInsegnante");
            	list.add(t);
            } 
            
        }catch (SQLException e) {
        	list = null;
		}
		
		return list;
	}

	
	
	
	public boolean match(int idClasse, List<String> newStudents, List<String> deleteStudents, List<String> newTeachers, List<String> deleteTeachers) throws SQLException
	{
		boolean res = false;
		
		try
		{
			c.setAutoCommit(false);
			realCreateMatch(idClasse, newStudents, newTeachers); //Inserimento alunni e insegnanti nella classe
			realDeleteSCMatch(deleteStudents); //cancellazione studenti da questa classe
			realDeleteTCMatch(idClasse, deleteTeachers); //cancellazione insegnanti da questa classe
			
			c.commit();
			
			res = true;
			
		}catch(SQLException e)
		{
			c.rollback();
			
			res = false;
		}
		finally
		{
			c.setAutoCommit(true);
		}
		
		
		return res;
	}
		
	private void realCreateMatch(int idClasse, List<String> selectedStudentsItems, List<String> selectedTeachersItems) throws SQLException
	{
		
		//Students matching:
		
		String sql = "UPDATE alunno SET classe = ? where CF = ?;";
		for(int i=0; i<selectedStudentsItems.size(); i++)
		{
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, idClasse);
			preparedStatement.setString(2, selectedStudentsItems.get(i));
			preparedStatement.executeQuery();
			
		}
		
		
		//Teachers Matching:
		
		sql = "INSERT INTO possiede (CFInsegnante, IDClasse) VALUES (?, ?);";
		for(int i=0; i<selectedTeachersItems.size(); i++)
		{
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, selectedTeachersItems.get(i));
			preparedStatement.setInt(2, idClasse);
			preparedStatement.executeQuery();
		}
		
		
		
	}

	private void realDeleteSCMatch(List<String> list) throws SQLException
	{
		
		String sql = "UPDATE alunno SET classe = NULL where CF = ?;";
		for(int i=0; i<list.size(); i++)
		{
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, list.get(i));
			preparedStatement.executeQuery();
		}
	}

	private void realDeleteTCMatch(int id, List<String> list) throws SQLException
	{
		String sql = "DELETE from possiede where CFInsegnante = ? and IDClasse = ?;";
		for(int i=0; i<list.size(); i++)
		{
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, list.get(i));
			preparedStatement.setInt(2, id);
			preparedStatement.executeQuery();
		}
		
	}

	
	
	
	
	public Map<String, List<Student>> getAllStudentsOfAllClasses(List<SchoolClass> sc)
	{
		Map matchStudents = new HashMap<String, List<Student>>();
		
		if(sc!=null)
		{
			List<Student> list;
			
			String sql = "Select * from scuola.alunno where classe = ?";
			
			try
			{
				for(int i=0; i<sc.size(); i++)
				{
					list = new ArrayList<Student>();
					PreparedStatement preparedStatement = c.prepareStatement(sql);
					preparedStatement.setInt(1, Integer.parseInt(sc.get(i).getId()));
	                ResultSet resultSet = preparedStatement.executeQuery();
		        	
		            // Process the results
		            while (resultSet.next()) 
		            {
		                // Retrieve data from the result set
		            	Student s = new Student();
		            	
		            	s.setCf(resultSet.getString("CF"));
		            	s.setNome(resultSet.getString("nome"));
		            	s.setCognome(resultSet.getString("cognome"));
		            	s.setIndirizzo(resultSet.getString("indirizzo"));
		            	s.setDataNascita(resultSet.getDate("dataNascita").toString());
		            	
		            	list.add(s);
		            }
		            
		            matchStudents.put(sc.get(i).getId(), list);
				}
				
				return matchStudents;
	            
	        }catch (SQLException e) {
	        	return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	public Map<String, List<Teacher>> getAllTeachersOfAllClasses(List<SchoolClass> sc)
	{
		
		
		Map matchTeachers = new HashMap<String, List<Teacher>>();
		
		if(sc!=null)
		{
			List<Teacher> list;
			
			String sql = "Select * from scuola.possiede P JOIN scuola.insegnante I ON P.CFInsegnante = I.CF where IDClasse = ?";
			
			try
			{
				for(int i=0; i<sc.size(); i++)
				{
					list = new ArrayList<Teacher>();
					PreparedStatement preparedStatement = c.prepareStatement(sql);
					preparedStatement.setInt(1, Integer.parseInt(sc.get(i).getId()));
	                ResultSet resultSet = preparedStatement.executeQuery();
		        	
		            // Process the results
		            while (resultSet.next()) 
		            {
		                // Retrieve data from the result set
		            	Teacher t = new Teacher();
		            	
		            	t.setCf(resultSet.getString("CF"));
		            	t.setNome(resultSet.getString("nome"));
		            	t.setCognome(resultSet.getString("cognome"));
		            	t.setIndirizzo(resultSet.getString("indirizzo"));
		            	t.setDataNascita(resultSet.getDate("dataNascita").toString());
		            	
		            	list.add(t);
		            }
		            
		            matchTeachers.put(sc.get(i).getId(), list);
				}
				
				return matchTeachers;
	            
	        }catch (SQLException e) {
	        	return null;
			}
		}
		else
		{
			return null;
		}
	}

	
}
