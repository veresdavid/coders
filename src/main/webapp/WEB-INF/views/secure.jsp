<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Game</title>
    <script
      src="https://code.jquery.com/jquery-3.2.1.min.js"
      integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
      crossorigin="anonymous"></script>
    <script>
    function startJob(){
                $.ajax({
                  type: "POST",
                  url: "jobs/start/0",
                  success: function(data){

                    console.log(data);

                  }
                });
            }
        function finishJob(){
            $.ajax({
              type: "POST",
              url: "jobs/finish",
              success: function(data){

                console.log(data);

              }
            });
        }
        function startAttack(){
                    $.ajax({
                      type: "POST",
                      url: "attack/start/1",
                      success: function(data){

                        console.log(data);

                      }
                    });
                }
        function finishAttack(){
                    $.ajax({
                      type: "POST",
                      url: "attack/finish",
                      success: function(data){

                        console.log(data);

                      }
                    });
                }
                function learnSkill(){
                                    $.ajax({
                                      type: "POST",
                                      url: "skills/learn/0",
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
        <button onclick="startJob()">Start Job</button>
        <button onclick="finishJob()">Finish Job</button>
        <button onclick="startAttack()">startAttack</button>
        <button onclick="finishAttack()">finishAttack</button>
        <button onclick="learnSkill()">SKRRRRRRA</button>
    </div>
</div>
</body>
</html>
