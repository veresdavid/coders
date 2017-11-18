<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<h2>Secure page!!!</h2>
<div>
    <sec:authentication var="user" property="principal" />
    ${user}
</div>
</body>
</html>
