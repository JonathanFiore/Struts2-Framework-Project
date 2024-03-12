<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="pages/css/home.css">
</head>
<body>

<div class="menu">
<s:url action="index" var="index" />
	<p><a href="${index}">Studenti</a></p>

<s:url action="indexTeachers" var="indexTeachers" />
	<p><a href="${indexTeachers}">Insegnanti</a></p>

 <label> Classi </label>
 
 <div class="out">
  <s:url action="logout" var="logout" />
	<p><a href="${logout}">Logout</a></p>
	</div>

</div>

<h1>Classi</h1>
    
    <s:if test="%{#request.tu == true}"> 
     <div class="create">
    <s:url action="insertClass" var="insertClass" />
	<p><a href="${insertClass}">Inserisci nuova classe</a></p>
	</div>
	</s:if>
	
    <table border="1">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <s:if test="%{#request.tu == true}"> 
          <th>Azioni</th>
          </s:if>
        </tr>
      </thead>
      <tbody>
        <s:iterator value="schoolClasses" var="item">
          <tr>
         	
            <td><s:property value="#item.id" /></td>
            	
            <td><s:property value="#item.nome" /></td>
            
            <s:if test="%{#request.tu == true}">
            
            <td> 
            	
	            <s:url action="modifyClass" var="modifyClass" >
			    <s:param name="id"><s:property value="#item.id"/></s:param>
			    </s:url>
				<p><a href="${modifyClass}">Modifica</a></p>
				
				<s:url action="RemoveClass" var="RemoveClass" >
			    <s:param name="id"><s:property value="#item.id"/></s:param>
			    </s:url>
				<p><a href="${RemoveClass}">Cancella</a></p>
            </td>
            
            </s:if>
            
            
          </tr>
        </s:iterator>
      </tbody>
    </table>


</body>
</html>