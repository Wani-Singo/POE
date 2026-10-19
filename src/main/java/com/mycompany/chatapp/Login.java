/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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

public Login(){
   this.firstName = firstName;
   this.lastName = lastName;
   this.Password = Password;
   this.cellNumber = cellNumber;
   this.userName = userName;
   }

public void setFirstName(String firstName){
    this.firstName = firstName;
}
public void setLastName(String lastName){
    this.lastName = lastName;
}

public boolean checkUserName(){
    if(userName == null){
        return false;
    }
    return userName.contains("_") && userName.length() <=5;
}
public boolean checkPasswordComplexity(String Password){
    if (Password == null || Password.length() < 8){
        return false;
    }
    boolean hasCapital = Pattern.matches(".*[A-Z].*", Password);
    boolean hasDigit = Pattern.matches(".*[0-9].*", Password);
    boolean hasSpecial = Pattern.matches(".*[^a-zA-Z0-9].*", Password);
    
    return hasCapital && hasDigit && hasSpecial;
}
public boolean checkCellNumber(String cellNumber){
    String regex = "^\\+27\\d{9)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(cellNumber);
    return matcher.matches();
    }
 public String registerUser(String username, String password, String cellnumber){
     if(!checkUserName()){
         return "Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than five characters in length";
     }
     if(!checkPasswordComplexity(password)){
         return "Password is not correctly formatted. Enter at least eight character, a capital letter, a number and a special Character";
     }
     if(!checkCellNumber(cellnumber)){
         return "Cellphone number is incorrectly formatted or does not contain internation code";
     }
     
     this.userName = username;
     this.Password = password;
     this.cellNumber = cellnumber;
     return "Username and Password successfully captured";
 }   
 
 public boolean loginUser(String inputUsername, String inputPassword){
     if(userName ==null || Password == null){
         return false;
     }
     return userName.equals(inputUsername)&& Password.equals(inputPassword);
 }
 public String returnLoginStatus(String inputUsername, String inputPassword){
     if(loginUser(inputUsername,inputPassword)){
           return "Welcome " + firstName + " " + lastName + ", it is great to see you again";
        }else{
            return "Username or password incorrect. Please try again";
        }
    }
}

