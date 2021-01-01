<%-- 
    Document   : ViewMyOrders
    Created on : Apr 28, 2020, 3:50:43 PM
    Author     : abdal
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="Models.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View My Orders</title>
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

        <%
            //String username = (String)session.getAttribute("username");
        %>
        <%try {%>
        <jsp:useBean id="sessionBean" type="Models.SessionOrderBean" scope="session"/>

        <div class="order-servlet-header">
            <h1 class="title-1">Hello,<jsp:getProperty name="sessionBean" property="username"/> </h1>
            <div class="line-1"></div>
            <h2 class="title-2">Check Your Orders:</h2>
        </div>

        <%if (!sessionBean.getOrderList().equals("")) {%>
        <br><br>
        <jsp:getProperty name="sessionBean" property="orderList"/>
        <% } else {%>
        You don't have any orders !
        <%}
        } catch (Exception ex) {%>Session Order Bean Exception<%}%>
        <%
            // SessionOrderBean ob = (SessionOrderBean) session.getAttribute("sessionBean");

            // out.print(ob.getUsername()+"<br>");
            // out.print(ob.getOrderList()+"<br>");
            /*
            List<Order> orderList = (List<Order>) session.getAttribute("orderList");
            if(orderList != null && (orderList.size()>0))
            {
                for(Order o:orderList)
                out.print(o.printOrder());
            }
            else if(orderList != null && (orderList.size()==0))
            {
                out.print("OrderList list is empty");
            }
            else
            {
                out.print("OrderList list is null");
            }
             */
        %>
        <script src="showDialog.js"></script>
    </body>
</html>
