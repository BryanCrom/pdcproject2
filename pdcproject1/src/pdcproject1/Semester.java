package pdcproject1;

/**
 *
 
@author Bryan Crombach & Jerome Joseph */


public class Semester { //Public class
    private int semesterID; //Private variable
    private String startDate; //Private variable
    private String endDate; //Private variable

    public Semester(int semesterID) { //Constructor
        this.semesterID = semesterID;
        if(semesterID == 1){
            this.startDate = "27 February";
            this.endDate = "23 June";
        }
        else if(semesterID == 2)
        {
            this.startDate = "17 July";
            this.endDate = "10 November";
        }

    }

    public int getSemesterID() { //Get method
        return semesterID;
    }

    public void setSemesterID(int semesterID) { //Set method
        this.semesterID = semesterID;
    }

    public String getStartDate() { //Get method
        return startDate;
    }

    public void setStartDate(String startDate) { //Set method
        this.startDate = startDate;
    }

    public String getEndDate() { //Set method
        return endDate;
    }

    public void setEndDate(String endDate) { //Set method
        this.endDate = endDate;
    }
    
    @Override
    public String toString(){
        return "\nSemester: " + semesterID + "\nStart date: " + startDate + "\nEnd date: " + endDate;
    }
}