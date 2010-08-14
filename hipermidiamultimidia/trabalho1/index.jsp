<%-- 
    Document   : index.jsp
    Created on : Aug 14, 2010, 16:11:45 PM
    Author     : Ricardo Bernardelli 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        <h1>Trabalho 1</h1><br/><br/>
        <form name="login" method="post" action="login">
            <label for="username">Usuário: </label>
            <input type="text" name="username"/><br/><br/>
            <label for="password">Senha: </label>
            <input type="text" name="password"/><br/><br/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html> 

