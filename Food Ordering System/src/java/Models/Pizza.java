/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author abdal
 */
public class Pizza extends Meal{
    
    private String pizzaType;
    private String pizzaSize;
    
    public Pizza () {}
    public Pizza (String pizzaType,String pizzaSize)
    {
        this.setMealType("Pizza");
        this.setPizzaType(pizzaType);
        this.setPizzaSize(pizzaSize);
        this.calculatePrice();
        String details = this.pizzaType+","+this.pizzaSize;
        this.setDetails(details);
    }
    
    public void setPizzaType (String pizzaType)
    {
        this.pizzaType = pizzaType;
    }
    public String getPizzaType ()
    {
        return this.pizzaType;
    }
    
    public void setPizzaSize (String pizzaSize)
    {
        this.pizzaSize = pizzaSize;
    }
    public String getPizzaSize ()
    {
        return this.pizzaSize;
    }
    
    @Override
    public void calculatePrice()
    {
        double price = 3;
        
        if(this.pizzaSize.contains("Small")) price+=0;
        else if(this.pizzaSize.contains("Medium")) price+=1.0;
        else if(this.pizzaSize.contains("Large")) price+=2.0;
        
        this.setPrice(price);
    }
    
    @Override
    public String printMeal ()
    {
        String output = "";
        output += "<div class=\"column\">\n"
                + "<div class=\"content\">";
        output += "<img src=\"./Images/Pizza.jpg\" alt=\"\" class=\"image\">";
        output += "<div>";
        output += "Meal Type: " + this.getMealType() + "<br>";
        output += "Pizza Type: " + this.getPizzaType() + "<br>";
        output += ("Price: " + this.getPrice());
        output += "</div> </div> </div>";

        output += "<div class=\"modal\">"
                + "<div class=\"modal-content\">"
                + "<span class=\"close\">&times;</span>"
                + "<h4>"
                + "Meal Details:"
                + "</h4>";
        output += "Meal Type: " + this.getMealType() + "<br>";
        output += "Pizza Type: " + this.getPizzaType() + "<br>";
        output += ("Size : " + this.getPizzaSize() + "<br>");
        output += ("Price: " + this.getPrice());
        output += "</div> </div>";
        return output;
    }
}
