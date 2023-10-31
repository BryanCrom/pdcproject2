/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject1;

/**
 *
 
@author Bryan Crombach & Jerome Joseph */
public abstract class Student { //public class
    private String firstName; //private variable
    private String lastName; //private variable
    private String email; //private variable

    public Student(String firstName, String lastName, String email) { //Constructor
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getter and setter methods for attributes
    public String getLastName() { //Get method
        return lastName;
    }

    public void setLastName(String lastName) { //Set method
        this.lastName = lastName;
    }

    public String getFirstName() { //Get method
        return firstName;
    }

    public void setFirstName(String firstName) { //Set method
        this.firstName = firstName;
    }

    public String getEmail() { //Get method
        return email;
    }

    public void setEmail(String email) { //Set method
        this.email = email;
    }
}
