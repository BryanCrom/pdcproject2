/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject1;

/**
 *
 * @author bryan
 */
public class MainApp {
    public static void main(String[] args) {
        LectureRoom lectureRoom1 = new LectureRoom("WG402", 100, "Epson EB-W06");
        LabRoom labRoom1 = new LabRoom("WZ504", 30, 40);
        //Creates lectureRoom and labRoom instances
        LectureRoom lectureRoom2 = new LectureRoom("WG403", 120, "Epson EB-W06");
        LabRoom labRoom2 = new LabRoom("WZ505", 40, 50);
        //Creates lectureRoom and labRoom instances
        LectureRoom lectureRoom3 = new LectureRoom("WG404", 80, "Epson EB-W06");
        LabRoom labRoom3 = new LabRoom("WZ506", 20, 25);
        //Creates lectureRoom and labRoom instances
        LectureRoom lectureRoom4 = new LectureRoom("WG405", 90, "Epson EB-W06");
        LabRoom labRoom4 = new LabRoom("WZ507", 25, 25);
        
        //Creates course instances
        Course course1 = new Course("ENSE701", "Contemporary Methods in Software Engineering", "This paper explores contemporary software \nengineering issues, methods, and tools, emphasizing \ncollaboration, with a focus on large software-intensive \nsystems.", 15, labRoom1, lectureRoom1,1);
        //Creates course instances
        Course course2 = new Course("COMP716", "Highly Secure Systems", "This paper focuses on advanced network security, \ncovering cryptographic algorithms, authentication, and data \nprotection.", 15, labRoom2, lectureRoom2,1);
        //Creates course instances
        Course course3 = new Course("ENSE601", "Software Team Project", "This paper focuses on collaborative software \ndevelopment, encompassing project management, quality control, \nand the expansion of development skills through team projects \nand assessments.", 15, labRoom3, lectureRoom3,2);
        //Creates course instances 
        Course course4 = new Course("ENGE600", "Engineering Management I", "This paper provides a comprehensive exploration of \nengineering management, including history, environmental \nresponsibility, ethics, organizational dynamics, and leadership.", 15, labRoom4, lectureRoom4,2);

        //database showcase 
        
        Database DB = new Database();
        Account account1 = new Account("BryanC", "1111", "Bryan", "Crombach", "bryancrombach@gmail.com");
        Account account2 = new Account("JeromeJ", "2222", "Jerome", "Joseph", "JeromeJoseph@gmail.com");
        Account account3 = new Account("AdamD", "3333", "Adam", "Dickson", "bryancrombach@gmail.com");
        Account account4 = new Account("LucaE", "4444", "Luca", "Edwards", "LucaEdwards@gmail.com");
        Account account5 = new Account("LucaE", "44", "Luca", "Edwards", "LucaEdwards@gmail.com");
        Account account6 = new Account("LucaE", "4444", "Luca", "Edwards", "Luca@gmail.com");
        DB.addAccount(account1);
        DB.addAccount(account2);
        DB.addAccount(account3);
        DB.addAccount(account4);
        DB.printAccount(0);
        DB.printAccount(1);
        DB.printAccount(2);
        DB.addCourse(course1);
        DB.addCourse(course2);
        DB.addCourse(course3);
        DB.addCourse(course4);
        DB.printCourses();
        DB.selectCourse(course1, account1);
        DB.selectCourse(course2, account2);
        DB.selectCourse(course3, account4);
        DB.selectCourse(course4, account1);
        DB.getSelectedCourses(account1);
    }
    
}
