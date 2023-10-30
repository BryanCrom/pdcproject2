package pdcproject1;
import java.util.Scanner;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
/**
 *
 * @author Bryan Crombach & Jerome Joseph 
 */

public class MainApp { //Public class

    public static void main(String[] args) {
        Database DB = new Database();
        DB.createAccountTable("ACCOUNTS");
        DB.addAccount("Byan", "Crombach", "bryancrombach@gmail.com");
        DB.addAccount("Jerome", "Joseph", "JeromeJoseph@gmail.com");
        DB.addAccount("Luca", "Edwards", "JeromeJoseph@gmail.com");
        DB.printAccount(1);
        DB.printAccount(1);
        DB.printAccount(2);
        Scanner scanner = new Scanner(System.in);
        Set<String> enrollments = new HashSet<>();

        //Print title
        System.out.println("||============================================================||");
        System.out.println("||           Welcome to the Course Selection System!          ||");
        System.out.println("||============================================================||\n");
        
        //Get first name with input validation
        String firstName = "";
        boolean validFirstName = false;
        while (!validFirstName) {
            System.out.print("Enter your first name: ");
            firstName = scanner.nextLine();
            if (firstName.matches("[a-zA-Z]+")) {
                validFirstName = true;
            } else {
                System.out.println("Invalid input. Please enter letters only.");
            }
        }

        //Get last name with input validation
        String lastName = "";
        boolean validLastName = false;
        while (!validLastName) {
            System.out.print("Enter your last name: ");
            lastName = scanner.nextLine();
            if (lastName.matches("[a-zA-Z]+")) {
                validLastName = true;
            } else {
                System.out.println("Invalid input. Please enter letters only.");
            }
        }
        
        //Get email with input validation
        String email = "";
        boolean validEmail = false;
        while (!validEmail) {
            System.out.print("Enter your email: ");
            email = scanner.nextLine();
            if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                validEmail = true;
            } else {
                System.out.println("Invalid input. Please enter a valid email address.");
            }
        }

        //Generate random 6 digit student ID
        int studentID = generateRandomStudentID();
        
        //Student object creation
        Student student = new Student(studentID, firstName + " " + lastName, email);

        //Print users information
        System.out.println("\nThank you for signing up!\n");
        System.out.println("================================================================\n");
        System.out.println("Your student ID: " + student.getStudentID());
        System.out.println("Your name: " + student.getName());
        System.out.println("Your email: " + student.getEmail() + "\n\n================================================================\n");
        
        //Creates lectureRoom and labRoom instances
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
        Course course1 = new Course("ENSE701", "Contemporary Methods in Software Engineering", "This paper explores contemporary software \nengineering issues, methods, and tools, emphasizing \ncollaboration, with a focus on large software-intensive \nsystems.", 15, labRoom1, lectureRoom1);
        //Creates course instances
        Course course2 = new Course("COMP716", "Highly Secure Systems", "This paper focuses on advanced network security, \ncovering cryptographic algorithms, authentication, and data \nprotection.", 15, labRoom2, lectureRoom2);
        //Creates course instances
        Course course3 = new Course("ENSE601", "Software Team Project", "This paper focuses on collaborative software \ndevelopment, encompassing project management, quality control, \nand the expansion of development skills through team projects \nand assessments.", 15, labRoom3, lectureRoom3);
        //Creates course instances 
        Course course4 = new Course("ENGE600", "Engineering Management I", "This paper provides a comprehensive exploration of \nengineering management, including history, environmental \nresponsibility, ethics, organizational dynamics, and leadership.", 15, labRoom4, lectureRoom4);

        //Creates course catalog
        CourseCatalog catalog = new CourseCatalog();
        catalog.addCourse(course1);
        catalog.addCourse(course2);
        catalog.addCourse(course3);
        catalog.addCourse(course4);

        //Creates selection system
        CourseSelectionSystem system = new CourseSelectionSystem(catalog);

        //Save data to text file
        catalog.writeTxt();

        boolean exit = false;
        while (!exit) {
            System.out.println("Please select an option:");
            System.out.println("1. View courses");
            System.out.println("2. Select courses");
            System.out.println("3. View selected courses");
            System.out.println("4. Exit" + "\n");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 4) {
            switch (choice) {
                case 1: //Display course list
                    system.displayCourseList();
                    break;
                case 2: //Allow user to select courses
                    Set<String> enrolledCourses = system.selectCourses(student, catalog);
                    Iterator it1 = enrolledCourses.iterator();
                    while(it1.hasNext()){
                        enrollments.add((String) it1.next());
                    }
                    break;
                case 3: //Show selected courses
                    Iterator it2 = enrollments.iterator();
                    while(it2.hasNext()){
                        System.out.println(it2.next());
                    }
                    break;
                case 4: //Exit the program
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                }
                } else {
                    System.out.println("\nInvalid option. Please enter a number between 1 and 4.\n");
                }
            } else {
                System.out.println("\nInvalid input. Please enter a valid number between 1 and 4.\n");
                scanner.next();
            }
        }
    }
    private static int generateRandomStudentID() { //Generates random student ID
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
}