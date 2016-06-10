F<html>
<head><title>Greet</title></head>
<body>
Hello,
[<%= request.getAttribute("greet.name") %>]
Your secret name is ,
[<%= request.getAttribute("secret.name") %>]
</body>
</html>