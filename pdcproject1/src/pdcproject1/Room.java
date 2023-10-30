package pdcproject1;

/**
 *
 
@author Bryan Crombach & Jerome Joseph */

public abstract class Room { //Public abstract class
    private String roomNumber; //Private variable
    private int capacity; //Private variable

    public Room(String roomNumber, int capacity) { //Constructor
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    public String getRoomNumber() {  //Get method
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) { //Set method
        this.roomNumber = roomNumber;
    }

    public int getCapacity() { //Get method
        return capacity;
    }

    public void setCapacity(int capacity) { //Set method
        this.capacity = capacity;
    }

    public abstract String displayRoomDetails();

    public abstract int getNumComputers();
    
    public abstract String getProjectorModel();
}
