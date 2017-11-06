<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Credit Card</title>

  <link type="text/css" rel="stylesheet" href="http://wangjohn.github.io/creditly/src/creditly.css" />
  <link type="text/css" rel="stylesheet" href="http://wangjohn.github.io/creditly/teaser.css" />
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.js" ></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/corejs-typeahead/1.1.1/typeahead.bundle.js"></script>


<script type="text/javascript">
$(document).ready(function(){
    
     var cardNumbers = [];
    
    <c:forEach items="${sessionScope.creditCardNumbers}" var="creditCardNumbers">
    cardNumbers.push('${creditCardNumbers}');
    </c:forEach>
    
    var cardNumbers = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.whitespace,
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        local: cardNumbers
    });
    
    $('.typeahead').typeahead({
        hint: true,
        highlight: true, /* Enable substring highlighting */
        minLength: 1 /* Specify minimum characters required for showing result */
    },
    {
        name: 'cardNumbers',
        source: cardNumbers
    });
});

function validate(){
	 
    if( document.creditCardSearchFormName.creditCardnumber.value == ""){
       alert( "Please provide a Credit Card number" );
       return false;
    }else{
   	return true;
    }
    
    return returning;
 }
</script>

<style type="text/css">
.bs-example {
	font-family: sans-serif;
	position: relative;
	margin: 100px;
}
.typeahead, .tt-query, .tt-hint {
	border: 2px solid #CCCCCC;
	border-radius: 8px;
	font-size: 22px; /* Set input font size */
	height: 30px;
	line-height: 30px;
	outline: medium none;
	padding: 8px 12px;
	width: 396px;
}
.typeahead {
	background-color: #FFFFFF;
}
.typeahead:focus {
	border: 2px solid #0097CF;
}
.tt-query {
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
}
.tt-hint {
	color: #999999;
}
.tt-menu {
	background-color: #FFFFFF;
	border: 1px solid rgba(0, 0, 0, 0.2);
	border-radius: 8px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
	margin-top: 12px;
	padding: 8px 0;
	width: 422px;
}
.tt-suggestion {
	font-size: 20px;
	padding: 3px 20px;
}
.tt-suggestion:hover {
	cursor: pointer;
	background-color: #0097CF;
	color: #FFFFFF;
}
.tt-suggestion p {
	margin: 0;
}
</style>
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
		<form:form name='creditCardSearchFormName' action="displayCreditCard"  method='POST' onsubmit="javascript : return(validate());">

			<table align="center">
				<tr>
					<td>Type Credit Card number to search it :</td><td></td><td></td>
					
					
				</tr>
				<tr>
					<td colspan='2'><br/><input type="text" class="typeahead tt-query" autocomplete="off" spellcheck="false" name="creditCardnumber"></td><td colspan='5'></td>
					<td colspan='2'><br/><button class="creditly-gray-theme-submit"><span>Get Info</span></button></td>
				</tr>
				
				
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form:form>
		
		<c:if test = "${cardNumber != null}">
		<br/>
		<table border="2" align="center" cellpadding="4" cellpadding="4">
		<tr><td align="center"><b>Credit Card Info</b></td></tr>
		<tr><td><table border="0" cellpadding="4" cellpadding="4">
		<tr><td>Card Number : </td><td>&nbsp;</td><td>${cardNumber}</td></tr>
		<tr><td>Cvv : </td><td>&nbsp;</td><td>${cvv}</td></tr>
		<tr><td>Name on Card : </td><td>&nbsp;</td><td>${cardName}</td></tr>
		<tr><td>Expiration Date : </td><td>&nbsp;</td><td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${expirationDate}" /></td></tr>
		</table></td></tr>
		</table>
		</c:if>
		
		</c:if>
		</sec:authorize>
</body>
</html>
