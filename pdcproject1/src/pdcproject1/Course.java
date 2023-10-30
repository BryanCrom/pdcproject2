package pdcproject1;

/**
 *
 
@author Bryan Crombach & Jerome Joseph */
public class Course{ //Public class
    
    private String courseCode; //Private variable
    private String title; //Private variable
    private String description; //Private variable
    private int points; //Private variable
    private Room lab; //Private variable
    private Room lecture; //Private variable

    public Course(String courseCode, String title, String description, int points, Room lab, Room lecture) { //Constructor
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.points = points;
        this.lab = lab;
        this.lecture = lecture;
    }

    public String getCourseCode() { //Get method
        return this.courseCode;
    }

    public void setCourseCode(String courseCode) { //Set method
        this.courseCode = courseCode;
    }

    public String getTitle() { //Get method
        return this.title;
    }

    public void setTitle(String title) { //Set method
        this.title = title;
    }

    public String getDescription() { //Get method
        return this.description;
    }

    public void setDescription(String description) { //Set method
        this.description = description;
    }

    public int getPoints() { //Get method
        return this.points;
    }

    public void setPoints(int points) { //Set method
        this.points = points;
    }
    
    public Room getLab() { //Get method
        return this.lab;
    }
    
    public void setlab(Room lab) { //Set method
        this.lab = lab;
    }
    
    public Room getLecture() { //Get method
        return this.lecture;
    }
    
    public void setlecture(Room lecture) { //Set method
        this.lecture = lecture;
    }

    @Override
    public String toString() //toString method
    {
        return "Course Code: " + getCourseCode() + "\n" + "Title: " + getTitle() + "\n" + "Description: " + getDescription() + "\n" + "Points: " + getPoints() + "\n\nLecture Room:\n" + lecture.displayRoomDetails() + "\n\nLab Room:\n" + lab.displayRoomDetails() + "\n================================================================";
    }
}
