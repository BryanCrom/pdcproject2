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
    
    public static boolean validateUsername(String username){
        boolean valid = false;
        while(!valid){
            if(username.matches("^/S*$"))
                valid = true;
        }
        return valid;
    }
    
    public static boolean validatePassword(String password){
        boolean valid = false;
        while(!valid){
            if(password.matches("^/S*$"))
                valid = true;
        }
        return valid;
    }
    
    public static boolean validateFirstName(String firstName){
        boolean valid = false;
        while(!valid){
            if(firstName.matches("^[a-zA-Z]$"))
                valid = true;
        }
        return valid;
    }
    
    public static boolean validateLastName(String lastName){
        boolean valid = false;
        while(!valid){
            if(lastName.matches("^[a-zA-Z]$"))
                valid = true;
        }
        return valid;
    }
    
    public static boolean validateEmail(String email){
        boolean valid = false;
        while(!valid){
            if(email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
                valid = true;
        }
        return valid;
    }
    
    
}
