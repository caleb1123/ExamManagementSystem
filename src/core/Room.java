/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author ryy15
 */
public class Room {
    String roomID;
    int capacity;
    String location;

    public Room(String roomID, int capacity, String location) {
        this.roomID = roomID;
        this.capacity = capacity;
        this.location = location;
    }
  @Override
    public boolean equals(Object obj){
        Room r = (Room)obj;
        return this.roomID.equalsIgnoreCase(r.roomID);
    }
    Room(String roomID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
}
