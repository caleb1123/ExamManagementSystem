/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ryy15
 */
public class RoomList {

    Room[] rooms;
    int size;
    String fName = "src\\data\\Rooms.dat";

    public RoomList() {
        this.rooms = new Room[100];
        this.size = 0;
    }

    public void addRoom() {
        String roomID;
        int capacity;
        String location;
        int pos;

        do {
            roomID = checkValidation.checkValid.InputPattern("Room ID: ", "[Rr][\\d]{5}");
            pos = -1;
            for (int i = 0; i < this.size; i++) {
                if (this.rooms[i].getRoomID().equals(roomID)) {
                    pos = i;
                    break;
                }
            }
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);

        capacity = checkValidation.checkValid.checkInt("Capacity: ", 0);
        location = checkValidation.checkValid.checkLength("Location: ", 0, 20);

        Room newRoom = new Room(roomID, capacity, location);
        this.add(newRoom);
    }

    private void add(Room room) {
        if (this.size == this.rooms.length) {
            this.rooms = Arrays.copyOf(this.rooms, this.rooms.length * 2);
        }
        this.rooms[this.size++] = room;
    }

    public void readFromFile() {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File does not exist!");
            return;
        }

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String roomID = st.nextToken().trim();
                int capacity = Integer.parseInt(st.nextToken().trim());
                String location = st.nextToken().trim();
                Room room = new Room(roomID, capacity, location);
                this.add(room);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Error reading file!");
        }
    }

    public void writeToFile() {
        if (size == 0) {
            System.out.println("Empty list!");
            return;
        }

        try {
            PrintWriter pw = new PrintWriter(fName);
            for (int i = 0; i < size; i++) {
                Room room = rooms[i];
                pw.println(room.getRoomID() + ", " + room.getCapacity() + ", " + room.getLocation());
            }
            pw.close();
            System.out.println("Writing file: DONE.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void viewRooms() {
        System.out.println("List of rooms:");
        System.out.format("+--------+----------+-----------------+\n");
        System.out.format("| RoomID | Capacity |     Location    |\n");
        System.out.format("+--------+----------+-----------------+\n");

        for (int i = 0; i < size; i++) {
            System.out.format("| %-6s | %-8d | %-15s |\n",
                    rooms[i].getRoomID(), rooms[i].getCapacity(), rooms[i].getLocation());
            System.out.format("+--------+----------+-----------------+\n");
        }
    }

    public void updateRoom() {
        // Ask for room ID
        System.out.print("Enter the room's ID: ");
        String roomId = checkValidation.checkValid.InputPattern("Room ID: ", "[Rr][\\d]{5}");

        // Search for room with the given ID
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (rooms[i].getRoomID().equals(roomId)) {
                index = i;
                break;
            }
        }

        // If room is found, update room information
        if (index >= 0) {
            System.out.println("Enter new room information (leave blank if you don't want to update):");
            int capacity = checkValidation.checkValid.checkInt("Capacity: ", 0);
            if (capacity > 0) {
                rooms[index].setCapacity(capacity);
            }

            String location = checkValidation.checkValid.checkLength("Location: ", 0, 20);
            if (!location.isEmpty()) {
                rooms[index].setLocation(location);
            }

            System.out.println("Room information has been updated.");
        } else {
            System.out.println("No room with ID " + roomId + " found.");
        }

        // Write changes to file
        writeToFile();
    }

   

}
