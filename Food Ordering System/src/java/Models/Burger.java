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
public class Burger extends Meal{
    
    private String meatType;
    private List<String> addOns;
    private int weight;
    
    public Burger () {}
    
    public Burger (String meatType,List<String> addOns,int weight,double price)
    {
        this.setMealType("Burger");
        this.setMeatType(meatType);
        this.setAddOns(addOns);
        this.setWeight(weight);
        //this.calculatePrice();
        this.setPrice(price);
        String details = this.meatType+","+this.getAddOns()+this.getWeight()+"g";
        this.setDetails(details);
    }
    
    public void setMeatType (String meatType)
    {
        this.meatType = meatType;
    }
    public String getMeatType ()
    {
        return this.meatType;
    }
    
    public void setAddOns (List<String> addOns)
    {
        this.addOns = new ArrayList <String> ();
        for(int i=0;i<addOns.size();i++)
            this.addOns.add(addOns.get(i));
    }
    public String getAddOns ()
    {
        String addOnsResult="";
        for (int i=0;i<addOns.size();i++)
            addOnsResult+=this.addOns.get(i);
        return addOnsResult;
    }
    
    public void setWeight (int weight)
    {
        this.weight = weight;
    }
    public int getWeight ()
    {
        return this.weight;
    }
    
    @Override
    public void calculatePrice()
    {
        double price = 3;
        
        if(this.meatType.equals("Beef"))
            price+=0.5;
        if(this.addOns.contains("Cheese"))
            price+=0.25;
        
        if(this.weight == 100) price+=0;
        else if(this.weight == 150) price+=0.5;
        else if(this.weight == 200) price+=1.0;
        else if(this.weight == 250) price+=1.5;
        
        this.setPrice(price);
    }
    
    @Override
    public String printMeal ()
    {
       String output = "";

        output += "<div class=\"column\">"
                + "<div class=\"content\">";
        output += "<img src=\"./Images/Burger.jpg\" alt=\"\" class=\"image\">";
        output += "<div>";
        output += "Meal Type: " + this.getMealType() + "<br>";
        output += "Meat Type: " + this.getMeatType() + "<br>";
        output += ("Price: " + this.getPrice());
        output += "</div> </div> </div>";

        output += "<div class=\"modal\">"
                + "<div class=\"modal-content\">"
                + "<span class=\"close\">&times;</span>"
                + "<h4>"
                + "Meal Details:"
                + "</h4>";
        output += "Meal Type: " + this.getMealType() + "<br>";
        output += "Meat Type: " + this.getMeatType() + "<br>";
        output += "Weight: " + this.getWeight() + "<br>";
        if (addOns.size() != 0) {
            output += ("Add ons: ");
            for (String s : addOns) {
                output += (s + " ");
            }
            output += ("<br>");
        }
        output += ("Price: " + this.getPrice()) + "<br>";
        output += "</div> </div>";
        return output;
    }
}
