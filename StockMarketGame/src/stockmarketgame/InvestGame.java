/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmarketgame;

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

    // constructor 
    public InvestGame() throws IOException {
        // create new UserInfo
        this.userInfo = new UserInfo(users);
    }

    // method that adds user provided their username, password, and balance
    public void addUser(String username, String password, String balance) throws IOException {
        // create a new user
        User user = new User(username, password, balance);
        // add new user to arraylist
        this.users.add(user);
        // write the new user to the text file
        this.userInfo.writeToFile(this.users.get(this.users.size() - 1).getUsername(), this.users.get(this.users.size() - 1).getPassword(), this.users.get(this.users.size() - 1).getBalance());
    }

    // method to check if accound details are correct
    public boolean checkUser(String username, String password) {
        
        int validUser = 0;
        
        // iterate through the arraylist of users
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(username) && this.users.get(i).getPassword().equals(password) == true) {
                validUser = 1;
                break;
            }
        }
        
        if (validUser == 1) {
            return true;
        } else {
            return false;
        }
        
    }
    
//    public static void main(String[] args) throws IOException {
//        
//        InvestGame investGame = new InvestGame();
//        
//        System.out.println(users.size());
//        
//        for (int i = 0; i < users.size(); i++) {
//            System.out.println(users.get(i).getUsername());
//            System.out.println(users.get(i).getPassword());
//            System.out.println(users.get(i).getBalance());
//            System.out.println("\n");
//        }
//    }

}
