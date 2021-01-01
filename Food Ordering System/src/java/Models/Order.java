/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdal
 */
public class Order {

    static private int counter = 0;
    private int id;
    List<Meal> meals;
    double totalBill;

    public Order() {
        this.id = ++counter;
    }

    public Order(List<Meal> meals) {
        this.id = ++counter;
        this.setMeals(meals);
        this.setTotalBill();
    }

    public Order(int id, List<Meal> meals) {
        this.id = id;
        this.setMeals(meals);
        this.setTotalBill();
    }

    public int getId() {
        return this.id;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = new ArrayList<Meal>();
        for (int i = 0; i < meals.size(); i++) {
            this.meals.add(meals.get(i));
        }
    }

    public List<Meal> getMeals() {
        return this.meals;
    }

    public void setTotalBill() {
        double total = 0;
        if (!(this.meals.isEmpty())) {
            for (int i = 0; i < this.meals.size(); i++) {
                total += meals.get(i).getPrice();
            }
        }
        this.totalBill = total;
    }

    public double getTotalBill() {
        return this.totalBill;
    }

    public String printOrder() {
        String output = "";
        output += "<h3 class=\"order-id\">";
        output += "Order Id:" + this.getId() + "<br>";
        output += "Total price for this order is : " + this.getTotalBill();
        output += "</h3>";
        for (Meal meal : meals) {
            output += "<div>";
            output += meal.printMeal();
            output += "</div>";
        }
        return output;
    }

}
