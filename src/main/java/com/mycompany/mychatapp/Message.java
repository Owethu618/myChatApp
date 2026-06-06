/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONObject;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageStatus;
    private int totalMessagesSent;

    public String MessageTextInput = "Hi , nice to meet you";
    public String CreateMessageHash;
    public String sentMessages;
    public String printMessages;

    // Declaration of Arrays
    private static  List<String> sentMESSAGES = new ArrayList<>();
    private static List<String> disregardedMessages = new ArrayList<>();
    private static List<String> storedMessages = new ArrayList<>();
    private static List<String> messageHashes = new ArrayList<>();
    private static List<String> messageIDs = new ArrayList<>();
    private static final List<String> recipientList = new ArrayList<>();

    public Message(String messageID, int messageNumber, String recipient, String MessageText) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = MessageText;
        this.MessageTextInput = MessageText == null ? "Hi , nice to meet you" : MessageText;
        this.messageStatus = "Pending";
    }

    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10;
    }

    public String generateMessageID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        sb.append(random.nextInt(9) + 1);

        for (int i = 0; i < 9; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public String checkRecipient() {
        boolean valid = checkRecipient(this.recipient);
        return valid
                ? "Recipient cellphone number is successfully captured"
                : "Recipient cell phone number is incorrectly formatted or does not contain international code";
    }

    public boolean checkRecipient(String recipient) {
        return recipient != null && recipient.startsWith("+27") && recipient.length() >= 12;
    }

    private static boolean lengthCheck(String text, int length) {
        return text != null && text.length() <= length;
    }

    public String createMessageHash() {
        String messageIdPart = (messageID == null || messageID.length() < 2) ? "00" : messageID.substring(0, 2);
        String text = (messageText == null || messageText.isBlank()) ? MessageTextInput : messageText;
        String[] words = text.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        String messageHash = messageIdPart + ":" + messageNumber + ":" + firstWord + lastWord;
        this.CreateMessageHash = messageHash.toUpperCase();
        return this.CreateMessageHash;
    }

    public String createMessageHash(String messageIdPart, int sequence, String firstWord, String lastWord) {
        String hash = messageIdPart + ":" + sequence + ":" + firstWord + lastWord;
        this.CreateMessageHash = hash.toUpperCase();
        return this.CreateMessageHash;
    }

    public String CreateMessageHash(String messageIdPart, int sequence, String firstWord, String lastWord) {
        return createMessageHash(messageIdPart, sequence, firstWord, lastWord);
    }

    public int returnTotalMessagesSent() {
        return totalMessagesSent;
    }

    public String validateMessages(String messageText, String recipient, int numMessages) {
        if (!lengthCheck(messageText, 250)) {
            return "Must not exceed the length of 250 characters";
        }

        if (!numMessages(numMessages)) {
            return "Total messages don't match the number of messages";
        }

        if (!checkRecipient(recipient)) {
            return "Recipient cell phone number is incorrectly formatted or does not contain international code";
        }

        return "Validations have been passed";
    }

    public String sentMessage() {
        Scanner input = new Scanner(System.in);

        System.out.println("What would you like to do with this message?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard message");
        System.out.println("3) Store Message to send later");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                markAsSent();
                sentMESSAGES.add(messageStatus);
                messageIDs.add(messageID);
                messageHashes.add(CreateMessageHash);
                return "Message successfully sent";
            case 2:
                this.messageStatus = "Disregard";
                disregardedMessages.add(messageStatus);
                return "Press 0 to delete the message";
            case 3:
                this.messageStatus = "Store";
                storeMessage();
                return "Message stored for later";
            default:
                return "Invalid option entered";
        }
    }

    public String printMessages() {
<<<<<<< Updated upstream
        String currentHash = createMessageHash();
        this.printMessages = "messageID:" + messageID + "\nMessage Number:" + messageNumber + "\nRecipient:" + recipient + "\nMessage:" + MessageTextInput + "\nMessage Hash:" + currentHash + "\nStatus:" + (messageStatus == null ? "Pending" : messageStatus);
=======
        this.printMessages = "Message ID:" + messageID + "\nMessage Hash:" + createMessageHash() + "\nRecipient:" + recipient + "\nMessage:" + MessageTextInput;
>>>>>>> Stashed changes
        return this.printMessages;
    }

    public boolean numMessages(int totalMessagesSent) {
        return totalMessagesSent == this.totalMessagesSent;
    }

    public void storeMessage() {
        JSONObject obj = new JSONObject();
        obj.put("messageID", this.messageID);
        obj.put("recipient", this.recipient);
        obj.put("message", this.MessageTextInput);

        try (FileWriter fw = new FileWriter("message.json", true)) {
            fw.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //====Part 3: Sub-Menu methods====
    //Method to display the stored messages
    public static void displayStoredMessages(){
        if (storedMessages.isEmpty()){
            System.out.println("No stored Messages detected");
            return;
        }
        
        for (int i = 0; i < storedMessages.size(); i++){
            System.out.println((i+1) + " " + storedMessages.get(i));
        }
    }
    
    //Method to display the longest message for the sub-menu
      public static String displayLongestMessage(){
          if (storedMessages.isEmpty()){
             System.out.println("No stored messages to look for");
          }
          String longest = "";
          for (String text : storedMessages){
              if (text.length() > longest.length()){
                  longest = text;
              }
          }
          return longest;
      }
      
      //Method to search by messageID
       public static String searchByMessageID(String messageID){
           // Searching through messageIDs array
            for (int i = 0; i < messageIDs.size(); i++){
                if (messageIDs.get(i).equals(messageID)) {
                    String text = "";
                    if (i < sentMESSAGES.size()) {
                        text = sentMESSAGES.get(i);
                    } else if (i < disregardedMessages.size()) {
                        text = disregardedMessages.get(i);
                    } else {
                        text = "Message not found";
                    }
                    return "Recipient:" + recipientList.get(i) + "\nMessage: " + text;
                }
            }

            return "Message not detected for ID" + messageID;
       }
       
       //Searching by recipient 
       public static String searchByRecipient(String recipient){
           StringBuilder results = new StringBuilder();
           boolean found = false;

           for (int i = 0; i < recipientList.size(); i++){
               if (recipientList.get(i).equals(recipient)) {
                   found = true;
                   String text = "";
                   if (i < sentMESSAGES.size()) {
                       text = sentMESSAGES.get(i);
                   } else if (i < disregardedMessages.size()) {
                       text = disregardedMessages.get(i);
                   } else {
                       text = "Message Text not available";
                   }
                   results.append("Recipient: ").append(recipient).append("\nMessage: ").append(text).append("\n\n");
               }
           }
           return found ? results.toString() : "No messages detected for recipient: " + recipient;
       }
       
       // A method that will allow a user to delete a message by using a messageHash
        public static String deleteByHash(String createMessageHash){
            for (int i = 0; i < messageHashes.size(); i++) {
                if (messageHashes.get(i).equalsIgnoreCase(createMessageHash)){
                    String deletedText = "";
                    if (i < sentMESSAGES.size()){
                        deletedText = sentMESSAGES.get(i);
                        sentMESSAGES.remove(i);
                    } else if (i < disregardedMessages.size()){
                        deletedText = disregardedMessages.get(i);
                        disregardedMessages.remove(i);
                    } else {
                        deletedText = "Message unknown";
                    }
                    messageHashes.remove(i);
                    messageIDs.remove(i);
                    recipientList.remove(i);
                    return "Message: " + deletedText + " successfully deleted";
                }
            }
            return "messageHash not found";
        }
        
        //Dispay a full report
        public static String displayFullReport(){
            StringBuilder report = new StringBuilder();
            report.append("====Full Message Report ====\n");
            for (int i = 0; i < sentMESSAGES.size(); i++){
                report.append("messageHash: ").append(messageHashes.get(i))
                      .append("\nRecipient: ").append(recipientList.get(i))
                      .append("\nMessage: ").append(sentMESSAGES.get(i))
                      .append("\n---------------------------------\n");
            }
            return report.toString();
        }
        
      
    
    // Populating the array for the message status of "Sent", which reprents the array of Sent Messages
    public void markAsSent() {
        this.messageStatus = "Sent";
        this.totalMessagesSent++;
        sentMESSAGES.add(printMessages());
    }

    // A method if a user wants to clear the array list of Sent Messages
    public static void clearSentMessages() {
        sentMESSAGES.clear();
    }
    
    // When user wants to view Sent Messages on their array list
    public static List<String> getSentMessages() {
        return new ArrayList<>(sentMESSAGES);
    }
    
    // Populating the array list of Disregarded Messages
    public void markAsDisregarded(){
        this.messageStatus = "Disregard";
        disregardedMessages.add(printMessages());
    }
    // When user wants to clear the array list of disregarded messages
    public static void clearDisregardedMessages(){
        disregardedMessages.clear();
    }
    
    // When the user wants to access the array list of disregarded messages
    public static List<String> getDisregardedMessages(){
        return new ArrayList<>(disregardedMessages);
    }
    
    //Populating messageID array list
      public void messageID(){
          messageIDs.add(this.messageID);
      }
      
      //When user wants to clear the array list of messageIDs
       public static void clearMessageIDs(){
           messageIDs.clear();
       }
       
       // When a user wants to access the messageIDs on the array list of messageIDs
        public static List<String> getMessageIDs(){
            return new ArrayList<>(messageIDs);
        }
        
        //Populating messageHashes array list
          public void messageHash(){
            this.CreateMessageHash = createMessageHash().toUpperCase();
            messageHashes.add(this.CreateMessageHash);
          }
          
         // When user wants to clear the array list of messageHashes
          public static void clearMessageHashes(){
              messageHashes.clear();
          }
          
          // When user wants to access the messageHashes on their array list
            public static List<String> getMessageHashes(){
                return new ArrayList<>(messageHashes);
            }
            
            public String StoredMessages(){
                Scanner input = new Scanner(System.in);

                System.out.println("a) Display all stored messages");
                System.out.println("b) Display longest message");
                System.out.println("c) Search by messageID");
                System.out.println("d) Search by recipient");
                System.out.println("e) Delete by message Hash");
                System.out.println("f) Display full report");

                String rawChoice = input.hasNextLine() ? input.nextLine().trim().toLowerCase() : "";
                char choice = rawChoice.isEmpty() ? ' ' : rawChoice.charAt(0);

                switch (choice) {
                    case 'a':
                        System.out.println("a) Display all stored messages");
                        displayStoredMessages();
                        return "Stored messages listed";
                    case 'b':
                        System.out.println("b) Display longest message");
                        return displayLongestMessage();
                    case 'c':
                        System.out.println("c) Search by messageID");
                        return searchByMessageID(this.messageID);
                    case 'd':
                        System.out.println("d) Search by recipient");
                        return searchByRecipient(this.recipient);
                    case 'e':
                        System.out.println("e) Delete by message Hash");
                        return deleteByHash(this.CreateMessageHash);
                    case 'f':
                        System.out.println("f) Display full report");
                        return displayFullReport();
                    default:
                        return "Invalid stored message option";
                }
            }
            
            
    
    
    
    
}
