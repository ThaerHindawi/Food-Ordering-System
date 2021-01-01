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

//This class is just an implementation of the abstract class because we needed to use it

public class Meals extends Meal{

    @Override
    public void calculatePrice() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String printMeal() {
        String output = "";

        output += "<div class=\"column\">"
                + "<div class=\"content\">";
        output += "<img src=\"./Images/"
                + this.getMealType() + ".jpg\""
                + " alt=\"\" class=\"image\">";
        output += "<div>";
        output += "Meal Type: " + this.getMealType() + "<br>";
        output += ("Price: " + this.getPrice());
        output += "</div> </div> </div>";
        
         output += "<div class=\"modal\">"
                + "<div class=\"modal-content\">"
                + "<span class=\"close\">&times;</span>"
                + "<h4>"
                + "Meal Details:"
                + "</h4>";
        output += "Meal Type: " + this.getMealType() + "<br>";
        output += "Meal Deatails: " + this.getDetails() + "<br>";
        output += ("Price: " + this.getPrice()) + "<br>";
        output += "</div> </div>";
        return output;
    }
    
}
