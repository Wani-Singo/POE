/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.util.regex.Pattern;
/**
 *
 * @author Student
 */
public class Login {
    private String userName;
    private String Password; 
    private String cellNumber;
    private String firstName;
    private String lastName;

public Login( String firstName, String lastName){
   this.firstName = firstName;
   this.lastName = lastName;
   }

public boolean checkUserName(String userName){
   return userName != null && userName.contains("_") && userName.length() <=5;
    }

public boolean checkPasswordComplexity(String Password){
    return Password != null && Password.length() >=8 && Password.matches(".*[A-Z].*") && Password.matches(".*\\d.*") && Password.matches(".*[^A-Za-z0-9].*");
    }

private static final Pattern Cell = Pattern.compile("^\\+\\d{1,3}\\d{1,10}$");
public boolean checkCellNumber(String cellNumber){
    return cellNumber != null && Cell.matcher(cellNumber).matches();
    }

public String usernameMessage(String userName){
    return checkUserName(userName)? "Username successfully captured."
            :"Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length";
}
public String passwordMessage(String Password){
    return checkPasswordComplexity(Password)? "Password successfully captured"
            : "Password is not correctly formatted; please ensure that the password  containes at least eight character, a capital letter, a number and a special Character";
}
public String cellMessage(String cellNumber){
    return checkCellNumber(cellNumber)? "Cell Numnber successfully captured"
            : "Cellphone number is incorrectly formatted or does not contain internation code; please coorect the number and try again";
}
public String registerUser(String userName, String Password, String cellNumber){
     if(!checkUserName(userName)){
         return usernameMessage(userName);
     }
     if(!checkPasswordComplexity(Password)){
         return passwordMessage(Password);
     }
     if(!checkCellNumber(cellNumber)){
         return cellMessage(cellNumber);
    }
     this.userName = userName;
     this.Password = Password;
     this.cellNumber = cellNumber;
     return "User has benn registered successfully";
 }   
 
 public boolean loginUser(String userName, String Password){
     return this.userName != null && this.userName.equals(userName) && this.Password.equals(Password);
 }
 
 public String returnLoginStatus(boolean loggedIn){
     return loggedIn ? "Welcome " +firstName+ " " +lastName+ " it is great to see you again." 
             :"Username or password incorrect, please try again.";
}
}

