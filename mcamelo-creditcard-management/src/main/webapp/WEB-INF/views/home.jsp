<!DOCTYPE html>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
    <meta charset="UTF-8">
    <title>Credit Card</title>

  <link type="text/css" rel="stylesheet" href="http://wangjohn.github.io/creditly/src/creditly.css" />
  <link type="text/css" rel="stylesheet" href="http://wangjohn.github.io/creditly/teaser.css" />
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.js" ></script>
  <script type="text/javascript" src="http://wangjohn.github.io/creditly/src/creditly.js"></script>
  
    <script type="text/javascript">
    $(function() {

      var grayThemeCreditly = Creditly.initialize(
          '.creditly-wrapper.gray-theme .expiration-month-and-year',
          '.creditly-wrapper.gray-theme .credit-card-number',
          '.creditly-wrapper.gray-theme .security-code',
          '.creditly-wrapper.gray-theme .card-type');

      $(".creditly-gray-theme-submit").click(function(e) {
        
        var output = grayThemeCreditly.validate();
        if (!output) {
        	e.preventDefault();
        }
      });
    });
  </script>
 
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
		<center>Insert your Credit Card data and click the button to save</center><br/>	
		<c:if test="${!empty cardSaved}"> 
		<center><font color="#00ff00">Your Credit Card has been saved</font></center><br/>
		</c:if>
	<div class="banner">
    <div class="row">
      <form:form  name='creditCardFormName' action="processCreditCard"  method='POST' commandName="creditCardForm">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
        <div class="top-level-wrapper gray-theme-wrapper" >
          <section class="creditly-wrapper gray-theme">
            <h3>Credit Card</h3>
            <i>
              <div class="card-type" style="text-align:right;margin-top:10px;margin-right:10px;min-height:20px;margin-bottom:-15px"></div>
            </i>
            <div class="credit-card-wrapper">
              <div class="first-row form-group">
                <div class="col-sm-8 controls">
                  <label class="control-label">Card Number</label>
                  <input class="number credit-card-number form-control"
                    type="text" name="creditCardnumber"
                    pattern="(\d*\s){3}\d*"
                    inputmode="numeric" autocomplete="cc-number" autocompletetype="cc-number" x-autocompletetype="cc-number"
                    placeholder="&#149;&#149;&#149;&#149; &#149;&#149;&#149;&#149; &#149;&#149;&#149;&#149; &#149;&#149;&#149;&#149;">
                </div>
                <div class="col-sm-4 controls">
                  <label class="control-label">CVV</label>
                  <input class="security-code form-control"
                    inputmode="numeric"
                    pattern="\d*"
                    type="text" name="cvv"
                    placeholder="&#149;&#149;&#149;">
                </div>
              </div>
              <div class="second-row form-group">
                <div class="col-sm-8 controls">
                  <label class="control-label">Name on Card</label>
                  <input class="billing-address-name form-control"
                    type="text" name="cardName"
                    placeholder="Name Surname">
                </div>
                <div class="col-sm-4 controls">
                  <label class="control-label">Expiration</label>
                  <input class="expiration-month-and-year form-control"
                    type="text" name="expiryDate"
                    placeholder="MM / YY">
                </div>
              </div>
            </div>
          </section>
          <button class="creditly-gray-theme-submit"><span>Save</span></button>
        </div>
      </form:form>
    </div>
  </div>
	
		</c:if>
	</sec:authorize>
	
</body>
</html>
