<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
#chart svg {
	height: 500px;
	width: 1200px;
}
</style>
<link type="text/css" rel="stylesheet" href="css/IoTDashboard.css">
<link type="text/css" rel="stylesheet" href="css/nv.d3.css">

<script type="text/javascript" language="javascript" src="MEpWPDatas/MEpWPDatas.nocache.js"></script>
<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
<script src="scripts/nv.d3.min.js"></script>
<script type="text/javascript" language="javascript" src="scripts/smartcitygrapher.js"></script>

<title>SmartCity</title>
</head>
<body>
	<div id="root" style="display: none">
		<jsp:include page="header.jsp" />

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Statistics</h3>
			</div>
			<div class="panel-body">
				<div id="formContainer"></div>

				<div style="display: none" id="chart">
					<svg></svg>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>
