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

<h1>Modify data Student, CF: <s:property value="%{#request.studentToModify.cf}"/></h1>
<h4>Class ID:  <s:property value="%{#request.sc.id}"/></h4>
<h4>Class name: <s:property value="%{#request.sc.nome}"/></h4>


<h3><s:property value="%{#request.errorMessage}"/></h3>

<s:url action="index" var="index">
    </s:url>
    <s:a href="%{index}">Back to index</s:a>
    
    </br>

<s:form action="ModifyDataStudentReceiver">
	      <s:textfield name="studentBean.nome" label="Nome" value="%{#request.studentToModify.nome}" required="true"/>
	      <s:textfield name="studentBean.cognome" label="Cognome" value="%{#request.studentToModify.cognome}" required="true"/>
	      <s:textfield name="studentBean.indirizzo" label="Indirizzo" value="%{#request.studentToModify.indirizzo}" required="true"/>
	      <s:textfield name="studentBean.dataNascita" label="Data Nascita - format: yyyy-MM-dd" value="%{#request.studentToModify.dataNascita}" required="true"/>
	      <s:submit/>
	    </s:form>

<p>1</p>
</body>
</html>