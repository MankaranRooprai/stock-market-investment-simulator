/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

import java.io.IOException;
import java.math.BigDecimal;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 *
 * @author Mankaran
 */
public class StockData {

    // instance of stock class and users arraylist
    Stock stock;
    Stocks userStocks;

    // contructor that takes in the account number and the users arraylist
    public StockData() {

    }

    // method to get asking price for stock
    public String getStockAsk(String ticker) throws IOException {

        BigDecimal askPrice;

        // check if the stock exists 
        if ((this.stock = YahooFinance.get(ticker)) != null) {
            // if the stock exists, get the price
            askPrice = this.stock.getQuote().getAsk();
            return askPrice.toString();
        } else {
            // otherwise return nothing 
            return null;
        }
    }

    public String getStockBid(String ticker) throws IOException {

        BigDecimal bidPrice;

        if ((this.stock = YahooFinance.get(ticker)) != null) {
            bidPrice = this.stock.getQuote().getAsk();
            return bidPrice.toString();
        } else {
            return null;
        }
    }

    public boolean buyStock(String ticker, User user, double quantity) throws IOException {
        System.out.println("Buying stock");
        this.stock = YahooFinance.get(ticker);

        boolean canBuy = false;

        BigDecimal askPrice;

        if ((this.stock = YahooFinance.get(ticker)) != null) {
            askPrice = this.stock.getQuote().getAsk();
            // set user's balance and add the stock to their stocks arraylist
            double decreasePrice = askPrice.doubleValue() * quantity;
            if (user.getBalance() > decreasePrice) {
                System.out.println(user.getBalance());
                user.decreaseBalance(decreasePrice);
                System.out.println(user.getBalance());
                user.stocks.add(new Stocks(ticker, askPrice.doubleValue(), quantity));
                canBuy = true;
            } else {
                canBuy = false;
            }
        }

        return canBuy;

    }

    public void sellStock(String ticker, User user, double quantity) throws IOException {
        System.out.println("Selling stock");
        this.stock = YahooFinance.get(ticker);

        boolean canBuy = false;

        BigDecimal bidPrice;

        if ((this.stock = YahooFinance.get(ticker)) != null) {
            bidPrice = this.stock.getQuote().getAsk();
            // set user's balance and add the stock to their stocks arraylist
            double decreasePrice = bidPrice.doubleValue() * quantity;
            System.out.println(user.getBalance());
            user.decreaseBalance(decreasePrice);
            System.out.println(user.getBalance());
            for (int i = 0; i < user.stocks.size(); i++) {
                if (user.stocks.get(i).getTicker().equals(ticker)) {
                    user.stocks.remove(i);
                }
            }
        }

    }

}
