/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mankaran
 */
public class InvestGame {

    // arraylist to store users
    ArrayList<User> users = new ArrayList<>();
    // create instance of user info
    UserInfo userInfo;
    // create instance of stock data
    StockData stockData;
    // instance of user
    User currentUser;
    
    // instance variable
    public double purchaseTotal = 0; 

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
            // if user information exists,
            if (this.users.get(i).getUsername().equals(username) && this.users.get(i).getPassword(password).equals(password)) {
                // then user exists and we can set the user equal to the found user
                validUser = true;
                user = this.users.get(i);
                // break out of for loop
                break;
            }
        }

        // if user exists, return the user, otherwise return null
        if (validUser) {
            return user;
        } else {
            return null;
        }

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
        // store new purchase total
        this.purchaseTotal = currentUser.stocks.get(this.stockData.counter).getPurchaseTotal();
        // update text file
        this.userInfo.writeToFile(this.users);
        
        // return the new quantity of stocks 
        return newQuantity;
    }
    
}
