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
    }
}
