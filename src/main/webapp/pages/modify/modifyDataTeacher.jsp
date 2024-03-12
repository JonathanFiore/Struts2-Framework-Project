<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="pages/css/modify.css">
<s:head />
</head>
<body>

<h1>Modify data Student, CF: <s:property value="%{#request.teacherToModify.cf}"/> </h1>
<h3><s:property value="%{#request.errorMessage}"/></h3>

<s:url action="indexTeachers" var="indexTeachers">
    </s:url>
    <s:a href="%{indexTeachers}">Back to index</s:a>
    
    </br>

<s:form action="ModifyDataTeacherReceiver">
	      <s:textfield name="teacherBean.nome" label="Nome" value="%{#request.teacherToModify.nome}" required="true"/>
	      <s:textfield name="teacherBean.cognome" label="Cognome" value="%{#request.teacherToModify.cognome}" required="true"/>
	      <s:textfield name="teacherBean.indirizzo" label="Indirizzo" value="%{#request.teacherToModify.indirizzo}" required="true"/>
	      <s:textfield name="teacherBean.dataNascita" label="Data Nascita - format: yyyy-MM-dd" value="%{#request.teacherToModify.dataNascita}" required="true"/>
	      <s:submit/>
	    </s:form>

</body>
</html>