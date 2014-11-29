<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="IoTDashboard.css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="body">
<form action="">
		<h3>Controllers</h3>
		<br />
		<table>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Min capacity</th>
				<th>Max capacity</th>
				<th>Status</th>
			</tr>
			<tr>
				<td><input type="button" value="Edit" name="btEdit" id="btEdit" onClick="ediC();"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><input type="button" value="Add" name="btAdd" id="btAdd" onClick="addC();"></td>
			</tr>
		</table>
	</form>
  </div>
  
  
<div id="body">
<script type="text/javascript">
	function addS() {
				window.location = "layout.jsp?page=addSensor";
			}
	
	
	function ediS() {
		window.location = "layout.jsp?page=addSensor";
	}
		</script>
		
		
	<form action="">
		<h3>Sensors</h3>
		<br />
		<table>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Unit</th>
			</tr>
			<tr>
				<td><input type="button" value="Edit" name="btEdit" id="btEdit" onClick="ediS();"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><input type="button" value="Add" name="btAdd" id="btAdd" onClick="addS();"></td>
			</tr>
		</table>
	</form>
  </div>
</div>


<jsp:include page="footer.jsp" />
</body>
</html>



