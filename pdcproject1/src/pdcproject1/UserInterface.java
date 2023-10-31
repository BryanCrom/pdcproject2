package pdcproject1;

/**
 *
 * @author jeromejoseph and bryancrombach
 */
public interface UserInterface {

    public void establishConnection();
    
    public void createAccountTable(String tableName);
    
    public boolean checkTableExisting(String newTableName);
    
    public void addAccount(Account account);
    
    public void getAccounts();
    
    public void printAccount(int index);
    
    public boolean checkAccount(Account account);
    
    public void createCourseTables(String tableName1, String tableName2, String tableName3);
    
    public void addCourse(Course course);
    
    public void getCourses();
    
    public void getLabs();
    
    public void getLectures();
    
    public boolean checkCourseCode(String courseCode);
    
    public void printCourse(int index);
    
    public void printCourses();
    
    public void createSelectedTable(String tableName);
    
    public void selectCourse(Course course, int id);
    
    public boolean checkSelectedCourse(int id, String courseCode);
    
    public void getSelectedCourses();
    
    public boolean login(String username, String password);
}
