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
public class LoginTest {
    Login obj = new Login();
    
    @Test
   
    public void TestUsernameCorrectlyFormattedMessage(){
        String Username = "Kyle";
        String Password = "Ch&&sec@ke99!";
        String expected = "Welcome Kyle Black, it is great to see you again";
        String results = obj.returnLoginStatus(Username, Password);
        assertEquals(expected, results);
    }
    
    @Test
    public void TestUsernameIncorrectlyFormattedMessage(){
        String Invalidusername = "kyle!!!!!!!";
        String Password = "Ch&&sec@ke99!";
        String expected = "Username is not correctly formatted; please ensure that youur username contains an underscore and is not more than five characters in length";
        String results = obj.returnLoginStatus(Invalidusername, Password);
        assertEquals(expected,results);
    }
    
    @Test
    public void TestPasswordComplexityMessage(){
        String Username = "Kyle";
        String Password = "Ch&&sec@ke99!";
        String expected ="Password successfully captured";
        String results = obj.returnLoginStatus(Username, Password);
        assertEquals(expected,results);
    }
    
    @Test
    public void TestPasswordIncomplexityMessage(){
        String Username = "Kyle";
        String Invalidpassword = "password";
        String expected = "Password is not correctly formatted; please ensure that the password contains at leat eight character, a capital letter, a number and, a speacial character";
        String results = obj.returnLoginStatus(Username, Invalidpassword);
        assertEquals(expected,results);
    }
    
    @Test
    public void TestcellphoneNumberCorrectlyFormattedMessage(){
        String Username = "kyl_1";
        String Password = "Ch&&sec@ke99!";  
        String cellNumber = "+27838968976";
        String expected ="Cell number successful captured";
        String results = obj.registerUser(Username, Password, cellNumber);
        assertEquals(expected,results);
    }
    
    @Test
    public void TestcellphoneNumberIncorrectlyFormattedMessage(){
        String Username = "kyl_1";
        String Password = "Ch&&sec@ke99!"; 
        String InvalidcellNumber ="0838968976";
        String expected ="Cell number successful captured";
        String results = obj.registerUser(Username, Password, InvalidcellNumber);
        assertEquals(expected,results);
    }
    
    @Test
    public void TestLoginSuccess(){
        String Username = "kyl_1";
        String Password = "Ch&&sec@ke99!"; 
        boolean results = obj.loginUser(Username, Password);
        assertTrue(results);
    }
    
    @Test
    public void TestLoginFailure(){
        String Invalidusername = "kyle!!!!!!!";
        String Invalidpassword = "password";
        boolean results = obj.loginUser(Invalidusername, Invalidpassword);
        assertFalse(results);
    }
    
    @Test
    public void TestUsernameCorrectformat(){
        boolean results = obj.checkUserName();
        assertTrue(results);
    }
    
    @Test
    public void TestUsernameIncorrectformat(){
        String Invalidusername = "kyle!!!!!!!";
        boolean results = obj.checkUserName();
        assertFalse(results);
    }
    
    @Test
    public void TestPasswordComplexity(){
        String Password = "Ch&&sec@ke99!";
        boolean results = obj.checkPasswordComplexity(Password);
        assertTrue(results);
    }
    
    @Test
    public void TestPasswordIncomplexity(){
        String InvalidPassword = "password";
        boolean results = obj.checkPasswordComplexity(InvalidPassword);
        assertFalse(results);
    }
    
    @Test
    public void TestCellPhoneSuccess(){
        String cellNumber = "+27838968976";
        boolean results = obj.checkCellNumber(cellNumber);
        assertTrue(results);
    }
    
    @Test
    public void TestCellPhoneFailure(){
        String InvalidcellNumber = "0838968976";
        boolean results = obj.checkCellNumber(InvalidcellNumber);
        assertFalse(results);
    }
    
}
