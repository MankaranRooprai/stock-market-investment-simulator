/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 *
 * @author Mankaran
 */
public class StockData {
    
    Stock stock;
    ArrayList<User> users;
    int accountNumber;
    
    public StockData(ArrayList<User> users, int accountNumber) {
        this.users = users;
        this.accountNumber = accountNumber;
    }
    
    // method to get asking price for stock
    public String getStockAsk(String ticker) throws IOException {
        this.stock = YahooFinance.get(ticker);
        
        BigDecimal askPrice = this.stock.getQuote().getAsk();
        
        return askPrice.toString();
    }
    
    public String getStockBid(String ticker) throws IOException {
        this.stock = YahooFinance.get(ticker);
        
        BigDecimal bidPrice = this.stock.getQuote().getBid();
        
        return bidPrice.toString();
    }
    
    public void buyStock(String ticker) throws IOException {
        this.stock = YahooFinance.get(ticker);
        
        BigDecimal askPrice = this.stock.getQuote().getAsk();
        
        this.users.get(this.accountNumber).setBalance(askPrice.toString());
    }
    
}
