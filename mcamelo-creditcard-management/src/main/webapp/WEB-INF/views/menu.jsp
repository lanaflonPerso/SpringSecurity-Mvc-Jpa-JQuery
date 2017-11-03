<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br/>
<center>
<h2>
<font style="color : black"><c:out value = "${sessionScope.userNameTitle}"/> ${pageContext.request.userPrincipal.name}</font> | <a href="<c:url value='/' />">HomePage</a> | <a href="<c:url value='/findCreditCard' />">Search Credit Card</a> | <a href="javascript:formSubmit()" style="color : #00ccff">Logout</a>
</h2>
</center>