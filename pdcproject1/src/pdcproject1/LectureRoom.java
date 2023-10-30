package pdcproject1;

/**
 *
 
@author Bryan Crombach & Jerome Joseph */
public class LectureRoom extends Room { //Public class
    private String projectorModel; //Private variable

    public LectureRoom(String roomNumber, int capacity, String projectorModel) { //Constructor
        super(roomNumber, capacity);
        this.projectorModel = projectorModel;
    }

    @Override
    public String getProjectorModel() { //Get method
        return projectorModel;
    }

    public void setProjectorModel(String projectorModel) { //Set method
        this.projectorModel = projectorModel;
    }

    @Override
    public String displayRoomDetails() {
        return "Room Number: " + getRoomNumber() + "\n" + "Capacity: " + getCapacity() + " people" + "\n" + "Projector Model: " + projectorModel;
    }

    @Override
    public int getNumComputers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
