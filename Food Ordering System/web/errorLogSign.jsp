<%-- 
    Document   : LoginResult
    Created on : May 1, 2020, 11:22:47 PM
    Author     : abdal
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%try 
        {
            String errorType = (String)session.getAttribute("errorType");
            
            //If user doesn't exist 
            if(errorType.contains("invalid"))
            {%>
                Invalid username or password.Try again !
                <br>
                <a href="index.html"> Go back to Log-in/Sign-up page </a>
                <%
            }
            else if(errorType.contains("exists"))
            {%>
                User already exists. Try a different username !
                <br>
                <a href="index.html"> Go back to Log-in/Sign-up page </a>
            <%}
        }
        catch(Exception e){%>
        Exception
        <%}%>
        
    </body>
</html>
