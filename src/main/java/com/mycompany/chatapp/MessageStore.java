/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
/**
 *
 * @author Student
 */
public class MessageStore {
    private static final int Max = 100; //this is the capacity of every array
    
    private final Message[] sentMessages = new Message[Max];
    private final Message[] disregardedMessages = new Message[Max];
    private final Message[] storedMessages = new Message[Max];
    private final Message[] allMessages = new Message[Max];
    private final String[] messageHashes = new String[Max];
    private final String[] messageIDs = new String[Max];
    
    //these are the positions used while reading the JSON text
    private int sentCount = 0;
    private int disregardedCount = 0;
    private int storedCount = 0;
    private int allCount = 0;
    
    //populating the Array
    public boolean addSent(Message m){
        if (sentCount >= Max || allCount >= Max){
            return false;
        }
      sentMessages[sentCount++] = m;
      register(m);
      return true;
    }
    public boolean addDisregarded(Message m){
        if (disregardedCount >= Max || allCount >= Max){
            return false;
        }
      disregardedMessages[disregardedCount++] = m;
      register(m);
      return true;
    }
    //adds the messages to the all messgaes, hash and ID arrays
    private void register(Message m){
        allMessages[allCount] = m;
        messageHashes[allCount] = m.getMessageHash();
        messageIDs[allCount] = m.getMessageID();
        allCount++;
    }
    private boolean idExists(String id){
        for (int i = 0; i < allCount; i++){
            if (messageIDs[i].equals(id)){
                return true;
            }
        }
        return false;
    }
    
    //Reading and Writing message.json
    public int loadStoredFromJson(String fileName){
        Path file = Paths.get(fileName);
        if (!Files.exists(file)){
            return 0;
        }
        String json;
        try{
            json = Files.readString(file);
        }catch(IOException e){
            return 0;
        }
        int loaded = 0;
        parsePos = 0;
        boolean more = true;
        while (more){
            String id = readValue(json,"messageID");
            String hash = readValue(json, "messageHash");
            String recipient = readValue(json, "recipient");
            String text = readValue(json, "messageText");
            if (id ==  null || hash == null || recipient == null || text == null){
                more = false;
            }else if (!idExists(id) && addStored(Message.fromStored(recipient, text, id, hash))){
                loaded++;
            }
        }
        return loaded;
    }
    private String readValue(String json, String key){
        int k = json.indexOf("\"" + key + "\"", parsePos);
        if (k == -1){
            return null;
        }
        int colon = json.indexOf(':', k);
        int open = json.indexOf('"', colon + 1);
        if (colon == 1 || open == -1){
            return null;
        }
        StringBuilder value = new StringBuilder();
        int i = open +1 ;
        while (i < json.length() && json.charAt(i) != '"'){
            int c = json.charAt(i);
            if (c == '\\' && i + 1 < json.length()){
                i++;
                int next = json.charAt(i);
                value.append(next == 'n' ? '\n' : next);
            }else{
                value.append(c);
            }
            i++;
            parsePos = 1 + 1;
            return value.toString();
        }
        //rewrites the message.json from the stored messages array
        public boolean saveStoredToJson(String fileName){
          StringBuilder sb = new StringBuilder("[");
          for (int = 0; i < storedCount; i++){
            sb.append(i == 0 ? "\n": ",\n").append(storedMessages[i].toJson());
        }
          sb.append(storedCount==0 ? "]" : "\n");
          try{
              Files.writeString(Paths.get(fileName), sb.toString());
              return true;
          }catch(IOException e){
              return false;
          }
        }
        public String displaySenderAndRecipient(String sender){
            if (storedCount == 0){
                return "No stored messages.";
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < storedCount; i++){
                sb.append("Sender: ").append(sender). append(" | Recipient: ").append(storedMessages[i].getRecipient()).append("\n");
            }
            return sb.toString();
        }
        public String longestStoredMessage(){
            if (storedCount == 0){
                return "No stored messages.";
            }
            String longest = storedMessages[0].getMessageText();
            for (int i = 1; i < storedCount; i++){
                if (storedMessages[i].getMessageText().length() > longest.length()){
                    longest = storedMessages[i].getMessageText();
                }
            }
            return longest;
        }
        public Message searchByMessageID(String id){
            for ( int i = 0; i < allCount; i++){
                if (messageIDs[i].equals(id)){
                    return allMessages[i];
                }
            }
            return null;
        }
        public String[] searchByRecipient(String recipient){
            int matches = 0;
            for (int i =0; i < sentCount; i++){
            if (sentMessages[i].getRecipient().equals(recipient)){
                matches++;
                }
            }
            for (int i =0; i < storedCount; i++){
            if (storedMessages[i].getRecipient().equals(recipient)){
                matches++;
                }
            }
            String[] found = new String[matches];
            int pos =0;
            for (int i =0; i < sentCount; i++){
            if (sentMessages[i].getRecipient().equals(recipient)){
                found[pos++] = sentMessages[i].getMessageText();
                }
            }
            for (int i =0; i < storedCount; i++){
            if (storedMessages[i].getRecipient().equals(recipient)){
                found[pos++] = storedMessages[i].getMessageText();
                }
            }
            return found;
        }
        public String deleteByHash(String hash){
            for (int i =0; i< storedCount;i++){
                if (storedMessages[i].getMessageHash().equals(hash)){
                    Message removed = storedMessages[i];
                    for (int j = i; j < storedCount-1; j++){
                        storedMessages[j] = storedMessages[j + 1];
                    }
                    storedMessages[storedCount -1] = null;
                    storedCount--;
                    removeFromRegister(removed.getMessageID());
                    return "Message: \"" +removed.getMessageText() + "\" sSuccessfully deleted";
                }
            }
            return "Message not found.";
        }
        private void removeFromRegister(String id){
            for (int i = 0; i < allCount; i++){
                if (messageIDs[i].equals(id)){
                for (int j = i ; j < allCount -1 ; j++){
                    allMessages[j] = allMessages[j+1];
                    messageHashes[j]= messageHashes[j+1];
                    messageIDs[j] = messageIDs[j+1];
                    }
                allMessages[allCount -1]= null;
                messageHashes[allCount -1]= null;
                messageIDs[allCount -1]= null;
                return;
                }
            }
        }
        public String storedMessagesReport(){
            if (storedCount == 0){
                return "No stored messages.";
            }
            StringBuilder sb = new StringBuilder();
            for (int i =0; i < storedCount; i++){
                sb.append(storedMessages[i].getDetails()).append("\n\n");
            }
            return sb.toString();
        }
        public String sentMessagesReport(){
            if (sentCount == 0){
                return "No messages have been sent.";
            }
            StringBuilder sb = new StringBuilder();
            for (int i =0; i < sentCount; i++){
            sb.append("Message Hash: ").append(sentMessages[i].getMessageHash()).append("\n").append("Recipient: ").append(sentMessages[i].getRecipient()).append("\n")
                    .append("Message: ").append(sentMessages[i].getMessageText()).append("\n\n");
            }
          return sb.toString();
        }
}
