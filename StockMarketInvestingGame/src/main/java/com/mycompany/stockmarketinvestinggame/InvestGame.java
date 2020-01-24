/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mankaran
 */
public class InvestGame {

    // arraylist to store users
    ArrayList<User> users = new ArrayList<>();
    // create instance of user info
    private UserInfo userInfo;
    // create instance of stock data
    private StockData stockData;

    // instance variables (are to be set in this class from StockData, and thne used in StockScreen class)
    double purchase;
    double newPurchaseTotal;
    double oldPurchaseTotal;

    // constructor 
    public InvestGame() throws IOException {
        // initialize UserInfo
        this.userInfo = new UserInfo(this.users);
        // initialize stockData
        this.stockData = new StockData();
    }

    // method that adds user provided their username, password, and balance
    public void addUser(String username, String password, double balance) throws IOException {
        // create a new user
        User user = new User(username, password, balance);
        // add new user to arraylist
        this.users.add(user);
        // write the new user to the text file
        this.userInfo.writeToFile(this.users);
    }

    // method to check if a username exists or not that takes in a username 
    public boolean checkUsername(String username) {

        // boolean to store whether username exists or not 
        boolean existingUsername = false;

        // iterate through the users arraylist, checking if the username exists or not 
        for (int i = 0; i < this.users.size(); i++) {
            // if the username exists, set boolean to true and break out of for loop
            if (this.users.get(i).getUsername().equals(username)) {
                existingUsername = true;
                break;
            }
        }

        // return the boolean
        return existingUsername;

    }

    // method to check if accound details are correct that takes in a username and password 
    public User checkUser(String username, String password) {
        // boolean stores whether user exists or not
        boolean validUser = false;
        User user = null;

        // iterate through users arraylist to check if username and password match any of the existing users
        for (int i = 0; i < this.users.size(); i++) {
            // if username exists,
            if (this.users.get(i).getUsername().equals(username)) {
                // and if password exists (isn't null),
                if (this.users.get(i).getPassword(password) != null) {
                    // then user exists and user equals found user
                    validUser = true;
                    user = this.users.get(i);
                    // break out of for loop
                    break;
                }
            }
        }

        // if user exists, return the user, otherwise return null
        if (validUser) {
            return user;
        } else {
            return null;
        }

    }

    // method to get history of stock and takes in ticker symbol
    public List getHistory(String ticker) throws IOException {
        // return stock history from stockData
        return this.stockData.stockHistory(ticker);
    }

    // method to get asking price for stock and takes in ticker symbol
    public String getStockAsk(String ticker) throws IOException {
        // return stock asking price from stockData
        return this.stockData.stockAskPrice(ticker);
    }

    // method to get bid price of stock and takes in ticker symbol
    public String getStockBid(String ticker) throws IOException {
        // return stock bidding price from stockData
        return this.stockData.stockBidPrice(ticker);
    }

    // method to get day high price of stock and takes in ticker symbol
    public String getStockDayHigh(String ticker) throws IOException {
        // return stock's day high price from stockData
        return this.stockData.stockDayHigh(ticker);
    }

    // method to get day low price of stock and takes in ticker symbol
    public String getStockDayLow(String ticker) throws IOException {
        // return stock's day low price from stockData
        return this.stockData.stockDayLow(ticker);
    }

    // method to get volume of stock and takes in ticker symbol
    public long getStockVolume(String ticker) throws IOException {
        // return volume of stock from stockData
        return this.stockData.stockVolume(ticker);
    }

    // method to buy stock that takes in a user, a ticker symbol, and quantity of stocks user would like to buy
    public boolean buyStock(User currentUser, String ticker, int quantity) throws IOException {
        // buy stock if user can buy stock (sufficient funds)
        if (this.stockData.buyStock(ticker, currentUser, quantity)) {
            // update text file
            this.userInfo.writeToFile(this.users);
            return true;
            // if user cannot buy stocks, return false
        } else {
            return false;
        }
    }

    // method to sell stocks that takes in a user, ticker symbol, and quantity of stocks user would like to buy
    public int sellStock(User currentUser, String ticker, int quantity, String time) throws IOException {
        // store quantity of stocks user has now 
        int newQuantity = this.stockData.sellStock(ticker, currentUser, quantity, time);
        
        // check if user still has stocks left,
        if (newQuantity != 0) {
            // get the purchase total
            this.purchase = currentUser.stocks.get(this.stockData.counter).getPurchaseTotal();
        // otherwise, set purchase to 0
        } else {
            this.purchase = 0;
        }
        // update text file
        this.userInfo.writeToFile(this.users);

        // set the variables of the old purchase total and the new purchase total
        this.newPurchaseTotal = this.stockData.newPurchaseTotal;
        this.oldPurchaseTotal = this.stockData.oldPurchaseTotal;

        // return the new quantity of stocks 
        return newQuantity;
    }

}
