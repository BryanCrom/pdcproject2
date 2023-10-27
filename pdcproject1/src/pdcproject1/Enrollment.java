package pdcproject1;

/**
 *
 
@author Bryan Crombach & Jerome Joseph */
public class Enrollment {
    private int studentID;
    private String courseCode;
    private int semesterID;

    public Enrollment(int studentID, String courseCode, int semesterID){
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.semesterID = semesterID;
    }

    public int getStudentID() { //Get method
        return studentID;
    }

    public void setStudentID(int studentID) { //Set method
        this.studentID = studentID;
    }

    public String getCourseCode() { //Get method
        return courseCode;
    }

    public void setCourseCode(String courseCode) { //Set method
        this.courseCode = courseCode;
    }

    public int getSemesterID() { //Get method
        return semesterID;
    }

    public void setSemesterID(int semesterID) { //Set method
        this.semesterID = semesterID;
    }

    @Override
    public String toString() //toString method
    {
        return courseCode + ": Semester " + semesterID;
    }
}

