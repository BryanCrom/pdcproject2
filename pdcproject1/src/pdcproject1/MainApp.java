package pdcproject1;

/**
 *
 * @author Bryan Crombach & Jerome Joseph 
 */

public class MainApp { //Public class

    public static void main(String[] args) {
        Room lectureRoom = new LectureRoom("WG402", 100, "Epson EB-W06"); 
        Room labRoom = new LabRoom("WZ504", 30, 40);
        //Creates lectureRoom and labRoom instances
        Room lectureRooms = new LectureRoom("WG403", 120, "Epson EB-W06");
        Room labRooms = new LabRoom("WZ505", 40, 50);
        
        //Creates course instances
        Course course = new Course("ENSE701", "Contemporary Methods in Software Engineering", "This paper explores contemporary software \nengineering issues, methods, and tools, emphasizing \ncollaboration, with a focus on large software-intensive \nsystems.", 15, labRoom, lectureRoom, 1);
        //Creates course instances
        Course courses = new Course("COMP716", "Highly Secure Systems", "This paper focuses on advanced network security, \ncovering cryptographic algorithms, authentication, and data \nprotection.", 15, labRooms, lectureRooms, 2);
        
        Database DB = new Database();
        DB.createAccountTable("ACCOUNTS");
        Account account1 = new Account("BryanCrom", "1111", "Bryan", "Crombach", "bryancrombach@gmail.com");
        Account account2 = new Account("JeromeJoseph", "2222", "Jerome", "Joseph", "JeromeJoseph@gmail.com");
        Account account3 = new Account("LucaEdwards", "3333", "Luca", "Edwards", "bryancrombach@gmail.com");
        Account account4 = new Account("LucaEdwards", "4444", "Luca", "Edwards", "LucaEdwards@gmail.com");
        Account account5 = new Account("LucaEdwards", "4444", "Luca", "Edwards", "Edwards@gmail.com");
        DB.addAccount(account1);
        DB.addAccount(account2);
        DB.addAccount(account3);
        DB.addAccount(account4);
        DB.addAccount(account5);
        DB.printAccount(0);
        DB.printAccount(1);
        DB.printAccount(2);
        DB.createCourseTables("COURSES", "LAB_ROOMS", "LECTURE_ROOMS");
        DB.addCourse(course);
        DB.addCourse(courses);
        DB.printCourses();
        DB.createSelectedTable("SELECTED_COURSES");
        DB.selectCourse(course, account1);
        DB.selectCourse(course, account2);
        DB.selectCourse(course, account4);
        DB.selectCourse(courses, account1);
        DB.getSelectedCourses(account1);
    }
}