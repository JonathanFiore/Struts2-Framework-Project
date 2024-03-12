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
<h1>Inserimento dati classe:</h1>

	<s:form action="insertDataClass">
	      <s:textfield name="classBean.nome" label="Nome Classe format example 1^A" required="true"/>
	      <s:submit/>
	    </s:form>
</body>
</html>