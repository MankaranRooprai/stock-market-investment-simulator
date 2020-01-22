/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

/**
 *
 * @author Mankaran
 */
public class Stocks {
    
    private String date;
    private String time;
    private String ticker;
    private double buyPrice;
    private int quantity;
    private double purchaseTotal;
    
    public Stocks(String date, String time, String ticker, double buyPrice, int quantity, double purchaseTotal) {
        this.date = date;
        this.time = time;
        this.ticker = ticker;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
        this.purchaseTotal = purchaseTotal;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public String getTicker() {
        return this.ticker;
    }
    
    public double getPurchaseTotal() {
        return this.getBuyPrice() * this.getQuantity();
    }
    
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
    
    public double getBuyPrice() {
        return this.buyPrice;
    }
    
    public void setBuyprice(double buyPrice) {
        this.buyPrice = buyPrice;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}