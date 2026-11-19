/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
/**
 *
 * @author Student
 */
public class MessageTest {
    private static final String MSG1 ="Hi Mike, can you join us for dinner tonight?";
    private static final String MSG2 ="Hi Keegan, did you receive the payment?";
    
    @BeforeEach
    void reset(){
        Message.restTotals();
    }
     //----AsssertEquals
    @Test void messageLengthSuccess(){
        assertEquals("Message ready to send.", Message.checkMessageLength(MSG1));
    }
    @Test void messageLengthFailure(){
        String  longMsg = "a".repeat(260);
        assertEquals("Message exceeds 250 characters by 10, please reduce the size.",Message.checkMessageLength(longMsg));
    }
    @Test void RecipientSuccess(){
        assertEquals("Cell phone number successfully captured.", Message.checkRecipientCell("+27718693002"));
    }
    @Test void recipientFailure(){
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again."
                ,Message.checkRecipientCell("08575975889"));
    }
    @Test void hashTestCase(){
        Message m = new Message("+27718693002", MSG1,"0012345678",0);
        assertEquals("00:0:HITONIGHT",m.createMessageHash());
    }
    @Test void hashLoop(){
        for(int i=0; i < 2 ; i++){
            String text = (i == 0) ?MSG1 :MSG2;
            String expected = (i == 0) ? "00:0:HITONIGHT" : "00:1:HIPAYMENT";
            Message m = new Message("+27718693002", text,"0012345678",i);
            assertEquals(expected, m.createMessageHash());
        }
    }
    @Test void messageIDCreated(){
        Message m = new Message("+27718693002", MSG1,"0012345678",0);
        assertEquals("Message ID generated: 0012345678",m.messageIDGenerated());
        assertEquals(10, m.getMessageID().length());
    }
    @Test void sentOption1(){
        assertEquals("Message successfully sent.", new Message("+27718693002", MSG1, "0012345678", 0).sentMessage(1));
    }
    @Test void sentOption2(){
        assertEquals("Press 0 to delete the message.", new Message("+27718693002",MSG2,"0012345678", 1).sentMessage(2));
    }
    @Test void sentOption3(){
        assertEquals("Message successfully stored.", new Message("+27718693002", MSG1,"0012345678", 0).sentMessage(3));
    }
    @Test void totalMessagesSent(){
        new Message("+27718693002", MSG1,"0012345678", 0).sentMessage(1);
        assertEquals(1, Message.returnTotalMessages());
    }
    
    //------Assert True/ Assert False
    @Test void messageIDValid(){
        assertTrue(new Message("+27718693002", MSG1, "0012345678", 0).checkMessageID());
    }
    @Test void recipientTrue(){
        assertTrue(Message.isValidRecipient("+27718693002"));
    }
    @Test void recipientFalse(){
        assertFalse(Message.isValidRecipient("08575975889"));
    }
    
   
}
  