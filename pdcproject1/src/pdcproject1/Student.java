/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject1;

/**
 *
 
@author Bryan Crombach & Jerome Joseph */
public class Student { //public class
    private int studentID; //private variable
    private String name; //private variable
    private String email; //private variable

    public Student(int studentID, String name, String email) { //Constructor
        this.studentID = studentID;
        this.name = name;
        this.email = email;
    }

    // Getter and setter methods for attributes
    public int getStudentID() { //Get method
        return studentID;
    }

    public void setStudentID(int studentID) { //Set method
        this.studentID = studentID;
    }

    public String getName() { //Get method
        return name;
    }

    public void setName(String name) { //Set method
        this.name = name;
    }

    public String getEmail() { //Get method
        return email;
    }

    public void setEmail(String email) { //Set method
        this.email = email;
    }
}
