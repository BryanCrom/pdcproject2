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
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO ACCOUNTS (STUDENT_ID, UNAME, PWORD, FNAME, LNAME, EMAIL) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String INSERT_COURSE_SQL = "INSERT INTO COURSES (COURSE_CODE, COURSE_NAME, DESCRIPTION, POINTS, SEMESTER) VALUES(?, ?, ?, ?, ?)";
    private static final String INSERT_LAB_ROOM_SQL = "INSERT INTO LAB_ROOMS (ROOM_CODE, COURSE_CODE, CAPACITY, COMPUTER_AMOUNT) VALUES(?, ?, ?, ?)";
    private static final String INSERT_LECTURE_ROOM_SQL = "INSERT INTO LECTURE_ROOMS (ROOM_CODE, COURSE_CODE, CAPACITY, PROJECTOR_MODEL) VALUES(?, ?, ?, ?)";
    private static final String INSERT_SELCTED_COURSE_SQL = "INSERT INTO SELECTED_COURSES (STUDENT_ID, COURSE_CODE, COURSE_NAME, DESCRIPTION, POINTS, SEMESTER) VALUES(?, ?, ?, ?, ?, ?)";
    
    private static final String GET_ACCOUNT_SQL = "SELECT * FROM ACCOUNTS";
    private static final String GET_COURSE_SQL = "SELECT * FROM COURSES";
    private static final String GET_LAB_ROOMS_SQL = "SELECT * FROM LAB_ROOMS";
    private static final String GET_LECTURE_ROOMS_SQL = "SELECT * FROM LECTURE_ROOMS";
    
    private static final String URL = "jdbc:derby:UniDB; create=true";
    private static final String DB_USERNAME = "DBUsername";
    private static final String DB_PASSWORD = "DBPassword";
    
    private int[] studentIDs = new int[20];
    private String[] accountUNames = new String[20];
    private String[] accountPWords = new String[20];
    private String[] accountLNames = new String[20];
    private String[] accountFNames = new String[20];
    private String[] accountEmails = new String[20];
    
    private String[] courseCodes = new String[20];
    private String[] courseNames = new String[20];
    private String[] descriptions = new String[20];
    private int[] points = new int[20];
    private int[] semesters = new int[20];
    
    private String[] labRoomCodes = new String[20];
    private String[] labCourseCodes = new String[20];
    private int[] labCapacity = new int[20];
    private int[] computerAmounts = new int[20];
    
    private String[] lectureRoomCodes = new String[20];
    private String[] lectureCourseCodes = new String[20];
    private int[] lectureCapacity = new int[20];
    private String[] projectorModels = new String[20];
    
    private int[] selectedStudentIDs = new int[20];
    private String[] selectedCourseCodes = new String[20];
    private String[] selectedCourseNames = new String[20];
    private String[] selectedDescriptions = new String[20];
    private int[] selectedPoints = new int[20];
    private int[] selectedSemesters = new int[20];
    
    private int[] accountSelectedStudentIDs = new int[20];
    private String[] accountSelectedCourseCodes = new String[20];
    private String[] accountSelectedCourseNames = new String[20];
    private String[] accountSelectedDescriptions = new String[20];
    private int[] accountSelectedPoints = new int[20];
    private int[] accountSelectedSemesters = new int[20];
    
    public Database() {
        establishConnection();
    }

    public void establishConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
                System.out.println(URL + " connection successful\n");
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
                statement.executeUpdate("CREATE TABLE " + tableName + " (STUDENT_ID INT PRIMARY KEY, UNAME VARCHAR(30), PWORD VARCHAR(30), FNAME VARCHAR(15), LNAME VARCHAR(15), EMAIL VARCHAR(40))");
                System.out.println("Table " + tableName + " created successfully.\n");
            }
        } catch (SQLException e) {
            System.err.println("Failed to create the table: " + e.getMessage());
        }
    }

    public boolean checkTableExisting(String newTableName) {
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

    public void addAccount(Account account) {
        getAccounts();
        if(!checkAccount(account)){
            try {
                PreparedStatement newAccount = conn.prepareStatement(INSERT_ACCOUNT_SQL);
                newAccount.setInt(1, account.getStudentID());
                newAccount.setString(2, account.getUsername());
                newAccount.setString(3, account.getPassword());
                newAccount.setString(4, account.getFirstName());
                newAccount.setString(5, account.getLastName());
                newAccount.setString(6, account.getEmail());
                newAccount.executeUpdate();
                System.out.println("Account added successfully.\n");
            } catch (SQLException e) {
                System.err.println("Add account error: " + e.getMessage());
            }
        }else
            System.out.println("Another account already uses this username or email\n");
    }
    
    public void getAccounts(){
        int count = 0;
        try{
            Statement statement = conn.createStatement();
            ResultSet accounts = statement.executeQuery(GET_ACCOUNT_SQL);
            while(accounts.next())
            {
                studentIDs[count] = accounts.getInt("STUDENT_ID");
                accountUNames[count] = accounts.getString("UNAME");
                accountPWords[count] = accounts.getString("PWORD");
                accountFNames[count] = accounts.getString("FNAME");
                accountLNames[count] = accounts.getString("LNAME");
                accountEmails[count] = accounts.getString("EMAIL");
                count++;
            }
        }
        catch(SQLException e){
            System.err.println("get account error: " + e.getMessage());
        }
    }
    
    public void printAccount(int index){
        getAccounts();
        System.out.println("USER\nUsername: " + accountUNames[index] + "\nStudent ID: " + studentIDs[index] + "\nName: " + accountFNames[index] + " " + accountLNames[index] + "\nEmail: " + accountEmails[index] + "\n");
    }
    
    public boolean checkAccount(Account account){
        boolean check = false;
        try{
            PreparedStatement findEmail = conn.prepareStatement("SELECT UNAME, EMAIL FROM ACCOUNTS WHERE UNAME=? OR EMAIL=?");
            findEmail.setString(1, account.getUsername());
            findEmail.setString(2, account.getEmail());
            ResultSet usedAccount = findEmail.executeQuery();
            while(usedAccount.next()){
                if(account.getEmail().equalsIgnoreCase(usedAccount.getString("EMAIL")) || account.getPassword().equals(usedAccount.getString("UNAME"))){
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
                statement.executeUpdate("CREATE TABLE " + tableName1 + " (COURSE_CODE VARCHAR(7) PRIMARY KEY, COURSE_NAME VARCHAR(50), DESCRIPTION VARCHAR(200), POINTS INT, SEMESTER INT)");
                System.out.println("Table " + tableName1 + " created successfully.\n");
            }
            if (!checkTableExisting(tableName2)) {
                statement.executeUpdate("CREATE TABLE " + tableName2 + " (ROOM_CODE VARCHAR(5), COURSE_CODE VARCHAR(7),CAPACITY INT, COMPUTER_AMOUNT INT, PRIMARY KEY (ROOM_CODE), FOREIGN KEY (COURSE_CODE) REFERENCES COURSES(COURSE_CODE))");
                System.out.println("Table " + tableName2 + " created successfully.\n");
            }
            if (!checkTableExisting(tableName3)) {
                statement.executeUpdate("CREATE TABLE " + tableName3 + " (ROOM_CODE VARCHAR(5), COURSE_CODE VARCHAR(7), CAPACITY INT, PROJECTOR_MODEL VARCHAR(15), PRIMARY KEY (ROOM_CODE), FOREIGN KEY (COURSE_CODE) REFERENCES COURSES(COURSE_CODE))");
                System.out.println("Table " + tableName3 + " created successfully.\n");
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
                newCourse.setInt(5, course.getSemester());
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
                
                System.out.println("Course added successfully.\n");
            } catch (SQLException e) {
                System.err.println("Add course error: " + e.getMessage());
            }
        }else
            System.out.println("Another course already uses this course code\n");
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
                semesters[count] = courses.getInt("SEMESTER");
                count++;
            }
        }
        catch(SQLException e){
            System.err.println("get courses error: " + e.getMessage());
        }
    }
    
    public void getLabs(){
        int count = 0;
        try{
            Statement statement = conn.createStatement();
            ResultSet labs = statement.executeQuery(GET_LAB_ROOMS_SQL);
            while(labs.next()){
                labRoomCodes[count] = labs.getString("ROOM_CODE");
                labCapacity[count] = labs.getInt("CAPACITY");
                computerAmounts[count] = labs.getInt("COMPUTER_AMOUNT");
                count++;
            }
        }
        catch(SQLException e){
            System.err.println("get labs error: " + e.getMessage());
        }
    }
    
    public void getLectures(){
        int count = 0;
        try{
            Statement statement = conn.createStatement();
            ResultSet lectures = statement.executeQuery(GET_LECTURE_ROOMS_SQL);
            while(lectures.next()){
                lectureRoomCodes[count] = lectures.getString("ROOM_CODE");
                lectureCapacity[count] = lectures.getInt("CAPACITY");
                projectorModels[count] = lectures.getString("PROJECTOR_MODEL");
                count++;
            }
        }
        catch(SQLException e){
            System.err.println("get labs error: " + e.getMessage());
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
        getLabs();
        getLectures();
        Semester sem = new Semester(semesters[index]);
        System.out.println(courseCodes[index] + ": " + courseNames[index] + "\n" + descriptions[index] + "\nPoints: " + points[index] + sem.toString() + "\n\nLAB: " + labRoomCodes[index] + "\nCapacity: " + labCapacity[index] + "\nNumber of computers: " + computerAmounts[index] + "\n\nLECTURE: " + lectureRoomCodes[index] + "\nCapacity: " + lectureCapacity[index] + "\nProjector model: " + projectorModels[index] + "\n================================================================");
    }
    
    public void printCourses(){
        try{
            Statement print = conn.createStatement();
            ResultSet courseAmount = print.executeQuery("SELECT COUNT(COURSE_CODE) as numberOfCourses FROM COURSES");
            courseAmount.next();
            int num = courseAmount.getInt("numberOfCourses");
            for(int i = 0; i < num; i++){
                printCourse(i);
            }
        }
        catch(SQLException e){
            System.err.println("print courses code error: " + e.getMessage());
        }
    }
    
    public void createSelectedTable(String tableName) {
        try {
            Statement statement = conn.createStatement();
            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (STUDENT_ID INT, COURSE_CODE VARCHAR(7), COURSE_NAME VARCHAR(50), DESCRIPTION VARCHAR(200), POINTS INT, SEMESTER INT)");
                System.out.println("Table " + tableName + " created successfully.\n");
            }
        } catch (SQLException e) {
            System.err.println("Failed to create the table: " + e.getMessage());
        }
    }
    
    public void selectCourse(Course course, Account account) {
        if(!checkSelectedCourse(account.getStudentID(), course.getCourseCode())){
            try {
                PreparedStatement newSelection = conn.prepareStatement(INSERT_SELCTED_COURSE_SQL);
                newSelection.setInt(1, account.getStudentID());
                newSelection.setString(2, course.getCourseCode());
                newSelection.setString(3, course.getTitle());
                newSelection.setString(4, course.getDescription());
                newSelection.setInt(5, course.getPoints());
                newSelection.setInt(6, course.getSemester());
                newSelection.executeUpdate();
                System.out.println("Course selected successfully.\n");
            } catch (SQLException e) {
                System.err.println("Select course error: " + e.getMessage());
            }
        }else
            System.out.println("User has already selected this course\n");
    }
    
    public boolean checkSelectedCourse(int id, String courseCode){
        boolean check = false;
        try{
            PreparedStatement findSelectedCourse = conn.prepareStatement("SELECT STUDENT_ID, COURSE_CODE FROM SELECTED_COURSES WHERE STUDENT_ID=? AND COURSE_CODE=?");
            findSelectedCourse.setInt(1, id);
            findSelectedCourse.setString(2, courseCode);
            ResultSet usedCourses = findSelectedCourse.executeQuery();
            while(usedCourses.next()){
                if(id == usedCourses.getInt("STUDENT_ID") && courseCode.equalsIgnoreCase(usedCourses.getString("COURSE_CODE"))){
                    check = true;
                }
            }
        }
        catch(SQLException e){
            System.err.println("check selected course error: " + e.getMessage());
        }
        return check;
    }
    
    public void getSelectedCourses(Account account){
        int count = 0;
        try{
            PreparedStatement findSelectedCourses = conn.prepareStatement("SELECT * FROM SELECTED_COURSES WHERE STUDENT_ID=?");
            findSelectedCourses.setInt(1, account.getStudentID());
            ResultSet slectedCourseAmount = findSelectedCourses.executeQuery();
            while(slectedCourseAmount.next())
            {
                selectedStudentIDs[count] = slectedCourseAmount.getInt("STUDENT_ID");
                selectedCourseCodes[count] = slectedCourseAmount.getString("COURSE_CODE");
                selectedCourseNames[count] = slectedCourseAmount.getString("COURSE_NAME");
                selectedDescriptions[count] = slectedCourseAmount.getString("DESCRIPTION");
                selectedPoints[count] = slectedCourseAmount.getInt("POINTS");
                selectedSemesters[count] = slectedCourseAmount.getInt("SEMESTER");
                count++;
            }
            for(int i = 0; i < count; i++){
                printSelectedCourse(account, i);
            }
        }
        catch(SQLException e){
            System.err.println("get selected courses error: " + e.getMessage());
        }
    }
    
    public void printSelectedCourse(Account account, int index){
        Semester sem = new Semester(semesters[index]);
        System.out.println(selectedCourseCodes[index] + ": " + selectedCourseNames[index] + "\n" + selectedDescriptions[index] + "\nPoints: " + selectedPoints[index] + sem.toString() + "\n================================================================");
    }
    
    public boolean login(String username, String password){
        boolean checkAccount = false;
        try{
            PreparedStatement loginInfo = conn.prepareStatement("SELECT UNAME, PWORD FROM ACOUNTS WHERE UNAME=? AND PWORD=?");
            loginInfo.setString(1, username);
            loginInfo.setString(2, password);
            ResultSet login = loginInfo.executeQuery();
            if(login != null){
                checkAccount = true;
            }
        }
        catch(SQLException e){
            System.err.println("login error: " + e.getMessage());
        }
        return checkAccount;
    }
}