<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Basic Struts 2 Application - Welcome</title>
    <link rel="stylesheet" type="text/css" href="pages/css/home.css">
  </head>
  <body>
  
  <div class="menu">
  <label> Studenti </label>
  
  <s:url action="indexTeachers" var="indexTeachers" />
	<p><a href="${indexTeachers}">Insegnanti</a></p>
	
	<s:url action="indexClasses" var="indexClasses" />
	<p><a href="${indexClasses}">Classi</a></p>
	
	<div class="out">
  <s:url action="logout" var="logout" />
	<p><a href="${logout}">Logout</a></p>
  
  </div>
  
  </div>

    <h1>Studenti</h1>
    <s:if test="%{#request.tu == true}"> 
    <div class="create">
     <s:url action="insertStudent" var="insertStudent" />
	<p><a href="${insertStudent}">Inserisci nuovo Studente</a></p>
	</div>
	</s:if>
	
     <table border="1">
      <thead>
        <tr>
        <s:if test="%{#request.tu == true}"> 
         <th class="firstCol">CF</th>
        </s:if>
          <th>Nome</th>
          <th>Cognome</th>
          <th>Azioni</th>
        </tr>
      </thead>
      <tbody>
        <s:iterator value="students" var="item">
          <tr>
          	<s:if test="%{#request.tu == true}"> 
            	<td class="firstCol"><s:property value="#item.cf" /></td>
            </s:if>
            <td><s:property value="#item.nome" /></td>
            <td><s:property value="#item.cognome" /></td>
            <td>
            	<s:if test="%{#request.tu == true}"> 
            	
		            <s:url action="modifyStudent" var="modifyStudent" >
				    <s:param name="cf"><s:property value="#item.cf"/></s:param>
				    </s:url>
					<p><a href="${modifyStudent}">Modifica</a></p>
					
					<s:url action="RemoveStudent" var="RemoveStudent" >
				    <s:param name="cf"><s:property value="#item.cf"/></s:param>
				    </s:url>
					<p><a href="${RemoveStudent}">Cancella</a></p>
				
				</s:if>
				
				<s:url action="DetailsStudent" var="DetailsStudent">
				<s:param name="cf"><s:property value="#item.cf"/></s:param>
				</s:url>
				<p><a href="${DetailsStudent}">Dettagli</a></p>
            </td>
          </tr>
        </s:iterator>
      </tbody>
    </table>
    
  </body>
</html>