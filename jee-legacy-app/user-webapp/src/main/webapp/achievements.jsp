<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.util.List" %>
<%@ page import="com.redhat.demo.common.service.AchievementService" %>
<%@ page import="com.redhat.demo.common.entity.Achievement" %>
<html>
<head>
  <link rel="stylesheet" href="style.css">
</head>

<body>
<div class="header">
  <table>
    <tr>
      <td class="widecol"><span class="header">APPreciate - Achievements</span></td>
      <td><a href="index.jsp" class="r-button">Kudos (Home)</a></td>
      <td><a href="/admin-webapp" class="r-button">Admin app</a></td>
      <td><a href="logout.jsp" class="r-button">Logout (<%=request.getUserPrincipal().getName()%>)</a></td>
    </tr>
  </table>
</div>

<div class="workarea">
  <%
    AchievementService achievementService = (AchievementService) new InitialContext().lookup("java:app/core-ejb-1.0-SNAPSHOT/achievementService!com.redhat.demo.common.service.AchievementService");
  %>
  <table class="data">
    <tr>
      <th class="idcol">ID</th>
      <th class="widecol">Achievement</th>
      <th>Created</th>
    </tr>
    <%
      List<Achievement> achievementList = achievementService.listAchievements(request.getUserPrincipal().getName());
      if (achievementList == null || achievementList.size() == 0) {
    %>
    <tr>
      <td colspan="5">No data here yet</td>
    </tr>
    <%
    } else {
      for (Achievement achievement : achievementList) {
    %>
    <tr>
      <td class="idcol"><%=achievement.getId()%>
      </td>
      <td class="widecol"><%=achievement.getType()%>
      </td>
      <td><%=achievement.getCreationDate()%>
      </td>
    </tr>
    <%
        }
      }
    %>
  </table>


</div>

</body>
</html>
