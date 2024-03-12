<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="pages/css/modifyClass.css">
<s:head />
</head>
<body>

<h1>Modify data class, ID: <s:property value="%{#request.classToModify.id}"/></h1>
<h3><s:property value="%{#request.errorMessage}"/></h3>

<s:url action="indexClasses" var="indexClasses">
    </s:url>
    <s:a href="%{indexClasses}">Back to index</s:a>
    
    </br>

<form action="ModifyDataClassReceiver">
         
         
	      <s:textfield name="classBean.nome" label="Nome Classe format example: 1^A" value="%{#request.classToModify.nome}" required="true"/>
	      
	      
	      </br>
	      </br>
	       <h4>STUDENTS: </h4>
	       <s:iterator value="studentsWithoutClass" var="item">
             <s:checkbox name="selectedStudentsItems" fieldValue="%{#item.cf}" value="false"/>
             <s:property value="#item.nome + ', ' + #item.cognome + ' - ' + #item.cf" />
               </br>
          </s:iterator>
           <s:iterator value="studentsWithClass" var="item">
             <s:checkbox name="selectedStudentsItems" fieldValue="%{#item.cf}" value="true"/>
             <s:property value="#item.nome + ', ' + #item.cognome + ' - ' + #item.cf" />
             </br>
          </s:iterator>
          
          
          <h4>TEACHERS: </h4>
          
          <s:iterator value="teachersOutOfThisClass" var="item">
             <s:checkbox name="selectedTeachersItems" fieldValue="%{#item.cf}"  value="false"/>
             <s:property value="#item.nome + ', ' + #item.cognome + ' - ' + #item.cf"/>
               </br>
          </s:iterator>
           <s:iterator value="teachersWithClass" var="item">
             <s:checkbox name="selectedTeachersItems" fieldValue="%{#item.cf}" value="true"/>
             <s:property value="#item.nome + ', ' + #item.cognome + ' - ' + #item.cf" />
             </br>
          </s:iterator>
	      
	     <input type="submit" value="Submit">
	    </form>
</body>
</html>