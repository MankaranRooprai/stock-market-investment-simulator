/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stockmarketinvestinggame;

import java.io.BufferedReader;
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
    private FileWriter writer;
    private BufferedReader br = null;
    private File file;

    // instance variables
    private String username;
    private String password;
    private String balance;
    private int userNumber;

    // constructor
    public UserInfo(ArrayList<User> users) throws IOException {
        // create a filewriter object
        this.file = new File("User_Info.txt");
        this.writer = new FileWriter(this.file, true);
        this.br = new BufferedReader(new FileReader(this.file));

        // if the file exists and has values in it, go ahead and read it
        if (this.file.exists() && this.file.length() != 0) {
            // while the buffered reader is still reading in values,   
            String line1;
            
            while ((line1 = this.br.readLine()) != null) {
                // read in the username, password, and balance of each user
                this.username = this.br.readLine();
                this.password = this.br.readLine();
                this.balance = this.br.readLine();
                // create the new user
                User user = new User(this.username, this.password, this.balance);
                // add the user to the arraylist
                users.add(user);
                // separate a line for the buffered reader to read in more users
                String x = System.lineSeparator();
                // if the buffered reader reads in no more users,
                if (this.br.readLine() == null) {
                    // break out of the loop
                    break;
                }
            }
        }

    }

    // method that writes user data to the text file
    public void writeToFile(String username, String password, String balance) throws IOException {
        // declare variables
        this.username = username;
        this.password = password;
        this.balance = balance;

        // write user data to file
        if (this.file.length() == 0) {
            this.userNumber = 0;
            this.writer.write(Integer.toString(userNumber));
            this.writer.write("\n" + username);
            this.writer.write("\n" + password);
            this.writer.write("\n" + balance);
            this.writer.flush();
        } else {
            this.userNumber++;
            this.writer.write("\n" + "\n" + Integer.toString(userNumber));
            this.writer.write("\n" + username);
            this.writer.write("\n" + password);
            this.writer.write("\n" + balance);
            this.writer.flush();
        }

    }

}
