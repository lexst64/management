<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.management.tools.*" %>    
<%@ page import="java.util.Queue" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		try {
			out.println(UserCollection.getUserList());
		} catch (final ListIsEmptyException e) {
			e.printStackTrace();
		}
	%>
	<%-- <form action="${pageContext.request.contextPath}/servlet" method="post">
		<input type="text" name="login" placeholder="login" /><br />
		<input type="text" name="email" placeholder="email" /><br />
		<input type="password" name="password" placeholder="pass" /><br />
		<input type="tel" name="telnumber" placeholder="telnum" /><br />
		<input type="submit" name="signUpButton" value="Sign Up" /><br />
		<span>Do you have an account yet?  </span>
		<input type="submit" name="signInButton" value="Sign In" />
	</form> --%>
</body>
</html>
