/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

import java.text.DecimalFormat;

/**
 *
 * @author Mankaran
 */
public class Stocks {
    
    // instance variables
    private String date;
    private String time;
    private String ticker;
    private double buyPrice;
    private int quantity;
    private double purchaseTotal;
    DecimalFormat df = new DecimalFormat("#.##");
    
    // contructor
    public Stocks(String date, String time, String ticker, double buyPrice, int quantity, double purchaseTotal) {
        // set stock values equal to instance variables
        this.date = date;
        this.time = time;
        this.ticker = ticker;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
        this.purchaseTotal = purchaseTotal;
    }
    
    /**
     * returns the date the stock is bought
     * @return the date
     */
    public String getDate() {
        return this.date;
    }
    
    /**
     * returns the time the stock is bought
     * @return the time
     */
    public String getTime() {
        return this.time;
    }
    
    /**
     * returns the ticker symbol of the stock
     * @return the ticker symbol
     */
    public String getTicker() {
        return this.ticker;
    }
    
    /**
     * returns the cost of the stocks the user spent
     * @return the cost the user spent
     */
    public double getPurchaseTotal() {
        return Double.parseDouble(this.df.format(this.getBuyPrice() * this.getQuantity()));
    }
    
    /**
     * sets purchase total to the newTotal
     * @param newTotal 
     */
    public void setPurchaseTotal(double newTotal) {
        this.purchaseTotal = newTotal;
    }
    
    /**
     * returns the price the user initially bought one share at
     * @return the initial buy price
     */
    public double getBuyPrice() {
        return this.buyPrice;
    }
    
    /**
     * returns the quantity of shares the user has
     * @return the amount of shares the  user has
     */
    public int getQuantity() {
        return this.quantity;
    }
    
    /**
     * sets quantity equal to the quantity entered in the parameters
     * @param quantity 
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}