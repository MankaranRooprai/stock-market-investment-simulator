/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mankaran
 */
public class UserInfo {

    // filewriter object
    private FileWriter writer = null;
    private BufferedWriter bufferedWriter;
    private BufferedReader br = null;
    private File file;
    ArrayList<User> users;

    // instance variables
    private String username;
    private String password;
    private String balance;
    private int accountNumber;
    private String ticker;
    private double buyPrice;
    private int quantity;

    // constructor
    public UserInfo(ArrayList<User> users) throws IOException {
        // create a filewriter object
        this.file = new File("User_Info.txt");
        this.writer = new FileWriter(this.file, true);
        this.bufferedWriter = new BufferedWriter(this.writer);
        this.br = new BufferedReader(new FileReader(this.file));
        this.users = users;

        // if the file exists and has values in it, go ahead and read it
        if (this.file.exists() && this.file.length() != 0) {

            long lines = this.br.lines().count();
            this.br = new BufferedReader(new FileReader(this.file));
            for (int i = 0; i < lines / 7; i++) {
                // while the buffered reader is still reading in values,   
                this.accountNumber = Integer.parseInt(this.br.readLine());
                this.username = this.br.readLine();
                this.password = this.br.readLine();
                this.balance = this.br.readLine();
                this.ticker = this.br.readLine();
                this.buyPrice = Double.parseDouble(this.br.readLine());
                this.quantity = Integer.parseInt(this.br.readLine());
                this.br.readLine();
//                System.out.println(this.accountNumber);
//                System.out.println(this.username);
//                System.out.println(this.password);
//                System.out.println(this.balance);

            }
            users.add(new User(this.username, this.password, this.balance, this.accountNumber));
            users.get(users.size() - 1).stocks.add(new Stocks(this.ticker, this.buyPrice, this.quantity));
        }

    }
    
    // method that writes user data to the text file
    public void writeToFile(ArrayList<User> users) throws IOException {

        String userData;

        if (this.file.length() == 0) {
            userData = "";
        } else {
            userData = "\n \n";
        }

        for (int i = 0; i < users.size(); i++) {
            userData += users.get(i).getAccountNumber() + "\n";
            userData += users.get(i).getUsername() + "\n";
            userData += users.get(i).getPass() + "\n";
            userData += users.get(i).getBalance();
            if (!users.get(i).stocks.isEmpty()) {
                userData += users.get(i).stocks.get(i).getTicker();
                userData += users.get(i).stocks.get(i).getBuyPrice();
                userData += users.get(i).stocks.get(i).getQuantity();
            }
        }

        this.writer = new FileWriter(this.file, false);
        this.bufferedWriter.write(userData);
        this.bufferedWriter.flush();

    }

    public ArrayList userArrayList() {
        return this.users;
    }
    
}
