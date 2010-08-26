<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="mack.db.*, mack.auth.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Restrito</title>
  </head>
  <body>
    <%
      if(request.getSession().getAttribute("username") == null){
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
      }

      String message = (String) request.getAttribute("message");

      if(message != null){
        out.print(message);
      }
    %>
    <br/><br/>
    <h1>Welcome <%= request.getSession().getAttribute("username") %></h1>

    <form method="post" action="user_registration.do">
      <input type="text" name="username" size="10"/>
      <input type="text" name="password" size="10"/>
      <input type="submit" value="Register"/>
    </form>

    <br/>
    <button onclick="window.location='login.do'">Logoff</button>
  </body>
</html>
