/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
/**
 *
 * @author Student
 */
public class MessageStoreTest {
    
    private MessageStore store;
    private Message m1, m2, m3, m4, m5;
    
    @BeforeEach
    void setUp(){
        store = new MessageStore();
        m1 = new Message("+27834557896", "Did you get the cake?","1000000001", 0);
        m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.","2000000002", 1);
        m3 = new Message("+27838884567", "Yohoooo, I am at your gate.","3000000003", 2);
        m4 = new Message("0838884567", "It is dinner time!","4000000004", 3);
        m5 = new Message("+27838884567", "Ok. I am leaving without you.","5000000005", 4);
        
        store.addSent(m1);
        store.addStored(m2);
        store.addDisregarded(m3);
        store.addSent(m4);
        store.addStored(m5);
    }
    
    //Assert Equals
    @Test void sentMessahesArrayPopulated(){
        assertArrayEquals(new String[]{"Did you get the cake?","It is dinner time!"},store.getSentMessagesTexts());
    }
    @Test void longestMessage(){
        assertEquals("Where are you? You are late! I have asked you to be on time.",store.longestStoredMessage());
    }
    @Test void searchForMessageID(){
        assertEquals("It is dinner time!",store.searchByMessageID("4000000004").getMessageText());
    }
    @Test void searchByRecipient(){
        assertArrayEquals( new String[]{
            "Where are you? You are late! I have asked you to be on time.","Ok. I am leaving without you."
        }, store.searchByRecipient("+27838884567"));
    }
    @Test void deleteUsingMessageHash(){
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted",store.deleteByHash(m2.getMessageHash()));
    }
    @Test void reportShowsSentMessages(){
        String report = store.sentMessagesReport();
        assertTrue(report.contains("Message Hash: "+ m1.getMessageHash()));
        assertTrue(report.contains("Recipient: +27834557896"));
        assertTrue(report.contains("Message: Did you get the cake?"));
        assertTrue(report.contains("Message: It is dinner time!"));
    }
    @Test void disregardedArrayPopulated(){
        assertArrayEquals(new String[]{"Yohoooo, I am at your gate."},store.getDisregardedMessagesTexts());
    }
    @Test void hashdAndIDArraysPopulated(){
        assertEquals(5, store.getMessageHashes().length);
        assertArrayEquals(new String[]{
            "1000000001","2000000002","3000000003","4000000004","5000000005"
        }, store.getMessageIDs());
    }
    @Test void jsonSaveAndLoad()throws IOException{
        Path temp = Files.createTempFile("messages", ".json");
        try{
        assertTrue(store.saveStoredToJson(temp.toString()));
        MessageStore loaded = new MessageStore();
        assertEquals(2,loaded.loadStoredFromJson(temp.toString()));
        assertArrayEquals(store.getStoredMessagesTexts(),loaded.getStoredMessagesTexts());
        }finally{
        Files.deleteIfExists(temp);
        }
    }
    
    //Assert true/ assert false/ assert null
    @Test void deletedMessageNoLongerFound(){
        store.deleteByHash(m2.getMessageHash());
        assertNull(store.searchByMessageID("2000000002"));
    } 
    @Test void unknownHashNotDeleted(){
        assertEquals("Message not found.", store.deleteByHash("NOSUCHHASH"));
    }
    @Test void unknownIDReturnsNull(){
        assertNull(store.searchByMessageID("9999999999"));
    }
}
