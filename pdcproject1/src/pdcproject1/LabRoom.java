package pdcproject1;

/**
 *
 
@author Bryan Crombach & Jerome Joseph */
public class LabRoom extends Room { //Public class
    private int numComputers; //Private variable

    public LabRoom(String roomNumber, int capacity, int numComputers) { //Constructor
        super(roomNumber, capacity);
        this.numComputers = numComputers;
    }

    public int getNumComputers() { //Get method
        return numComputers;
    }

    public void setNumComputers(int numComputers) { //Set method
        this.numComputers = numComputers;
    }

    @Override
    public String displayRoomDetails() {
        return "Room Number: " + getRoomNumber() + "\n" + "Capacity: " + getCapacity() + " people" + "\n" + "Number of Computers: " + numComputers;
    }
}