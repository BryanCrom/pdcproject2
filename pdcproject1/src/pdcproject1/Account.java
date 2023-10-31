/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject1;

/**
 *
 * @author bryan
 */
public class Account extends Student{
    
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int studentID;
    
    public Account(String username, String password, String firstName, String lastName, String email){
        super(firstName, lastName, email);
        this.username = username;
        this.password = password;
        this.studentID = StudentID.generateRandomStudentID();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
