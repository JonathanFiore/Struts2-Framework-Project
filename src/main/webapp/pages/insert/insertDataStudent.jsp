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
	<h1> Inserisci Studente</h1>
	
	<s:form action="insertDataStudent">
	<s:textfield name="student.cf" label="CF" required="true"/>
    <s:textfield name="student.nome" label="Nome Studente" required="true"/>
    <s:textfield name="student.cognome" label="Cognome Studente" required="true"/>
    <s:textfield name="student.indirizzo" label="Indirizzo" required="true"/>
    <s:textfield name="student.dataNascita" label="Data Nascita - format: yyyy-MM-dd" required="true"/>
      <s:submit/>
    </s:form>

</body>
</html>