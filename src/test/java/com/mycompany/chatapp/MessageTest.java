/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
   @Test
   public void testCheckMessageId(){
       Message msg = new Message("+27718693002", "Hello World");
       assertTrue(msg.checkMessageID(), "Message ID should be valid and <=10 characters.");
   }
   
  @Test 
  public void testCheckRecipientCellSuccess(){
      Message msg = new Message("+27718693002", "Test Message");
      assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
  }
  
  @Test
  public void testCheckRecipientCellFailure(){
      Message msg = new Message("08575975889", "Test Message");
      assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
  }
  
  @Test 
  public void testMessageLengthSuccess(){
      String shortText = "Hi Mike, can you join us for dinner tonight?";
      Message msg = new Message("+27718693002", "shortText");
      String result = msg.SendMessage(1);
      assertEquals("Message ready to send.",result);
  }
  
  @Test 
  public void testMessageLengthFailure(){
      StringBuilder sb = new StringBuilder();
      for(int i =0 ; i< 260; i++){
          sb.append("a");
      }
      String longText = sb.toString();
      Message msg = new Message("+27718693002", longText);
      String result = msg.SendMessage(1);
      assertEquals("Message ready to send.",result);
  }
  
  @Test
  public void testCreateMessageHash(){
      Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
      String hash = msg.createMessageHash();
      assertNotNull(hash);
      assertTrue(hash.isEmpty(), "Hash must follow the correct formatted stucture.");
  }
  
  @Test
  public void testReturnTotalMessages(){
      int initialTotal = Message.returnTotalMessages();
      new Message("+27718693002", "Message One");
      assertEquals(initialTotal +1, Message.returnTotalMessages(), "Total messages should increment correctly");
  }
  
}
