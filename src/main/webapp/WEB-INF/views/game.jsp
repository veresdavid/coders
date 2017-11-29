<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Game</title>
		<c:url value="/resources/js/jquery.js" var="jQuery" />
		<c:url value="/resources/js/game.js" var="gameJs" />
		<script type="text/javascript" src="${jQuery}"></script>
		<script type="text/javascript" src="${gameJs}"></script>
	</head>
	<body>
		<div>
			<div id="profile"></div>
			<hr/>
			<div>
				<div id="status"></div>
				<div id="statusCounter"></div>
			</div>
			<hr/>
			<div id="skills"></div>
			<hr/>
			<div id="jobs"></div>
			<hr/>
			<div id="messages"></div>
			<hr/>
			<div id="users"></div>
		</div>
	</body>
</html>