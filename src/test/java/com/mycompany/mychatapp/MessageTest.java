/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mychatapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author oweth
 */
public class MessageTest {
    
    //Calling my messages methods
    
    
    @Test
    public void testMessageIDLength(){
        Message message = new Message("0:1:Hi:Thanks",3, "+27596946645", "Hello World");
        assertEquals(10, message.generateMessageID().length());
    }
    
    //Checking whether a valid recipient cell number passes
    @Test
    
    public void testValidRecipientCell(){
        String recipient = "+27796499327";
        assertEquals("+27796499327", recipient);
            
    }
    
    //Checking for invalid recipient cell 
    @Test
    public void testInvalidRecipient(){
        Message message = new Message("0:1:Hi:Thanks",1,"0796599327", "Hi, good to here from you");
        String recipient = "0796599327";
        assertFalse(message.checkRecipient(recipient));
    }
    //Test whether the method for messageText works
    @Test
    public void testMessageLengthValid(){
        Message message = new Message("0:1:Hi:Thanks",1,"+27796599327", "Hi, good to here feom you");
        //Defining the length
        int length = 250;
        assertEquals("Message ready to send",message.MessageTextInput.length()== length);
    
}
    //Test whether whether the method for when the message Text is exceeded
    @Test
    public void testMessageLengthTooLong(){
         String longText = "A".repeat(251);
         Message message = new Message("0:1:Hi:Thanks",1, "+27796599327", longText);
         assertNotEquals("Message ready to send", message.MessageTextInput.length());
    }
    
    @Test
    public void testMessageHashNotEmpty(){
        Message message = new Message("0:1:Hi:Thanks",1, "+27796599327", "Hello");
        //Defining the variables String messageIDPart, String firstWord, and String lastWord
        String MessageIdPart = "0:1";
        int sequence = 0;
        String firstWord = "Hi";
        String lastWord = "Thanks";
        
        assertNotNull(message.CreateMessageHash(MessageIdPart, 0, firstWord, lastWord));
        assertFalse(message.CreateMessageHash(MessageIdPart, 0, firstWord, lastWord).isEmpty());
    
}
    
    @Test
    public void testPrintMessageContainsRecipient(){
        Message message = new Message("0:1:Hi:Thanks",1, "+27796599327", "Hello");
        assertTrue(message.printMessages().contains("+27796599327"));
    }
    
    @Test
    public void testClearSentMessages(){
        Message message = new Message("0:1:Hi:Thanks",1, "+27796599327", "Hello");
        message.markAsSent();
        Message.clearSentMessages();
        assertEquals(0,Message.getSentMessages().size());
    }

    
    
    
    
    
           
            
    
    
    
    
            
    
    
    
    
    
}
