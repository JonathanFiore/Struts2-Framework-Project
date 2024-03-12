<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="pages/css/insert.css">
<s:head />
</head>
<body>
	<h1> Inserisci Insegnante</h1>
	
	<s:form action="insertDataTeacher">
	<s:textfield name="teacher.cf" label="CF" required="true"/>
    <s:textfield name="teacher.nome" label="Nome Insegnante" required="true"/>
    <s:textfield name="teacher.cognome" label="Cognome Insegnante" required="true"/>
    <s:textfield name="teacher.indirizzo" label="Indirizzo" required="true"/>
    <s:textfield name="teacher.dataNascita" label="Data Nascita - format: yyyy-MM-dd" required="true"/>
      <s:submit/>
    </s:form>

</body>
</html>