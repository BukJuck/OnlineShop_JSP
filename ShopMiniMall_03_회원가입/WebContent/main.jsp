<%@ page language="java" conte2ntType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% 
	String mesg = (String)session.getAttribute("memberAdd"); 
	
	if(mesg!=null){
%>	
	<script type="text/javascript">
		alert("<%=mesg%>");
	</script>
<%
	session.removeAttribute("memberAdd"); //새로고침해도 회원가입성공 alert창 안뜸.
	} 
%>
</head>
<body>
<h1>Main 화면입니다.</h1>
<jsp:include page="common/top.jsp" flush="true"></jsp:include><br>
<jsp:include page="common/menu.jsp" flush="true"></jsp:include>
</body>
</html>