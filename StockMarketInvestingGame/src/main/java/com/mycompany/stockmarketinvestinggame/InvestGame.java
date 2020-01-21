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

    User currentUser;

    private int accountNumber;

    // constructor 
    public InvestGame() throws IOException {
        // create new UserInfo
        this.userInfo = new UserInfo(this.users);
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

    public boolean checkUsername(String username) {
        boolean existingUsername = false;

        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(username)) {
                existingUsername = true;
                break;
            }
        }

        return existingUsername;

    }

    public boolean checkPassword(String password) {
        boolean existingPassword = false;

        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getPassword(password).equals(password)) {
                existingPassword = true;
                break;
            }
        }

        return existingPassword;
    }

    // method to check if accound details are correct
    public User checkUser(String username, String password) {
        // tells us whether or not user exists
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

    // method to buy stock
    public boolean buyStock(User currentUser, String ticker, double quantity) throws IOException {
        // buy the stock
        if (this.stockData.buyStock(ticker, currentUser, quantity)) {
            this.userInfo.writeToFile(this.users);
            return true;
        } else {
            return false;
        }
    }

    // returns the current user
    public User getCurrentUser() {
        return this.currentUser;
    }

}
