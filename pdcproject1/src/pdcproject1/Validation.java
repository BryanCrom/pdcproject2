/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject1;

/**
 *
 * @author bryan
 */
public class Validation {
    
    //validation for all usernames
    public static boolean validateUsername(String username){
        boolean valid = false;
        if(username.matches("//S*"))
            valid = true;
        return valid;
    }
    
    //validation for all passwords
    public static boolean validatePassword(String password){
        boolean valid = false;
        if(password.matches("//S*"))
            valid = true;
        return valid;
    }
    
    //validation for all first names
    public static boolean validateFirstName(String firstName){
        boolean valid = false;
        if(firstName.matches("^[a-zA-Z]+"))
            valid = true;
        return valid;
    }
    
    //validation for all last names
    public static boolean validateLastName(String lastName){
        boolean valid = false;
        if(lastName.matches("^[a-zA-Z]+"))
            valid = true;
        return valid;
    }
    
    //validation for all emails
    public static boolean validateEmail(String email){
        boolean valid = false;
        if(email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
            valid = true;
        return valid;
    }
}
