/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Mankaran
 */
public class User {
    
    // instance variables
    private String username;
    private String password;
    private double balance;
    private int accountNumber;
    DecimalFormat df = new DecimalFormat("#.##");
    ArrayList<Stocks> stocks = new ArrayList<>();
    
    // constructor 
    public User(String username, String password, double balance) {
        // set user values equal to instance variables
        this.username = username;
        this.password = password;
        this.balance = balance;
    }
    
    /**
     * get username of user
     * @return username
     */
    public String getUsername() {
        return this.username; 
    }
    
    /**
     * check if password entered is correct, and only then return it
     * @param password 
     * @return password
     */
    public String getPassword(String password) {
        if (this.password.equals(password)) {
            return this.password;
        } else {
            return "";
        }
    }
    
    /**
     * only used for writing to textfile
     * @return password
     */
    public String getPass() {
        return this.password;
    }
    
    /**
     * get balance of user
     * @return balance
     */
    public double getBalance() {
        return Double.parseDouble(this.df.format(this.balance));
    }
    
    /**
     * decreases the balance and rounds to two decimal places
     * @param decreaseAmount value to decrease balance by
     */
    public void decreaseBalance(double decreaseAmount) {
        this.df.format(this.balance -= decreaseAmount);
    }
    
    /**
     * increases the balance and rounds to two decimal places
     * @param increaseAmount value to increase balance by
     */
    public void increaseBalance(double increaseAmount) {
        this.df.format(this.balance += increaseAmount);
    }
    
    /**
     * returns the account number of the user
     * @return account number of user
     */
    public int getAccountNumber() {
        return this.accountNumber;
    }
    
    /**
     * sets the account number
     * @param accountNumber 
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    
}