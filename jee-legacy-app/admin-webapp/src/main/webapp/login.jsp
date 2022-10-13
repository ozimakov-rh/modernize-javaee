<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kudos Admin Login (Java EE)</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<%
    boolean error = Boolean.parseBoolean(request.getParameter("error"));
    if (error) {
%>
<div>Error</div>
<%
    }
%>

<div class="header">Kudo Admin (Java EE)</div>

<div class="login">
    <form method="POST" action="j_security_check">
        <label for="login">Login:</label><br/>
        <input type="text" name="j_username" id="login"><br/>

        <label for="login">Password:</label><br/>
        <input type="password" name="j_password" id="password">

        <br/><br/>
        <input type="submit" value="Login" class="button">
    </form>
</div>

</body>
</html>
