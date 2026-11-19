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
    
    private static final String JSON_FILE = "messages.json";
    
    private static int readInt(Scanner in, String prompt){
        System.out.print(prompt);
        String line = in.nextLine().trim();
        while(!line.matches("\\d{1,9}")){
            System.out.print("Please enter a whole number: ");
            line = in.nextLine().trim();
        }
        return Integer.parseInt(line);
    }
    
    // the stored messages menu
    private static void storedMessagesMenu(Scanner in, MessageStore store, String sender){
        boolean inMenu = true;
        while(inMenu){
            System.out.println("\n--------Stored Messages------");
            System.out.println("1. Display sender and recipient of all stored messages");
            System.out.println("2. Display the longest stored message");
            System.out.println("3. Search for a message ID");
            System.out.println("4. Search for all messages fora recipient");
            System.out.println("5. Delete a message using the message hash");
            System.out.println("6. Display a report of all stored messages");
            System.out.println("7. Back to main menu");
            int pick = readInt(in, "Choose an Option (1-7): ");
            
            if (pick == 1){
                System.out.println(store.displaySenderAndRecipient(sender));
            }else if (pick == 2){
                System.out.println("Longest stored message: " + store.longestStoredMessage());
            }else if (pick == 3){
                System.out.println("Enter the message ID: ");
                Message found = store.searchByMessageID(in.nextLine().trim());
                if (found == null){
                    System.out.println("No message found with that ID.");
                }else{
                    System.out.println("Recipient: " + found.getRecipient());
                    System.out.println("Message: " + found.getMessageText());
                }
            }else if (pick == 4){
                System.out.println("Enter the recipient's cell number: ");
                String[] results = store.searchByRecipient(in.nextLine().trim());
                if (results.length == 0){
                    System.out.println("No messages found for that recipient");
                }else{
                    for (int i = 0; i < results.length; i++){
                        System.out.println((i +1)+". "+results[i]);
                    }
                }
            }else if ( pick == 5){
                System.out.println("Enter the message Hash: ");
                System.out.println(store.deleteByHash(in.nextLine().trim().toUpperCase()));
                store.saveStoredToJson(JSON_FILE);
            }else if (pick == 6){
                System.out.println(store.storedMessagesReport());
            }else if (pick ==7){
                inMenu = false;
            }else{
                System.out.println("Invalid option. Please Choose 1 to 7.");
            }
        }
    }

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
       System.out.println("Enter cell phone number (e.g. +27838968976: )");
       String cellNumber = input.nextLine();
       System.out.println(obj.cellMessage(cellNumber));
       while (!obj.checkCellNumber(cellNumber)){
           System.out.print("Enter cell phone number (e.g. +27838968976: )");
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
       //---ChatApp
        System.out.println("\n Welcome to QuickChat!");
        int limit = readInt(input, "How many messages do you wish to enter? ");
        boolean messagesEntered = false;  
        boolean running = true;
            while(running){
                System.out.println("----Numeric Menu-----");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");
                int choice = readInt(input,"Choose an option (1 - 3): ");
                  
                  if(choice ==1){
                      if(messagesEntered){
                          System.out.println("you have already entered your " +limit+ " messages.");
                  }else{
                          for(int i = 1; i <= limit; i++ ){
                          System.out.println("---Entering Message " + i  + " of " + limit + "---");
                          
                          System.out.print("Enter recipient cell number (e.g., +27811324...): ");
                          String recipient = input.nextLine().trim();
                          
                          System.out.println(Message.checkRecipientCell(recipient));
                          while(!Message.isValidRecipient(recipient)){
                              System.out.println("Enter recipient cell number: ");
                              recipient = input.nextLine().trim();
                              System.out.println(Message.checkRecipientCell(recipient));
                          }
                          System.out.println("Enter your message: ");
                          String text = input.nextLine().trim();
                          System.out.println(Message.checkMessageLength(text));
                          while(text.length() >250){
                            System.out.print("Enter your message (Max 250 characters): ");
                            text = input.nextLine().trim();
                            System.out.println(Message.checkMessageLength(text));
                          }
                          Message msg = new Message(recipient,text, Message.generateID(), i);
                          
                          System.out.println("1. Send Message");
                          System.out.println("2. Disregard Message (Press 0 to delete)");
                          System.out.println("3. Store message in JSON file");
                          int option = readInt(input, "Choose an option (1 - 3): ");
                          while(option <1 || option >3){
                              option = readInt(input, "Please choose 1, 2 or 3.");
                          }
                          System.out.println(msg.sentMessage(option));
                          
                          if(option == 1){
                              System.out.println("\n"+ msg.getDetails());
                          }else if(option==2){
                              System.out.println(".");
                              String del = input.nextLine().trim();
                              if(del.equals("0")){
                                  System.out.println("Message deleted.");
                              }
                          }
                          }
                          messagesEntered = true;
                          System.out.println("\n Messages sent this session: \n" + Message.printMessages());
                          System.out.println("Total messages sent: "+ Message.returnTotalMessages());
                            }
                        }else if(choice ==2 ){
                            System.out.println("Coming Soon.");
                        }else if(choice ==3){
                             running = false;
                              }else{
                                    System.out.println("Invalid option. Please choose 1, 2 or 3");
                     }
                    }
      input.close();
    
    }
}