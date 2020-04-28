<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
	<main>
<c:if test="${errors.size()>0 }">
	<div class="danger">
		<ul>
			<c:forEach var="error" items="${errors }">
				<li>${error }</li>
			</c:forEach>
		</ul>
	</div>
</c:if> <c:choose>
	<c:when test="${user!=null}">
		<p>Welcome ${user.getFirstName()}!</p>
		<form method="post" action="Controller?action=LogOut">
			<p>
				<input type="submit" id="logoutbutton" value="Log Out">
			</p>
		</form>
	</c:when>
	<c:otherwise>
		<form method="post" action="Controller?action=LogIn">
			<p>
				<label for="email">Your email </label>
				<input type="text" id="email" name="email" value="jan@ucll.be">
			</p>
			<p>
				<label for="password">Your password</label>
				<input type="password" id="password" name="password" value="t">
			</p>
			<p>
				<input type="submit" id="loginbutton" value="Log in">
			</p>
		</form>
	</c:otherwise>
</c:choose>

<h1>BLOG</h1>
		<!--<button type="button" onclick="openSocket();" >Open</button>
		<button type="button" onclick="closeSocket();" >Close</button>-->

		<div>
			<div>
				<label for="review">Op welke review wil je een comment plaatsen? </label>
				<input id="review" type="number" step="1" min="1" max="5">
			</div>
			<div>
				<label for="name">Name:</label>
				<input id="name" type="text">
			</div>
			<div>
				<label for="comment">Comment:</label>
				<input id="comment" type="text">
			</div>
			<div>
				<label for="rating">Rating :</label>
				<input id="rating" type="number" step="1" min="0" max="10">
			</div>
			<div>
				<button type="button" onclick="send();" >Send</button>
			</div>
		</div>

		<div>
			<h2>1) Hoe studeer je tijdens de coronatijden?</h2>


			<div id="review1">

			</div>
		</div>

		<br/>
		</div>
		<div>
			<h2>2) Wat ben je van plan om te doen vandaag?</h2>

			<div id="review2">

			</div>
		</div>
		<br/>
		<div>
			<h2>3) Welke muziek luister je vandaag?</h2>

			<div id="review3">

			</div>
		</div>
		<br/>
		<div>
			<h2>4) Hoe lukt je project van web4 ?</h2>

			<div id="review4">

			</div>
		</div>
		<br/>
		<div>
			<h2>5) Wat heb je in de paasvakantie gedaan?</h2>

			<div id="review5">

			</div>
		</div>
		<br/>
	</main>

	<script type="text/javascript" src="js/comment.js"></script>

	<jsp:include page="footer.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
</body>
</html>