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
          
          if(loginStatus.contains("Wlelcom")){
              loggedIn = true;
              System.out.println("----Welcome to QuikChat----");
              
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
                      System.out.println("How many messages do you wishh to enter? ");
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
                          
                          System.out.print("Enter your message (Max 205 characters): ");
                          String messageText = input.nextLine();
                          
                          Message msg =new Message(recipient, messageText);
                          
                          String cellcheck = msg.checkRecipientCell();
                          System.out.println(cellcheck);
                          
                          if(cellcheck.contains("successfully captures")){
                              System.out.println("Skipping message action dur to invalid recipient number.");
                              continue;
                          } 
                          System.out.print("Choose action for this message: ");
                          System.out.print("1. Send Message");
                          System.out.print("2. Disregard Message(Press 0 to delete)");
                          System.out.print("Stroe message in JSON file");
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
                }
      input.close();
    }
}
