/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.User.Student;
import core.User.StudentList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author ryy15
 */
public class ExamList {

    Exam[] exams;
    int size;
    String fName = "src\\data\\Exam.dat";
    RoomList rL;

    public ExamList(RoomList rL) {
        this.exams = new Exam[1000];
        this.rL = rL;
        this.size = 0;
    }

  public Exam getExamByID(String examId) {
    for (int i = 0; i < size; i++) {
        if (exams[i].getExamId().equals(examId)) {
            return exams[i];
        }
    }

    return null;
}

  
   public void add(Exam newExam) {
    if (size >= exams.length) {
        System.out.println("Cannot add more exams, the list is full!");
        return;
    }

    this.exams[this.size] = newExam;
    this.size++;

   
}

    

public void readFromFile() {
    File file = new File(fName);
    if (!file.exists()) {
        System.out.println("File does not exist!");
        System.exit(0);
    } else {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String examID = st.nextToken().trim();
                String subject = st.nextToken().trim();
                int duration = Integer.parseInt(st.nextToken().trim());
                String roomID = st.nextToken().trim();
                Exam exam = new Exam(examID, subject, duration, roomID);
                add(exam);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}



    public void writeToFile() {
        if (size == 0) {
            System.out.println("Empty list!");
        } else {
            try (PrintWriter pw = new PrintWriter(new File(fName))) {
                for (int i = 0; i < size; i++) {
                    pw.println(exams[i].getExamId() + ", " + exams[i].getSubject() + ", " + exams[i].getDuration() + ", " + exams[i].getRoomID());

                }
                System.out.println("Writing to file: DONE.");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
public void viewExam() {
    System.out.println("List of exams:");
    System.out.format("+--------+---------------------------+---------+----------+\n");
    System.out.format("|   ID   |          Subject          |  Hours  | Room ID  |\n");
    System.out.format("+--------+---------------------------+---------+----------+\n");

    for (int i = 0; i < size; i++) {
        System.out.format("| %-6s | %-25s | %-7d | %-8s |\n",
                exams[i].getExamId(), exams[i].getSubject(), exams[i].getDuration(), exams[i].getRoomID());
        System.out.format("+--------+---------------------------+---------+----------+\n");
    }
}


}
