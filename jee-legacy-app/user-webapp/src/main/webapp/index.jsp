<%@ page import="com.redhat.demo.common.service.KudosService" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="com.redhat.demo.common.entity.Kudos" %>
<%@ page import="java.util.List" %>
<%@ page import="com.redhat.demo.common.entity.Kudos" %>
<%@ page import="com.redhat.demo.common.service.KudosService" %>
<%@ page import="com.redhat.demo.common.service.KudosService" %>
<%@ page import="com.redhat.demo.common.entity.Kudos" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
</head>

<body>
<div class="header">
    <table>
        <tr>
            <td class="widecol"><span class="header">APPreciate - Kudos</span></td>
            <td><a href="achievements.jsp" class="r-button">My Achievements</a></td>
            <td><a href="/admin-webapp" class="r-button">Admin app</a></td>
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
            <th class="widecol">Kudos</th>
            <th>Created</th>
        </tr>
        <%
            List<Kudos> kudosList = kudosService.listKudos(request.getUserPrincipal().getName());
            if (kudosList == null || kudosList.size() == 0) {
        %>
        <tr>
            <td colspan="5">No data here yet</td>
        </tr>
        <%
            } else {
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
        </tr>
        <%
                }
            }
        %>
    </table>

    <div style="width: 100%; text-align: center;">
        <a href="kudos.jsp" class="button">Send Kudos</a>
    </div>


</div>

</body>
</html>
