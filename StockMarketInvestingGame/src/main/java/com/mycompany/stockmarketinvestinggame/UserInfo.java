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

    // instance variables
    private String username;
    private String password;
    private String balance;
    private String accountNumber;

    // constructor
    public UserInfo(ArrayList<User> users) throws IOException {
        // create a filewriter object
        this.file = new File("User_Info.txt");
        this.writer = new FileWriter(this.file, true);
        this.bufferedWriter = new BufferedWriter(this.writer);
        this.br = new BufferedReader(new FileReader(this.file));

        // if the file exists and has values in it, go ahead and read it
        if (this.file.exists() && this.file.length() != 0) {
            while (this.br != null) {
                // while the buffered reader is still reading in values,   
                this.accountNumber = this.br.readLine();
                this.username = this.br.readLine();
                this.password = this.br.readLine();
                this.balance = this.br.readLine();
                User user = new User(this.username, this.password, this.balance, Integer.parseInt(this.accountNumber));
                users.add(user);
            }
        }

    }

    // method that writes user data to the text file
    public void writeToFile(ArrayList<User> users) throws IOException {

        String userData = "";

        for (int i = 0; i < users.size(); i++) {
            userData += users.get(i).getAccountNumber() + "\n";
            userData += users.get(i).getUsername() + "\n";
            userData += users.get(i).getPass() + "\n";
            userData += users.get(i).getBalance();
        }

        this.writer = new FileWriter(this.file, false);
        this.bufferedWriter.write(userData);
        this.bufferedWriter.flush();

    }
    
}
