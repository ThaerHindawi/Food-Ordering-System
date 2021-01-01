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
public class Pasta extends Meal{
    
    private String pastaType;
    private String meatType;
    
    public Pasta () {}
    public Pasta (String pastaType,String meatType)
    {
        this.setMealType("Pasta");
        this.setPastaType(pastaType);
        this.setMeatType(meatType);
        this.calculatePrice();
        String details = this.pastaType+","+this.meatType;
        this.setDetails(details);
    }
    
    public void setPastaType (String pastaType)
    {
        this.pastaType = pastaType;
    }
    public String getPastaType ()
    {
        return this.pastaType;
    }
    
    public void setMeatType (String meatType)
    {
        this.meatType = meatType;
    }
    public String getMeatType ()
    {
        return this.meatType;
    }
    
    @Override
    public void calculatePrice()
    {
        double price = 3;
        
        if(this.meatType.equals("Beef"))
            price+=0.5;
      
        this.setPrice(price);
    }
    
    @Override
    public String printMeal ()
    {
       String output = "";
        output += "<div class=\"column\">"
                + "<div class=\"content\">";
        output += "<img src=\"./Images/Pasta.jpg\" alt=\"\" class=\"image\">";
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
        output += "Pasta Type: " + this.getPastaType() + "<br>";
        output += "Meat Type: " + this.getMeatType() + "<br>";
        output += ("Price: " + this.getPrice());
        output += "</div> </div>";
        return output;
    }
}
