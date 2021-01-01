<%-- 
    Document   : ViewAllOrders
    Created on : Apr 28, 2020, 4:14:23 PM
    Author     : abdal
--%>

<%@page import="Models.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View All Orders</title>
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
        <jsp:useBean id="sessionBean" type="Models.SessionOrderBean" scope="session"/>
        <div class="order-servlet-header">
            <h1 class="title-1">Hello,<jsp:getProperty name="sessionBean" property="username"/> </h1>
            <div class="line-1"></div>
            <h2 class="title-2">Check All Orders:</h2>
        </div>
        <%
            Map<String, List<Order>> applicationOrderList = (HashMap) application.getAttribute("applicationOrderList");
            if (null == applicationOrderList) {
                out.print("No Orders !");
                String html = "<p><a href='order.html'>Go back to order page</a></p>";
                out.print(html);
            } else {
                for (Map.Entry<String, List<Order>> entry : applicationOrderList.entrySet()) {
                    String u = entry.getKey();
                    List<Order> orders = entry.getValue();
                    for (Order o : orders) {
        %>                            
        <div class='order-id'>User: <%=u%></div>
        <div class="row">
            <%=o.printOrder()%>
        </div>;
        <%
                }
            }
        %>

        <%
            }
        %>
        <script src="showDialog.js"></script>
    </body>
</html>
