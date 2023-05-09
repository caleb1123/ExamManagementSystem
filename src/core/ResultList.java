package core;

import core.User.Student;
import core.User.StudentList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ResultList {

    private Result[] resultLists;
    private int size;
    private String fName = "src\\data\\Result.dat";
    private StudentList studentList;
    private ExamList examList;

    public ResultList(StudentList studentList,ExamList examList) {
        this.studentList = studentList;
        this.examList = examList;
        this.resultLists = new Result[1000];
        this.size = 0;
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
                    StringTokenizer stk = new StringTokenizer(line, ",");
                    String studentID = stk.nextToken().trim();
                    double score = Double.parseDouble(stk.nextToken().trim());
                    String examID = stk.nextToken().trim();
                    
                    

                    Result result = new Result(studentID, score, examID);
                add(result);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}




 private void add(Result result) {
    if (this.size == this.resultLists.length) {
        this.resultLists = Arrays.copyOf(this.resultLists, this.resultLists.length * 2);
    }
    this.resultLists[this.size++] = result;
}
public void viewResult() {
    if (resultLists == null || size == 0) {
        System.out.println("No results to show.");
        return;
    }
    System.out.println("List of results:");
    System.out.format("+------------+--------+--------+\n");
    System.out.format("|  Student   |   ID   | Score  |\n");
    System.out.format("+------------+--------+--------+\n");

    for (int i = 0; i < size; i++) {
        System.out.format("| %-10s | %-6s | %-6.2f |\n",
                resultLists[i].getStudentId(), resultLists[i].getExamID(),
                 resultLists[i].getScore());
        System.out.format("+------------+--------+--------+\n");
    }
}

public void searchStudentResult(String studentID) {
     
    boolean found = false;
    System.out.println("Results for student " + studentID + ":");
    System.out.format("+--------+------------+\n");
    System.out.format("| ExamID |   Score    |\n");
    System.out.format("+--------+------------+\n");

    for (int i = 0; i < size; i++) {
        if (resultLists[i].getStudentId().equals(studentID)) {
            found = true;
            System.out.format("| %-6s | %-10.2f |\n",
                    resultLists[i].getExamID(), resultLists[i].getScore());
            System.out.format("+--------+------------+\n");
        }
    }

    if (!found) {
        System.out.println("No results found for student " + studentID);
    }
}

}


