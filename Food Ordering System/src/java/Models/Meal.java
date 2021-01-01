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
public abstract class Meal {
    
    private String mealType;
    private String details;
    private double price;
    
    public void setMealType (String mealType)
    {
        if (mealType.equals(""))
            this.mealType = "Invalid Meal Type";
        else
            this.mealType = mealType;
    }
    
    public String getMealType ()
    {
        return this.mealType;
    }
    
    public void setDetails (String details)
    {
        this.details = details;
    }
    
    public String getDetails ()
    {
        return this.details;
    }
    
    public abstract void calculatePrice();
    public abstract String printMeal();
    
    public void abstractDetails (String mealType,String details,double price)
    {
        this.mealType = mealType;
        this.details = details;
        this.price = price;
    }
    
    public void setPrice (double price)
    {
        this.price = price;
    }
    public double getPrice ()
    {
        return this.price;
    }
    
}
