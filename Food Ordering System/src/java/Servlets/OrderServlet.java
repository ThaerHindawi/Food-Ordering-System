package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Models.*;
import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

/**
 *
 * @author abdal
 */
@WebServlet(urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    //private static final String oraclePass = "thaer";
    private static final String oraclePass = "123456";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String errorType = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        session.removeAttribute("loginResult");

        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", oraclePass);
            Statement st = c.createStatement();

            //Ceck if tables exist
            DatabaseMetaData dbm = c.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "USERS", null);
            if (tables.next()) {
                //out.print("TABLE USERS EXISTS <br>");
            } else {
                String userTable = "create table users (username varchar(30),password varchar(30),primary key(username))";
                st.executeUpdate(userTable);
            }
            tables = dbm.getTables(null, null, "ORDERS", null);
            if (tables.next()) {
                //out.print("TABLE ORDERS EXISTS <br>");
            } else {
                String orderTable = "create table orders (username varchar(30),id integer,orderPrice decimal(9,2),"
                        + "status varchar(10),primary key(username,id),foreign key(username) REFERENCES users(username))";
                st.executeUpdate(orderTable);
            }
            tables = dbm.getTables(null, null, "MEALSINORDER", null);
            if (tables.next()) {
                //out.print("TABLE MEALSINORDER EXISTS <br>");
            } else {
                String mealInOrderTable = "create table mealsInOrder (username varchar(30),orderId integer,mealId inte"
                        + "ger,mealType varchar(10),details varchar(100),mealPrice decimal(5,2),"
                        + "primary key(username,orderId,mealId),foreign key(username,orderId) REFERENCES orders(username,id))";
                st.executeUpdate(mealInOrderTable);
            }

            if (session.isNew()) {
                //Changing active orders to past orders for all users
                String updateQuery = "update orders set status='past'";
                st.executeUpdate(updateQuery);
            }

            if (request.getParameter("button").contains("Log-in")) {
                //Check if the user exists
                int count = 0;

                String userSelectQuery = "select * from users where username='" + username + "' and password='" + password + "'";
                ResultSet rs = st.executeQuery(userSelectQuery);
                while (rs.next()) {
                    count++;
                }

                //If user doesn't exist
                if (count == 0) {
                    errorType = "invalid username or password";
                    session.setAttribute("errorType", errorType);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("errorLogSign.jsp");
                    dispatcher.include(request, response);
                } //If user exists
                else {
                    session.setAttribute("username", username);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("order.html");
                    dispatcher.include(request, response);
                }
            } else if (request.getParameter("button").contains("Sign-up")) {
                //Check if the user exists
                int count = 0;

                String userSelectQuery = "select * from users where username='" + username + "'";
                ResultSet rs = st.executeQuery(userSelectQuery);
                while (rs.next()) {
                    count++;
                }

                //Username already exists
                if (count != 0) {
                    errorType = "user already exists";
                    session.setAttribute("errorType", errorType);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("errorLogSign.jsp");
                    dispatcher.include(request, response);
                } //All things are ok .Then add user
                else {
                    //Add user to database
                    String userAddQuery = "insert into users values('" + username + "','" + password + "')";
                    st.executeUpdate(userAddQuery);

                    session.setAttribute("username", username);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("order.html");
                    dispatcher.include(request, response);
                }

            }
        } catch (SQLException ex) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<br>exception <br>");

            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ServletContext application = getServletContext();
        String username = (String) session.getAttribute("username");
        String clickChoice = (String) request.getParameter("button");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", oraclePass);
            Statement st = c.createStatement();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (clickChoice.contains("Place Order")) {
                int mealId = 0;
                List<Meal> mealList = new ArrayList<Meal>();
                String[] mealType = (request.getParameterValues("mealType"));
                String[] mealDetails = (request.getParameterValues("mealDetails"));
                String[] mealPrice = (request.getParameterValues("mealPrice"));
                if (null != mealType) {
                    for (int i = 0; i < mealType.length; i++) {
                        if (mealType[i].contains("Burger")) {
                            String[] details = mealDetails[i].split(",");

                            String meatType = details[0];
                            int weight = Integer.parseInt(details[1]);

                            List<String> addOns = new ArrayList<String>();
                            for (int j = 2; j < details.length; j++) {
                                if (details[j] != null) {
                                    addOns.add(details[j]);
                                }
                            }

                            double price = Double.parseDouble(mealPrice[i]);

                            Burger burger = new Burger(meatType, addOns, weight,price);
                            mealList.add(burger);

                        } else if (mealType[i].contains("Pizza")) {
                            String[] details = mealDetails[i].split(",");

                            String pizzaType = details[0];
                            String size = details[1];
                            double price = Double.parseDouble(mealPrice[i]);

                            Pizza pizza = new Pizza(pizzaType, size);
                            mealList.add(pizza);

                        } else if (mealType[i].contains("Pasta")) {
                            String[] details = mealDetails[i].split(",");

                            String pastaType = details[0];
                            String meatType = details[1];
                            double price = Double.parseDouble(mealPrice[i]);

                            Pasta pasta = new Pasta(pastaType, meatType);
                            mealList.add(pasta);

                        }
                    }

                    //Get current number of orders
                    int orderId = 0;
                    String orderNumbersSelectQuery = "select * from orders";
                    ResultSet rs = st.executeQuery(orderNumbersSelectQuery);
                    while (rs.next()) {
                        orderId++;
                    }

                    //Insert order into table
                    Order order = new Order(++orderId, mealList);
                    String orderAddQuery = "insert into orders values('" + username + "'," + order.getId() + "," + order.getTotalBill() + ",'active')";
                    st.executeUpdate(orderAddQuery);

                    //Adding meals
                    for (Meal m : mealList) {
                        ++mealId;
                        //Add meals to database
                        String mealAddQuery = "insert into mealsinorder values('" + username + "'," + order.getId() + "," + mealId
                                + ",'" + m.getMealType() + "'," + "'" + m.getDetails() + "'," + m.getPrice() + ")";
                        st.executeUpdate(mealAddQuery);
                    }

                    RequestDispatcher dispatcher = request.getRequestDispatcher("order.html");
                    dispatcher.include(request, response);
                } else {
                    out.print("Error You should Add Meals first");
                }

            } else if (clickChoice.contains("View My Orders")
                    || clickChoice.contains("View My Past Orders")) {
                List<Order> orderList = new ArrayList<Order>();
                List<Integer> orderNumbers = new ArrayList<Integer>();

                //Fetch this user's orders from database
                String orderSelectQuery = "select * from orders where orders.username='" + username + "'and status='active'";
                ResultSet rs = st.executeQuery(orderSelectQuery);

                if (clickChoice.contains("View My Past Orders")) {
                    orderSelectQuery = "select * from orders where orders.username='" + username + "'and status='past'";
                    rs = st.executeQuery(orderSelectQuery);
                }

                while (rs.next()) {
                    int id = rs.getInt("id");
                    orderNumbers.add(id);
                }
                for (int i : orderNumbers) {
                    List<Meal> listOfMeals = new ArrayList<Meal>();

                    String mealSelectQuery = "select * from mealsinorder where mealsinorder.orderid ='" + i + "' and mealsinorder.username='" + username + "'";
                    ResultSet rs2 = st.executeQuery(mealSelectQuery);
                    while (rs2.next()) {
                        int mealid = rs2.getInt("mealid");
                        double mealPrice = rs2.getDouble("mealPrice");
                        String mealType = rs2.getString("mealtype");
                        String details = rs2.getString("details");
                        Meals m = new Meals();
                        m.abstractDetails(mealType, details, mealPrice);
                        listOfMeals.add(m);
                    }
                    Order order = new Order(i, listOfMeals);
                    orderList.add(order);

                }

                //session.removeAttribute("orderList");
                //session.setAttribute("orderList",orderList);
                SessionOrderBean sessionBean = new SessionOrderBean(username, orderList);
                session.removeAttribute("sessionBean");
                session.setAttribute("sessionBean", sessionBean);

                if (clickChoice.contains("View My Orders")) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("ViewMyOrders.jsp");
                    dispatcher.include(request, response);
                } else if (clickChoice.contains("View My Past Orders")) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("ViewMyPastOrders.jsp");
                    dispatcher.include(request, response);
                }

            } else if (clickChoice.contains("View All Users' Orders")) {
                Map<String, List<Order>> applicationOrderList = new HashMap<String, List<Order>>();
                Map<Integer, String> orderidUsernameMap = new HashMap<Integer, String>();
                //Fetch this all users orders from database
                //Select * from orders
                List<Order> orderList = new ArrayList<Order>();
                List<Integer> orderNumbers = new ArrayList<Integer>();
                String orderSelectQuery = "select * from orders";
                ResultSet rs = st.executeQuery(orderSelectQuery);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String orderUsername = rs.getString("username");
                    orderNumbers.add(id);
                    orderidUsernameMap.put(id, orderUsername);
                    if (applicationOrderList.get(orderUsername) == null) {
                        applicationOrderList.put(orderUsername, new ArrayList<Order>());
                    }
                }

                for (int i : orderNumbers) {
                    orderList = applicationOrderList.get(orderidUsernameMap.get(i));
                    List<Meal> listOfMeals = new ArrayList<Meal>();

                    String mealSelectQuery = "select * from mealsinorder where mealsinorder.orderid ='" + i + "'";
                    ResultSet rs2 = st.executeQuery(mealSelectQuery);
                    while (rs2.next()) {
                        int mealid = rs2.getInt("mealid");
                        double mealPrice = rs2.getDouble("mealPrice");
                        String mealType = rs2.getString("mealtype");
                        String details = rs2.getString("details");
                        Meals m = new Meals();
                        m.abstractDetails(mealType, details, mealPrice);
                        listOfMeals.add(m);
                    }

                    Order order = new Order(i, listOfMeals);
                    orderList.add(order);
                    applicationOrderList.replace(orderidUsernameMap.get(i), orderList);
                }

                application.setAttribute("applicationOrderList", applicationOrderList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllOrders.jsp");
                dispatcher.include(request, response);
            }

        } catch (SQLException ex) {
            //Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);    
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.print("Exception");
            out.print("<br><a href='order.html'>Go back</a>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
