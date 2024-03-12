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

<h1>Info dettagliate Studente:</h1>


<h4>CF: <s:property value="%{#request.s.cf}"/></h4>
<h4>Nome: <s:property value="%{#request.s.nome}"/></h4>
<h4>Cognome: <s:property value="%{#request.s.cognome}"/></h4>
<h4>Indirizzo: <s:property value="%{#request.s.indirizzo}"/></h4>
<h4>Data di nascita: <s:property value="%{#request.s.dataNascita}"/></h4>
<h4>ID classe: <s:property value="%{#request.s.sc}"/></h4>

<s:url action="index" var="index" />
	<p><a href="${index}">Back to home</a></p>

</div>

</body>
</html>