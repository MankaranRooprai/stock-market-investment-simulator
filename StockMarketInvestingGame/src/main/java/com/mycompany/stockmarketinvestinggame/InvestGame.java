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
    
    Stocks stocks;

    User currentUser;
    
    private int accountNumber = 0;

    // constructor 
    public InvestGame() throws IOException {
        // create new UserInfo
        this.userInfo = new UserInfo(this.users);
        this.stockData = new StockData(this.users, this.accountNumber);
    }

    // method that adds user provided their username, password, and balance
    public void addUser(String username, String password, String balance) throws IOException {
        
        if (this.users.isEmpty()) {
            this.accountNumber = 0;
        } else {
            this.accountNumber = this.users.get(this.users.size() - 1).getAccountNumber() + 1;
        }

        // create a new user
        User user = new User(username, password, balance, this.accountNumber);
        // add new user to arraylist
        this.users.add(user);
        
        this.userInfo.writeToFile(users);
        // write the new user to the text file
    }

    public boolean checkUsername(String username) {
        boolean validUsername = false;

        if (!this.users.isEmpty()) {
            for (int i = 0; i < this.users.size(); i++) {
                if (this.users.get(i).getUsername().equals(username)) {
                    validUsername = true;
                    currentUser = this.users.get(i);;
                    this.accountNumber = this.users.get(i).getAccountNumber();
                    break;
                }
            }
        } else {
            return false;
        }

        if (validUsername) {
            return true;
        } else {
            return false;
        }

    }

    // method to check if accound details are correct
    public User checkUser(String username, String password) {
        // keep track of whether user is valid or not 
        User user = null;
        boolean validUser = false;

        // iterate through users arraylist 
        for (int i = 0; i < this.users.size(); i++) {
            if (checkUsername(username)) {
                if (this.users.get(i).getPassword(password).equals(password)) {
                    user = this.users.get(i);
                    currentUser = this.users.get(i);;
                    this.accountNumber = this.users.get(i).getAccountNumber();
                    validUser = true;
                    break;
                }
            }

        }

        if (validUser) {
            return user;
        } else {
            return null;
        }

    }

    public void buyStock(String ticker, int quantity, String password) throws IOException {
        this.stocks = this.stockData.buyStock(ticker, quantity, password);
        this.currentUser.stocks.add(stocks);
    }

}
