/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author oweth
 */

public class Login {
    
    //Declaring the variables that the user is going to use to store data
    
    String firstname;
    String lastname;
    String username;
    String password;
    String phoneNumber;
    
    //A Constructor that prepares default values for user data
    public Login() {
        this.username = "";
        this.password = "";
        this. phoneNumber = "";
        
    }
    
    //Returns the first name that is stored
    public String getFirstName() {
        return firstname;
    }
    
    //Returns the last name that is stored
    public String getLastName() {
        return lastname;
    }
    
    //Returns the username that is stored
    public String getUsername() {
        return username;
    }
    
    //Returns the password that is stored
    public String getPassword(){
        return password;
    }
    
    //Returns the password that is stored of the user
    public String getPhoneNumber(){
        return phoneNumber;
    }
    //First name validation
    public boolean checkFirstName(String firstname){
        //Declaring boolean variable 
        boolean hasCapital = false;
        
        for (int i = 0; i < firstname.length(); i++){
            char c = firstname.charAt(i);
            
            if(Character.isUpperCase(c)){
                hasCapital = true;
            }
        }
        return firstname.length() >=1 && hasCapital;
    }
    
    //Last name validation
    public boolean checkLastName(String lastname){
        //Declaring boolean variable
        boolean hasCapital = false;
        
        for  (int i = 0; i < lastname.length(); i++){
            char c = lastname.charAt(i);
            
            if(Character.isUpperCase(c)){
                hasCapital = true;
            }
            
        }
       return lastname.length() >=1 && hasCapital; 
    }
    
    /*Username's validation , username must contain at least about
      <=5 characters within the username and must also have an underscore
    */
    
    
    public boolean checkUserName(String username){
        return username.contains("_") && username.length() <=5;
    }
    
    //Password validation using for while loop and boolean methods , the password must have >=8`
    
    public boolean checkPasswordComplexity(String password) {
        //Declaring boolean variables
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        /*for while loop and if statements to check if the user's password contains the neccessary features which are,
        it has to have at least 8 characters, must have a capital letter , a number and a special character
        */
        
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }else if (Character.isDigit(c)) {
                hasNumber = true;
            }else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        
        return password.length() >=8 && hasCapital && hasNumber && hasSpecial;
    }
    
    /*Cellphone number validation, the cellphone number must have South
      Africa's unique international code for a cellphone number
    */
    
    public boolean checkCellphoneNumber(String phoneNumber) {
        return phoneNumber.startsWith("+27") && phoneNumber.length() <=12;
    }
    
    //Register user
    public String registerUser (String firstname,String lastname,String username, String password, String phoneNumber){
        
        if(!checkFirstName(firstname)){
            return "First name is incorrectly entered , must contain a Capital at the first letter";
        }
        
        if(!checkLastName(lastname)){
            return "Last name is incorrectly entered, must contain a capital letter at the first letter";
        }
        if(!checkUserName(username)){
           return "Username is not correctly entered, please ensure the username correctly, it has to contain an underscore  and must be no more than 5 characters."; 
        }
        
        if(!checkPasswordComplexity(password)){
            return "Password is not correctly entered, it has to consist of at least 8 characters , a number and a special character";
        }
        if (!checkCellphoneNumber(phoneNumber)){ 
            return "Cellphone number is incorrectly entered or does not contain the international code";
        }
        
        //This is to store the user's data
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        
        return "User is successfully registerd";
        
        
        
       //This looks if the login details matches the one that the user used to register with 
    }
    
    //Login User
    public boolean loginUser(String username, String password){
        if(this.username == null|| this.username.isEmpty()){
            System.out.println("False, username needs to be entered");
        }
        return this.username.equals(username) && this.password.equals(password);
    }
    
    //Return Login Status
    public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + username + " ,it is great to see you again";
        } else {
            return "Username or password is invalid, please try again";
        }
    }
}
