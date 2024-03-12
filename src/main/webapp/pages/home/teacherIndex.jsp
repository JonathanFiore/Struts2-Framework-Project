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

 <label> Insegnanti </label>
	
	<s:url action="indexClasses" var="indexClasses" />
	<p><a href="${indexClasses}">Classi</a></p>
	
	<div class="out">
  <s:url action="logout" var="logout" />
	<p><a href="${logout}">Logout</a></p>
	</div>
	
	
</div>


<h1>Insegnanti</h1>
    
    <s:if test="%{#request.tu == true}"> 
    <div class="create">
     <s:url action="insertTeacher" var="insertTeacher" />
	<p><a href="${insertTeacher}">Inserisci nuovo Insegnante</a></p>
	</div>
	</s:if>
	
    <table border="1">
      <thead>
        <tr>
        <s:if test="%{#request.tu == true}"> 
          <th class = "firstCol">CF</th>
          </s:if>
          <th>Nome</th>
          <th>Cognome</th>
          <th>Azioni</th>
        </tr>
      </thead>
      <tbody>
        <s:iterator value="teachers" var="item">
          <tr>
          	<s:if test="%{#request.tu == true}"> 
            	<td class="firstCol"><s:property value="#item.cf" /></td>
            </s:if>
            <td><s:property value="#item.nome" /></td>
            <td><s:property value="#item.cognome" /></td>
            <td>
            	<s:if test="%{#request.tu == true}"> 
	            <s:url action="modifyTeacher" var="modifyTeacher" >
			    <s:param name="cf"><s:property value="#item.cf"/></s:param>
			    </s:url>
				<p><a href="${modifyTeacher}">Modifica</a></p>
				
				<s:url action="RemoveTeacher" var="RemoveTeacher" >
			    <s:param name="cf"><s:property value="#item.cf"/></s:param>
			    </s:url>
				<p><a href="${RemoveTeacher}">Cancella</a></p>
				
				</s:if>
				
				
				<s:url action="DetailsTeacher" var="DetailsTeacher">
				<s:param name="cf"><s:property value="#item.cf"/></s:param>
				</s:url>
				<p><a href="${DetailsTeacher}">Dettagli</a></p>
				
				
            </td>
          </tr>
        </s:iterator>
      </tbody>
    </table>

</body>
</html>