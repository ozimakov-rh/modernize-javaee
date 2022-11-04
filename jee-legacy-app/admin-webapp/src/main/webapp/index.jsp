<%@ page import="com.redhat.demo.common.service.KudosService" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="com.redhat.demo.common.entity.Kudos" %>
<%@ page import="java.util.List" %>
<%@ page import="com.redhat.demo.common.entity.Kudos" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
</head>

<body>
<div class="header">
    <table>
        <tr>
            <td class="widecol"><span class="header">APPreciate Admin</span></td>
            <td><a href="/user-webapp" class="r-button">User app</a></td>
            <td><a href="logout.jsp" class="r-button">Logout (<%=request.getUserPrincipal().getName()%>)</a></td>
        </tr>
    </table>
</div>

<div class="workarea">
    <%
        KudosService kudosService = (KudosService) new InitialContext().lookup("java:app/core-ejb-1.0-SNAPSHOT/kudosService");
    %>
    <table class="data">
        <tr>
            <th class="idcol">ID</th>
            <th>From</th>
            <th>To</th>
            <th class="widecol">Kudo</th>
            <th>Created</th>
            <th>Delete</th>
        </tr>
        <%
            List<Kudos> kudosList = kudosService.listAllKudos();
            if (kudosList == null || kudosList.size() == 0) {
        %>
        <tr>
            <td colspan="6">No data here yet</td>
        </tr>
        <%
                return;
            }

            for (Kudos kudos : kudosList) {
        %>
        <tr>
            <td class="idcol"><%=kudos.getId()%>
            </td>
            <td><%=kudos.getUserFrom()%>
            </td>
            <td><%=kudos.getUserTo()%>
            </td>
            <td class="widecol"><%=kudos.getDescription()%>
            </td>
            <td style="white-space: nowrap;"><%=kudos.getCreationDate()%>
            </td>
            <td><a href="./servlet/admin?id=<%=kudos.getId()%>" class="button">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
