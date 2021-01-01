<%-- 
    Document   : ViewMyPastOrders
    Created on : May 2, 2020, 11:34:40 PM
    Author     : abdal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View My Past Orders</title>
        <link rel="stylesheet" href="style.css">
        <link
            href="https://fonts.googleapis.com/css2?family=Exo:wght@600&family=Lobster&family=Raleway:ital,wght@1,700&display=swap"
            rel="stylesheet">
        <link
            href="https://fonts.googleapis.com/css2?family=Amiri:ital,wght@1,700&family=Baloo+Paaji+2:wght@500&family=Dancing+Script:wght@562&family=Exo:wght@600&family=Lobster&family=Raleway:ital,wght@1,700&display=swap"
            rel="stylesheet">
    </head>
    <body class="order-sevlet">
        <nav>
            <ul>
                <li><a class="active" href="order.html">Menu</a></li>
                <li class="btn-form__logout"><a href="index.html">Logout</a></li>
            </ul>
        </nav>  
        
        <%try {%>
        <jsp:useBean id="sessionBean" type="Models.SessionOrderBean" scope="session"/>

        <div class="order-servlet-header">
            <h1 class="title-1">Hello,<jsp:getProperty name="sessionBean" property="username"/> </h1>
            <div class="line-1"></div>
            <h2 class="title-2">Check your past orders:</h2>
        </div>
        <br>
        <%if (!sessionBean.getOrderList().equals("")) {%>
        <br><br>
        <jsp:getProperty name="sessionBean" property="orderList"/>
        <% } else {%>
        You don't have any past orders !
        <%}
        } catch (Exception ex) {%>Session Order Bean Exception<%}%>

        <script src="showDialog.js"></script>

    </body>
</html>
