<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Index</title>
  </head>
  <body>
    <%
      if(request.getSession().getAttribute("username") != null){
        RequestDispatcher view = request.getRequestDispatcher("restrito.jsp");
        view.forward(request, response);
      }

      String message = (String) request.getAttribute("message");

      if(message != null){
        out.print(message);
      }
    %>
    <form method="post" action="login.do">
      <label for="username">Username</label>
      <input type="text" size="10" name="username"/><br/>
      <label for="password">Password</label>
      <input type="text" size="10" name="password"><br/>
      <input type="submit" value="Login"/>
    </form>
  </body>
</html>
