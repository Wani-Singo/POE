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
    private String generateRandomMessageID(){
        Random rand = new Random();
        long id = 1000000000L + rand.nextLong(9000000000L);
        return String.valueOf(id);
    }
    public boolean checkMessageID(){
        return this.messageID != null && this.messageID.length() <= 10;
    }
    public String checkRecipientCell(){
        if(this.recipient != null && this.recipient.startsWith("+") && this.recipient.length() <= 10){
            return "Cell phone number successfully captured";
        }else{
            return "Cell phone number is incorrectly formatted or does not contain an intrnational code. Please correct the number and try again";
        }
    }
    public String createMessageHash(){
        String text = messageText.trim();
        int firstSpace = text.indexOf(' ');
        int lastSpace = text.lastIndexOf(' ');
        
        String first = (firstSpace < 0 ? text : text.substring(0, firstSpace)).replaceAll("[^A-Za-z0-9]","");
        String last = (lastSpace < 0 ? text : text.substring(lastSpace + 1)).replaceAll("[^A-Za-z0-9]","");
        return (messageID.substring(0,2) + ":" + numMessageSent + ";" + first + last).toUpperCase();
    }
    public String SendMessage(int choice){
        if(this.messageText != null && this.messageText.length() > 250){
            int excess = this.messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size";
        }
        if(choice ==1){
            return "Message successfully sent.";
        }else if(choice ==2){
            return "Press 0 to delete the message.";
        }else if(choice ==3){
            boolean stored = storeMessage();
              if(stored){
                  return "Message successfully stored.";
              }else{
                  return "Error storing message.";
              }
        }else { 
            return "Invalid option selected.";
        }
    }
   public boolean storeMessage(){
       String jsonContent = String.format("{\n \"messageID\": \"%s\" \n \"messageHash\": \"%s\",\n \"recipient\": \"%s\", \n \"message\": \"%s\" \n},\n, this.messadeID, this.messageHash,this.recipient,this.messageText");
       
       try (FileWriter writer = new FileWriter("message.json", true)){
           writer.write(jsonContent);
           return true;
       }catch(IOException e){
           e.printStackTrace();
           return false;
       }
   }
   public String printMessage(){
       return "ID: " + this.messageID +
               ", Hash: " +this.messageHash+
               ", Recipient: " + this.recipient+
               ", Message: "+ this.messageText;
   }
   public static int returnTotalMessages(){
       return totalMessagesSent;
   }
   public String getMessageText(){return messageText;}
   public String getRecipient(){return recipient;}
   public String getMessageID(){return messageID;}
   public String getMessageHash(){return messageHash;}
}
    
