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
    
    private String ticker;
    private double buyPrice;
    private int quantity;
    
    public Stocks(String ticker, double buyPrice, int quantity) {
        this.ticker = ticker;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
    }
    
    public String getTicker() {
        return this.ticker;
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