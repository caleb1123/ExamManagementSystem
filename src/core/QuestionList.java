/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static checkValidation.checkValid.sc;
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
public class QuestionList {

    Question[] questions;
    int size;
    String fName = "src\\data\\Question.dat";

    public QuestionList() {
        this.questions = new Question[1000];
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
                String questionID = stk.nextToken().trim();
                String question = stk.nextToken().trim();
                String answer = stk.nextToken().trim();

                Question q = new Question(questionID, question, answer);

                add(q);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

private void add(Question question) {
    if (this.size == this.questions.length) {
        this.questions = Arrays.copyOf(this.questions, this.questions.length * 2);
    }
    this.questions[this.size++] = question;
}

  public void writeToFile() {
        if (questions.length == 0) {
            System.out.println("Empty list!");
        } else {
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(fName);
                for (int i = 0; i < size; i++) {
                    pw.println(questions[i].getQuestionID()+ ", " + questions[i].getQuestion()+ ", " + questions[i].getAnswer());
                }
                pw.close();
                System.out.println("Writing file: DONE.");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                if (pw != null) {
                    pw.close();
                }
            }
        }
    }
    public void addQuestion() {
        String questionID;
        String question;
        String[] options = new String[4];
        String answer;

        // Prompt user to input question ID
        do {
            questionID = checkValidation.checkValid.InputPattern("Question ID: ", "[Qq][0-9]{5}");
            if (findQuestionByID(questionID) != null) {
                System.out.println("Question ID is duplicated!");
            }
        } while (findQuestionByID(questionID) != null);

        // Prompt user to input question content
        question = checkValidation.checkValid.checkLength("Question content: ", 1, 100);

        // Prompt user to input answer options and correct answer
        
        answer = checkValidation.checkValid.checkLength("Correct answer: ", 1, 50);

        // Create Question object and add to questions array
        Question q = new Question(questionID, question, answer);
        add(q);
        System.out.println("New question added successfully.");
    }


// Helper method to find a Question object by its ID in the questions array
    private Question findQuestionByID(String questionID) {
        for (int i = 0; i < size; i++) {
            if (questions[i].getQuestionID().equalsIgnoreCase(questionID)) {
                return questions[i];
            }
        }
        return null;
    }
public void removeQuestion() {
    // Get the ID of the question to be removed
    String questionID = checkValidation.checkValid.InputPattern("Question ID: ", "[Qq][\\d]{5}");

    // Find the index of the question with the specified ID in the array
    int questionIndex = -1;
    for (int i = 0; i < size; i++) {
        if (questions[i].getQuestionID().equalsIgnoreCase(questionID)) {
            questionIndex = i;
            break;
        }
    }

    // If the question exists, confirm deletion and remove it from the array
    if (questionIndex != -1) {
        System.out.print("Are you sure you want to remove this question? (Y/N) ");
        String confirmation = sc.nextLine();
        if (confirmation.equalsIgnoreCase("Y")) {
            for (int i = questionIndex; i < size - 1; i++) {
                questions[i] = questions[i + 1];
            }
            size--;
            System.out.println("Question removed successfully!");
        } else {
            System.out.println("Question removal cancelled.");
        }
    } else {
        System.out.println("Question not found!");
    }
}
public void viewQuestion() {
    System.out.println("List of questions:");
    System.out.format("+--------+---------------------------+-------------------------------+\n");
    System.out.format("|   ID   |           Question         |             Answer            |\n");
    System.out.format("+--------+---------------------------+-------------------------------+\n");

    for (int i = 0; i < size; i++) {
        System.out.format("| %-6s | %-25s | %-29s |\n",
                questions[i].getQuestionID(), questions[i].getQuestion(), questions[i].getAnswer());
        System.out.format("+--------+---------------------------+-------------------------------+\n");
    }
}

}
