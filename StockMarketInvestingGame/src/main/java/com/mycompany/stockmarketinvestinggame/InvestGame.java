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

    private int accountNumber;

    // constructor 
    public InvestGame() throws IOException {
        // create new UserInfo
        this.userInfo = new UserInfo(users);
        this.stockData = new StockData(users, accountNumber);
    }

    // method that adds user provided their username, password, and balance
    public void addUser(String username, String password, String balance) throws IOException {
        // create a new user
        User user = new User(username, password, balance);
        // add new user to arraylist
        this.users.add(user);
        // write the new user to the text file
        this.userInfo.writeToFile(this.users.get(this.users.size() - 1).getUsername(), this.users.get(this.users.size() - 1).getPassword(password), this.users.get(this.users.size() - 1).getBalance());
    }

    public int checkUsername(String username) {
        boolean validUsername = false;

        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(username)) {
                validUsername = true;
                this.accountNumber = i;
                break;
            }
        }

        if (validUsername) {
            return this.accountNumber;
        } else {
            return -3;
        }

    }

    // method to check if accound details are correct
    public boolean checkUser(String username, String password) {
        // keep track of whether user is valid or not 
        boolean validUser = false;

        // iterate through users arraylist 
        for (int i = 0; i < this.users.size(); i++) {
            if (checkUsername(username) == this.accountNumber) {
                if (this.users.get(i).getPassword(password).equals(password)) {
                    validUser = true;
                    break;
                }
            }

        }

        return validUser;

    }
}
