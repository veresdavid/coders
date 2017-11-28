<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Game</title>
    <script
      src="https://code.jquery.com/jquery-3.2.1.min.js"
      integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
      crossorigin="anonymous"></script>
    <script>
        function finishJob(){
            $.ajax({
              type: "POST",
              url: "jobs/finish",
              success: function(data){

                console.log(data);

              }
            });
        }
    </script>
</head>
<body>
<h2>Secure page!!!</h2>
<div>
    <sec:authentication var="user" property="principal" />
    ${user}
    <div>
        <button onclick="finishJob()">Finish Job</button>
    </div>
</div>
</body>
</html>
