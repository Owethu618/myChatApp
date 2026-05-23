/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;

/**
 *
 * @author oweth
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.json.JSONObject;
import org.json.JSONArray;
public class Message {
    
    //Decalaring variables that we are going to use.
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String MessageText;
    
    
    
    //Checking if messageID contains 10 characters using if statements
    
    private boolean checkMessageID(String messageID){
        return messageID.length() <=10;
    }
    
    //Generating the MessageId
    private String generateMessageID(){
        
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        
        //First digit must not be ( has to be 0-9)
        sb.append(random.nextInt(9) + 1);
        
        // Last 9 digits can be 0-9
        for(int i = 0; i < 9; i++){
            sb.append(random.nextInt(10));
        }
        
        return sb.toString();
    }
    
    //Recipient cell validation
    
    private boolean recipient(String recipient){
        return recipient.contains("+27") && recipient.length() >=10;
    }
    
    //Message Text validation
    String MessageTextInput = "Hi , nice to meet you";
    int length = 250;
     
    private static boolean lengthCheck(String MessageTextInput, int length){
        return MessageTextInput.length()== length;
    }
    
    
    //Generating a message hash
    String MessageIdPart = messageID.substring(0,2);
    String []words = MessageText.split(" ");
    String firstWord = words[0];
    String lastWord = words[words.length -1];
    public String CreateMessageHash(String MessageIdPart ,int numMessages, String firstWord , String lastWord) {
    String CreateMessageHash = MessageIdPart + ":" + numMessages + firstWord + lastWord;
    
    return CreateMessageHash.toUpperCase();
}
    
   private int TotalMessagesSent = 0;
   
   public int returnTotalMessagesSent(){
       return TotalMessagesSent;
   }
    
   public String ValidateMessages( String MessageText, String recipient, int numMessages){
        if(!lengthCheck(MessageTextInput, 250)){
            return "Must not exceed the length of 250 characters";
        }
        
        if(!recipient(recipient)){
            return "Recipient number incorrectly entered must contain the international code and must be at least 10 characters long";
        }
        
        if(!numMessages(TotalMessagesSent)){
            return "Total messages don't match the number of messages";
        }
        return "Validations have been passed";
       
    }
  
   public String SentMessages(boolean SendMessages, boolean DisregardMessage, boolean StoreMessage ){
       if(SendMessages){
           return "Message successfully sent";
       }
       
       if(DisregardMessage){
           return "Press 0 to delete the message";
       }
       
       if(StoreMessage){
           return "Message successfully stored";
       }
       
       return "No action has been taken";
   }

    private boolean numMessages(int TotalMessagesSent) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void storeMessage(){
        JSONObject obj = new JSONObject();
        
        //variables to store on JSON File
        obj.put("messageID", this.messageID);
        obj.put("recipient", this.recipient);
        obj.put("message", this.MessageTextInput);
        
        //Calling file writer to store variables on the file for json
        try (FileWriter fw = new FileWriter("message.json", true)){
            fw.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
    
}
   

