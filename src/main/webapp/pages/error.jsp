<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1>Error DB</h1>
 
 	<s:url action="errorData" var="errorData">
    </s:url>
    <s:a href="%{errorData}">Back to index</s:a>
</body>
</html>