/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

/**
 *
 * @author Mankaran
 */
public class User {
    
    // instance variables
    private String username;
    private String password;
    private String balance; 
    
    // constructor 
    public User(String username, String password, String balance) {
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
    public String getBalance() {
        return this.balance;
    }
    
    /**
     * sets new balance for user
     * @param newBalance
     * @return new balance
     */
    public String setBalance(String newBalance) {
        return this.balance += newBalance;
    }
    
}