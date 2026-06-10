/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;

import static com.mycompany.mychatapp.Message.deleteByHash;
import static com.mycompany.mychatapp.Message.displayFullReport;
import static com.mycompany.mychatapp.Message.displayLongestMessage;
import static com.mycompany.mychatapp.Message.displayStoredMessages;
import static com.mycompany.mychatapp.Message.searchByMessageID;
import static com.mycompany.mychatapp.Message.searchByRecipient;
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
        System.out.println("Enter a username (must contain an '_', and must be at least 5 characters)");
        String username = input.nextLine();

        //Prompting user to enter his or her password
        System.out.println("Enter your password (must be at least 8 characters, must have a capital letter, a special character, and a number)");
        String password = input.nextLine();

        //Prompting user to enter his or her cellphone number
        System.out.println("Enter cellphone number, (must contain +27, and must have 10 characters)");
        String phoneNumber = input.nextLine();

        /*Response when the registerUser has been called,
          and store the message it returns
        */
        String response = login.registerUser(firstname, lastname, username, password, phoneNumber);

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
        System.out.println(loginMessage);

        if (!loggedIn) {
            return;
        }

        System.out.println("Welcome to QuickChat");
        boolean running = true;

        while (running) {
            System.out.println("===Messages Menu ===");
            System.out.println("Choose which option you would like to go with");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");
            System.out.println("Please enter your preferred choice");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("How many messages do you want to send");
                    int numMessages = input.nextInt();
                    input.nextLine();

                    for (int i = 0; i < numMessages; i++) {
                        int messageNumber = i + 1;
                        System.out.println("=== Message " + messageNumber + " ===");
                        System.out.println("Enter recipient's number");
                        String recipient = input.nextLine();

                        System.out.println("Enter your message");
                        String messageTextInput = input.nextLine();

                        Message currentMessage = new Message(
                                new Message("", 0, "", "").generateMessageID(),
                                messageNumber,
                                recipient,
                                messageTextInput
                        );

                        System.out.println(currentMessage.checkRecipient());

                        if (messageTextInput.length() > 250) {
                            System.out.println("You have exceeded your character limit of 250 characters");
                            continue;
                        }

                        System.out.println("Message ready to send");
                        String result = currentMessage.sentMessage();
                        System.out.println(result);
                        System.out.println(currentMessage.printMessages());
                    }
                    break;

                case 2:
                    System.out.println("Coming Soon");
                    break;

                case 3:
                    System.out.println("Exiting QuickChat...");
                    running = false;
                    break;
          //Switch Case for stored messages, allowing the user to choose from the Stored Messages menu.
                case 4:
                    System.out.println("====Sent Messages Menu====");
                    System.out.println("4) Stored Messages");
                    System.out.println("a) Display all stored messages");
                    System.out.println("b) Display longest message");
                    System.out.println("c) Search by messageID");
                    System.out.println("d) Search by recipient");
                    System.out.println("e) Delete by message Hash");
                    System.out.println("f) Display full report");
                   

                String rawChoice = input.hasNextLine() ? input.nextLine().trim().toLowerCase() : "";
                char subChoice = rawChoice.isEmpty() ? ' ' : rawChoice.charAt(0);
                
                
                switch (subChoice) {
                    case 'a':
                        System.out.println("a) Display all stored messages");
                        Message.displayStoredMessages();
                        
                        break;
                    case 'b':
                        System.out.println("b) Display longest message");
                        Message.displayLongestMessage();
                        break;
                    case 'c':
                        System.out.println("c) Search by messageID");
                        String id = input.nextLine();
                        Message.searchByMessageID(id);
                        break;
                    case 'd':
                        System.out.println("d) Search by recipient");
                        String recipient = input.nextLine();
                        Message.searchByRecipient(recipient);
                        break;
                    case 'e':
                        System.out.println("e) Delete by message Hash");
                        String Hash = input.nextLine();
                        Message.deleteByHash(Hash);
                        break;
                    case 'f':
                        System.out.println("f) Display full report");
                        Message.displayFullReport();
                        break;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                       
                } 

                
                    
            }
        }

        input.close();
    }
}
