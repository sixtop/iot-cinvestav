<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="IoTDashboard.css">
<script type="text/javascript" language="javascript"
	src="controller/controller.nocache.js"></script>

<script type="text/javascript">
	function addC() {
		window.location = "addController.jsp";
	}

	function ediC() {
		window.location = "addController.jsp";
	}
</script>


<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="body">

		<h2>Controllers</h2>
		<br />
		<table>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Location</th>
				<th>Status</th>
			</tr>
			<tr>
				<td><input type="button" value="Edit" onClick="ediC();"></td>
			</tr>
		</table>

		<table>
			<tr>
				<td><input type="button" value="Add" onClick="addC();"></td>
			</tr>
		</table>

	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>



