<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/IoTDashboard.css">
<script type="text/javascript" language="javascript"
	src="MEpSensor/MEpSensor.nocache.js"></script>
<title>SmartCity</title>
</head>
<body>
	<div id="root" style="display: none">
		<jsp:include page="header.jsp" />

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Sensor</h3>
			</div>
			<div class="panel-body">
				<div id="formContainer"></div>
			</div>
		</div>

		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>
