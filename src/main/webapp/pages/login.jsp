<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="pages/css/login.css">
</head>
<body>

<h3><s:property value="%{#request.error}"/></h3>

<div class="login-container">
        <h1>Login</h1>
        <s:form action="loginCheck">
	      <s:textfield name="utente.user" label="Username" required="true"/>
	      <s:textfield name="utente.pass" label="Password" required="true"/>
	      <s:submit/>
	    </s:form>
    </div>

</body>
</html>