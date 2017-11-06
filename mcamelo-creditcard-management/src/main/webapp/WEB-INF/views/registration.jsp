<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
<link type="text/css" rel="stylesheet" href="http://wangjohn.github.io/creditly/src/creditly.css" />
<link type="text/css" rel="stylesheet" href="http://wangjohn.github.io/creditly/teaser.css" />
<style>

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}
</style>

<script type="text/javascript">
      function validate(){
    	 
         if( document.registrationForm.username.value == "" || document.registrationForm.password.value == ""){
            alert( "Please provide username and password for registration" );
            document.registrationForm.username.focus() ;
            return false;
         }else{
        	return true;
         }
         
         return returning;
      }   
 </script>
</head>
<body onload='javascript : document.registrationForm.username.focus();'>

	<center><h1>Credit Card Management</h1></center>

	<div id="login-box">

		<h3>Protected Area Registration</h3>

		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<form:form name='registrationForm' action="processRegistration"  method='POST' commandName="userForm" onsubmit="javascript : return(validate());">

			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><br/><button class="creditly-gray-theme-submit"><span>Register</span></button></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form:form>
	</div>

</body>
</html>
