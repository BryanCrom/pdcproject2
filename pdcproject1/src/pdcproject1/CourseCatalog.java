package pdcproject1;

/**
 *
 * @author jeromejoseph
 */
//import statements
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {
    private final List<Course> courses;

    //Constructor
    public CourseCatalog() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) { //Adds course to catalog
        if(course != null)
            courses.add(course);
    }

    public List<Course> getCourses() { //Gets list of all courses
        return courses;
    }
    
    public Course getCourse(int index) //Gets specific course
    {
        return this.courses.get(index);
    }
    
    public int size(){//gets size
        return this.courses.size();
    }
    
    public Course get(int index){//gets element
        return courses.get(index);
    }
    
    public static String printTxt() //Print list of available courses by reading from a text file
    {
        BufferedReader br;
        String output = "Available Courses:\n\n================================================================\n";
        try
        {
            br = new BufferedReader(new FileReader("resources/courses.txt"));
            String str;
            while((str = br.readLine()) != null){
                output = output + str + "\n";
            }
            br.close();
        }
        catch(FileNotFoundException e)
        {   
            System.out.println("File not found");
        }
        catch(IOException e)
        {
            System.out.println("Error reading file courses.txt");
        }
        return output;
    }
    
    public void writeTxt() //Write course data to text file
    {
        PrintWriter pw;
        try
        {
            pw = new PrintWriter(new FileOutputStream("resources/courses.txt"));
            for(int i = 0; i < this.courses.size(); i++)
            {
                pw.append((i + 1) + ": " + this.courses.get(i) + "\n");
            }
            pw.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
}


