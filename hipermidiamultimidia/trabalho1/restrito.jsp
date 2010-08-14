<%-- 
    Document   : restrito.jsp
    Created on : Aug 14, 2010, 16:15:51 PM
    Author     : Ricardo Bernardelli 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <%
            String username = (String) session.getAttribute("username");
            if(username == null){
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        %>
        <h1>Bem-vindo <%= username %>!</h1>
        <button type="button" onclick="window.location='login';">Logoff</button>
    </body>
</html> 

