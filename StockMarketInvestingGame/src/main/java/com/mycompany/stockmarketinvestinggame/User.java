/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

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
    
    public String getPass() {
        return this.password;
    }
    
    /**
     * sets a new password for user
     * @param newPassword
     * @return new password of user
     */
    public String setPassword(String newPassword) {
        return this.password = newPassword;
    }
    
    /**
     * get balance of user
     * @return balance
     */
    public double getBalance() {
        return this.balance;
    }
    
    /**
     * decreases the balance
     * @param decreaseAmount value to decrease balance by
     */
    public void decreaseBalance(double decreaseAmount) {
        this.balance -= decreaseAmount;
    }
    
    public void increaseBalance(double increaseAmount) {
        this.balance += increaseAmount;
    }
    
    /**
     * sets the account number
     * @param accountNumber 
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    /**
     * returns the account number of the user
     * @return account number of user
     */
    public int getAccountNumber() {
        return this.accountNumber;
    }
    
}