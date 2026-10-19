/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class ChatApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login obj = new Login();
        
       System.out.println("----Welcome to The Lounge----");
       System.out.println("-----Registration-----");
       
       System.out.print("Enter you First Name: ");
       String name = input.nextLine();
      
       System.out.print("Enter your Last Name: ");
       String lastname = input.nextLine();
       
       boolean isRegistered = false;
       
       while(!isRegistered){
       System.out.println("Enter Username: ");
       String userName = input.nextLine();
       
       System.out.println("Enter Password: ");
       String Password = input.nextLine();
       
       System.out.println("Enter Cellphone Number(start with +27): ");
       String cellNumber = input.nextLine();
       
       String regMessage= obj.registerUser(userName, Password, cellNumber);
       System.out.println(regMessage);
       
      if(regMessage.equals("Username and Password successfully captured")){
          isRegistered = true;
      }else{
          System.out.println("Please try registering again with the correct details");
      }
    }
        boolean loggedIn = false;
      while(!loggedIn){
          System.out.println("Enter Username: ");
          String inputUsername = input.nextLine();
          System.out.println("Enter password: ");
          String inputPassword = input.nextLine();
         
          String loginStatus = obj.returnLoginStatus(inputUsername, inputPassword);
          System.out.println(loginStatus);
          
          if(!loggedIn){
              System.out.println("Please try again");
          }
      }
      input.close();
    }
}
