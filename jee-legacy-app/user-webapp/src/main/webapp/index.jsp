<%@ page import="com.redhat.demo.common.service.KudoService" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="com.redhat.demo.common.entity.Kudo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.redhat.demo.common.entity.Kudo" %>
<%@ page import="com.redhat.demo.common.service.KudoService" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
</head>

<body>
<div class="header">
    <table>
        <tr>
            <td class="widecol"><span class="header">Your Kudos</span></td>
            <td><a href="kudo.jsp" class="button">New Kudo</a></td>
            <td><a href="/admin-webapp" class="r-button">Admin app</a></td>
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
        </tr>
        <%
            List<Kudo> kudos = kudoService.listKudos(request.getUserPrincipal().getName());
            if (kudos == null || kudos.size() == 0) {
        %>
        <tr>
            <td colspan="5">No data here yet</td>
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
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
