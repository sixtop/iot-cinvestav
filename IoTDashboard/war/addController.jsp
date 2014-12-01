<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="IoTDashboard.css">
<script type="text/javascript" language="javascript" src="controller/controller.nocache.js"></script>


<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="centro">
<h2>Controller</h2>
		<table>
			<tr>
				<td>Id:</td>
				<td id="tbIdContainer"></td>
			</tr>
			<tr>
				<td>Name:</td>
				<td id="tbNameContainer"></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td id="tbDescriptionContainer"></td>
			</tr>
			<tr>
				<td>Location:</td>
				<td id="tbLocationContainer"></td>
			</tr>
			<tr>
		</table>
<h3>Properties</h3>
<div id="propertyContainer"></div>
<table>
	<tr></tr>
			<tr>
			 	<td colspan="3"></td>
				<td id="btSaveContainer"></td>
				<td id="btCancelContainer"></td>
			</tr>
		</table>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>


