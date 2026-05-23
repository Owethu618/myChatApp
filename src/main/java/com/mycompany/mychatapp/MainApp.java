/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;

import java.util.Scanner;
//import org.json.JSONObject;
//import org.json.JSONArray;

/**
 *
 * @author oweth
 */
public class MainApp {
    public static void main(String[] args) {
         //Adding a scanner that allows a user to enter information
         Scanner input = new Scanner(System.in);
         
         //Creating an object for the Login class so that we can be able to call our methods
         
         Login login = new Login();
         
         //---Registration Section---
         
         //Getting user's details
         //Prompting user to enter his or her First name
         System.out.println("Enter your first name");
         String firstname = input.nextLine();
         
         //Prompting user to enter his or her last name
         System.out.println("Enter your last name");
         String lastname = input.nextLine();
         
         //Prompting user to enter his or her username
         System.out.println("Enter a username (must contain an '_', and must be at least 5 characters) ");
         String username = input.nextLine();
         
         //Prompting user to enter his or her password
         System.out.println("Enter your password (must be at least 8 characters, must have a capital letter, a special character , and a number)");
         String password = input.nextLine();
         
         //Prompting user to enter his or her cellphone number
         System.out.println("Enter cellphone number, (must contain +27, and must have 10 characters)");
         String phoneNumber = input.nextLine();
         
         
         /*Response when the  registerUser has been called,
           and store the message it returns
         */
         String response = login.registerUser(firstname,lastname,username, password, phoneNumber);
         
         //Displaying the registration message
         System.out.println(response);
         
         
         
         //---Login---
         System.out.println("\n===User Login===");
         
         
         System.out.println("Enter username");
         String loginUsername = input.nextLine();
         
         System.out.println("Enter password");
         String loginPassword = input.nextLine();
         
         //Verifying username and password if it's entered correctly
         boolean loggedIn = login.loginUser(loginUsername, loginPassword);
         
         //Displaying the login message
         String loginMessage = login.returnLoginStatus(loggedIn);
         System.out.println("Login successful");
    }
}
