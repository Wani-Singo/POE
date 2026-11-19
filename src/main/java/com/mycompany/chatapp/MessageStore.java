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
}
