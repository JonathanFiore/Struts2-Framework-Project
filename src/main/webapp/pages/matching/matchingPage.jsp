<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="pages/css/matching.css">
</head>
<body>

<h1>Welcome to Matching page!</h1>
<h2>ID Class: <s:property value="%{#request.id}" /></h2>

<form action="CreateMatchAction">
        <!-- Itera sulla lista e crea le checkbox -->
        
        <br/>
        
        
        <h4>STUDENTS AVAILABLES: </h4>
        <s:textfield type="hidden" name="id" value="%{#request.id}" />
        
        <s:iterator value="students" var="item">
             <s:checkbox name="selectedStudentsItems" fieldValue="%{#item.cf}" />
             <s:property value="#item.nome + ', ' + #item.cognome + ' - ' + #item.cf" />
            <br/>
        </s:iterator>
        
        <br/>
        <br/>
        
        <h4>TEACHERS AVAILABLES: </h4>
         <s:iterator value="teachers" var="item">
             <s:checkbox name="selectedTeachersItems" fieldValue="%{#item.cf}" />
             <s:property value="#item.nome + ', ' + #item.cognome + ' - ' + #item.cf" />
            <br/>
        </s:iterator>
        
        <br/>

        <input type="submit" value="Submit">
    </form>


</body>
</html>