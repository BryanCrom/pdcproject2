/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public final class Database {

    private Connection conn;
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO ACCOUNTS (FNAME, LNAME, EMAIL) VALUES(?, ?, ?)";
    private static final String INSERT_COURSE_SQL = "INSERT INTO COURSES (COURSE_CODE, COURSE_NAME, DESCRIPTION, POINTS) VALUES(?, ?, ?, ?)";
    private static final String INSERT_LAB_ROOM_SQL = "INSERT INTO LAB_ROOMS (ROOM_CODE, COURSE_CODE, CAPACITY, COMPUTER_AMOUNT) VALUES(?, ?, ?, ?)";
    private static final String INSERT_LECTURE_ROOM_SQL = "INSERT INTO LECTURE_ROOMS (ROOM_CODE, COURSE_CODE, CAPACITY, PROJECTOR_MODEL) VALUES(?, ?, ?, ?)";
    private static final String GET_ACCOUNT_SQL = "SELECT * FROM ACCOUNTS";
    private static final String GET_COURSE_SQL = "SELECT * FROM COURSES";
    private static final String URL = "jdbc:derby:UniDB; create=true";
    private static final String DB_USERNAME = "DBUsername";
    private static final String DB_PASSWORD = "DBPassword";
    private int[] accountIDs = new int[20];
    private String[] accountLNames = new String[20];
    private String[] accountFNames = new String[20];
    private String[] accountEmails = new String[20];
    private String[] courseCodes = new String[20];
    private String[] courseNames = new String[20];
    private String[] descriptions = new String[20];
    private int[] points = new int[20];
    public Database() {
        establishConnection();
    }

    public void establishConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
                System.out.println(URL + " connection successful");
            } 
            catch (SQLException e) {
                System.err.println("Connection error: " + e.getMessage());
            }
        }
    }

    public void createAccountTable(String tableName) {
        try {
            Statement statement = conn.createStatement();
            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, FNAME VARCHAR(15), LNAME VARCHAR(15), EMAIL VARCHAR(40))");
                System.out.println("Table " + tableName + " created successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to create the table: " + e.getMessage());
        }
    }

    private boolean checkTableExisting(String newTableName) {
        boolean tableExists = false;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, newTableName, null);
            tableExists = rsDBMeta.next();
            rsDBMeta.close();
        } catch (SQLException e) {
            System.err.println("Check table error: " + e.getMessage());
        }
        return tableExists;
    }

    public void addAccount(String firstName, String lastName, String email) {
        getAccounts();
        if(!checkEmail(email)){
            try {
                PreparedStatement newAccount = conn.prepareStatement(INSERT_ACCOUNT_SQL);
                newAccount.setString(1, firstName);
                newAccount.setString(2, lastName);
                newAccount.setString(3, email);
                newAccount.executeUpdate();
                System.out.println("Account added successfully.");
            } catch (SQLException e) {
                System.err.println("Add account error: " + e.getMessage());
            }
        }else
            System.out.println("Another account already uses this email");
    }
    
    public void getAccounts(){
        int count = 0;
        try{
            Statement statement = conn.createStatement();
            ResultSet accounts = statement.executeQuery(GET_ACCOUNT_SQL);
            while(accounts.next())
            {
                accountIDs[count] = accounts.getInt("ID");
                accountEmails[count] = accounts.getString("EMAIL");
                accountFNames[count] = accounts.getString("FNAME");
                accountLNames[count] = accounts.getString("LNAME");
                count++;
            }
        }
        catch(SQLException e){
            System.err.println("get account error: " + e.getMessage());
        }
    }
    
    public void printAccount(int index){
        getAccounts();
        System.out.println(accountIDs[index] + ": " + accountFNames[index] + " " + accountLNames[index] + " " + accountEmails[index]);
    }
    
    public boolean checkEmail(String email){
        boolean check = false;
        try{
            PreparedStatement findEmail = conn.prepareStatement("SELECT EMAIL FROM ACCOUNTS WHERE EMAIL=?");
            findEmail.setString(1, email);
            ResultSet usedEmail = findEmail.executeQuery();
            while(usedEmail.next()){
                if(email.equalsIgnoreCase(usedEmail.getString("EMAIL"))){
                    check = true;
                }
            }
        }
        catch(SQLException e){
            System.err.println("check email error: " + e.getMessage());
        }
        return check;
    }
    
    public void createCourseTables(String tableName1, String tableName2, String tableName3) {
        try {
            Statement statement = conn.createStatement();
            if (!checkTableExisting(tableName1)) {
                statement.executeUpdate("CREATE TABLE " + tableName1 + " (COURSE_CODE VARCHAR(7) PRIMARY KEY, COURSE_NAME VARCHAR(50), DESCRIPTION VARCHAR(200), POINTS INT)");
                System.out.println("Table " + tableName1 + " created successfully.");
            }
            if (!checkTableExisting(tableName2)) {
                statement.executeUpdate("CREATE TABLE " + tableName2 + " (ROOM_CODE VARCHAR(5), COURSE_CODE VARCHAR(7),CAPACITY INT, COMPUTER_AMOUNT INT, PRIMARY KEY (ROOM_CODE), FOREIGN KEY (COURSE_CODE) REFERENCES COURSES(COURSE_CODE))");
                System.out.println("Table " + tableName2 + " created successfully.");
            }
            if (!checkTableExisting(tableName3)) {
                statement.executeUpdate("CREATE TABLE " + tableName3 + " (ROOM_CODE VARCHAR(5), COURSE_CODE VARCHAR(7), CAPACITY INT, PROJECTOR_MODEL VARCHAR(15), PRIMARY KEY (ROOM_CODE), FOREIGN KEY (COURSE_CODE) REFERENCES COURSES(COURSE_CODE))");
                System.out.println("Table " + tableName3 + " created successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to create the table: " + e.getMessage());
        }
    }
    
    public void addCourse(Course course) {
        getCourses();
        if(!checkCourseCode(course.getCourseCode())){
            try {
                PreparedStatement newCourse = conn.prepareStatement(INSERT_COURSE_SQL);
                newCourse.setString(1, course.getCourseCode());
                newCourse.setString(2, course.getTitle());
                newCourse.setString(3, course.getDescription());
                newCourse.setInt(4, course.getPoints());
                newCourse.executeUpdate();
                
                PreparedStatement newLabRoom = conn.prepareStatement(INSERT_LAB_ROOM_SQL);
                newLabRoom.setString(1, course.getLab().getRoomNumber());
                newLabRoom.setString(2, course.getCourseCode());
                newLabRoom.setInt(3, course.getLab().getCapacity());
                newLabRoom.setInt(4, course.getLab().getNumComputers());
                newLabRoom.executeUpdate();
                
                PreparedStatement newLectureRoom = conn.prepareStatement(INSERT_LECTURE_ROOM_SQL);
                newLectureRoom.setString(1, course.getLecture().getRoomNumber());
                newLectureRoom.setString(2, course.getCourseCode());
                newLectureRoom.setInt(3, course.getLecture().getCapacity());
                newLectureRoom.setString(4, course.getLecture().getProjectorModel());
                newLectureRoom.executeUpdate();
                
                System.out.println("Course added successfully.");
            } catch (SQLException e) {
                System.err.println("Add course error: " + e.getMessage());
            }
        }else
            System.out.println("Another course already uses this course code");
    }
    
    public void getCourses(){
        int count = 0;
        try{
            Statement statement = conn.createStatement();
            ResultSet courses = statement.executeQuery(GET_COURSE_SQL);
            while(courses.next())
            {
                courseCodes[count] = courses.getString("COURSE_CODE");
                courseNames[count] = courses.getString("COURSE_NAME");
                descriptions[count] = courses.getString("DESCRIPTION");
                points[count] = courses.getInt("POINTS");
                count++;
            }
        }
        catch(SQLException e){
            System.err.println("get courses error: " + e.getMessage());
        }
    }
    
    public boolean checkCourseCode(String courseCode){
        boolean check = false;
        try{
            PreparedStatement findCourseCode = conn.prepareStatement("SELECT COURSE_CODE FROM COURSES WHERE COURSE_CODE=?");
            findCourseCode.setString(1, courseCode);
            ResultSet usedEmail = findCourseCode.executeQuery();
            while(usedEmail.next()){
                if(courseCode.equalsIgnoreCase(usedEmail.getString("COURSE_CODE"))){
                    check = true;
                }
            }
        }
        catch(SQLException e){
            System.err.println("check course code error: " + e.getMessage());
        }
        return check;
    }
    
    public void printCourse(int index){
        getCourses();
        System.out.println(courseCodes[index] + ": " + courseNames[index] + "\n" + descriptions[index] + "\n" + points[index] + "\n");
    }
    
    public void createSelectedTable(String tableName) {
        try {
            Statement statement = conn.createStatement();
            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (COURSE_CODE VARCHAR(7) PRIMARY KEY, COURSE_NAME VARCHAR(50), DESCRIPTION VARCHAR(200), POINTS INT)");
                System.out.println("Table " + tableName + " created successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to create the table: " + e.getMessage());
        }
    }
}