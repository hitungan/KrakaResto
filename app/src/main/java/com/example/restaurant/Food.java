package com.example.restaurant;

public class Food {
    private String foodId;
    private String foodName;
    private String foodDesc;
    private int foodQty;
    private int foodImg;
    private double foodPrice;

    public Food(String foodId, String foodName, String foodDesc, int foodQty, int foodImg, double foodPrice) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.foodQty = foodQty;
        this.foodImg = foodImg;
        this.foodPrice = foodPrice;
    }

    public void changeQuantity(int newqty){
        foodQty = newqty;
    }


    public String getFoodName() {
        return foodName;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public int getFoodQty() {
        return foodQty;
    }

    public void setFoodQty(int foodQty) {
        this.foodQty = foodQty;
    }

    public int getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(int foodImg) {
        this.foodImg = foodImg;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
}
