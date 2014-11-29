<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="body">
		<script type="text/javascript">
			function addS() {
				window.location = "layout.jsp?page=addSensor";
			}

			function addP() {
				var name = prompt("Name");
				var value = prompt("Value");
			}
		</script>

		<form action="">
			<h3>Things</h3>
			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" value=""></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><input type="text" value=""></td>
				</tr>
				<tr>
					<td>Controller:</td>
					<td><select name="controllers">
							<option>A</option>
							<option>B</option>
					</select></td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Property:</td>
					<td>
						<table>
							<tr>
								<th>Name</th>
								<th>Value</th>
							</tr>
							<tr>
								<td><input type="button" value="Add" name="btAdd"
									id="btAdd" onClick="addP();"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<jsp:include page="wpSensors.jsp" />


			<table>
				<tr>
					<td><input type="submit" value="Save" /></td>
					<td><input type="submit" value="Cancel" /></td>
				</tr>
			</table>

		</form>
	</div>


<jsp:include page="footer.jsp" />
</body>
</html>

