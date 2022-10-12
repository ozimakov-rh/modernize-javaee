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
            <td class="widecol"><span class="header">Your greetings</span></td>
            <td><a href="greet.jsp" class="button">New Greeting</a></td>
            <td><a href="/admin-webapp" class="r-button">Admin app</a></td>
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
        </tr>
        <%
            List<Greeting> greetings = greetingService.listGreetings("user1");

            if (greetings == null || greetings.size() == 0) {
        %>
        <tr>
            <td colspan="5">No data here yet</td>
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
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
