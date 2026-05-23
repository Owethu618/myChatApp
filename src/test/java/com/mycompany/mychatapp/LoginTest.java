/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mychatapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author oweth
 */
public class LoginTest {
    
    Login login = new Login();
    
    //Testing our username
    @Test
    public void testInvalidUsernameMessage(){
        
        String result = login.registerUser( "Owethhu ","Manene", "kyle!!!!!!!", "Ch&&@ke99!", "+27838968976");
        
        
        //We are using assertEquals to check for the result , whether its the same or not
        
        assertEquals("Username is not correctly formatted, please ensure  that your username contains an underscore and is no more than 5 characters", result);
        
    }
    //this checks the username, it must have an underscore "_"
    //
    @Test
    public void testValidUsername() {
        assertTrue(login.checkUserName("kyl_1"));
        
    }
    
    @Test
    public void testInvalidUsername_NoUnderscore() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    
    @Test
    public void testInvalidUserName_tooLong(){
        //Reasons why itis False, the username consist of more than 5 characters
        //it was supposed to be 5 characters
        assertFalse(login.checkUserName("kyyyyl"));
    }
    
    @Test
    public void testValidPassword(){
        assertTrue(login.checkPasswordComplexity("Ch&&@ke99!"));
    }
    
    @Test
    public void testInvalidPassword_NoCapital(){
        assertFalse(login.checkPasswordComplexity("ch&&@ke99!"));
    }
    
    @Test
    public void testInvalidPaaword_NoNumber(){
        // This looks for the password with no number
        // the result expected , it has to be false
        assertFalse(login.checkPasswordComplexity("Ch&&@ke!"));
    }
    
    @Test
    public void testValidCellPhoneNumber(){
        assertTrue(login.checkCellphoneNumber("+27796499327"));
    }
    
    @Test
    public void testInvalidCellphoneNumber(){
        assertFalse(login.checkCellphoneNumber("07964993"));
    }
    
    //Registration Test
    //Testing where the user registered successfully
    @Test
    public void testRegisterUser_Success(){
        String result = login.registerUser("Owethu","Manene","kyl_1", "Ch&&@ke99!", "+27796489328");
        assertEquals("User registered successfully", result);
    }
    @Test
    
    public void testRegisterUser_InvalidUsername(){
        String result = login.registerUser("Owethu","Manene","kyl1", "Ch&&@ke99!", "+27796499327");
        assertTrue(result.contains("Wrong username"));
        
    }
    
    @Test
    public void testRegisterUser_InvalidPassword(){
        String result = login.registerUser("Owethu" ,"Manene","kyl_1", "pass", "+27796499327");
        assertTrue(result.contains("Password incorrect"));
    }
    
    @Test
    public void testRegisterUser_InvalidPhone(){
        String result = login.registerUser("Owethu","Manene","kyl_1", "Ch&&@ke99!", "0796499327");
        assertTrue(result.contains("Cell number is incorrect"));
    }
        
        
        
        
    
    
    @Test
    public void testLoginSuccess(){
        //Registering the uset the user with the correct details
        login.registerUser("Owethu","Manene","kyl_1", "Ch&&@ke99!", "+27796499327");
        
        
    }  
}