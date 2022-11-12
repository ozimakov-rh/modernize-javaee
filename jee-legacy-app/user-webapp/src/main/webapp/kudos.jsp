<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <link rel="stylesheet" href="style.css">
    </head>
</head>
<body>

<div class="header">Send a Kudos</div>

<div class="workarea">
    <form action="./servlet/kudos" method="post">
        <table>
            <tr>
                <td><label for="from">From:</label></td>
                <td><input type="text" id="from" name="from" value="<%=request.getUserPrincipal().getName()%>" readonly></td>
                <td>&nbsp;</td>
                <td><label for="to">To:</label></td>
                <td><input type="text" id="to" name="to"></td>
            </tr>
            <tr>
                <td><label for="descr">Kudos text:</label></td>
                <td colspan="4"><textarea id="descr" name="descr" style="width: 100%;"></textarea></td>
            </tr>
            <tr>
                <td colspan="5"><input type="submit" class="button"> <a href="./" class="r-button">Cancel</a></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
