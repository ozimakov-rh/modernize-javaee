<%@ page import="com.redhat.demo.common.service.GreetingService" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="com.redhat.demo.common.entity.Greeting" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
</head>

<body>
<div class="header">
    <table>
        <tr>
            <td class="widecol"><span class="header">Admin users' greetings</span></td>
            <td><a href="/user-webapp" class="r-button">User app</a></td>
        </tr>
    </table>
</div>

<div class="workarea">
    <%
        GreetingService greetingService = (GreetingService) new InitialContext().lookup("java:app/core-ejb-1.0-SNAPSHOT/greetingService");
    %>
    <table class="data">
        <tr>
            <th class="idcol">ID</th>
            <th>From</th>
            <th>To</th>
            <th class="widecol">Greeting</th>
            <th>Created</th>
            <th>Delete</th>
        </tr>
        <%
            List<Greeting> greetings = greetingService.listAllGreetings();

            if (greetings == null || greetings.size() == 0) {
        %>
        <tr>
            <td colspan="6">No data here yet</td>
        </tr>
        <%
                return;
            }

            for (Greeting g : greetings) {
        %>
        <tr>
            <td class="idcol"><%=g.getId()%>
            </td>
            <td><%=g.getUserFrom()%>
            </td>
            <td><%=g.getUserTo()%>
            </td>
            <td class="widecol"><%=g.getDescription()%>
            </td>
            <td style="white-space: nowrap;"><%=g.getCreationDate()%>
            </td>
            <td><a href="./admin?id=<%=g.getId()%>" class="button">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
