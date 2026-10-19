/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
/**
 *
 * @author Student
 */
public class Message {
    private String messageID;
    private int numMessageSent;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    private static int totalMessagesSent=0; //keeps track of total messages through out the program
    
    public Message(String recipient, String MessageText){
        this.messageID = generateRandomMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.numMessageSent = ++totalMessagesSent;
        this.messageHash = createMessageHash();
    }
}
