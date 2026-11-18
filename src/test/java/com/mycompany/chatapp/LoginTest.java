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
    private final Login obj =new Login("Kyle", "Smith");
    
    //Assert true & Assert false
    @Test void loginSucessful(){
        obj.registerUser("kyl_1","Ch&&sec@ke99!", "+27838968976");
        assertTrue(obj.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    @Test void loginFailed(){
        obj.registerUser("kyl_1","Ch&&sec@ke99!", "+27838968976");
        assertFalse(obj.loginUser("kyl_1", "Password"));
    }
    @Test void usernameTrue(){
        assertTrue(obj.checkUserName("kyl_1"));
    }
    @Test void usernameFalse(){
        assertFalse(obj.checkUserName("kyle!!!!!!!!!"));
    }
    @Test void passwordTrue(){
        assertTrue(obj.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    @Test void passwordFale(){
        assertFalse(obj.checkPasswordComplexity("Password"));
    }
    @Test void cellTrue(){
        assertTrue(obj.checkCellNumber("+27838968976"));
    }
    @Test void cellFalse(){
        assertFalse(obj.checkCellNumber("08966553"));
    }
    
    //Assert Equals\
    @Test void usernameCorrectMessage(){
        assertEquals("Username successfully captured.", obj.usernameMessage("kyl_1"));
    }
    @Test void usernameIncorrectMessage(){
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length",
                obj.usernameMessage("kyle!!!!!"));
    }
    @Test void passswordCorrectMessage(){
        assertEquals("Password successfully captured", obj.passwordMessage("Ch&&sec@ke99!"));
    }
    @Test void passwordIncorrectMessage(){
        assertEquals("Password is not correctly formatted; please ensure that the password  containes at least eight character, a capital letter, a number and a special Character",
                obj.passwordMessage("Password"));
    }
    @Test void cellCorredtMesssage(){
        assertEquals("Cell Numnber successfully captured",obj.cellMessage("+27838968976"));
    }
    @Test void cellIncorrectMessage(){
        assertEquals("Cellphone number is incorrectly formatted or does not contain internation code; please coorect the number and try again",
                obj.cellMessage("08966553"));
    }
    @Test void welcomeMessage(){
        obj.registerUser("kyl_1","Ch&&sec@ke99!", "+27838968976");
        assertEquals("Welcome Kyle Smith it is great to see you again.",obj.returnLoginStatus(obj.loginUser("kyl_1","Ch&&sec@ke99!")));
    }
    
}
