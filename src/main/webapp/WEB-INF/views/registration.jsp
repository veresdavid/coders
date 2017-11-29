<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Registration</title>
	<c:url value="/resources/js/jquery.js" var="jQuery" />
	<c:url value="/resources/js/registration.js" var="regJs" />
	<script type="text/javascript" src="${jQuery}"></script>
	<script type="text/javascript" src="${regJs}"></script>
</head>
<body>
	<div>
		<label>username</label>
		<input id="username" type="text">
		<br/>
		<label>email</label>
		<input id="email" type="text">
		<br/>
		<label>password</label>
		<input id="password" type="password">
		<br/>
		<label>password again</label>
		<input id="passwordRepeat" type="password">
		<br/>
		<button onclick="registration()">sign up</button>
	</div>
	<div id="message">
	</div>
</body>
</html>