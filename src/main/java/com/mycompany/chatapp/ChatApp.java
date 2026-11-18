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
        
       System.out.println("----Welcome to ChatApp----");
       System.out.println("-----Registration-----");
       
       System.out.print("Enter you First Name: ");
       String firstName = input.nextLine();
      
       System.out.print("Enter your Last Name: ");
       String lastName = input.nextLine();
       Login obj = new Login(firstName, lastName);
       
       //---Registration
       System.out.println("Enter Username: ");
       String userName = input.nextLine();
       System.out.println(obj.usernameMessage(userName));
       while (!obj.checkUserName(userName)){
           System.out.print("Enter Username: ");
           userName = input.nextLine().trim();
           System.out.println(obj.usernameMessage(userName));
       }
       System.out.println("Enter Password: ");
       String Password = input.nextLine();
       System.out.println(obj.passwordMessage(Password));
       while (!obj.checkPasswordComplexity(Password)){
           System.out.print("Enter Password: ");
           Password = input.nextLine().trim();
           System.out.println(obj.passwordMessage(Password));
       }
       System.out.println("Enter cell phone number (e.g. +27838968976: ");
       String cellNumber = input.nextLine();
       System.out.println(obj.cellMessage(cellNumber));
       while (!obj.checkCellNumber(cellNumber)){
           System.out.print("Enter cell phone number (e.g. +27838968976: ");
           cellNumber = input.nextLine().trim();
           System.out.println(obj.cellMessage(cellNumber));
       }
       System.out.println(obj.registerUser(userName, Password, cellNumber));
    
       //----Login
       System.out.println("\n-----Login-----");
       System.out.println("Enter Username: ");
       String username = input.nextLine().trim();
       System.out.println("Enter Password: ");
       String password = input.nextLine().trim();
       boolean loggedIn = obj.loginUser(username, password);
       System.out.println(obj.returnLoginStatus(loggedIn));
       
       while (!loggedIn){
           System.out.println("Enter Username: ");
           username = input.nextLine().trim();
           System.out.println("Enter password: ");
           password = input.nextLine().trim();
           loggedIn = obj.loginUser(username, password);
           System.out.println(obj.returnLoginStatus(loggedIn));
        }
       
     /*     String loginStatus = obj.returnLoginStatus(inputUsername, inputPassword);
          System.out.println(loginStatus);
          
          if(loginStatus.contains("Welcome")){
              loggedIn = true;
              System.out.println("----Welcome to QuickChat----");
              
              boolean running = true;
              while(running){
                  System.out.println("----Numeric Menu-----");
                  System.out.println("1. Send Messages");
                  System.out.println("2. Show recently sent messages");
                  System.out.println("3. Quit");
                  System.out.println("Choose an option (1 - 3)");
                  
                  String choiceStr = input.nextLine();
                  int choice;
                  
                  try{
                      choice = Integer.parseInt(choiceStr);
                  } catch (NumberFormatException e){
                      System.out.println("Invalid input. please enter a number between 1 and 3");
                      continue;
                  }
                  if(choice ==1){
                      System.out.println("How many messages do you wish to enter? ");
                      int numMessages;
                      try{
                          numMessages = Integer.parseInt(input.nextLine());
                      }catch (NumberFormatException e){
                      System.out.println("Invalid number. Returning to menu");
                      continue;
                  }
                      for(int i = 1; i <= numMessages; i++ ){
                          System.out.println("---Entering Message " + i + " of" + numMessages + "---");
                          
                          System.out.print("Enter recipient cell number (e.g., +27811324...");
                          String recipient = input.nextLine();
                          
                          System.out.print("Enter your message (Max 250 characters): ");
                          String messageText = input.nextLine();
                          
                          Message msg =new Message(recipient, messageText);
                          
                          String cellcheck = msg.checkRecipientCell();
                          System.out.println(cellcheck);
                          
                          if(!cellcheck.contains("successfully captured")){
                              System.out.println("Skipping message action due to invalid recipient number.");
                              continue;
                          } 
                          System.out.print("Choose action for this message: ");
                          System.out.print("1. Send Message");
                          System.out.print("2. Disregard Message (Press 0 to delete)");
                          System.out.print("Store message in JSON file");
                          System.out.println("Choose an option (1 - 3)");
                     
                      
                      int actionchoice;
                      try{
                          actionchoice = Integer.parseInt(input.nextLine());
                      }catch (NumberFormatException e){
                      actionchoice = 1;
                      }
                      String result = msg.SendMessage(actionchoice);
                      System.out.println(result);
                      
                      System.out.println("Message Details: ");
                      System.out.println("Messade ID: "+ msg.getMessageID());
                      System.out.println("Messsage Hash: " + msg.getMessageHash());
                      System.out.println("Recipient: " + msg.getRecipient());
                      System.out.println("Message: " + msg.getMessageText());
                      }
                        System.out.println("Total Messages processed: "+ Message.returnTotalMessages());
                      }else if(choice ==2){
                        System.out.println("Coming Soon");
                      }else if(choice ==3){
                          System.out.println("Exiting QuickChat. GoodBye!");
                              running = false;
                        }else{
                              System.out.println("Invalid option. Please choose 1, 2 or 3");
                    }
                   }     
                }else {
                       System.out.println("Please try again"); 
                     }
                }*/
      input.close();
    
}
}