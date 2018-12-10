package com.example.yijun.finaltruefinal;
import android.support.annotation.RequiresPermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class Subject {
    //private static Map<String, ArrayList<String>> history; for test use only
    private Map<String, ArrayList<String>> problemSet;

    public Subject(String pathQuestions, String pathAnswers) throws FileNotFoundException {
        try {
            File questionFile = new File(pathQuestions);
            File answerFile = new File(pathAnswers);
            if (!questionFile.exists() || !answerFile.exists() || questionFile.isDirectory() || answerFile.isDirectory()) {
                throw new FileNotFoundException();
            }
            problemSet = combineQA(readQuestions(answerFile), readAnswers(questionFile));
        } catch (FileNotFoundException e) {
            System.out.println("Illegal path or file not found.");
            e.printStackTrace();
        }
    }

/* test use; make sure to change everything to static
    public static void main(String[] args) throws FileNotFoundException {
        //make sure to call the readQuestions() method with the questions file and readAnswers() with answers file
        //may not work if the files are not written with the proper order or the number of questions and the answers differ
        history = combineQA(readQuestions("/Users/Ben/Desktop/historyQuestions.txt"), readAnswers("/Users/Ben/Desktop/historyAnswers.txt"));
        print(history);
    }
*/

    public Map<String, ArrayList<String>> getProblemSet() {
        return getProblemSet();
    }

    public void print() { //prints the questions and answers
        for (Map.Entry<String, ArrayList<String>> entry : problemSet.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            for (String aString : value) {
                System.out.println("Question : " + key + " - Answer : " + aString);
            }
        }
    }

    private ArrayList<String> readQuestions(File questions) throws FileNotFoundException {
        ArrayList<String> read = new ArrayList<>();
        Scanner scanner = new Scanner(questions);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            read.add(line);
        }
        return read;
    }

    private Map<Integer, ArrayList<String>> readAnswers(File answers) throws FileNotFoundException { //is a Map instead of an ArrayList like readQuestions()
        Map<Integer, ArrayList<String>> read = new LinkedHashMap<>();
        Scanner scanner = new Scanner(answers);
        int i = 0;
        String line = scanner.nextLine();
        while (scanner.hasNextLine()) {
            ArrayList<String> temp = new ArrayList<>();
            line = scanner.nextLine();
            //if the answers file doesn't begin with "///", the first answer choice will not be read; similar applies for if the file doesn't end with "///"
            while (!line.equals("///") && scanner.hasNext()) {
                temp.add(line);
                line = scanner.nextLine();
            }
            read.put(i, temp);
            i++;
        }
        return read;
    }

    private Map<String, ArrayList<String>> combineQA(ArrayList<String> questions, Map<Integer, ArrayList<String>> answers) { //helper function to put the questions and answers into the same Map
        Map<String, ArrayList<String>> combineResult = new LinkedHashMap<>();
        for (int i = 0; i < questions.size(); i++) {
            combineResult.put(questions.get(i), answers.get(i));
        }
        return combineResult;
    }
}
