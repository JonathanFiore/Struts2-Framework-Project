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

<h1>Errore: <s:property value="%{#request.error}"/></h1>

<s:if test="%{#request.errorCode == 1}"> 
    <!-- Back to index Student page -->
    <s:url action="errorDataStudent" var="errorDataStudent">
    </s:url>
    <s:a href="%{errorDataStudent}">Back to index</s:a>
</s:if>
<s:else>
    <s:if test="%{#request.errorCode == 2}">
   <!-- Back To index Teacher page -->
   <s:url action="errorDataTeacher" var="errorDataTeacher">
    </s:url>
    <s:a href="%{errorDataTeacher}">Back to index</s:a>
	</s:if>
	<s:else>
    
    <s:if test="%{#request.errorCode == 3}"> 
    <!-- Back to index class page -->
     <s:url action="errorDataClass" var="errorDataClass">
    </s:url>
    <s:a href="%{errorDataClass}">Back to index</s:a>
	</s:if>
	<s:else>
	<!-- Back to login page -->
	<s:url action="logout" var="logout" />
	<p><a href="${logout}">Logout</a></p>
	</s:else>
	</s:else>
</s:else>

</body>
</html>