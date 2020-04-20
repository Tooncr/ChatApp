<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 16/04/2020
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="ChatPagina" />
</jsp:include>

<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="ChatPagina" />
</jsp:include>

<h1>CHAT</h1>
<div id="status"></div>

<div>
    <label>Status</label><input type="text" id="statustext"/>
    <input type="button" value="Change Status" id="statusbutton" >
</div>



<script type="text/javascript" src="js/status.js"></script>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home" />
</jsp:include>


</body>
</html>
