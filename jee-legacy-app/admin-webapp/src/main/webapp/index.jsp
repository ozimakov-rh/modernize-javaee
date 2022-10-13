<%@ page import="com.redhat.demo.common.service.KudoService" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="com.redhat.demo.common.entity.Kudo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.redhat.demo.common.entity.Kudo" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
</head>

<body>
<div class="header">
    <table>
        <tr>
            <td class="widecol"><span class="header">Admin users' kudos</span></td>
            <td><a href="/user-webapp" class="r-button">User app</a></td>
            <td><a href="logout.jsp" class="r-button">Logout (<%=request.getUserPrincipal().getName()%>)</a></td>
        </tr>
    </table>
</div>

<div class="workarea">
    <%
        KudoService kudoService = (KudoService) new InitialContext().lookup("java:app/core-ejb-1.0-SNAPSHOT/kudoService");
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
            List<Kudo> kudos = kudoService.listAllKudos();
            if (kudos == null || kudos.size() == 0) {
        %>
        <tr>
            <td colspan="6">No data here yet</td>
        </tr>
        <%
                return;
            }

            for (Kudo kudo : kudos) {
        %>
        <tr>
            <td class="idcol"><%=kudo.getId()%>
            </td>
            <td><%=kudo.getUserFrom()%>
            </td>
            <td><%=kudo.getUserTo()%>
            </td>
            <td class="widecol"><%=kudo.getDescription()%>
            </td>
            <td style="white-space: nowrap;"><%=kudo.getCreationDate()%>
            </td>
            <td><a href="./servlet/admin?id=<%=kudo.getId()%>" class="button">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
