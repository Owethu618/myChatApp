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
         System.out.println(loginMessage);
    
    
    if(loggedIn){
    
        System.out.println("Welcome to QuickChat");
    //Setting a variable to know when the loop should begin and end
    boolean running = true;
   
    //Creating the while loop
    while (running)
    {
    System.out.println("===Messages Menu ===");
    System.out.println("Choose which option you would like to go with");
    
    System.out.println("1) Send Messages");
    System.out.println("2) Show recently sent messages");
    System.out.println("3) Quit");
    
    // prompting the user to enter his or her choice
    System.out.println("Please enter your preferred choice");
    
    // collecting the user's input
    int choice = input.nextInt();
    input.nextLine();
    
    switch(choice)
    {
        case 1:
           System.out.println("How many messages do you want to send");
           int numMessages = input.nextInt();
           for(int i = 0; i < numMessages; i++){
        int messageNumber = i + 1;
        System.out.println("=== Message" + numMessages +"===");
}
       System.out.println("Enter recepient's number");
       String recipient = input.nextLine();
   
    
       System.out.println("Enter your message");
       String messageTextInput = input.nextLine();
       
       Message message = new Message( messageID, messageNumber, recipient, messageText);
       
       //Displaying the recipient validation result
       System.out.println(message.checkRecipient());
       
       //Checking if the user has exceeded 250 characters
       if(messageTextInput.length() > 250){
          int over = messageTextInput.length() - 250;
          System.out.println("You have exceeded your character limit of 250 characters");
}else{
    System.out.println("Message ready to send");
}
       
       //Continuing only if inputs are valid
       if(message.checkRecipient().equals("Recipient cellphone number successfully captured") && message.MessageTextInput.equals("Message ready to send")){
           //Asking the user what to do with the message
           String result = message.sentMessage();
           
           //Printing the message details
           System.out.println(result);
           
           //Printing out the details of the messages of the user
           System.out.println(message.printMessages());
           break;
       }
        case 2:
             
        //Feature has not yet been created
         System.out.println("Coming Soon");
    
        //Display total of the messages sent
        System.out.println("Total messages sent: " + message.returnTotalMessagesSent());
        break;
    
        case 3: 
        
        //Exit message
        System.out.println("Exiting QuickChat...");
    
        //Stopping the while loop
        running = false;
      
    
    
        
    
}
        
         
    
    
    
    
    
    
    
    
    
}  
}
}

