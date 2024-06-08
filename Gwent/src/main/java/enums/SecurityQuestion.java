package enums;

import java.util.ArrayList;

public enum SecurityQuestion {
    QUESTION_1("", 0),
    QUESTION_2("", 1),
    QUESTION_3("", 2);

    private final String questionText;
    private final int questionNumber;
    private static final ArrayList<String> questions = new ArrayList<>();


    SecurityQuestion(String questionText, int questionNumber) {
        this.questionText = questionText;
        this.questionNumber = questionNumber;
    }

    public static String getListOfQuestions() {
        StringBuilder result = new StringBuilder();
        int i = 1;
        for (String question : questions) {
            result.append(i).append(". ").append(question).append("\n");
            i++;
        }
        return result.toString();
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }
}
