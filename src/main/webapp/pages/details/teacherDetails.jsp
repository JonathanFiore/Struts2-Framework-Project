<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="pages/css/details.css">
</head>
<body>

<div class = container>
<h1>Info dettagliate Insegnante:</h1>


<h4>CF: <s:property value="%{#request.t.cf}"/></h4>
<h4>Nome: <s:property value="%{#request.t.nome}"/></h4>
<h4>Cognome: <s:property value="%{#request.t.cognome}"/></h4>
<h4>Indirizzo: <s:property value="%{#request.t.indirizzo}"/></h4>
<h4>Data di nascita: <s:property value="%{#request.t.dataNascita}"/></h4>

<s:url action="indexTeachers" var="indexTeachers" />
	<p><a href="${indexTeachers}">Back to home</a></p>
	
</div>

</body>
</html>