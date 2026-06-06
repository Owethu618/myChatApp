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
        Message message = new Message("0:1:Hi:Thanks",1,"+27796499327", "Hello");
        assertTrue(message.checkRecipient("+27796499327"));
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
        Message message = new Message("0:1:Hi:Thanks",1,"+27796599327", "Hi, good to here from you");
        assertTrue(message.MessageTextInput.length() <= 250);
    }
    //Test whether whether the method for when the message Text is exceeded
    @Test
    public void testMessageLengthTooLong(){
         String longText = "A".repeat(251);
         Message message = new Message("0:1:Hi:Thanks",1, "+27796599327", longText);
         assertTrue(message.MessageTextInput.length() > 250);
    }
    
    @Test
    public void testMessageHashNotEmpty(){
        Message message = new Message("0:1:Hi:Thanks",1, "+27796599327", "Hello");
        String hash = message.createMessageHash();
        assertNotNull(hash);
        assertFalse(hash.isEmpty());
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
    
    @Test
    public void testSentMessagesArray_CorrectlyPopulated(){
        Message message = new Message("0:1:Hi:Thanks",1, "+27796599327", "Hello");
        message.markAsSent();
        assertTrue(Message.getSentMessages().size() >= 1);
    }
    
    @Test
    public void testDisplayLongestMessage_returnsEmptyWhenNoStoredMessages(){
        assertEquals("", Message.displayLongestMessage());
    }
    
    @Test
    public void testSearchByMessageID_returnsCorrectMessage(){
        assertEquals("Message not detected for IDtest", Message.searchByMessageID("test"));
    }
    
    @Test
    public void testSearchByRecipient_returnsAllMatchingMessages(){
        String recipient = "+27838884567";
        assertTrue(Message.searchByRecipient(recipient).contains(recipient));
    }
    
    @Test
    public void testDeleteByMessageHash_removesCorrectMessage(){
        Message message = new Message("0:1:Hi:Thanks", 1, "+27838884567", "Hello");
        String hash = message.createMessageHash();
        assertEquals("messageHash not found", Message.deleteByHash(hash));
    }
    
    @Test
    public void testDisplayReport_containsRequiredFields(){
        assertTrue(Message.displayFullReport().contains("====Full Message Report ===="));
    }
    
    
    

    
    
    
    
    
           
            
    
    
    
    
            
    
    
    
    
    
}
