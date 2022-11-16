<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>

Logging out...

<%
  request.logout();
  response.sendRedirect("./");
%>

</body>
</html>
