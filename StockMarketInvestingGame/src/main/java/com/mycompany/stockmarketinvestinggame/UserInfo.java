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
import java.io.PrintWriter;
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
    private int accountNumber;
    public String date;
    private String time;
    private String ticker;
    private double buyPrice;
    private int quantity;
    private double purchaseTotal;

    // constructor
    public UserInfo(ArrayList<User> users) throws IOException {
        // create a filewriter object
        this.file = new File("User_Info.txt");
        this.writer = new FileWriter(this.file, true);
        this.br = new BufferedReader(new FileReader(this.file));

        // if the file exists and has values in it, go ahead and read it
        if (this.file.exists() && this.file.length() != 0) {
            // while the buffered reader is still reading in values,
            while (this.br != null) {

                // read in the username, password, and balance of each user
                this.accountNumber = Integer.parseInt(this.br.readLine());
                this.username = this.br.readLine();
                this.password = this.br.readLine();
                this.balance = this.br.readLine();
                this.br.readLine();

                // create a new user
                User user = new User(this.username, this.password, Double.parseDouble(this.balance));

                // add the user to the arraylist
                users.add(user);
                users.get(users.size() - 1).setAccountNumber(this.accountNumber);

                if (this.br.readLine().equals("None")) {

                    // skip a line
                    this.br.readLine();

                } else {

                    // empty string
                    String end = "";
                    int counter = 0;

                    // while end does not store the word end, keep reading in stocks
                    while (!end.equals("End")) {
                        if (counter == 0) {
                            // read in the date-time, ticker, buy price, and quantity
                            this.date = this.br.readLine();
                            this.time = this.br.readLine();
                            this.ticker = this.br.readLine();
                            this.buyPrice = Double.parseDouble(this.br.readLine());
                            this.quantity = Integer.parseInt(this.br.readLine());
                            this.purchaseTotal = Double.parseDouble(this.br.readLine());
                            users.get(users.size() - 1).stocks.add(new Stocks(this.date, this.time, this.ticker, this.buyPrice, this.quantity, this.purchaseTotal));
                            this.br.readLine();
                            end = this.br.readLine();
                        } else {
                            // read in the ticker, buy price, and quantity
                            this.date = end;
                            this.time = this.br.readLine();
                            this.ticker = this.br.readLine();
                            this.buyPrice = Double.parseDouble(this.br.readLine());
                            this.quantity = Integer.parseInt(this.br.readLine());
                            this.purchaseTotal = Double.parseDouble(this.br.readLine());
                            users.get(users.size() - 1).stocks.add(new Stocks(this.date, this.time, this.ticker, this.buyPrice, this.quantity, this.purchaseTotal));
                            this.br.readLine();
                            end = this.br.readLine();
                        }
                        counter++;
                    }

                    
                    // skip one line
                    this.br.readLine();
                    
                }

                // if the buffered reader reads in no more users,
                if (this.br.readLine() == null) {
                    this.br.close();
                    // break out of the loop
                    break;
                }
            }
        }

    }

    // method that writes user data to the text file
    public void writeToFile(ArrayList<User> users) throws IOException {

        PrintWriter pw = new PrintWriter(this.file);
        pw.close();

        for (int i = 0; i < users.size(); i++) {
            // write user data to file
            if (this.file.length() == 0) {
                this.accountNumber = 0;
                this.writer.write(Integer.toString(this.accountNumber) + "\n");
                this.writer.write(users.get(i).getUsername() + "\n");
                this.writer.write(users.get(i).getPass() + "\n");
                this.writer.write(String.valueOf(users.get(i).getBalance()) + "\n");
                this.writer.write("Owned Stocks:" + "\n");
                if (users.get(i).stocks.isEmpty()) {
                    this.writer.write("None" + "\n");
                } else {
                    for (int k = 0; k < users.get(i).stocks.size(); k++) {
                        this.writer.write("\n" + users.get(i).stocks.get(k).getDate() + "\n");
                        this.writer.write(users.get(i).stocks.get(k).getTime() + "\n");
                        this.writer.write(users.get(i).stocks.get(k).getTicker() + "\n");
                        this.writer.write(users.get(i).stocks.get(k).getBuyPrice() + "\n");
                        this.writer.write(users.get(i).stocks.get(k).getQuantity() + "\n");
                        this.writer.write(users.get(i).stocks.get(k).getPurchaseTotal() + "\n");
                    }
                    this.writer.write("\nEnd\n");
                }
                this.writer.flush();
            } else {
                this.accountNumber++;
                this.writer.write("\n" + "\n" + Integer.toString(this.accountNumber) + "\n");
                this.writer.write(users.get(i).getUsername() + "\n");
                this.writer.write(users.get(i).getPass() + "\n");
                this.writer.write(String.valueOf(users.get(i).getBalance()) + "\n");
                this.writer.write("Owned Stocks: \n");
                if (users.get(i).stocks.isEmpty()) {
                    this.writer.write("None" + "\n");
                } else {
                    for (int j = 0; j < users.get(i).stocks.size(); j++) {
                        this.writer.write("\n" + users.get(i).stocks.get(j).getDate() + "\n");
                        this.writer.write(users.get(i).stocks.get(j).getTime() + "\n");
                        this.writer.write(users.get(i).stocks.get(j).getTicker() + "\n");
                        this.writer.write(users.get(i).stocks.get(j).getBuyPrice() + "\n");
                        this.writer.write(users.get(i).stocks.get(j).getQuantity() + "\n");
                        this.writer.write(users.get(i).stocks.get(j).getPurchaseTotal() + "\n");
                    }
                    this.writer.write("\nEnd\n");
                }
                this.writer.flush();
            }
        }

    }

}
