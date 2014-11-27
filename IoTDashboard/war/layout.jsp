<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <%

  // define la pagina a desplegar de acuerdo al parametro "page" del request.
  String sUrlPage = "index.jsp";
  if (request.getParameter("page") != null) { 
    sUrlPage = request.getParameter("page").toString()+".jsp";
  }
  %>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" type="image/png" href="favicon.ico">
<link rel="stylesheet" type="text/css" href="IoTDashboard.css" />
<title>SmartCity</title>
</head>
<body>
  <jsp:include page="header.jsp" />
  <div id="container">
    <jsp:include page="<%=sUrlPage%>" />
  </div>
  <jsp:include page="footer.jsp" />
</body>
</html>