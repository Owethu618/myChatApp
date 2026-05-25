/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;

import java.util.Scanner;

/**
 *
 * @author oweth
 */
public class MainApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();

        System.out.println("Enter your first name");
        String firstname = input.nextLine();

        System.out.println("Enter your last name");
        String lastname = input.nextLine();

        System.out.println("Enter a username (must contain an '_', and must be at least 5 characters)");
        String username = input.nextLine();

        System.out.println("Enter your password (must be at least 8 characters, must have a capital letter, a special character, and a number)");
        String password = input.nextLine();

        System.out.println("Enter cellphone number, (must contain +27, and must have 10 characters)");
        String phoneNumber = input.nextLine();

        String response = login.registerUser(firstname, lastname, username, password, phoneNumber);
        System.out.println(response);

        System.out.println("\n===User Login===");
        System.out.println("Enter username");
        String loginUsername = input.nextLine();

        System.out.println("Enter password");
        String loginPassword = input.nextLine();

        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(loggedIn));

        if (!loggedIn) {
            return;
        }

        System.out.println("Welcome to QuickChat");
        boolean running = true;
        Message message = null;

        while (running) {
            System.out.println("===Messages Menu ===");
            System.out.println("Choose which option you would like to go with");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
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
                        message = currentMessage;
                    }
                    break;

                case 2:
                    if (message == null) {
                        System.out.println("No messages have been sent yet.");
                    } else {
                        System.out.println("Coming Soon");
                        System.out.println("Total messages sent: " + message.returnTotalMessagesSent());
                    }
                    break;

                case 3:
                    System.out.println("Exiting QuickChat...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        input.close();
    }
}
