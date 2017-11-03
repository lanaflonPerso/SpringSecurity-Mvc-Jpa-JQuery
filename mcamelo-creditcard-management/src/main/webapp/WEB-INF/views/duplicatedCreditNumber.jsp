<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>

<title>Credit Card</title>
<link type="text/css" rel="stylesheet" href="http://wangjohn.github.io/creditly/src/creditly.css" />
<link type="text/css" rel="stylesheet" href="http://wangjohn.github.io/creditly/teaser.css" /> 
</head>
<body>

	<center><h1>Credit Card Management</h1></center>
	<sec:authorize access="hasRole('ROLE_USER')">
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
		
		<c:if test="${pageContext.request.userPrincipal.name != null}">
		
		<jsp:include page="menu.jsp" />
		<br/>
		
		<br/>
		<table border="0" align="center" cellpadding="4" cellpadding="4">
		<tr><td align="center">The Credit Card's number inserted already exists from another user. It's no possible save this number.</td></tr>
		<tr><td align="center">Please <a href="<c:url value='/' />">click here</a> to save e different Credit Card.</td></tr>
		</table>
		
		</c:if>
		
		
		</sec:authorize>
</body>
</html>