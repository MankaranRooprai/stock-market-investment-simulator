/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 *
 * @author Mankaran
 */
public class StockData {

    // instance variables
    Stock stock;
    public int counter = 0;
    DecimalFormat df = new DecimalFormat("#.##");
    public double newPurchaseTotal;
    public double oldPurchaseTotal;

    // contructor
    public StockData() {

    }

    // method to get stock history takes in a ticker symbol
    public List getHistory(String ticker) throws IOException {
        // get stock
        this.stock = YahooFinance.get(ticker);

        // return stock history
        return this.stock.getHistory();
    }

    // method to get asking price for a stock and takes in a ticker symbol 
    public String getStockAsk(String ticker) throws IOException {

        // variable to store stock asking price
        BigDecimal askPrice;

        // check if the stock exists 
        if ((this.stock = YahooFinance.get(ticker)) != null) {
            // if the stock exists, get the asking price
            askPrice = this.stock.getQuote().getAsk();
            //return the asking price
            return askPrice.toString();
            // otherwise return null
        } else {
            return null;
        }
    }

    // method to get bid price for a stock and takes in a ticker symbol 
    public String getStockBid(String ticker) throws IOException {

        // variable to store stock bidding price
        BigDecimal bidPrice;

        // check if the stock exists
        if ((this.stock = YahooFinance.get(ticker)) != null) {
            // if the stock exists, get the bidding price and return it
            bidPrice = this.stock.getQuote().getBid();
            return bidPrice.toString();
            // otherwise return null
        } else {
            return null;
        }
    }

    // method to get stock high price for the day of a stock, and takes in a ticker symbol 
    public String getStockHigh(String ticker) throws IOException {

        // variable to store stock day high price
        BigDecimal dayHigh;

        // check if the stock exists
        if ((this.stock = YahooFinance.get(ticker)) != null) {
            // if the stock exists, get the day high price and return it
            dayHigh = this.stock.getQuote().getDayHigh();
            return dayHigh.toString();
            // otherwise return null
        } else {
            return null;
        }
    }

    // method to get stock high price for the day of a stock, and takes in a ticker symbol 
    public String getStockLow(String ticker) throws IOException {

        // variable to store stock day low price
        BigDecimal dayLow;

        // check if the stock exists
        if ((this.stock = YahooFinance.get(ticker)) != null) {
            // if the stock exists, get the day low price and return it
            dayLow = this.stock.getQuote().getDayLow();
            return dayLow.toString();
            // otherwise return null
        } else {
            return null;
        }
    }

    // method to get stock volume of a stock, and takes in a ticker symbol 
    public long getStockVolume(String ticker) throws IOException {

        // variable to store stock volume
        long volume;

        // check if the stock exists
        if ((this.stock = YahooFinance.get(ticker)) != null) {
            // if the stock exists, get the volume and return it 
            volume = this.stock.getQuote().getVolume();
            return volume;
            // otherwise return a number
        } else {
            return -2;
        }
    }

    // method to buy stock which takes in a ticker symbol, user, and quantity of stocks the user would like to buy
    public boolean buyStock(String ticker, User user, int quantity) throws IOException {

        // variable to store whether or not user can buy stock
        boolean canBuy = false;

        // asking price of stock
        String askPrice = this.getStockAsk(ticker);

        // if there is an asking price, 
        if (askPrice != null) {

            // calculate cost of buying the stocks
            double decreasePrice = Double.parseDouble(this.df.format(Double.parseDouble(askPrice) * quantity));

            // if the user has sufficient funds to buy the stock, then buy it 
            if (user.getBalance() > decreasePrice) {

                // decrease the user's balance
                user.decreaseBalance(decreasePrice);

                // add the new stock to the user's stocks arraylist
                user.stocks.add(new Stocks(java.time.LocalDate.now().toString(), java.time.LocalTime.now().toString(), ticker, Double.parseDouble(askPrice), quantity, decreasePrice));

                //set canBuy to true
                canBuy = true;
            // otherwise, if user cannot afford the stock, set canBuy to false
            } else {
                canBuy = false;
            }
        }

        return canBuy;

    }

    // method to sell stocks that takes in a ticker, user, and quantity of stocks that the user would like to sell
    public int sellStock(String ticker, User user, int quantity, String time) throws IOException {

        // variable to store quantity of stocks user has left after selling
        int newQuantity = 0;

        // store bidding price of stock
        String bidPrice = this.getStockBid(ticker);

        // check if the stock exists, 
        if (bidPrice != null) {

            // iterate through each user's stocks arraylist
            for (int i = 0; i < user.stocks.size(); i++) {

                // check if any of the stocks are equal to the ticker symbol entered by the user and the buyPrice, since the user could have multiple positions with the same ticker symbol
                if (user.stocks.get(i).getTime().equals(time)) {

                    // check to see if the user has more stocks than the user would like to sell
                    if (user.stocks.get(i).getQuantity() >= quantity) {

                        this.oldPurchaseTotal = Double.parseDouble(this.df.format(user.stocks.get(i).getBuyPrice() * quantity));
                        
                        // calculate the amount to add to the user's balance
                        this.newPurchaseTotal = Double.parseDouble(this.df.format(Double.parseDouble(bidPrice) * quantity));

                        // increase the user's balance
                        user.increaseBalance(this.newPurchaseTotal);

                        // set the quantity of stocks of the user
                        user.stocks.get(i).setQuantity(user.stocks.get(i).getQuantity() - quantity);

                        // set the new purchase total with the remaining stocks
                        user.stocks.get(i).setPurchaseTotal(user.stocks.get(i).getBuyPrice() * quantity);

                        // check if quantity is 0, 
                        if (user.stocks.get(i).getQuantity() == 0) {
                            // and if so, remove the stock from the arraylist and exit out of the for loop
                            user.stocks.remove(i);
                            // set newQuantity equal to 0 and the counter equal to the stock index
                            newQuantity = 0;
                            this.counter = i;
                        // otherwise, set newQuantity equal to however many shares the user has and the counter to the index of the stock
                        } else {
                            newQuantity = user.stocks.get(i).getQuantity();
                            this.counter = i;
                        }
                        // break out of the for loop
                        break;
                     // if the user has less stocks in quantity than the amount they've entered to sell, 
                    } else {
                        
                        // store old purchase total
                        this.oldPurchaseTotal = Double.parseDouble(this.df.format(user.stocks.get(i).getBuyPrice() * user.stocks.get(i).getQuantity()));
                        
                        // then calculate the amount to add to the user's balance (all of the stocks in this position will be sold)
                        this.newPurchaseTotal = Double.parseDouble(this.df.format(Double.parseDouble(bidPrice) * user.stocks.get(i).getQuantity()));

                        // increase the user's balance
                        user.increaseBalance(this.newPurchaseTotal);

                        // set the new purchase total with the remaining stocks
                        user.stocks.get(i).setPurchaseTotal(user.stocks.get(i).getBuyPrice() * quantity);
                        
                        // set newQuantity equal to zero and store the stock's index in counter
                        newQuantity = 0;
                        this.counter = i;

                        // remove the stock from the arraylist and exit of out of the for loop
                        user.stocks.remove(i);

                        break;
                    }
                }
            }
        }

        // return newQuantity
        return newQuantity;

    }

}
