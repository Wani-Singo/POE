/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;
/**
 *
 * @author Student
 */
public class Message {
    private static int totalSent =0; //keeps track of total messages through out the program
    private static final StringBuilder sentLog = new StringBuilder();
    private static final Pattern Cell = Pattern.compile("^\\+\\d{1,3}\\d{1,10}$");//referencing cell used in Login cell regex
    
    private String messageID;
    private int numMessage;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    public Message(String recipient, String MessageText){
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
        this.numMessage= numMessage;
        this.messageHash = buildHash(messageID, numMessage, messageText);
    }
    private static String generateID(){
        StringBuilder id = new StringBuilder();
        for(int i = 0; i < 10; i++){
            id.append(ThreadLocalRandom.current().nextInt(10));
        }
        return id.toString();
    }
    
    public boolean checkMessageID(){
        return messageID != null && messageID.length() <= 10;
    }
    public String messageIDGenerates(){
        return "Messaage id generated: " + messageID;
    }
    
    public static boolean isValidRecipient(String cell){
        return cell != null && Cell.matcher(cell).matches();
    }
    public static String checkRecipientCell(String cell){
        return isValidRecipient(cell)? "Cell phone number successfully captured."
               :"Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    
    public static String checkMessageLength(String message){
        if(message.length()<= 250){
            return "Message is ready to send.";
        }
        int over= message.length() - 250;
        return "Message exceeds 250 character by " + over + ", please reduce the size";
    }
    
    public static String buildHash(String id, int num, String message){
        String text = message.trim();
        int firstSpace = text.indexOf(' ');
        int lastSpace= text.lastIndexOf(' ');
        String first = (firstSpace == -1) ?text: text.substring(0, firstSpace);
        String last= (lastSpace == -1) ?text: text.substring(lastSpace + 1);
        first = first.replaceAll("[^A-Za-z0-9]", "");
        last = last.replaceAll("[^A-Za-z0-9]", "");
        return(id.substring(0,2) + ":" + num + ":" +first+ last).toUpperCase();
    }
    public String createMessageHash(){
        return messageHash;
    }
    public String SentMessage(int choice){
        if(choice ==1){
            totalSent++;
            sentLog.append(getDetails()).append("\n\n");
            return "Message successfully sent.";
        }else if(choice ==2){
            return "Press 0 to delete the message.";
        }else if(choice ==3){
            return storeMessage() ? "Message successfully stored."
                    : "Message could not be stored.";
        }else { 
            return "Invalid choice.";
        }
    }
    public String getDetails(){
       return "ID: " + messageID +
               ", Message Hash: " +messageHash+
               ", Recipient: " + recipient+
               ", Message: "+ messageText;
   }
   public static String printMessages(){
       return sentLog.toString();
   }
   public static int returnTotalMessages(){
       return totalSent;
   }
   
   public static void restTotoals(){
       totalSent =0;
       sentLog.setLength(0);
   }
   
   public boolean storeMessage(){
       Path file = Paths.get("messages.json");
       String obj = "{\n"
               + "  \"messageID\": \"" + esc(messageID) +"\",\n"
               +  "  \"messageHas\": \"" + esc(messageHash) +"\",\n"
               + "  \"recipient\": \"" + esc(recipient) +"\",\n"
               + "  \"message\": \"" + esc(messageText) +"\",\n"
               + " ]";
       try {
           String content;
           String existing = Files.exists(file) ? Files.readString(file).trim(): "";
           int end= existing.lastIndexOf(']');
           if (existing.isEmpty() || end == -1){
               content = "[\n" +obj +"\n]";
           }else{
               String body = existing.substring(0, end).stripTrailing();
               content = body.equals("[") ? "[\n"+obj +"\n]"
                       :body +",\n"+obj +"\n]";
           }
           Files.writeString(file,content);
           return true;
       }catch(IOException e){
           return false;
       }
   }
   
   private static String esc(String s){
       return s.replace("\\", "\\\\").replace("\"","\\\"").replace("\n", "\\n");
   }
  
   public String getMessageText(){return messageText;}
   public String getRecipient(){return recipient;}
   public String getMessageID(){return messageID;}
   public String getMessageHash(){return messageHash;}
}
    
