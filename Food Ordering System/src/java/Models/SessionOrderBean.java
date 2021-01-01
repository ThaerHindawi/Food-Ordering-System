/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.*;

/**
 *
 * @author abdal
 */
//This class is made just to be used as a bean in jsp. To be able to get infomation.
public class SessionOrderBean {

    String username;
    List<Order> orderList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderList() {
        String output = "";        
        
        
        for (Order o : orderList) {
            output += "<div class=\"row\">";
            output += o.printOrder();
            output += "</div>";
        }
        
        return output;
    }

    /*
    public List<Order> getOrderList() {
        return orderList;
    }
     */
    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void setItem(Order order) {
        this.orderList.add(order);
    }

    public SessionOrderBean() {
        orderList = new ArrayList<Order>();
    }

    public SessionOrderBean(String username, List<Order> orderList) {
        this.username = username;
        if (orderList.size() != 0) {
            this.orderList = orderList;
        } else {
            this.orderList = new ArrayList<Order>();
        }
    }

}
