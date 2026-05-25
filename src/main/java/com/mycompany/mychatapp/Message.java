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

    private static final List<String> SENT_MESSAGES = new ArrayList<>();

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
                return "Message successfully sent";
            case 2:
                this.messageStatus = "Disregard";
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
        String currentHash = createMessageHash();
        this.printMessages = "messageID:" + messageID + "\nMessage Number:" + messageNumber + "\nRecipient:" + recipient + "\nMessage:" + MessageTextInput + "\nMessage Hash:" + currentHash + "\nStatus:" + (messageStatus == null ? "Pending" : messageStatus);
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

    public void markAsSent() {
        this.messageStatus = "Sent";
        this.totalMessagesSent++;
        SENT_MESSAGES.add(printMessages());
    }

    public static void clearSentMessages() {
        SENT_MESSAGES.clear();
    }

    public static List<String> getSentMessages() {
        return new ArrayList<>(SENT_MESSAGES);
    }
}
