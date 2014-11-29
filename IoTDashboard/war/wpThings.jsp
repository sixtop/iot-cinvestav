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
<script type="text/javascript">
	function addT() {
				window.location = "layout.jsp?page=addThing";
			}
		</script>
<form action="">
	 <h3>Things</h3> <br />
	 	<table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th></th>
            </tr>
            <tr>
            <td>aaaaaa</td>
            <td>aaaaaa</td>
            	<td><input type="button" value="Edit" name="btEdit" id="btEdit" onClick="ediT();"></td>
            </tr>
        </table>
        	
	 	<table>
            <tr>
                <td><input type="button" value="Add" name="btAdd" id="btAdd" onClick="addT();"></td>
            </tr>
        </table>
	
	</form>
  </div>
<jsp:include page="footer.jsp" />
</body>
</html>


